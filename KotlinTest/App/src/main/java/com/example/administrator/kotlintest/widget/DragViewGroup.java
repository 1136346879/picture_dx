package com.example.administrator.kotlintest.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author wdx 2018年3月1日15:47:05
 * 电台播放器拖拽
 */
public class DragViewGroup extends LinearLayout {
    private int  w;
    private int screenHeight;
    private int screenWidth;
    private ViewConfiguration configuration;
    float startX = 0;
    float startY = 0;
    private int parentTop = 0;

    public DragViewGroup(Context context) {
        this(context, null);
        configuration = ViewConfiguration.get(context);
    }

    public DragViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        configuration = ViewConfiguration.get(context);
    }

    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
        //减去下边的高度
        screenHeight = dm.heightPixels - 50;
        configuration = ViewConfiguration.get(context);
    }

    //定位
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //可以在这里确定这个viewGroup的：宽 = r-l.高 = b - t
        if (changed) {
            this.w = r - l;
            ViewGroup viewGroup = (ViewGroup) getParent();
            int[] out = new int[2];
            viewGroup.getLocationInWindow(out);
            parentTop = out[1];
        }
    }


    /**
     * 拦截子view的触摸事件
     * @param ev 事件
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float moveX = ev.getX();
                float moveY = ev.getY();
                if (moveX - startX > configuration.getScaledTouchSlop() || moveY - startY
                        > configuration.getScaledTouchSlop()) {
                    //移动状态拦截子view触摸事件
                    return true;
                }
                break;
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * 在该方法中处理拖拽逻辑
     * @param event  事件
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float lastX = 0;
        float lastY = 0;
        int setX;
        int setY;
        //展开状态点击时，不滑动
//        if (SharePreferenceUtils.getValue(getContext(),
//                PlayOpenStatus.PLAY_STATUS.name(),
//                Boolean.class)) {
//            return false;
//        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                lastX = event.getRawX();
                lastY = event.getRawY();
                setX = (int) (lastX - startX);
                setY = (int) (lastY - startY - parentTop);
                setX(setX);
                setY(setY);
                break;
            case MotionEvent.ACTION_UP:
                lastX = event.getRawX();
                lastY = event.getRawY();
                setX = (int) (lastX - startX);
                setY = (int) (lastY - startY - parentTop);
                if (lastX < 0 || lastX < screenWidth / 2) {
                    setX = 0;
                } else if (lastX >= screenWidth / 2) {
                    setX = screenWidth - w;
                }
                if (setY <= 0) {
                    setY = DensityUtil.dip2px(getContext(), 45);
                } else if (lastY > screenHeight - DensityUtil.dip2px(getContext(), 108)) {
                    setY = screenHeight - DensityUtil.dip2px(getContext(), 108)-getPaddingBottom();
                }
                setX(setX);
                setY(setY);
                break;
            default:
                break;
        }

        return super.onTouchEvent(event);
    }
}
