package com.itz.livemap.locationpinned;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap googleMap;
    FrameLayout map;
    Double longitude,latitude;
    String address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = findViewById(R.id.map);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent = getIntent();
        address = intent.getStringExtra("address");
        longitude = intent.getDoubleExtra("longitude",0);
        latitude = intent.getDoubleExtra("latitude",0);
        Log.d("tracingname", "onCreate: address = "+address+"    longitude="+longitude+"   latitude= "+latitude);
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
      // LatLng mapCanada = new LatLng(56.1304,106.3468);
        LatLng mapCanada = new LatLng(latitude,longitude);
        this.googleMap.addMarker(new MarkerOptions().position(mapCanada).title(address));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(mapCanada));

    }
}