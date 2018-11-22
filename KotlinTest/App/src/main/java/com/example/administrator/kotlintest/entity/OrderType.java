package com.example.administrator.kotlintest.entity;

public enum OrderType {

    WILL_CHECK("9"),//待审核
    WILL_PAY("10"),//待支付
    ALL_ORDER(""),//全部订单
    WILL_SEND("50"),//待发货
    WILL_RECEIVER("60"),//待收货
    HAS_RECEICERED_WILL_BALANCE("70"),//已收货待结算
    SUCESS_ORDER("80"),//订单成功
    CANCEL_ORDER("100"),//取消
    CLOSE_ORDER("110"),//订单关闭
    ALL("");//全部

    private String orderType;

    OrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getType() {
        return orderType;
    }
}