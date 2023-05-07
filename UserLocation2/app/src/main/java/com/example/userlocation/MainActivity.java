package com.example.userlocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {

    int PERMISSION_INT = 44;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FusedLocationProviderClient fusedlocationproviderclient = LocationServices.getFusedLocationProviderClient(this);

        if(permissionIsGranted()){                     // User gives Location permission
            if(locationIsEnabled()){                   // User has turned on the GPS
                     fusedlocationproviderclient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                         @Override
                         public void onSuccess(Location location) {
                             String lat = String.valueOf(location.getLatitude());
                             String lng = String.valueOf(location.getLongitude());

                             Log.i("Latitude", lat);
                             Log.i("Longitude", lng);
                         }
                     });
            }
        } else {
          requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION} , PERMISSION_INT);
        }
    }
    private boolean permissionIsGranted(){
    return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    private boolean locationIsEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

}