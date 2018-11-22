package com.example.administrator.kotlintest.entity;

import java.io.Serializable;

public class InvoiceEntity implements Serializable {


    /**
     * msg : SUCCESS
     * errorCode : 0
     * sub_code : null
     * sub_msg : null
     * data : {"company_name":"豆豆","member_id":37,"invoice_type":10,"taxpayer_code":"123456","reg_address":"北京市大兴区大兴区黄村镇华师开始扩大苛近段时间","reg_telephone":"1380000000001","open_account_bank":"中国建设银行分行","bank_account":"6623236512121212121212","create_time":"2018-08-27 09:22:51","update_time":"2018-09-13 19:47:48"}
     */

    private String msg;
    private int errorCode;
    private Object sub_code;
    private Object sub_msg;
    private DataBean data;
    private String invoiceType;

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

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

    public Object getSub_code() {
        return sub_code;
    }

    public void setSub_code(Object sub_code) {
        this.sub_code = sub_code;
    }

    public Object getSub_msg() {
        return sub_msg;
    }

    public void setSub_msg(Object sub_msg) {
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
         * company_name : 豆豆
         * member_id : 37
         * invoice_type : 10
         * taxpayer_code : 123456
         * reg_address : 北京市大兴区大兴区黄村镇华师开始扩大苛近段时间
         * reg_telephone : 1380000000001
         * open_account_bank : 中国建设银行分行
         * bank_account : 6623236512121212121212
         * create_time : 2018-08-27 09:22:51
         * update_time : 2018-09-13 19:47:48
         */

        private String company_name;
        private int member_id;
        private int invoice_type;
        private String taxpayer_code;
        private String reg_address;
        private String reg_telephone;
        private String open_account_bank;
        private String bank_account;
        private String create_time;
        private String update_time;

        public String getCompany_name() {
            return company_name==null?"":company_name;
        }

        public void setCompany_name(String company_name) {
            this.company_name = company_name;
        }

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public int getInvoice_type() {
            return invoice_type;
        }

        public void setInvoice_type(int invoice_type) {
            this.invoice_type = invoice_type;
        }

        public String getTaxpayer_code() {
            return taxpayer_code == null?"":taxpayer_code;
        }

        public void setTaxpayer_code(String taxpayer_code) {
            this.taxpayer_code = taxpayer_code;
        }

        public String getReg_address() {
            return reg_address == null?"":reg_address;
        }

        public void setReg_address(String reg_address) {
            this.reg_address = reg_address;
        }

        public String getReg_telephone() {
            return reg_telephone == null?"":reg_telephone;
        }

        public void setReg_telephone(String reg_telephone) {
            this.reg_telephone = reg_telephone;
        }

        public String getOpen_account_bank() {
            return open_account_bank == null?"":open_account_bank;
        }

        public void setOpen_account_bank(String open_account_bank) {
            this.open_account_bank = open_account_bank;
        }

        public String getBank_account() {
            return bank_account == null?"":bank_account;
        }

        public void setBank_account(String bank_account) {
            this.bank_account = bank_account;
        }

        public String getCreate_time() {
            return create_time== null?"":create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getUpdate_time() {
            return update_time== null?"":update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
