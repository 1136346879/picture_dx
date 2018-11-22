package com.example.administrator.kotlintest.entity;


import java.util.List;

/**
 * 提交订单成功后返回的成功信息bean
 */
public class CommitSucessEntity {

        private String errorCode;
        private String errorMessage;
        private String tip;
        private String mayArrivedIn;
        private String orderCreatedTime;
        private String orderId;
        private String payName;
        private String payType;
        private String wannaArrivedAtBegin;
        private String wannaArrivedAtEnd;
    private List<AbnormalProductsBean> abnormalProducts;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }
        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
        public String getErrorMessage() {
            return errorMessage;
        }

        public void setMayArrivedIn(String mayArrivedIn) {
            this.mayArrivedIn = mayArrivedIn;
        }
        public String getMayArrivedIn() {
            return mayArrivedIn;
        }

        public void setOrderCreatedTime(String orderCreatedTime) {
            this.orderCreatedTime = orderCreatedTime;
        }
        public String getOrderCreatedTime() {
            return orderCreatedTime;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }
        public String getOrderId() {
            return orderId;
        }

        public void setPayName(String payName) {
            this.payName = payName;
        }
        public String getPayName() {
            return payName;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }
        public String getPayType() {
            return payType;
        }

        public void setWannaArrivedAtBegin(String wannaArrivedAtBegin) {
            this.wannaArrivedAtBegin = wannaArrivedAtBegin;
        }
        public String getWannaArrivedAtBegin() {
            return wannaArrivedAtBegin;
        }

        public void setWannaArrivedAtEnd(String wannaArrivedAtEnd) {
            this.wannaArrivedAtEnd = wannaArrivedAtEnd;
        }
        public String getWannaArrivedAtEnd() {
            return wannaArrivedAtEnd;
        }

    public List<AbnormalProductsBean> getAbnormalProducts() {
        return abnormalProducts;
    }

    public void setAbnormalProducts(List<AbnormalProductsBean> abnormalProducts) {
        this.abnormalProducts = abnormalProducts;
    }

    public static class AbnormalProductsBean {
        /**
         * code : 73
         * skuCode : 91004056001
         * skuId : 32301
         * skuName : 长城ChangCheng 绿平胶板
         * tip : 没有到货周期信息
         */

        private String code;
        private String skuCode;
        private String skuId;
        private String skuName;
        private String tip;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }

}
