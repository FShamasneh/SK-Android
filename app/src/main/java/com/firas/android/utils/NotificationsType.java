package com.firas.android.utils;


public enum NotificationsType {

  HIGH_VALUE("high"),LOW_VALUE("low");


  private String name;

  NotificationsType(String name){
    this.name = name;
  }

  public String getName() {
    return name;
  }


  public static NotificationsType getNotificationsType(String name){
    for(NotificationsType s: values()){
      if(s.getName().equalsIgnoreCase(name)) return s;
    }
    return null;
  }
}
