package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {


        GoogleMap mGoogleMap;
        SupportMapFragment mapFrag;
        LocationRequest mLocationRequest;
        GoogleApiClient mGoogleApiClient;
        Location mLastLocation;
        Marker mCurrLocationMarker;

        TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }


        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.myMap_locationapi);
        mapFrag.getMapAsync(this);
        textView = (TextView) findViewById(R.id.text_location);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {

            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null)
        {
            mCurrLocationMarker.remove();
        }

        Log.d("strrrrr", ">>......................................");

        //Place current location marker
//        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
//        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
//        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

        if (mGoogleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }

        try {

            final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://13.232.113.112/nanocart_api/index.php/Api/nc_location_details",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            Log.d("strrrrr", ">>" + response);
                            System.out.println(response);

                            try {
                                ArrayList<LatLng> sourcePoints = new ArrayList<>();
                                JSONObject obj = new JSONObject(response);
                                Log.d("..........",",,,,,,,,"+obj.getString("user_data"));
//                                Log.d("..........",",,,,,,,,"+obj.getString("locality_name"));
                                if (obj.getInt("status") == 1)
                                {

                                    JSONObject obj1 = new JSONObject(obj.getString("user_data"));
                                    Log.d("..........",",,,,,,,,=========================="+obj.getString("user_data"));


//                                    JSONArray maps=obj.getJSONArray("user_data");
//                                    for (int i=0;i<maps.length();i++) {
//                                        JSONObject jsonObject = maps.getJSONObject(i);
                                        String name = obj1.getString("locality_name");
                                        String address1 = obj1.getString("locality_name");
                                        String city1 = obj1.getString("locality_name");
                                        String lat = obj1.getString("latitude");
                                        String lng = obj1.getString("longitude");
//                                        String text = jsonObject.getString("locality_name");



                                        double latitude1 = Double.parseDouble(lat);
                                        double longitude1 = Double.parseDouble(lng);
                                        MarkerOptions near = new MarkerOptions().position(new LatLng(latitude1, longitude1))
                                                .title("Name: " + name)
                                                .snippet("Address: " + address1 + "\n" + "City: " + city1)
                                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                    LatLng latLng = new LatLng(latitude1, longitude1);
                                    textView.setText(name);

                                    mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                    mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

                                        mGoogleMap.addMarker(near);

                                    }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //displaying the error in toast if occurrs
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("user_id", "8");
                    params.put("signed_user_api_key", "25e4f5087d509d7b02487e181139bbdb1e2d63ecac57bb1a847a47469842a891");

//                params.put("itemcount",String.valueOf(itemcount));

                    //               params.put("user_id",userid);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
//            String url = "http://13.232.113.112/nanocart_api/index.php/Api/nc_location_details";
//            List<LatLng> sourcePoints = new ArrayList<>();
//            JSONObject obj = new JSONObject(url);
//            if (obj.getInt("status") == 1)
//            {
//            JSONArray maps=obj.getJSONArray("user_data");
//            for (int i=0;i<maps.length();i++) {
//                JSONObject jsonObject = maps.getJSONObject(i);
//                String name = jsonObject.getString("name");
//                String address1 = jsonObject.getString("address");
//                String city1 = jsonObject.getString("city");
//                String lat = jsonObject.getString("latitude");
//                String lng = jsonObject.getString("longitude");
//
//                double latitude1 = Double.parseDouble(lat);
//                double longitude1 = Double.parseDouble(lng);
//                MarkerOptions near = new MarkerOptions().position(new LatLng(latitude1, longitude1))
//                        .title("Name: " + name)
//                        .snippet("Address: " + address1 + "\n" + "City: " + city1)
//                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
//
//
//                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
//                        Uri.parse("google.navigation:q=" + latitude1 + "," + longitude1 + "&mode=b"));
//                startActivity(intent);
//
////               mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sourcePoints.get(0), 15));
//
//                // route for location
//
//                sourcePoints.add(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude()));
//                sourcePoints.add(new LatLng(latitude1, longitude1));
//
//                PolylineOptions routeLine;
//                LatLng pos = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
//                routeLine = new PolylineOptions().add(pos);
//                routeLine.add(new LatLng(latitude1, longitude1));
//                routeLine.addAll(sourcePoints);
//                routeLine.color(getResources().getColor(R.color.red));
//                mGoogleMap.addPolyline(routeLine);
//
////                List<LatLng> snappedPoints = new ArrayList<>();
////                new GetSnappedPointsAsyncTask().execute(sourcePoints, null, snappedPoints);
//
//
//                mGoogleMap.addMarker(near);
//
//                mGoogleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
//
//                    @Override
//                    public View getInfoWindow(Marker arg0) {
//                        return null;
//                    }
//
//
//                    @Override
//                    public View getInfoContents(Marker marker) {
//
//                        LinearLayout info = new LinearLayout(getApplicationContext());
//                        info.setOrientation(LinearLayout.VERTICAL);
//
//                        TextView title = new TextView(getApplicationContext());
//                        title.setTextColor(Color.RED);
//                        title.setTypeface(null, Typeface.BOLD);
//                        title.setText(marker.getTitle());
//
//                        TextView snippet = new TextView(getApplicationContext());
//                        snippet.setTextColor(Color.BLUE);
//                        snippet.setText(marker.getSnippet());
//
//                        info.addView(title);
//                        info.addView(snippet);
//
//                        return info;
//                    }
//                });
//
//            }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap=googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }
    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


}
