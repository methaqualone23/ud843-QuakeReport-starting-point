package com.example.android.quakereport;

public class Earthquake {
    private String magnitude;
    private String location;
    private String date;

    public String getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDate(){
        return date;
    }

    public Earthquake(String magnitudeBase, String locationBase, String dateBase) {
        magnitude = magnitudeBase;
        location = locationBase;
        date = dateBase;
    }
}
