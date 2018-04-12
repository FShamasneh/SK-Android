package com.firas.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class DateUtil {


    static ArrayList<String> res = new ArrayList<>();

    public static Date calcDate(Date date, int key, int value){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(key,value);
        return calendar.getTime();
    }

    public static Date getDate(String s){
        String strDate = s;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(strDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getCurrentDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date = null;
        try {
            date = dateFormat.parse(getCurrentDateString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getCurrentDateString() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }



    public static Date getCurrentDateTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());
        Date date = null;
        try {
            date = mdformat.parse(strDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getSpecificDateTime(String dateTime) {
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = mdformat.parse(dateTime);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }




    public static Object validate(Object o){
        return o!=null?o:"--";
    }

    public static String fromDateFull(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return date != null ? formatter.format(date) : "--";
    }


    public static String fromDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return date != null ? formatter.format(date) : "--";
    }

}
