package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;


public class BatteryVoltMeasurement extends Measurement {

    public BatteryVoltMeasurement(BluetoothGattCharacteristic characteristic, String serviceUUID) {
        super(characteristic, serviceUUID);
        unit = "mV";
        setValue();
    }

    public BatteryVoltMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);
        unit = "mV";
        setValue();
    }

    public BatteryVoltMeasurement() {
        super();
    }

    @Override
    public Object getValue() {
        return value;
    }

    private void setValue(){
        value = (characteristic.getValue()[1]& 0xFF) << 8 | (characteristic.getValue()[0] & 0xFF);
        valueType = value.getClass();
    }

    @Override
    public String getValueString() {
        return name+": "+value+ unit
                    + showCharacteristicContent()
                ;
    }



    @Override
    public String toInfluxLine() {
        return null;
    }


}
