package com.example.administrator.kotlintest.entity;

public enum SendType {

    //物流方式 private：自有物流  kd:快递 ky:快运  car:专车包车
    xfs_send("private","鑫方盛物流"),
    kd("kd","快递"),
    ky("ky","快运"),
    car("car","专车包车");
    private String sendType;
    private String sendTypeName;

    SendType(String sendType,String sendTypeName) {
        this.sendType = sendType;
        this.sendTypeName = sendTypeName;
    }

    public String getType() {
        return sendType;

    }
    public String getSendTypeName(){
        return sendTypeName;
    }


}
