package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NavigationMapActivity extends AppCompatActivity {


    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    TextView textView;
    double last_longitude,last_latitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_map);

        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.myMap1);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(NavigationMapActivity.this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            getCurrentLocation();
        }
        else
        {
            ActivityCompat.requestPermissions(NavigationMapActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
    }
    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("navlocation.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void getCurrentLocation()
    {

        final LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(final Location location) {
                if (location != null)
                {
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(final GoogleMap googleMap) {
                            final LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());

                            LocationServices.getFusedLocationProviderClient(NavigationMapActivity.this)
                                    .requestLocationUpdates(locationRequest,new LocationCallback()
                                    {
                                        @Override
                                        public void onLocationResult(LocationResult locationResult) {
                                            super.onLocationResult(locationResult);
                                            LocationServices.getFusedLocationProviderClient(NavigationMapActivity.this)
                                                    .removeLocationUpdates(this);
                                            if (locationRequest != null && locationResult.getLocations().size() > 0)
                                            {
                                                int latestLocationIndex = locationResult.getLocations().size() - 1;
                                                double latitude = locationResult.getLocations().get(latestLocationIndex).getLatitude();
                                                double longitude = locationResult.getLocations().get(latestLocationIndex).getLongitude();
                                                Geocoder geocoder;
                                                List<Address> addresses;
                                                geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                                try
                                                {
                                                    addresses = geocoder.getFromLocation(latitude, longitude,1);
                                                    if (addresses != null && addresses.size() > 0)
                                                    {
                                                        try {
                                                            JSONObject obj = new JSONObject(readJSONFromAsset());
                                                            JSONArray maps=obj.getJSONArray("Maps");
                                                            for (int i=0;i<maps.length();i++){
                                                                JSONObject jsonObject=maps.getJSONObject(i);
                                                                String name=jsonObject.getString("name");
                                                                String address1=jsonObject.getString("address");
                                                                String city1=jsonObject.getString("city");
                                                                String lat=  jsonObject.getString("latitude");
                                                                String lng= jsonObject.getString("longitude");

                                                                double  latitude1=Double.parseDouble(lat);
                                                                double longitude1= Double.parseDouble(lng);
                                                                MarkerOptions near = new MarkerOptions().position(new LatLng(latitude1,longitude1))
                                                                        .title("Name: "+name)
                                                                        .snippet("Address: "+address1+"\n"+"City: "+city1)
                                                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));


                                                                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                                                                        Uri.parse("google.navigation:q="+latitude1+","+longitude1+ "&mode=b"));
                                                                startActivity(intent);

                                                                // route for location
                                                                PolylineOptions routeLine;
                                                                LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
                                                                routeLine = new PolylineOptions().add(pos);
                                                                routeLine.add(new LatLng(latitude1, longitude1));
                                                                routeLine.getPoints().add(pos);
                                                                routeLine.color(getResources().getColor(R.color.red));
                                                                googleMap.addPolyline(routeLine);

//                                                                LatLngBounds.Builder builder = new LatLngBounds.Builder();
//                                                                builder.include(result.get(0));
//                                                                builder.include(result.get(result.size()-1));
//                                                                LatLngBounds bounds = builder.build();
//                                                                googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 10));

                                                                 googleMap.addMarker(near);

                                                                googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                                                                    @Override
                                                                    public View getInfoWindow(Marker arg0) {
                                                                        return null;
                                                                    }


                                                                    @Override
                                                                    public View getInfoContents(Marker marker) {

                                                                        LinearLayout info = new LinearLayout(getApplicationContext());
                                                                        info.setOrientation(LinearLayout.VERTICAL);

                                                                        TextView title = new TextView(getApplicationContext());
                                                                        title.setTextColor(Color.RED);
                                                                        title.setTypeface(null, Typeface.BOLD);
                                                                        title.setText(marker.getTitle());

                                                                        TextView snippet = new TextView(getApplicationContext());
                                                                        snippet.setTextColor(Color.BLUE);
                                                                        snippet.setText(marker.getSnippet());

                                                                        info.addView(title);
                                                                        info.addView(snippet);

                                                                        return info;
                                                                    }
                                                                });


                                                            }

                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                }
                                                catch (IOException e) {
                                                }

                                            }
                                        }
                                    }, Looper.getMainLooper());
                            Log.d("hello","current location" +latLng);

                            googleMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
                                @Override
                                public void onMarkerDragStart(Marker arg0) {
                                    // TODO Auto-generated method stub
                                    Log.d("System out", "onMarkerDragStart..."+arg0.getPosition().latitude+"..."+arg0.getPosition().longitude);
                                }

                                @SuppressWarnings("unchecked")
                                @Override
                                public void onMarkerDragEnd(Marker arg0) {
                                    // TODO Auto-generated method stub
                                    Log.d("System out", "onMarkerDragEnd"+arg0.getPosition().latitude+""+arg0.getPosition().longitude);

                                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));

                                    last_latitude = arg0.getPosition().latitude;
                                    last_longitude = arg0.getPosition().longitude;
                                    Geocoder geocoder;
                                    List<Address> addresses;
                                    geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                    try
                                    {
                                        addresses = geocoder.getFromLocation(last_latitude, last_longitude,1);
                                        if (addresses != null && addresses.size() > 0)
                                        {
                                            String address = addresses.get(0).getAddressLine(0);
                                            String address11 = addresses.get(0).getAddressLine(1);
                                            String city = addresses.get(0).getLocality();
                                            textView.setText(address+address11+city);

                                        }
                                    }
                                    catch (IOException e) {
                                    }
                                }

                                @Override
                                public void onMarkerDrag(Marker arg0) {
                                    // TODO Auto-generated method stub
                                    Log.i("System out", "onMarkerDrag...");
                                }
                            });
                            MarkerOptions options = new MarkerOptions()
                                    .position(latLng)
                                    .title("Current location")
                                    .draggable(true);
                            options.icon(BitmapDescriptorFactory.fromResource(R.drawable.locationpin));

                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                            googleMap.addMarker(options);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 44)
        {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                getCurrentLocation();
            }
        }
    }
}
