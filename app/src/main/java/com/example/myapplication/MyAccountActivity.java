package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MyAccountActivity extends AppCompatActivity {

    LinearLayout linear_personal,linear_business,linear_banner,linear_currentlocation,linear_bank,linear_franchise,linear_password,linear_pincode,linear_profile,linear_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        linear_personal = (LinearLayout) findViewById(R.id.linear_personaldetails1);
        linear_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,PersonalDetailsActivity.class);
                startActivity(i);
            }
        });

        linear_business = (LinearLayout) findViewById(R.id.linear_businessdetails1);
        linear_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,BusinessDetailsActivity.class);
                startActivity(i);
            }
        });

        linear_banner = (LinearLayout) findViewById(R.id.linear_banner);
        linear_banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,SpinnerCheckboxActivity.class);
                startActivity(i);
            }
        });

        linear_currentlocation = (LinearLayout) findViewById(R.id.linear_updatelocation);
        linear_currentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,MapSearchActivity.class);
                startActivity(i);
            }
        });

        linear_bank = (LinearLayout) findViewById(R.id.linear_bankdetails);
        linear_bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,BankDetailsActivity.class);
                startActivity(i);
            }
        });

        linear_franchise = (LinearLayout) findViewById(R.id.linear_franchisepremium);
        linear_franchise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,VendorPremiumActivity.class);
                startActivity(i);
            }
        });

        linear_password = (LinearLayout) findViewById(R.id.linear_changepassword);
        linear_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,ChangePasswordActivity.class);
                startActivity(i);
            }
        });

        linear_pincode = (LinearLayout) findViewById(R.id.linear_pincodeenabled);
        linear_pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,PincodeEnableActivity.class);
                startActivity(i);
            }
        });

        linear_profile = (LinearLayout) findViewById(R.id.linear_profile);
        linear_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,ProfilePicActivity.class);
                startActivity(i);
            }
        });

        linear_order = (LinearLayout) findViewById(R.id.linear_orders);
        linear_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MyAccountActivity.this,OrdersActivity.class);
                startActivity(i);
            }
        });

    }
}
