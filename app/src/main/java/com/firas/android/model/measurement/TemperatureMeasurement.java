package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;




public class TemperatureMeasurement extends Measurement {

    public TemperatureMeasurement(BluetoothGattCharacteristic characteristic, String serviceUUID) {
        super(characteristic, serviceUUID);
        unit = "C°";
        setValue();
        Log.w("Temp","received Temp : "+value+"   " +characteristic.getUuid());
    }

    public TemperatureMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);
        unit = "C°";
        setValue();
    }

    public TemperatureMeasurement() {
        super();
    }

    @Override
    public Object getValue() {
        return value;
    }

    private void setValue(){
        value = ((characteristic.getValue()[1] & 0xFF) << 8 | (characteristic.getValue()[0] & 0xFF)) / 100.;
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
