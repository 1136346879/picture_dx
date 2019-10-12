package com.example.administrator.kotlintest.banner.mybanner;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import androidx.annotation.CallSuper;
import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hexun on 2017/10/31.
 * 指示器适配器
 */

public abstract class IndicatorAdapter implements ViewPager.OnPageChangeListener {
    private int indicatorGravity = Gravity.RIGHT | Gravity.BOTTOM;
    /**
     * @deprecated {暂时不用}
     */
    @Deprecated
    private int titleBarGravity = Gravity.BOTTOM;
    private int gravity = Gravity.BOTTOM;
    protected SparseArray<View> indicators = new SparseArray<>();
    protected SparseArray<Drawable> drawables = new SparseArray<>();
    protected SparseArray<View> titles = new SparseArray<>();
    private LinearLayout linearLayout;
    private FrameLayout content;
    private boolean clear = false;
    protected int currentPosition;
    protected List<String> titleList = new ArrayList<>();
    private int titleMaxHeight;
    private ViewPager viewPager;
    private Context context;
    private int viewPagePosition;

    public IndicatorAdapter(ViewPager viewPager) {
        this.viewPager = viewPager;
        if (null != viewPager) {
            context = viewPager.getContext();
            titleMaxHeight = DensityUtil.dip2px(viewPager.getContext(), 45);
        }
    }

    /**
     * 开始适配布局，子类复写时需要调super
     */
    @CallSuper
    void startAdapter() {
        if (getCount() == 0) {
            return;
        }
        if (0 == titles.size()) {
            initTitle();
        }
        if (viewPagePosition == 0) {
            //修改为初始状态添加title
            //其余状态不主动更新title
            addTitleBar(0);
        }
        if (0 == indicators.size()) {
            initItem(0);
        }
        if (content.getParent() == null && null != viewPager) {
            FrameLayout parent = (FrameLayout) viewPager.getParent();
            if (parent.indexOfChild(content) == -1) {
                parent.addView(content);
                initContentParams();
                onPageChange(titles.get(0), indicators.get(0), 0);
            }
        }
    }


    /**
     * 初始化title
     */
    private void initTitle() {
        for (int i = 0; i < getCount(); i++) {
            titles.put(i, getTitleBar(i));
        }
    }

    /**
     * 初始化item
     */
    private void initItem(int selectPosition) {
        drawables.clear();
        indicators.clear();
        if (null != linearLayout && linearLayout.getChildCount() != 0) {
            linearLayout.removeAllViews();
        }
        if (getCount() <= 1) {
            return;
        }
        for (int i = 0; i < getCount(); i++) {
            View item = getIndicatorView(i);
            if (null != item) {
                initView(item.getContext());
                drawables.put(i, item.getBackground());
                linearLayout.addView(item);
                if (i == selectPosition) {
                    item.setBackground(selectedIndicator());
                }
                initItem(item, (LinearLayout.LayoutParams) item.getLayoutParams());
                indicators.put(i, item);
            }
        }
    }

    /**
     * 设置主布局的params
     */
    private void initContentParams() {
        if (null == content) {
            return;
        }
        FrameLayout.LayoutParams frameParams = (FrameLayout.LayoutParams) content.getLayoutParams();
        if (null == frameParams) {
            return;
        }
        frameParams.gravity = gravity;
        frameParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
        frameParams.height = titleMaxHeight;
        content.setLayoutParams(frameParams);
    }

    /**
     * 添加titleBar
     *
     * @param position
     */
    protected void addTitleBar(int position) {
        if (null == content) {
            content = new FrameLayout(context);
            int[] colors = new int[2];
            colors[0] = Color.argb(0xFF, 0x00, 0x00, 0x00);
            colors[1] = Color.argb(0x00, 0x00, 0x00, 0x00);
            GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
            drawable.setAlpha(0xB0);
            content.setBackground(drawable);
        }
        int count = content.getChildCount();
        if (indicators.size() != 0) {
            if (count >= 2) {
                //移除所有除指示器和标题的view
                for (int i = 0; i < count - 1; i++) {
                    content.removeViewAt(i);
                }
            } else {
                //此处限制指示器必须linearLayout包裹
                if (!(content.getChildAt(0) instanceof LinearLayout)) {
                    content.removeViewAt(0);
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                if (null != content.getChildAt(i) && !(content.getChildAt(i) instanceof LinearLayout)) {
                    content.removeViewAt(i);
                }
            }
        }
        View titleView = titles.get(position);
        if (null == titleView) {
            return;
        }
        if (titleView.getParent() != null) {
            ((ViewGroup) titleView.getParent()).removeView(titleView);
        }
        content.addView(titleView, 0);
        initTitleParams(position);
    }


    /**
     * 设置title的参数
     *
     * @param position
     */

    private void initTitleParams(int position) {
        View titleView = titles.get(position);
        if (null == titleView) {
            return;
        }
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) titleView.getLayoutParams();
        if (params == null) {
            return;
        }
        params.width = FrameLayout.LayoutParams.MATCH_PARENT;
        params.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        params.rightMargin = null != linearLayout ? linearLayout.getWidth() : 0;
        params.gravity = titleBarGravity;
        titleView.setLayoutParams(params);
        initTitleParams(titleView, params);
    }

    /**
     * 子类设置title参数
     *
     * @param view
     * @param params
     */
    protected void initTitleParams(View view, FrameLayout.LayoutParams params) {

    }

    /**
     * 重设指示器背景
     */
    protected void resetIndicatorBackGround() {
        View item;
        for (int i = 0; i < indicators.size(); i++) {
            item = indicators.get(i);
            item.setBackground(defaultIndicator(i));
            initItem(item, (LinearLayout.LayoutParams) item.getLayoutParams());
        }
    }

    /**
     * 初始化指示器所在布局LinearLayout
     *
     * @param context
     */
    private void initView(Context context) {
        if (null == content) {
            content = new FrameLayout(context);
            int[] colors = new int[2];
            colors[0] = Color.argb(0xFF, 0x00, 0x00, 0x00);
            colors[1] = Color.argb(0x00, 0x00, 0x00, 0x00);
            GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, colors);
            drawable.setAlpha(0xB0);
            content.setBackground(drawable);
        }
        if (null == linearLayout) {
            linearLayout = new LinearLayout(getIndicatorView(0).getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setPadding(DensityUtil.dip2px(context, 0), DensityUtil.dip2px(context, 0), DensityUtil.dip2px(context, 0), DensityUtil.dip2px(context, 7));
            if (content.indexOfChild(linearLayout) != -1) {
                return;
            }
            content.addView(linearLayout);
            initIndicatorParams();
        }
    }

    /**
     * 设置指示器参数
     */
    private void initIndicatorParams() {
        if (null == linearLayout) {
            return;
        }
        FrameLayout.LayoutParams linearParams = (FrameLayout.LayoutParams) linearLayout.getLayoutParams();
        if (null == linearParams) {
            return;
        }
        linearParams.width = FrameLayout.LayoutParams.WRAP_CONTENT;
        linearParams.height = FrameLayout.LayoutParams.WRAP_CONTENT;
        linearParams.rightMargin = DensityUtil.dip2px(context, 5);
        linearParams.bottomMargin = DensityUtil.dip2px(context, 8);
        linearParams.gravity = indicatorGravity;
        linearLayout.setLayoutParams(linearParams);
    }

    /**
     * @param gravity 设置位置
     */
    public void setIndicatorGravity(int gravity) {
        this.indicatorGravity = gravity;
        initIndicatorParams();
    }

    /**
     * 暂时无用  不能影响title的布局
     *
     * @param gravity
     * @deprecated {暂时不用}
     */
    @Deprecated
    public void setTitleBarGravity(int gravity) {
        this.titleBarGravity = gravity;
        initTitleParams(currentPosition);
    }

    /**
     * 设置指示器和title位置（上，下）
     *
     * @param gravity
     */
    public void setGravity(int gravity) {
        this.gravity = gravity;
        initContentParams();
    }

    /**
     * 获取titleView
     *
     * @param position
     * @return
     */
    protected abstract View getTitleBar(int position);

    /**
     * 获取单个指示器view
     *
     * @param position
     * @return
     */
    protected abstract View getIndicatorView(int position);

    /***
     * 设置标题，默认标题数与指示器数即为实际的image数量
     * @param list
     * @return
     */
    public IndicatorAdapter setTitleList(List<String> list) {
        this.titleList.clear();
        this.titleList.addAll(list);
        initTitle();
        initItem(currentPosition);
        return this;
    }

    /**
     * 初始化指示器view参数
     *
     * @param item
     * @param params
     */
    protected void initItem(View item, LinearLayout.LayoutParams params) {
        params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.weight = 1;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        params.leftMargin = DensityUtil.dip2px(context, 3);
        params.rightMargin = DensityUtil.dip2px(context, 3);
        params.gravity = Gravity.CENTER_VERTICAL;
        item.setLayoutParams(params);
    }

    /**
     * 返回指示器个数
     *
     * @return
     */
    protected int getCount() {
        return titleList.size();
    }

    /**
     * 页面改变时调用
     *
     * @param title
     * @param item
     * @param position
     */
    public abstract void onPageChange(@Nullable View title, View item, int position);

    /**
     * 获取默认的指示器背景
     *
     * @param position
     * @return
     */
    protected Drawable defaultIndicator(int position) {
        return drawables.get(position);
    }

    /**
     * 获取指示器选中状态背景
     *
     * @return
     */
    protected abstract Drawable selectedIndicator();

    /**
     * PageListener 方法
     *
     * @param position
     * @param positionOffset
     * @param positionOffsetPixels
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 页面被选中
     *
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        viewPagePosition = position;
        if (getCount() == 0) {
            return;
        }
        currentPosition = position % getCount();
        if (clear) {
            return;
        }
        resetIndicatorBackGround();
        addTitleBar(currentPosition);
        if (null != selectedIndicator()) {
            View view = indicators.get(currentPosition);
            if (view != null) {
                view.setBackground(selectedIndicator());
            }
        }
        onPageChange(titles.get(currentPosition), indicators.get(currentPosition), currentPosition);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 更新设置项（设置之后notify通知更新）
     */
    protected void notifyChange() {
        onPageSelected(currentPosition);
    }


    /**
     * 清理adapter中指示器布局
     */
    public void clearAdapter() {
        if (null == content) {
            return;
        }
        clear = true;
        content.removeAllViews();
        indicators.clear();
        drawables.clear();
        titles.clear();
        currentPosition = 0;
        clear = false;
    }


    /**
     * 默认模板
     */
    public abstract static class DefaultIndicatorAdapter extends IndicatorAdapter {
        /**
         * 圆点  . . .
         */
        public static final int TYPE_INDICATOR_CIRCLE = 0x00000000;
        /**
         * 数字  1 2 3
         */
        public static final int TYPE_INDICATOR_FIGURE = 0x00000001;
        /**
         * 百分比 1/3
         * 尚未完成待补充
         *
         * @deprecated {百分比样式待补充}
         */
        @Deprecated
        public static final int TYPE_INDICATOR_PERCENT = 0x00000002;
        /**
         * 矩形 - - -
         */
        public static final int TYPE_INDICATOR_RECT = 0x00000003;

        /**
         * 指示器类型，目前支持三种
         */
        @Retention(RetentionPolicy.SOURCE)
        @IntDef({TYPE_INDICATOR_CIRCLE, TYPE_INDICATOR_FIGURE, TYPE_INDICATOR_PERCENT, TYPE_INDICATOR_RECT})
        @interface TYPE_INDICATOR {

        }

        private Context context;
        /**
         * 指示器类型
         */
        private int indicatorType = TYPE_INDICATOR_CIRCLE;
        /**
         * 默认选中背景颜色红色
         */
        private @ColorInt
        int selectedColor = Color.WHITE;
        /**
         * 默认正常背景色白色
         */
        private @ColorInt
        int defaultColor = Color.argb(0xFF, 0xb1, 0xa8, 0x99);
        /**
         * 默认title位置居中
         */
        private int titleGravity = Gravity.LEFT | Gravity.BOTTOM;

        /**
         * 返回默认titleBar View
         *
         * @param position
         * @return
         */
        @Override
        protected View getTitleBar(int position) {
            if (null == titleList || titleList.isEmpty() || getCount() != titleList.size()) {
                return null;
            }
            if ("".equals(titleList.get(position)) || null == titleList.get(position)) {
                return null;
            }
            TextView view = new TextView(context);
            view.setPadding(DensityUtil.dip2px(context, 10), DensityUtil.dip2px(context, 0), DensityUtil.dip2px(context, 10), DensityUtil.dip2px(context, 7));
            view.setLines(1);
            view.setGravity(titleGravity);
            view.setText(titleList.get(position));
            view.setTextSize(TypedValue.COMPLEX_UNIT_DIP,18);
            view.setEllipsize(TextUtils.TruncateAt.END);
            view.setTextColor(Color.argb(0xFF, 0xFF, 0xFF, 0xFF));
            return view;
        }

        public DefaultIndicatorAdapter(Context context, ViewPager viewPager) {
            super(viewPager);
            this.context = context;
        }

        /**
         * 获取指示器view
         * 支持不同类型指示器view
         *
         * @param position
         * @return
         */
        @Override
        protected View getIndicatorView(int position) {
            switch (indicatorType) {
                case TYPE_INDICATOR_CIRCLE:
                    return getCircleIndicatorView(defaultColor);
                case TYPE_INDICATOR_FIGURE:
                    return getFigureIndicatorView(position);
                case TYPE_INDICATOR_PERCENT:
                    return getPercentIndicatorView(position);
                case TYPE_INDICATOR_RECT:
                    return getRectIndicatorView(defaultColor);
                default:
                    break;
            }
            return null;
        }

        /**
         * 返回圆点类型指示器
         *
         * @param defaultColor
         * @return
         */
        public View getCircleIndicatorView(@ColorInt int defaultColor) {
            View view = new View(context);
            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(DensityUtil.dip2px(context, 20));
            drawable.setColor(defaultColor);
            view.setBackground(drawable);
            return view;
        }

        /**
         * 返回数字指示器view
         *
         * @param position
         * @return
         */
        public View getFigureIndicatorView(int position) {
            TextView view = new TextView(context);
            view.setText(String.valueOf(position + 1));
            return view;
        }

        /**
         * 获取百分数指示器view
         *
         * @param position
         * @return
         */
        public View getPercentIndicatorView(int position) {
            TextView view = new TextView(context);
            view.setText((position + 1) + "/" + getCount());
            return view;
        }

        /**
         * 获取矩形指示器view
         *
         * @param defaultColor
         * @return
         */
        public View getRectIndicatorView(@ColorInt int defaultColor) {
            View view = new View(context);
            ColorDrawable drawable = new ColorDrawable(defaultColor);
            view.setBackground(drawable);
            return view;
        }

        /**
         * 重写父类初始化指示器方法，矩形和圆形指示器重写
         *
         * @param item
         * @param params
         */
        @Override
        protected void initItem(View item, LinearLayout.LayoutParams params) {
            switch (indicatorType) {
                case TYPE_INDICATOR_CIRCLE:
                    params.width = DensityUtil.dip2px(context, 5);
                    params.height = DensityUtil.dip2px(context, 5);
                    params.weight = 1;
                    params.leftMargin = DensityUtil.dip2px(context, 2);
                    params.rightMargin = DensityUtil.dip2px(context, 2);
                    params.gravity = Gravity.CENTER_VERTICAL;
                    item.setLayoutParams(params);
                    break;
                case TYPE_INDICATOR_RECT:
                    params.width = DensityUtil.dip2px(context, 9);
                    params.height = DensityUtil.dip2px(context, 2);
                    params.weight = 1;
                    params.leftMargin = DensityUtil.dip2px(context, 2);
                    params.rightMargin = DensityUtil.dip2px(context, 2);
                    item.setLayoutParams(params);
                    break;
                default:
                    super.initItem(item, params);
                    break;
            }
        }

        /**
         * 选中指示器的drawable
         *
         * @return
         */
        @Override
        protected Drawable selectedIndicator() {
            if (indicatorType == TYPE_INDICATOR_CIRCLE) {
                GradientDrawable drawable = new GradientDrawable();
                drawable.setCornerRadius(DensityUtil.dip2px(context, 20));
                drawable.setColor(selectedColor);
                return drawable;
            }
            if (indicatorType == TYPE_INDICATOR_RECT) {
                return new ColorDrawable(selectedColor);
            }
            return new ColorDrawable(Color.WHITE);
        }

        /**
         * 设置默认背景色
         *
         * @param defualtColor
         * @return
         */
        public DefaultIndicatorAdapter setDefaultColor(@ColorInt int defualtColor) {
            this.defaultColor = defualtColor;
            return this;
        }

        /**
         * 设置默认的选中背景颜色
         *
         * @param selectedColor
         * @return
         */
        public DefaultIndicatorAdapter setSelectedColor(@ColorInt int selectedColor) {
            this.selectedColor = selectedColor;
            return this;
        }

        /**
         * 页面改变时调用，对圆形指示器做放大处理
         *
         * @param title
         * @param item
         * @param position
         */
        @Override
        public void onPageChange(@Nullable View title, View item, int position) {
            if (null == item) {
                return;
            }
            if (indicatorType == TYPE_INDICATOR_CIRCLE) {
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) item.getLayoutParams();
                params.width = params.width + DensityUtil.dip2px(context, 2);
                params.height = params.height + DensityUtil.dip2px(context, 2);
                item.setLayoutParams(params);
            }
        }

        /**
         * 设置title的内容Gravity
         * 默认居中
         *
         * @param gravity
         * @return
         */
        public DefaultIndicatorAdapter setTitleGravity(int gravity) {
            this.titleGravity = gravity;
            return this;
        }

        /**
         * 设置当前指示器样式
         *
         * @param indicatorType
         */
        public DefaultIndicatorAdapter setIndicatorType(@TYPE_INDICATOR int indicatorType) {
            this.indicatorType = indicatorType;
            super.initItem(0);
            return this;
        }
    }
}
