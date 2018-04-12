package com.firas.android.mainDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

/**
 * Created by Feras on 1.04.2018.
 */

public class MainDB extends SQLiteOpenHelper{

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mainSmarkoInfantDB";


    public MainDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        for(DBTable table:DBTableHelper.tableMap.values()){
            db.execSQL(table.createTable());
        }

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + "nothingyet");

        // Create tables again
        onCreate(db);
    }

    public Long insertData(TableType tableType, Object data) {
        SQLiteDatabase db = this.getWritableDatabase();
        Long id = null;
        DBTable temp =  DBTableHelper.tableMap.get(tableType);
        ContentValues values = temp.insertData(data);

        id = db.insert(temp.getTableName(), null, values);
        Log.w("mainDB","insertedData: "+data+" \n contentValues: "+values);
        db.close(); // Closing database connection
        return id;
    }

    public void updateData(TableType tableType, Object data, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        DBTable temp =  DBTableHelper.tableMap.get(tableType);
        ContentValues values = temp.insertData(data);

        String[] args = new String[]{""+id};
        db.update(temp.getTableName(), values, "_id=?", args);

        Log.w("mainDB","updatedData: "+data);
        db.close(); // Closing database connection

    }

    public <T>List<T> getDataList(TableType tableType){
        SQLiteDatabase db = this.getReadableDatabase();
        DBTable temp =  DBTableHelper.tableMap.get(tableType);
        Cursor cursor =  db.rawQuery( "SELECT * from "+temp.getTableName(), null );
//        Log.w("mainDB","retrieveData cursor: "+cursor.getCount());
        List<T> data = temp.getDataList(cursor);
//        Log.w("mainDB","retrievedData: "+data);
        return data;
    }


    public void deleteDataById(int id, TableType tableType){
        SQLiteDatabase db = this.getWritableDatabase();
        DBTable temp =  DBTableHelper.tableMap.get(tableType);
        db.delete(temp.getTableName(), temp.getIDKey() + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }

    public void deleteDataByColumn(String columnName, String columnValue, TableType tableType){
        SQLiteDatabase db = this.getWritableDatabase();
        DBTable temp =  DBTableHelper.tableMap.get(tableType);
        db.delete(temp.getTableName(), columnName + " = ?",
                new String[] { String.valueOf(columnValue) });
        db.close();
    }

    public Object getDataById(int id, TableType tableType) {
        SQLiteDatabase db = this.getReadableDatabase();
        DBTable temp =  DBTableHelper.tableMap.get(tableType);
        String query = "select * from " + temp.getTableName() + " where "+ "_id" + " = '" + id + "'";
        Cursor cursor = db.rawQuery(query,null);
        List<Object> data = temp.getDataList(cursor);
        Object result = null;
        if(data.size()>0) result = data.get(0);
        return result;
    }

    public <T>List<T> getDataByColumn(String columnName, String columnValue, TableType tableType) {
        SQLiteDatabase db = this.getReadableDatabase();
        DBTable temp =  DBTableHelper.tableMap.get(tableType);
        String query = "select * from " + temp.getTableName() + " where "+ columnName + " = '" + columnValue + "'";
        Cursor cursor = db.rawQuery(query,null);
        List<T> data = temp.getDataList(cursor);
        return data;
    }
}