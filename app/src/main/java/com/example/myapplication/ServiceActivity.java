package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class ServiceActivity extends AppCompatActivity {

    Switch switch_service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        switch_service = (Switch) findViewById(R.id.switch_service);
        switch_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ServiceActivity.this,ProductActivity.class);
                startActivity(i);
            }
        });
    }
}
