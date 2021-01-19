package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryImagesActivity extends AppCompatActivity {


    TextView button_uploadphotos;
    ImageView imageViewphoto1,imageViewphoto2;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    private static final int SELECT_FILE1 = 1;
    private static final int SELECT_FILE2 = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_images);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imageViewphoto1 = (ImageView)findViewById(R.id.img1);
        imageViewphoto2 = (ImageView)findViewById(R.id.img2);
        button_uploadphotos = (TextView) findViewById(R.id.text_image);
        button_uploadphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery(SELECT_FILE1);
                openGallery(SELECT_FILE2);
            }
        });

    }

    public void openGallery(int req_code){

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, req_code);
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
        }
    }

}
