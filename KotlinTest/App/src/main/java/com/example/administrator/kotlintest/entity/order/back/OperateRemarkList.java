/**
  * Copyright 2018 bejson.com 
  */
package com.example.administrator.kotlintest.entity.order.back;
import java.util.Date;

/**
 * Auto-generated: 2018-10-17 11:27:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class OperateRemarkList {

    private int id;
    private long refund_id;
    private int operator_role;
    private int operate_userid;
    private String operate_username;
    private int operate_type;
    private int operate_reason;
    private int remark_type;
    private String refund_remark;
    private String created_at;
    private String updated_at;
    public void setId(int id) {
         this.id = id;
     }
     public int getId() {
         return id;
     }

    public void setRefund_id(long refund_id) {
         this.refund_id = refund_id;
     }
     public long getRefund_id() {
         return refund_id;
     }

    public void setOperator_role(int operator_role) {
         this.operator_role = operator_role;
     }
     public int getOperator_role() {
         return operator_role;
     }

    public void setOperate_userid(int operate_userid) {
         this.operate_userid = operate_userid;
     }
     public int getOperate_userid() {
         return operate_userid;
     }

    public void setOperate_username(String operate_username) {
         this.operate_username = operate_username;
     }
     public String getOperate_username() {
         return operate_username;
     }

    public void setOperate_type(int operate_type) {
         this.operate_type = operate_type;
     }
     public int getOperate_type() {
         return operate_type;
     }

    public void setOperate_reason(int operate_reason) {
         this.operate_reason = operate_reason;
     }
     public int getOperate_reason() {
         return operate_reason;
     }

    public void setRemark_type(int remark_type) {
         this.remark_type = remark_type;
     }
     public int getRemark_type() {
         return remark_type;
     }

    public void setRefund_remark(String refund_remark) {
         this.refund_remark = refund_remark;
     }
     public String getRefund_remark() {
         return refund_remark;
     }

    public void setCreated_at(String created_at) {
         this.created_at = created_at;
     }
     public String getCreated_at() {
         return created_at;
     }

    public void setUpdated_at(String updated_at) {
         this.updated_at = updated_at;
     }
     public String getUpdated_at() {
         return updated_at;
     }

}