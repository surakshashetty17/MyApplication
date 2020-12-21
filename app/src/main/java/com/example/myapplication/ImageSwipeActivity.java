package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

public class ImageSwipeActivity extends AppCompatActivity {

    ViewPager viewPager;
    SwipeAdapter adapter;
    private LinearLayout dotLayout;
    Button buttonzoom;
    VideoView videoView;
    MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_swipe);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

            videoView = (VideoView)findViewById(R.id.videoView);
            Uri path = Uri.parse( "android.resource://"+getPackageName()+"/"+ +R.raw.nano_cart);
            videoView.setVideoURI(path);
         mediaController = new MediaController(this);
        videoView.start();


        buttonzoom = findViewById(R.id.buttonzoom);
        buttonzoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ImageSwipeActivity.this,ZoomImageActivity.class);
                startActivity(intent);
            }
        });

        viewPager = findViewById(R.id.viewpager);
        dotLayout = findViewById(R.id.dotscontainer);
        adapter = new SwipeAdapter(this);
        viewPager.setAdapter(adapter);

        dots(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                dots(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    private void dots(int currentSlidePosition) {
        if (dotLayout.getChildCount() > 0)
            dotLayout.removeAllViews();

        ImageView dotsImage[] = new ImageView[5];
        for (int i = 0; i < 5; i++) {
            dotsImage[i] = new ImageView(this);
            if (i == currentSlidePosition)

                dotsImage[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots));
            else
                dotsImage[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.inactivedots));

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(4, 0, 4, 0);
            dotLayout.addView(dotsImage[i], layoutParams);

        }
    }

    public void videoshow(View view)
    {
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
        mediaController.show();
    }

}
