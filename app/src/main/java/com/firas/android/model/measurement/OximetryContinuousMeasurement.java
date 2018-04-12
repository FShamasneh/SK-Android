package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;


public class OximetryContinuousMeasurement extends Measurement {

    public OximetryContinuousMeasurement(BluetoothGattCharacteristic characteristic, String serviceUUID) {
        super(characteristic, serviceUUID);
        unit = "%";
        setValue();
    }

    public OximetryContinuousMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);
        unit = "%";
        setValue();
    }

    public OximetryContinuousMeasurement() {
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
