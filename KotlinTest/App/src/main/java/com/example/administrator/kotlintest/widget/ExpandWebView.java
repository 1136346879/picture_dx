package com.example.administrator.kotlintest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.webkit.WebView;


/**
 * 当开启JS脚本后，WebView内部会持有Context很长时间，destroy也不能使其释放，这里用ApplicationContext防止内存溢出
 * Created by ZY on 2017/7/24.
 */

public class ExpandWebView extends WebView {

    public ExpandWebView(Context context) {
        super(context.getApplicationContext());
    }

    public ExpandWebView(Context context, AttributeSet attrs) {
        super(context.getApplicationContext(), attrs);
    }

    public ExpandWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context.getApplicationContext(), attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public void destroy() {
        ViewGroup group = (ViewGroup) getParent();
        if (group != null) {
            group.removeAllViews();
        }
        removeAllViews();
        super.destroy();
    }
}
