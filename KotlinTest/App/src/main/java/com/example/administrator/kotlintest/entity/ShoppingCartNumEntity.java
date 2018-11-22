package com.example.administrator.kotlintest.entity;

/**
 * Created by kangf on 2018/8/2.
 */
public class ShoppingCartNumEntity {


    /**
     * errorMessage : 调用检查token接口成功!
     * errorCode : 1
     * result1 : {"errorMessage":"查询采购单成功","errorCode":0,"cartCount":"11"}
     * token : eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJqd3QiLCJpYXQiOjE1MzMyMTQyMTQsInN1YiI6IntcImxvZ2luQWNjb3VudFwiOlwiZGV2b3JkZXIxMjNcIixcImxvZ2luRmxhZ1wiOnRydWUsXCJtZW1iZXJJZFwiOlwiMTAyNjBcIn0iLCJleHAiOjE1MzMyMTc4MTR9.yeT6fKY-bFIqCzlnzzKWccNN3k6AWqHXGQZmSkNmQ1I
     * memberId : 10260
     */

    private String errorMessage;
    private int errorCode;
    private ResultBean result1;
    private String token;
    private String memberId;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public ResultBean getResult1() {
        return result1;
    }

    public void setResult1(ResultBean result1) {
        this.result1 = result1;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public static class ResultBean {
        /**
         * errorMessage : 查询采购单成功
         * errorCode : 0
         * cartCount : 11
         */

        private String errorMessage;
        private int errorCode;
        private String cartCount;

        public String getErrorMessage() {
            return errorMessage;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public String getCartCount() {
            return cartCount;
        }

        public void setCartCount(String cartCount) {
            this.cartCount = cartCount;
        }
    }
}
