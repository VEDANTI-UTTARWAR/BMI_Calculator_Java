package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Bmi_Chart extends AppCompatActivity {
    private TextView bmi_chart;
    private ImageView chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi__chart);
        bmi_chart=(TextView)findViewById(R.id.chart);
        chart=(ImageView)findViewById(R.id.chartImage);
    }
}