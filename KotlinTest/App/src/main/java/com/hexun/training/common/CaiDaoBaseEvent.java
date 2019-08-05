package com.hexun.training.common;

import android.view.View;


/**
 * Created by hexun on 2016/12/14.
 */
public class CaiDaoBaseEvent {


    /**
     * 登录成功后发送的事件，事件会携带启动{@link LocalLoginActivity}时的ID。
     */
    public static class LoginSuccessEvent {
        private int loginID;

        public static LoginSuccessEvent obtain(int id) {
            LoginSuccessEvent loginSuccessEvent = new LoginSuccessEvent();
            return loginSuccessEvent.setLoginID(id);
        }

        /**
         * 跳转登录页面时的ID。
         *
         * @return
         */
        public int getLoginID() {
            return loginID;
        }

        public LoginSuccessEvent setLoginID(int loginID) {
            this.loginID = loginID;
            return this;
        }
    }

    public static class ReceivePushEvent {
        private String text;
        private String url;

        public ReceivePushEvent(String text, String url) {
            this.text = text;
            this.url = url;
        }

        public String getText() {
            return text;
        }

        public String getUrl() {
            return url;
        }
    }

    /**
     * 退出登录。
     */
    public static class LogoutEvent {


    }

    /**
     * 登录状态失效
     */
    public static class LoginInvalidEvent {
    }

    /**
     * 登录操作开始
     */
    public static class LoginStartEvent {
    }

    /**
     * 动态配置Event
     */
//    public static class UpgradeConfigEvent {
//        private UpgradeConfig config;
//
//        public UpgradeConfigEvent(UpgradeConfig config) {
//            this.config = config;
//        }
//
//        public UpgradeConfig getConfig() {
//            return config;
//        }
//    }

    /**
     * 版本升级检查Event
     */
//    public static class UpgradeSettingEvent {
//
//        private UpgradeSetting setting;
//        /**
//         * 升级状态
//         * STAUTS_CHECK_NEW_APK_NO_UPGRADE：无更新
//         * STATUS_CHECK_NEW_APK_HAS_UPGRADE：有更新 未下载
//         * STATUS_CHECK_NEW_APK_HAS_DOWNLOAD：有更新 已下载
//         * STATUS_CHECK_NEW_APK_MANUAL_DOWNLOADING：有更新 正在下载 主动下载
//         */
//        private int status;
//
//        public UpgradeSettingEvent(UpgradeSetting setting) {
//            this.setting = setting;
//        }
//
//        public UpgradeSettingEvent(UpgradeSetting setting, int status) {
//            this.setting = setting;
//            this.status = status;
//        }
//
//        public UpgradeSetting getSetting() {
//            return setting;
//        }
//
//        public int getStatus() {
//            return status;
//        }
//    }

    public static class UPushRegisterEvent {
        public boolean isSuccess;

        public UPushRegisterEvent(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }
    }

    /**
     * 我的自选股发生变化时会发出此事件
     */
    public static class SelectedStockEvent {
        public SelectedStockEvent() {

        }
    }

    /**
     * 我的自选股发生变化时会发出此事件
     */
    public static class SelectedHistoryOptional {
        public SelectedHistoryOptional() {
        }
    }

    /**
     * 我的自选股发生变化时会发出此事件
     */
    public static class FromMyFavorite {
        public FromMyFavorite() {
        }
    }

    public static class AppForegroundChangedEvent {
        boolean isForeground;

        public AppForegroundChangedEvent(boolean isForeground) {
            this.isForeground = isForeground;
        }

        public boolean isForeground() {
            return isForeground;
        }
    }

    /**
     * 我要投诉验证提交事件
     */
    public static class InvestCommitEvent {
        private View view;
        private String tips;

        public View getView() {
            return view;
        }

        public String getTips() {
            return tips;
        }

        public InvestCommitEvent(View view, String tips) {
            this.view = view;
            this.tips = tips;
        }
    }

    /**
     * 我要投诉验证提交事件
     */
    public static class InvestCategorySyncEvent {
        public InvestCategorySyncEvent() {
        }
    }

    /**
     * 身份认证成功事件
     */
    public static class AuthenticaEvent {
        public AuthenticaEvent() {
        }
    }
}
