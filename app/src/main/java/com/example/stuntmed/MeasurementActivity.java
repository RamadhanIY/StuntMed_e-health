package com.example.stuntmed;

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

import com.example.stuntmed.R;

import java.util.ArrayList;
import java.util.Arrays;


public class MeasurementActivity extends AppCompatActivity {

    TextView accels_text, txt_prev, txt_change, txt_countdown,lastMeasurements_txt,txt_listdistances;
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

    private double NOISE = 0.03;

    private static final long START_TIME_IN_MILLIS = 5000;
    private CountDownTimer mCountDownTimer;
    private  boolean mTimerRunning;
    private boolean onoffsensor;
    private long mTimeLeftinMillis =  START_TIME_IN_MILLIS;
    private SharedPreferences mSharedPreferences;

    double lastAcceleration; // to store the last acceleration measurement
    ArrayList<Double> sessionAccel = new ArrayList<>(); // to store the last acceleration of each session
    // ...

    double lastDistance; // to store the last acceleration measurement
    ArrayList<Double> listDistances = new ArrayList<>(); // to store the last acceleration of each session
    // ...


    private SensorEventListener sensorEventListener = new SensorEventListener() {

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {


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


            }

            double accChangeVal = Math.abs(accCurrentVal - accPrevVal);
            accPrevVal = accCurrentVal;
            lastAcceleration = accCurrentVal;



            //Update text
            accels_text.setText("Current = " + Math.round(accCurrentVal * 100.0) / 100.0 + "m/s^2");
            txt_prev.setText("Previous = " + Math.round(accPrevVal * 100.0) / 100.0);
            probar.setProgress((int) accChangeVal);

            long currentTimeInSeconds = System.currentTimeMillis() / 1000;

            // Hitung durasi dalam detik sejak pengukuran terakhir
            long durationInSeconds = currentTimeInSeconds - mStartTime;

            // Hitung jarak berdasarkan rumus fisika: distance = 0.5 * acceleration * time^2
            // Kita asumsikan bahwa akselerasi adalah akselerasi rata-rata selama interval waktu tersebut


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
        setContentView(R.layout.activity_measurement);

        accels_text = findViewById(R.id.accels_text);
        txt_change = findViewById(R.id.txt_change);
        txt_prev = findViewById(R.id.txt_prev);
        probar = findViewById(R.id.probar);
        txt_countdown = findViewById(R.id.txt_countdown);
        mButtonStart = findViewById(R.id.mButtonStart);
        lastMeasurements_txt = findViewById(R.id.lastMeasurement_txt);
        distance_text = findViewById(R.id.distance_text);
        txt_listdistances = findViewById(R.id.txt_listdistances);


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
//        mSharedPreferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
//
//        // Read and display the last saved measurement
//        float lastMeasurement = mSharedPreferences.getFloat(KEY_LAST_MEASUREMENT, 0);
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
                updateCountDownText();
                sessionAccel.add(lastAcceleration); // add the last acceleration of the session
                // Convert the list to String and display it
                String sessionAccelStr = Arrays.toString(sessionAccel.toArray());
                txt_change.setText("List: " + sessionAccelStr);
                distance = 0.5 * lastAcceleration * Math.pow(START_TIME_IN_MILLIS/1000, 2);
                // Update teks jarak
                distance_text.setText("Distance = " + distance + "m");
                listDistances.add(distance);
                String sessionlistdistances = Arrays.toString(listDistances.toArray());
                txt_listdistances.setText("List" + sessionlistdistances);




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
