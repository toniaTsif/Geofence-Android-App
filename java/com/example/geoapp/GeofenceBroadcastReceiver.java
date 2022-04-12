package com.example.geoapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofenceStatusCodes;
import com.google.android.gms.location.GeofencingEvent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
        String action="";
        String timestamp="";

        if (geofencingEvent.hasError()) {
            String errorMessage = GeofenceStatusCodes.getStatusCodeString(geofencingEvent.getErrorCode());
            Log.e("BroadcastReceiver", errorMessage);
            return;
        }
        int transition = geofencingEvent.getGeofenceTransition();
        if(transition == Geofence.GEOFENCE_TRANSITION_ENTER){
            action = "ENTER";
            Log.d("BroadcastReceiver","User entered in geofence!");
            Toast.makeText(context, "User entered in geofence!", Toast.LENGTH_SHORT).show();

            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm", Locale.getDefault());
            timestamp = sdf.format(new Date());
        }
        if (transition == Geofence.GEOFENCE_TRANSITION_EXIT){
            action = "EXIT";
            Log.d("BroadcastReceiver","User exited from geofence!");
            Toast.makeText(context, "User exited from geofence!", Toast.LENGTH_SHORT).show();

            SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy HH:mm", Locale.getDefault());
            timestamp = sdf.format(new Date());
        }

        Locations location = new Locations(geofencingEvent.getTriggeringLocation().getLatitude(),geofencingEvent.getTriggeringLocation().getLongitude(), action,timestamp,context);
        long result = 0;
        try {
            result = location.insert();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(context, "Added row "+result+" to database.", Toast.LENGTH_SHORT).show();
    }
}
