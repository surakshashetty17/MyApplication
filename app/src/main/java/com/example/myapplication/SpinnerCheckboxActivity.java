package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SpinnerCheckboxActivity extends AppCompatActivity {

    TextView choose_file1,choose_file2,choose_file3,choose_file4;
//    private static final int PICK_IMAGE = 100;
    private static final int SELECT_FILE1 = 1;
    private static final int SELECT_FILE2 = 2;
    private static final int SELECT_FILE3 = 3;
    private static final int SELECT_FILE4 = 4;
    ImageView imageViewphoto1,imageViewphoto2,imageViewphoto3,imageViewphoto4;
    Uri imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_checkbox);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imageViewphoto1 = (ImageView)findViewById(R.id.imageViewphoto1);
        choose_file1 = (TextView) findViewById(R.id.choose_file1);
        choose_file1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(SELECT_FILE1);
            }
        });
        imageViewphoto2 = (ImageView)findViewById(R.id.imageViewphoto2);
        choose_file2 = (TextView) findViewById(R.id.choose_file2);
        choose_file2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(SELECT_FILE2);
            }
        });
        imageViewphoto3 = (ImageView)findViewById(R.id.imageViewphoto3);
        choose_file3 = (TextView) findViewById(R.id.choose_file3);
        choose_file3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(SELECT_FILE3);
            }
        });
        imageViewphoto4 = (ImageView)findViewById(R.id.imageViewphoto4);
        choose_file4 = (TextView) findViewById(R.id.choose_file4);
        choose_file4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(SELECT_FILE4);
            }
        });
    }

    public void openGallery(int req_code){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, req_code);
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        startActivityForResult(Intent.createChooser(intent,"Select file to upload "), req_code);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            if (requestCode == SELECT_FILE1)
            {
                imageUri = data.getData();
                imageViewphoto1.setImageURI(imageUri);
            }
            if (requestCode == SELECT_FILE2)
            {
                imageUri = data.getData();
                imageViewphoto2.setImageURI(imageUri);
            }
            if (requestCode == SELECT_FILE3)
            {
                imageUri = data.getData();
                imageViewphoto3.setImageURI(imageUri);
            }
            if (requestCode == SELECT_FILE4)
            {
                imageUri = data.getData();
                imageViewphoto4.setImageURI(imageUri);
            }
//            tv.setText("Selected File paths : " + selectedPath1 + "," + selectedPath2);
        }
    }
//    private void openGallery()
//    {
//        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(gallery, PICK_IMAGE);
//    }


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE)
//        {
//            imageUri = data.getData();
//            imageViewphoto1.setImageURI(imageUri);
//        }
//
//
//    }
}
