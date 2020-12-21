package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    BarChart chart;
    BarChart barChart;
    BarData barData;
    BarDataSet barDataSet;
    ArrayList barEntries;
    ArrayList xAxis;
    String[] car = { "India", "USA", "China", "Japan", "Other"};
    Button buttonchart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
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

        buttonchart = (Button)findViewById(R.id.barchartapi);
        buttonchart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BarChartActivity.this,BarChartdynamicActivity.class);
                startActivity(i);
            }
        });

        getEntries();
        getXAxis();
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



        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,car);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
//        barDataSet.setValueTextSize(18f);

//        ArrayList<BarEntry> value = new ArrayList<>();
//        value.add(new BarEntry(20f, 0)); // Jan
//        value.add(new BarEntry(40f, 1));
//        value.add(new BarEntry(60f, 2));
//        value.add(new BarEntry(10f, 3));
//        value.add(new BarEntry(0f, 4));
//        value.add(new BarEntry(80f, 5));
//        value.add(new BarEntry(30f, 6));
//        value.add(new BarEntry(60f, 7));
//        value.add(new BarEntry(20f, 8));
//        value.add(new BarEntry(40f, 9));
//        value.add(new BarEntry(10f, 10));
//        value.add(new BarEntry(70f, 11));
//
//        BarDataSet dataSet = new BarDataSet(value,"Orders");
//        ArrayList<String> xAxis = new ArrayList<>();
//        xAxis.add("JAN");
//        xAxis.add("FEB");
//        xAxis.add("MAR");
//        xAxis.add("APR");
//        xAxis.add("MAY");
//        xAxis.add("JUN");
//        xAxis.add("JUL");
//        xAxis.add("AUG");
//        xAxis.add("SEP");
//        xAxis.add("OCT");
//        xAxis.add("NOV");
//        xAxis.add("DEC");
//
//        BarData data = new BarData(xAxis,dataSet);
//
//        chart.setData(data);
//        chart.setTouchEnabled(true);
//        chart.setDragEnabled(true);
//        chart.setScaleEnabled(true);

//        dataSet.addColor(ContextCompat.getColor(chart.getContext(), R.color.green));


    }
    private void getEntries() {
        barEntries = new ArrayList<>();
//        barEntries.add(new BarEntry(2f, 0));
//        barEntries.add(new BarEntry(4f, 1));
//        barEntries.add(new BarEntry(6f, 1));
//        barEntries.add(new BarEntry(8f, 3));
//        barEntries.add(new BarEntry(7f, 4));
//        barEntries.add(new BarEntry(3f, 3));
        barEntries.add(new BarEntry(1, 10f)); // Jan
        barEntries.add(new BarEntry(2, 40f));
        barEntries.add(new BarEntry(3, 60f));
        barEntries.add(new BarEntry(4, 10f));
        barEntries.add(new BarEntry(5, 0f));
        barEntries.add(new BarEntry(6, 80f));
        barEntries.add(new BarEntry(7, 30f));
        barEntries.add(new BarEntry(8, 60f));
        barEntries.add(new BarEntry(9, 20f));
        barEntries.add(new BarEntry(10, 40f));
        barEntries.add(new BarEntry(11, 10f));
        barEntries.add(new BarEntry(12, 70f));
    }
    private void getXAxis() {
        xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AUG");
        xAxis.add("SEP");
        xAxis.add("OCT");
        xAxis.add("NOV");
        xAxis.add("DEC");
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
