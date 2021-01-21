package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class GalleryImagesActivity extends AppCompatActivity {

    ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10;
    TextView text_gallery;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
        private int clickImage;
        int count = 0;
        boolean val1 = true,val2 = true,val3 = true,val4 = true,val5 = true,val6 = true,val7 = true,val8 = true,val9 = true,val10 = true;

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

        imageView1 = findViewById(R.id.img1);
        imageView2 = findViewById(R.id.img2);
        imageView3 = findViewById(R.id.img3);
        imageView4 = findViewById(R.id.img4);
        imageView5 = findViewById(R.id.img5);
        imageView6 = findViewById(R.id.img6);
        imageView7 = findViewById(R.id.img7);
        imageView8 = findViewById(R.id.img8);
        imageView9 = findViewById(R.id.img9);
        imageView10 = findViewById(R.id.img10);


        text_gallery = (TextView)findViewById(R.id.text_gallery);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=1;
                showPictureDialog();
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=2;
                showPictureDialog();
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=3;
                showPictureDialog();
            }
        });
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=4;
                showPictureDialog();
            }
        });

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=5;
                showPictureDialog();
            }
        });
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=6;
                showPictureDialog();
            }
        });
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=7;
                showPictureDialog();
            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=8;
                showPictureDialog();
            }
        });
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=9;
                showPictureDialog();
            }
        });
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=10;
                showPictureDialog();
            }
        });
    }

    private void showPictureDialog() {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera"};
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent();
//        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        switch (clickImage) {
            case 1:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView1.setImageBitmap(bitmap);
                            if(val1){
                                count++;
                                val1 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView1.setImageBitmap(thumbnail);
                    if(val1){
                        count++;
                        val1 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView2.setImageBitmap(bitmap);
                            if(val2){
                                count++;
                                val2 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView2.setImageBitmap(thumbnail);
                    if(val2){
                        count++;
                        val2 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 3:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView3.setImageBitmap(bitmap);
                            if(val3){
                                count++;
                                val3 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView3.setImageBitmap(thumbnail);
                    if(val3){
                        count++;
                        val3 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 4:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView4.setImageBitmap(bitmap);
                            if(val4){
                                count++;
                                val4 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView4.setImageBitmap(thumbnail);
                    if(val4){
                        count++;
                        val4 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 5:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView5.setImageBitmap(bitmap);
                            if(val5){
                                count++;
                                val5 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView5.setImageBitmap(thumbnail);
                    if(val5){
                        count++;
                        val5 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 6:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView6.setImageBitmap(bitmap);
                            if(val6){
                                count++;
                                val6 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView6.setImageBitmap(thumbnail);
                    if(val6){
                        count++;
                        val6 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 7:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView7.setImageBitmap(bitmap);
                            if(val7){
                                count++;
                                val7 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView7.setImageBitmap(thumbnail);
                    if(val7){
                        count++;
                        val7 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 8:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView8.setImageBitmap(bitmap);
                            if(val8){
                                count++;
                                val8 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView8.setImageBitmap(thumbnail);
                    if(val8){
                        count++;
                        val8 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 9:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView9.setImageBitmap(bitmap);
                            if(val9){
                                count++;
                                val9 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView9.setImageBitmap(thumbnail);
                    if(val9){
                        count++;
                        val9 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
            case 10:
                if (requestCode == GALLERY) {
                    if (data != null) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                            String path = saveImage(bitmap);
                            Toast.makeText(GalleryImagesActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                            imageView10.setImageBitmap(bitmap);
                            if(val10){
                                count++;
                                val10 = false;
                            }
                            text_gallery.setText(count+" image uploaded");

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(GalleryImagesActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    imageView10.setImageBitmap(thumbnail);
                    if(val10){
                        count++;
                        val10 = false;
                    }
                    text_gallery.setText(count+" image uploaded");
                    saveImage(thumbnail);
                    Toast.makeText(GalleryImagesActivity.this, "Image Saved!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }
}
