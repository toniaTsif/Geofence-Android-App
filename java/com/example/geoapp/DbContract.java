package com.example.geoapp;

public class DbContract {
    static public String db_name = "LOCATIONS_DB";
    static public int db_version = 1;
    static public String table_name = "LOCATIONS";
    static public String key_lat = "LAT";
    static public String key_lon = "LON";
    static public String key_actions = "ACTIONS";
    static public String key_time = "TIMESTAMP";
    static public String create_table = "CREATE TABLE "+table_name+" ('"+key_lat+"' FLOAT, '"+key_lon+"' FLOAT, '"+key_actions+"' TEXT, '"+key_time+"' TEXT);";
    public static String AUTHORITY = "com.example.geoapp";
    public static String PATH = DbContract.table_name;
}
