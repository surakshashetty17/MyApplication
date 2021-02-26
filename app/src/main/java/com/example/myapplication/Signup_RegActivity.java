package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup_RegActivity extends AppCompatActivity {

    EditText User_name,Email,Mobile,Password,ReenterPass;
    ImageButton img1, img2;  //  password eye
    private int passwordNotVisible = 1;
    String sign_url = "http://192.168.0.147/mobile-api/index.php/api/signup";
    Button register,buttonConfirm;
    TextView login;
    EditText confirmotp;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__reg);

        User_name = (EditText) findViewById(R.id.signup_username);
        Email = (EditText) findViewById(R.id.signup_email);
        Mobile = (EditText) findViewById(R.id.signup_mobilenumber);
        Password = (EditText) findViewById(R.id.signup_pass);
        ReenterPass = (EditText) findViewById(R.id.signup_repass);
        img1 = (ImageButton) findViewById(R.id.image_pass);
        img2 = (ImageButton) findViewById(R.id.image_repass);
        login = (TextView) findViewById(R.id.signup_reglog);
        register = (Button) findViewById(R.id.signup_regNow);


        //show/hide password
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordNotVisible == 1) {
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }


                Password.setSelection(Password.length());
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordNotVisible == 1) {
                    ReenterPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {

                    ReenterPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }

                ReenterPass.setSelection(ReenterPass.length());

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = User_name.getText().toString();
                String userEmail = Email.getText().toString();
                String userMobile = Mobile.getText().toString();
                String userPassword = Password.getText().toString();
                String userRePassword = ReenterPass.getText().toString();
                // Pattern match for email id
                Pattern p = Pattern.compile(com.example.myapplication.Common.Constants.regEx);
                Matcher m = p.matcher(userEmail); //used to check weather the give format by developer(in Constraints.regEx) is matching the user pattern or not


                //    Pattern match for Mobile No
                Pattern mobi = Pattern.compile(Constants.mobregEx);
                Matcher mob = mobi.matcher(userMobile); //used to check weather the give format by developer(in Constraints.regEx) is matching the user pattern or not

                if (userName.equalsIgnoreCase("") || userName.length() >= 3 || userEmail.equalsIgnoreCase("") || userEmail.length() >= 8 ||
                        userMobile.equalsIgnoreCase("") || userMobile.length() == 10 || userPassword.equalsIgnoreCase("") || userPassword.length() >= 5 || userRePassword.equalsIgnoreCase("") || userRePassword.length() >= 5)
                {
                    if (userName.equalsIgnoreCase("")) {
                        User_name.setError("Please Enter User Name ");
                    } else if (userEmail.equalsIgnoreCase("") || !m.find()) {
                        Email.setError("Please Enter Valid Email ");
                    }else if (userMobile.equalsIgnoreCase("") || !mob.find()) {
                        Mobile.setError("Pleas Enter Valid Mobile Number");
                    }  else if (userPassword.equalsIgnoreCase("")) {
                        Password.setError("Please Enter Valid Password ");
                    } else if (userRePassword.equalsIgnoreCase("")) {
                        ReenterPass.setError("Please Enter Re-enterPassword  ");
                    } else if (!userPassword.equals(userRePassword)) {    //this is to check weather password and reenterpassword is matching or not
                        ReenterPass.setError("your password are not matched....");
                    } else {
//                        if (Constants.isOnline(getApplicationContext())) {
////                            //String SIGNUP_URL = CommonUtils.baseurl + "api/user/sign_up";
                            register(userName, userMobile, userEmail, userPassword, userRePassword);
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


        login = (TextView) findViewById(R.id.signup_reglog);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup_RegActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });
    }

    private void register(final String getuserName, final String getuserMobile,
                          final String getuserEmail, final String getuserPassword, final String getReenterPass){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.0.147/mobile-api/index.php/api/signup",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);

                            confirmOtp();

                            Toast.makeText(getApplicationContext(), obj.getString("message"),Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), SignInActivity.class));

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
                params.put("username",getuserName);
                params.put("mobile", getuserMobile);
                params.put("email",getuserEmail);
                params.put("password", getuserPassword);
                params.put("cpassword",getReenterPass);
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
                final ProgressDialog loading = ProgressDialog.show(Signup_RegActivity.this, "Authenticating", "Please wait while we check the entered code", false,false);

                //Getting the user entered otp from edittext
                final String fullname=User_name.getText().toString().trim();
                final String mobile=Mobile.getText().toString().trim();
                final String email=Email.getText().toString().trim();
                final String pass=Password.getText().toString().trim();
                final String otp = confirmotp.getText().toString().trim();
                //Creating an string request
                StringRequest stringRequest = new StringRequest(Request.Method.POST,sign_url ,
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
                                        startActivity(new Intent(Signup_RegActivity.this, SignInActivity.class));
                                    }else{
                                        //Displaying a toast if the otp entered is wrong
                                        Toast.makeText(Signup_RegActivity.this,"Wrong OTP Please Try Again",Toast.LENGTH_LONG).show();
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
                                Toast.makeText(Signup_RegActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        //Adding the parameters otp and username
                        params.put("fullname",fullname);
                        params.put("mobile",mobile );
                        params.put("email",email);
                        params.put("password",pass);
                        params.put("otp",otp);
                        return params;
                    }
                };

                //Adding the request to the queue
                requestQueue.add(stringRequest);
            }
        });

    }

//    @Override
//    public void onBackPressed() {
//        Intent i=new Intent(Signup_RegActivity.this,SignInActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//        startActivity(i);
//        finish();
//    }

//        URL url = new URL("http://192.168.0.147/mobile-api/index.php/api/signup");
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("POST");
        //if everything is fine
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://192.168.0.147/mobile-api/index.php/api/signup",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        //   progressBar.setVisibility(View.GONE);
//
////                        Log.e("Response"+response);
//                        Log.e("hello", "Response from url: " + response);
//
//                        try {
//                            //converting response to json object
//                            JSONObject obj = new JSONObject(response);
//                            int error = obj.getInt("error");
//                            //if no error in response
//                            if(error==1) {
//
//                                    Toast.makeText(getApplicationContext(), "Successfully Signed In", Toast.LENGTH_SHORT).show();
////                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//
//
////                            startActivity(new Intent(Signup_RegActivity.this, SignInActivity.class));
//                                Intent i = new Intent(Signup_RegActivity.this,SignInActivity.class);
//                                startActivity(i);
////                            finish();
//                            } else {
//                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("username",getuserName);
//                params.put("mobile", getuserMobile);
//                params.put("email",getuserEmail);
//                params.put("password", getuserPassword);
//                params.put("cpassword",getReenterPass);
//                return params;
//            }
//        };
//
////        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
//        RequestQueue rq= Volley.newRequestQueue(this);
//        rq.add(stringRequest);
//    }

}
