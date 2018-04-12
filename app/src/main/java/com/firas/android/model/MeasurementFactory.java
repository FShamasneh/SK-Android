package com.firas.android.model;

import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;

import com.firas.android.smarko.MyApplication;
import com.firas.android.model.measurement.AccelerationMeasurement;
import com.firas.android.model.measurement.BatteryCurrentMeasurement;
import com.firas.android.model.measurement.BatteryLevelMeasurement;
import com.firas.android.model.measurement.BatteryTempMeasurement;
import com.firas.android.model.measurement.BatteryVoltMeasurement;
import com.firas.android.model.measurement.BodyTempMeasurement;
import com.firas.android.model.measurement.HeartrateMeasurement;
import com.firas.android.model.measurement.HumidityMeasurement;
import com.firas.android.model.measurement.Measurement;
import com.firas.android.model.measurement.OrientationMeasurement;
import com.firas.android.model.measurement.OximetryContinuousMeasurement;
import com.firas.android.model.measurement.TemperatureMeasurement;
import com.firas.android.model.measurement.UnknownMeasurement;
import com.firas.android.utils.BleNamesResolver;
import com.firas.android.utils.DeviceType;
import com.firas.android.utils.SensorType;


import java.util.AbstractMap;
import java.util.List;




public class MeasurementFactory {

    private static Measurement measurement;
    private static String criteria;
    private static SensorType type;

    public static Measurement getMeasurement(AbstractMap.SimpleEntry<BluetoothGattService, String> characteristicInfo, MyApplication context)
    {

        List<BluetoothGattCharacteristic> temp = characteristicInfo.getKey().getCharacteristics();
        BluetoothGattCharacteristic characteristic = null;
        if(temp.size()>0) characteristic = temp.get(0);
        String serviceUUID = characteristicInfo.getKey().getUuid().toString();
        String address = characteristicInfo.getValue();
        if (characteristic!=null)criteria = BleNamesResolver.resolveCharacteristicName(characteristic.getUuid().toString());
        else criteria = "null";
        if ( criteria.equals("Heart Rate Measurement") ) {
            measurement = new HeartrateMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.HEART_RATE_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Battery Level") ) {
            measurement = new BatteryLevelMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.BATTERY_LEVEL_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Battery Voltage") ) {
            measurement = new BatteryVoltMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.BATTERY_VOLT_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Battery Current") ) {
            measurement = new BatteryCurrentMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.BATTERY_CURRENT_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Battery Temperature") ) {
            measurement = new BatteryTempMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.BATTERY_TEMP_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Pulse Oximetry Continuous Measurement") ) {
            measurement = new OximetryContinuousMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.OXIMETER_CONTINOUS_MEASUREMENT_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Temperature Measurement") ) {


//            Device device =  ChildDeviceManagement.getDeviceByAddress(address, context);
//            BodyPart bodyPart = null;
//            if(device!=null)device.getBodyPart();
       //     measurement = new BodyTempMeasurement(characteristic, serviceUUID, CollectorService.getTemporaryMeasurement(address,SensorType.TEMPARATURE_SENSOR), BodyPart.HAND);
            measurement.setSensorType(SensorType.BODY_TEMP_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Humidity") ) {
            measurement = new HumidityMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.HUMIDITY_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Temperature") ) {
            measurement = new TemperatureMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.TEMPARATURE_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Acceleration Measurement") ) {
            measurement = new AccelerationMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.ACCELERATION_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else if ( criteria.equals("Orientation Measurement") ) {
            measurement = new OrientationMeasurement(characteristic, serviceUUID);
            measurement.setSensorType(SensorType.ORIENTATION_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
            measurement.setDeviceAddress(address);
        } else {
            measurement = new UnknownMeasurement(characteristic);
            measurement.setSensorType(SensorType.UNKNOWN);
            measurement.setDeviceAddress(address);
        }
        return measurement;
    }


    public static Measurement getMeasurement(SensorType sensorType)
    {

        type = sensorType;

        if ( type.equals(SensorType.HEART_RATE_SENSOR) ) {
            measurement = new HeartrateMeasurement();
            measurement.setSensorType(SensorType.HEART_RATE_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.BATTERY_LEVEL_SENSOR) ) {
            measurement = new BatteryLevelMeasurement();
            measurement.setSensorType(SensorType.BATTERY_LEVEL_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.BATTERY_VOLT_SENSOR) ) {
            measurement = new BatteryVoltMeasurement();
            measurement.setSensorType(SensorType.BATTERY_VOLT_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.BATTERY_CURRENT_SENSOR) ) {
            measurement = new BatteryCurrentMeasurement();
            measurement.setSensorType(SensorType.BATTERY_CURRENT_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.BATTERY_TEMP_SENSOR) ) {
            measurement = new BatteryTempMeasurement();
            measurement.setSensorType(SensorType.BATTERY_TEMP_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.OXIMETER_CONTINOUS_MEASUREMENT_SENSOR) ) {
            measurement = new OximetryContinuousMeasurement();
            measurement.setSensorType(SensorType.OXIMETER_CONTINOUS_MEASUREMENT_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.BODY_TEMP_SENSOR) ) {
            measurement = new BodyTempMeasurement();
            measurement.setSensorType(SensorType.BODY_TEMP_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.HUMIDITY_SENSOR) ) {
            measurement = new HumidityMeasurement();
            measurement.setSensorType(SensorType.HUMIDITY_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.TEMPARATURE_SENSOR) ) {
            measurement = new TemperatureMeasurement();
            measurement.setSensorType(SensorType.TEMPARATURE_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.ACCELERATION_SENSOR) ) {
            measurement = new AccelerationMeasurement();
            measurement.setSensorType(SensorType.ACCELERATION_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else if ( type.equals(SensorType.ORIENTATION_SENSOR) ) {
            measurement = new OrientationMeasurement();
            measurement.setSensorType(SensorType.ORIENTATION_SENSOR);
            measurement.setDeviceType(DeviceType.SMARKO_COLLECTION);
        } else {
            measurement = new UnknownMeasurement();
            measurement.setSensorType(SensorType.UNKNOWN);
        }
        return measurement;
    }
}
