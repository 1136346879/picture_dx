package com.example.administrator.kotlintest.banner.mybanner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.example.administrator.kotlintest.banner.mybanner.imageLoader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hexun on 2017/4/25.
 *
 * @author wdx
 */
public class ImageBannerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> list;
    private ViewPager mViewPager;
    private RunnableTask runnableTask;
    private ImageBanner.BannerClickListener listener;
    private boolean isOnTouch = true;
    private List<OnTouchEvent> touchEvents = new ArrayList<>();
    private Drawable drawable = new ColorDrawable(Color.parseColor("#f5f5f5"));
    private boolean clear = false;
    private List<ImageView> imageViews;
    private int screenWidth;

    public ImageBannerAdapter(Context context, List<String> list, ViewPager viewPager) {
        this.mContext = context;
        this.list = list;
        this.mViewPager = viewPager;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;
    }

    @Override
    public int getCount() {
        if (list == null || list.isEmpty()) {
            return 0;
        } else if (list.size() == 1) {
            return 1;
        }
        return Integer.MAX_VALUE;
    }

    public int getDataCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        if (clear || null == list || list.isEmpty() || null == imageViews) {
            return null;
        }
        int index = list.size();
        // Image布局
        ImageView view = null;
        // 加载图片
        if (index != 1) {
            if ((position % 4) < imageViews.size()) {
                view = imageViews.get(position % 4);
            }
        } else {
            if (position % index < imageViews.size()) {
                view = imageViews.get(position % index);
            }
        }
        if (null == view) {
            view = new ImageView(mContext);
            ImageLoader.with(mContext).load(list.get(position % index)).placeholder(drawable).tag(hashCode()).fit().into(view);
        } else {
            ImageLoader.with(mContext).load(list.get(position % index)).placeholder(drawable).tag(hashCode()).fit().into(view);
        }
        container.addView(view);
        if (isOnTouch) {
            OnTouchEvent touchEvent;
            if (touchEvents.size() > position % index) {
                touchEvent = touchEvents.get(position % index);
                if (touchEvent == null) {
                    touchEvent = new OnTouchEvent();
                    touchEvent.setFinalPosition(position % index);
                    touchEvents.add(touchEvent);
                }
            } else {
                touchEvent = new OnTouchEvent();
                touchEvent.setFinalPosition(position % index);
                touchEvents.add(touchEvent);
            }
            view.setOnTouchListener(touchEvent);
        } else {
            final int finalPosition = position % index;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.performClick(finalPosition);
                    }
                }
            });
        }
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (object instanceof View) {
            View view = (View) object;
            view.clearFocus();
            view.setOnTouchListener(null);
            container.removeView(view);
        }
        int index = -1;
        if (null != list) {
            index = list.size();
        }
        View temp = null;
        if (null != imageViews) {
            if (index != 1) {
                temp = imageViews.get(position % 4);
            } else {
                temp = imageViews.get(position % index);
            }
        }
        ViewParent parent = null;
        if (null != temp) {
            parent = temp.getParent();
        }

        if (null != parent) {
            ((ViewGroup) parent).removeView(temp);
        }
    }

    private Handler handler = new Handler() {
        int[] ints = new int[2];

        @Override
        public void handleMessage(android.os.Message msg) {
            if (mViewPager == null || !mViewPager.hasWindowFocus()) {
                return;
            }
            int position = mViewPager.getCurrentItem() + 1;
            mViewPager.getLocationInWindow(ints);
            if (ints[0] > -screenWidth && ints[0] < screenWidth) {
                // 让viewpager按照维护的索引去指向界面
                mViewPager.setCurrentItem(position);
                // 一直去维护runnable任务去滚动起来
                startRoll();
            }
        }
    };

    public void startRoll() {
        notifyDataSetChanged();
        // 2,滚动起来(1,定时器2,handler)
        if (null == list || list.size() <= 1) {
            return;
        }
        if (runnableTask == null) {
            runnableTask = new RunnableTask(handler);
        }
        handler.postDelayed(runnableTask, 4000);
    }

    public void stopRoll() {
        if (runnableTask != null) {
            handler.removeCallbacks(runnableTask);
        }
    }

    public void setList(List<String> list) {
        if (this.list != null && !this.list.isEmpty()) {
            clear = true;
        }
        if (null != list) {
            initImageView(list);
        }
        this.list = list;
        notifyDataSetChanged();
        if (list != null && list.size() > 1) {
            resumeStartScroll();
        }
    }

    private void initImageView(List<String> list) {
        if (imageViews == null) {
            imageViews = new ArrayList<>();
        } else {
            //获取预加载的下一个view
            //替换掉已经加载出来的上个缓存的图
            // 防止出现banner数据更新但图已添加到viewpager而没有及时更新问题
            int position = mViewPager.getCurrentItem() + 1;
            int index = imageViews.size();
            if (index != 1) {
                ImageView view = imageViews.get(position % 4);
                ImageLoader.with(mContext).load(list.get(position % list.size())).placeholder(drawable).tag(hashCode()).fit().into(view);
            }
            imageViews.clear();
        }
        int temp = list.size();
        if (temp != 1) {
            temp = 4;
        }
        for (int i = 0; i < temp; i++) {
            imageViews.add(new ImageView(mContext));
        }
    }

    /**
     * 回收无用监听和引用
     * 防止无法自动回收
     */
    public void recycle() {
        handler.removeCallbacks(runnableTask);
        handler = null;
        mViewPager = null;
        touchEvents.clear();
        imageViews.clear();
    }


    public void clearList() {
        ImageLoader.with(mContext).cancelTag(hashCode());
        stopRoll();
        clearImageCache();
    }

    private void clearImageCache() {
        if (null != list) {
            for (String url : list) {
                ImageLoader.with(mContext).invalidate(url);
            }
        }
    }


    /**
     * 重新开始滚动
     */
    public void resumeStartScroll() {
        if (list.size() > 1) {
            cancleHandle();
            startRoll();
        }
    }

    public void cancleHandle() {
        handler.removeCallbacksAndMessages(null);
    }

    public void setBannerClickListener(ImageBanner.BannerClickListener listener) {
        this.listener = listener;
    }

    class OnTouchEvent implements View.OnTouchListener {
        private long downTime;
        private int finalPosition;

        public void setFinalPosition(int finalPosition) {
            this.finalPosition = finalPosition;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    // 轮播图不再滚动,取消维护的任务,发送的消息
                    handler.removeCallbacksAndMessages(null);
                    // 1,按下的坐标点
                    downTime = System.currentTimeMillis();
                    break;
                case MotionEvent.ACTION_MOVE:
                    break;
                case MotionEvent.ACTION_UP:
                    // 让轮播图再次滚动起来
                    int timeSpace = 500;
                    if (System.currentTimeMillis() - downTime < timeSpace && listener != null) {
                        listener.performClick(finalPosition);
                    }
                    startRoll();
                    break;
                case MotionEvent.ACTION_CANCEL:
                    startRoll();
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    /**
     * 移除当前的监听
     */
    public void removeBannerClickListener() {
        this.listener = null;
        touchEvents.clear();
    }

    @Override
    public int getItemPosition(Object object) {
        if (clear) {
            clear = false;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}