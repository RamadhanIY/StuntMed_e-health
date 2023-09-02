package com.stuntmed.stuntmed;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.Drawable;

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

public class Grafik {

    private LineData data;
    private LineChart mChart;
    LineDataSet line_sd3_neg;
    private LineDataSet line_sd3;
    private LineDataSet line_sd2_neg;
    private LineDataSet line_sd2;
    private LineDataSet line_sd1_neg;
    private LineDataSet line_sd1;
    private LineDataSet line_sd0;
    private LineDataSet line_dataset;

    public Grafik(LineChart mChart,
                  float[] dataset,
                  float[] data_sd0,
                  float[] data_sd1_neg,
                  float[] data_sd1,
                  float[] data_sd2_neg,
                  float[] data_sd2,
                  float[] data_sd3_neg,
                  float[] data_sd3){

        this.mChart = mChart;

        line_sd3_neg = new LineDataSet(dataValues1(data_sd3_neg), "line_sd3_neg");
        line_sd3 = new LineDataSet(dataValues1(data_sd3), "line_sd3");

        line_sd2_neg = new LineDataSet(dataValues1(data_sd2_neg), "line_sd2_neg");
        line_sd2 = new LineDataSet(dataValues1(data_sd2), "line_sd2");

        line_sd1_neg = new LineDataSet(dataValues1(data_sd1_neg), "line_sd1_neg");
        line_sd1 = new LineDataSet(dataValues1(data_sd1), "line_sd1");

        line_sd0 = new LineDataSet(dataValues1(data_sd0), "line_sd0");

        line_dataset = new LineDataSet(dataValues1(dataset), "Dataset");

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
        datasets.add(line_dataset);


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


        data = new LineData(datasets);
    }

    public void run(){

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

    public void setLine_sd0(LineDataSet line_sd0) {
        this.line_sd0 = line_sd0;
    }

    public void setLine_sd1(LineDataSet line_sd1) {
        this.line_sd1 = line_sd1;
    }

    public void setLine_sd1_neg(LineDataSet line_sd1_neg) {
        this.line_sd1_neg = line_sd1_neg;
    }

    public void setLine_sd2(LineDataSet line_sd2) {
        this.line_sd2 = line_sd2;
    }

    public void setLine_sd2_neg(LineDataSet line_sd2_neg) {
        this.line_sd2_neg = line_sd2_neg;
    }

    public void setLine_sd3(LineDataSet line_sd3) {
        this.line_sd3 = line_sd3;
    }

    public void setLine_sd3_neg(LineDataSet line_sd3_neg) {
        this.line_sd3_neg = line_sd3_neg;
    }
}
