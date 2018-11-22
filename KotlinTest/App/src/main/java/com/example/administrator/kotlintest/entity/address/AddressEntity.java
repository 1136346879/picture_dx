package com.example.administrator.kotlintest.entity.address;

import java.io.Serializable;
import java.util.List;

/**
*@author : HaoBoy
*@date : 2018/8/15
*@description :地址实体类
**/
public class AddressEntity implements Serializable{

    /**
     * total : 5
     * errorMessage : 调用成功!
     * errorCode : 0
     * pageSize : 40
     * list : [{"detail_address":"发士大夫撒发生","addressPersonList":[{"receiverId":"14104","createTime":"2018-08-09 16:47:13.0","receiverName":"李东","mobile":"18393512064","id":"555","addressId":"1008","status":"10"}],"add_alias":"方法","is_default":"2","office_phone":"","ship_add_id":1008,"cityName":"朝阳区","province_id":1,"areaName":"四环到五环之间","district_id":545,"provinceName":"北京","street_id":0,"email":"","city_id":87},{"detail_address":"1111111111","add_alias":"1111111111","is_default":"2","office_phone":"","ship_add_id":557,"streetName":"刘营乡","cityName":"邯郸市","province_id":5,"areaName":"永年县","district_id":1849,"provinceName":"河北","street_id":5992,"email":"","city_id":183},{"detail_address":"0000000","add_alias":"0000000000000000000","is_default":"2","office_phone":"","ship_add_id":552,"streetName":"刘营乡","cityName":"邯郸市","province_id":5,"areaName":"永年县","district_id":1849,"provinceName":"河北","street_id":5992,"email":"","city_id":183},{"detail_address":"交换机","add_alias":"6666","is_default":"2","office_phone":"","ship_add_id":509,"cityName":"和平区","province_id":3,"areaName":"全境","district_id":799,"provinceName":"天津","street_id":0,"email":"","city_id":121},{"detail_address":"哦哦哦哦哦哦","add_alias":"哈哈哈哈","is_default":"2","office_phone":"","ship_add_id":487,"cityName":"海淀区","province_id":1,"areaName":"西二旗","district_id":43976,"provinceName":"北京","street_id":0,"email":"","city_id":43962}]
     * xMapTotal : {"total":5}
     * pageNum : 1
     */
    private int total;
    private String errorMessage;
    private int errorCode;
    private String pageSize;
    private XMapTotalBean xMapTotal;
    private String pageNum;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

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

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public XMapTotalBean getXMapTotal() {
        return xMapTotal;
    }

    public void setXMapTotal(XMapTotalBean xMapTotal) {
        this.xMapTotal = xMapTotal;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class XMapTotalBean {
        /**
         * total : 5
         */

        private int total;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }

    public static class ListBean implements Serializable{
        /**
         * detail_address : 发士大夫撒发生
         * addressPersonList : [{"receiverId":"14104","createTime":"2018-08-09 16:47:13.0","receiverName":"李东","mobile":"18393512064","id":"555","addressId":"1008","status":"10"}]
         * add_alias : 方法
         * is_default : 2
         * office_phone :
         * ship_add_id : 1008
         * cityName : 朝阳区
         * province_id : 1
         * areaName : 四环到五环之间
         * district_id : 545
         * provinceName : 北京
         * street_id : 0
         * email :
         * city_id : 87
         * streetName : 刘营乡
         */
        private String warehouse_code;
        private String detail_address;
        private String add_alias;
        private String is_default;//1默认
        private String office_phone;
        private int ship_add_id;
        private String cityName;
        private int province_id;
        private String areaName;
        private int district_id;
        private String provinceName;
        private int street_id;
        private String email;
        private int city_id;
        private String streetName;
        private String limitLine;//10限行 20不限行
        private String city_code;
        private String province_code;
        private String street_code;
        private String areaLimitLine;
        private String district_code;
        private List<AddressPersonListBean> addressPersonList;


        public String getCity_code() {
            return city_code;
        }

        public void setCity_code(String city_code) {
            this.city_code = city_code;
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

        public String getAreaLimitLine() {
            return areaLimitLine;
        }

        public void setAreaLimitLine(String areaLimitLine) {
            this.areaLimitLine = areaLimitLine;
        }

        public String getDistrict_code() {
            return district_code;
        }

        public void setDistrict_code(String district_code) {
            this.district_code = district_code;
        }

        public String getWarehouse_code() {
            return warehouse_code;
        }

        public void setWarehouse_code(String warehouse_code) {
            this.warehouse_code = warehouse_code;
        }

        //是否为默认地址
        private boolean isSelected;
        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        public String getLimitLine() {
            return limitLine;
        }

        public void setLimitLine(String limitLine) {
            this.limitLine = limitLine;
        }

        public String getDetail_address() {
            return detail_address;
        }

        public void setDetail_address(String detail_address) {
            this.detail_address = detail_address;
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

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public List<AddressPersonListBean> getAddressPersonList() {
            return addressPersonList;
        }

        public void setAddressPersonList(List<AddressPersonListBean> addressPersonList) {
            this.addressPersonList = addressPersonList;
        }

        public static class AddressPersonListBean implements Serializable{
            /**
             * receiverId : 14104
             * createTime : 2018-08-09 16:47:13.0
             * receiverName : 李东
             * mobile : 18393512064
             * id : 555
             * addressId : 1008
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
