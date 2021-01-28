package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FranchiseBusinessActivity extends AppCompatActivity {

    Button button_pincode,button_newbusiness,button_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franchise_business);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        button_pincode = (Button) findViewById(R.id.managepincode);
        button_pincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FranchiseBusinessActivity.this,ManagePincodeActivity.class);
                startActivity(i);
            }
        });

        button_newbusiness = (Button) findViewById(R.id.button_newbusiness);
        button_newbusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FranchiseBusinessActivity.this,NewBusinessActivity.class);
                startActivity(i);
            }
        });

        button_edit = (Button) findViewById(R.id.button_edit_newbusiness);
        button_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FranchiseBusinessActivity.this,NewBusinessActivity.class);
                startActivity(i);
            }
        });
    }
}
