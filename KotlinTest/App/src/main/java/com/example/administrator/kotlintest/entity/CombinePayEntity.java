package com.example.administrator.kotlintest.entity;

/**
 * Created by kangf on 2018/8/8.
 */
public class CombinePayEntity {


    /**
     * msg : SUCCESS
     * errorCode : 0
     * sub_code : null
     * sub_msg : null
     * data : app_id=2018011701925663&biz_content=%7B%22out_trade_no%22%3A2180000171111%2C%22total_amount%22%3A0.01%2C%22subject%22%3A%22%E9%91%AB%E6%96%B9%E7%9B%9B%E5%95%86%E5%93%81%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=https%3A%2F%2Ft.fsyuncai.com%2Fpayment%2Fali_pay_async_notice&sign_type=RSA2&timestamp=2018-08-08+13%3A25%3A57&version=1.0&sign=ZJ0dNcqsdu%2FmwoxQkHAJGIxhE9NgLUzXJtOmGvLjUUFpSjuUrKU13D4qRyLhyeSoJ7Lk%2B8ZHRA42nVDJyMBTreympXHxdXv%2Fttu%2BLv7inQ3eiCF7mqzqdpTqbi5re81bYofI24Ii4eE4%2BxpXEY0t7Ug9T9tWFeitta3qRB2UxK6MBqUkkmzsT%2FWNr2cb1X%2B7F6S6yCgf1ieFK3LWsszrc09ikgRA8HQsv0XjeifZur9U5dPb%2FpY59b5i%2BbCMKwmbS6OJfxCcITKm4YFjKVEm%2BslGtFJbdO6%2FEx8YLPzfGEAZlvpkgCSci3fF%2BR6Ogam7FI8dDbAyM8%2FGdmKzaGcLvQ%3D%3D
     */

    private String msg;
    private int errorCode;
    private String sub_code;
    private String sub_msg;
    private String data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getSub_code() {
        return sub_code;
    }

    public void setSub_code(String sub_code) {
        this.sub_code = sub_code;
    }

    public String getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(String sub_msg) {
        this.sub_msg = sub_msg;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
