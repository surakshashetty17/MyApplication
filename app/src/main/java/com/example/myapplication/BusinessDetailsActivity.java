package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BusinessDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7;
    Spinner spin1,spin2;
    Button save_changes;
    String[] interest;
    ArrayList<String> interestList;

    String[] start_time = { "Start Time","4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM", "12AM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM", "12AM"};
    String[] stop_time = { "Stop Time","4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM", "12AM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM", "12AM"};
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

//        spin1 = (Spinner) findViewById(R.id.spinner1);
//        List<String> list1 = new ArrayList<String>();
//        list1.add("Start Time");
//        list1.add("8AM");
//        list1.add("9AM");
//        list1.add("10AM");
//        list1.add("11AM");
//        list1.add("12PM");
//        list1.add("1PM");
//        list1.add("2PM");
//        list1.add("3PM");
//        list1.add("4PM");
//        list1.add("5PM");
//        list1.add("6PM");
//        list1.add("7PM");
//        list1.add("8PM");
//        list1.add("9PM");
//        list1.add("10PM");
//
//        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list1);
//        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin1.setAdapter(dataAdapter1);
//        spin1.setOnItemSelectedListener(this);
//
//
//        spin2 = (Spinner) findViewById(R.id.spinner2);
//        List<String> list2 = new ArrayList<String>();
//        list2.add("Start Time");
//        list2.add("8AM");
//        list2.add("9AM");
//        list2.add("10AM");
//        list2.add("11AM");
//        list2.add("12PM");
//        list2.add("1PM");
//        list2.add("2PM");
//        list2.add("3PM");
//        list2.add("4PM");
//        list2.add("5PM");
//        list2.add("6PM");
//        list2.add("7PM");
//        list2.add("8PM");
//        list2.add("9PM");
//        list2.add("10PM");
//
//        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list2);
//        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spin2.setAdapter(dataAdapter2);
//        spin2.setOnItemSelectedListener(this);


        spin1 = (Spinner) findViewById(R.id.spinner1);
//        spin1.setOnItemSelectedListener(this);
        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,start_time);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin1.setAdapter(aa1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View view, int position, long id)
            {
                //change the spinner text color
                ((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        spin2 = (Spinner) findViewById(R.id.spinner2);
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
                    interestList.add("Saturday");
                }

                if (checkBox2.isChecked()) {
                    interestList.add("Sunday");
                }

                if (checkBox3.isChecked()) {
                    interestList.add("Monday");
                }

                if (checkBox4.isChecked()) {
                    interestList.add("Tuesday");

                }
                if (checkBox5.isChecked()) {
                    interestList.add("Wednesday");
                }

                if (checkBox6.isChecked()) {
                    interestList.add("Thursday");
                }

                if (checkBox7.isChecked()) {
                    interestList.add("Friday");
                }
                // convert the list into an array
                interest = interestList.toArray(new String[interestList.size()]);

                System.out.println("Selected");
                for (String str : interest) {
                    System.out.println(str);
                }

                System.out.println("List Length"+interest.length);
                System.out.println("Array Size"+interestList.size());

                String start_timetext = spin1.getSelectedItem().toString();
                System.out.println("Start Time  "+start_timetext);
                String stop_timetext = spin2.getSelectedItem().toString();
                System.out.println("Stop Time  "+stop_timetext);

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
