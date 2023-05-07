package com.example.hikerwatchapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btn; String lat,lng; TextView latitude,longitude,address;
    int PERMISSION_INT = 44;
    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();                  // To hide action bar
       latitude = findViewById(R.id.latitude); longitude = findViewById(R.id.longitude); address= findViewById(R.id.address);
        FusedLocationProviderClient fusedlocationproviderclient = LocationServices.getFusedLocationProviderClient(this);
        if(permissionIsGranted()){                     // User gives Location permission
            if(locationIsEnabled()){                   // User has turned on the GPS
                fusedlocationproviderclient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        lat = String.valueOf(location.getLatitude());
                        lng = String.valueOf(location.getLongitude());
                        // Log.i("Latitude", lat); Log.i("Longitude", lng);
                        latitude.setText("Latitude : " + lat);
                        longitude.setText("Longitude : " + lng);
                        try {
                            String fullAddress = getAddress(location.getLatitude(),location.getLongitude());
                            address.setText("Address : " +fullAddress);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        } else {
            requestPermissions(new String[] {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION} , PERMISSION_INT);
        }

    }

    private String getAddress(Double latitude, Double longitude) throws IOException {
        String output = "";

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());

        addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); // Only if available else return NULL
        output = address +" , " +city + " , "+state + " , " +country + " , " +postalCode + " , " ;
        return  output;
    }

    private boolean permissionIsGranted(){
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
    private boolean locationIsEnabled(){
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

}