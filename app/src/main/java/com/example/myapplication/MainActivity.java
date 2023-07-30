package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TextView accels_text, txt_prev, txt_change, txt_countdown,lastMeasurements_txt;
    TextView distance_text;
    double distance;
    Button mButtonStart;
    ProgressBar probar;

    private long mStartTime = 0;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private double accCurrentVal;
    private double accPrevVal;

    private boolean mInitialized;

    private double mLastX;

    private double NOISE = 0.0003;

    private static final long START_TIME_IN_MILLIS = 10000;
    private CountDownTimer mCountDownTimer;
    private  boolean mTimerRunning;
    private boolean onoffsensor;
    private long mTimeLeftinMillis =  START_TIME_IN_MILLIS;
    private SharedPreferences mSharedPreferences;
    private static final String PREFERENCES_NAME = "sensor_data";
    private static final String KEY_LAST_MEASUREMENT = "last_measurement";

    private SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            final ArrayList<Double> accln = new ArrayList<>();

            float x = sensorEvent.values[0];


            if (!mInitialized) {
                mLastX = x;


                mInitialized = true;
            } else {
//                long currentTime = System.currentTimeMillis();
//                if ((currentTime - mStartTime) > 3000) { // 3000 ms = 3 sec
//                    return; // Skip processing if 3 seconds have passed
//                }
                double deltaX = Math.abs(mLastX - x);

                if (deltaX < NOISE) deltaX = (double) 0.0;

                mLastX = x;

                //calculate agar nilai ketiga axis tidak berantakan

                accCurrentVal = deltaX;
                accln.add(accCurrentVal);

            }
            double accChangeVal = Math.abs(accCurrentVal - accPrevVal);
            accPrevVal = accCurrentVal;

            //Update text
            accels_text.setText("Current = " + Math.round(accCurrentVal * 100.0) / 100.0);
            txt_prev.setText("Previous = " + Math.round(accPrevVal * 100.0) / 100.0);
            txt_change.setText("Change " + Math.round(accChangeVal * 100.0) / 100.0);


            probar.setProgress((int) accChangeVal);
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putFloat(KEY_LAST_MEASUREMENT, (float) accCurrentVal);
            editor.apply();

            long currentTimeInSeconds = System.currentTimeMillis() / 1000;

            // Hitung durasi dalam detik sejak pengukuran terakhir
            long durationInSeconds = currentTimeInSeconds - mStartTime;

            // Hitung jarak berdasarkan rumus fisika: distance = 0.5 * acceleration * time^2
            // Kita asumsikan bahwa akselerasi adalah akselerasi rata-rata selama interval waktu tersebut
            distance = 0.5 * accCurrentVal * Math.pow(durationInSeconds, 2);
            mStartTime = currentTimeInSeconds;

            // Update teks jarak
            distance_text.setText("Distance = " + distance);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    lastMeasurements_txt.setText("Last measurement = " + accCurrentVal);
                }
            });


        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accels_text = findViewById(R.id.accels_text);
        txt_change = findViewById(R.id.txt_change);
        txt_prev = findViewById(R.id.txt_prev);
        probar = findViewById(R.id.probar);
        txt_countdown = findViewById(R.id.txt_countdown);
        mButtonStart = findViewById(R.id.mButtonStart);
        lastMeasurements_txt = findViewById(R.id.lastMeasurement_txt);
        distance_text = findViewById(R.id.distance_text);


        //Sensor
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        //Function
        mButtonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTimerRunning){
                    pauseTimer();
                }
                else{
                    startTimer();
                }
            }
        });
        updateCountDownText();

        // Initialize SharedPreferences
        mSharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);

        // Read and display the last saved measurement
        float lastMeasurement = mSharedPreferences.getFloat(KEY_LAST_MEASUREMENT, 0);
//        lastMeasurements_txt.setText("Last measurement = " + lastMeasurement);
        mStartTime = System.currentTimeMillis() / 1000;

    }

    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStart.setText("Start");
    }
    private void startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftinMillis, 1000) {
            @Override
            public void onTick(long l) {
                mTimeLeftinMillis = l;
                mSensorManager.registerListener(sensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL); // register listener here
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning =  false;
                mSensorManager.unregisterListener(sensorEventListener); // unregister listener here
                mButtonStart.setText("Start");
                mTimeLeftinMillis = START_TIME_IN_MILLIS;
//                onoffsensor = false;
                updateCountDownText();


            }
        }.start();
        mTimerRunning = true;
//        onoffsensor = true;
        mButtonStart.setText("Pause");
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftinMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftinMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d",minutes,seconds);
        txt_countdown.setText(timeLeftFormatted);
    }

    protected void onResume() {
        super.onResume();
//        mSensorManager.registerListener(sensorEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        if (mTimerRunning) { // Unregister listener only if timer is running
            mSensorManager.unregisterListener(sensorEventListener);
        }
    }
}
