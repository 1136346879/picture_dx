package com.example.administrator.kotlintest.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author yangyi 2017年10月18日11:04:48
 */

public class NoScrollingViewPager extends ViewPager {

    public NoScrollingViewPager(Context context) {
        super(context);
    }

    public NoScrollingViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
