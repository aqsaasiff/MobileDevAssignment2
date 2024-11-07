package com.itz.livemap.locationpinned.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LocationEntity.class}, version = 1)
public abstract class LocationDatabase extends RoomDatabase {
    public abstract LocationDao locationDao();
    static LocationDatabase locationDatabase;
    public static LocationDatabase getInstance(Context context) {
        if (locationDatabase == null) {
            // Create or retrieve the Room Database instance
            locationDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    LocationDatabase.class,
                    "location_database" // Name of your database file
            ).allowMainThreadQueries().build();
        }
        return locationDatabase;
    }
}
