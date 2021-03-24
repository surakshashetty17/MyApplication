package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UploadImageActivity extends AppCompatActivity {


    ImageView imageView;
    private static final String IMAGE_DIRECTORY = "/demonuts";
    private int GALLERY = 1, CAMERA = 2;
    private int clickImage;
    private RequestQueue requestQueue;
//    Bitmap bitmap,thumbnail;
    Button upload;
//    String path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        imageView = findViewById(R.id.upload_image);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickImage=1;
                showPictureDialog();
            }
        });
//        upload = (Button)findViewById(R.id.buttonupload_image100);
//        uploadimage(path);

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
//        Intent galleryIntent = new Intent();
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

//        galleryIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);  //for selecting multiple images from gallery
//        galleryIntent.setType("image/*");
//        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
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
                if (requestCode == GALLERY) {
                        Uri contentURI = data.getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
//                            bitmap = BitmapFactory.decodeFile("/path/to/image.jpg");
                            String path = saveImage(bitmap);
                            Toast.makeText(UploadImageActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();

                            imageView.setImageBitmap(bitmap);
//                            System.out.println(path);
                            uploadimage(path);

//                            try {
//                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), contentURI);
//                                Bitmap lastBitmap = null;
//                                lastBitmap = bitmap;
//                                //encoding image to string
//                                String image = getStringImage(lastBitmap);
//                                Log.d("image",image);
//                                //passing the image to volley
//                                SendImage(image);
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(UploadImageActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                    }

                } else if (requestCode == CAMERA) {
                    Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
                    String path1 = saveImage(thumbnail);
                    imageView.setImageBitmap(thumbnail);
                    uploadimage(path1);
                    Toast.makeText(UploadImageActivity.this, "Image uploaded successfully!", Toast.LENGTH_SHORT).show();
                }
    }

//    public String getStringImage(Bitmap bmp) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        return encodedImage;
//
//    }

    public String saveImage(Bitmap myBitmap) {

        Log.d("","hello000000000000000999999999999"+myBitmap);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.instaimage);
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Log.d("/////////////","............,,,,,,,,,"+imageString);
//                System.out.println(imageString);
        return imageString;
//        textView.setText(imageString);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        Log.d("/////////////","............,,,,,,,,,"+myBitmap);
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        byte[] imageBytes = baos.toByteArray();
//        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
//        Log.d("/////////////","............,,,,,,,,,"+encodedImage);
////        SendImage(encodedImage);
//        return encodedImage;
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
//
//
//        File wallpaperDirectory = new File(
//                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
//        // have the object build the directory structure, if needed.
//        if (!wallpaperDirectory.exists()) {
//            wallpaperDirectory.mkdirs();
//        }
//
//        try {
//            File f = new File(wallpaperDirectory, Calendar.getInstance()
//                    .getTimeInMillis() + ".jpg");
//            f.createNewFile();
//            FileOutputStream fo = new FileOutputStream(f);
//            fo.write(bytes.toByteArray());
//            MediaScannerConnection.scanFile(this,
//                    new String[]{f.getPath()},
//                    new String[]{"image/jpeg"}, null);
//            fo.close();
//
//            Log.d("TAG", "File Saved::--->" + f.getAbsolutePath());
//            SendImage(f.getAbsolutePath());
//
//            return f.getAbsolutePath();
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
//        return "";
    }

    private void uploadimage(final String getimage)
    {
        Log.d("....////////..........",",,,,''''''''/'''''',,,,,,,,,,,");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://13.232.113.112/nanocart_api/index.php/Account/upload_image",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("....////////..........",",,,,''''''''/'''''',,,,,,,,,,,");
                        try {

                            JSONObject obj = new JSONObject(response);
                            if (obj.getInt("status") == 1)
                            {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params  = new HashMap<>();
                params.put("image_byte_array",getimage);
                params.put("mobile", "8550838918");
                params.put("signed_user_api_key", "25e4f5087d509d7b02487e181139bbdb1e2d63ecac57bb1a847a47469842a891");

                return params;
            }
        };

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
