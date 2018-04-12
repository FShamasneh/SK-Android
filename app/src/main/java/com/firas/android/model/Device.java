package com.firas.android.model;

import android.bluetooth.BluetoothDevice;

import com.firas.android.utils.SensorType;

import java.util.Map;


// Child Model
public class Device {

    private static final long serialVersionUID = 1L;

    public Integer id = null;

    private String name;
    private String address;
    private Integer image;
    private Integer childRef;
    private BodyPart bodyPart;
    private Map<SensorType,Range> sensorRanges;


    public Device(){
    }

    public Device(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public Device(String address) {
        this.name = "";
        this.address = address;
    }

    public Device(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Device(BluetoothDevice device) {
        this.name = device.getName();
        this.address = device.getAddress();
    }

    public Device(String name, String address, Integer childRef) {
        this.name = name;
        this.address = address;
        this.childRef = childRef;
    }

    public Device(String name, String address, int image, Integer childRef, Map<SensorType,Range> checkedCharacteristics) {
        this.name = name;
        this.address = address;
        this.childRef = childRef;
        this.image = image;
        this.sensorRanges = checkedCharacteristics;
    }

    public Device(int id, String name, String address, int image, Integer childRef, BodyPart bodyPart, Map<SensorType,Range> checkedCharacteristics) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.childRef = childRef;
        this.image = image;
        this.bodyPart = bodyPart;
        this.sensorRanges = checkedCharacteristics;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getChildRef() {
        return childRef;
    }

    public void setChildRef(Integer childRef) {
        this.childRef = childRef;
    }

    public Map<SensorType,Range> getSensorRanges() {
        return sensorRanges;
    }

    public void setSensorRanges(Map<SensorType,Range> sensorRanges) {
        this.sensorRanges = sensorRanges;
    }

    public void addSensorRange(SensorType sensorType, Range range) {
        this.sensorRanges.put(sensorType,range);
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public String toString(){
        return "Device - name: "+ name
                +"\t\n address: "+address
                +"\t\n childRef: "+childRef
                +"\t\n picture: "+image
                +"\t\n bodyPart: "+bodyPart
                +"\t\n sensorRanges: "+ sensorRanges;

    }
}
