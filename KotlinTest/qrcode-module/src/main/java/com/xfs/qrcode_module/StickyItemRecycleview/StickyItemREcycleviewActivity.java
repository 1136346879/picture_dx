package com.xfs.qrcode_module.StickyItemRecycleview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xfs.qrcode_module.R;
import com.xfs.qrcode_module.StickyItemRecycleview.adapter.RecyclerAdapter;
import com.xfs.qrcode_module.StickyItemRecycleview.bean.data;
import com.xfs.qrcode_module.StickyItemRecycleview.view.StickyItemDecoration;

/**
 * RecycleView列表带吸附的标题
 */
public class StickyItemREcycleviewActivity extends AppCompatActivity{


    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_sticky_item);
        mRecyclerView  =findViewById(R.id.recyclelist);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new StickyItemDecoration());
        mRecyclerView.setAdapter(new RecyclerAdapter(this, data.getDataList()));

    }

}
