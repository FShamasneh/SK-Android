package com.firas.android.utils;

public enum SensorType {

   GLUCOSE_SENSOR(""),
   WEIGHT_SENSOR(""),
   BATTERY_LEVEL_SENSOR(""),
   BATTERY_VOLT_SENSOR(""),
   BATTERY_CURRENT_SENSOR(""),
   BATTERY_TEMP_SENSOR(""),
   HEART_RATE_SENSOR("Heart Rate"),
   BODY_LOCATION_SENSOR(""),
   OXIMETER_SPOCK_CHECK_SENSOR(""),
   OXIMETER_CONTINOUS_MEASUREMENT_SENSOR("Blood Oxygen Saturation"),
   BODY_TEMP_SENSOR("Body Temperature"),
   HUMIDITY_SENSOR(""),
   TEMPARATURE_SENSOR(""),
   ACCELERATION_SENSOR(""),
   ORIENTATION_SENSOR(""),
   GPS_SENSOR("GPS"),
   SKIN_CONTACT("Skin Contact"),
   UNKNOWN("");

   private String name;

   SensorType(String name){
      this.name = name;
   }

   public String getName() {
      return name;
   }

   public static SensorType getSensorType(String name){
      for(SensorType s: values()){
         if(s.getName().equalsIgnoreCase(name)) return s;
      }
      return null;
   }
}
