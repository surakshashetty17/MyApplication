package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MyAccountLinearActivity extends AppCompatActivity {

    LinearLayout linear_personal,linear_business,linear_banner,linear_currentlocation,linear_bank,linear_franchise,linear_password,linear_pincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_linear);

        linear_personal = (LinearLayout) findViewById(R.id.linear1_personal);
        linear_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountLinearActivity.this,PersonalDetailsActivity.class);
                startActivity(i);
            }
        });

        linear_business = (LinearLayout) findViewById(R.id.linear1_business);
        linear_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountLinearActivity.this,BusinessDetailsActivity.class);
                startActivity(i);
            }
        });

        linear_banner = (LinearLayout) findViewById(R.id.linear1_pic);
        linear_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountLinearActivity.this,SpinnerCheckboxActivity.class);
                startActivity(i);
            }
        });

        linear_password = (LinearLayout) findViewById(R.id.linear1_password);
        linear_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountLinearActivity.this,ChangePasswordActivity.class);
                startActivity(i);
            }
        });

        linear_currentlocation = (LinearLayout) findViewById(R.id.linear1_location);
        linear_currentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountLinearActivity.this,MapSearchActivity.class);
                startActivity(i);
            }
        });

        linear_pincode = (LinearLayout) findViewById(R.id.linear1_enable);
        linear_pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountLinearActivity.this,PincodeEnableActivity.class);
                startActivity(i);
            }
        });
    }
}
