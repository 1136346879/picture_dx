package com.example.administrator.kotlintest.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.widget.ToastUtilKt;

public class RuanActivity extends Activity {
    Double x1;
    Double x2;
    Double y1;
    Double y2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rauan);
        final EditText shurushuzi = findViewById(R.id.shurushuzi);
        shurushuzi.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        final TextView result = findViewById(R.id.result);
        TextView doit = findViewById(R.id.doit);

        doit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (shurushuzi.getText().toString().trim().equals("")

                        ||  Double.valueOf(shurushuzi.getText().toString().trim())<0.0
                        ||  Double.valueOf(shurushuzi.getText().toString().trim())>8.0) {
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


    private void getX1AndX2(Double x) {
        if (0 <= x && x <= 0.5) {
            x1 = 0.0;
            x2 = 0.5;
            y1 = 0.6;
            y2 = 0.8;
        } else if (0.5 < x && x <= 1) {
            x1 = 0.5;
            x2 = 1.0;
            y1 = 0.8;
            y2 = 0.95;
        } else if (1 < x && x <= 2) {
            x1 = 1.0;
            x2 = 2.0;
            y1 = 0.95;
            y2 = 1.18;
        } else if (2 < x && x <= 3) {
            x1 = 2.0;
            x2 = 3.0;
            y1 = 1.18;
            y2 = 1.35;
        } else if (3 < x && x <= 4) {
            x1 = 3.0;
            x2 = 4.0;
            y1 = 1.35;
            y2 = 1.48;
        } else if (4 < x && x <= 5) {
            x1 = 4.0;
            x2 = 5.0;
            y1 = 1.48;
            y2 = 1.57;
        } else if (5 < x && x <= 6) {
            x1 = 5.0;
            x2 = 6.0;
            y1 = 1.57;
            y2 = 1.63;
        } else if (6 < x && x <= 7) {
            x1 = 6.0;
            x2 = 7.0;
            y1 = 1.63;
            y2 = 1.66;
        } else if (7 < x && x <= 8) {
            x1 = 7.0;
            x2 = 8.0;
            y1 = 1.66;
            y2 = 1.7;
        }else{
            ToastUtilKt.INSTANCE.showCustomToast("请输入0-8以内的小数");
            return;
        }
    }
}
