package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Common.Constants;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PersonalDetailsActivity extends AppCompatActivity {

    EditText name, email, number;
    Button button_submit;
    private RequestQueue requestQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        name = (EditText) findViewById(R.id.personal_name);
        email = (EditText) findViewById(R.id.personal_email);
        number = (EditText) findViewById(R.id.personal_number);
        button_submit = (Button) findViewById(R.id.personal_button);
        loadcontactdetails();

        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userMobile = number.getText().toString();
                // Pattern match for email id
                Pattern p = Pattern.compile(com.example.myapplication.Common.Constants.regEx);
                Matcher m = p.matcher(userEmail); //used to check weather the give format by developer(in Constraints.regEx) is matching the user pattern or not


                //    Pattern match for Mobile No
                Pattern mobi = Pattern.compile(Constants.mobregEx);
                Matcher mob = mobi.matcher(userMobile); //used to check weather the give format by developer(in Constraints.regEx) is matching the user pattern or not

                if (userName.equalsIgnoreCase("") || userName.length() >= 3 || userEmail.equalsIgnoreCase("") || userEmail.length() >= 8 ||
                        userMobile.equalsIgnoreCase("") || userMobile.length() == 10)
                {
                    if (userName.equalsIgnoreCase("")) {
                        name.setError("Please Enter User Name ");
                    }else if (userEmail.equalsIgnoreCase("") || !m.find()) {
                        email.setError("Please Enter Valid Email ");
                    }else if (userMobile.equalsIgnoreCase("") || !mob.find()) {
                        number.setError("Pleas Enter Valid Mobile Number");
                    }  else {
//                        if (Constants.isOnline(getApplicationContext())) {
////                            //String SIGNUP_URL = CommonUtils.baseurl + "api/user/sign_up";
                        String apikey = "25e4f5087d509d7b02487e181139bbdb1e2d63ecac57bb1a847a47469842a891";
                        submit(userName, userMobile, userEmail,apikey);
////                        } else {
//                            Toast.makeText(getApplicationContext(),"connection failed",Toast.LENGTH_LONG).show();
//                        }
//                        Intent i = new Intent(Signup_RegActivity.this,SignInActivity.class);
//                        startActivity(i);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"entries are wrong",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void loadcontactdetails() {
        final StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://13.232.113.112/nanocart_api/index.php/Api/nc_contact_details",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);
                        System.out.println(response);

                        try {
                            ArrayList<LatLng> sourcePoints = new ArrayList<>();
                            JSONObject obj = new JSONObject(response);
                            if (obj.getInt("status") == 1) {
                                JSONObject obj1 = new JSONObject(obj.getString("user_data"));
                                String name1 = obj1.getString("contact_person");
                                String email1 = obj1.getString("email");
                                String number1 = obj1.getString("mobile");

                                name.setText(name1);
                                email.setText(email1);
                                number.setText(number1);
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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", "707");
                params.put("signed_user_api_key", "25e4f5087d509d7b02487e181139bbdb1e2d63ecac57bb1a847a47469842a891");

//                params.put("itemcount",String.valueOf(itemcount));

                //               params.put("user_id",userid);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void submit(final String getuserName, final String getuserMobile, final String getuserEmail, final String getapikey)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://13.232.113.112/nanocart_api/index.php/Api/nc_update_contact_details",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj = new JSONObject(response);
                            if (obj.getInt("status") == 1)
                            {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(PersonalDetailsActivity.this, BusinessDetailsActivity.class));
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                }

//                            int error = obj.getInt("status");
                            //if no error in response
//                            if(error==1) {
////                                confirmOtp();
//                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(PersonalDetailsActivity.this, BusinessDetailsActivity.class));
//                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
////                            startActivity(new Intent(SignupActivity.this, SignInActivity.class));
////                            finish();
//                            } else {
//                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            }
//                            JSONObject obj = new JSONObject(response);
//                            confirmOtp();
//                            Toast.makeText(getApplicationContext(), obj.getString("message"),Toast.LENGTH_LONG).show();
//                            startActivity(new Intent(getApplicationContext(), SignInActivity.class));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params  = new HashMap<>();
                params.put("contact_person",getuserName);
                params.put("mobile", getuserMobile);
                params.put("email",getuserEmail);
                params.put("user_id", "707");
                params.put("signed_user_api_key", "25e4f5087d509d7b02487e181139bbdb1e2d63ecac57bb1a847a47469842a891");

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
