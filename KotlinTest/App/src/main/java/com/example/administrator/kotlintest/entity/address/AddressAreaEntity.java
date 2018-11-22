package com.example.administrator.kotlintest.entity.address;

import java.util.List;

/**
*@author : HaoBoy
*@date : 2018/8/17
*@description : 区域选择实体类
**/

public class AddressAreaEntity {


    /**
     * errorMessage : 成功
     * errorCode : 1
     * list : [{"code":"4","name":"重庆","level":101,"available":100,"address_id":4,"area_id":146,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"1","child_address_library":[]},{"code":"3","name":"天津","level":101,"available":100,"address_id":3,"area_id":144,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"2","child_address_library":[]},{"code":"2","name":"上海","level":101,"available":100,"address_id":2,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"1","child_address_library":[]},{"code":"1","name":"北京","level":101,"available":100,"address_id":1,"area_id":100,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"1","child_address_library":[]},{"code":"5","name":"河北","level":101,"available":100,"address_id":5,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"6","name":"山西","level":101,"available":100,"address_id":6,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"7","name":"河南","level":101,"available":100,"address_id":7,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"8","name":"辽宁","level":101,"available":100,"address_id":8,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"9","name":"吉林","level":101,"available":100,"address_id":9,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"10","name":"黑龙江","level":101,"available":100,"address_id":10,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"11","name":"内蒙古","level":101,"available":100,"address_id":11,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"12","name":"江苏","level":101,"available":100,"address_id":12,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"13","name":"山东","level":101,"available":100,"address_id":13,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"14","name":"安徽","level":101,"available":100,"address_id":14,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"15","name":"浙江","level":101,"available":100,"address_id":15,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"16","name":"福建","level":101,"available":100,"address_id":16,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"17","name":"湖北","level":101,"available":100,"address_id":17,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"18","name":"湖南","level":101,"available":100,"address_id":18,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"19","name":"广东","level":101,"available":100,"address_id":19,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"20","name":"广西","level":101,"available":100,"address_id":20,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"21","name":"江西","level":101,"available":100,"address_id":21,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"22","name":"四川","level":101,"available":100,"address_id":22,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"23","name":"海南","level":101,"available":100,"address_id":23,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"24","name":"贵州","level":101,"available":100,"address_id":24,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"25","name":"云南","level":101,"available":100,"address_id":25,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"26","name":"西藏","level":101,"available":100,"address_id":26,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"27","name":"陕西","level":101,"available":100,"address_id":27,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"28","name":"甘肃","level":101,"available":100,"address_id":28,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"29","name":"青海","level":101,"available":100,"address_id":29,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"30","name":"宁夏","level":101,"available":100,"address_id":30,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"31","name":"新疆","level":101,"available":100,"address_id":31,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"32","name":"台湾","level":101,"available":100,"address_id":32,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"42","name":"香港","level":101,"available":100,"address_id":33,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"43","name":"澳门","level":101,"available":100,"address_id":34,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]},{"code":"84","name":"钓鱼岛","level":101,"available":100,"address_id":35,"area_id":0,"zip_code":"","parent_code":"","seq_no":0,"created_at":"2018-07-27T12:36:05.000+0000","upate_time":"2018-07-27T12:36:05.000+0000","limit_line":20,"warehouse_code":"","child_address_library":[]}]
     */

    private String errorMessage;
    private int errorCode;
    private List<ListBean> list;

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * code : 4
         * name : 重庆
         * level : 101
         * available : 100
         * address_id : 4
         * area_id : 146
         * zip_code :
         * parent_code :
         * seq_no : 0
         * created_at : 2018-07-27T12:36:05.000+0000
         * upate_time : 2018-07-27T12:36:05.000+0000
         * limit_line : 20
         * warehouse_code : 1
         * child_address_library : []
         */

        private int code;
        private String name;
        private int level;
        private int available;
        private int address_id;
        private int area_id;
        private String zip_code;
        private String parent_code;
        private int seq_no;
        private String created_at;
        private String upate_time;
        private int limit_line;
        private String warehouse_code;
        private List<?> child_address_library;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }
        public void setChecked(boolean checked) {
            isChecked = checked;
        }
        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public int getAddress_id() {
            return address_id;
        }

        public void setAddress_id(int address_id) {
            this.address_id = address_id;
        }

        public int getArea_id() {
            return area_id;
        }

        public void setArea_id(int area_id) {
            this.area_id = area_id;
        }

        public String getZip_code() {
            return zip_code;
        }

        public void setZip_code(String zip_code) {
            this.zip_code = zip_code;
        }

        public String getParent_code() {
            return parent_code;
        }

        public void setParent_code(String parent_code) {
            this.parent_code = parent_code;
        }

        public int getSeq_no() {
            return seq_no;
        }

        public void setSeq_no(int seq_no) {
            this.seq_no = seq_no;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpate_time() {
            return upate_time;
        }

        public void setUpate_time(String upate_time) {
            this.upate_time = upate_time;
        }

        public int getLimit_line() {
            return limit_line;
        }

        public void setLimit_line(int limit_line) {
            this.limit_line = limit_line;
        }

        public String getWarehouse_code() {
            return warehouse_code;
        }

        public void setWarehouse_code(String warehouse_code) {
            this.warehouse_code = warehouse_code;
        }

        public List<?> getChild_address_library() {
            return child_address_library;
        }

        public void setChild_address_library(List<?> child_address_library) {
            this.child_address_library = child_address_library;
        }
    }
}
