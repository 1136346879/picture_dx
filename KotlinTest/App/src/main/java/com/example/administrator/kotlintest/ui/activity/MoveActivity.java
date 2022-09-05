package com.example.administrator.kotlintest.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

import com.example.administrator.kotlintest.R;


public class MoveActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
//        ((SlideView) findViewById(R.id.slider5)).setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
//            @Override
//            public void onSlideComplete(SlideView slideView) {
//                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                vibrator.vibrate(100);
//                slideView.setEnabled(false);
//                slideView.setText("Disabled");
//            }
//        });
//
//        SlideView slideView = (SlideView) findViewById(R.id.slider1);
//        slideView.setOnSlideCompleteListener(new SlideView.OnSlideCompleteListener() {
//            @Override
//            public void onSlideComplete(SlideView slideView) {
//                // vibrate the device
//                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//                vibrator.vibrate(100);
//
//                // go to a new activity
//                Toast.makeText(MoveActivity.this,"滑动完成",0).show();
//            }
//        });
    }
}
