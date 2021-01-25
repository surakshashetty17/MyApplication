package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagePincodeActivity extends AppCompatActivity {

    Button button_editpincode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_pincode);

        button_editpincode = (Button) findViewById(R.id.button_editpincode);
        button_editpincode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagePincodeActivity.this,EditPincodeActivity.class);
                startActivity(i);
            }
        });

    }
}
