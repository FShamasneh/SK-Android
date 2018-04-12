package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;


public class OrientationMeasurement extends Measurement {

    public OrientationMeasurement(BluetoothGattCharacteristic characteristic, String serviceUUID) {
        super(characteristic, serviceUUID);
        unit = "";
        setValue();
    }

    public OrientationMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);
        unit = "";
        setValue();
    }

    public OrientationMeasurement() {
        super();
    }

    @Override
    public Object getValue() {
        return value;
    }

    private void setValue(){
        value = (characteristic.getValue()[1] << 8 | characteristic.getValue()[0]);
        valueType = value.getClass();
    }

    @Override
    public String getValueString() {
        return name+": "+value+ unit
//                    + showCharacteristicContent()
                ;
    }

    @Override
    public String toInfluxLine() {
        return null;
    }


}
