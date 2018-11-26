package com.example.administrator.kotlintest.banner.mybanner;

import android.content.Context;
import android.widget.Scroller;

/**
 * Created by hexun on 2018/1/19.
 * <p>
 * 重写scroller修改viewpager滚动时长
 *
 * @author wdx
 */

public class ViewPagerScroller extends Scroller {
    private int newDuration = 1000;

    public ViewPagerScroller(Context context) {
        super(context);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, newDuration);
    }
}
