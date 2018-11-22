package com.example.administrator.kotlintest.entity;

import java.util.List;

public class MyOrderAuditEntity {


    /**
     * msg : SUCCESS
     * errorCode : 0
     * sub_code : null
     * sub_msg : null
     * data : {"pageStoreOrderPojoList":{"totalPage":1,"pageSize":8,"pageNumber":1,"totalRecord":2,"startIndex":0,"result":[{"order_id":"18100785563","created_at":"2018-10-23 10:55:35","order_status":9,"paid_type":1301,"final_paid_type":"","paid_amount":27.8,"listOrderItems":[{"id":25933,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":17261,"cost_price":3.35,"original_price":4.6,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":4.6,"sale_price":4.6,"sku_code":"91002052008","sku_weight":57.5,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"38穿心 12*0.5","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":0.36,"unit_name":"根","product_code":"07.02.01.075724","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":57.5,"sku_height":2,"sku_volume":15},{"id":25934,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":17258,"cost_price":5.2,"original_price":7.2,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":7.2,"sale_price":7.2,"sku_code":"91002052005","sku_weight":57.5,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"38主 12*0.8","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":0.56,"unit_name":"根","product_code":"07.02.01.075721","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":57.5,"sku_height":2,"sku_volume":15},{"id":25935,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":4928,"cost_price":10.26,"original_price":14,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":14,"sale_price":14,"sku_code":"91002052002","sku_weight":0.025,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"50主 15*1.2","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":1.08,"unit_name":"根","product_code":"07.02.01.056086","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}],"listOrderVerifyRecord":[{"order_id":"18100785563","member_id":546,"account_type":10,"login_account":"beijing005","verify_status":0,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100785563","member_id":547,"account_type":30,"login_account":"beijing006","verify_status":10,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100785563","member_id":545,"account_type":20,"login_account":"beijing004","verify_status":0,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"}],"current_time":1540266621842,"limit_time":1540868135000,"is_show":false,"audit_button_show":false,"authorityList":[2],"paid_button_show":false,"order_total_count":3,"cancle_apply_show":true,"final_sent_type":20,"buy_again_button":true},{"order_id":"18100769563","created_at":"2018-10-22 17:43:18","order_status":9,"paid_type":1001,"final_paid_type":"","paid_amount":9.66,"listOrderItems":[{"id":25874,"order_id":"18100769563","member_id":545,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2138,"sku_id":20246,"cost_price":0.54,"original_price":0.7,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":0.7,"sale_price":0.7,"sku_code":"91002138020","sku_weight":0.025,"color":"蓝色","product_name":"锌丝杆","sku_info":"6mm*1m","product_pic":"http://192.168.0.107/images/009974a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":2.43,"unit_name":"根","product_code":"03.13.01.009973","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":1,"occupy_virtual_store":0,"arrival_cycle":15,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15},{"id":25875,"order_id":"18100769563","member_id":545,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2138,"sku_id":1896,"cost_price":1.08,"original_price":1.46,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":1.46,"sale_price":1.46,"sku_code":"91002138001","sku_weight":0.025,"color":"蓝色","product_name":"锌丝杆","sku_info":"6mm*2m","product_pic":"http://192.168.0.107/images/009974a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":5.07,"unit_name":"根","product_code":"03.13.01.009985","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":1,"occupy_virtual_store":0,"arrival_cycle":15,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}],"listOrderVerifyRecord":[{"order_id":"18100769563","member_id":546,"account_type":10,"login_account":"beijing005","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":547,"account_type":10,"login_account":"beijing006","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":545,"account_type":30,"login_account":"beijing004","verify_status":10,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":545,"account_type":20,"login_account":"beijing004","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"}],"current_time":1540266621892,"limit_time":1540287798000,"is_show":false,"audit_button_show":true,"authorityList":[3],"paid_button_show":false,"order_total_count":2,"cancle_apply_show":true,"final_sent_type":10,"buy_again_button":true}]},"tab_audit_show":true}
     */

    private String msg;
    private int errorCode;
    private Object sub_code;
    private Object sub_msg;
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
         * pageStoreOrderPojoList : {"totalPage":1,"pageSize":8,"pageNumber":1,"totalRecord":2,"startIndex":0,"result":[{"order_id":"18100785563","created_at":"2018-10-23 10:55:35","order_status":9,"paid_type":1301,"final_paid_type":"","paid_amount":27.8,"listOrderItems":[{"id":25933,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":17261,"cost_price":3.35,"original_price":4.6,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":4.6,"sale_price":4.6,"sku_code":"91002052008","sku_weight":57.5,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"38穿心 12*0.5","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":0.36,"unit_name":"根","product_code":"07.02.01.075724","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":57.5,"sku_height":2,"sku_volume":15},{"id":25934,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":17258,"cost_price":5.2,"original_price":7.2,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":7.2,"sale_price":7.2,"sku_code":"91002052005","sku_weight":57.5,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"38主 12*0.8","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":0.56,"unit_name":"根","product_code":"07.02.01.075721","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":57.5,"sku_height":2,"sku_volume":15},{"id":25935,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":4928,"cost_price":10.26,"original_price":14,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":14,"sale_price":14,"sku_code":"91002052002","sku_weight":0.025,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"50主 15*1.2","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":1.08,"unit_name":"根","product_code":"07.02.01.056086","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}],"listOrderVerifyRecord":[{"order_id":"18100785563","member_id":546,"account_type":10,"login_account":"beijing005","verify_status":0,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100785563","member_id":547,"account_type":30,"login_account":"beijing006","verify_status":10,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100785563","member_id":545,"account_type":20,"login_account":"beijing004","verify_status":0,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"}],"current_time":1540266621842,"limit_time":1540868135000,"is_show":false,"audit_button_show":false,"authorityList":[2],"paid_button_show":false,"order_total_count":3,"cancle_apply_show":true,"final_sent_type":20,"buy_again_button":true},{"order_id":"18100769563","created_at":"2018-10-22 17:43:18","order_status":9,"paid_type":1001,"final_paid_type":"","paid_amount":9.66,"listOrderItems":[{"id":25874,"order_id":"18100769563","member_id":545,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2138,"sku_id":20246,"cost_price":0.54,"original_price":0.7,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":0.7,"sale_price":0.7,"sku_code":"91002138020","sku_weight":0.025,"color":"蓝色","product_name":"锌丝杆","sku_info":"6mm*1m","product_pic":"http://192.168.0.107/images/009974a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":2.43,"unit_name":"根","product_code":"03.13.01.009973","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":1,"occupy_virtual_store":0,"arrival_cycle":15,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15},{"id":25875,"order_id":"18100769563","member_id":545,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2138,"sku_id":1896,"cost_price":1.08,"original_price":1.46,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":1.46,"sale_price":1.46,"sku_code":"91002138001","sku_weight":0.025,"color":"蓝色","product_name":"锌丝杆","sku_info":"6mm*2m","product_pic":"http://192.168.0.107/images/009974a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":5.07,"unit_name":"根","product_code":"03.13.01.009985","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":1,"occupy_virtual_store":0,"arrival_cycle":15,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}],"listOrderVerifyRecord":[{"order_id":"18100769563","member_id":546,"account_type":10,"login_account":"beijing005","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":547,"account_type":10,"login_account":"beijing006","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":545,"account_type":30,"login_account":"beijing004","verify_status":10,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":545,"account_type":20,"login_account":"beijing004","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"}],"current_time":1540266621892,"limit_time":1540287798000,"is_show":false,"audit_button_show":true,"authorityList":[3],"paid_button_show":false,"order_total_count":2,"cancle_apply_show":true,"final_sent_type":10,"buy_again_button":true}]}
         * tab_audit_show : true
         */

        private PageStoreOrderPojoListBean pageStoreOrderPojoList;
        private boolean tab_audit_show;

        public PageStoreOrderPojoListBean getPageStoreOrderPojoList() {
            return pageStoreOrderPojoList;
        }

        public void setPageStoreOrderPojoList(PageStoreOrderPojoListBean pageStoreOrderPojoList) {
            this.pageStoreOrderPojoList = pageStoreOrderPojoList;
        }

        public boolean isTab_audit_show() {
            return tab_audit_show;
        }

        public void setTab_audit_show(boolean tab_audit_show) {
            this.tab_audit_show = tab_audit_show;
        }

        public static class PageStoreOrderPojoListBean {
            /**
             * totalPage : 1
             * pageSize : 8
             * pageNumber : 1
             * totalRecord : 2
             * startIndex : 0
             * result : [{"order_id":"18100785563","created_at":"2018-10-23 10:55:35","order_status":9,"paid_type":1301,"final_paid_type":"","paid_amount":27.8,"listOrderItems":[{"id":25933,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":17261,"cost_price":3.35,"original_price":4.6,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":4.6,"sale_price":4.6,"sku_code":"91002052008","sku_weight":57.5,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"38穿心 12*0.5","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":0.36,"unit_name":"根","product_code":"07.02.01.075724","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":57.5,"sku_height":2,"sku_volume":15},{"id":25934,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":17258,"cost_price":5.2,"original_price":7.2,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":7.2,"sale_price":7.2,"sku_code":"91002052005","sku_weight":57.5,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"38主 12*0.8","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":0.56,"unit_name":"根","product_code":"07.02.01.075721","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":57.5,"sku_height":2,"sku_volume":15},{"id":25935,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":4928,"cost_price":10.26,"original_price":14,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":14,"sale_price":14,"sku_code":"91002052002","sku_weight":0.025,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"50主 15*1.2","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":1.08,"unit_name":"根","product_code":"07.02.01.056086","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}],"listOrderVerifyRecord":[{"order_id":"18100785563","member_id":546,"account_type":10,"login_account":"beijing005","verify_status":0,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100785563","member_id":547,"account_type":30,"login_account":"beijing006","verify_status":10,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100785563","member_id":545,"account_type":20,"login_account":"beijing004","verify_status":0,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"}],"current_time":1540266621842,"limit_time":1540868135000,"is_show":false,"audit_button_show":false,"authorityList":[2],"paid_button_show":false,"order_total_count":3,"cancle_apply_show":true,"final_sent_type":20,"buy_again_button":true},{"order_id":"18100769563","created_at":"2018-10-22 17:43:18","order_status":9,"paid_type":1001,"final_paid_type":"","paid_amount":9.66,"listOrderItems":[{"id":25874,"order_id":"18100769563","member_id":545,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2138,"sku_id":20246,"cost_price":0.54,"original_price":0.7,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":0.7,"sale_price":0.7,"sku_code":"91002138020","sku_weight":0.025,"color":"蓝色","product_name":"锌丝杆","sku_info":"6mm*1m","product_pic":"http://192.168.0.107/images/009974a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":2.43,"unit_name":"根","product_code":"03.13.01.009973","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":1,"occupy_virtual_store":0,"arrival_cycle":15,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15},{"id":25875,"order_id":"18100769563","member_id":545,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2138,"sku_id":1896,"cost_price":1.08,"original_price":1.46,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":1.46,"sale_price":1.46,"sku_code":"91002138001","sku_weight":0.025,"color":"蓝色","product_name":"锌丝杆","sku_info":"6mm*2m","product_pic":"http://192.168.0.107/images/009974a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":5.07,"unit_name":"根","product_code":"03.13.01.009985","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":1,"occupy_virtual_store":0,"arrival_cycle":15,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}],"listOrderVerifyRecord":[{"order_id":"18100769563","member_id":546,"account_type":10,"login_account":"beijing005","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":547,"account_type":10,"login_account":"beijing006","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":545,"account_type":30,"login_account":"beijing004","verify_status":10,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100769563","member_id":545,"account_type":20,"login_account":"beijing004","verify_status":0,"remark":null,"created_at":"2018-10-22 17:43:20","operated_at":"1000-01-01 00:00:00"}],"current_time":1540266621892,"limit_time":1540287798000,"is_show":false,"audit_button_show":true,"authorityList":[3],"paid_button_show":false,"order_total_count":2,"cancle_apply_show":true,"final_sent_type":10,"buy_again_button":true}]
             */

            private int totalPage;
            private int pageSize;
            private int pageNumber;
            private int totalRecord;
            private int startIndex;
            private List<ResultBean> result;

            public int getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(int totalPage) {
                this.totalPage = totalPage;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(int pageNumber) {
                this.pageNumber = pageNumber;
            }

            public int getTotalRecord() {
                return totalRecord;
            }

            public void setTotalRecord(int totalRecord) {
                this.totalRecord = totalRecord;
            }

            public int getStartIndex() {
                return startIndex;
            }

            public void setStartIndex(int startIndex) {
                this.startIndex = startIndex;
            }

            public List<ResultBean> getResult() {
                return result;
            }

            public void setResult(List<ResultBean> result) {
                this.result = result;
            }

            public static class ResultBean {
                /**
                 * order_id : 18100785563
                 * created_at : 2018-10-23 10:55:35
                 * order_status : 9
                 * paid_type : 1301
                 * final_paid_type :
                 * paid_amount : 27.8
                 * listOrderItems : [{"id":25933,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":17261,"cost_price":3.35,"original_price":4.6,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":4.6,"sale_price":4.6,"sku_code":"91002052008","sku_weight":57.5,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"38穿心 12*0.5","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":0.36,"unit_name":"根","product_code":"07.02.01.075724","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":57.5,"sku_height":2,"sku_volume":15},{"id":25934,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":17258,"cost_price":5.2,"original_price":7.2,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":7.2,"sale_price":7.2,"sku_code":"91002052005","sku_weight":57.5,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"38主 12*0.8","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":0.56,"unit_name":"根","product_code":"07.02.01.075721","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":57.5,"sku_height":2,"sku_volume":15},{"id":25935,"order_id":"18100785563","member_id":547,"customer_name":"孙本才","merchant_id":0,"shop_id":1,"warehouse_id":1,"spu_id":2052,"sku_id":4928,"cost_price":10.26,"original_price":14,"plat_discount_rate":1,"category_discount_rate":1,"init_sale_price":14,"sale_price":14,"sku_code":"91002052002","sku_weight":0.025,"color":"蓝色","product_name":"优质轻钢龙骨","sku_info":"50主 15*1.2","product_pic":"http://192.168.0.107/images/056087a.jpg","buyyer_count":1,"delivery_count":0,"receive_count":0,"coupon_value":0,"fare":1.08,"unit_name":"根","product_code":"07.02.01.056086","have_returned_count":0,"can_returned_count":0,"maintaining_count":0,"can_maintain_count":0,"occupy_store":0,"occupy_virtual_store":0,"arrival_cycle":5,"sku_long":10,"sku_width":0.025,"sku_height":2,"sku_volume":15}]
                 * listOrderVerifyRecord : [{"order_id":"18100785563","member_id":546,"account_type":10,"login_account":"beijing005","verify_status":0,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100785563","member_id":547,"account_type":30,"login_account":"beijing006","verify_status":10,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"},{"order_id":"18100785563","member_id":545,"account_type":20,"login_account":"beijing004","verify_status":0,"remark":null,"created_at":"2018-10-23 10:55:36","operated_at":"1000-01-01 00:00:00"}]
                 * current_time : 1540266621842
                 * limit_time : 1540868135000
                 * is_show : false
                 * audit_button_show : false
                 * authorityList : [2]
                 * paid_button_show : false
                 * order_total_count : 3
                 * cancle_apply_show : true
                 * final_sent_type : 20
                 * buy_again_button : true
                 */

                private String order_id;
                private String created_at;
                private int order_status;
                private int paid_type;
                private String final_paid_type;
                private double paid_amount;
                private long current_time;
                private long limit_time;
                private boolean is_show;
                private boolean audit_button_show;
                private boolean paid_button_show;
                private int order_total_count;
                private boolean cancle_apply_show;
                private int final_sent_type;
                private boolean buy_again_button;
                private List<ListOrderItemsBean> listOrderItems;
                private List<ListOrderVerifyRecordBean> listOrderVerifyRecord;
                private List<Integer> authorityList;

                public String getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(String order_id) {
                    this.order_id = order_id;
                }

                public String getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(String created_at) {
                    this.created_at = created_at;
                }

                public int getOrder_status() {
                    return order_status;
                }

                public void setOrder_status(int order_status) {
                    this.order_status = order_status;
                }

                public int getPaid_type() {
                    return paid_type;
                }

                public void setPaid_type(int paid_type) {
                    this.paid_type = paid_type;
                }

                public String getFinal_paid_type() {
                    return final_paid_type;
                }

                public void setFinal_paid_type(String final_paid_type) {
                    this.final_paid_type = final_paid_type;
                }

                public double getPaid_amount() {
                    return paid_amount;
                }

                public void setPaid_amount(double paid_amount) {
                    this.paid_amount = paid_amount;
                }

                public long getCurrent_time() {
                    return current_time;
                }

                public void setCurrent_time(long current_time) {
                    this.current_time = current_time;
                }

                public long getLimit_time() {
                    return limit_time;
                }

                public void setLimit_time(long limit_time) {
                    this.limit_time = limit_time;
                }

                public boolean isIs_show() {
                    return is_show;
                }

                public void setIs_show(boolean is_show) {
                    this.is_show = is_show;
                }

                public boolean isAudit_button_show() {
                    return audit_button_show;
                }

                public void setAudit_button_show(boolean audit_button_show) {
                    this.audit_button_show = audit_button_show;
                }

                public boolean isPaid_button_show() {
                    return paid_button_show;
                }

                public void setPaid_button_show(boolean paid_button_show) {
                    this.paid_button_show = paid_button_show;
                }

                public int getOrder_total_count() {
                    return order_total_count;
                }

                public void setOrder_total_count(int order_total_count) {
                    this.order_total_count = order_total_count;
                }

                public boolean isCancle_apply_show() {
                    return cancle_apply_show;
                }

                public void setCancle_apply_show(boolean cancle_apply_show) {
                    this.cancle_apply_show = cancle_apply_show;
                }

                public int getFinal_sent_type() {
                    return final_sent_type;
                }

                public void setFinal_sent_type(int final_sent_type) {
                    this.final_sent_type = final_sent_type;
                }

                public boolean isBuy_again_button() {
                    return buy_again_button;
                }

                public void setBuy_again_button(boolean buy_again_button) {
                    this.buy_again_button = buy_again_button;
                }

                public List<ListOrderItemsBean> getListOrderItems() {
                    return listOrderItems;
                }

                public void setListOrderItems(List<ListOrderItemsBean> listOrderItems) {
                    this.listOrderItems = listOrderItems;
                }

                public List<ListOrderVerifyRecordBean> getListOrderVerifyRecord() {
                    return listOrderVerifyRecord;
                }

                public void setListOrderVerifyRecord(List<ListOrderVerifyRecordBean> listOrderVerifyRecord) {
                    this.listOrderVerifyRecord = listOrderVerifyRecord;
                }

                public List<Integer> getAuthorityList() {
                    return authorityList;
                }

                public void setAuthorityList(List<Integer> authorityList) {
                    this.authorityList = authorityList;
                }

                public static class ListOrderItemsBean {
                    /**
                     * id : 25933
                     * order_id : 18100785563
                     * member_id : 547
                     * customer_name : 孙本才
                     * merchant_id : 0
                     * shop_id : 1
                     * warehouse_id : 1
                     * spu_id : 2052
                     * sku_id : 17261
                     * cost_price : 3.35
                     * original_price : 4.6
                     * plat_discount_rate : 1.0
                     * category_discount_rate : 1.0
                     * init_sale_price : 4.6
                     * sale_price : 4.6
                     * sku_code : 91002052008
                     * sku_weight : 57.5
                     * color : 蓝色
                     * product_name : 优质轻钢龙骨
                     * sku_info : 38穿心 12*0.5
                     * product_pic : http://192.168.0.107/images/056087a.jpg
                     * buyyer_count : 1
                     * delivery_count : 0
                     * receive_count : 0
                     * coupon_value : 0.0
                     * fare : 0.36
                     * unit_name : 根
                     * product_code : 07.02.01.075724
                     * have_returned_count : 0
                     * can_returned_count : 0
                     * maintaining_count : 0
                     * can_maintain_count : 0
                     * occupy_store : 0.0
                     * occupy_virtual_store : 0.0
                     * arrival_cycle : 5
                     * sku_long : 10.0
                     * sku_width : 57.5
                     * sku_height : 2.0
                     * sku_volume : 15.0
                     */

                    private int id;
                    private String order_id;
                    private int member_id;
                    private String customer_name;
                    private int merchant_id;
                    private int shop_id;
                    private int warehouse_id;
                    private int spu_id;
                    private int sku_id;
                    private double cost_price;
                    private double original_price;
                    private double plat_discount_rate;
                    private double category_discount_rate;
                    private double init_sale_price;
                    private double sale_price;
                    private String sku_code;
                    private double sku_weight;
                    private String color;
                    private String product_name;
                    private String sku_info;
                    private String product_pic;
                    private int buyyer_count;
                    private int delivery_count;
                    private int receive_count;
                    private double coupon_value;
                    private double fare;
                    private String unit_name;
                    private String product_code;
                    private int have_returned_count;
                    private int can_returned_count;
                    private int maintaining_count;
                    private int can_maintain_count;
                    private double occupy_store;
                    private double occupy_virtual_store;
                    private int arrival_cycle;
                    private double sku_long;
                    private double sku_width;
                    private double sku_height;
                    private double sku_volume;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getOrder_id() {
                        return order_id;
                    }

                    public void setOrder_id(String order_id) {
                        this.order_id = order_id;
                    }

                    public int getMember_id() {
                        return member_id;
                    }

                    public void setMember_id(int member_id) {
                        this.member_id = member_id;
                    }

                    public String getCustomer_name() {
                        return customer_name;
                    }

                    public void setCustomer_name(String customer_name) {
                        this.customer_name = customer_name;
                    }

                    public int getMerchant_id() {
                        return merchant_id;
                    }

                    public void setMerchant_id(int merchant_id) {
                        this.merchant_id = merchant_id;
                    }

                    public int getShop_id() {
                        return shop_id;
                    }

                    public void setShop_id(int shop_id) {
                        this.shop_id = shop_id;
                    }

                    public int getWarehouse_id() {
                        return warehouse_id;
                    }

                    public void setWarehouse_id(int warehouse_id) {
                        this.warehouse_id = warehouse_id;
                    }

                    public int getSpu_id() {
                        return spu_id;
                    }

                    public void setSpu_id(int spu_id) {
                        this.spu_id = spu_id;
                    }

                    public int getSku_id() {
                        return sku_id;
                    }

                    public void setSku_id(int sku_id) {
                        this.sku_id = sku_id;
                    }

                    public double getCost_price() {
                        return cost_price;
                    }

                    public void setCost_price(double cost_price) {
                        this.cost_price = cost_price;
                    }

                    public double getOriginal_price() {
                        return original_price;
                    }

                    public void setOriginal_price(double original_price) {
                        this.original_price = original_price;
                    }

                    public double getPlat_discount_rate() {
                        return plat_discount_rate;
                    }

                    public void setPlat_discount_rate(double plat_discount_rate) {
                        this.plat_discount_rate = plat_discount_rate;
                    }

                    public double getCategory_discount_rate() {
                        return category_discount_rate;
                    }

                    public void setCategory_discount_rate(double category_discount_rate) {
                        this.category_discount_rate = category_discount_rate;
                    }

                    public double getInit_sale_price() {
                        return init_sale_price;
                    }

                    public void setInit_sale_price(double init_sale_price) {
                        this.init_sale_price = init_sale_price;
                    }

                    public double getSale_price() {
                        return sale_price;
                    }

                    public void setSale_price(double sale_price) {
                        this.sale_price = sale_price;
                    }

                    public String getSku_code() {
                        return sku_code;
                    }

                    public void setSku_code(String sku_code) {
                        this.sku_code = sku_code;
                    }

                    public double getSku_weight() {
                        return sku_weight;
                    }

                    public void setSku_weight(double sku_weight) {
                        this.sku_weight = sku_weight;
                    }

                    public String getColor() {
                        return color;
                    }

                    public void setColor(String color) {
                        this.color = color;
                    }

                    public String getProduct_name() {
                        return product_name;
                    }

                    public void setProduct_name(String product_name) {
                        this.product_name = product_name;
                    }

                    public String getSku_info() {
                        return sku_info;
                    }

                    public void setSku_info(String sku_info) {
                        this.sku_info = sku_info;
                    }

                    public String getProduct_pic() {
                        return product_pic;
                    }

                    public void setProduct_pic(String product_pic) {
                        this.product_pic = product_pic;
                    }

                    public int getBuyyer_count() {
                        return buyyer_count;
                    }

                    public void setBuyyer_count(int buyyer_count) {
                        this.buyyer_count = buyyer_count;
                    }

                    public int getDelivery_count() {
                        return delivery_count;
                    }

                    public void setDelivery_count(int delivery_count) {
                        this.delivery_count = delivery_count;
                    }

                    public int getReceive_count() {
                        return receive_count;
                    }

                    public void setReceive_count(int receive_count) {
                        this.receive_count = receive_count;
                    }

                    public double getCoupon_value() {
                        return coupon_value;
                    }

                    public void setCoupon_value(double coupon_value) {
                        this.coupon_value = coupon_value;
                    }

                    public double getFare() {
                        return fare;
                    }

                    public void setFare(double fare) {
                        this.fare = fare;
                    }

                    public String getUnit_name() {
                        return unit_name;
                    }

                    public void setUnit_name(String unit_name) {
                        this.unit_name = unit_name;
                    }

                    public String getProduct_code() {
                        return product_code;
                    }

                    public void setProduct_code(String product_code) {
                        this.product_code = product_code;
                    }

                    public int getHave_returned_count() {
                        return have_returned_count;
                    }

                    public void setHave_returned_count(int have_returned_count) {
                        this.have_returned_count = have_returned_count;
                    }

                    public int getCan_returned_count() {
                        return can_returned_count;
                    }

                    public void setCan_returned_count(int can_returned_count) {
                        this.can_returned_count = can_returned_count;
                    }

                    public int getMaintaining_count() {
                        return maintaining_count;
                    }

                    public void setMaintaining_count(int maintaining_count) {
                        this.maintaining_count = maintaining_count;
                    }

                    public int getCan_maintain_count() {
                        return can_maintain_count;
                    }

                    public void setCan_maintain_count(int can_maintain_count) {
                        this.can_maintain_count = can_maintain_count;
                    }

                    public double getOccupy_store() {
                        return occupy_store;
                    }

                    public void setOccupy_store(double occupy_store) {
                        this.occupy_store = occupy_store;
                    }

                    public double getOccupy_virtual_store() {
                        return occupy_virtual_store;
                    }

                    public void setOccupy_virtual_store(double occupy_virtual_store) {
                        this.occupy_virtual_store = occupy_virtual_store;
                    }

                    public int getArrival_cycle() {
                        return arrival_cycle;
                    }

                    public void setArrival_cycle(int arrival_cycle) {
                        this.arrival_cycle = arrival_cycle;
                    }

                    public double getSku_long() {
                        return sku_long;
                    }

                    public void setSku_long(double sku_long) {
                        this.sku_long = sku_long;
                    }

                    public double getSku_width() {
                        return sku_width;
                    }

                    public void setSku_width(double sku_width) {
                        this.sku_width = sku_width;
                    }

                    public double getSku_height() {
                        return sku_height;
                    }

                    public void setSku_height(double sku_height) {
                        this.sku_height = sku_height;
                    }

                    public double getSku_volume() {
                        return sku_volume;
                    }

                    public void setSku_volume(double sku_volume) {
                        this.sku_volume = sku_volume;
                    }
                }

                public static class ListOrderVerifyRecordBean {
                    /**
                     * order_id : 18100785563
                     * member_id : 546
                     * account_type : 10
                     * login_account : beijing005
                     * verify_status : 0
                     * remark : null
                     * created_at : 2018-10-23 10:55:36
                     * operated_at : 1000-01-01 00:00:00
                     */

                    private String order_id;
                    private int member_id;
                    private int account_type;
                    private String login_account;
                    private int verify_status;
                    private Object remark;
                    private String created_at;
                    private String operated_at;

                    public String getOrder_id() {
                        return order_id;
                    }

                    public void setOrder_id(String order_id) {
                        this.order_id = order_id;
                    }

                    public int getMember_id() {
                        return member_id;
                    }

                    public void setMember_id(int member_id) {
                        this.member_id = member_id;
                    }

                    public int getAccount_type() {
                        return account_type;
                    }

                    public void setAccount_type(int account_type) {
                        this.account_type = account_type;
                    }

                    public String getLogin_account() {
                        return login_account;
                    }

                    public void setLogin_account(String login_account) {
                        this.login_account = login_account;
                    }

                    public int getVerify_status() {
                        return verify_status;
                    }

                    public void setVerify_status(int verify_status) {
                        this.verify_status = verify_status;
                    }

                    public Object getRemark() {
                        return remark;
                    }

                    public void setRemark(Object remark) {
                        this.remark = remark;
                    }

                    public String getCreated_at() {
                        return created_at;
                    }

                    public void setCreated_at(String created_at) {
                        this.created_at = created_at;
                    }

                    public String getOperated_at() {
                        return operated_at;
                    }

                    public void setOperated_at(String operated_at) {
                        this.operated_at = operated_at;
                    }
                }
            }
        }
    }
}
