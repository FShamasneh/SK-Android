package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;

import java.text.SimpleDateFormat;
import java.util.Date;


public class WeightMeasurement extends Measurement{

    // offsets in bytes
    private int weightOffset;
    private int timestampOffset;
    private int userIdOffset;
    private int bmiOffset;
    private int heightOffset;

    public static final String UNIT_SCI_WEIGHT = "kg";
    public static final int FACTOR_SCI_WEIGHT = 200;
    public static final String UNIT_SCI_HEIGHT = "m";
    public static final int FACTOR_SCI_HEIGHT = 1000;
    public static final String UNIT_IMP_WEIGHT = "lb";
    public static final int FACTOR_IMP_WEIGHT = 100;
    public static final String UNIT_IMP_HEIGHT = "in";
    public static final int FACTOR_IMP_HEIGHT = 10;
    public static final int FACTOR_BMI = 10;

    // flag positions
    private static final int FLAG_UNIT = 0;
    private static final int FLAG_TIMESTAMP = 1;
    private static final int FLAG_USERID = 2;
    private static final int FLAG_BMI_HEIGHT = 3;

    public WeightMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);

        // calculate the offsets depending on the flags
        weightOffset = flagOffset + 1; // 16 bits for weight indication
        timestampOffset = weightOffset + 2; // 56 bits for time date field
        userIdOffset = isFlagSet(FLAG_TIMESTAMP) ? timestampOffset + 7 : timestampOffset;
        bmiOffset = isFlagSet(FLAG_USERID) ? userIdOffset + 1 : userIdOffset;
        heightOffset = isFlagSet(FLAG_BMI_HEIGHT) ? bmiOffset + 2 : bmiOffset;
        valueType = getValue().getClass();
    }

    @Override
    public Object getValue() {
        return getWeight();
    }

    @Override
    public String getValueString() {
        return "Weight: "+getWeight()+ "\n Height: "+getHeight();
    }

    /**
     * Prints the weight unit depending on the flag.
     *
     * @return
     */
    public String getWeightUnit() {
        return isFlagSet(FLAG_UNIT) ? UNIT_IMP_WEIGHT : UNIT_SCI_WEIGHT;
    }

    /**
     * Prints the height unit depending on the flag
     *
     * @return
     */
    public String getHeightUnit() {
        return isFlagSet(FLAG_UNIT) ? UNIT_IMP_HEIGHT : UNIT_SCI_HEIGHT;
    }

    /**
     * Returns the base time or null if not given.
     */
    public Date getBaseTime() {
        if(isFlagSet(FLAG_TIMESTAMP)) {
            return readTime(timestampOffset);
        }
        return null;
    }

    /**
     * Returns the parsed weight in units.
     *
     * @return
     */
    public double getWeight() {
        double baseVal = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, weightOffset);
        double converted;
        if (isFlagSet(FLAG_UNIT)) {
            converted = baseVal / FACTOR_IMP_WEIGHT;
        } else {
            converted = baseVal / FACTOR_SCI_WEIGHT;
        }
        return converted;
    }

    /**
     * Returns the parsed weight in units.
     *
     * @return
     */
    public boolean weightFailed() {
        int baseVal = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, weightOffset);
        return baseVal == 0xFFFF;
    }

    public int getUserId() {
        if(isFlagSet(FLAG_USERID)) {
            return characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, userIdOffset);
        }
        return -1;
    }

    public double getBmi() {
        if(isFlagSet(FLAG_BMI_HEIGHT)) {
            int base = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, bmiOffset);
            return base / 10;
        }
        return -1;
    }

    public double getHeight() {
        if(!isFlagSet(FLAG_BMI_HEIGHT)) {
            return -1;
        }

        int base = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, heightOffset);
        double converted;
        if(isFlagSet(FLAG_UNIT)) {
            converted = base / FACTOR_IMP_WEIGHT;
        } else {
            converted = base / FACTOR_SCI_HEIGHT;
        }
        return converted;

    }

    public String toInfluxLine() {
        StringBuilder builder = new StringBuilder();

        // Name of the measurement
        builder.append("Weight");

        // tags
        if(isFlagSet(FLAG_BMI_HEIGHT)) {
            builder.append(",height_unit=").append(getHeightUnit());
        }
        if(isFlagSet(FLAG_USERID)) {
            builder.append(",user_id=").append(getUserId());
        }
        builder.append(",weight_unit=").append(getWeightUnit());

        // separator
        builder.append(" ");

        // measurements
        builder.append("weight=").append(getWeight());
        if(isFlagSet(FLAG_BMI_HEIGHT)) {
            builder.append(",height=").append(getHeight());
            builder.append(",bmi=").append(getBmi());
        }

        if(isFlagSet(FLAG_TIMESTAMP)) {
            // separator
            builder.append(" ");

            // time in unix format
            builder.append(getBaseTime().getTime());
            // by default, influx db wants the time in nano-seconds, not milliseconds
            builder.append("000000");
        }

        return builder.toString();
    }

    /**
     * Converts glucose measurement data into a human-readable string.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // Weight
        builder.append("Weight: ");
        if(weightFailed()) {
            builder.append("Measurement failed");
        } else {
            builder.append(getWeight()).append(" ").append(getWeightUnit());
        }

        // Base time and if existing, time offset
        if(isFlagSet(FLAG_TIMESTAMP)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy HH:mm:ss Z");
            builder.append("\nTime: ").append(dateFormat.format(getBaseTime()));
        }

        // User id, if set
        if(isFlagSet(FLAG_USERID)) {
            int userId = getUserId();
            builder.append("\nUser ID: ").append(userId == 0xFF ? "unknown" : userId);
        }

        // BMI and height
        if(isFlagSet(FLAG_BMI_HEIGHT)) {
            builder.append("\nBMI: ").append(getBmi());
            builder.append("\nHeight: ").append(getHeight()).append(" ").append(getHeightUnit());
        }

        return builder.toString();
    }

//    public void setUnit(String unit) {
//
//    }
//
//    public void setName(String name) {
//
//    }

}
