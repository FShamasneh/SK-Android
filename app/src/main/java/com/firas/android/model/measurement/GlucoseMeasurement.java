package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Date;
import java.util.List;


public class GlucoseMeasurement extends Measurement {

    // offsets
    private int sequenceNumberOffset;
    private int baseTimeOffset;
    private int timeDiffOffset;
    private int glucoseConcentrationOffset;
    private int typeLocationOffset;
    private int sensorStatusOffset;

    public static final String[] TYPES = new String[]{
            "", // 0 - reserved"
            "Capillary Whole blood",
            "Capillary Plasma",
            "Venous Whole blood",
            "Venous Plasma",
            "Arterial Whole blood",
            "Arterial Plasma",
            "Undetermined Whole blood",
            "Undetermined Plasma",
            "Interstitial Fluid (ISF)",
            "Control Solution",
            "", "", "", "", "" // 11-15 reserved
    };

    public static final String[] LOCATIONS = new String[]{
            "", // 0 - reserved",
            "Finger",
            "Alternate Site Test (AST)",
            "Earlobe",
            "Control solution",
            "", "", "", "", "", "", "", "", "", "", // 5-14 samples
            "Sample Location value not available"
    };

    public static final String[] STATES = new String[]{
            "Device battery low at time of measurement",
            "Sensor malfunction or faulting at time of measurement",
            "Sample size for blood or control solution insufficient at time of measurement",
            "Strip insertion error",
            "Strip type incorrect for device",
            "Sensor result higher than the device can process",
            "Sensor result lower than the device can process",
            "Sensor temperature too high for valid test/result at time of measurement",
            "Sensor temperature too low for valid test/result at time of measurement",
            "Sensor read interrupted because strip was pulled too soon at time of measurement",
            "General device fault has occurred in the sensor",
            "Time fault has occurred in the sensor and time may be inaccurate",
            "", "", "", "" // 12-15 reserved
    };

    public GlucoseMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);

        // calculate the offsets depending on the flags
        sequenceNumberOffset = flagOffset + 1; // 16 bit for mandatory sequence number
        baseTimeOffset = sequenceNumberOffset + 2; // 16y + 8m + 8d + 8h + 8min + 8s - org.bluetooth.characteristic.date_time
        timeDiffOffset = baseTimeOffset + 7; // 16 bit time offset depending on flag 0
        glucoseConcentrationOffset = isFlagSet(0) ? timeDiffOffset + 2 : timeDiffOffset; // 16 bit
        typeLocationOffset = isFlagSet(1) ? glucoseConcentrationOffset + 2 : glucoseConcentrationOffset; // 2 nibbles type and status
        sensorStatusOffset = isFlagSet(1) ? typeLocationOffset + 1 : typeLocationOffset;

        valueType = getValue().getClass();
    }

    @Override
    public Object getValue() {
        return getGlucoseConcentration();
    }

    /**
     * Prints the mass unit of the glucose concentration.
     *
     * @return
     */
    public String getUnit() {
        return isFlagSet(2) ? "mol/L" : "kg/L";
    }

    /**
     * Returns the sequence number of the measurement.
     */
    public int getSequenceNumber() {
        return characteristic.getIntValue(
                BluetoothGattCharacteristic.FORMAT_UINT16, sequenceNumberOffset);
    }

    /**
     * Returns the base time.
     */
    public Date getBaseTime() {
        return readTime(baseTimeOffset);
    }

    /**
     * Returns the time offset in minutes.
     *
     * @return 0, if no information given.
     */
    public int getTimeOffset() {
        if (isFlagSet(0))
            return characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_SINT16, timeDiffOffset);
        else return 0;
    }

    /**
     * Returns the parsed glucose concentration in units.
     *
     * @return 0, if no information given
     */
    public float getGlucoseConcentration() {
        if (isFlagSet(1))
            return characteristic.getFloatValue(BluetoothGattCharacteristic.FORMAT_SFLOAT, glucoseConcentrationOffset);
        else return 0;
    }

    public String getValueString() {
        if (isFlagSet(1))
            return ""+characteristic.getFloatValue(BluetoothGattCharacteristic.FORMAT_SFLOAT, glucoseConcentrationOffset);
        else return "0";
    }

    /**
     * Returns a description of the measurement type.
     *
     * @return empty string, if no information given
     */
    public String getType() {
        if (!isFlagSet(1)) return "";

        BitSet fullByte = BitSet.valueOf(new byte[]{characteristic.getValue()[typeLocationOffset]});
        BitSet lowerNibble = fullByte.get(0, 4);
        int index = readUnsignedInt(lowerNibble);

        return TYPES[index];
    }

    /**
     * Returns a description of the measuring location.
     *
     * @return empty string, if no information given
     */
    public String getSampleLocation() {
        if (!isFlagSet(1)) return "";

        BitSet fullByte = BitSet.valueOf(new byte[]{characteristic.getValue()[typeLocationOffset]});
        BitSet upperNibble = fullByte.get(4, 8);
        int index = readUnsignedInt(upperNibble);

        return LOCATIONS[index];
    }

    /**
     * Returns a list of all the sensor status information the glucose device has given.
     *
     * @return
     */
    public List<String> getSensorStatus() {
        List<String> result = new ArrayList<>();
        if (!isFlagSet(3)) return result;

        // The states are described by 16 bit bitmap
        byte[] statusArray = {
                characteristic.getValue()[sensorStatusOffset],
                characteristic.getValue()[sensorStatusOffset + 1]
        };
        BitSet statusFlags = BitSet.valueOf(statusArray);

        // get the descriptions
        for (int i = 0; i < 16; i++) {
            if (statusFlags.get(i)) result.add(STATES[i]);
        }

        return result;
    }

    public String toInfluxLine() {
        StringBuilder builder = new StringBuilder();

        // Name of the measurement
        builder.append("Glucose");

        // tags
        if(!"".equals(this.getSampleLocation())) {
            String location = getSampleLocation().replace(" ","\\ ").replace(",","\\,");
            builder.append(",sample_location=").append(location);
        }
        // builder.append(",sequence_number=").append(getSequenceNumber());
        if(this.getTimeOffset() > 0) {
            builder.append(",time_offset=").append(getTimeOffset());
        }
        if(!"".equals(this.getType())) {
            String type = getType().replace(" ","\\ ").replace(",","\\,");
            builder.append(",type=").append(type);
        }
        String unit = getUnit().replace(" ", "\\ ").replace(",", "\\,");
        builder.append(",unit=").append(unit);

        // separator
        builder.append(" ");

        // measurement values
        builder.append("glucose_concentration=").append(getGlucoseConcentration());

        // separator
        builder.append(" ");

        // Unix time stamp
        builder.append(getBaseTime().getTime());
        // influx db wants the time in nanoseconds, not milliseconds
        builder.append("000000");

        return builder.toString();
    }

    /**
     * Converts glucose measurement data into a human-readable string.
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        // sequence number
        builder.append("Sequence Number: ").append(this.getSequenceNumber());

        // Base time and if existing, time offset
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy HH:mm:ss Z");
        builder.append("\nBase Time: ").append(dateFormat.format(this.getBaseTime()));
        if (this.getTimeOffset() > 0) {
            builder.append("\nTime Offset: ").append(this.getTimeOffset()).append(" min");
        }

        // glucose concentration in value and unit
        builder.append("\nGlucose Concentration: ").append(this.getGlucoseConcentration());
        builder.append(" ").append(this.getUnit());

        // if existing, type and sample location
        if (!"".equals(this.getType())) {
            builder.append("\nType: ").append(this.getType());
        }
        if (!"".equals(this.getSampleLocation())) {
            builder.append("\nSample Location: ").append(this.getSampleLocation());
        }

        // Sensor status announciations
        List<String> sensorStates = this.getSensorStatus();
        if (sensorStates.isEmpty()) {
            builder.append("\nNo sensor announciations.");
        } else {
            builder.append("\nSensor states:");
            for (String state : sensorStates) {
                builder.append("\n- ").append(state);
            }
        }

        return builder.toString();
    }

}
