package com.example.administrator.kotlintest.dateyearmonthday;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.kotlintest.R;
import com.example.baselibrary.widgets.ToastUtilKt;

import java.util.List;

/**
 * Created by lirl on 2017-09-29.
 */
public class CalendarAdapter extends BaseAdapter {
    private List<DateInfo> list = null;
    private Context context = null;
    private int selectedPosition = -1;

    AttendviewActivity activity;
    private String toastTime;

    public CalendarAdapter(AttendviewActivity activity, List<DateInfo> list) {
        this.context = activity;
        this.list = list;
        this.activity = activity;

    }

    public List<DateInfo> getList() {
        return list;
    }

    public void setList(List<DateInfo> _list) {
        list = _list;
    }

    public int getCount() {
        return list == null ? 0 : list.size();
    }

    public Object getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    /**
     * 设置选中位置
     */
    public void setSelectedPosition(int position) {
        selectedPosition = position;
    }

    /**
     * 产生一个view
     */
    public View getView(int position, View convertView, ViewGroup group) {
        //通过viewholder做一些优化
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_attendview, null);
            viewHolder.date = (TextView) convertView.findViewById(R.id.item_date);
            viewHolder.itemLayout = (RelativeLayout) convertView.findViewById(R.id.item_layout);
            viewHolder.nongliDate = (TextView) convertView.findViewById(R.id.item_nongli_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //根据数据源设置单元格的字体颜色、背景等
        viewHolder.date.setText(list.get(position).getDate() + "");
        String longlidate = list.get(position).getNongliDate();
        viewHolder.nongliDate.setText(longlidate);
        //if (selectedPosition == position) {viewHolder.date.setTextColor(Color.WHITE);viewHolder.nongliDate.setTextColor(Color.WHITE);convertView.setBackgroundColor(Color.RED);} else {
        //convertView.setBackgroundResource(R.drawable.item_bkg);
        viewHolder.date.setTextColor(Color.BLACK);
        viewHolder.nongliDate.setTextColor(Color.BLACK);
        if (longlidate.contains("出勤") || longlidate.contains("公出") || longlidate.contains("带薪") || longlidate.contains("旅游假")) {
//            viewHolder.nongliDate.setTextColor(Color.GREEN);
        }
        if (longlidate.contains("假") || longlidate.contains("旷工")) {
//            viewHolder.nongliDate.setTextColor(Color.RED);
        }

        //if (list.get(position).isHoliday()) {viewHolder.nongliDate.setTextColor(Color.BLUE);}
        if (list.get(position).isThisMonth() == false) {
            viewHolder.date.setTextColor(Color.rgb(210, 210, 210));
        }
        //if (list.get(position).isWeekend()) {viewHolder.date.setTextColor(Color.rgb(255, 97, 0));}}

        if (list.get(position).getNongliDate().length() > 3)
            viewHolder.nongliDate.setTextSize(10);
        if (list.get(position).getNongliDate().length() >= 5)
            viewHolder.nongliDate.setTextSize(8);
        convertView.setLayoutParams(new GridView.LayoutParams(LayoutParams.WRAP_CONTENT, DataUtil.getScreenWidth(activity) / 7));

        viewHolder.itemLayout.setOnClickListener(v -> {

            if (list.get(position).isThisMonth()) {
                toastTime = "当月";
            } else {
                if (list.get(position).getDate() > 20) {
                    toastTime = "上月";

                } else if (list.get(position).getDate() < 10) {
                    toastTime = "下月";
                }
            }
            ToastUtilKt.INSTANCE.showCustomToast("点击de日期-->" + toastTime + list.get(position).getDate() + "号");
        });
        return convertView;
    }

    private class ViewHolder {
        TextView date;
        TextView nongliDate;
        RelativeLayout itemLayout;
    }
}
