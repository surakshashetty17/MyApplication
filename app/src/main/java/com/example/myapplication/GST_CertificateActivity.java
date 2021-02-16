package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.spark.submitbutton.SubmitButton;

public class GST_CertificateActivity extends AppCompatActivity {

    Button button_upload;
    SubmitButton submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gst__certificate);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        submitButton = (SubmitButton) findViewById(R.id.gst_certificateupload);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do something after 2s = 2000 miliseconds
                        Intent i = new Intent(GST_CertificateActivity.this,UploadGstActivity.class);
                        startActivity(i);
                    }
                }, 5000); //Time in milisecond
//                Intent i = new Intent(GST_CertificateActivity.this,UploadGstActivity.class);
//                startActivity(i);
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left_x, R.anim.slide_out_right_x);
    }
}
