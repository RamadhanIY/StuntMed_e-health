package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class CheckStuntingActivity extends AppCompatActivity {

    EditText Et;
    TextView tv;
    ImageView iv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkstunting);

        Iv = (ImageView)findViewById(R.id.picttButton);

        if(!Python.isStarted()){
            Python.start(new AndroidPlatform(this));

            Python py = Python.getInstance();
            PyObject pyobj = py.getModule("FaceBodyDetect");


            //LOM TAU
//            //Reference to UI elements
//            PyObject obj=null;
//            iv.setOnClickListener((new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    obj = pyobj.callAttr("main", )
//                }
//            }));

        }
    }
}