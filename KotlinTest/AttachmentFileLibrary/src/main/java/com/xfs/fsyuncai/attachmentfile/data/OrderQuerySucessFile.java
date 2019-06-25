package com.xfs.fsyuncai.attachmentfile.data;

import java.util.List;

public class OrderQuerySucessFile {

    /**
     * msg : SUCCESS
     * errorCode : 0
     * sub_code : null
     * sub_msg : null
     * data : {"operate_flag":false,"operateOrderAttachmentList":[{"id":4,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530151545169724.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:40"}],"unOperateOrderAttachmentList":[{"id":1,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530110846647619.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:12"},{"id":2,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530151545169724.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:12"},{"id":3,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530110846647619.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:40"},{"id":4,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530151545169724.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:40"}]}
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
         * operate_flag : false
         * operateOrderAttachmentList : [{"id":4,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530151545169724.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:40"}]
         * unOperateOrderAttachmentList : [{"id":1,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530110846647619.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:12"},{"id":2,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530151545169724.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:12"},{"id":3,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530110846647619.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:40"},{"id":4,"member_id":1518,"login_account":"yangfanxd1","order_id":"19101914084","attachment_url":"https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530151545169724.jpg","attachment_name":"20190322143736.jpg","attachment_status":10,"created_at":"2019-05-30 15:20:40"}]
         */

        private boolean operate_flag;
        private List<OrderAttachmentListBean> operateOrderAttachmentList;
        private List<OrderAttachmentListBean> unOperateOrderAttachmentList;

        public boolean isOperate_flag() {
            return operate_flag;
        }

        public void setOperate_flag(boolean operate_flag) {
            this.operate_flag = operate_flag;
        }

        public List<OrderAttachmentListBean> getOperateOrderAttachmentList() {
            return operateOrderAttachmentList;
        }

        public void setOperateOrderAttachmentList(List<OrderAttachmentListBean> operateOrderAttachmentList) {
            this.operateOrderAttachmentList = operateOrderAttachmentList;
        }

        public List<OrderAttachmentListBean> getUnOperateOrderAttachmentList() {
            return unOperateOrderAttachmentList;
        }

        public void setUnOperateOrderAttachmentList(List<OrderAttachmentListBean> unOperateOrderAttachmentList) {
            this.unOperateOrderAttachmentList = unOperateOrderAttachmentList;
        }


    }
}
