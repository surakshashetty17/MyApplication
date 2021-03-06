package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;
import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.spark.submitbutton.SubmitButton;

import java.util.Calendar;
import java.util.TimeZone;

public class DowloadActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


    EditText editurl,_editText;
    Button buttonurl,buttonpdf,button_switch;
    private static final int PERMISIO_STORAGE_CODE = 1000;
    private int _day;
    private int _month;
    private int _birthYear;
    Switch simple_switch;
    ElegantNumberButton button_ele;
    SubmitButton submitButton;
    LottieAnimationView lt_loading_view;

    private AnimationDrawable animationDrawable;
    private ImageView mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dowload);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        editurl = findViewById(R.id.editurl);
        buttonurl = findViewById(R.id.downloadurl);
        buttonpdf = findViewById(R.id.downloadpdf);

        buttonurl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission,PERMISIO_STORAGE_CODE);
                    }
                    else
                    {
                        startDownloading();
                    }
                }
                else
                {
                    startDownloading();
                }
            }
        });

        buttonpdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                    {
                        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission,PERMISIO_STORAGE_CODE);
                    }
                    else
                    {
                        startDownloadingpdf();
                    }
                }
                else
                {
                    startDownloadingpdf();
                }
            }
        });

        _editText=(EditText)findViewById(R.id.date);
        _editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdate();

            }
        });



        //code for animation progress baar
        mProgressBar = (ImageView) findViewById(R.id.ivProgress);
        mProgressBar.setBackgroundResource(R.drawable.loading_web_animation);
        animationDrawable =(AnimationDrawable)mProgressBar.getBackground();
        mProgressBar.setVisibility(View.VISIBLE);
        animationDrawable.start();
//        mProgressBar.setVisibility(View.GONE);
//        animationDrawable.stop();


        simple_switch = (Switch) findViewById(R.id.simple_switch);
        button_switch = (Button) findViewById(R.id.button_switch);
        button_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String statusSwitch1;
                if (simple_switch.isChecked()) {
                    statusSwitch1 = simple_switch.getTextOn().toString();
                }
                else {
                    statusSwitch1 = simple_switch.getTextOff().toString();
                }

                String num;
                num = button_ele.getNumber();
                Log.d("Switch","Swiitch is: "+statusSwitch1);
                Log.d("Elegent number button","Selected is: "+num);
                Toast.makeText(getApplicationContext(), "Switch1 :" + statusSwitch1, Toast.LENGTH_LONG).show(); // display the current state for switch's
            }
        });

        button_ele = (ElegantNumberButton) findViewById(R.id.button_elegantnumber);
//        button_ele.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String num = button_ele.getNumber();
//                Log.d("Elegent number button","Selected: "+num);
//            }
//        });

        submitButton = (SubmitButton) findViewById(R.id.button_animation);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Animation", "onClick: Submit");
            }
        });


        lt_loading_view = (LottieAnimationView) findViewById(R.id.button_animation1);

        lt_loading_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lt_loading_view.playAnimation();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do something after 2s = 2000 miliseconds
                        lt_loading_view.pauseAnimation();
                        Intent i = new Intent(DowloadActivity.this,ActivateSubscribeActivity.class);
                        startActivity(i);

                    }
                }, 1000); //Time in milisecond
            }
        });

    }

    //to dowload the pdf give in the code
    private void startDownloadingpdf()
    {
        String url = "http://www.africau.edu/images/default/sample.pdf";
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("dummy pdf");
        request.setDescription("dummy pdf..");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Dummy pdf");
        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    //to dowload the url give in outside the code
    private void startDownloading()
    {
        String url = editurl.getText().toString().trim();
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("dummy file");
        request.setDescription("dummy file..");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"Dummy");
        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case PERMISIO_STORAGE_CODE:{
                if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    startDownloading();
                    startDownloadingpdf();
                }
                else
                {
                    Toast.makeText(this,"Permission Denied....",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth)
    {
        _birthYear = year;
        _month = month;
        _day = dayOfMonth;
        updateDisplay();
    }
    public void getdate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(this, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }
    private void updateDisplay() {

        _editText.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" "));
    }
}
