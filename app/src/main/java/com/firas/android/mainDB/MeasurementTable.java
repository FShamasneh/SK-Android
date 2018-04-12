package com.firas.android.mainDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;


import com.google.gson.Gson;
import com.firas.android.model.MeasurementFactory;
import com.firas.android.model.measurement.Measurement;
import com.firas.android.utils.DateUtil;
import com.firas.android.utils.DeviceType;
import com.firas.android.utils.SensorType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Feras on 1.04.2018.
 */

public class MeasurementTable implements DBTable {


    public static final String KEY_TABLE_NAME = "measurement_table";
    public static final String KEY_MEASUREMENT_ID = "_id";
    public static final String KEY_MEASUREMENT_NAME = "measurement_name";
    public static final String KEY_MEASUREMENT_SENSOR_TYPE = "measurement_sensor_type";
    public static final String KEY_MEASUREMENT_DEVICE_TYPE = "measurement_device_type";
    public static final String KEY_MEASUREMENT_DEVICE_ADDRESS = "measurement_device_address";
    public static final String KEY_MEASUREMENT_VALUE = "measurement_value";
    public static final String KEY_MEASUREMENT_UNIT = "measurement_unit";
    public static final String KEY_MEASUREMENT_RECORDED_DATE = "measurement_recorded_date";


    public String createTable(){
        return  "CREATE TABLE " + KEY_TABLE_NAME + "("
                + KEY_MEASUREMENT_ID + " INTEGER PRIMARY KEY,"
                + KEY_MEASUREMENT_NAME + " TEXT,"
                + KEY_MEASUREMENT_SENSOR_TYPE + " TEXT,"
                + KEY_MEASUREMENT_DEVICE_TYPE + " TEXT,"
                + KEY_MEASUREMENT_DEVICE_ADDRESS + " TEXT,"
                + KEY_MEASUREMENT_VALUE + " TEXT,"
                + KEY_MEASUREMENT_UNIT + " TEXT,"
                + KEY_MEASUREMENT_RECORDED_DATE + " TEXT"+ ")";
    }

    @Override
    public List<Measurement> getDataList(Cursor cursor) {
        ArrayList<Measurement> measurements = new ArrayList<>();
        Gson gson = new Gson();
        if (cursor.moveToFirst()) {
            do {
                Measurement measurement = MeasurementFactory.getMeasurement(gson.fromJson(cursor.getString(2),SensorType.class));
                measurement.setId(cursor.getInt(0));
                measurement.setName(cursor.getString(1));
                measurement.setDeviceType(gson.fromJson(cursor.getString(3), DeviceType.class));
                measurement.setDeviceAddress(cursor.getString(4));
                measurement.setValue(gson.fromJson(cursor.getString(5),Object.class));
                measurement.setUnit(cursor.getString(6));
                measurement.setTime(DateUtil.getSpecificDateTime(cursor.getString(7)));
                measurements.add(measurement);
            } while (cursor.moveToNext());
        }
        Log.w("MeasurementTable", "getMeasurement: "+measurements);
        return measurements;
    }


    @Override
    public <T>ContentValues insertData(T data) {
        Gson gson = new Gson();
        Measurement measurement = (Measurement) data;
        ContentValues values = new ContentValues();

        values.put(KEY_MEASUREMENT_NAME, measurement.getName());
        values.put(KEY_MEASUREMENT_SENSOR_TYPE, gson.toJson(measurement.getSensorType(),SensorType.class));
        values.put(KEY_MEASUREMENT_DEVICE_TYPE, gson.toJson(measurement.getDeviceType(),DeviceType.class));
        values.put(KEY_MEASUREMENT_DEVICE_ADDRESS, measurement.getDeviceAddress());
        values.put(KEY_MEASUREMENT_VALUE, gson.toJson(measurement.getValue(),Object.class));
        values.put(KEY_MEASUREMENT_UNIT, measurement.getUnit());
        values.put(KEY_MEASUREMENT_RECORDED_DATE, DateUtil.fromDateFull(measurement.getTime()));

        return values;
    }

    @Override
    public String getTableName() {
        return KEY_TABLE_NAME;
    }

    @Override
    public String getIDKey() {
        return KEY_MEASUREMENT_ID;
    }
}
