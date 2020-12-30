package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {


    Button button,button1,buttonlocation,buttonpagenation,buttonshare,buttonimage,buttoncart,buttonfiltersearch,buttonchart,buttonpiechart,buttondiffrece,buttonnanocart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,FullAddressActivity.class);
                startActivity(i);
            }
        });


        button = (Button)findViewById(R.id.buttonnext);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PincodeActivity.class);
                startActivity(i);
            }
        });

        buttonlocation = (Button)findViewById(R.id.buttonlocation);
        buttonlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MyLocationActivity.class);
                startActivity(i);
            }
        });
        buttonpagenation = (Button)findViewById(R.id.buttonpagenation);
        buttonpagenation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PagenationActivity.class);
                startActivity(i);
            }
        });
        buttonshare = (Button)findViewById(R.id.buttonshare);
        buttonshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SharingActivity.class);
                startActivity(i);
            }
        });
        buttonimage = (Button)findViewById(R.id.buttonimage);
        buttonimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ImageSwipeActivity.class);
                startActivity(i);
            }
        });
        buttoncart = (Button)findViewById(R.id.cart);
        buttoncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CartActivity.class);
                startActivity(i);
            }
        });
        buttonfiltersearch = (Button)findViewById(R.id.filetrsearch);
        buttonfiltersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SearchFilterActivity.class);
                startActivity(i);
            }
        });
        buttonchart = (Button)findViewById(R.id.barchart);
        buttonchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,BarChartActivity.class);
                startActivity(i);
            }
        });
        buttonpiechart = (Button)findViewById(R.id.piechart);
        buttonpiechart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,PieChartActivity.class);
                startActivity(i);
            }
        });
        buttondiffrece = (Button)findViewById(R.id.diffrence);
        buttondiffrece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DiffrenceLayoutActivity.class);
                startActivity(i);
            }
        });
        buttonnanocart = (Button)findViewById(R.id.nanocart);
        buttonnanocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(i);
            }
        });
    }


}
