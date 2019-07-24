package com.example.administrator.kotlintest.util;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by kangf on 2018/7/30.
 */
public class SoftInputUtil {

    /**
     * 显示软键盘
     *
     * @param context
     */
    public static void showSoftInput(Context context) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 显示软键盘
        if (imm != null)
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /*
     * 显示软键盘
     *
     * @param context
     */
//    public static void showSoftInput(Context context, View view) {
//        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 显示软键盘
//        if (imm != null)
//            imm.showSoftInput(view, 0);
//    }

    /*
     * 隐藏软键盘
     *
     * @param context
     * @param view
     */
//    public static void hideSoftInput(Context context, View view) {
//        InputMethodManager immHide = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE); // 隐藏软键盘
//        if (immHide != null)
//            immHide.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }

    /**
     * 判断在什么情况下隐藏软键盘，点击EditText视图显示软键盘
     *
     * @param view  Incident event
     * @param event
     * @return
     */
    public static boolean isShouldHideSoftKeyBoard(View view, MotionEvent event) {
        if (view instanceof EditText) {
            int[] l = {0, 0};
            view.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + view.getHeight(), right = left
                    + view.getWidth();
            return !(event.getX() > left) || !(event.getX() < right)
                    || !(event.getY() > top) || !(event.getY() < bottom);
        }
        // if the focus is EditText,ignore it;
        return false;
    }
}
