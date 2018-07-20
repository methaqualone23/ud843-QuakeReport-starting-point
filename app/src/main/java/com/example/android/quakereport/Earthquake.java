package com.example.android.quakereport;

public class Earthquake {
    private double magnitude;
    private String location;
    private long date;

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getDate(){
        return date;
    }

    public Earthquake(double magnitudeBase, String locationBase, long dateBase) {
        magnitude = magnitudeBase;
        location = locationBase;
        date = dateBase;
    }
}
