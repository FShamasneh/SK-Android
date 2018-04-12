package com.firas.android.mainDB;

import android.content.ContentValues;
import android.database.Cursor;

import com.firas.android.model.BodyPart;
import com.firas.android.model.Device;
import com.firas.android.model.Range;
import com.firas.android.utils.DataUtils;
import com.firas.android.utils.SensorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by Feras on 1.04.2018.
 */

public class DeviceTable implements DBTable {

    public static final String KEY_TABLE_NAME = "device_table";
    public static final String KEY_DEVICE_ID = "_id";
    public static final String KEY_DEVICE_NAME = "device_name";
    public static final String KEY_DEVICE_ADDRESS = "device_address";
    public static final String KEY_DEVICE_PIC = "device_pic";
    public static final String KEY_CHILD_REF = "device_child_ref";
    public static final String KEY_DEVICE_BODY_PART = "device_body_part";
    public static final String KEY_CHECKED_CHARACTERISTICS = "device_checked_characteristics";


    public String createTable(){
        return  "CREATE TABLE " + KEY_TABLE_NAME + "("
                + KEY_DEVICE_ID + " INTEGER PRIMARY KEY,"
                + KEY_DEVICE_NAME + " TEXT,"
                + KEY_DEVICE_ADDRESS + " TEXT,"
                + KEY_DEVICE_PIC + " INTEGER,"
                + KEY_CHILD_REF + " INTEGER,"
                + KEY_DEVICE_BODY_PART + " TEXT,"
                + KEY_CHECKED_CHARACTERISTICS + " TEXT"+ ")";
    }

    @Override
    public List<Device> getDataList(Cursor cursor) {
        ArrayList<Device> devices = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Map<SensorType,Range> temp =  DataUtils.getRangeMapFromJson(cursor.getString(6));
                Device device = new Device(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4),
                        BodyPart.isBodyPart(cursor.getString(5)),
                        temp);
                devices.add(device);
            } while (cursor.moveToNext());
        }
//        Log.w("DeviceTable", "getDevices: "+devices);
        return devices;
    }



    @Override
    public ContentValues insertData(Object data) {

        Device device = (Device) data;
        ContentValues values = new ContentValues();

        values.put(KEY_DEVICE_NAME, device.getName());
        values.put(KEY_DEVICE_ADDRESS, device.getAddress());
        values.put(KEY_DEVICE_PIC, device.getImage());
        values.put(KEY_CHILD_REF, device.getChildRef());
        values.put(KEY_DEVICE_BODY_PART, device.getBodyPart()+"");
        values.put(KEY_CHECKED_CHARACTERISTICS, DataUtils.getJsonFromRangeMap(device.getSensorRanges()));

        return values;
    }

    @Override
    public String getTableName() {
        return KEY_TABLE_NAME;
    }

    @Override
    public String getIDKey() {
        return KEY_DEVICE_ID;
    }
}
