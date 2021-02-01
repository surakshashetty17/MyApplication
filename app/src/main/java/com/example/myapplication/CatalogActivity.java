package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AndroidException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CatalogActivity extends AppCompatActivity {

    TextView textView;
    ArrayList<String> arraylist;
    Dialog dialog;
    Button button_createproduct,button_refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        textView = (TextView)findViewById(R.id.search_spinner);
        button_createproduct = (Button) findViewById(R.id.button_createproduct);

        button_createproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(CatalogActivity.this,CreateProductActivity.class);
                startActivity(i);
            }
        });

        button_refresh = (Button) findViewById(R.id.button_refresh);

        button_refresh .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //refresh and reload the current page
                Intent i = new Intent(CatalogActivity.this, CatalogActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });

        arraylist = new ArrayList<>();
        arraylist.add("one");
        arraylist.add("two");
        arraylist.add("three");
        arraylist.add("four");
        arraylist.add("five");
        arraylist.add("six");
        arraylist.add("seven");
        arraylist.add("eight");
        arraylist.add("nine");
        arraylist.add("ten");

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(CatalogActivity.this);
                dialog.setContentView(R.layout.search_spinner_dialog);
//                dialog.getWindow().setLayout(650,800);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                dialog.show();

                EditText editText = dialog.findViewById(R.id.search_edit);
                ListView listView = dialog.findViewById(R.id.search_list);

                final ArrayAdapter<String> adapter = new ArrayAdapter<>(CatalogActivity.this,android.R.layout.simple_list_item_1,arraylist);
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                        adapter.getFilter().filter(s);

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        textView.setText(adapter.getItem(position));
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
