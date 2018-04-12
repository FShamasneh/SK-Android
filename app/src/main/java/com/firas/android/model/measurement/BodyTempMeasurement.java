package com.firas.android.model.measurement;

import android.bluetooth.BluetoothGattCharacteristic;
import android.util.Log;

import com.firas.android.model.BodyPart;
import com.firas.android.model.Tuple;

import java.math.BigDecimal;
import java.math.RoundingMode;




public class BodyTempMeasurement extends Measurement {

    public BodyTempMeasurement(BluetoothGattCharacteristic characteristic, String serviceUUID,Measurement m, BodyPart bodyPart) {
        super(characteristic, serviceUUID);
        unit = "C°";
        //Tuple contains the ambientTemp (index 0) and BodyPart (index 1)
        calcValue(m,bodyPart);
//        Log.w("BOdyTemp","received BodyTemp: "+value+"  "+characteristic.getUuid());
    }

    public BodyTempMeasurement(BluetoothGattCharacteristic characteristic, Tuple temperature) {
        super(characteristic);
        unit = "C°";
//        calcValue(temperature);
    }

    public BodyTempMeasurement() {
        super();
    }

    @Override
    public Object getValue() {
        return value;
    }

    private void calcValue(Measurement m,BodyPart bodyPart){
        Object skinTemp = ((characteristic.getValue()[1] & 0xFF) << 8 | (characteristic.getValue()[0] & 0xFF)) / 100.;
        if(m==null||bodyPart==null){
            value = skinTemp;
            return;
        }
            value = round(((Double)((double) skinTemp + bodyPart.getAlpha() * ( (double) skinTemp - (double)m.getValue()))),2);
        Log.w("BodyTemp","calculated BodyTemp: "+value+"  skinTemp: "+skinTemp+"  (ambientTemp,bodyPart): ("+m.getValue()+","+bodyPart+")");
        valueType = value.getClass();
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
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
