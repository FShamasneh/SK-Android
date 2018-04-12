package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;

import com.firas.android.utils.BleNamesResolver;
import com.firas.android.utils.DeviceType;
import com.firas.android.utils.SensorType;

import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;



public abstract class Measurement {

    private int id;

    // underlying byte array
    protected BluetoothGattCharacteristic characteristic;
    protected int flags;
    protected Date time;
    private String deviceAddress;
    private DeviceType deviceType;
    private SensorType sensorType;
    protected Object value;
    protected Class valueType;
    protected String unit;
    protected String name;
    protected String serviceUUID;

    // offset
    protected int flagOffset;

    public Measurement(BluetoothGattCharacteristic characteristic) {
        // take the GATT coded data
        this.characteristic = characteristic;
        name = BleNamesResolver.resolveCharacteristicName(characteristic.getUuid().toString());
        // take the flags
        this.flags = characteristic.getValue()[0];
        this.flagOffset = 0;
        this.time = new Date(System.currentTimeMillis());
    }

    public Measurement(BluetoothGattCharacteristic characteristic,String serviceUUID) {
        // take the GATT coded data
        this.characteristic = characteristic;
        name = BleNamesResolver.resolveCharacteristicName(characteristic.getUuid().toString());
        this.serviceUUID = serviceUUID;
        // take the flags
        this.flags = characteristic.getValue()[0];
        this.flagOffset = 0;
        this.time = new Date(System.currentTimeMillis());
    }

    public Measurement(){
    }

    /**
     * Checks, if the bit at the given position is set.
     *
     * @param data word to be checked
     * @param pos  position of the bit
     * @return true, if the bit on the position is checked, else false.
     */
    protected boolean isBitSet(int data, int pos) {
        int mask = 1 << pos;
        return (data & mask) == mask;
    }

    /**
     * Gives the status of the flags in the first byte of the underlying characteristic-
     *
     * @param pos position of the bit starting with zero
     * @return true if the flag is set to 1
     */
    protected boolean isFlagSet(int pos) {
        return isBitSet(flags, pos);
    }

    /**
     * Creates a pseudo unsigned int out of the given value.
     *
     * @return
     */
    protected int readUnsignedInt(BitSet bits) {
        int result = 0;
        for (int i = 0; i < bits.length(); i++) {
            if (bits.get(i)) result += Math.pow(2, i);
        }
        return result;
    }

    public  abstract Object getValue();

    public abstract String getValueString();

    public Class getValueType(){
        return valueType;
    };

    public String showCharacteristicContent(){
        String value = "";
        int i = 0;
        if(characteristic!=null)for(byte b:characteristic.getValue()) {
            value += "\nbyte["+i+"]= "+b;
            i++;
        }
        return value;
    }

    /**
     * Returns the 7-bit time by reading data till the given offset
     */
    protected Date readTime(int startOffset) {
        int yearOffset = startOffset;
        int monthOffset = yearOffset + 2;
        int dayOffset = monthOffset + 1;
        int hourOffset = dayOffset + 1;
        int minuteOffset = hourOffset + 1;
        int secondOffset = minuteOffset + 1;

        int year = characteristic.getIntValue(
                BluetoothGattCharacteristic.FORMAT_UINT16, yearOffset);
        int month = characteristic.getIntValue(
                BluetoothGattCharacteristic.FORMAT_UINT8, monthOffset);
        int day = characteristic.getIntValue(
                BluetoothGattCharacteristic.FORMAT_UINT8, dayOffset);
        int hour = characteristic.getIntValue(
                BluetoothGattCharacteristic.FORMAT_UINT8, hourOffset);
        int minute = characteristic.getIntValue(
                BluetoothGattCharacteristic.FORMAT_UINT8, minuteOffset);
        int seconds = characteristic.getIntValue(
                BluetoothGattCharacteristic.FORMAT_UINT8, secondOffset);

        Calendar cal = GregorianCalendar.getInstance();
        // Attention: MONTH field in Calendar starts with 0
        cal.set(year, month - 1, day, hour, minute, seconds);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * Serializes the measurement data (tags and fields) to the line protocol.
     * The line protocol is a space-efficient single line format used by InfluxDB.
     * @return
     */
    public abstract String toInfluxLine();

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        // Base time and if existing, time offset

        builder.append(getTimeString());

        builder.append("  address: "+getDeviceAddress());

        // value and unit
        builder.append("\n"+this.getValueString());

        return builder.toString();
    };

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTimeString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy HH:mm:ss Z");
        return dateFormat.format(time);
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public String getUnit() {
        return unit;
    }

    public String getName() {
        return name;
    }

    public BluetoothGattCharacteristic getCharacteristic() {
        return characteristic;
    }

    public String getServiceUUID() {
        return serviceUUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setValueType(Class valueType) {
        this.valueType = valueType;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }
}
