package com.example.administrator.kotlintest.entity;

public enum PayType {


    online_pay("1001", "在线支付"),
    bank_pay("1301", "银行转账"),
    draft_pay("1401", "银行汇票"),
    account_pay("1201", "账期支付"),
    delivery_pay("1101", "货到付款");

    private String payType;
    private String payTypeName;

    PayType(String payType, String payTypeName) {
        this.payType = payType;
        this.payTypeName = payTypeName;
    }

    public String getPayTypeName() {
        return payTypeName;

    }

    public String getPayType() {
        return payType;
    }

}