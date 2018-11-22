package com.example.administrator.kotlintest.entity;

import com.example.administrator.kotlintest.entity.address.AddressEntity;
import com.xfs.fsyuncai.entity.CouponEntity;

import java.io.Serializable;
import java.util.List;

public class ConfirmOrderentity implements Serializable{


    /**
     * cityId : 1
     * codeUuid : 1000000451659610
     * couponChoosen : {"couponCode":"12811","couponDesc":"浅浅不限","couponRealValue":"10.00","couponScope":"20","couponType":"10","couponUseCondition":"0.00","couponUseStatus":"0","couponValue":"10.00",
     *                       "spuSkuBoList":[{"skuId":"2125","spuId":"2"}],"useBegtime":"2018-08-21 10:01:10","useEndtime":"2018-08-22 23:59:59"}
     *
     *
     * couponSpuSkuBoList : [ {"couponCode":"12811","couponDesc":"浅浅不限","couponRealValue":"10.00","couponScope":"20","couponType":"10","couponUseCondition":"0.00","couponUseStatus":"0","couponValue":"10.00",
     * "                                spuSkuBoList":[{"skuId":"2125","spuId":"2"}],"useBegtime":"2018-08-21 10:01:10","useEndtime":"2018-08-22 23:59:59"},
     *                        {"couponCode":"12812","couponDesc":"浅浅不限","couponRealValue":"10.00","couponScope":"20","couponType":"10","couponUseCondition":"0.00","couponUseStatus":"0","couponValue":"10.00",
     *                                  "spuSkuBoList":[{"skuId":"2125","spuId":"2"}],"useBegtime":"2018-08-21 10:01:10","useEndtime":"2018-08-22 23:59:59"},
     *                        {"couponCode":"12910","couponDesc":"浅浅不限运费","couponRealValue":"6.00","couponScope":"20","couponType":"20","couponUseCondition":"0.00","couponUseStatus":"0","couponValue":"6.00",
     *                                   "spuSkuBoList":[],"useBegtime":"2018-08-21 09:59:30","useEndtime":"2018-09-30 09:59:32"},
     *                        {"couponCode":"12911","couponDesc":"浅浅不限运费","couponRealValue":"6.00","couponScope":"20","couponType":"20","couponUseCondition":"0.00","couponUseStatus":"0","couponValue":"6.00","
     *                                    spuSkuBoList":[],"useBegtime":"2018-08-21 09:59:30","useEndtime":"2018-09-30 09:59:32"},
     *                        {"couponCode":"12912","couponDesc":"浅浅不限运费","couponRealValue":"6.00","couponScope":"20","couponType":"20","couponUseCondition":"0.00","couponUseStatus":"0","couponValue":"6.00",
     *                                   "spuSkuBoList":[],"useBegtime":"2018-08-21 09:59:30","useEndtime":"2018-09-30 09:59:32"},
     *                        {"couponCode":"12013","couponDesc":"运费满100减5","couponRealValue":"5.00","couponScope":"20","couponType":"20","couponUseCondition":"100.00","couponUseStatus":"0","couponValue":"5.00",
     *                                   "spuSkuBoList":[],"useBegtime":"2018-08-21 10:01:18","useEndtime":"2018-08-22 23:59:59"},
     *                        {"couponCode":"12014","couponDesc":"运费满100减5","couponRealValue":"5.00","couponScope":"20","couponType":"20","couponUseCondition":"100.00","couponUseStatus":"0","couponValue":"5.00",
     *                                   "spuSkuBoList":[],"useBegtime":"2018-08-21 10:01:18","useEndtime":"2018-08-22 23:59:59"},
     *                        {"couponCode":"12015","couponDesc":"运费满100减5","couponRealValue":"5.00","couponScope":"20","couponType":"20","couponUseCondition":"100.00","couponUseStatus":"0","couponValue":"5.00",
     *                                   "spuSkuBoList":[],"useBegtime":"2018-08-21 10:01:18","useEndtime":"2018-08-22 23:59:59"}]
     * deliverSupport : private,kd
     * deliverWay : private
     * errorCode : 0
     * errorMessage : success
     * freight : {"kdDeliverTime":"2018年08月23日","kdFreight":1000,"kdPrescription":1,"privateFreight":100,"privatePrescription":0}
     * goodTotalMoney : 642.0000
     * goodTotalWeight : 25.68
     * maxArrivalCycle : 0
     * maxLength : 38.0
     * payModelChoosen : {"payName":"在线支付","payType":"1001"}
     *
     * payModelList : [{"payName":"在线支付","payType":"1001"},
     *                  {"payName":"银行转账","payType":"1301"},
     *                  {"payName":"银行汇票","payType":"1401"},
     *                  {"payName":"账期支付","payType":"1201"},
     *                  {"payName":"货到付款","payType":"1101"}]
     * payTotalMoney : 732.0000
     * serverTime : 1534934517800
     * shippingAddressModel : {"detail_address":"1","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"778","createTime":"2018-08-22 17:48:02.0","receiverName":"李燕","mobile":"18393512064","id":"281","addressId":"925","status":"10"}],
     *                      "add_alias":"1","is_default":"1","province_code":"1","street_code":"","office_phone":"","ship_add_id":925,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86}
     *
     * shoppingAddList : [{"detail_address":"1","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"778","createTime":"2018-08-22 17:48:02.0","receiverName":"李燕","mobile":"18393512064","id":"281","addressId":"925","status":"10"}],
     *                      "add_alias":"1","is_default":"1","province_code":"1","street_code":"","office_phone":"","ship_add_id":925,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1",
     *                      "addressPersonList":[{"receiverId":"14104","createTime":"2018-07-25 14:51:26.0","receiverName":"李东","mobile":"18393512064","id":"99","addressId":"831","status":"10"}],
     *                      "add_alias":" ","is_default":"1","office_phone":"","ship_add_id":831,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 19:18:58.0","receiverName":"李东","mobile":"18393512064","id":"277","addressId":"921","status":"0"}],
     *                      "add_alias":" 222222222","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":921,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 19:18:52.0","receiverName":"李","mobile":"18393512062","id":"276","addressId":"920","status":"0"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":920,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 19:16:15.0","receiverName":"李","mobile":"18393512064","id":"275","addressId":"919","status":"0"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":919,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 19:08:03.0","receiverName":"李东","mobile":"18393512064","id":"274","addressId":"918","status":"0"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":918,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 19:06:09.0","receiverName":"李东","mobile":"18393512064","id":"273","addressId":"917","status":"0"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":917,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 19:04:04.0","receiverName":"李东","mobile":"18393512064","id":"272","addressId":"916","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":916,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 19:02:05.0","receiverName":"李东","mobile":"18393512064","id":"271","addressId":"915","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":915,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 18:59:32.0","receiverName":"李东","mobile":"18393512064","id":"270","addressId":"914","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":914,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 18:57:56.0","receiverName":"李东","mobile":"18393512064","id":"269","addressId":"913","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":913,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯ddddddddddd","warehouse_code":"1","city_code":"2816","
     *                      addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 18:51:56.0","receiverName":"李东","mobile":"18393512064","id":"268","addressId":"912","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":912,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯","warehouse_code":"2","city_code":"51036","
     *                      addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 18:50:11.0","receiverName":"李东","mobile":"18393512064","id":"267","addressId":"911","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"3","street_code":"","office_phone":"","ship_add_id":911,"cityName":"和平区","areaLimitLine":"20","province_id":3,"areaName":"全境","district_code":"2984","limitLine":"20","district_id":799,"provinceName":"天津","street_id":0,"email":"","city_id":121},
     *                      {"detail_address":"咯咯咯nnjnkjnjknknkjnj","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 18:49:55.0","receiverName":"李东","mobile":"18393512064","id":"266","addressId":"910","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":910,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"咯咯咯nnjnjnkjnkjnkjnjk","warehouse_code":"1","city_code":"2820",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 18:49:30.0","receiverName":"李东","mobile":"18393512064","id":"265","addressId":"909","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"2","street_code":"","office_phone":"","ship_add_id":909,"cityName":"闸北区","areaLimitLine":"20","province_id":2,"areaName":"城区","district_code":"51972","limitLine":"20","district_id":676,"provinceName":"上海","street_id":0,"email":"","city_id":104},
     *                      {"detail_address":"咯咯咯","warehouse_code":"1","city_code":"2816",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-08-21 18:49:13.0","receiverName":"李东","mobile":"18393512064","id":"264","addressId":"908","status":"10"}],
     *                      "add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":908,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","district_code":"6667","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"珇有","warehouse_code":"1",
     *                      "addressPersonList":[{"receiverId":"14104","createTime":"2018-07-25 14:40:07.0","receiverName":"李东","mobile":"18393512064","id":"98","addressId":"830","status":"10"}],
     *                      "add_alias":"ff","is_default":"2","office_phone":"","ship_add_id":830,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"11","warehouse_code":"1","
     *                      addressPersonList":[{"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"1","mobile":"13718002648","id":"87","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"2","mobile":"","id":"88","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"3","mobile":"","id":"89","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"4","mobile":"","id":"90","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"5","mobile":"","id":"91","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"6","mobile":"","id":"92","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"7","mobile":"","id":"93","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"8","mobile":"","id":"94","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"9","mobile":"","id":"95","addressId":"828","status":"10"},
     *                      {"receiverId":"0","createTime":"2018-07-25 11:19:11.0","receiverName":"10","mobile":"","id":"96","addressId":"828","status":"10"}],
     *                      "add_alias":"11","is_default":"2","office_phone":"","ship_add_id":828,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86},
     *                      {"detail_address":"12345","warehouse_code":"2",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-07-19 14:25:55.0","receiverName":"张三","mobile":"13937923678","id":"78","addressId":"818","status":"10"},
     *                      {"receiverId":"14105","createTime":"2018-07-19 14:25:55.0","receiverName":"李连富是","mobile":"18393512064","id":"79","addressId":"818","status":"10"}],
     *                      "add_alias":"乐乐乐","is_default":"2","office_phone":"","ship_add_id":818,"cityName":"沧州市","areaLimitLine":"20","province_id":5,"areaName":"献县","limitLine":"20","district_id":1786,"provinceName":"河北","street_id":0,"email":"","city_id":179},
     *                      {"detail_address":"666","warehouse_code":"1",
     *                      "addressPersonList":[{"receiverId":"0","createTime":"2018-07-19 11:37:12.0","receiverName":"张三","mobile":"13937923678","id":"67","addressId":"817","status":"10"}],
     *                      "add_alias":"仙仙555","is_default":"2","office_phone":"","ship_add_id":817,"cityName":"密云区","areaLimitLine":"20","province_id":1,"areaName":"城区","limitLine":"20","district_id":542,"provinceName":"北京","street_id":0,"email":"","city_id":86}]
     * skuModelList : {"2":
     *                  [{"arrivalCycle":"2",
     *                  "buyyerCount":"6",
     *                  "cartId":"47559",
     *                  "categoryDiscountRate":"1",
     *                  "categoryId":"39",
     *                  "color":"黑色","
     *                  costPrice":"76.83",
     *                  "fare":"100.000000",
     *                  "high":"15.199999809265137",
     *                  "length":"38.0",
     *                  "originalPrice":"107.00",
     *                  "platDiscountRate":"1",
     *                  "productCode":"01.15.11.055980",
     *                  "productName":"我是商品名称",
     *                  "productPic":"https://test-fsyuncai.oss-cn-beijing.aliyuncs.com/image/大桥切割片(不锈钢绿片).png",
     *                  "salePrice":"107.00",
     *                  "salesManagerId":"0",
     *                  "skuCode":"91000002002",
     *                  "skuId":"2125",
     *                  "skuInfo":"152mm*300mm",
     *                  "skuName":"博深BOSUN 速锐经济型晶钻水钻头",
     *                  "skuWeight":"4.28",
     *                  "specifications":"152mm*300mm",
     *                  "spuId":"2",
     *                  "status":"1",
     *                  "stockNum":"9",
     *                  "unitName":"支",
     *                  "virtualStock":"10",
     *                  "warehouseId":"1",
     *                  "width":"15.199999809265137"}]}
     * skuTotalNum : 6.0
     * wannaDeliverAtBegin :
     * wannaDeliverAtEnd :
     * warehouseId : 1
     */

    private String cityId;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    private String tip;
    private String codeUuid;
    private CouponEntity couponChoosen;
    private String deliverSupport;
    private String deliverWay;
    private String errorCode;
    private String errorMessage;
    private FreightBean freight;
    private String goodTotalMoney;
    private String goodTotalWeight;
    private String goodTotalVolume;
    private String maxArrivalCycle;
    private String maxLength;
    private PayModelChoosenBean payModelChoosen;
    private String payTotalMoney;
    private long serverTime;
    private AddressEntity.ListBean shippingAddressModel;
    private Object skuModelList;
    private String skuTotalNum;
    private String wannaDeliverAtBegin;
    private String wannaDeliverAtEnd;
    private String warehouseId;
    private List<CouponEntity> couponSpuSkuBoList;
    private List<PayModelListBean> payModelList;
    private List<ShoppingAddListBean> shoppingAddList;

    public String getGoodTotalVolume() {
        return goodTotalVolume;
    }

    public void setGoodTotalVolume(String goodTotalVolume) {
        this.goodTotalVolume = goodTotalVolume;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCodeUuid() {
        return codeUuid;
    }

    public void setCodeUuid(String codeUuid) {
        this.codeUuid = codeUuid;
    }

    public CouponEntity getCouponChoosen() {
        return couponChoosen;
    }

    public void setCouponChoosen(CouponEntity couponChoosen) {
        this.couponChoosen = couponChoosen;
    }

    public String getDeliverSupport() {
        return deliverSupport;
    }

    public void setDeliverSupport(String deliverSupport) {
        this.deliverSupport = deliverSupport;
    }

    public String getDeliverWay() {
        return deliverWay;
    }

    public void setDeliverWay(String deliverWay) {
        this.deliverWay = deliverWay;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public FreightBean getFreight() {
        return freight;
    }

    public void setFreight(FreightBean freight) {
        this.freight = freight;
    }

    public String getGoodTotalMoney() {
        return goodTotalMoney;
    }

    public void setGoodTotalMoney(String goodTotalMoney) {
        this.goodTotalMoney = goodTotalMoney;
    }

    public String getGoodTotalWeight() {
        return goodTotalWeight;
    }

    public void setGoodTotalWeight(String goodTotalWeight) {
        this.goodTotalWeight = goodTotalWeight;
    }

    public String getMaxArrivalCycle() {
        return maxArrivalCycle;
    }

    public void setMaxArrivalCycle(String maxArrivalCycle) {
        this.maxArrivalCycle = maxArrivalCycle;
    }

    public String getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(String maxLength) {
        this.maxLength = maxLength;
    }

    public PayModelChoosenBean getPayModelChoosen() {
        return payModelChoosen;
    }

    public void setPayModelChoosen(PayModelChoosenBean payModelChoosen) {
        this.payModelChoosen = payModelChoosen;
    }

    public String getPayTotalMoney() {
        return payTotalMoney;
    }

    public void setPayTotalMoney(String payTotalMoney) {
        this.payTotalMoney = payTotalMoney;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }

    public AddressEntity.ListBean getShippingAddressModel() {
        return shippingAddressModel;
    }

    public void setShippingAddressModel(AddressEntity.ListBean shippingAddressModel) {
        this.shippingAddressModel = shippingAddressModel;
    }

    public Object getSkuModelList() {
        return skuModelList;
    }

    public void setSkuModelList(Object skuModelList) {
        this.skuModelList = skuModelList;
    }

    public String getSkuTotalNum() {
        return skuTotalNum;
    }

    public void setSkuTotalNum(String skuTotalNum) {
        this.skuTotalNum = skuTotalNum;
    }

    public String getWannaDeliverAtBegin() {
        return wannaDeliverAtBegin;
    }

    public void setWannaDeliverAtBegin(String wannaDeliverAtBegin) {
        this.wannaDeliverAtBegin = wannaDeliverAtBegin;
    }

    public String getWannaDeliverAtEnd() {
        return wannaDeliverAtEnd;
    }

    public void setWannaDeliverAtEnd(String wannaDeliverAtEnd) {
        this.wannaDeliverAtEnd = wannaDeliverAtEnd;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public List<CouponEntity> getCouponSpuSkuBoList() {
        return couponSpuSkuBoList;
    }

    public void setCouponSpuSkuBoList(List<CouponEntity> couponSpuSkuBoList) {
        this.couponSpuSkuBoList = couponSpuSkuBoList;
    }

    public List<PayModelListBean> getPayModelList() {
        return payModelList;
    }

    public void setPayModelList(List<PayModelListBean> payModelList) {
        this.payModelList = payModelList;
    }

    public List<ShoppingAddListBean> getShoppingAddList() {
        return shoppingAddList;
    }

    public void setShoppingAddList(List<ShoppingAddListBean> shoppingAddList) {
        this.shoppingAddList = shoppingAddList;
    }

    public static class FreightBean implements Serializable{
        /**
         * kdDeliverTime : 2018年08月23日
         * kdFreight : 1000
         * kdPrescription : 1
         * privateFreight : 100
         * privatePrescription : 0
         */

        private String kdDeliverTime;
        private String kyDeliverTime;
        private String carDeliverTime;
        private double kdFreight;

        public String getKyDeliverTime() {
            return kyDeliverTime;
        }

        public void setKyDeliverTime(String kyDeliverTime) {
            this.kyDeliverTime = kyDeliverTime;
        }

        public String getCarDeliverTime() {
            return carDeliverTime;
        }

        public void setCarDeliverTime(String carDeliverTime) {
            this.carDeliverTime = carDeliverTime;
        }

        private int kdPrescription;
        private Double privateFreight;
        private int privatePrescription;
        private Double kyFreight;
        private int kyPrescription;
        private Double carFreight;
        private int carPrescription;

        public Double getKyFreight() {
            return kyFreight;
        }

        public void setKyFreight(Double kyFreight) {
            this.kyFreight = kyFreight;
        }

        public int getKyPrescription() {
            return kyPrescription;
        }

        public void setKyPrescription(int kyPrescription) {
            this.kyPrescription = kyPrescription;
        }

        public Double getCarFreight() {
            return carFreight;
        }

        public void setCarFreight(Double carFreight) {
            this.carFreight = carFreight;
        }

        public int getCarPrescription() {
            return carPrescription;
        }

        public void setCarPrescription(int carPrescription) {
            this.carPrescription = carPrescription;
        }

        public String getKdDeliverTime() {
            return kdDeliverTime;
        }

        public void setKdDeliverTime(String kdDeliverTime) {
            this.kdDeliverTime = kdDeliverTime;
        }

        public double getKdFreight() {
            return kdFreight;
        }

        public void setKdFreight(double kdFreight) {
            this.kdFreight = kdFreight;
        }

        public int getKdPrescription() {
            return kdPrescription;
        }

        public void setKdPrescription(int kdPrescription) {
            this.kdPrescription = kdPrescription;
        }

        public Double getPrivateFreight() {
            return privateFreight;
        }

        public void setPrivateFreight(Double privateFreight) {
            this.privateFreight = privateFreight;
        }

        public int getPrivatePrescription() {
            return privatePrescription;
        }

        public void setPrivatePrescription(int privatePrescription) {
            this.privatePrescription = privatePrescription;
        }
    }

    public static class PayModelChoosenBean implements Serializable {
        /**
         * payName : 在线支付
         * payType : 1001
         */

        private String payName;
        private String payType;

        public String getPayName() {
            return payName;
        }

        public void setPayName(String payName) {
            this.payName = payName;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }
    }

    public static class ShippingAddressModelBean implements Serializable{
        /**
         * detail_address : 1
         * warehouse_code : 1
         * city_code : 2816
         * addressPersonList : [{"receiverId":"778","createTime":"2018-08-22 17:48:02.0","receiverName":"李燕","mobile":"18393512064","id":"281","addressId":"925","status":"10"}]
         * add_alias : 1
         * is_default : 1
         * province_code : 1
         * street_code :
         * office_phone :
         * ship_add_id : 925
         * cityName : 密云区
         * areaLimitLine : 20
         * province_id : 1
         * areaName : 城区
         * district_code : 6667
         * limitLine : 20
         * district_id : 542
         * provinceName : 北京
         * street_id : 0
         * email :
         * city_id : 86
         */
        private String detail_address;
        private String warehouse_code;
        private String city_code;
        private String add_alias;
        private String is_default;
        private String province_code;
        private String street_code;
        private String office_phone;
        private int ship_add_id;
        private String cityName;
        private String areaLimitLine;
        private int province_id;
        private String areaName;
        private String district_code;
        private String limitLine;
        private int district_id;
        private String provinceName;
        private int street_id;
        private String email;
        private int city_id;
        private List<AddressPersonListBean> addressPersonList;

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public String getWarehouse_code() {
            return warehouse_code;
        }

        public void setWarehouse_code(String warehouse_code) {
            this.warehouse_code = warehouse_code;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getAdd_alias() {
            return add_alias;
        }

        public void setAdd_alias(String add_alias) {
            this.add_alias = add_alias;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public String getStreet_code() {
            return street_code;
        }

        public void setStreet_code(String street_code) {
            this.street_code = street_code;
        }

        public String getOffice_phone() {
            return office_phone;
        }

        public void setOffice_phone(String office_phone) {
            this.office_phone = office_phone;
        }

        public int getShip_add_id() {
            return ship_add_id;
        }

        public void setShip_add_id(int ship_add_id) {
            this.ship_add_id = ship_add_id;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaLimitLine() {
            return areaLimitLine;
        }

        public void setAreaLimitLine(String areaLimitLine) {
            this.areaLimitLine = areaLimitLine;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getDistrict_code() {
            return district_code;
        }

        public void setDistrict_code(String district_code) {
            this.district_code = district_code;
        }

        public String getLimitLine() {
            return limitLine;
        }

        public void setLimitLine(String limitLine) {
            this.limitLine = limitLine;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getStreet_id() {
            return street_id;
        }

        public void setStreet_id(int street_id) {
            this.street_id = street_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public List<AddressPersonListBean> getAddressPersonList() {
            return addressPersonList;
        }

        public void setAddressPersonList(List<AddressPersonListBean> addressPersonList) {
            this.addressPersonList = addressPersonList;
        }

        public static class AddressPersonListBean implements Serializable{
            /**
             * receiverId : 778
             * createTime : 2018-08-22 17:48:02.0
             * receiverName : 李燕
             * mobile : 18393512064
             * id : 281
             * addressId : 925
             * status : 10
             */

            private String receiverId;
            private String createTime;
            private String receiverName;
            private String mobile;
            private String id;
            private String addressId;
            private String status;

            public String getReceiverId() {
                return receiverId;
            }

            public void setReceiverId(String receiverId) {
                this.receiverId = receiverId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }

//    public static class SkuModelListBean {
//        @SerializedName("2")
//        private List<_$2Bean> _$2;
//
//        public List<_$2Bean> get_$2() {
//            return _$2;
//        }
//
//        public void set_$2(List<_$2Bean> _$2) {
//            this._$2 = _$2;
//        }
//
//        public static class _$2Bean {
//            /**
//             * arrivalCycle : 2
//             * buyyerCount : 6
//             * cartId : 47559
//             * categoryDiscountRate : 1
//             * categoryId : 39
//             * color : 黑色
//             * costPrice : 76.83
//             * fare : 100.000000
//             * high : 15.199999809265137
//             * length : 38.0
//             * originalPrice : 107.00
//             * platDiscountRate : 1
//             * productCode : 01.15.11.055980
//             * productName : 我是商品名称
//             * productPic : https://test-fsyuncai.oss-cn-beijing.aliyuncs.com/image/大桥切割片(不锈钢绿片).png
//             * salePrice : 107.00
//             * salesManagerId : 0
//             * skuCode : 91000002002
//             * skuId : 2125
//             * skuInfo : 152mm*300mm
//             * skuName : 博深BOSUN 速锐经济型晶钻水钻头
//             * skuWeight : 4.28
//             * specifications : 152mm*300mm
//             * spuId : 2
//             * status : 1
//             * stockNum : 9
//             * unitName : 支
//             * virtualStock : 10
//             * warehouseId : 1
//             * width : 15.199999809265137
//             */
//
//            private String arrivalCycle;
//            private String buyyerCount;
//            private String cartId;
//            private String categoryDiscountRate;
//            private String categoryId;
//            private String color;
//            private String costPrice;
//            private String fare;
//            private String high;
//            private String length;
//            private String originalPrice;
//            private String platDiscountRate;
//            private String productCode;
//            private String productName;
//            private String productPic;
//            private String salePrice;
//            private String salesManagerId;
//            private String skuCode;
//            private String skuId;
//            private String skuInfo;
//            private String skuName;
//            private String skuWeight;
//            private String specifications;
//            private String spuId;
//            private String status;
//            private String stockNum;
//            private String unitName;
//            private String virtualStock;
//            private String warehouseId;
//            private String width;
//
//            public String getArrivalCycle() {
//                return arrivalCycle;
//            }
//
//            public void setArrivalCycle(String arrivalCycle) {
//                this.arrivalCycle = arrivalCycle;
//            }
//
//            public String getBuyyerCount() {
//                return buyyerCount;
//            }
//
//            public void setBuyyerCount(String buyyerCount) {
//                this.buyyerCount = buyyerCount;
//            }
//
//            public String getCartId() {
//                return cartId;
//            }
//
//            public void setCartId(String cartId) {
//                this.cartId = cartId;
//            }
//
//            public String getCategoryDiscountRate() {
//                return categoryDiscountRate;
//            }
//
//            public void setCategoryDiscountRate(String categoryDiscountRate) {
//                this.categoryDiscountRate = categoryDiscountRate;
//            }
//
//            public String getCategoryId() {
//                return categoryId;
//            }
//
//            public void setCategoryId(String categoryId) {
//                this.categoryId = categoryId;
//            }
//
//            public String getColor() {
//                return color;
//            }
//
//            public void setColor(String color) {
//                this.color = color;
//            }
//
//            public String getCostPrice() {
//                return costPrice;
//            }
//
//            public void setCostPrice(String costPrice) {
//                this.costPrice = costPrice;
//            }
//
//            public String getFare() {
//                return fare;
//            }
//
//            public void setFare(String fare) {
//                this.fare = fare;
//            }
//
//            public String getHigh() {
//                return high;
//            }
//
//            public void setHigh(String high) {
//                this.high = high;
//            }
//
//            public String getLength() {
//                return length;
//            }
//
//            public void setLength(String length) {
//                this.length = length;
//            }
//
//            public String getOriginalPrice() {
//                return originalPrice;
//            }
//
//            public void setOriginalPrice(String originalPrice) {
//                this.originalPrice = originalPrice;
//            }
//
//            public String getPlatDiscountRate() {
//                return platDiscountRate;
//            }
//
//            public void setPlatDiscountRate(String platDiscountRate) {
//                this.platDiscountRate = platDiscountRate;
//            }
//
//            public String getProductCode() {
//                return productCode;
//            }
//
//            public void setProductCode(String productCode) {
//                this.productCode = productCode;
//            }
//
//            public String getProductName() {
//                return productName;
//            }
//
//            public void setProductName(String productName) {
//                this.productName = productName;
//            }
//
//            public String getProductPic() {
//                return productPic;
//            }
//
//            public void setProductPic(String productPic) {
//                this.productPic = productPic;
//            }
//
//            public String getSalePrice() {
//                return salePrice;
//            }
//
//            public void setSalePrice(String salePrice) {
//                this.salePrice = salePrice;
//            }
//
//            public String getSalesManagerId() {
//                return salesManagerId;
//            }
//
//            public void setSalesManagerId(String salesManagerId) {
//                this.salesManagerId = salesManagerId;
//            }
//
//            public String getSkuCode() {
//                return skuCode;
//            }
//
//            public void setSkuCode(String skuCode) {
//                this.skuCode = skuCode;
//            }
//
//            public String getSkuId() {
//                return skuId;
//            }
//
//            public void setSkuId(String skuId) {
//                this.skuId = skuId;
//            }
//
//            public String getSkuInfo() {
//                return skuInfo;
//            }
//
//            public void setSkuInfo(String skuInfo) {
//                this.skuInfo = skuInfo;
//            }
//
//            public String getSkuName() {
//                return skuName;
//            }
//
//            public void setSkuName(String skuName) {
//                this.skuName = skuName;
//            }
//
//            public String getSkuWeight() {
//                return skuWeight;
//            }
//
//            public void setSkuWeight(String skuWeight) {
//                this.skuWeight = skuWeight;
//            }
//
//            public String getSpecifications() {
//                return specifications;
//            }
//
//            public void setSpecifications(String specifications) {
//                this.specifications = specifications;
//            }
//
//            public String getSpuId() {
//                return spuId;
//            }
//
//            public void setSpuId(String spuId) {
//                this.spuId = spuId;
//            }
//
//            public String getStatus() {
//                return status;
//            }
//
//            public void setStatus(String status) {
//                this.status = status;
//            }
//
//            public String getStockNum() {
//                return stockNum;
//            }
//
//            public void setStockNum(String stockNum) {
//                this.stockNum = stockNum;
//            }
//
//            public String getUnitName() {
//                return unitName;
//            }
//
//            public void setUnitName(String unitName) {
//                this.unitName = unitName;
//            }
//
//            public String getVirtualStock() {
//                return virtualStock;
//            }
//
//            public void setVirtualStock(String virtualStock) {
//                this.virtualStock = virtualStock;
//            }
//
//            public String getWarehouseId() {
//                return warehouseId;
//            }
//
//            public void setWarehouseId(String warehouseId) {
//                this.warehouseId = warehouseId;
//            }
//
//            public String getWidth() {
//                return width;
//            }
//
//            public void setWidth(String width) {
//                this.width = width;
//            }
//        }
//    }

    public static class PayModelListBean  implements  Serializable{
        /**
         * payName : 在线支付
         * payType : 1001
         */

        private String payName;
        private String payType;

        public String getPayName() {
            return payName;
        }

        public void setPayName(String payName) {
            this.payName = payName;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }
    }

    public static class ShoppingAddListBean  implements Serializable{
        /**
         * detail_address : 1
         * warehouse_code : 1
         * city_code : 2816
         * addressPersonList : [{"receiverId":"778","createTime":"2018-08-22 17:48:02.0","receiverName":"李燕","mobile":"18393512064","id":"281","addressId":"925","status":"10"}]
         * add_alias : 1
         * is_default : 1
         * province_code : 1
         * street_code :
         * office_phone :
         * ship_add_id : 925
         * cityName : 密云区
         * areaLimitLine : 20
         * province_id : 1
         * areaName : 城区
         * district_code : 6667
         * limitLine : 20
         * district_id : 542
         * provinceName : 北京
         * street_id : 0
         * email :
         * city_id : 86
         */

        private String detail_address;
        private String warehouse_code;
        private String city_code;
        private String add_alias;
        private String is_default;
        private String province_code;
        private String street_code;
        private String office_phone;
        private int ship_add_id;
        private String cityName;
        private String areaLimitLine;
        private int province_id;
        private String areaName;
        private String district_code;
        private String limitLine;
        private int district_id;
        private String provinceName;
        private int street_id;
        private String email;
        private int city_id;
        private List<AddressPersonListBeanX> addressPersonList;

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
        }

        public String getWarehouse_code() {
            return warehouse_code;
        }

        public void setWarehouse_code(String warehouse_code) {
            this.warehouse_code = warehouse_code;
        }

        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
        }

        public String getAdd_alias() {
            return add_alias;
        }

        public void setAdd_alias(String add_alias) {
            this.add_alias = add_alias;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public String getProvince_code() {
            return province_code;
        }

        public void setProvince_code(String province_code) {
            this.province_code = province_code;
        }

        public String getStreet_code() {
            return street_code;
        }

        public void setStreet_code(String street_code) {
            this.street_code = street_code;
        }

        public String getOffice_phone() {
            return office_phone;
        }

        public void setOffice_phone(String office_phone) {
            this.office_phone = office_phone;
        }

        public int getShip_add_id() {
            return ship_add_id;
        }

        public void setShip_add_id(int ship_add_id) {
            this.ship_add_id = ship_add_id;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getAreaLimitLine() {
            return areaLimitLine;
        }

        public void setAreaLimitLine(String areaLimitLine) {
            this.areaLimitLine = areaLimitLine;
        }

        public int getProvince_id() {
            return province_id;
        }

        public void setProvince_id(int province_id) {
            this.province_id = province_id;
        }

        public String getAreaName() {
            return areaName;
        }

        public void setAreaName(String areaName) {
            this.areaName = areaName;
        }

        public String getDistrict_code() {
            return district_code;
        }

        public void setDistrict_code(String district_code) {
            this.district_code = district_code;
        }

        public String getLimitLine() {
            return limitLine;
        }

        public void setLimitLine(String limitLine) {
            this.limitLine = limitLine;
        }

        public int getDistrict_id() {
            return district_id;
        }

        public void setDistrict_id(int district_id) {
            this.district_id = district_id;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getStreet_id() {
            return street_id;
        }

        public void setStreet_id(int street_id) {
            this.street_id = street_id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public int getCity_id() {
            return city_id;
        }

        public void setCity_id(int city_id) {
            this.city_id = city_id;
        }

        public List<AddressPersonListBeanX> getAddressPersonList() {
            return addressPersonList;
        }

        public void setAddressPersonList(List<AddressPersonListBeanX> addressPersonList) {
            this.addressPersonList = addressPersonList;
        }

        public static class AddressPersonListBeanX implements Serializable{
            /**
             * receiverId : 778
             * createTime : 2018-08-22 17:48:02.0
             * receiverName : 李燕
             * mobile : 18393512064
             * id : 281
             * addressId : 925
             * status : 10
             */

            private String receiverId;
            private String createTime;
            private String receiverName;
            private String mobile;
            private String id;
            private String addressId;
            private String status;

            public String getReceiverId() {
                return receiverId;
            }

            public void setReceiverId(String receiverId) {
                this.receiverId = receiverId;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getReceiverName() {
                return receiverName;
            }

            public void setReceiverName(String receiverName) {
                this.receiverName = receiverName;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
