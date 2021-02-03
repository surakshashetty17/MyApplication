package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class HelpDeskActivity extends AppCompatActivity {

    LinearLayout linear_email,linear_call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        linear_email = (LinearLayout) findViewById(R.id.help_email);
        linear_call = (LinearLayout) findViewById(R.id.help_call);

        linear_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String to = "jsunilkumarr96@gmail.com";
//                String sharebody = ("Rating and Review: "+String.valueOf(ratingbar.getRating())+" and "+(editreview.getText().toString()));
                String sharesubject = "Your Subject here";
//                intent.putExtra(Intent.EXTRA_TEXT,sharebody);
                intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to}); //email should be in string form

                startActivity(Intent.createChooser(intent,"shareusing"));
            }
        });

        linear_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:9987546859"));
                startActivity(callIntent);
            }
        });
    }
}
