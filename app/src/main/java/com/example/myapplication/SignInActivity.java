package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    EditText email,password;
    TextView forget_password,join_now;
    Button signIn;
    ImageButton img3;
    private int passwordNotVisible=1;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);

        forget_password = (TextView) findViewById(R.id.forgot_password);
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this,ChangePasswordActivity.class);
                startActivity(i);
            }
        });
        join_now = (TextView) findViewById(R.id.join_now);
        join_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this,ChangePasswordActivity.class);
                startActivity(i);
            }
        });

        signIn = (Button) findViewById(R.id.login_123);
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(SignInActivity.this,DashBoardActivity.class);
//                startActivity(i);
//            }
//        });

        img3=(ImageButton)findViewById(R.id.imageButton1);
        //hide/show password
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordNotVisible == 1) {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }

                password.setSelection(password.length());
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call the webservice for login after checking the id and password
                String user = email.getText().toString();
                String pwd = password.getText().toString();
                Log.d("email","email"+user);
                Log.d("password","password"+pwd);


//                if ((null == user || user.equalsIgnoreCase("")) ||
//                        (null == pwd || pwd.equalsIgnoreCase(""))) {
//                    Toast.makeText(SignInActivity.this, "ID/Password cannot be empty. Please check", Toast.LENGTH_LONG).show();
//                } else {
                    // call the webservice for login
                    String url = "http://13.232.113.112/nanocart_api/index.php/Api/nc_business_signin";
                    String apikey = "e7ae715d21b12b5421420dbb7636a3a37ff40dc73df25691a57fb38cf9fa25a2";
                Log.d("login","login"+url);

                login(url, user, pwd, apikey);
//
//                }
            }
        });
    }
    private void login(String loginUrl, final String login_userMob, final String login_userPass,final String login_apikey){

        Log.d("inside","inside"+login_userMob);
        Log.d("apikey","apikey"+login_apikey);

        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST,loginUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //   progressBar.setVisibility(View.GONE);

                        try {
                            //converting response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if(obj.getInt("status")==1) {
                                Toast.makeText(getApplicationContext(), "Successfully Logged In", Toast.LENGTH_SHORT).show();
                                Log.d("LogeIn","hello"+obj);

                                //getting the user from the response
//                                JSONObject userJson = obj.getJSONObject("user_details");
//                                //System.out.println(userJson);
//                                LoginData loginData=new LoginData(
//                                        userJson.getInt("user_id"),
//                                        userJson.getString("full_name"),
//                                        userJson.getString("mobile"),
//                                        userJson.getString("email"));
//                                //creating a new user object
////
//
//                                //storing the user in shared preferences
//                                System.out.println(loginData);
//                                SharedPrefManager.getInstance(getApplicationContext()).userLogin(loginData);
                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), DashBoardActivity.class));
                            } else {
                                Log.d("LogeOut","hey"+obj);
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",login_userMob);
                params.put("password", login_userPass);
                params.put("signin_api_key", login_apikey);

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
