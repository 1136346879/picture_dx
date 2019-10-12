package com.example.administrator.kotlintest.banner.mybanner;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Scroller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hexun on 2016/04/25.
 * 自定义
 * @author wdx
 */
public class ImageBanner extends FrameLayout {
    protected ViewPager mViewPager;
    private ImageBannerAdapter mImageBannerAdapter;
    private Context mContext;
    private IndicatorAdapter adapter;
    private BannerClickListener listener;
    /**
     * banner高度，默认屏幕宽度一半
     */
    private int bannerHeight;
    private Consumer<Throwable> throwable = throwable -> {
        removeAllViews();
        postInvalidate();
    };
    private boolean isFirst = true;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getChildCount() == 0) {
            super.onMeasure(widthMeasureSpec, 0);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public ImageBanner(Context context) {
        super(context);
        this.mContext = context;
    }

    public ImageBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public ImageBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context) {
        mViewPager = new ViewPager(context);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        mViewPager.setLayoutParams(layoutParams);
        initScroller(mViewPager);
        addView(mViewPager);
        setAdapter(context);
    }

    private void setAdapter(Context context) {
        mImageBannerAdapter = new ImageBannerAdapter(context, null, mViewPager);
        mViewPager.setAdapter(mImageBannerAdapter);
        if (null != listener) {
            mImageBannerAdapter.setBannerClickListener(listener);
        }
    }

    private void initScroller(ViewPager pager) {
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            Scroller scroller = new ViewPagerScroller(getContext());
            field.set(pager, scroller);
        } catch (NoSuchFieldException e) {
            Log.d("反射设置banner滚动异常", e.getMessage());
        } catch (IllegalAccessException e) {
            Log.d("反射设置banner滚动异常", e.getMessage());
        }
    }

    /**
     * 设置banner屏占比
     *
     * @param percent 传入百分比
     */
    public void setBannerPercent(float percent) {
        bannerHeight = (int) (percent * mContext.getResources().getDisplayMetrics().widthPixels);
        setBannerHeight(bannerHeight);
    }

    private void setBannerHeight(int bannerHeight) {
        LayoutParams params = (LayoutParams) mViewPager.getLayoutParams();
        if (null != params) {
            params.height = bannerHeight;
            mViewPager.setLayoutParams(params);
        }
    }


    /**
     * 设置指示器适配器
     *
     * @param adapter
     */
    public void setIndicatorAdapter(IndicatorAdapter adapter) {
        this.adapter = adapter;
    }

    public void setList(@NonNull List<String> list) {
        checkList(list).subscribe(new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) throws Exception {
                reAddView();
                setBannerHeight(bannerHeight == 0 ? (getContext().getResources().getDisplayMetrics().widthPixels / 2) : bannerHeight);
                mImageBannerAdapter.setList(strings);
                adapter = createIndicatorAdapter();
                mViewPager.clearOnPageChangeListeners();
                addOnPageChangeListener(adapter);
                adapter.startAdapter();
                isFirst = false;
            }
        }, throwable);
    }

    public void clearList() {
        if (mImageBannerAdapter != null) {
            mImageBannerAdapter.clearList();
            if (null != adapter) {
                mViewPager.removeOnPageChangeListener(adapter);
            }
        }
    }

    public void setList(@NonNull final List<String> imgUrls, @NonNull final List<String> titles) {
        checkList(imgUrls).zipWith(checkList(titles), new BiFunction<List<String>, List<String>, Boolean>() {
            @Override
            public Boolean apply(List<String> imgUrls, List<String> titles) throws Exception {
                reAddView();
                mViewPager.clearOnPageChangeListeners();
                mImageBannerAdapter.setList(imgUrls);
                adapter = createIndicatorAdapter();
                addOnPageChangeListener(adapter);
                if (titles.size() == imgUrls.size()) {
                    adapter.setTitleList(titles);
                }
                adapter.startAdapter();
                isFirst = false;
                return true;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                setBannerHeight(bannerHeight == 0 ? (getContext().getResources().getDisplayMetrics().widthPixels / 2) : bannerHeight);
            }
        }, throwable);
    }

    protected void addOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mViewPager.addOnPageChangeListener(listener);
    }

    /**
     * 筛选list前三个item
     *
     * @param list
     * @return
     */
    private Observable<List<String>> checkList(List<String> list) {
        return Observable.just(list).flatMap(new Function<List<String>, ObservableSource<List<String>>>() {
            int size = 4;

            @Override
            public ObservableSource<List<String>> apply(List<String> strings) throws Exception {
                if (strings.isEmpty()) {
                    return Observable.error(new RuntimeException("数据为0"));
                } else if (strings.size() > size) {
                    List<String> temp = new ArrayList<>();
                    temp.add(strings.get(0));
                    temp.add(strings.get(1));
                    temp.add(strings.get(2));
                    temp.add(strings.get(3));
                    return Observable.just(temp);
                }
                return Observable.just(strings);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 重新添加view
     */
    private void reAddView() {
        if (getChildCount() == 0) {
            init(getContext());
            if (null != mImageBannerAdapter && null != listener) {
                setBannerClickListener(listener);
            }
        }
    }

    public void setBannerClickListener(BannerClickListener listener) {
        this.listener = listener;
    }

    public void setCancleHandle() {
        mImageBannerAdapter.cancleHandle();
    }

    public void setStartRoll() {
        if (null != mImageBannerAdapter && !isFirst) {
            mImageBannerAdapter.resumeStartScroll();
        }
    }

    public interface BannerClickListener {
        /**
         * banner点击事件
         *
         * @param position
         */
        void performClick(int position);
    }

    /**
     * 创建模板adapter或者返回设置的adapter
     *
     * @return
     */
    public IndicatorAdapter createIndicatorAdapter() {
        if (null == this.adapter) {
            adapter = new CountIndicatorAdapter(getContext(), mViewPager);
            ((CountIndicatorAdapter) adapter).setIndicatorType(IndicatorAdapter.DefaultIndicatorAdapter.TYPE_INDICATOR_RECT);
        }
        return adapter;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (null != mImageBannerAdapter && !isFirst) {
            mImageBannerAdapter.resumeStartScroll();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (null != mImageBannerAdapter) {
            mImageBannerAdapter.cancleHandle();
        }
    }

    public ViewPager getViewPager() {
        return mViewPager;
    }

    public @Nullable
    IndicatorAdapter getAdapter() {
        return adapter;
    }

    public void recycle() {
        if (null != mImageBannerAdapter) {
            mImageBannerAdapter.recycle();
            mImageBannerAdapter = null;
        }
        removeAllViews();
        removeBannerClickListener();
        mContext = null;
        adapter = null;
        mViewPager = null;
    }

    /**
     * 移除监听回调
     */
    public void removeBannerClickListener() {
        listener = null;
        if (null != mImageBannerAdapter) {
            mImageBannerAdapter.removeBannerClickListener();
        }
    }

    private class CountIndicatorAdapter extends IndicatorAdapter.DefaultIndicatorAdapter {

        public CountIndicatorAdapter(Context context, ViewPager viewPager) {
            super(context, viewPager);
        }

        @Override
        protected int getCount() {
            return mImageBannerAdapter != null ? mImageBannerAdapter.getDataCount() : 0;
        }
    }
}

