package com.xfs.qrcode_module.StickyItemRecycleview.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdx on 2018/4/26.
 */

public class data {
    /**
     * 名字
     */
    private String name;
    /**
     * 子项类型
     * 1: 标题
     * 2：普通
     */
    private int type;
    public data(String name, int type) {
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
    /**
     * 存储的数据列表
     */
    private static List<data> mDataList;
    public static List<data> getDataList() {
        if (mDataList == null){
            mDataList = new ArrayList<>();

            data bean1 = new data("今天",1);
            mDataList.add(bean1);
            mDataList.addAll(foods("推送新闻标题"));

            data bean2 = new data("明天",1);
            mDataList.add(bean2);
            mDataList.addAll(foods("推送新闻"));

            data bean3 = new data("后天",1);
            mDataList.add(bean3);
            mDataList.addAll(foods("推送~"));

            data bean4 = new data("更早",1);
            mDataList.add(bean4);
            mDataList.addAll(foods("太早了"));

        }
        return mDataList;
    }
    /**
     * 循环存储食物名字
     * @param foodName
     * @return
     */
    private static List<data> foods(String foodName){
        List<data> foods =new ArrayList<>();
        data food = null;
        for (int i = 0 ; i < 10 ; i++){
            food = new data(foodName+"("+i+")",2);
            foods.add(food);
        }
        return foods;
    }
}
