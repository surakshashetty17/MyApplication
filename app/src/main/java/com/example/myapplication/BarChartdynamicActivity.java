package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class BarChartdynamicActivity extends AppCompatActivity {

    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    ArrayList xAxis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chartdynamic);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

//        chart = (BarChart) findViewById(R.id.chart);
        barChart = findViewById(R.id.chart);
        try {
            loadJSONFromAsset();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        barDataSet = new BarDataSet(barEntries, "orders");
        barData = new BarData(barDataSet);
//        barData = new BarData(xAxis);
        barChart.setData(barData);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
//        barDataSet.setColor(R.color.blue);
        barDataSet.setValueTextColor(Color.BLACK);
    }

    public void loadJSONFromAsset() throws IOException, JSONException {
        String json = null;
        InputStream is = getAssets().open("barchart.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        JSONObject obj = new JSONObject(json);

        barEntries = new ArrayList<>();
        JSONArray contacts = obj.getJSONArray("");

        // looping through All Contacts
        for (int i = 0; i < contacts.length(); i++) {
            JSONObject c = contacts.getJSONObject(i);

            String jumlah = c.getString("jumlah");
            String nilai = c.getString("nilai");
            HashMap<String, String> contact = new HashMap<>();

            // adding each child node to HashMap key => value
            contact.put("id", nilai);
            contact.put("name", jumlah);

            // adding contact to contact list
            barEntries.add(contact);
        }

//        JSONArray m_jArry = obj.getJSONArray("cart");
//        HashMap<String, String> m_li;
//
//        for (int i = 0; i < m_jArry.length(); i++) {
//            CartModal cat = new CartModal();
//            JSONObject jo_inside = m_jArry.getJSONObject(i);
//            Log.d("Details-->", jo_inside.getString("name"));
//            Log.d("Details-->", jo_inside.getString("college"));
//
////            String getTxt_no_table = jo_inside.getString("name");
////            String getTxt_pax = jo_inside.getString("college");
//
//            cat.setId(jo_inside.getInt("id"));
//            cat.setName(jo_inside.getString("name"));
//            cat.setSubject(jo_inside.getString("subject"));
//            cat.setPercentage(jo_inside.getString("percentage"));
//            cat.setCollege(jo_inside.getString("college"));
////            String name=jo_inside.getString("name");
////            String college=jo_inside.getString("college");
////            m_li = new HashMap<String, String>();
////            m_li.put("name", name);
////            m_li.put("college", college);
//            barEntries.add(cat);
//        }

//        Recycler();
//        myrv.setAdapter(cartAdapter);
//        myrv.setHasFixedSize(true);
//        myrv.setLayoutManager(new GridLayoutManager(this, 6));
//        myrv.setLayoutManager(new LinearLayoutManager(this));
    }

}
