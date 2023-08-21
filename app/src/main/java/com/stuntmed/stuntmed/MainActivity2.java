package com.stuntmed.stuntmed;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.*;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;







public class MainActivity2 extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    CameraBridgeViewBase cameraBridgeViewBase;
    File cascafile;
    CascadeClassifier bodydetector;

    private Mat mRgba,mGrey;

    int counter = 0;
    private static String TAG = "MainActivity2";

    JavaCameraView javaCameraView;
    Mat mRGBA, mRGBAT;

    private final int PERMISSIONS_READ_CAMERA=1;

    BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(MainActivity2.this) {
        @Override
        public void onManagerConnected(int status) {
            Log.d(TAG, "callbacksuccess");
            switch (status)
            {
                case BaseLoaderCallback.SUCCESS:
                {
                    Log.d(TAG, "case success");
//                    InputStream is = getResources().openRawResource(R.raw.haarcascade_fullbody);
//                    File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
//                    cascafile = new File(cascadeDir,"haarcascade_fullbody");
//
//                    FileOutputStream fos = null;
//                    try {
//                        fos = new FileOutputStream(cascafile);
//                    } catch (FileNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    byte[] buffer =  new byte[4096];
//                    int byteread;
//
//                    while(true){
//                        try {
//                            if (!((byteread = is.read(buffer)) != 1)) break;
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                        try {
//                            fos.write(buffer,0,byteread);
//                        } catch (IOException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//
//                    try {
//                        is.close();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    try {
//                        fos.close();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    bodydetector = new CascadeClassifier(cascafile.getAbsolutePath());
//
//                    if(bodydetector .empty()){
//                        bodydetector = null;
//                    }
//                    else{
//                        cascadeDir.delete();
//                    }

                    javaCameraView.enableView();

                    break;
                }
                default:
                {
                    Log.d(TAG, "case default");
                    super.onManagerConnected(status);
                    break;
                }

            }

        }
    };

    static
    {
        if (OpenCVLoader.initDebug())
        {
            Log.d(TAG, "OpenCV is intialised");
        }
        else
        {
            Log.d(TAG, "OpenCV is not initialised");
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main2);
        javaCameraView = (JavaCameraView)findViewById(R.id.CameraView);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(MainActivity2.this);

// Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        PERMISSIONS_READ_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            Log.d(TAG, "PERMISSIONSs granted");
            javaCameraView.setCameraPermissionGranted();
            // Permission has already been granted
        }


    }
    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        // Ensure that this result is for the camera permission request
        if (requestCode == PERMISSIONS_READ_CAMERA) {
            // Check if the request was granted or denied
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // The request was granted -> tell the camera view
                javaCameraView.setCameraPermissionGranted();
            } else {
                // The request was denied -> tell the user and exit the application
                Toast.makeText(this, "Camera permission required.",
                        Toast.LENGTH_LONG).show();
                this.finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
//        mRgba =  inputFrame.rgba();
//        mGrey =  inputFrame.gray();
//
//        MatOfRect bodydetections = new MatOfRect();
//        bodydetector.detectMultiScale(mRgba,bodydetections);
//
//        for(Rect rect: bodydetections.toArray()) {
//            Imgproc.rectangle(mRgba,new Point(rect.x,rect.y),new Point(rect.x + rect.width,rect.y+rect.height),new Scalar(255,0,0));
//
//        }
//        return mRgba;

        Mat frame = inputFrame.rgba();

        if (counter % 2 == 0){

            Core.flip(frame, frame, 1);
            Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGBA2GRAY);


        }

        counter = counter + 1;






        return frame;
    }


    @Override
    public void onCameraViewStarted(int width, int height) {
//        mRgba = new Mat();
//        mGrey = new Mat();
    }


    @Override
    public void onCameraViewStopped() {
//        mRgba.release();
//        mGrey.release();
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (!OpenCVLoader.initDebug()){
            Toast.makeText(getApplicationContext(),"There's a problem, yo!", Toast.LENGTH_SHORT).show();
        }

        else
        {
            baseLoaderCallback.onManagerConnected(baseLoaderCallback.SUCCESS);
        }



    }

    @Override
    protected void onPause() {
        super.onPause();
        if(cameraBridgeViewBase!=null){

            cameraBridgeViewBase.disableView();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraBridgeViewBase!=null){
            cameraBridgeViewBase.disableView();
        }
    }
}