package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SharingActivity extends AppCompatActivity {

    RatingBar ratingbar;
    Button buttonrating,buttonalterdialog;
    TextView textrating;
    EditText editreview;
    ImageView imageshare;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ratingbar = findViewById(R.id.ratingBar);
        editreview = findViewById(R.id.editreview);
        buttonrating = findViewById(R.id.ratingbutton);
        textrating = findViewById(R.id.textrating);
        imageshare = findViewById(R.id.share);
        buttonrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String review = String.valueOf(editreview);
                String review = editreview.getText().toString();
                String rating=String.valueOf(ratingbar.getRating());
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
                textrating.setText("Rating: "+rating+"\n"+"Review: "+review);
            }
        });

        //code for share-image in Linear Layout
        imageshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String to = "jsunilkumarr96@gmail.com";
                String sharebody = ("Rating and Review: "+String.valueOf(ratingbar.getRating())+" and "+(editreview.getText().toString()));
                String sharesubject = "Your Subject here";

                intent.putExtra(Intent.EXTRA_TEXT,sharebody);
                intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);
//                intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to}); //email should be in string form

                startActivity(Intent.createChooser(intent,"shareusing"));
            }
        });

        //dialog box code
        buttonalterdialog = (Button) findViewById(R.id.alterdialogbutton);
        builder = new AlertDialog.Builder(this);
        buttonalterdialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Uncomment the below code to Set the message and title from the strings.xml file
//                builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                //Setting message manually and performing action on button click
                builder.setMessage("Do you want to close this application ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                Toast.makeText(getApplicationContext(),"you choose yes",
                                        Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //  Action for 'NO' Button
                                dialog.cancel();
                                Toast.makeText(getApplicationContext(),"you choose no",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("AlertDialogExample");
                alert.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //code for share-image in toolbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sharebody = ("Rating and Review: "+String.valueOf(ratingbar.getRating())+" and "+(editreview.getText().toString()));
                String sharesubject = "Your Subject here";

                intent.putExtra(Intent.EXTRA_TEXT,sharebody);
                intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);

                startActivity(Intent.createChooser(intent,"shareusing"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
