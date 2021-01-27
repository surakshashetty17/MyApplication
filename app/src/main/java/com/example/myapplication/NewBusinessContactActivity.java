package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewBusinessContactActivity extends AppCompatActivity {

    Button button_savecontact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business_contact);

        button_savecontact = (Button)findViewById(R.id.button_contactperson);
        button_savecontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewBusinessContactActivity.this,NewBusinessBankActivity.class);
                startActivity(i);
            }
        });
    }
}
