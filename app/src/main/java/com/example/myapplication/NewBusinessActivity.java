package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class NewBusinessActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7;
    Spinner spin1,spin2,spin3,spin4,spin5;
    Button save_changes;
    String[] interest;
    ArrayList<String> interestList;
    RadioGroup radiogroup_shipping,radiogroup_status;
    RadioButton radiobutton_shipping,radiobutton_status;
    TextView text_shipping,text_status;

    TextInputEditText edit_compname, edit_street;

    String[] start_time = { "Start Time","4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM", "12AM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM", "12AM"};
    String[] stop_time = { "Stop Time","4AM", "5AM", "6AM", "7AM", "8AM", "9AM", "10AM", "11AM", "12AM", "1PM", "2PM", "3PM", "4PM", "5PM", "6PM", "7PM", "8PM", "9PM", "10PM", "11PM", "12AM"};
    String[] business_type = {"Select Type","Service","Product"};
    String[] select_state = {"Select State","Karnataka","Telangana","AndraPradesh","Maharashtra"};
    String[] pan_city = {"Select City"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_business);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        text_shipping = (TextView) findViewById(R.id.text_shipping);
        text_status = (TextView) findViewById(R.id.text_status);


        edit_compname = (TextInputEditText) findViewById(R.id.textedit_compname);
        edit_street = (TextInputEditText) findViewById(R.id.textedit_street);

        checkBox1=(CheckBox)findViewById(R.id.checkBox11);
        checkBox2=(CheckBox)findViewById(R.id.checkBox12);
        checkBox3=(CheckBox)findViewById(R.id.checkBox13);
        checkBox4=(CheckBox)findViewById(R.id.checkBox14);
        checkBox5=(CheckBox)findViewById(R.id.checkBox15);
        checkBox6=(CheckBox)findViewById(R.id.checkBox16);
        checkBox7=(CheckBox)findViewById(R.id.checkBox17);

        spin1 = (Spinner) findViewById(R.id.spinner11);
//        spin1.setOnItemSelectedListener(this);
        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,start_time);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin1.setAdapter(aa1);

        spin2 = (Spinner) findViewById(R.id.spinner12);
        spin2.setOnItemSelectedListener(this);
        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,stop_time);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin2.setAdapter(aa2);

        spin3 = (Spinner) findViewById(R.id.spinner_business);
        spin3.setOnItemSelectedListener(this);
        ArrayAdapter aa3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,business_type);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin3.setAdapter(aa3);

        spin4 = (Spinner) findViewById(R.id.spinner_state);
        spin4.setOnItemSelectedListener(this);
        ArrayAdapter aa4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,select_state);
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin4.setAdapter(aa4);

        spin5 = (Spinner) findViewById(R.id.spinner_panindia);
        spin5.setOnItemSelectedListener(this);
        ArrayAdapter aa5 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pan_city);
        aa5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin5.setAdapter(aa5);

        radiogroup_shipping = (RadioGroup)findViewById(R.id.radiogroup_shipping);
        radiogroup_status = (RadioGroup)findViewById(R.id.radiogroup_status);

        save_changes = (Button)findViewById(R.id.button_savebusiness);
        save_changes.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
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
                System.out.println("Start Time:  "+start_timetext);

                String stop_timetext = spin2.getSelectedItem().toString();
                System.out.println("Stop Time:  "+stop_timetext);

                String business_typetext = spin3.getSelectedItem().toString();
                System.out.println("Business Type:  "+business_typetext);

                String select_statetext = spin4.getSelectedItem().toString();
                System.out.println("Select State:  "+select_statetext);

                String pan_indiatext = spin5.getSelectedItem().toString();
                System.out.println("Select City:  "+pan_indiatext);

                //for printing radiobutton selected values in android studio
                if(radiogroup_shipping.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select Shipping / Service PanIndia", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    text_shipping.setError(null);
                    // get selected radio button from radioGroup
                    int selectedId = radiogroup_shipping.getCheckedRadioButtonId();
                    // find the radiobutton by returned id
                    radiobutton_shipping = (RadioButton)findViewById(selectedId);
                    Log.d("tag","Shipping/Service PanIndia: " + radiobutton_shipping.getText());
                }

                if(radiogroup_status.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select Status", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    text_status.setError(null);
                    int selectedId = radiogroup_status.getCheckedRadioButtonId();
                    radiobutton_status = (RadioButton)findViewById(selectedId);
                    Log.d("tag","Status: " + radiobutton_status.getText());
                }

                //field validation code for 1.company name 2.street(address) 3.radio button
                final String Name=edit_compname.getText().toString();
                final String word=edit_street.getText().toString();

                if(Name.length()==0)
                {
                    edit_compname.requestFocus();
                    edit_compname.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!Name.matches("[a-zA-Z ]+"))
                {
                    edit_compname.requestFocus();
                    edit_compname.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(word.length()==0)
                {
                    edit_street.requestFocus();
                    edit_street.setError("FIELD CANNOT BE EMPTY");
                }
                else if(radiogroup_shipping.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select Shipping / Service PanIndia", Toast.LENGTH_SHORT).show();
                    text_shipping.setError("Choose Shipping/Service PanIndia");
                }
                else if(radiogroup_status.getCheckedRadioButtonId()==-1)
                {
                    text_status.setError("Choose Status");
                    Toast.makeText(getApplicationContext(), "Please select Status", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(NewBusinessActivity.this,NewBusinessContactActivity.class);
                    startActivity(i);
                }
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
