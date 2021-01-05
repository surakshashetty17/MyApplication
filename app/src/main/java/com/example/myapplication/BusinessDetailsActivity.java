package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class BusinessDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7;
    Button save_changes;
    String[] interest;
    ArrayList<String> interestList;
    String[] start_time = { "4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM", "12AM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM", "12AM"};
    String[] stop_time = { "4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM", "12AM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM", "12AM"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_details);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        checkBox1=(CheckBox)findViewById(R.id.checkBox1);
        checkBox2=(CheckBox)findViewById(R.id.checkBox2);
        checkBox3=(CheckBox)findViewById(R.id.checkBox3);
        checkBox4=(CheckBox)findViewById(R.id.checkBox4);
        checkBox5=(CheckBox)findViewById(R.id.checkBox5);
        checkBox6=(CheckBox)findViewById(R.id.checkBox6);
        checkBox7=(CheckBox)findViewById(R.id.checkBox7);

        Spinner spin1 = (Spinner) findViewById(R.id.spinner1);
        spin1.setOnItemSelectedListener(this);
        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,start_time);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin1.setAdapter(aa1);

        Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        spin2.setOnItemSelectedListener(this);
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,stop_time);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin2.setAdapter(aa2);

        save_changes = (Button)findViewById(R.id.save_changes);
        save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                interestList = new ArrayList<>();

                // for each checkbox checked, accumulate a category id in the list
                if (checkBox1.isChecked()) {
                    interestList.add("catagory_id=1");
                }

                if (checkBox2.isChecked()) {
                    interestList.add("catagory_id=2");
                }

                if (checkBox3.isChecked()) {
                    interestList.add("catagory_id=3");
                }

                if (checkBox4.isChecked()) {
                    interestList.add("catagory_id=4");

                }
                if (checkBox5.isChecked()) {
                    interestList.add("catagory_id=5");
                }

                if (checkBox6.isChecked()) {
                    interestList.add("catagory_id=6");
                }

                if (checkBox7.isChecked()) {
                    interestList.add("catagory_id=7");
                }
                // convert the list into an array
                interest = interestList.toArray(new String[interestList.size()]);

                System.out.println("Selected");
                for (String str : interest) {
                    System.out.println(str);
                }

                System.out.println("List Length"+interest.length);
                System.out.println("Array Size"+interestList.size());


                Intent i = new Intent(BusinessDetailsActivity.this,SpinnerCheckboxActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
