package com.firas.android.mainDB;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * Created by Feras on 1.04.2018.
 */

interface DBTable {

     String createTable();

     <T>ContentValues insertData(T data);

     <T>List<T> getDataList(Cursor cursor);

     String getTableName();

     String getIDKey();

}