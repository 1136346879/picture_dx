package com.example.administrator.kotlintest.area;

import android.content.Context;
import androidx.core.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.adapter.BaseRvAdapter;
import com.example.administrator.kotlintest.entity.address.AddressAreaEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by HaoBoy on 2018/8/15.
 */

public class AreaSelectorAdapter extends BaseRvAdapter<AddressAreaEntity.ListBean> {

    private Context context;

    public AreaSelectorAdapter(ArrayList<AddressAreaEntity.ListBean> mDatas, Context context) {
        super(mDatas, R.layout.item_area_selector, context);
        this.context = context;
    }


    @Override
    public void onBindView(@NotNull  BaseRvAdapter.Companion.BaseRvHolder holder, final AddressAreaEntity.ListBean data) {
        ImageView ivItemArea = holder.findViewById(R.id.ivItemArea);
        TextView tvItemArea = holder.findViewById(R.id.tvItemArea);
        tvItemArea.setText(data.getName());
        if (data.isChecked()){
            tvItemArea.setTextColor(ContextCompat.getColor(context,R.color.tab_indicator_color));
            ivItemArea.setVisibility(View.VISIBLE);
        }else {
            tvItemArea.setTextColor(ContextCompat.getColor(context,R.color.text_color_light));
            ivItemArea.setVisibility(View.INVISIBLE);
        }
    }

    public void notifyItemByPosition(int position){
        notifyItemChanged(position);
    }
}
