package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FranchiseBusinessActivity extends AppCompatActivity {

    Button button_pincode,button_newbusiness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_franchise_business);

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
    }
}
