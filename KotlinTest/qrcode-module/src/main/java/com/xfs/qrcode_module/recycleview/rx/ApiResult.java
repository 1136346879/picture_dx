package com.xfs.qrcode_module.recycleview.rx;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author zhangpeiyuan
 * @date 2017/10/18
 */

public class ApiResult<T> implements Serializable {

    @SerializedName(
            value = "data",
            alternate = {
                    "datas",
                    "revdata",
                    "focus"
            })
    public T data;

    public int status;

    @SerializedName(
            value = "msg",
            alternate = {
                    "errorMsg",
                    "error"
            }
    )
    public String msg;

    public long serverTime;

    public boolean fromCache;

    public boolean isSuccess() {
        return status == 1;
    }


    public String getErrorMessage() {
        return msg;
    }


    public int getStatus() {
        return status;
    }


    public boolean isFromCache() {
        return fromCache;
    }

    public void setFromCache(boolean fromCache) {
        this.fromCache = fromCache;
    }

    @Override
    public String toString() {
        return "ApiResult{" +
                "data=" + data +
                ", status=" + status +
                ", msg='" + msg + '\'' +
                ", serverTime=" + serverTime +
                ", fromCache=" + fromCache +
                '}';
    }
}
