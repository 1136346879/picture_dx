package com.example.administrator.kotlintest.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;
import com.example.baselibrary.widgets.ToastUtilKt;

public class YingActivity extends Activity {



        Double x1;
        Double x2;
        Double y1;
        Double y2;

        @Override
        protected void onCreate (@Nullable Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ying);
            final EditText shurushuzi = findViewById(R.id.shurushuzi);
            shurushuzi.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
            final TextView result = findViewById(R.id.result);
            TextView doit = findViewById(R.id.doit);

            doit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (shurushuzi.getText().toString().trim().equals("")
                            ||  Double.valueOf(shurushuzi.getText().toString().trim())<0.0
                            ||  Double.valueOf(shurushuzi.getText().toString().trim())>4.0) {
                        ToastUtilKt.INSTANCE.showCustomToast("请输入合法内容");
                        return;
                    }
                    Double x = Double.valueOf(shurushuzi.getText().toString().trim());
                    getX1AndX2(x);

                    Double resu = y1 + (y2 - y1) * (x - x1) / (x2 - x1);


                    result.setText("结果是：  " + resu.toString());
                }
            });


        }


        private void getX1AndX2 (Double x){
            if (0 <= x && x <= 0.5) {
                x1 = 0.0;
                x2 = 0.5;
                y1 = 0.45;
                y2 = 0.65;
            } else if (0.5 < x && x <= 1) {
                x1 = 0.5;
                x2 = 1.0;
                y1 = 0.65;
                y2 = 0.81;
            } else if (1 < x && x <= 2) {
                x1 = 1.0;
                x2 = 2.0;
                y1 = 0.81;
                y2 = 0.9;
            } else if (2 < x && x <= 3) {
                x1 = 2.0;
                x2 = 3.0;
                y1 = 0.9;
                y2 = 1.0;
            } else if (3 < x && x <= 4) {
                x1 = 3.0;
                x2 = 4.0;
                y1 = 1.0;
                y2 = 1.04;
            }else{
                ToastUtilKt.INSTANCE.showCustomToast("请输入0-4以内的小数");
                return;
            }
        }
    }
