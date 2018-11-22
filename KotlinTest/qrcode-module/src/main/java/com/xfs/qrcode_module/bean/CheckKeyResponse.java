package com.xfs.qrcode_module.bean;

/**
 * @author yangyi 2018年8月8日17:45:25
 */

public class CheckKeyResponse {


    /**
     * status : 1
     * code : ok
     */

    private int status;
    private String code;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
