package com.firas.android.mainDB;

import android.content.ContentValues;
import android.database.Cursor;

import com.firas.android.model.Child;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feras on 1.04.2018.
 */

public class ChildrenTable implements DBTable {

    public static final String KEY_TABLE_NAME = "children_table";
    public static final String KEY_CHILD_ID = "_id";
    public static final String KEY_CHILD_NAME = "children_name";
    public static final String KEY_CHILD_GENDER = "children_gender";
    public static final String KEY_CHILD_PIC = "children_pic";
    public static final String KEY_CHILD_BIRTH_DATE = "children_birth_date";
    public static final String KEY_CHILD_HEIGHT = "children_height";
    public static final String KEY_CHILD_WEIGHT = "children_weight";
    public static final String KEY_CHILD_DEVICE_ADDRESS = "children_device_address";

    public String createTable() {
        return  "CREATE TABLE " + KEY_TABLE_NAME + "("
                + KEY_CHILD_ID + " INTEGER PRIMARY KEY,"
                + KEY_CHILD_NAME + " TEXT,"
                + KEY_CHILD_BIRTH_DATE + " TEXT,"
                + KEY_CHILD_GENDER + " TEXT,"
                + KEY_CHILD_HEIGHT + " DOUBLE,"
                + KEY_CHILD_WEIGHT + " DOUBLE,"
                + KEY_CHILD_PIC + " TEXT,"
                + KEY_CHILD_DEVICE_ADDRESS + " TEXT"+ ")";
    }

    @Override
    public List<Child> getDataList(Cursor cursor) {
        ArrayList<Child> children = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                Child child = new Child(cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getDouble(4),
                        cursor.getDouble(5),
                        cursor.getString(6),
                        cursor.getString(7));
                child.setId(cursor.getInt(0));
                children.add(child);
            } while (cursor.moveToNext());
        }
        return children;
    }


    @Override
    public <T>ContentValues insertData(T data) {

        Child child = (Child) data;
        ContentValues values = new ContentValues();

        values.put(KEY_CHILD_NAME, child.getName());
        values.put(KEY_CHILD_BIRTH_DATE, child.getBirthDate());
        values.put(KEY_CHILD_GENDER, child.getGender());
        values.put(KEY_CHILD_HEIGHT, child.getHeight());
        values.put(KEY_CHILD_WEIGHT, child.getWeight());
        values.put(KEY_CHILD_PIC, child.getBitmapString());
        values.put(KEY_CHILD_DEVICE_ADDRESS, child.getDeviceAddress());

        return values;
    }

    @Override
    public String getTableName() {
        return KEY_TABLE_NAME;
    }

    @Override
    public String getIDKey() {
        return KEY_CHILD_ID;
    }
}
