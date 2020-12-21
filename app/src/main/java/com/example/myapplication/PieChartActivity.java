package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    PieChart pieChart;
    PieData pieData;
    PieDataSet pieDataSet;
    ArrayList pieEntries;
    ArrayList PieEntryLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        pieChart = findViewById(R.id.pieChart);
        getEntries();
        pieDataSet = new PieDataSet(pieEntries, "orders");
        pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        pieDataSet.setSliceSpace(15);
        pieDataSet.setValueTextColor(Color.BLACK);
//        pieDataSet.setValueTextSize(5f);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(8f);
//        pieDataSet.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
//        pieDataSet.setValueTextSize(10f);
        pieDataSet.setSliceSpace(5f);
    }
    private void getEntries() {
        pieEntries = new ArrayList<>();
//        pieEntries.add(new PieEntry(2f, 0));
//        pieEntries.add(new PieEntry(4f, 1));
//        pieEntries.add(new PieEntry(6f, 2));
//        pieEntries.add(new PieEntry(8f, 3));
//        pieEntries.add(new PieEntry(7f, 4));
//        pieEntries.add(new PieEntry(3f, 5));

        pieEntries.add(new PieEntry(10f, "Jan")); // Jan
        pieEntries.add(new PieEntry(40f, "Feb"));
        pieEntries.add(new PieEntry(60f, "Mar"));
        pieEntries.add(new PieEntry(10f, "Apr"));
        pieEntries.add(new PieEntry(0f, "May"));
        pieEntries.add(new PieEntry(80f, "Jun"));
        pieEntries.add(new PieEntry(30f, "Jul"));
        pieEntries.add(new PieEntry(60f, "Aug"));
        pieEntries.add(new PieEntry(20f, "Sep"));
        pieEntries.add(new PieEntry(40f, "Oct"));
        pieEntries.add(new PieEntry(10f, "Nov"));
        pieEntries.add(new PieEntry(70f, "Dec"));
    }
}
