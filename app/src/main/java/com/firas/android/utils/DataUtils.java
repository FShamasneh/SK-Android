package com.firas.android.utils;

import android.app.Activity;

import com.firas.android.model.Device;
import com.firas.android.model.Range;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.firas.android.model.measurement.Measurement;

import java.lang.reflect.Type;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DataUtils {

    public static final String KEY_UPLOAD_URL = "upload_url_key" ;
    public static final String KEY_UPLOAD_SETTINGS = "upload_settings_key";
    public static final String KEY_CHILDREN_LIST = "children_list_key";
    private static Gson gson = new Gson();


    public static <T>String getJsonFromList(List<T> list){
        Type listType = new TypeToken<ArrayList<T>>() {}.getType();
        return gson.toJson(list,listType);
    }

    public static String getJsonFromDeviceList(List<Device> list){
        Type listType = new TypeToken<ArrayList<Device>>() {}.getType();
        return gson.toJson(list,listType);
    }

    public static String getJsonFromRangeMap(Map<SensorType,Range> list){
        Type listType = new TypeToken<HashMap<SensorType,Range>>() {}.getType();
        return gson.toJson(list,listType);
    }

    public static <T>List<T> getListFromJson(String json){
        Type listType = new TypeToken<ArrayList<T>>() {}.getType();
        if (json!=null)return gson.fromJson(json,listType);
        return null;
    }

    public static List<Device> getDeviceListFromJson(String json){
        Type listType = new TypeToken<ArrayList<Device>>() {}.getType();
        if (json!=null)return gson.fromJson(json,listType);
        return null;
    }

    public static Map getRangeMapFromJson(String json){
        Type listType = new TypeToken<HashMap<SensorType,Range>>() {}.getType();
        if (json!=null)return gson.fromJson(json,listType);
        return null;
    }

    public static String getJsonFromTempList(List<Measurement> list){
        Type listType = new TypeToken<ArrayList<Measurement>>() {}.getType();
        return gson.toJson(list,listType);
    }

    public static ArrayList<Measurement> getTempListFromJson(String json){
        Type listType = new TypeToken<ArrayList<Measurement>>() {}.getType();
        if (json!=null)return gson.fromJson(json,listType);
        return new ArrayList<>();
    }

    public static String getJsonFromTempMap(Map<String,List<AbstractMap.SimpleEntry<SensorType,Measurement>>> list){
        Type listType = new TypeToken<HashMap<String,List<AbstractMap.SimpleEntry<SensorType,Measurement>>>>() {}.getType();
        return gson.toJson(list,listType);
    }

    public static HashMap<String,List<AbstractMap.SimpleEntry<SensorType,Measurement>>> getTempMapFromJson(String json){
        Type listType = new TypeToken<HashMap<String,List<AbstractMap.SimpleEntry<SensorType,Measurement>>>>() {}.getType();
        if (json!=null)return gson.fromJson(json,listType);
        return null;
    }


}
