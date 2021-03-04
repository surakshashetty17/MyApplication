package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Common.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileSignupActivity extends AppCompatActivity {

    EditText Mobile;
    Button register,buttonConfirm;
    EditText confirmotp,confirmuserid;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_signup);

        Mobile = (EditText) findViewById(R.id.mobile_signup1);
        register = (Button) findViewById(R.id.regnow_signup1);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userMobile = Mobile.getText().toString();

                //    Pattern match for Mobile No
                Pattern mobi = Pattern.compile(Constants.mobregEx);
                Matcher mob = mobi.matcher(userMobile); //used to check weather the give format by developer(in Constraints.regEx) is matching the user pattern or not

                if (userMobile.equalsIgnoreCase("") || userMobile.length() == 10)
                {
                    if (userMobile.equalsIgnoreCase("") || !mob.find()) {
                        Mobile.setError("Pleas Enter Valid Mobile Number");
                    } else {
                        if (Constants.isOnline(getApplicationContext())) {
                            String apikey = "4503b318cc4c599ba00e1e92a70559ecaa911a2cfea20b08bed568c98869e99e";
                            register(userMobile,apikey);
                        } else {
                            Toast.makeText(getApplicationContext(),"No internet Connection",Toast.LENGTH_LONG).show();
                        }

//                        String apikey = "4503b318cc4c599ba00e1e92a70559ecaa911a2cfea20b08bed568c98869e99e";
//                        register(userMobile,apikey);

                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"entries are wrong",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void register(final String getuserMobile, final String getapikey){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://13.232.113.112/nanocart_api/index.php/Api/signup_otp",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject obj = new JSONObject(response);
                            int error = obj.getInt("status");
                            //if no error in response
                            if(error==1) {
                                confirmOtp();
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(SignupActivity.this, SignInActivity.class));
//                            finish();
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
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
                params.put("mobile", getuserMobile);
                params.put("request_otp_api_key",getapikey);
                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void confirmOtp() throws JSONException {
        //Creating a LayoutInflater object for the dialog box
        LayoutInflater li = LayoutInflater.from(this);
        //Creating a view to get the dialog box
        View confirmDialog = li.inflate(R.layout.dialog_confirm, null);

        //Initizliaing confirm button fo dialog box and edittext of dialog box
        buttonConfirm = (Button) confirmDialog.findViewById(R.id.buttonConfirm);
        confirmotp = (EditText) confirmDialog.findViewById(R.id.editTextOtp);
        confirmuserid = (EditText) confirmDialog.findViewById(R.id.editTextuserid);


        //Creating an alertdialog builder
        AlertDialog.Builder alert = new AlertDialog.Builder(this);

        //Adding our dialog box to the view of alert dialog
        alert.setView(confirmDialog);

        //Creating an alert dialog
        final AlertDialog alertDialog = alert.create();

        //Displaying the alert dialog
        alertDialog.show();

        //On the click of the confirm button from alert dialog
        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hiding the alert dialog
                alertDialog.dismiss();

                //Displaying a progressbar
                final ProgressDialog loading = ProgressDialog.show(MobileSignupActivity.this, "Authenticating", "Please wait while we check the entered code", false,false);

                //Getting the user entered otp from edittext
                final String mobile=Mobile.getText().toString().trim();
                final String otp = confirmotp.getText().toString().trim();
                final String user_id = confirmuserid.getText().toString().trim();
                final String apikey1 = "f871caac92a40678baa9e5b020b7635302a108509139f7d20e4da37fe0ad0225";
                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://13.232.113.112/nanocart_api/index.php/Api/confirm_signup_otp" ,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject objo = new JSONObject(response);

                                    System.out.println(objo);

                                    int otp=objo.getInt("status");
                                    System.out.println(objo);
                                    //if the server response is success
                                    if(otp==1){
                                        //dismissing the progressbar

                                        loading.dismiss();

                                        //Starting a new activity
                                        startActivity(new Intent(MobileSignupActivity.this, Signup_RegActivity.class));
                                    }else{
                                        //Displaying a toast if the otp entered is wrong
                                        Toast.makeText(MobileSignupActivity.this,"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
                                        try {
                                            //Asking user to enter otp again
                                            confirmOtp();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }
                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                alertDialog.dismiss();
                                Toast.makeText(MobileSignupActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //Adding the parameters otp and username
                        params.put("mobile",mobile );
                        params.put("otp",otp);
                        params.put("user_id",user_id);
                        params.put("confirm_otp_api_key",apikey1);
                        return params;
                    }
                };

                //Adding the request to the queue
                requestQueue.add(stringRequest);
            }
        });

    }
}
