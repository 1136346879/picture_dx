package com.example.administrator.kotlintest.entity;

/**
 * Created by kangf on 2018/8/7.
 */
public class SavePayEntity {

    /**
     * msg : SUCCESS
     * errorCode : 0
     * sub_code : null
     * sub_msg : null
     * data : {"payId":2180000159110,"needPayAmount":1,"availableAccount":96625.91,"systemTime":"2018-08-08 12:00:45"}
     */

    private String msg;
    private int errorCode;
    private String sub_code;
    private String sub_msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * payId : 2180000159110
         * needPayAmount : 1.0
         * availableAccount : 96625.91
         * systemTime : 2018-08-08 12:00:45
         */

        private long payId;
        private double needPayAmount;
        private double availableAccount;
        private String systemTime;

        public long getPayId() {
            return payId;
        }

        public void setPayId(long payId) {
            this.payId = payId;
        }

        public double getNeedPayAmount() {
            return needPayAmount;
        }

        public void setNeedPayAmount(double needPayAmount) {
            this.needPayAmount = needPayAmount;
        }

        public double getAvailableAccount() {
            return availableAccount;
        }

        public void setAvailableAccount(double availableAccount) {
            this.availableAccount = availableAccount;
        }

        public String getSystemTime() {
            return systemTime;
        }

        public void setSystemTime(String systemTime) {
            this.systemTime = systemTime;
        }
    }
}
