package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;


public class BatteryCurrentMeasurement extends Measurement {

    public BatteryCurrentMeasurement(BluetoothGattCharacteristic characteristic, String serviceUUID) {
        super(characteristic, serviceUUID);
        unit = "mA";
        setValue();
    }

    public BatteryCurrentMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);
        unit = "mA";
        setValue();
    }

    public BatteryCurrentMeasurement() {
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
