package com.example.accidentsms;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.io.IOException;
import java.util.List;

public class Accident extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap map;
    SupportMapFragment mapFragment;

    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident);

        fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(this);
        fetchLastLocation();

        mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.maps_frag_show);
        getQuery();

    }



    public void getQuery()
    {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("AccidentData");

        query.findInBackground( new FindCallback<ParseObject>() {
            public void done(List<ParseObject> objects, ParseException e) {
                for(ParseObject place:objects){

                    String locationA=place.get("Place").toString();
                    List<Address> addressListA=null;


                    if(locationA!=null || !locationA.equals(""))
                    {
                        Geocoder geocoder=new Geocoder(Accident.this);

                        try {
                            addressListA=geocoder.getFromLocationName(locationA,1);
                        } catch (IOException et) {
                            et.printStackTrace();
                        }

                        Address addressA=addressListA.get(0);
                        LatLng latLngA=new LatLng(addressA.getLatitude(),addressA.getLongitude());
                        map.addMarker(new MarkerOptions().position(latLngA).title(locationA));
                        //map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLngA,10));
                    }
                }
            }

        });

        mapFragment.getMapAsync(this::onMapReady);

    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;
    }


    private void fetchLastLocation() {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {


                new AlertDialog.Builder(this)
                        .setTitle("Required Location Permission")
                        .setMessage("You have to give this permission to acess this feature")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(Accident.this,
                                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create()
                        .show();


            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);

            }
        } else {

            fusedLocationProviderClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());

                                map.addMarker(new MarkerOptions().position(latLng).title(String.valueOf(location)));
                                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                                Toast.makeText(getApplicationContext(), "Your Current Location ", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                //abc
            }else{

            }
        }
    }

}
