package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashBoardActivity extends AppCompatActivity {

    CardView cardView_dash,cardView_myaccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        cardView_dash = (CardView) findViewById(R.id.cardview_dash);
        cardView_dash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardActivity.this,DashLandingActivity.class);
                startActivity(i);
            }
        });
        cardView_myaccount = (CardView) findViewById(R.id.cardview_myaccount);
        cardView_myaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashBoardActivity.this,MyAccountActivity.class);
                startActivity(i);
            }
        });
    }
}
