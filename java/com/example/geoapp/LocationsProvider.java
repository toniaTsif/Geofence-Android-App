package com.example.geoapp;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LocationsProvider extends ContentProvider {
    static final UriMatcher uriMatcher;
    private DbHelper helper;
    static{
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(DbContract.AUTHORITY,DbContract.PATH,1); //select all
        uriMatcher.addURI(DbContract.AUTHORITY,DbContract.PATH+"/latlong",2); //select latitude, longitude
    }

    @Override
    public boolean onCreate() {
        helper = new DbHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = null;
        switch (uriMatcher.match(uri)){
            case 1:
                //select * from locations
                cursor = db.query(DbContract.table_name, null,null,null,null,null,null);
                break;
            case 2:
                //select latitude, longitude from locations
                cursor = db.query(DbContract.table_name, new String[]{DbContract.key_lat, DbContract.key_lon},null,null,null,null,null);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
