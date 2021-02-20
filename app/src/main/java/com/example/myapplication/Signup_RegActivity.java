package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Common.Constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup_RegActivity extends AppCompatActivity {

    EditText User_name,Email,Mobile,Password,ReenterPass;
    ImageButton img1, img2;  //  password eye
    private int passwordNotVisible = 1;
    Button register;
    TextView login;

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
////                            register(userName, userMobile, userEmail, userPassword, userRePassword);
////                        } else {
//                            Toast.makeText(getApplicationContext(),"connection failed",Toast.LENGTH_LONG).show();
//                        }
                        Intent i = new Intent(Signup_RegActivity.this,SignInActivity.class);
                        startActivity(i);
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
}
