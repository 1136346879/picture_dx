package com.example.administrator.kotlintest.entity.order.repair;


import java.util.List;

/**
 * 维修订单详情页bean
 */
public class RepairOrderDetialEntity {


    /**
     * product_pic : null
     * sku_code : null
     * product_name : null
     * sku_info : null
     * warehouse_id : 0
     * buyyer_count : 0
     * delivery_count : 0
     * receive_count : 0
     * maintain_count : 0
     * refund_count : 0
     * orderRefundMaintain : {"id":338,"maintain_id":910003369,"order_id":"18101381369","member_id":193,"customer_name":"北京世纪有恒钢结构有限公司","login_account":"gaoshanwang11","shop_id":1,"warehouse_id":1,"maintain_role":10,"maintain_status":50,"maintain_reason":80,"maintain_desc":"","maintain_type":30,"repair_method":0,"take_goods_type":30,"platform":20,"province":"","province_name":"北京","city":"","city_name":"顺义区","area":"","area_name":"城区","town":"","town_name":"","detail_address":"顺义区 通灵学院","sales_manager_name":"张祥","created_at":"2018-10-11 15:06:54","updated_at":"2018-10-11 15:08:03","close_role":0,"close_time":"0999-12-31T16:00:00.000+0000","close_reason":null,"close_desc":null,"audit_person_id":0,"audit_person_name":null,"audit_time":"1000-01-01 00:00:00","audit_desc":null,"received_name":"杜建秋","received_telephone":"13669998889","created_address_code":"","finished_person_id":0,"finished_person_name":null,"finished_time":null,"finished_desc":null}
     * orderRefundMaintainProof : null
     * orderRefundMaintainItems : null
     * orderRefundMaintainRemark : null
     * listOrderRefundMaintainRemark : [{"id":254,"maintain_id":910003369,"operator_role":20,"operate_userid":22223,"operate_username":"王嵩","operate_type":10,"operate_reason":0,"remark_type":10,"maintain_remark":"没问题","created_at":"2018-10-11 15:07:25","updated_at":"1000-01-01 00:00:00"},{"id":255,"maintain_id":910003369,"operator_role":20,"operate_userid":22223,"operate_username":"王嵩","operate_type":40,"operate_reason":0,"remark_type":10,"maintain_remark":"对对对","created_at":"2018-10-11 15:08:03","updated_at":"1000-01-01 00:00:00"}]
     * listOrderRefundMaintainItems : null
     * listBaseAndMaintain : [{"unit_name":"桶","maintain_status":0,"created_at":null,"order_id":null,"maintain_id":0,"product_pic":"http://192.168.0.107/images/075120a.jpg","sku_code":"91001724001","product_name":"立邦LB 竹炭超亚光净味五合一内墙乳胶漆","sku_id":16379,"sku_info":"18L","warehouse_id":1,"buyyer_count":2,"delivery_count":2,"receive_count":2,"maintain_count":2,"refund_count":0,"can_maintain_count":0,"maintaining_count":0,"listOrderitems":null,"listOrderRefundMaintainItems":null,"listOrderRefundMaintain":null,"sale_price":null,"sale_total_price":null,"color":"蓝色"}]
     * orderExt : null
     * maintainProofList : null
     * authorityList : null
     * listAddressReceiver : null
     * orderInvoice : null
     */

    private Object product_pic;
    private Object sku_code;
    private Object product_name;
    private Object sku_info;
    private int warehouse_id;
    private int buyyer_count;
    private int delivery_count;
    private int receive_count;
    private int maintain_count;
    private int refund_count;
    private OrderRefundMaintainBean orderRefundMaintain;
    private Object orderRefundMaintainProof;
    private Object orderRefundMaintainItems;
    private Object orderRefundMaintainRemark;
    private List<ListBaseAndMaintainBean> listOrderRefundMaintainItems;
    private Object orderExt;
    private Object maintainProofList;
    private Object authorityList;
    private Object listAddressReceiver;
    private OrderInvoice orderInvoice;
    private List<ListOrderRefundMaintainRemarkBean> listOrderRefundMaintainRemark;
    private List<ListBaseAndMaintainBean> listBaseAndMaintain;

    public List<ListBaseAndMaintainBean> getListOrderRefundMaintainItems() {
        return listOrderRefundMaintainItems;
    }

    public void setListOrderRefundMaintainItems(List<ListBaseAndMaintainBean> listOrderRefundMaintainItems) {
        this.listOrderRefundMaintainItems = listOrderRefundMaintainItems;
    }

    public Object getProduct_pic() {
        return product_pic;
    }

    public void setProduct_pic(Object product_pic) {
        this.product_pic = product_pic;
    }

    public Object getSku_code() {
        return sku_code;
    }

    public void setSku_code(Object sku_code) {
        this.sku_code = sku_code;
    }

    public Object getProduct_name() {
        return product_name;
    }

    public void setProduct_name(Object product_name) {
        this.product_name = product_name;
    }

    public Object getSku_info() {
        return sku_info;
    }

    public void setSku_info(Object sku_info) {
        this.sku_info = sku_info;
    }

    public int getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(int warehouse_id) {
        this.warehouse_id = warehouse_id;
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

    public int getMaintain_count() {
        return maintain_count;
    }

    public void setMaintain_count(int maintain_count) {
        this.maintain_count = maintain_count;
    }

    public int getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(int refund_count) {
        this.refund_count = refund_count;
    }

    public OrderRefundMaintainBean getOrderRefundMaintain() {
        return orderRefundMaintain;
    }

    public void setOrderRefundMaintain(OrderRefundMaintainBean orderRefundMaintain) {
        this.orderRefundMaintain = orderRefundMaintain;
    }

    public Object getOrderRefundMaintainProof() {
        return orderRefundMaintainProof;
    }

    public void setOrderRefundMaintainProof(Object orderRefundMaintainProof) {
        this.orderRefundMaintainProof = orderRefundMaintainProof;
    }

    public Object getOrderRefundMaintainItems() {
        return orderRefundMaintainItems;
    }

    public void setOrderRefundMaintainItems(Object orderRefundMaintainItems) {
        this.orderRefundMaintainItems = orderRefundMaintainItems;
    }

    public Object getOrderRefundMaintainRemark() {
        return orderRefundMaintainRemark;
    }

    public void setOrderRefundMaintainRemark(Object orderRefundMaintainRemark) {
        this.orderRefundMaintainRemark = orderRefundMaintainRemark;
    }

//    public Object getListOrderRefundMaintainItems() {
//        return listOrderRefundMaintainItems;
//    }
//
//    public void setListOrderRefundMaintainItems(Object listOrderRefundMaintainItems) {
//        this.listOrderRefundMaintainItems = listOrderRefundMaintainItems;
//    }

    public Object getOrderExt() {
        return orderExt;
    }

    public void setOrderExt(Object orderExt) {
        this.orderExt = orderExt;
    }

    public Object getMaintainProofList() {
        return maintainProofList;
    }

    public void setMaintainProofList(Object maintainProofList) {
        this.maintainProofList = maintainProofList;
    }

    public Object getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(Object authorityList) {
        this.authorityList = authorityList;
    }

    public Object getListAddressReceiver() {
        return listAddressReceiver;
    }

    public void setListAddressReceiver(Object listAddressReceiver) {
        this.listAddressReceiver = listAddressReceiver;
    }

    public OrderInvoice getOrderInvoice() {
        return orderInvoice;
    }

    public void setOrderInvoice(OrderInvoice orderInvoice) {
        this.orderInvoice = orderInvoice;
    }

    public List<ListOrderRefundMaintainRemarkBean> getListOrderRefundMaintainRemark() {
        return listOrderRefundMaintainRemark;
    }

    public void setListOrderRefundMaintainRemark(List<ListOrderRefundMaintainRemarkBean> listOrderRefundMaintainRemark) {
        this.listOrderRefundMaintainRemark = listOrderRefundMaintainRemark;
    }

    public List<ListBaseAndMaintainBean> getListBaseAndMaintain() {
        return listBaseAndMaintain;
    }

    public void setListBaseAndMaintain(List<ListBaseAndMaintainBean> listBaseAndMaintain) {
        this.listBaseAndMaintain = listBaseAndMaintain;
    }

    public static class OrderRefundMaintainBean {
        /**
         * id : 338
         * maintain_id : 910003369
         * order_id : 18101381369
         * member_id : 193
         * customer_name : 北京世纪有恒钢结构有限公司
         * login_account : gaoshanwang11
         * shop_id : 1
         * warehouse_id : 1
         * maintain_role : 10
         * maintain_status : 50
         * maintain_reason : 80
         * maintain_desc :
         * maintain_type : 30
         * repair_method : 0
         * take_goods_type : 30
         * platform : 20
         * province :
         * province_name : 北京
         * city :
         * city_name : 顺义区
         * area :
         * area_name : 城区
         * town :
         * town_name :
         * detail_address : 顺义区 通灵学院
         * sales_manager_name : 张祥
         * created_at : 2018-10-11 15:06:54
         * updated_at : 2018-10-11 15:08:03
         * close_role : 0
         * close_time : 0999-12-31T16:00:00.000+0000
         * close_reason : null
         * close_desc : null
         * audit_person_id : 0
         * audit_person_name : null
         * audit_time : 1000-01-01 00:00:00
         * audit_desc : null
         * received_name : 杜建秋
         * received_telephone : 13669998889
         * created_address_code :
         * finished_person_id : 0
         * finished_person_name : null
         * finished_time : null
         * finished_desc : null
         */

        private int id;
        private int maintain_id;
        private String order_id;
        private int member_id;
        private String customer_name;
        private String login_account;
        private int shop_id;
        private int warehouse_id;
        private int maintain_role;
        private int maintain_status;
        private int maintain_reason;
        private String maintain_desc;
        private int maintain_type;
        private int repair_method;
        private int take_goods_type;
        private int platform;
        private String province;
        private String province_name;
        private String city;
        private String city_name;
        private String area;
        private String area_name;
        private String town;
        private String town_name;
        private String detail_address;
        private String sales_manager_name;
        private String created_at;
        private String updated_at;
        private int close_role;
        private String close_time;
        private Object close_reason;
        private Object close_desc;
        private int audit_person_id;
        private Object audit_person_name;
        private String audit_time;
        private Object audit_desc;
        private String received_name;
        private String received_telephone;
        private String created_address_code;
        private int finished_person_id;
        private Object finished_person_name;
        private Object finished_time;
        private Object finished_desc;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMaintain_id() {
            return maintain_id;
        }

        public void setMaintain_id(int maintain_id) {
            this.maintain_id = maintain_id;
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

        public String getLogin_account() {
            return login_account;
        }

        public void setLogin_account(String login_account) {
            this.login_account = login_account;
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

        public int getMaintain_role() {
            return maintain_role;
        }

        public void setMaintain_role(int maintain_role) {
            this.maintain_role = maintain_role;
        }

        public int getMaintain_status() {
            return maintain_status;
        }

        public void setMaintain_status(int maintain_status) {
            this.maintain_status = maintain_status;
        }

        public int getMaintain_reason() {
            return maintain_reason;
        }

        public void setMaintain_reason(int maintain_reason) {
            this.maintain_reason = maintain_reason;
        }

        public String getMaintain_desc() {
            return maintain_desc;
        }

        public void setMaintain_desc(String maintain_desc) {
            this.maintain_desc = maintain_desc;
        }

        public int getMaintain_type() {
            return maintain_type;
        }

        public void setMaintain_type(int maintain_type) {
            this.maintain_type = maintain_type;
        }

        public int getRepair_method() {
            return repair_method;
        }

        public void setRepair_method(int repair_method) {
            this.repair_method = repair_method;
        }

        public int getTake_goods_type() {
            return take_goods_type;
        }

        public void setTake_goods_type(int take_goods_type) {
            this.take_goods_type = take_goods_type;
        }

        public int getPlatform() {
            return platform;
        }

        public void setPlatform(int platform) {
            this.platform = platform;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getProvince_name() {
            return province_name;
        }

        public void setProvince_name(String province_name) {
            this.province_name = province_name;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getArea_name() {
            return area_name;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getTown_name() {
            return town_name;
        }

        public void setTown_name(String town_name) {
            this.town_name = town_name;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public String getSales_manager_name() {
            return sales_manager_name;
        }

        public void setSales_manager_name(String sales_manager_name) {
            this.sales_manager_name = sales_manager_name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public int getClose_role() {
            return close_role;
        }

        public void setClose_role(int close_role) {
            this.close_role = close_role;
        }

        public String getClose_time() {
            return close_time;
        }

        public void setClose_time(String close_time) {
            this.close_time = close_time;
        }

        public Object getClose_reason() {
            return close_reason;
        }

        public void setClose_reason(Object close_reason) {
            this.close_reason = close_reason;
        }

        public Object getClose_desc() {
            return close_desc;
        }

        public void setClose_desc(Object close_desc) {
            this.close_desc = close_desc;
        }

        public int getAudit_person_id() {
            return audit_person_id;
        }

        public void setAudit_person_id(int audit_person_id) {
            this.audit_person_id = audit_person_id;
        }

        public Object getAudit_person_name() {
            return audit_person_name;
        }

        public void setAudit_person_name(Object audit_person_name) {
            this.audit_person_name = audit_person_name;
        }

        public String getAudit_time() {
            return audit_time;
        }

        public void setAudit_time(String audit_time) {
            this.audit_time = audit_time;
        }

        public Object getAudit_desc() {
            return audit_desc;
        }

        public void setAudit_desc(Object audit_desc) {
            this.audit_desc = audit_desc;
        }

        public String getReceived_name() {
            return received_name;
        }

        public void setReceived_name(String received_name) {
            this.received_name = received_name;
        }

        public String getReceived_telephone() {
            return received_telephone;
        }

        public void setReceived_telephone(String received_telephone) {
            this.received_telephone = received_telephone;
        }

        public String getCreated_address_code() {
            return created_address_code;
        }

        public void setCreated_address_code(String created_address_code) {
            this.created_address_code = created_address_code;
        }

        public int getFinished_person_id() {
            return finished_person_id;
        }

        public void setFinished_person_id(int finished_person_id) {
            this.finished_person_id = finished_person_id;
        }

        public Object getFinished_person_name() {
            return finished_person_name;
        }

        public void setFinished_person_name(Object finished_person_name) {
            this.finished_person_name = finished_person_name;
        }

        public Object getFinished_time() {
            return finished_time;
        }

        public void setFinished_time(Object finished_time) {
            this.finished_time = finished_time;
        }

        public Object getFinished_desc() {
            return finished_desc;
        }

        public void setFinished_desc(Object finished_desc) {
            this.finished_desc = finished_desc;
        }
    }

    public static class ListOrderRefundMaintainRemarkBean {
        /**
         * id : 254
         * maintain_id : 910003369
         * operator_role : 20
         * operate_userid : 22223
         * operate_username : 王嵩
         * operate_type : 10
         * operate_reason : 0
         * remark_type : 10
         * maintain_remark : 没问题
         * created_at : 2018-10-11 15:07:25
         * updated_at : 1000-01-01 00:00:00
         */

        private int id;
        private int maintain_id;
        private int operator_role;
        private int operate_userid;
        private String operate_username;
        private int operate_type;
        private int operate_reason;
        private int remark_type;
        private String maintain_remark;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMaintain_id() {
            return maintain_id;
        }

        public void setMaintain_id(int maintain_id) {
            this.maintain_id = maintain_id;
        }

        public int getOperator_role() {
            return operator_role;
        }

        public void setOperator_role(int operator_role) {
            this.operator_role = operator_role;
        }

        public int getOperate_userid() {
            return operate_userid;
        }

        public void setOperate_userid(int operate_userid) {
            this.operate_userid = operate_userid;
        }

        public String getOperate_username() {
            return operate_username;
        }

        public void setOperate_username(String operate_username) {
            this.operate_username = operate_username;
        }

        public int getOperate_type() {
            return operate_type;
        }

        public void setOperate_type(int operate_type) {
            this.operate_type = operate_type;
        }

        public int getOperate_reason() {
            return operate_reason;
        }

        public void setOperate_reason(int operate_reason) {
            this.operate_reason = operate_reason;
        }

        public int getRemark_type() {
            return remark_type;
        }

        public void setRemark_type(int remark_type) {
            this.remark_type = remark_type;
        }

        public String getMaintain_remark() {
            return maintain_remark;
        }

        public void setMaintain_remark(String maintain_remark) {
            this.maintain_remark = maintain_remark;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

    public static class ListBaseAndMaintainBean {
        /**
         * unit_name : 桶
         * maintain_status : 0
         * created_at : null
         * order_id : null
         * maintain_id : 0
         * product_pic : http://192.168.0.107/images/075120a.jpg
         * sku_code : 91001724001
         * product_name : 立邦LB 竹炭超亚光净味五合一内墙乳胶漆
         * sku_id : 16379
         * sku_info : 18L
         * warehouse_id : 1
         * buyyer_count : 2
         * delivery_count : 2
         * receive_count : 2
         * maintain_count : 2
         * refund_count : 0
         * can_maintain_count : 0
         * maintaining_count : 0
         * listOrderitems : null
         * listOrderRefundMaintainItems : null
         * listOrderRefundMaintain : null
         * sale_price : null
         * sale_total_price : null
         * color : 蓝色
         */

        private String unit_name;
        private int maintain_status;
        private Object created_at;
        private Object order_id;
        private int maintain_id;
        private String product_pic;
        private String sku_code;
        private String product_name;
        private int sku_id;
        private String sku_info;
        private int warehouse_id;
        private int buyyer_count;
        private int delivery_count;
        private int receive_count;
        private int maintain_count;
        private int refund_count;
        private int can_maintain_count;
        private int maintaining_count;
        private Object listOrderitems;
//        private Object listOrderRefundMaintainItems;
        private Object listOrderRefundMaintain;
        private String sale_price;
        private String sale_total_price;
        private String color;

        public String getUnit_name() {
            return unit_name;
        }

        public void setUnit_name(String unit_name) {
            this.unit_name = unit_name;
        }

        public int getMaintain_status() {
            return maintain_status;
        }

        public void setMaintain_status(int maintain_status) {
            this.maintain_status = maintain_status;
        }

        public Object getCreated_at() {
            return created_at;
        }

        public void setCreated_at(Object created_at) {
            this.created_at = created_at;
        }

        public Object getOrder_id() {
            return order_id;
        }

        public void setOrder_id(Object order_id) {
            this.order_id = order_id;
        }

        public int getMaintain_id() {
            return maintain_id;
        }

        public void setMaintain_id(int maintain_id) {
            this.maintain_id = maintain_id;
        }

        public String getProduct_pic() {
            return product_pic;
        }

        public void setProduct_pic(String product_pic) {
            this.product_pic = product_pic;
        }

        public String getSku_code() {
            return sku_code;
        }

        public void setSku_code(String sku_code) {
            this.sku_code = sku_code;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public int getSku_id() {
            return sku_id;
        }

        public void setSku_id(int sku_id) {
            this.sku_id = sku_id;
        }

        public String getSku_info() {
            return sku_info;
        }

        public void setSku_info(String sku_info) {
            this.sku_info = sku_info;
        }

        public int getWarehouse_id() {
            return warehouse_id;
        }

        public void setWarehouse_id(int warehouse_id) {
            this.warehouse_id = warehouse_id;
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

        public int getMaintain_count() {
            return maintain_count;
        }

        public void setMaintain_count(int maintain_count) {
            this.maintain_count = maintain_count;
        }

        public int getRefund_count() {
            return refund_count;
        }

        public void setRefund_count(int refund_count) {
            this.refund_count = refund_count;
        }

        public int getCan_maintain_count() {
            return can_maintain_count;
        }

        public void setCan_maintain_count(int can_maintain_count) {
            this.can_maintain_count = can_maintain_count;
        }

        public int getMaintaining_count() {
            return maintaining_count;
        }

        public void setMaintaining_count(int maintaining_count) {
            this.maintaining_count = maintaining_count;
        }

        public Object getListOrderitems() {
            return listOrderitems;
        }

        public void setListOrderitems(Object listOrderitems) {
            this.listOrderitems = listOrderitems;
        }

//        public Object getListOrderRefundMaintainItems() {
//            return listOrderRefundMaintainItems;
//        }
//
//        public void setListOrderRefundMaintainItems(Object listOrderRefundMaintainItems) {
//            this.listOrderRefundMaintainItems = listOrderRefundMaintainItems;
//        }

        public Object getListOrderRefundMaintain() {
            return listOrderRefundMaintain;
        }

        public void setListOrderRefundMaintain(Object listOrderRefundMaintain) {
            this.listOrderRefundMaintain = listOrderRefundMaintain;
        }

        public String getSale_price() {
            return sale_price;
        }

        public void setSale_price(String  sale_price) {
            this.sale_price = sale_price;
        }

        public String  getSale_total_price() {
            return sale_total_price;
        }

        public void setSale_total_price(String sale_total_price) {
            this.sale_total_price = sale_total_price;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }
    }

    public class OrderInvoice {

        private int invoice_type ;

        public int getInvoice_type() {
            return invoice_type;
        }

        public void setInvoice_type(int invoice_type) {
            this.invoice_type = invoice_type;
        }
    }
}
