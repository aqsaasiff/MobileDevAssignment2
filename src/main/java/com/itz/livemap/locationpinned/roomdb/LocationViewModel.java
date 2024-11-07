package com.itz.livemap.locationpinned.roomdb;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LocationViewModel extends AndroidViewModel {
    private LocationRepository repository;
    private LiveData<List<LocationEntity>> allLocations;

    public LocationViewModel(Application application) {
        super(application);
        repository = new LocationRepository(application);
        allLocations = repository.getAllLocations();
    }

    public void insert(LocationEntity location) {
        repository.insert(location);
    }

    public void update(LocationEntity location) {
        repository.update(location);
    }

    public void delete(LocationEntity location) {
        repository.delete(location);
    }

    public LiveData<List<LocationEntity>> getAllLocations() {
        return allLocations;
    }
}

