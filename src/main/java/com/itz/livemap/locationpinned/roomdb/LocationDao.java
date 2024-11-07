package com.itz.livemap.locationpinned.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insertLocation(LocationEntity locationEntity);

    @Update
    void updateLocation(LocationEntity locationEntity);

    @Delete
    void deleteLocation(LocationEntity locationEntity);

    @Query("SELECT * FROM locations")
    List<LocationEntity> getAllLocations();

    // Additional queries as needed
}
