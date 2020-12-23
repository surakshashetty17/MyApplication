package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiffrenceLayoutActivity extends AppCompatActivity {

    Button diffconstraintlayout,difflinearlayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diffrence_layout);

        diffconstraintlayout = (Button)findViewById(R.id.diffconstraintlayout);
        diffconstraintlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiffrenceLayoutActivity.this,ConstraintLayoutActivity.class);
                startActivity(i);
            }
        });


        difflinearlayout = (Button)findViewById(R.id.difflinearlayout);
        difflinearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DiffrenceLayoutActivity.this,LinearLayoutActivity.class);
                startActivity(i);
            }
        });
    }
}
