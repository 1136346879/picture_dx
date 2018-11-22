/**
  * Copyright 2018 bejson.com 
  */
package com.example.administrator.kotlintest.entity.order.back;
import java.util.List;
import java.util.Objects;

/**
 * Auto-generated: 2018-10-17 11:27:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 *
 * 退货订单详情页bean
 */
public class JsonRootBean {

    private String product_pic;
    private String sku_code;
    private String product_name;
    private String sku_info;
    private int warehouse_id;
    private int buyyer_count;
    private int delivery_count;
    private int receive_count;
    private int refund_count;
    private String refund_id;
    private OrderRefund orderRefund;
    private String orderRefundProof;
    private String orderRefundRemark;
    private String refundOperationLog;
    private List<ListBaseAndRefund> listOrderRefundItems;
    private String listOrderItems;
    private Object listBaseAndRefund;
    private String orderExt;
    private List<OperateRemarkList> operateRemarkList;
    private double return_product_amount;
    private double return_shipping_fee;
    private int return_coupon_fee;
    private double refund_amount_final;
    private Object refundProofList;
    private Object authorityList;
    private String listAddressReceiver;
    private boolean is_returnable_freight;
    private String orderInvoice;
    private Object listOrderRefundRemark;
    public void setProduct_pic(String product_pic) {
         this.product_pic = product_pic;
     }
     public String getProduct_pic() {
         return product_pic;
     }

    public void setSku_code(String sku_code) {
         this.sku_code = sku_code;
     }
     public String getSku_code() {
         return sku_code;
     }

    public void setProduct_name(String product_name) {
         this.product_name = product_name;
     }
     public String getProduct_name() {
         return product_name;
     }

    public void setSku_info(String sku_info) {
         this.sku_info = sku_info;
     }
     public String getSku_info() {
         return sku_info;
     }

    public void setWarehouse_id(int warehouse_id) {
         this.warehouse_id = warehouse_id;
     }
     public int getWarehouse_id() {
         return warehouse_id;
     }

    public void setBuyyer_count(int buyyer_count) {
         this.buyyer_count = buyyer_count;
     }
     public int getBuyyer_count() {
         return buyyer_count;
     }

    public void setDelivery_count(int delivery_count) {
         this.delivery_count = delivery_count;
     }
     public int getDelivery_count() {
         return delivery_count;
     }

    public void setReceive_count(int receive_count) {
         this.receive_count = receive_count;
     }
     public int getReceive_count() {
         return receive_count;
     }

    public void setRefund_count(int refund_count) {
         this.refund_count = refund_count;
     }
     public int getRefund_count() {
         return refund_count;
     }

    public void setRefund_id(String refund_id) {
         this.refund_id = refund_id;
     }
     public String getRefund_id() {
         return refund_id;
     }

    public void setOrderRefund(OrderRefund orderRefund) {
         this.orderRefund = orderRefund;
     }
     public OrderRefund getOrderRefund() {
         return orderRefund;
     }

    public void setOrderRefundProof(String orderRefundProof) {
         this.orderRefundProof = orderRefundProof;
     }
     public String getOrderRefundProof() {
         return orderRefundProof;
     }

    public void setOrderRefundRemark(String orderRefundRemark) {
         this.orderRefundRemark = orderRefundRemark;
     }
     public String getOrderRefundRemark() {
         return orderRefundRemark;
     }

    public void setRefundOperationLog(String refundOperationLog) {
         this.refundOperationLog = refundOperationLog;
     }
     public String getRefundOperationLog() {
         return refundOperationLog;
     }

    public void setListOrderRefundItems(List<ListBaseAndRefund> listOrderRefundItems) {
         this.listOrderRefundItems = listOrderRefundItems;
     }
     public List<ListBaseAndRefund> getListOrderRefundItems() {
         return listOrderRefundItems;
     }

    public void setListOrderItems(String listOrderItems) {
         this.listOrderItems = listOrderItems;
     }
     public String getListOrderItems() {
         return listOrderItems;
     }

    public void setListBaseAndRefund(Object listBaseAndRefund) {
         this.listBaseAndRefund = listBaseAndRefund;
     }
     public Object getListBaseAndRefund() {
         return listBaseAndRefund;
     }

    public void setOrderExt(String orderExt) {
         this.orderExt = orderExt;
     }
     public String getOrderExt() {
         return orderExt;
     }

    public void setOperateRemarkList(List<OperateRemarkList> operateRemarkList) {
         this.operateRemarkList = operateRemarkList;
     }
     public List<OperateRemarkList> getOperateRemarkList() {
         return operateRemarkList;
     }

    public void setReturn_product_amount(double return_product_amount) {
         this.return_product_amount = return_product_amount;
     }
     public double getReturn_product_amount() {
         return return_product_amount;
     }

    public void setReturn_shipping_fee(Double return_shipping_fee) {
         this.return_shipping_fee = return_shipping_fee;
     }
     public Double getReturn_shipping_fee() {
         return return_shipping_fee;
     }

    public void setReturn_coupon_fee(int return_coupon_fee) {
         this.return_coupon_fee = return_coupon_fee;
     }
     public int getReturn_coupon_fee() {
         return return_coupon_fee;
     }

    public void setRefund_amount_final(double refund_amount_final) {
         this.refund_amount_final = refund_amount_final;
     }
     public double getRefund_amount_final() {
         return refund_amount_final;
     }

    public void setRefundProofList(String refundProofList) {
         this.refundProofList = refundProofList;
     }
     public Object getRefundProofList() {
         return refundProofList;
     }

    public void setAuthorityList(Object authorityList) {
         this.authorityList = authorityList;
     }
     public Object getAuthorityList() {
         return authorityList;
     }

    public void setListAddressReceiver(String listAddressReceiver) {
         this.listAddressReceiver = listAddressReceiver;
     }
     public String getListAddressReceiver() {
         return listAddressReceiver;
     }

    public void setIs_returnable_freight(boolean is_returnable_freight) {
         this.is_returnable_freight = is_returnable_freight;
     }
     public boolean getIs_returnable_freight() {
         return is_returnable_freight;
     }

    public void setOrderInvoice(String orderInvoice) {
         this.orderInvoice = orderInvoice;
     }
     public String getOrderInvoice() {
         return orderInvoice;
     }

    public void setListOrderRefundRemark(Object listOrderRefundRemark) {
         this.listOrderRefundRemark = listOrderRefundRemark;
     }
     public Object getListOrderRefundRemark() {
         return listOrderRefundRemark;
     }

}