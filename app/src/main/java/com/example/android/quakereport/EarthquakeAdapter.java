package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context, ArrayList<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Earthquake currentEarthquake = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        TextView locationView = convertView.findViewById(R.id.location);
        TextView magnitudeView = convertView.findViewById(R.id.magnitude);
        TextView dateView = convertView.findViewById(R.id.date);

        magnitudeView.setText(currentEarthquake.getMagnitude());
        dateView.setText(currentEarthquake.getDate());
        locationView.setText(currentEarthquake.getLocation());

        return convertView;
    }
}
