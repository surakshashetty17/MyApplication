package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.Settings;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
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
import com.google.android.gms.location.LocationSettingsRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LocationSearchActivity extends AppCompatActivity implements LocationListener {

    EditText editText;
    LinearLayout linearLayout;
    LocationManager locationManager;
    private LocationSettingsRequest.Builder builder;
    ArrayList<LocationSearch> lstlocdata ;
    private RecyclerView recyclerView;
    private LocationSearchAdapter locationSearchAdapter;
    String category = "http://13.232.113.112/nanocart_api/index.php/Api/nc_record_details";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        editText = (EditText)findViewById(R.id.search_edittext);
        linearLayout = (LinearLayout) findViewById(R.id.search_currentlocation);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grantPermission();
                checkLocationIsEnableorNot();
                getLocation();
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.search_recyclerview);
//        editText.length();
//        editText.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (editText.length()>=2)
//                {
//                    loadsearch();
//                }
//            }
//        });
//       if (editText.length()>=2)
//       {
//           loadsearch();
//       }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("beforeTextChanged",s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("onTextChanged",s.toString()+"count="+count);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("afterTextChanged",s.toString()+"length="+s.toString().length());
                if(s.toString().length()>=4){
                    String value = String.valueOf(s);
                    loadsearch(value);

                }
            }
        });

        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (event.getX() > ( editText.getWidth() -
                            editText.getCompoundPaddingEnd())){
                        editText.setText("");
                    }
                }
                return false;
            }
        });
    }

    private void loadsearch(final String getapi)
    {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://13.232.113.112/nanocart_api/index.php/Account/get_pincode_suggestions",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);
                        System.out.println(response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            if(obj.optInt("status")==1){

                                lstlocdata = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray("pincode_data");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    LocationSearch locationSearch = new LocationSearch();
                                    JSONObject objs = dataArray.getJSONObject(i);
                                    locationSearch.setPincode(objs.getString("pincode"));
                                    locationSearch.setOfficename(objs.getString("officename"));
                                    lstlocdata.add(locationSearch);
//                                    recyclerView.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            String pincode = objs.getString("pincode");
//                                            String officeaddress = objs.getString("officename");
//                                            editText.setText(pincode+ ", "+officeaddress);
//                                        }
//                                    });


                                }

                                Recycler();

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
                params.put("suggestion_text", getapi);
                params.put("signed_user_api_key", "25e4f5087d509d7b02487e181139bbdb1e2d63ecac57bb1a847a47469842a891");

                return params;
            }
        };
        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void Recycler(){

        locationSearchAdapter = new LocationSearchAdapter(this,lstlocdata);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(locationSearchAdapter);
        StaggeredGridLayoutManager staggeredGridVertical=new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridVertical);


    }

    private void getLocation()
    {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,500,5,(LocationListener)this);
        }catch (SecurityException e)
        {
            e.printStackTrace();
        }
    }

    private void checkLocationIsEnableorNot()
    {
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gpsEnabled = false;
        boolean networkEnabled = false;

        try{
            gpsEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            networkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        if (!gpsEnabled && !networkEnabled){
            new AlertDialog.Builder(LocationSearchActivity.this)
                    .setTitle("Enable GPS Service")
                    .setCancelable(false)
                    .setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    }).setNegativeButton("cancel",null)
                    .show();
        }
    }

    private void grantPermission()
    {
        if (ContextCompat.checkSelfPermission(LocationSearchActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(LocationSearchActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            String address = addresses.get(0).getAddressLine(0);
//            String address11 = addresses.get(0).getAddressLine(1);
//            String city = addresses.get(0).getLocality();
            String city1 = addresses.get(0).getLocality();
            String pincode = addresses.get(0).getPostalCode();
//            editText.setText(addresses.get(0).getPostalCode());
            editText.setText(pincode+", #"+address );

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
