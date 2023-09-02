package com.stuntmed.stuntmed;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.github.mikephil.charting.animation.ChartAnimator;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.renderer.LineChartRenderer;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class HasilStuntingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_stunting);

        LineChart mChart = findViewById(R.id.chart);

        float[] data_sd3_neg = {30.7f, 33.8f, 35.6f, 37f, 38f, 38.9f, 39.7f, 40.3f, 40.8f, 41.2f, 41.6f, 41.9f, 42.2f, 42.5f, 42.7f, 42.9f, 43.1f, 43.2f, 43.4f, 43.5f, 43.7f, 43.8f, 43.9f, 44.1f, 44.2f};
        float[] data_sd3 = {38.3f, 40.8f, 42.6f, 44.1f, 45.2f, 46.2f, 47f, 47.7f, 48.3f, 48.8f, 49.2f, 49.6f, 49.9f, 50.2f, 50.5f, 50.7f, 51f, 51.2f, 51.4f, 51.5f, 51.7f, 51.9f, 52f, 52.2f, 52.3f};
        float[] data_sd2_neg = {31.9f, 34.9f, 36.8f, 38.1f, 39.2f, 40.1f, 40.9f, 41.5f, 42f, 42.5f, 42.9f, 43.2f, 43.5f, 43.8f, 44f, 44.2f, 44.4f, 44.6f, 44.7f, 44.9f, 45f, 45.2f, 45.3f, 45.4f, 45.5f};
        float[] data_sd2 = {37f, 39.6f, 41.5f, 42.9f, 44f, 45f, 45.8f, 46.4f, 47f, 47.5f, 47.9f, 48.3f, 48.6f, 48.9f, 49.2f, 49.4f, 49.6f, 49.8f, 50f, 50.2f, 50.4f, 50.5f, 50.7f, 50.8f, 51f};
        float[] data_sd1_neg = {33.2f, 36.1f, 38f, 39.3f, 40.4f, 41.4f, 42.1f, 42.7f, 43.3f, 43.7f, 44.1f, 44.5f, 44.8f, 45f, 45.3f, 45.5f, 45.7f, 45.9f, 46f, 46.2f, 46.4f, 46.5f, 46.6f, 46.8f, 46.9f};
        float[] data_sd1 = {35.7f, 38.4f, 40.3f, 41.7f, 42.8f, 43.8f, 44.6f, 45.2f, 45.8f, 46.3f, 46.7f, 47f, 47.4f, 47.6f, 47.9f, 48.1f, 48.3f, 48.5f, 48.7f, 48.9f, 49f, 49.2f, 49.3f, 49.5f, 49.6f};
        float[] data_sd0 = {34.5f, 37.3f, 39.1f, 40.5f, 41.6f, 42.6f, 43.3f, 44f, 44.5f, 45f, 45.4f, 45.8f, 46.1f, 46.3f, 46.6f, 46.8f, 47f, 47.2f, 47.4f, 47.5f, 47.7f, 47.8f, 48f, 48.1f, 48.3f};
        float[] dataset = {32f, 30f};

        Grafik graf = new Grafik(
                mChart,
                dataset,
                data_sd0,
                data_sd1_neg,data_sd1,
                data_sd2_neg, data_sd2,
                data_sd3_neg, data_sd3
        );
        graf.run();

    }
}