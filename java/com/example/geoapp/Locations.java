package com.example.geoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Locations {
    private double lat;
    private double lon;
    private String actions;
    private String time;
    private DbHelper dbHelper;

    public Locations(double lat, double lon, String actions, String time, Context context) {
        this.lat = lat;
        this.lon = lon;
        this.actions = actions;
        this.time = time;
        dbHelper = new DbHelper(context);
    }

    public Locations(Context context) {
        dbHelper = new DbHelper(context);
    }

    public Locations(double lat, double lon, String actions, String time) {
        this.lat = lat;
        this.lon = lon;
        this.actions = actions;
        this.time = time;
    }

    public long insert() throws Exception {
        ContentValues values = new ContentValues();
        values.put(DbContract.key_lat, this.lat);
        values.put(DbContract.key_lon, this.lon);
        values.put(DbContract.key_actions, this.actions);
        values.put(DbContract.key_time, this.time);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long result = db.insert(DbContract.table_name, null, values);
        db.close();
        if(result == -1){
            throw new Exception("Insert failed.");
        }
        return result;
    }

    public static ArrayList<Locations> selectAll(Context context){
        DbHelper dbHelper = new DbHelper(context);
        ArrayList<Locations> locations = new ArrayList<Locations>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor results = db.query(DbContract.table_name,null,null,null,null,null,null);
        if(results.moveToFirst()){
            do{
                Locations location = new Locations(results.getFloat(0),results.getFloat(1),results.getString(2),results.getString(3));
                locations.add(location);
            }while (results.moveToNext());
        }
        db.close();
        return locations;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
