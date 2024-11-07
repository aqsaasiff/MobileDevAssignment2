package com.itz.livemap.locationpinned;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.itz.livemap.locationpinned.adapters.LocationListAdapter;
import com.itz.livemap.locationpinned.databinding.ActivityMainBinding;
import com.itz.livemap.locationpinned.roomdb.LocationDao;
import com.itz.livemap.locationpinned.roomdb.LocationDatabase;
import com.itz.livemap.locationpinned.roomdb.LocationEntity;
import com.itz.livemap.locationpinned.roomdb.LocationRepository;
import com.itz.livemap.locationpinned.roomdb.LocationViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LocationDao locationDao;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<LocationEntity> arrayList = new ArrayList<>();

        LocationDatabase database = LocationDatabase.getInstance(this);
        locationDao = database.locationDao();
        arrayList = locationDao.getAllLocations();


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        LocationListAdapter adapter = new LocationListAdapter(this,arrayList);
        binding.recyclerView.setAdapter(adapter);

        binding.floatingActionButton.setOnClickListener(v->{
            openDialog();
        });

    }

    private void openDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.edit_text_box);
        int width = WindowManager.LayoutParams.MATCH_PARENT;
        int height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(false);
        final EditText longitudeEdt = dialog.findViewById(R.id.longitudeEdt);
        final EditText latitudeEdt = dialog.findViewById(R.id.latitudeEdt);
        final EditText addressEdt = dialog.findViewById(R.id.addressEdt);
        final TextView clickButton = dialog.findViewById(R.id.addButton);


        clickButton.setOnClickListener(view -> {
            String longitude = longitudeEdt.getText().toString();
            String latitude = latitudeEdt.getText().toString();
            String address = addressEdt.getText().toString();

            if (longitude.isEmpty()) {
                longitudeEdt.setError("Enter longitude");
            } else if (latitude.isEmpty()) {
                latitudeEdt.setError("Enter latitude");
            } else {
             LocationEntity location = new LocationEntity(address,stringToDouble(latitude) , stringToDouble(longitude));
             locationDao.insertLocation(location);
             dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static double stringToDouble(String str) {
        try {
            double value = Double.parseDouble(str);
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid double format: " + str);
        }
    }



}