package com.example.stuntmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class grafik extends AppCompatActivity {

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafik);

        mChart = findViewById(R.id.chart);

        LineDataSet lineDataSet1 = new LineDataSet(dataValues1(new int[]{20, 24, 2, 10, 28}), "Dataset 1");
        LineDataSet lineDataSet2 = new LineDataSet(dataValues1(new int[]{12, 16, 23, 1, 18}), "Dataset 2");

        ArrayList<ILineDataSet> datasets = new ArrayList<>();
        datasets.add(lineDataSet1);
        datasets.add(lineDataSet2);

//        mChart.setBackgroundColor(Color.GREEN);
//        mChart.setNoDataText("tidak ada data");
//        mChart.setNoDataTextColor(Color.BLUE);

        mChart.setDrawGridBackground(true);
        mChart.setDrawBorders(true);
        mChart.setBorderColor(Color.RED);
        mChart.setBorderWidth(5);

        Legend legend = mChart.getLegend();
        legend.setEnabled(true);
        legend.setTextColor(Color.RED);
        legend.setTextSize(15);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormToTextSpace(5);

        Description description = new Description();
        description.setText("haha");
        description.setTextColor(Color.BLUE);
        description.setTextSize(20);
        mChart.setDescription(description);

        LineData data = new LineData(datasets);

        mChart.setData(data);
        mChart.invalidate();

    }

    private ArrayList<Entry> dataValues1(int[] values){
        ArrayList<Entry> dataVals = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            dataVals.add(new Entry(i, values[i]));
        }

        return dataVals;
    }
}