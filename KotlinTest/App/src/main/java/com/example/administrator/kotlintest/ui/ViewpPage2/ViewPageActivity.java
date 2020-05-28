package com.example.administrator.kotlintest.ui.ViewpPage2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.administrator.kotlintest.R;

public class ViewPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage);
        Button btnHorizontalScrolling = findViewById(R.id.btn_horizontal_scrolling);
        btnHorizontalScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HorizontalScrollingActivity.start(ViewPageActivity.this);
            }
        });
        Button btnVerticalScrolling = findViewById(R.id.button_vertical_scrolling);
        btnVerticalScrolling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerticalScrollingActivity.start(ViewPageActivity.this);
            }
        });
        Button btnFragmentStateAdapter = findViewById(R.id.button_fragment_state_adpater);
        btnFragmentStateAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentStateAdapterActivity.start(ViewPageActivity.this);
            }
        });
    }
}
