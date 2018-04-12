package com.firas.android.model;


public class Range<T> {

    private T lower;
    private T upper;

    public Range(T lower, T upper){
        this.lower = lower;
        this.upper = upper;
    }

    public T getLower() {
        return lower;
    }

    public void setLower(T lower) {
        this.lower = lower;
    }

    public T getUpper() {
        return upper;
    }

    public void setUpper(T upper) {
        this.upper = upper;
    }

    @Override
    public String toString() {
        return "("+lower+","+upper+")";
    }
}
