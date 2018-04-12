package com.firas.android.model;

import com.firas.android.utils.NotificationsType;
import com.firas.android.utils.SensorType;


// Notification Model
public class Notification {

    private static final long serialVersionUID = 1L;

    private int id;
    private boolean isNew;
    private NotificationsType notificationsType;
    private SensorType sensorType;
    private String deviceAddress;
    private int childRef;
    private String value;
    private String recorded;


    public Notification(NotificationsType notificationsType, SensorType sensorType, String address, String value, int childRef, boolean isNew, String time){
        this.isNew = isNew;
        this.notificationsType = notificationsType;
        this.sensorType = sensorType;
        this.deviceAddress = address;
        this.childRef = childRef;
        this.value = value;
        this.recorded = time;
    }

    public Notification(int id, NotificationsType notificationsType, SensorType sensorType, String address, String value, int childRef, boolean isNew, String time){
        this.id = id;
        this.isNew = isNew;
        this.notificationsType = notificationsType;
        this.sensorType = sensorType;
        this.deviceAddress = address;
        this.childRef = childRef;
        this.value = value;
        this.recorded = time;
    }

    public Notification clone(){
        return new Notification(getNotificationsType(),sensorType,deviceAddress,getValue(),getChildRef(),isNew(),getRecorded());
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isNew() {
        return isNew;
    }

    public Notification setNew(boolean aNew) {
        isNew = aNew;
        return this;
    }

    public NotificationsType getNotificationsType() {
        return notificationsType;
    }

    public void setNotificationsType(NotificationsType notificationsType) {
        this.notificationsType = notificationsType;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRecorded() {
        return recorded;
    }

    public void setRecorded(String recorded) {
        this.recorded = recorded;
    }

    public int getId() {
        return id;
    }

    public int getChildRef() {
        return childRef;
    }

    public void setChildRef(int childRef) {
        this.childRef = childRef;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", childRef="+childRef+
                ", type=" + notificationsType +
                ", sensorType=" + sensorType +
                ", isNew=" + isNew +
                ", value='" + value + '\'' +
                ", recorded='" + recorded + '\'' +
                '}';
    }
}
