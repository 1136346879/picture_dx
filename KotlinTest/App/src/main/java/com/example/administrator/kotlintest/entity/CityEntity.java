package com.example.administrator.kotlintest.entity;

import java.util.List;

/**
 * Created by HaoBoy on 2018/8/18.
 */

public class CityEntity {

    private String errorMessage;
    private int errorCode;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * item : A
         * list : [{"id":2,"address_id":521,"warehouse_id":1,"code":"15945","name":"阿拉尔","warehouse_code":"1"},{"id":8,"address_id":479,"warehouse_id":1,"code":"3970","name":"阿里地区","warehouse_code":"1"},{"id":45,"address_id":540,"warehouse_id":1,"code":"2770","name":"澳门","warehouse_code":"1"},{"id":48,"address_id":537,"warehouse_id":1,"code":"2744","name":"阿勒泰地区","warehouse_code":"1"},{"id":56,"address_id":529,"warehouse_id":1,"code":"2675","name":"阿克苏地区","warehouse_code":"1"},{"id":88,"address_id":491,"warehouse_id":1,"code":"2476","name":"安康","warehouse_code":"1"},{"id":116,"address_id":455,"warehouse_id":1,"code":"2189","name":"安顺","warehouse_code":"1"},{"id":125,"address_id":412,"warehouse_id":1,"code":"2070","name":"阿坝州","warehouse_code":"1"},{"id":242,"address_id":303,"warehouse_id":1,"code":"1140","name":"安庆","warehouse_code":"1"},{"id":281,"address_id":263,"warehouse_id":1,"code":"891","name":"阿拉善盟","warehouse_code":"1"},{"id":322,"address_id":220,"warehouse_id":2,"code":"579","name":"鞍山","warehouse_code":"2"},{"id":334,"address_id":211,"warehouse_id":4,"code":"468","name":"安阳","warehouse_code":"4"}]
         */

        private String item;
        private List<ListBean> list;

        public String getItem() {
            return item;
        }

        public void setItem(String item) {
            this.item = item;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 2
             * address_id : 521
             * warehouse_id : 1
             * code : 15945
             * name : 阿拉尔
             * warehouse_code : 1
             */

            private int id;
            private int address_id;
            private int warehouse_id;
            private String code;
            private String name;
            private String warehouse_code;
            private String sectionTag;//组第一条标记
            private boolean isLocationCity;//是否是当前定位城市

            public ListBean(int address_id, int warehouse_id, String code, String name) {
                this.address_id = address_id;
                this.warehouse_id = warehouse_id;
                this.code = code;
                this.name = name;
            }

            public ListBean(String code, String name) {
                this.code = code;
                this.name = name;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public int getWarehouse_id() {
                return warehouse_id;
            }

            public void setWarehouse_id(int warehouse_id) {
                this.warehouse_id = warehouse_id;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getWarehouse_code() {
                return warehouse_code;
            }

            public void setWarehouse_code(String warehouse_code) {
                this.warehouse_code = warehouse_code;
            }

            public String getSectionTag() {
                return sectionTag;
            }

            public void setSectionTag(String sectionTag) {
                this.sectionTag = sectionTag;
            }

            public boolean isLocationCity() {
                return isLocationCity;
            }

            public void setLocationCity(boolean locationCity) {
                isLocationCity = locationCity;
            }
        }
    }
}
