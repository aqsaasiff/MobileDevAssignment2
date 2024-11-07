package com.itz.livemap.locationpinned.roomdb;

import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationRepository {
    private LocationDao locationDao;
    private LiveData<List<LocationEntity>> allLocations;

    public LocationRepository(Context context) {
        LocationDatabase database = LocationDatabase.getInstance(context);
        locationDao = database.locationDao();
        allLocations = (LiveData<List<LocationEntity>>) locationDao.getAllLocations();
    }

    public void insert(LocationEntity location) {
        new InsertLocationAsyncTask(locationDao).execute(location);
    }

    public void update(LocationEntity location) {
        new UpdateLocationAsyncTask(locationDao).execute(location);
    }

    public void delete(LocationEntity location) {
        new DeleteLocationAsyncTask(locationDao).execute(location);
    }

    public LiveData<List<LocationEntity>> getAllLocations() {
        return allLocations;
    }

    // AsyncTask classes for background database operations
    private static class InsertLocationAsyncTask extends AsyncTask<LocationEntity, Void, Void> {
        private LocationDao locationDao;

        private InsertLocationAsyncTask(LocationDao locationDao) {
            this.locationDao = locationDao;
        }

        @Override
        protected Void doInBackground(LocationEntity... locations) {
            locationDao.insertLocation(locations[0]);
            return null;
        }
    }

    private static class UpdateLocationAsyncTask extends AsyncTask<LocationEntity, Void, Void> {
        private LocationDao locationDao;

        private UpdateLocationAsyncTask(LocationDao locationDao) {
            this.locationDao = locationDao;
        }

        @Override
        protected Void doInBackground(LocationEntity... locations) {
            locationDao.updateLocation(locations[0]);
            return null;
        }
    }

    private static class DeleteLocationAsyncTask extends AsyncTask<LocationEntity, Void, Void> {
        private LocationDao locationDao;

        private DeleteLocationAsyncTask(LocationDao locationDao) {
            this.locationDao = locationDao;
        }

        @Override
        protected Void doInBackground(LocationEntity... locations) {
            locationDao.deleteLocation(locations[0]);
            return null;
        }
    }
}

