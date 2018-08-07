package com.example.android.quakereport;

import android.app.LoaderManager.LoaderCallbacks;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<Earthquake>>  {
    private static final String LOG_TAG = EarthquakeActivity.class.getName();

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=1&limit=25";

    private static final int EARTHQUAKE_LOADER_ID = 1;
    private EarthquakeAdapter currentAdapter;
    private TextView emptyPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        ListView earthquakeListView = findViewById(R.id.list);

        emptyPlaceholder = findViewById(R.id.empty_placeholder);
        earthquakeListView.setEmptyView(emptyPlaceholder);

        currentAdapter = new EarthquakeAdapter(this, new ArrayList<Earthquake>());
        earthquakeListView.setAdapter(currentAdapter);

        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
        Log.v("VERIFY_INIT_LOADER", "Loader initialized");

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Earthquake currentEarthquake = currentAdapter.getItem(position);
                Uri webLink = Uri.parse(currentEarthquake.getUrl());
                Intent webBrowser = new Intent(Intent.ACTION_VIEW, webLink);
                startActivity(webBrowser);
            }
        });
    }


    @Override
    public Loader<List<Earthquake>> onCreateLoader(int i, Bundle bundle) {
        Log.v("VERIFY_ONCREATE_LOADER", "Loader created");
        return new EarthquakeLoader(this, USGS_REQUEST_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> earthquakes) {
        ProgressBar progress = findViewById(R.id.progressbar);
        progress.setVisibility(View.GONE);
        emptyPlaceholder.setText(R.string.empty_placeholder);
        currentAdapter.clear();

        if (earthquakes != null && !earthquakes.isEmpty()) {

            Log.v("VERIFY_ONFINISH_LOADER", "Loader finished");
            currentAdapter.addAll(earthquakes);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader){
        Log.v("VERIFY_ONRESET_LOADER", "Loader reset");
        currentAdapter.clear();
    }
}


/*
    }*/

/*    ConnectivityManager connMgr = (ConnectivityManager)
            getSystemService(Context.CONNECTIVITY_SERVICE);

    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

    // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
        // Get a reference to the LoaderManager, in order to interact with loaders.
        LoaderManager loaderManager = getLoaderManager();

        // Initialize the loader. Pass in the int ID constant defined above and pass in null for
        // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
        // because this activity implements the LoaderCallbacks interface).
        loaderManager.initLoader(EARTHQUAKE_LOADER_ID, null, this);
    } else {
        // Otherwise, display error
        // First, hide loading indicator so error message will be visible
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Update empty state with no connection error message
        mEmptyStateTextView.setText(R.string.no_internet_connection);
    }
}*/
