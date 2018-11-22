package com.example.administrator.kotlintest.entity.address;

import java.util.List;

/**
 * Created by HaoBoy on 2018/8/23.
 * 收货地址-编辑查看实体类
 */

public class AddressEditEntity {


    /**
     * errorMessage : 查看会员收货地址成功!
     * errorCode : 1
     * shippingAddress : {"member_id":15,"create_time":"2018-08-30T05:55:41.000+0000","trade_time":"2018-09-23T12:25:14.000+0000","detail_address":"888","warehouse_code":"1","city_code":"2800","addressPersonList":[{"create_time":"2018-08-30T05:55:41.000+0000","receiver_id":0,"address_id":1114,"mobile":"13718557895","receiver_name":"杨晓霞","id":608,"status":10}],"add_alias":" ","is_default":"2","province_code":"1","street_code":"","office_phone":"","ship_add_id":1114,"update_time":"2018-09-23T12:25:14.000+0000","cityName":"海淀区","province_id":1,"areaName":"五环内","district_code":"2848","district_id":43970,"provinceName":"北京","street_id":0,"email":"","city_id":43962,"status":"2"}
     * token :
     */

    private String errorMessage;
    private int errorCode;
    private ShippingAddressBean shippingAddress;
    private String token;

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

    public ShippingAddressBean getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddressBean shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class ShippingAddressBean {
        /**
         * member_id : 15
         * create_time : 2018-08-30T05:55:41.000+0000
         * trade_time : 2018-09-23T12:25:14.000+0000
         * detail_address : 888
         * warehouse_code : 1
         * city_code : 2800
         * addressPersonList : [{"create_time":"2018-08-30T05:55:41.000+0000","receiver_id":0,"address_id":1114,"mobile":"13718557895","receiver_name":"杨晓霞","id":608,"status":10}]
         * add_alias :
         * is_default : 2
         * province_code : 1
         * street_code :
         * office_phone :
         * ship_add_id : 1114
         * update_time : 2018-09-23T12:25:14.000+0000
         * cityName : 海淀区
         * province_id : 1
         * areaName : 五环内
         * district_code : 2848
         * district_id : 43970
         * provinceName : 北京
         * street_id : 0
         * email :
         * city_id : 43962
         * status : 2
         */

        private int member_id;
        private String streetName;
        private String create_time;
        private String trade_time;
        private String detail_address;
        private String warehouse_code;
        private String city_code;
        private String add_alias;
        private String is_default;
        private String province_code;
        private String street_code;
        private String office_phone;
        private int ship_add_id;
        private String update_time;
        private String cityName;
        private int province_id;
        private String areaName;
        private String district_code;
        private int district_id;
        private String provinceName;
        private int street_id;
        private String email;
        private int city_id;
        private String status;
        private List<AddressPersonListBean> addressPersonList;

        public int getMember_id() {
            return member_id;
        }

        public void setMember_id(int member_id) {
            this.member_id = member_id;
        }

        public String getStreetName() {
            return streetName;
        }

        public void setStreetName(String streetName) {
            this.streetName = streetName;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getTrade_time() {
            return trade_time;
        }

        public void setTrade_time(String trade_time) {
            this.trade_time = trade_time;
        }

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

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
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

        public String getDistrict_code() {
            return district_code;
        }

        public void setDistrict_code(String district_code) {
            this.district_code = district_code;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<AddressPersonListBean> getAddressPersonList() {
            return addressPersonList;
        }

        public void setAddressPersonList(List<AddressPersonListBean> addressPersonList) {
            this.addressPersonList = addressPersonList;
        }

        public static class AddressPersonListBean {
            /**
             * create_time : 2018-08-30T05:55:41.000+0000
             * receiver_id : 0
             * address_id : 1114
             * mobile : 13718557895
             * receiver_name : 杨晓霞
             * id : 608
             * status : 10
             */

            private String create_time;
            private int receiver_id;
            private int address_id;
            private String mobile;
            private String receiver_name;
            private int id;
            private int status;

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public int getReceiver_id() {
                return receiver_id;
            }

            public void setReceiver_id(int receiver_id) {
                this.receiver_id = receiver_id;
            }

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getReceiver_name() {
                return receiver_name;
            }

            public void setReceiver_name(String receiver_name) {
                this.receiver_name = receiver_name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
