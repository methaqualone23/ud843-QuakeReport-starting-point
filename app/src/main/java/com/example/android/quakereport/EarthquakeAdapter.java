package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(Context context, List<Earthquake> earthquakes) {
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
        TextView timeView = convertView.findViewById(R.id.time);
        TextView distanceView = convertView.findViewById(R.id.distance);

        DecimalFormat formatter = new DecimalFormat("0.0");
        String magnitude = formatter.format(currentEarthquake.getMagnitude());
        magnitudeView.setText(magnitude);

        String webLink = currentEarthquake.getUrl();
        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = (int) getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        String locationString = currentEarthquake.getLocation();
        String[] parts = locationString.split(Pattern.quote(","));
        if (locationString.contains(",")) {
            String part1 = parts[0];
            String part2 = parts[1];
            distanceView.setText(part1);
            locationView.setText(part2);
        } else {
            distanceView.setText("Near the");
            locationView.setText(currentEarthquake.getLocation());
        }

        Date dateObject = new Date(currentEarthquake.getDate());

        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        String formattedTime = formatTime(dateObject);
        timeView.setText(formattedTime);
        return convertView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private double getMagnitudeColor(double magnitude) {
        int magnitudeColorId;
        int magnitudeValue = (int) Math.floor(magnitude);
        switch (magnitudeValue) {
            case 0:
            case 1:
                magnitudeColorId = R.color.magnitude1;
                break;

            case 2:
                magnitudeColorId = R.color.magnitude2;
                break;

            case 3:
                magnitudeColorId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorId = R.color.magnitude9;
                break;
            default:
                magnitudeColorId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorId);
    }
}

