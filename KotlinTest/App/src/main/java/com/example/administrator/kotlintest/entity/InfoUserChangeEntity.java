package com.example.administrator.kotlintest.entity;

import com.xfs.fsyuncai.entity.CouponEntity;

import java.io.Serializable;

public class InfoUserChangeEntity  implements Serializable{

    //资质文件  10 需要  20 不需要
    private int need;
    //是否是文件原件  10  是  20 否
    private int original;
    //份数
    private int num;



    //运费是否分摊  10 单开   20 分摊

    private int sendMoneyMore;

    //用户填写的备注
    private String bezhu;



    //发票信息
       //普通
    private int invoiceFlag;  //10不开  20  开
    private int invoicetype;   //10  增值  20 普税
    private  String invoiceTitle; //个人或单位名称
    private String invoiceNum; //纳税人识别号
    private String goodsDetail; //商品明细

        //增值发票
    private String registerAddress; //公司注册地址
    private String registerPhone;  //注册电话
    private String openBank;      //开户行
    private String bankAccount;   //银行账户
    private boolean yesOrNot = true;   //是否可以提交增值税

    private String showInvoice;

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public InfoUserChangeEntity() {
        //
    }

    public String getShowInvoice() {
        return showInvoice;
    }

    public void setShowInvoice(String showInvoice) {
        this.showInvoice = showInvoice;
    }

    //优惠券
    private String couponValue;
    private CouponEntity couponModel;

    public CouponEntity getCouponModel() {
        return couponModel;
    }

    public void setCouponModel(CouponEntity couponModel) {
        this.couponModel = couponModel;
    }

    //支付配送信息
    private String onlinePay;
    private String sendType;
    private String sendBeginTime;
    private String sendEndTime;
    private boolean isOnlyAtChoice;
    private int isOnlyAt;
    private String deliverWay;
    private String formatSendTime;

    public String getFormatSendTime() {
        return formatSendTime;
    }

    public void setFormatSendTime(String formatSendTime) {
        this.formatSendTime = formatSendTime;
    }



    public String getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(String deliverWay) {
        this.deliverWay = deliverWay;
    }

    public int getIsOnlyAt() {
        return isOnlyAt;
    }

    public void setIsOnlyAt(int isOnlyAt) {
        this.isOnlyAt = isOnlyAt;
    }

    public boolean isOnlyAtChoice() {
        return isOnlyAtChoice;
    }

    public void setOnlyAtChoice(boolean onlyAtChoice) {
        this.isOnlyAtChoice = onlyAtChoice;
    }

    public void setSendBeginTime(String sendBeginTime) {
        this.sendBeginTime = sendBeginTime;
    }

    public String getSendEndTime() {
        return sendEndTime;
    }

    public void setSendEndTime(String sendEndTime) {
        this.sendEndTime = sendEndTime;
    }

    public void setCouponValue(String couponValue) {
        this.couponValue = couponValue;
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

    public String getSendBeginTime() {
        return sendBeginTime;
    }

    public void setSendTime(String sendTime) {
        this.sendBeginTime = sendTime;
    }

    public boolean isYesOrNot() {
        return yesOrNot;
    }

    public void setYesOrNot(boolean yesOrNot) {
        this.yesOrNot = yesOrNot;
    }


    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

    public String getRegisterPhone() {
        return registerPhone;
    }

    public void setRegisterPhone(String registerPhone) {
        this.registerPhone = registerPhone;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getInvoiceFlag() {
        return invoiceFlag;
    }

    public void setInvoiceFlag(int invoiceFlag) {
        this.invoiceFlag = invoiceFlag;
    }

    public int getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(int invoicetype) {
        this.invoicetype = invoicetype;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(String invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public String getBezhu() {
        return bezhu;
    }

    public void setBezhu(String bezhu) {
        this.bezhu = bezhu;
    }

    public int getSendMoneyMore() {
        if(sendMoneyMore ==20){
            return 20;
        }
        return 10;
    }

    public void setSendMoneyMore(int sendMoneyMore) {
        this.sendMoneyMore = sendMoneyMore;
    }

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    public int getOriginal() {
        return original;
    }

    public void setOriginal(int original) {
        this.original = original;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setCouposeValue(String i) {
        this.couponValue= i;
    }
    public String getCouponValue(){
        return couponValue;
    }
}
