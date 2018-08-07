package com.example.android.quakereport;

public class Earthquake {
    private double magnitude;
    private String location;
    private long date;
    private String url;

    public double getMagnitude() {
        return magnitude;
    }

    public String getLocation() {
        return location;
    }

    public long getDate(){
        return date;
    }

    public String getUrl(){
        return url;
    }
    public Earthquake(double magnitudeBase, String locationBase, long dateBase, String urlBase) {
        magnitude = magnitudeBase;
        location = locationBase;
        date = dateBase;
        url = urlBase;
    }
}
