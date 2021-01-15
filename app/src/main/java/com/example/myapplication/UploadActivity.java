package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class UploadActivity extends AppCompatActivity {

    LinearLayout linear_gstcertificate,linear_idproof;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        linear_gstcertificate = (LinearLayout) findViewById(R.id.linear_gstcertificate);
        linear_gstcertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UploadActivity.this,GST_CertificateActivity.class);
                startActivity(i);
            }
        });
        linear_idproof = (LinearLayout) findViewById(R.id.linear_idproof);
        linear_idproof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(UploadActivity.this,OwnerIdActivity.class);
                startActivity(i);
            }
        });
    }
}
