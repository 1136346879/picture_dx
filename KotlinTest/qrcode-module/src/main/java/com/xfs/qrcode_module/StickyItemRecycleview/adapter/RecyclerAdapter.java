package com.xfs.qrcode_module.StickyItemRecycleview.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xfs.qrcode_module.R;
import com.xfs.qrcode_module.StickyItemRecycleview.bean.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdx on 2018/4/26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    /**
     * context
     */
    public Context mContext;

    /**
     * 集合
     */
    public List<data> mDatas = new ArrayList<>();
    /**
     * data
     */
    public data mData;

    public RecyclerAdapter(Context mContext, List<data> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    static class ViewHolde extends RecyclerView.ViewHolder {

        TextView txt;
        public ViewHolde(View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txt);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(mContext).inflate(R.layout.item_layout , parent ,false);
//        Log.d("aaa","onCreateViewHolder————"+viewType);
        if (viewType == 1){//标题
            item.setTag(true);
        }else{
            item.setTag(false);
        }
        return new ViewHolde(item);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  ViewHolde){
            mData =mDatas.get(position);
           ((ViewHolde) holder).txt. setText(mData.getName());
//            Log.d("aaa","onBindViewHolder————"+mData.getName());
           if (mData.getType() == 1){
               ((ViewHolde) holder).txt .setTextColor(Color.parseColor("#ff9500"));
               ((ViewHolde) holder).txt .setBackgroundColor(Color.parseColor("#dd000000"));
               ((ViewHolde) holder).txt .setTextSize(20);
           }
        }
    }

    @Override
    public int getItemViewType(int position) {
//        Log.d("aaa","getItemViewType————"+ mDatas.get(position).getType());
        return mDatas.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
