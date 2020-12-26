package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class SignupActivity extends AppCompatActivity {

    EditText Password, ReenterPass;
    ImageButton img1, img2;  //  password eye
    private int passwordNotVisible = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Password = (EditText) findViewById(R.id.regPass);
        ReenterPass = (EditText) findViewById(R.id.regRepass);
        img1 = (ImageButton) findViewById(R.id.imageButton1);
        img2 = (ImageButton) findViewById(R.id.imageButton2);

        //show/hide password
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordNotVisible == 1) {
                    Password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {
                    Password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }


                Password.setSelection(Password.length());
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordNotVisible == 1) {
                    ReenterPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordNotVisible = 0;
                } else {

                    ReenterPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordNotVisible = 1;
                }

                ReenterPass.setSelection(ReenterPass.length());

            }
        });


    }

}
