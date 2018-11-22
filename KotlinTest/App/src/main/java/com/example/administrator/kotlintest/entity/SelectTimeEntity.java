package com.example.administrator.kotlintest.entity;

import java.io.Serializable;

public class SelectTimeEntity implements Serializable {

    private String year_month_day;//展示时间
    private String today;//向后台提交时间
    private String am;
    private String timeType;
    private boolean checkBox;
    private String limitLine;
    private String onlinePay;
    private String sendType;
    private String deliverWay;
    private String totalFreight;

    public String getYear_month_day() {
        return year_month_day;
    }

    public void setYear_month_day(String year_month_day) {
        this.year_month_day = year_month_day;
    }

    public String getTotalFreight() {
        return totalFreight;
    }

    public void setTotalFreight(String totalFreight) {
        this.totalFreight = totalFreight;
    }

    public String getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(String deliverWay) {
        this.deliverWay = deliverWay;
    }

    public String getOnlinePay() {
        return onlinePay;
    }

    public void setOnlinePay(String onlinePay) {
        this.onlinePay = onlinePay;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getLimitLine() {
        return limitLine;
    }

    public void setLimitLine(String limitLine) {
        this.limitLine = limitLine;
    }

    public String getToday() {
        if(today!=null){

            if(today.contains("")){
              today = today.replace("月","-");
            };
            if(today.contains("日")){
                today =  today.replace("日","");
            }
            if(today.contains("年")){
                today =  today.replace("年","-");
            }
        }

        return today == null ?"":today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getAm() {
        return am == null ? "" : am;
    }

    public void setAm(String am) {
        if ("am".equals(am) || "08:00-12:00".equals(am)) {
            am = "08:00-12:00";
        } else if ("pm".equals(am) || "12:00-17:00".equals(am)) {
            am = "12:00-17:00";
        } else if("night".equals(am) || "17:00-22:00".equals(am)){
            am = "17:00-22:00";
        }else{
            am =  "00:00-03:00";
        }
        this.am = am;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public boolean isCheckBox() {
        return checkBox;
    }

    public void setCheckBox(boolean checkBox) {
        this.checkBox = checkBox;
    }
}
