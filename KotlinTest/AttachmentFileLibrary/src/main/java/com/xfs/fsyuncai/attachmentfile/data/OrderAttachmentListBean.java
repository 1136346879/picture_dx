package com.xfs.fsyuncai.attachmentfile.data;

import java.io.Serializable;

public class OrderAttachmentListBean implements Serializable {

    /**
     * id : 4
     * member_id : 1518  1519
     * login_account : yangfanxd1
     * order_id : 19101914084
     * attachment_url : https://fsyuncai-file.oss-cn-beijing.aliyuncs.com/order/20190530151545169724.jpg
     * attachment_name : 20190322143736.jpg
     * attachment_status : 10
     * created_at : 2019-05-30 15:20:40
     */
    private int id;
    private int member_id;
    private String login_account;
    private String order_id;
    private String attachment_url;
    private String attachment_name;
    private int attachment_status;
    private String created_at;
    private String originPath;
    private Boolean checkBox = false;
    private Boolean NetOrLocal = false;  //本地  true   net  false

    public String getOriginPath() {
        return originPath;
    }

    public void setOriginPath(String originPath) {
        this.originPath = originPath;
    }

    public Boolean getNetOrLocal() {
        return NetOrLocal;
    }

    public void setNetOrLocal(Boolean netOrLocal) {
        NetOrLocal = netOrLocal;
    }

    public Boolean getCheckBox() {
        return checkBox;
    }

    public void setCheckBox(Boolean checkBox) {
        this.checkBox = checkBox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getLogin_account() {
        return login_account;
    }

    public void setLogin_account(String login_account) {
        this.login_account = login_account;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getAttachment_url() {
        return attachment_url;
    }

    public void setAttachment_url(String attachment_url) {
        this.attachment_url = attachment_url;
    }

    public String getAttachment_name() {
        return attachment_name;
    }

    public void setAttachment_name(String attachment_name) {
        this.attachment_name = attachment_name;
    }

    public int getAttachment_status() {
        return attachment_status;
    }

    public void setAttachment_status(int attachment_status) {
        this.attachment_status = attachment_status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
