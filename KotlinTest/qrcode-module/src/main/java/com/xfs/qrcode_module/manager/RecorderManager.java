package com.xfs.qrcode_module.manager;

import android.content.Context;


/**
 * QRCode相关埋点管理
 *
 * @author yangyi 2018年8月9日15:02:43
 */

public class RecorderManager {

    private static RecorderManager recorderManager;

    public static synchronized RecorderManager getInstance() {
        if (recorderManager == null) {
            recorderManager = new RecorderManager();
        }
        return recorderManager;
    }

    private RecorderManager() {
    }

    /**
     * 点击事件	PC看全文立即阅读	APP扫码PC看全文后弹出框上“立即阅读”按钮点击	APP扫码PC看全文后弹出框	事件名称	点击事件
     * 事件功能	PC看全文立即阅读
     * 用户ID	$UID
     * 设备ID	$IID
     */
//    public void recorderReadNowClick(Context context) {
//        EventRecorder.ClickEvent clickEvent = EventRecorder.obtainClickEvent("PC看全文立即阅读");
//        EventRecorder.onClickEvent(context, clickEvent);
//    }

    /**
     * 点击事件	PC看全文授权电脑阅读	APP扫码PC看全文后弹出框上“授权电脑阅读”按钮点击	APP扫码PC看全文后弹出框	事件名称	点击事件
     * 事件功能	PC看全文授权电脑阅读
     * 用户ID	$UID
     * 设备ID	$IID
     */
//    public void recorderCheckAppReadClick(Context context) {
//        EventRecorder.ClickEvent clickEvent = EventRecorder.obtainClickEvent("PC看全文授权电脑阅读");
//        EventRecorder.onClickEvent(context, clickEvent);
//    }

    /**
     * 点击事件	PC看全文重新扫码	APP扫码PC看全文后弹出框上“重新扫码”按钮点击	APP扫码PC看全文后弹出框	事件名称	点击事件
     * 事件功能	PC看全文重新扫码
     * 用户ID	$UID
     * 设备ID	$IID
     */
//    public void recorderReScanClick(Context context) {
//        EventRecorder.ClickEvent clickEvent = EventRecorder.obtainClickEvent("PC看全文重新扫码");
//        EventRecorder.onClickEvent(context, clickEvent);
//    }

    /**
     * 浏览事件	APP扫码PC看全文后弹出框	APP扫码PC看全文后弹出框弹出	APP扫码PC看全文后弹出框	事件名称	浏览事件
     * 页面名称	扫码PC看全文后弹出框
     * 用户ID	$UID
     * 设备ID	$IID
     */
//    public void recorderScanQRCodePage(Context context) {
//        EventRecorder.PageEvent pageEvent = EventRecorder.obtainPageEvent("扫码PC看全文后弹出框");
//        EventRecorder.onPageEvent(context, pageEvent);
//    }

    /**
     * 点击事件	APP扫一扫点击	APP扫一扫按钮点击	APP扫一扫按钮	事件名称	点击事件
     * 事件功能	APP扫一扫点击
     * 用户ID	$UID
     * 设备ID	$IID
     */
//    public void recorderQRCodeOpenClick(Context context) {
//        EventRecorder.ClickEvent clickEvent = EventRecorder.obtainClickEvent("APP扫一扫点击");
//        EventRecorder.onClickEvent(context, clickEvent);
//    }
}
