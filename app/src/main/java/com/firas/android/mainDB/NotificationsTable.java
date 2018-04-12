package com.firas.android.mainDB;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.Gson;
import com.firas.android.model.Notification;
import com.firas.android.utils.NotificationsType;
import com.firas.android.utils.SensorType;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Feras on 1.04.2018.
 */

public class NotificationsTable implements DBTable {


    public static final String KEY_TABLE_NAME = "notifications_table";
    public static final String KEY_NOTIFICATIONS_ID = "_id";
    public static final String KEY_NOTIFICATIONS_TYPE = "notifications_type";
    public static final String KEY_NOTIFICATIONS_SENSOR_TYPE = "notifications_sensor_type";
    public static final String KEY_NOTIFICATIONS_DEVICE_ADDRESS = "notifications_device_address";
    public static final String KEY_NOTIFICATIONS_VALUE = "notifications_value";
    public static final String KEY_NOTIFICATIONS_CHILD_REF = "notifications_child_ref";
    public static final String KEY_NOTIFICATIONS_IS_NEW = "notifications_is_new";
    public static final String KEY_NOTIFICATIONS_RECORDED_DATE = "notifications_recorded_date";



    public String createTable(){
        return  "CREATE TABLE " + KEY_TABLE_NAME + "("
                + KEY_NOTIFICATIONS_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOTIFICATIONS_TYPE + " TEXT,"
                + KEY_NOTIFICATIONS_SENSOR_TYPE + " TEXT,"
                + KEY_NOTIFICATIONS_DEVICE_ADDRESS + " TEXT,"
                + KEY_NOTIFICATIONS_VALUE + " TEXT,"
                + KEY_NOTIFICATIONS_CHILD_REF + " INTEGER,"
                + KEY_NOTIFICATIONS_IS_NEW + " TEXT,"
                + KEY_NOTIFICATIONS_RECORDED_DATE + " TEXT"+ ")";
    }

    @Override
    public List<Notification> getDataList(Cursor cursor) {
        Gson gson = new Gson();
        List<Notification> notifications = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Notification notification = new Notification(
                        cursor.getInt(0),
                        NotificationsType.valueOf(cursor.getString(1)),
                        SensorType.valueOf(cursor.getString(2)),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5),
                        Boolean.valueOf(cursor.getString(6)),
                        cursor.getString(7));
                notifications.add(notification);
            } while (cursor.moveToNext());

        }
        Log.w("NotificationTable", "getNotification: "+notifications);
        return notifications;
    }


    @Override
    public <T>ContentValues insertData(T data) {
        Gson gson = new Gson();
        Notification notification = (Notification) data;
        ContentValues values = new ContentValues();

        NotificationsType type = notification.getNotificationsType();
        SensorType sType = notification.getSensorType();
        values.put(KEY_NOTIFICATIONS_TYPE, type!=null?type.name():null);
        values.put(KEY_NOTIFICATIONS_SENSOR_TYPE, sType!=null?sType.name():null);
        values.put(KEY_NOTIFICATIONS_DEVICE_ADDRESS, notification.getDeviceAddress());
        values.put(KEY_NOTIFICATIONS_CHILD_REF,notification.getChildRef());
        values.put(KEY_NOTIFICATIONS_VALUE, notification.getValue());
        values.put(KEY_NOTIFICATIONS_IS_NEW,String.valueOf(notification.isNew()));
        values.put(KEY_NOTIFICATIONS_RECORDED_DATE, notification.getRecorded());

        return values;
    }

    @Override
    public String getTableName() {
        return KEY_TABLE_NAME;
    }

    @Override
    public String getIDKey() {
        return KEY_NOTIFICATIONS_ID;
    }
}
