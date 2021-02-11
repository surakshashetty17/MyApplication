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

public class SignupActivity extends AppCompatActivity {

    EditText Shop_name,User_name,Email,Mobile,Pincode,Password, ReenterPass;
    ImageButton img1, img2;  //  password eye
    private int passwordNotVisible = 1;
    Button register;
    TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Shop_name = (EditText) findViewById(R.id.reg_shopname);
        User_name = (EditText) findViewById(R.id.reg_username);
        Email = (EditText) findViewById(R.id.reg_email);
        Mobile = (EditText) findViewById(R.id.reg_mobilenumber);
        Pincode = (EditText) findViewById(R.id.reg_pincode);
        Password = (EditText) findViewById(R.id.reg_pass);
        ReenterPass = (EditText) findViewById(R.id.reg_repass);
        img1 = (ImageButton) findViewById(R.id.imageButton1);
        img2 = (ImageButton) findViewById(R.id.imageButton2);
        login = (TextView) findViewById(R.id.reglog);
        register = (Button) findViewById(R.id.regNow);

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

        register = (Button)findViewById(R.id.regNow);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String shopName = Shop_name.getText().toString();
                String userName = User_name.getText().toString();
                String userEmail = Email.getText().toString();
                String userMobile = Mobile.getText().toString();
                String userpincode = Pincode.getText().toString();
                String userPassword = Password.getText().toString();
                String userRePassword = ReenterPass.getText().toString();
                // Pattern match for email id
                Pattern p = Pattern.compile(Constants.regEx);
                Matcher m = p.matcher(userEmail); //used to check weather the give format by developer(in Constraints.regEx) is matching the user pattern or not


                //    Pattern match for Mobile No
                Pattern mobi = Pattern.compile(Constants.mobregEx);
                Matcher mob = mobi.matcher(userMobile); //used to check weather the give format by developer(in Constraints.regEx) is matching the user pattern or not

                if (userName.equalsIgnoreCase("") || userName.length() >= 3 || userEmail.equalsIgnoreCase("") || userEmail.length() >= 8 ||
                        userMobile.equalsIgnoreCase("") || userMobile.length() == 10 || userPassword.equalsIgnoreCase("") || userPassword.length() >= 5 || userRePassword.equalsIgnoreCase("") || userRePassword.length() >= 5)
                {
                    if (shopName.equalsIgnoreCase("")) {
                        Shop_name.setError("Please Enter Shop Name ");
                    } else if (userName.equalsIgnoreCase("")) {
                        User_name.setError("Please Enter User Name ");
                    } else if (userEmail.equalsIgnoreCase("") || !m.find()) {
                        Email.setError("Please Enter Valid Email ");
                    }else if (userMobile.equalsIgnoreCase("") || !mob.find()) {
                        Mobile.setError("Pleas Enter Valid Mobile Number");
                    } else if (userpincode.equalsIgnoreCase("")) {
                        Pincode.setError("Please Enter Pincode ");
                    } else if (userPassword.equalsIgnoreCase("")) {
                        Password.setError("Please Enter Valid Password ");
                    } else if (userRePassword.equalsIgnoreCase("")) {
                        ReenterPass.setError("Please Enter Re-enterPassword  ");
                    } else if (!userPassword.equals(userRePassword)) {    //this is to check weather password and reenterpassword is matching or not
                        Password.setError("your password are not matched....");
                    } else {
//                        if (Constants.isOnline(getApplicationContext())) {
//                            //String SIGNUP_URL = CommonUtils.baseurl + "api/user/sign_up";
//                            register(userName, userMobile, userEmail, userPassword, userRePassword);
//                        } else {
//                            Toast.makeText(getApplicationContext(),"connection failed",Toast.LENGTH_LONG).show();
//                        }
                        Intent i = new Intent(SignupActivity.this,SignInActivity.class);
                        startActivity(i);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),"entries are wrong",Toast.LENGTH_LONG).show();
                }
            }
        });


        login = (TextView) findViewById(R.id.reglog);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignupActivity.this,SignInActivity.class);
                startActivity(i);
            }
        });

    }

}
