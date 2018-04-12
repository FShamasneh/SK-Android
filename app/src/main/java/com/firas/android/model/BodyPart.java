package com.firas.android.model;

import java.util.ArrayList;
import java.util.List;



public enum BodyPart {

    RECTAL("RECTAL",0.0699),HEAD("HEAD",0.3094),TORSO("TORSO",0.5067),HAND("HAND",0.7665),FOOT("FOOT",2.1807);

    private String name;
    private double alpha;
    BodyPart(String name, double alpha){
        this.name = name;
        this.alpha = alpha;
    }

    public static BodyPart isBodyPart(String bobyPart){
        if (bobyPart==null) return null;
        for(BodyPart b: BodyPart.values()){
            if (b.name().equalsIgnoreCase(bobyPart)) return b;
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public double getAlpha() {
        return alpha;
    }

    public static List<String> getBodyPartList() {
        List<String> temp = new ArrayList<>();
        for(BodyPart b: BodyPart.values()) temp.add(b.getName());
        return temp;
    }
}
