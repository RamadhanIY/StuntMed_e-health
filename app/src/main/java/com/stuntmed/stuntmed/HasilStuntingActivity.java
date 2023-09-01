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

    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_stunting);

        mChart = findViewById(R.id.chart);

        LineDataSet line_sd3_neg = new LineDataSet(dataValues1(new float[]{30.7f, 33.8f, 35.6f, 37f, 38f, 38.9f, 39.7f, 40.3f, 40.8f, 41.2f, 41.6f, 41.9f, 42.2f, 42.5f, 42.7f, 42.9f, 43.1f, 43.2f, 43.4f, 43.5f, 43.7f, 43.8f, 43.9f, 44.1f, 44.2f}), "line_sd3_neg");
        LineDataSet line_sd3 = new LineDataSet(dataValues1(new float[]{38.3f, 40.8f, 42.6f, 44.1f, 45.2f, 46.2f, 47f, 47.7f, 48.3f, 48.8f, 49.2f, 49.6f, 49.9f, 50.2f, 50.5f, 50.7f, 51f, 51.2f, 51.4f, 51.5f, 51.7f, 51.9f, 52f, 52.2f, 52.3f}), "line_sd3");

        LineDataSet line_sd2_neg = new LineDataSet(dataValues1(new float[]{31.9f, 34.9f, 36.8f, 38.1f, 39.2f, 40.1f, 40.9f, 41.5f, 42f, 42.5f, 42.9f, 43.2f, 43.5f, 43.8f, 44f, 44.2f, 44.4f, 44.6f, 44.7f, 44.9f, 45f, 45.2f, 45.3f, 45.4f, 45.5f}), "line_sd2_neg");
        LineDataSet line_sd2 = new LineDataSet(dataValues1(new float[]{37f, 39.6f, 41.5f, 42.9f, 44f, 45f, 45.8f, 46.4f, 47f, 47.5f, 47.9f, 48.3f, 48.6f, 48.9f, 49.2f, 49.4f, 49.6f, 49.8f, 50f, 50.2f, 50.4f, 50.5f, 50.7f, 50.8f, 51f}), "line_sd2");

        LineDataSet line_sd1_neg = new LineDataSet(dataValues1(new float[]{33.2f, 36.1f, 38f, 39.3f, 40.4f, 41.4f, 42.1f, 42.7f, 43.3f, 43.7f, 44.1f, 44.5f, 44.8f, 45f, 45.3f, 45.5f, 45.7f, 45.9f, 46f, 46.2f, 46.4f, 46.5f, 46.6f, 46.8f, 46.9f}), "line_sd1_neg");
        LineDataSet line_sd1 = new LineDataSet(dataValues1(new float[]{35.7f, 38.4f, 40.3f, 41.7f, 42.8f, 43.8f, 44.6f, 45.2f, 45.8f, 46.3f, 46.7f, 47f, 47.4f, 47.6f, 47.9f, 48.1f, 48.3f, 48.5f, 48.7f, 48.9f, 49f, 49.2f, 49.3f, 49.5f, 49.6f}), "line_sd1");

        LineDataSet line_sd0 = new LineDataSet(dataValues1(new float[]{34.5f, 37.3f, 39.1f, 40.5f, 41.6f, 42.6f, 43.3f, 44f, 44.5f, 45f, 45.4f, 45.8f, 46.1f, 46.3f, 46.6f, 46.8f, 47f, 47.2f, 47.4f, 47.5f, 47.7f, 47.8f, 48f, 48.1f, 48.3f}), "line_sd0");

        LineDataSet dataset = new LineDataSet(dataValues1(new float[]{32f}), "Dataset");

        line_sd3_neg.setColor(Color.RED);
        line_sd3_neg.setLineWidth(2);
        line_sd3.setColor(Color.RED);
        line_sd3.setLineWidth(2);
        line_sd3.setFillFormatter(new MyFillFormatter(line_sd3_neg));
        line_sd3.setDrawFilled(true);
        line_sd3.setFillColor(Color.RED);

        // Set DrawCircles = false
        line_sd3_neg.setDrawCircles(false);
        line_sd3.setDrawCircles(false);
        line_sd2.setDrawCircles(false);
        line_sd2_neg.setDrawCircles(false);
        line_sd1.setDrawCircles(false);
        line_sd1_neg.setDrawCircles(false);
        line_sd0.setDrawCircles(false);

        line_sd2_neg.setColor(Color.YELLOW);
        line_sd2_neg.setLineWidth(2);
        line_sd2.setColor(Color.YELLOW);
        line_sd2.setLineWidth(2);
        line_sd2.setFillFormatter(new MyFillFormatter(line_sd2_neg));
        line_sd2.setDrawFilled(true);
        line_sd2.setFillColor(Color.YELLOW);

        line_sd1_neg.setColor(Color.GREEN);
        line_sd1_neg.setLineWidth(2);
        line_sd1.setColor(Color.GREEN);
        line_sd1.setLineWidth(2);
        line_sd1.setFillFormatter(new MyFillFormatter(line_sd1_neg));
        line_sd1.setDrawFilled(true);
        line_sd1.setFillColor(Color.GREEN);

        line_sd0.setColor(Color.BLUE);
        line_sd0.setLineWidth(2);


        ArrayList<ILineDataSet> datasets = new ArrayList<>();
        datasets.add(line_sd3_neg);
        datasets.add(line_sd3);
        datasets.add(line_sd2_neg);
        datasets.add(line_sd2);
        datasets.add(line_sd1_neg);
        datasets.add(line_sd1);
        datasets.add(line_sd0);
        datasets.add(dataset);

//        mChart.setBackgroundColor(Color.GREEN);
//        mChart.setNoDataText("tidak ada data");
//        mChart.setNoDataTextColor(Color.BLUE);

        mChart.setRenderer(new MyLineLegendRenderer(mChart, mChart.getAnimator(), mChart.getViewPortHandler()));
        mChart.setDrawGridBackground(true);
        mChart.setDrawBorders(true);
        mChart.setBorderColor(Color.RED);
        mChart.setBorderWidth(2);

        Legend legend = mChart.getLegend();
        legend.setEnabled(true);
        legend.setTextColor(Color.RED);
        legend.setTextSize(15);
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setFormToTextSpace(5);

        // set judul grafik
//        Description description = new Description();
//        description.setText("Lingkar Kepala");
////        description.setTextColor(Color.BLUE);
//        description.setTextSize(20);
//        description.setPosition(0,0);
//        mChart.setDescription(description);



        // menghilangkan angka di sebelah kanan grafik
        YAxis yAxisRight = mChart.getAxisRight();
        yAxisRight.setDrawLabels(false);

        // set posisi label x agar di bawah grafik
        XAxis xAxisTop = mChart.getXAxis();
        xAxisTop.setPosition(XAxis.XAxisPosition.BOTTOM);

        // hapus form dan label legend
        mChart.getLegend().setEnabled(false);

        // set agar grafik menampilkan range tertentu
//        mChart.getXAxis().setla
//        mChart.getXAxis().setDrawLabels(true);

        LineData data = new LineData(datasets);

        mChart.setData(data);
        mChart.invalidate();

    }

    private ArrayList<Entry> dataValues1(float[] values){
        ArrayList<Entry> dataVals = new ArrayList<>();

        for (int i = 0; i < values.length; i++) {
            dataVals.add(new Entry(i, values[i]));
        }

        return dataVals;
    }

    public class MyFillFormatter implements IFillFormatter {
        private ILineDataSet boundaryDataSet;

        public MyFillFormatter() {
            this(null);
        }
        //Pass the dataset of other line in the Constructor
        public MyFillFormatter(ILineDataSet boundaryDataSet) {
            this.boundaryDataSet = boundaryDataSet;
        }

        @Override
        public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
            return 0;
        }

        //Define a new method which is used in the LineChartRenderer
        public List<Entry> getFillLineBoundary() {
            if(boundaryDataSet != null) {
                return ((LineDataSet) boundaryDataSet).getValues();
            }
            return null;
        }}

    public class MyLineLegendRenderer extends LineChartRenderer {

        public MyLineLegendRenderer(LineDataProvider chart, ChartAnimator animator, ViewPortHandler viewPortHandler) {
            super(chart, animator, viewPortHandler);
        }

        //This method is same as it's parent implemntation
        @Override
        protected void drawLinearFill(Canvas c, ILineDataSet dataSet, Transformer trans, XBounds bounds) {
            final Path filled = mGenerateFilledPathBuffer;

            final int startingIndex = bounds.min;
            final int endingIndex = bounds.range + bounds.min;
            final int indexInterval = 128;

            int currentStartIndex = 0;
            int currentEndIndex = indexInterval;
            int iterations = 0;

            // Doing this iteratively in order to avoid OutOfMemory errors that can happen on large bounds sets.
            do {
                currentStartIndex = startingIndex + (iterations * indexInterval);
                currentEndIndex = currentStartIndex + indexInterval;
                currentEndIndex = currentEndIndex > endingIndex ? endingIndex : currentEndIndex;

                if (currentStartIndex <= currentEndIndex) {
                    generateFilledPath(dataSet, currentStartIndex, currentEndIndex, filled);

                    trans.pathValueToPixel(filled);

                    final Drawable drawable = dataSet.getFillDrawable();
                    if (drawable != null) {

                        drawFilledPath(c, filled, drawable);
                    } else {

                        drawFilledPath(c, filled, dataSet.getFillColor(), dataSet.getFillAlpha());
                    }
                }

                iterations++;

            } while (currentStartIndex <= currentEndIndex);
        }

        //This is where we define the area to be filled.
        private void generateFilledPath(final ILineDataSet dataSet, final int startIndex, final int endIndex, final Path outputPath) {

            //Call the custom method to retrieve the dataset for other line
            final List<Entry> boundaryEntry = ((MyFillFormatter)dataSet.getFillFormatter()).getFillLineBoundary();

            final float phaseY = mAnimator.getPhaseY();
            final Path filled = outputPath;
            filled.reset();

            final Entry entry = dataSet.getEntryForIndex(startIndex);

            filled.moveTo(entry.getX(), boundaryEntry.get(0).getY());
            filled.lineTo(entry.getX(), entry.getY() * phaseY);

            // create a new path
            Entry currentEntry = null;
            Entry previousEntry = null;
            for (int x = startIndex + 1; x <= endIndex; x++) {

                currentEntry = dataSet.getEntryForIndex(x);
                filled.lineTo(currentEntry.getX(), currentEntry.getY() * phaseY);

            }

            // close up
            if (currentEntry != null && previousEntry!= null) {
                filled.lineTo(currentEntry.getX(), previousEntry.getY());
            }

            //Draw the path towards the other line
            for (int x = endIndex ; x > startIndex; x--) {
                previousEntry = boundaryEntry.get(x);
                filled.lineTo(previousEntry.getX(), previousEntry.getY() * phaseY);
            }

            filled.close();
        }
    }
}