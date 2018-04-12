package com.firas.android.utils;

import android.util.SparseArray;

import java.util.HashMap;
import java.util.Map;

import static com.firas.android.utils.DeviceType.*;
import static com.firas.android.utils.DeviceType.GLUCOSE_COLLECTION;
import static com.firas.android.utils.DeviceType.SMARKO_COLLECTION;



public class BleNamesResolver {
    private static HashMap<String, String> mServices = new HashMap<String, String>();
    private static HashMap<String, String> mCharacteristics = new HashMap<String, String>();
    private static SparseArray<String> mValueFormats = new SparseArray<String>();
    private static SparseArray<String> mAppearance = new SparseArray<String>();
    private static SparseArray<String> mHeartRateSensorLocation = new SparseArray<String>();
    public static Map<String,DeviceType> preferedDeviceNames = new HashMap<>();
    public static Map<String,DeviceType> preferedDeviceAddresses = new HashMap<>();


    static public String resolveServiceName(final String uuid)
    {
        String result = mServices.get(uuid);
        if(result == null) result = "Unknown Service";
        return result;
    }

    static public String resolveValueTypeDescription(final int format)
    {
        Integer tmp = Integer.valueOf(format);
        return mValueFormats.get(tmp, "Unknown Format");
    }

    static public String resolveCharacteristicName(final String uuid)
    {
        String result = mCharacteristics.get(uuid);
        if(result == null) result = "Unknown Characteristic";
        return result;
    }


    static public String resolveCharacteristicUUID(final String name) {
        String result = null;
        if(mCharacteristics.containsValue(name)){
            for(String s: mCharacteristics.keySet()){
                if(mCharacteristics.get(s).equalsIgnoreCase(name)){
                    result = s;
                }
            }

        }

        if(result == null) result = "Unknown Characteristic";
        return result;
    }

    static public String resolveUuid(final String uuid) {
        String result = mServices.get(uuid);
        if(result != null) return "Service: " + result;

        result = mCharacteristics.get(uuid);
        if(result != null) return "Characteristic: " + result;

        result = "Unknown UUID";
        return result;
    }

    static public String resolveAppearance(int key) {
        Integer tmp = Integer.valueOf(key);
        return mAppearance.get(tmp, "Unknown Appearance");
    }

    static public String resolveHeartRateSensorLocation(int key) {
        Integer tmp = Integer.valueOf(key);
        return mHeartRateSensorLocation.get(tmp, "Other");
    }

    static public boolean isService(final String uuid) {
        return mServices.containsKey(uuid);
    }

    static public boolean isCharacteristic(final String uuid) {
        return mCharacteristics.containsKey(uuid);
    }

    public static Map<String, DeviceType> getPreferedDeviceNames() {
        return preferedDeviceNames;
    }

    public static Map<String, DeviceType> getPreferedDeviceAddresses() {
        return preferedDeviceAddresses;
    }

    static {
        mServices.put("00001801-0000-1000-8000-00805f9b34fb", "Generic Attribute"); // 1
        mServices.put("0000180d-0000-1000-8000-00805f9b34fb", "Heart Rate");// 2
        mServices.put("00001822-0000-1000-8000-00805f9b34fb", "Pulse Oximeter");// 3
        mServices.put("00001809-0000-1000-8000-00805f9b34fb", "Health Thermometer");// 4
        mServices.put("0000181a-0000-1000-8000-00805f9b34fb", "Environmental Sensing");// 5
        mServices.put("a4e649f4-4be5-11e5-885d-feff819cdc9f", "Acceleration and Orientation");// 6
        mServices.put("00001815-0000-1000-8000-00805f9b34fb", "Automation IO");// 7
        mServices.put("4880c12c-fdcb-4077-8920-a450d7f9b907", "SPP Service");// 8
        mServices.put("1d14d6ee-fd63-4fa1-bfa4-8f47b42119f0", "Silicon Labs OTA");// 9

        mServices.put("00001811-0000-1000-8000-00805f9b34fb", "Alert Notification Service");
        mServices.put("0000180f-0000-1000-8000-00805f9b34fb", "Battery Service");
        mServices.put("00001810-0000-1000-8000-00805f9b34fb", "Blood Pressure");
        mServices.put("00001805-0000-1000-8000-00805f9b34fb", "Current Time Service");
        mServices.put("00001818-0000-1000-8000-00805f9b34fb", "Cycling Power");
        mServices.put("00001816-0000-1000-8000-00805f9b34fb", "Cycling Speed and Cadence");
        mServices.put("0000180a-0000-1000-8000-00805f9b34fb", "Device Information");
        mServices.put("00001800-0000-1000-8000-00805f9b34fb", "Generic Access");
        mServices.put("00001808-0000-1000-8000-00805f9b34fb", "Glucose");
        mServices.put("00001812-0000-1000-8000-00805f9b34fb", "Human Interface Device");
        mServices.put("00001802-0000-1000-8000-00805f9b34fb", "Immediate Alert");
        mServices.put("00001803-0000-1000-8000-00805f9b34fb", "Link Loss");
        mServices.put("00001819-0000-1000-8000-00805f9b34fb", "Location and Navigation");
        mServices.put("00001807-0000-1000-8000-00805f9b34fb", "Next DST Change Service");
        mServices.put("0000180e-0000-1000-8000-00805f9b34fb", "Phone Alert Status Service");
        mServices.put("00001806-0000-1000-8000-00805f9b34fb", "Reference Time Update Service");
        mServices.put("00001814-0000-1000-8000-00805f9b34fb", "Running Speed and Cadence");
        mServices.put("00001813-0000-1000-8000-00805f9b34fb", "Scan Parameters");
        mServices.put("00001804-0000-1000-8000-00805f9b34fb", "Tx Power");
        mServices.put("78d93f91-3a1b-4340-89a4-a48c5f650050", "Skin Contact Service");


        mCharacteristics.put("00002a43-0000-1000-8000-00805f9b34fb", "Alert Category ID");
        mCharacteristics.put("00002a42-0000-1000-8000-00805f9b34fb", "Alert Category ID Bit Mask");
        mCharacteristics.put("00002a06-0000-1000-8000-00805f9b34fb", "Alert Level");
        mCharacteristics.put("00002a44-0000-1000-8000-00805f9b34fb", "Alert Notification Control Point");
        mCharacteristics.put("00002a3f-0000-1000-8000-00805f9b34fb", "Alert Status");
        mCharacteristics.put("00002a05-0000-1000-8000-00805f9b34fb", "Service Changed"); // 1
        mCharacteristics.put("00002a00-0000-1000-8000-00805f9b34fb", "Device Name"); // 2
        mCharacteristics.put("00002a01-0000-1000-8000-00805f9b34fb", "Appearance"); // 3
        mCharacteristics.put("00002a29-0000-1000-8000-00805f9b34fb", "Manufacturer Name String"); // 4
        mCharacteristics.put("00002a24-0000-1000-8000-00805f9b34fb", "Model Number String"); // 5
        mCharacteristics.put("00002a26-0000-1000-8000-00805f9b34fb", "Firmware Revision String"); // 6
        mCharacteristics.put("00002a23-0000-1000-8000-00805f9b34fb", "System ID"); // 7
        mCharacteristics.put("00002a19-0000-1000-8000-00805f9b34fb", "Battery Level"); // 8
        mCharacteristics.put("af863946-c039-4fc3-9930-efefef2dd794", "Battery Voltage");// 9
        mCharacteristics.put("2beebbdf-8abb-4933-a2e7-128b1014e8f1", "Battery Current");// 10
        mCharacteristics.put("ffa50ccd-6b28-4a38-902e-cfd09413fd49", "Battery Temperature");// 11
        mCharacteristics.put("00002a38-0000-1000-8000-00805f9b34fb", "Body Sensor Location");// 12 Heart Rate Service
        mCharacteristics.put("00002a37-0000-1000-8000-00805f9b34fb", "Heart Rate Measurement"); // 13 Heart Rate Service
        mCharacteristics.put("00002a39-0000-1000-8000-00805f9b34fb", "Heart Rate Control Point");

        mCharacteristics.put("00002a5e-0000-1000-8000-00805f9b34fb", "Pulse Oximetry Spot-check Measurement");// 14
        mCharacteristics.put("00002a5f-0000-1000-8000-00805f9b34fb", "Pulse Oximetry Continuous Measurement");// 15
        mCharacteristics.put("00002a60-0000-1000-8000-00805f9b34fb", "Pulse Oximetry Features");// 16
        mCharacteristics.put("00002a61-0000-1000-8000-00805f9b34fb", "Pulse Oximetry Features");
        mCharacteristics.put("00002a62-0000-1000-8000-00805f9b34fb", "Pulse Oximetry Control Point");

        mCharacteristics.put("00002a1c-0000-1000-8000-00805f9b34fb", "Temperature Measurement");// 17
        mCharacteristics.put("00002a6f-0000-1000-8000-00805f9b34fb", "Humidity");// 18 Environmental Sensing
        mCharacteristics.put("00002a6e-0000-1000-8000-00805f9b34fb", "Temperature");// 19 Environmental Sensing
        mCharacteristics.put("00002a76-0000-1000-8000-00805f9b34fb", "UV Index");// 20 Environmental Sensing

        mCharacteristics.put("c4c1f6e2-4be5-11e5-885d-feff819cdc9f", "Acceleration Measurement");// 21 Acceleration and Orientation Service
        mCharacteristics.put("b7c4b694-bee3-45dd-ba9f-f3b5e994f49a", "Orientation Measurement");// 22 Acceleration and Orientation Service
        mCharacteristics.put("71e30b8c-4131-4703-b0a0-b0bbba75856b", "Calibrate");// 23 Acceleration and Orientation Service

        mCharacteristics.put("00002a56-0000-1000-8000-00805f9b34fb", "Digital");// 24 Automation IO
        mCharacteristics.put("f5af1213-bad4-4af1-9feb-3a2881e545ed", "Reset");// 25 Automation IO

        mCharacteristics.put("fec26ec4-6d71-4442-9f81-55bc21d658d6", "SPP_data");// 26 SPP Service
        mCharacteristics.put("8dad5202-5d8f-4cc6-a1e4-3076543274b7", "SPP_enable");// 27 SPP Service

        mCharacteristics.put("f7bf3564-fb6d-4e53-88a4-5e37e0326063", "Silicon Labs OTA Control");// 28 Silicon Labs OTA

        mCharacteristics.put("00002a49-0000-1000-8000-00805f9b34fb", "Blood Pressure Feature");
        mCharacteristics.put("00002a35-0000-1000-8000-00805f9b34fb", "Blood Pressure Measurement");




        mCharacteristics.put("00002a22-0000-1000-8000-00805f9b34fb", "Boot Keyboard Input Report");
        mCharacteristics.put("00002a32-0000-1000-8000-00805f9b34fb", "Boot Keyboard Output Report");
        mCharacteristics.put("00002a33-0000-1000-8000-00805f9b34fb", "Boot Mouse Input Report");
        mCharacteristics.put("00002a5c-0000-1000-8000-00805f9b34fb", "CSC Feature");
        mCharacteristics.put("00002a5b-0000-1000-8000-00805f9b34fb", "CSC Measurement");
        mCharacteristics.put("00002a2b-0000-1000-8000-00805f9b34fb", "Current Time");
        mCharacteristics.put("00002a66-0000-1000-8000-00805f9b34fb", "Cycling Power Control Point");
        mCharacteristics.put("00002a65-0000-1000-8000-00805f9b34fb", "Cycling Power Feature");
        mCharacteristics.put("00002a63-0000-1000-8000-00805f9b34fb", "Cycling Power Measurement");
        mCharacteristics.put("00002a64-0000-1000-8000-00805f9b34fb", "Cycling Power Vector");
        mCharacteristics.put("00002a08-0000-1000-8000-00805f9b34fb", "Date Time");
        mCharacteristics.put("00002a0a-0000-1000-8000-00805f9b34fb", "Day Date Time");
        mCharacteristics.put("00002a09-0000-1000-8000-00805f9b34fb", "Day of Week");

        mCharacteristics.put("00002a0d-0000-1000-8000-00805f9b34fb", "DST Offset");
        mCharacteristics.put("00002a0c-0000-1000-8000-00805f9b34fb", "Exact Time 256");

        mCharacteristics.put("00002a51-0000-1000-8000-00805f9b34fb", "Glucose Feature");
        mCharacteristics.put("00002a18-0000-1000-8000-00805f9b34fb", "Glucose Measurement");
        mCharacteristics.put("00002a34-0000-1000-8000-00805f9b34fb", "Glucose Measurement Context");
        mCharacteristics.put("00002a27-0000-1000-8000-00805f9b34fb", "Hardware Revision String");

        mCharacteristics.put("00002a4c-0000-1000-8000-00805f9b34fb", "HID Control Point");
        mCharacteristics.put("00002a4a-0000-1000-8000-00805f9b34fb", "HID Information");
        mCharacteristics.put("00002a2a-0000-1000-8000-00805f9b34fb", "IEEE 11073-20601 Regulatory Certification Data List");
        mCharacteristics.put("00002a36-0000-1000-8000-00805f9b34fb", "Intermediate Cuff Pressure");
        mCharacteristics.put("00002a1e-0000-1000-8000-00805f9b34fb", "Intermediate Temperature");
        mCharacteristics.put("00002a6b-0000-1000-8000-00805f9b34fb", "LN Control Point");
        mCharacteristics.put("00002a6a-0000-1000-8000-00805f9b34fb", "LN Feature");
        mCharacteristics.put("00002a0f-0000-1000-8000-00805f9b34fb", "Local Time Information");
        mCharacteristics.put("00002a67-0000-1000-8000-00805f9b34fb", "Location and Speed");

        mCharacteristics.put("00002a21-0000-1000-8000-00805f9b34fb", "Measurement Interval");

        mCharacteristics.put("00002a68-0000-1000-8000-00805f9b34fb", "Navigation");
        mCharacteristics.put("00002a46-0000-1000-8000-00805f9b34fb", "New Alert");
        mCharacteristics.put("00002a04-0000-1000-8000-00805f9b34fb", "Peripheral Preferred Connection Parameters");
        mCharacteristics.put("00002a02-0000-1000-8000-00805f9b34fb", "Peripheral Privacy Flag");
        mCharacteristics.put("00002a50-0000-1000-8000-00805f9b34fb", "PnP ID");
        mCharacteristics.put("00002a69-0000-1000-8000-00805f9b34fb", "Position Quality");
        mCharacteristics.put("00002a4e-0000-1000-8000-00805f9b34fb", "Protocol Mode");
        mCharacteristics.put("00002a03-0000-1000-8000-00805f9b34fb", "Reconnection Address");
        mCharacteristics.put("00002a52-0000-1000-8000-00805f9b34fb", "Record Access Control Point");
        mCharacteristics.put("00002a14-0000-1000-8000-00805f9b34fb", "Reference Time Information");
        mCharacteristics.put("00002a4d-0000-1000-8000-00805f9b34fb", "Report");
        mCharacteristics.put("00002a4b-0000-1000-8000-00805f9b34fb", "Report Map");
        mCharacteristics.put("00002a40-0000-1000-8000-00805f9b34fb", "Ringer Control Point");
        mCharacteristics.put("00002a41-0000-1000-8000-00805f9b34fb", "Ringer Setting");
        mCharacteristics.put("00002a54-0000-1000-8000-00805f9b34fb", "RSC Feature");
        mCharacteristics.put("00002a53-0000-1000-8000-00805f9b34fb", "RSC Measurement");
        mCharacteristics.put("00002a55-0000-1000-8000-00805f9b34fb", "SC Control Point");
        mCharacteristics.put("00002a4f-0000-1000-8000-00805f9b34fb", "Scan Interval Window");
        mCharacteristics.put("00002a31-0000-1000-8000-00805f9b34fb", "Scan Refresh");
        mCharacteristics.put("00002a5d-0000-1000-8000-00805f9b34fb", "Sensor Location");
        mCharacteristics.put("00002a25-0000-1000-8000-00805f9b34fb", "Serial Number String");

        mCharacteristics.put("00002a28-0000-1000-8000-00805f9b34fb", "Software Revision String");
        mCharacteristics.put("00002a47-0000-1000-8000-00805f9b34fb", "Supported New Alert Category");
        mCharacteristics.put("00002a48-0000-1000-8000-00805f9b34fb", "Supported Unread Alert Category");

        mCharacteristics.put("00002a1d-0000-1000-8000-00805f9b34fb", "Temperature Type");
        mCharacteristics.put("00002a12-0000-1000-8000-00805f9b34fb", "Time Accuracy");
        mCharacteristics.put("00002a13-0000-1000-8000-00805f9b34fb", "Time Source");
        mCharacteristics.put("00002a16-0000-1000-8000-00805f9b34fb", "Time Update Control Point");
        mCharacteristics.put("00002a17-0000-1000-8000-00805f9b34fb", "Time Update State");
        mCharacteristics.put("00002a11-0000-1000-8000-00805f9b34fb", "Time with DST");
        mCharacteristics.put("00002a0e-0000-1000-8000-00805f9b34fb", "Time Zone");
        mCharacteristics.put("00002a07-0000-1000-8000-00805f9b34fb", "Tx Power Level");
        mCharacteristics.put("00002a45-0000-1000-8000-00805f9b34fb", "Unread Alert Status");
        mCharacteristics.put("1e34abff-c203-4dc7-bf99-2caa334f38b5", "Skin Contact");

        mValueFormats.put(Integer.valueOf(52), "32bit float");
        mValueFormats.put(Integer.valueOf(50), "16bit float");
        mValueFormats.put(Integer.valueOf(34), "16bit signed int");
        mValueFormats.put(Integer.valueOf(36), "32bit signed int");
        mValueFormats.put(Integer.valueOf(33), "8bit signed int");
        mValueFormats.put(Integer.valueOf(18), "16bit unsigned int");
        mValueFormats.put(Integer.valueOf(20), "32bit unsigned int");
        mValueFormats.put(Integer.valueOf(17), "8bit unsigned int");

        // lets add also couple appearance string description
        // https://developer.bluetooth.org/gatt/characteristics/Pages/CharacteristicViewer.aspx?u=org.bluetooth.characteristic.gap.appearance.xml
        mAppearance.put(Integer.valueOf(833), "Heart Rate Sensor: Belt");
        mAppearance.put(Integer.valueOf(832), "Generic Heart Rate Sensor");
        mAppearance.put(Integer.valueOf(0), "Unknown");
        mAppearance.put(Integer.valueOf(64), "Generic Phone");
        mAppearance.put(Integer.valueOf(1157), "Cycling: Speed and Cadence Sensor");
        mAppearance.put(Integer.valueOf(1152), "General Cycling");
        mAppearance.put(Integer.valueOf(1153), "Cycling Computer");
        mAppearance.put(Integer.valueOf(1154), "Cycling: Speed Sensor");
        mAppearance.put(Integer.valueOf(1155), "Cycling: Cadence Sensor");
        mAppearance.put(Integer.valueOf(1156), "Cycling: Speed and Cadence Sensor");
        mAppearance.put(Integer.valueOf(1157), "Cycling: Power Sensor");

        mHeartRateSensorLocation.put(Integer.valueOf(0), "Other");
        mHeartRateSensorLocation.put(Integer.valueOf(1), "Chest");
        mHeartRateSensorLocation.put(Integer.valueOf(2), "Wrist");
        mHeartRateSensorLocation.put(Integer.valueOf(3), "Finger");
        mHeartRateSensorLocation.put(Integer.valueOf(4), "Hand");
        mHeartRateSensorLocation.put(Integer.valueOf(5), "Ear Lobe");
        mHeartRateSensorLocation.put(Integer.valueOf(6), "Foot");

        preferedDeviceNames.put("SmarKo BLE",SMARKO_COLLECTION);
        preferedDeviceNames.put("Accu-Chek",GLUCOSE_COLLECTION);
        preferedDeviceAddresses.put("00:0B:57:71:1D:08",SMARKO_COLLECTION);
        preferedDeviceAddresses.put("Accu-Chek",GLUCOSE_COLLECTION);
    }
}
