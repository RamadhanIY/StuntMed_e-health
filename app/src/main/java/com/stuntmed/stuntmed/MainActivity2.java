package com.stuntmed.stuntmed;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class MainActivity2 extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {



    CascadeClassifier cascadeClassifier;

    private Mat mRgba,mGray;

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
        try {
            InputStream is = getResources().openRawResource(R.raw.haarcascade_fullbody);
            File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
            File cascafile = new File(cascadeDir,"haarcascade_fullbody.xml");
            FileOutputStream os = new FileOutputStream(cascafile);

            byte[ ] buffer = new byte[4096];
            int byteRead;

            while((byteRead= is.read(buffer))!= - 1){
                os.write(buffer,0,byteRead);
            }
            is.close();
            os.close();
            //Loading file from cascade folder created above
            cascadeClassifier = new CascadeClassifier(cascafile.getAbsolutePath());

        }
        catch (IOException e){
            Log.i(TAG,"Cascade file not found!");
        }

//        try {
//            InputStream is = getResources().openRawResource(R.raw.haarcascade_fullbody);
//            File cascadeDir = getDir("cascade", Context.MODE_PRIVATE);
//            File cascafile = new File(cascadeDir,"haarcascade_fullbody");
//            FileOutputStream os = new FileOutputStream(cascafile);
//        }
//        catch (IOException){
//            Log.i(TAG,"Cascade file not found!");
//        }



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
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()){
            //if load success
            Log.d(TAG,"Opencv initialization is done");
            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else{
            //if not loaded
            Log.d(TAG,"Opencv is not loaded. try again");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_4_0,this,baseLoaderCallback);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (javaCameraView !=null){
            javaCameraView.disableView();
        }
    }

    public void onDestroy(){
        super.onDestroy();
        if(javaCameraView !=null){
            javaCameraView.disableView();
        }

    }

    public void onCameraViewStarted(int width ,int height){
        mRgba=new Mat(height,width, CvType.CV_8UC4);
        mGray =new Mat(height,width,CvType.CV_8UC1);
    }
    public void onCameraViewStopped(){
        mRgba.release();
    }
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame){
        mRgba=inputFrame.rgba();
        mGray=inputFrame.gray();

        //in processing pass mRGBa to cascade class

        mRgba =  CascadeRec(mRgba);

        return mRgba;

    }
    private Mat CascadeRec(Mat mRgba){
        //Original frame is -90 degree so we have to rotate to 90 degree

        Core.flip(mRgba.t(),mRgba,1);
        //convert into RGB
        Mat mRBG = new Mat();
        Imgproc.cvtColor(mRgba,mRBG,Imgproc.COLOR_RGBA2RGB);

        int height = mRBG.height();
        //Minimum size of face
        int absolutebodysize = (int)(height*0.1);

        MatOfRect body = new MatOfRect();
        if(cascadeClassifier != null){
            //                                              input output                             Minimum size of output
            cascadeClassifier.detectMultiScale(mRBG,body,1.1,2,2,new Size(absolutebodysize,absolutebodysize),new Size());
        }
        //Loop through all body
        Rect[] bodyArray = body.toArray();
        for(int i =0;i<bodyArray.length;i++){
            //draw body on original frame
            Imgproc.rectangle(mRgba,bodyArray[i].tl(),bodyArray[i].br(),new Scalar(0,255,0,255),2);
        }
        //rotate back original frame to -90 degree
        Core.flip(mRgba.t(),mRgba,0);
        return mRgba;
    }
}