package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;


public class HeartrateMeasurement extends Measurement {


    public HeartrateMeasurement(BluetoothGattCharacteristic characteristic, String serviceUUID) {
        super(characteristic, serviceUUID);
        unit = "bpm";
        setValue();
    }

    public HeartrateMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);
        unit = "bpm";
        setValue();
    }

    public HeartrateMeasurement() {
        super();
    }

    @Override
    public Object getValue() {
        return value;
    }

    private void setValue(){
        Object temp = (characteristic.getValue()[1] << 8 | characteristic.getValue()[0]);
        value = /*temp.equals(-1)?"No skin detected":temp.equals(-2)?"Calculating value":*/temp;
        valueType = value.getClass();
    }

    @Override
    public String getValueString() {
        return name+": "+value+ (value instanceof String?"":unit)
//                    + showCharacteristicContent()
                ;
    }
    @Override
    public String toInfluxLine() {
        return null;
    }

}
