package com.firas.android.smarko;

import android.app.Application;

import com.firas.android.mainDB.MainDB;
import com.firas.android.model.Child;
import com.firas.android.model.Device;



public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    private static String globalFont = "fonts/Campton_Medium.ttf";

    private static MyApplication mInstance;
    private MainDB mainDB;
    private static Device device;
    private static Child currentChild;

    @Override
    public void onCreate() {
        super.onCreate();

        mainDB = new MainDB(getContext());

    }

    public MyApplication() {
        mInstance = this;
    }

    public static MyApplication getContext() {
        return mInstance;
    }


    public static String getGlobalFont() {
        return globalFont;
    }

    public static void setGlobalFont(String globalFont) {
        MyApplication.globalFont = globalFont;
    }

    public MyApplication getInstance(){
        return this;
    }



    public MainDB getMainDB() {
        return mainDB;
    }

    public static Device getDevice() {
        return device;
    }

    public static void setDevice(Device device) {
        MyApplication.device = device;
    }


    }
