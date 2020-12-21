package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
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
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CurrentLocationActivity extends AppCompatActivity {

    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    TextView textView;
    double last_longitude,last_latitude;
    SearchView searchView;

    boolean not_first_time_showing_info_window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_location);

//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        setSupportActionBar(mToolbar);
//        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
//        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
        textView = (TextView)findViewById(R.id.currentlocation);
        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        searchView = findViewById(R.id.search);

        supportMapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.myMap);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(CurrentLocationActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            getCurrentLocation();
        }
        else
        {
            ActivityCompat.requestPermissions(CurrentLocationActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},44);
        }
    }
    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("location.json");
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

                            LocationServices.getFusedLocationProviderClient(CurrentLocationActivity.this)
                                    .requestLocationUpdates(locationRequest,new LocationCallback()
                                    {
                                        @Override
                                        public void onLocationResult(LocationResult locationResult) {
                                            super.onLocationResult(locationResult);
                                            LocationServices.getFusedLocationProviderClient(CurrentLocationActivity.this)
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
                                                        String address = addresses.get(0).getAddressLine(0);
                                                        String address11 = addresses.get(0).getAddressLine(1);
                                                        String city = addresses.get(0).getLocality();
                                                        textView.setText(address+address11+city);
                                                        try {
                                                            final Context context;
                                                            Resources resources = getApplication().getResources();
                                                            JSONObject obj = new JSONObject(readJSONFromAsset());
                                                            JSONArray maps=obj.getJSONArray("Maps");
                                                            for (int i=0;i<maps.length();i++){
                                                                JSONObject jsonObject=maps.getJSONObject(i);
                                                                int pincode=jsonObject.getInt("pincode");
                                                                String name=jsonObject.getString("name");
                                                                String address1=jsonObject.getString("address");
                                                                String city1=jsonObject.getString("city");
                                                                String lat=  jsonObject.getString("latitude");
                                                                String lng= jsonObject.getString("longitude");
//                                                                String image = jsonObject.getString("image");

//                                                                String imageName = jsonObject.getString("image");
//
//                                                                final int resourceId = resources.getIdentifier(imageName, "drawable", getApplication().getPackageName());
//
//                                                                Bitmap bitmap = BitmapFactory.decodeResource(getApplication().getResources(), resourceId);
//                                                                String im = String.valueOf(bitmap);
//                                                                Picasso.with(getApplicationContext())
//                                                                        .load(image)
//                                                                        .error(R.drawable.location)
//                                                                        .into(image);
//                                                                maps.add(new Country(code,nameAr,nameEn));
                                                                double  latitude1=Double.parseDouble(lat);
                                                                double longitude1= Double.parseDouble(lng);
                                                                MarkerOptions near = new MarkerOptions().position(new LatLng(latitude1,longitude1))
                                                                        .title("Name: "+name)
                                                                        .snippet("Address: "+address1+"\n"+"City: "+city1)
                                                                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));

//                                                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.location));
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

//                                                                        View v = getLayoutInflater().inflate(R.layout.custom_window, null);
                                                                        ImageView image = new ImageView(getApplicationContext());

//                                                                        if (not_first_time_showing_info_window) {
//                                                                            Picasso.with(CurrentLocationActivity.this).load("google.png").into(image);
//
//                                                                        } else {
//                                                                            not_first_time_showing_info_window = true;
//                                                                            Picasso.with(CurrentLocationActivity.this).load("google.png").into(image);
//                                                                        }

                                                                        TextView title = new TextView(getApplicationContext());
                                                                        title.setTextColor(Color.RED);
//                                                                        title.setGravity(Gravity.CENTER);
                                                                        title.setTypeface(null, Typeface.BOLD);
                                                                        title.setText(marker.getTitle());
//                                                                        image.setImageResource(resourceId);

                                                                        TextView snippet = new TextView(getApplicationContext());
                                                                        snippet.setTextColor(Color.BLUE);
                                                                        snippet.setText(marker.getSnippet());

                                                                        info.addView(title);
                                                                        info.addView(snippet);

                                                                        return info;
                                                                    }
                                                                });


                                                            }
//                                                            DBHelper.getInstance(activity).insertCountries(countryList);

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


//                            MarkerOptions options = new MarkerOptions()
//                                    .position(latLng)
//                                    .title("Current location")
//                                    .draggable(true)
////                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_pin)
//                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                            textView.append("Lattitude: " + latLng);
//
//                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
//                            googleMap.addMarker(options);

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
//                                    textView.append("Lattitude: " + latLng);

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
//                                    textView.setText("Lattitude"+last_latitude+last_longitude);
                                }

                                @Override
                                public void onMarkerDrag(Marker arg0) {
                                    // TODO Auto-generated method stub
                                    Log.i("System out", "onMarkerDrag...");
                                }
                            });
//                            BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.location);
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
