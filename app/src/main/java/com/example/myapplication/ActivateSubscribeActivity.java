 package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

 public class ActivateSubscribeActivity extends AppCompatActivity {

     TextView amount;
     int cat=2,amt=2000,res;
     LottieAnimationView lt_loading_view,lt_loading_view1;
     Button button_sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activate_subscribe);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        res=cat*amt;
        amount = (TextView) findViewById(R.id.amount);
        amount.setText(String.valueOf(res));

        lt_loading_view = (LottieAnimationView) findViewById(R.id.animationView);
        lt_loading_view1 = (LottieAnimationView) findViewById(R.id.animationView1);


        button_sub = (Button)  findViewById(R.id.button_subscribe);
        button_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lt_loading_view.playAnimation();
                lt_loading_view1.playAnimation();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // do something after 2s = 2000 miliseconds
                        lt_loading_view.pauseAnimation();
                        lt_loading_view1.pauseAnimation();

                    }
                }, 3000); //Time in milisecond
            }
        });


    }
}
