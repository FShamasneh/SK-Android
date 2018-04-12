package com.firas.android.model;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

// Child Model
public class Child {

    private static final long serialVersionUID = 1L;

    public Integer id = null;

    private String fullName;
    private String birthDate;
    private String gender;
    private Double height;
    private Double weight;
    private String bitmap;
    private String deviceAddress;

    public Child(){

    }

    public Child(String fullName){
        this.fullName = fullName;
    }

    public Child(String fullName, String birthDate, String gender, Double height, Double weight, String image, String deviceAddress) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.bitmap = image;
        this.deviceAddress = deviceAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return fullName;
    }

    public void setName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public Bitmap getPicture() {
        try {
            byte [] encodeByte= Base64.decode(bitmap,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public void setPicture(Bitmap image) {
        if(image==null){
            bitmap=null;
            return;
        }
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        bitmap = Base64.encodeToString(b, Base64.DEFAULT);
    }

    public String getBitmapString(){
        return bitmap;
    }

    public String toString(){
        return "Child - name: "+fullName
                +"\t\t\t\n id: "+id
                +"\t\t\t\n deviceAddress: "+deviceAddress
                +"\t\t\t\n birthDate: "+birthDate
                +"\t\t\t\n gender: "+gender
                +"\t\t\t\n height: "+height
                +"\t\t\t\n weight: "+weight
                +"\t\t\t\n picture: "+ "bitmap.substring(0,10)"+"...";
    }
}
