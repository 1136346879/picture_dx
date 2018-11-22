package com.xfs.qrcode_module.event;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * @author yangyi 2018年8月7日14:41:04
 */

public class ScanResultEvent implements Serializable {

    /**
     * status：1  扫码成功  0  扫码失败
     */
    private int status;

    /**
     * 虚化背景
     */
    private Bitmap backgroundBitmap;

    private String[] params;

    public int getStatus() {
        return status;
    }

    public Bitmap getBackgroundBitmap() {
        return backgroundBitmap;
    }

    public ScanResultEvent(int status) {
        this.status = status;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public ScanResultEvent(Bitmap backgroundBitmap) {
        this.backgroundBitmap = backgroundBitmap;
    }
}
