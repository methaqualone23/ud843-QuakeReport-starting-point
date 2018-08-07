package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {

    private static final String LOG_TAG = EarthquakeLoader.class.getName();

    private String queryURL;
    public EarthquakeLoader(Context context, String url) {
        super(context);
        queryURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        Log.v("VERIFY_ONSTART_LOADER", "Loader started");
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (queryURL == null) {
            Log.v("VERIFY_INBACK_LOADER", "Loader started in background");
            return null;
        }
        List<Earthquake> result = QueryUtils.fetchEarthquakeData(queryURL);
        Log.v("VERIFY_QUERYUTILS", "Data fetched");
        return result;
    }
}
