package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AndroidException;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CatalogActivity extends AppCompatActivity {


    TextView textView;
    ArrayList<String> arraylist;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        textView = (TextView)findViewById(R.id.search_spinner);

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
