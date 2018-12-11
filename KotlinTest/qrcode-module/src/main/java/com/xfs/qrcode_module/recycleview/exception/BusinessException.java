package com.xfs.qrcode_module.recycleview.exception;


import com.hexun.base.exception.BaseException;

/**
 * @author zhangpeiyuan
 * @date 2017/10/18
 */

public class BusinessException extends BaseException {

    protected String errorMessage;
    protected int errorCode;

    public BusinessException(int code,String error){
        super(error);
        errorMessage = error;
        errorCode = code;
    }


    @Override
    public String toString() {
        return errorMessage;
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
}
