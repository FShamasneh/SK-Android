package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;


public class UnknownMeasurement extends Measurement {


    public UnknownMeasurement(BluetoothGattCharacteristic characteristic) {
        super(characteristic);

    }

    public UnknownMeasurement() {
        super();
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String getValueString() {
        return null;
    }

    @Override
    public String toInfluxLine() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
