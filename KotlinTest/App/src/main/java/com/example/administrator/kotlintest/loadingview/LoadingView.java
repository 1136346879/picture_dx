package com.example.administrator.kotlintest.loadingview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;


/**
 * 空页面 Loading 时页面展示
 */
public class LoadingView extends RelativeLayout implements View.OnClickListener {

    /**
     * 空页面
     */
    public static final int VIEW_TYPE_EMPTY = 0;

    /**
     * 错误页面
     */
    public static final int VIEW_TYPE_ERROR = 1;

    private View mProgressLayout;
    private View progressView;
    private TextView mProgressText;

    private View mEmptyLayout;
    private View mErrorLayout;

    private TextView mEmptyText;
    private TextView mErrorText;

    private OnLoadingViewClickListener mListener;
    private OnClickListener onClickListener;
    private ImageView mEmptyImage;
    private ImageView mErrorImage;

    private ViewStub emptyStub;
    private ViewStub errorStub;

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {

        mProgressLayout = findViewById(R.id.progress_layout);
        progressView = findViewById(R.id.progress_bar);
        mProgressText = (TextView) findViewById(R.id.progress_text);

        emptyStub = (ViewStub) findViewById(R.id.empty_stub);
        errorStub = (ViewStub) findViewById(R.id.error_stub);

        super.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
    }

    public boolean isShowing() {
        return getVisibility() == VISIBLE;
    }

    public boolean isLoading() {
        return getVisibility() == VISIBLE && mProgressLayout.getVisibility() == VISIBLE;
    }

    public void dismiss() {
        setVisibility(View.GONE);
    }

    /**
     * 显示加载进度
     */
    public void showProgress() {
        mProgressLayout.setVisibility(View.VISIBLE);
        if (mEmptyLayout != null) {
            mEmptyLayout.setVisibility(GONE);
        }
        if (mErrorLayout != null) {
            mErrorLayout.setVisibility(GONE);
        }
        setVisibility(View.VISIBLE);
    }

    /**
     * 显示错误的view
     */
    public void showErrorView() {
        mProgressLayout.setVisibility(GONE);
        if (mEmptyLayout != null){
            mEmptyLayout.setVisibility(GONE);
        }
        if (mErrorLayout == null){
            initErrorLayout();
        }
        mErrorLayout.setVisibility(VISIBLE);
        setVisibility(VISIBLE);
        mErrorText.setText("点击屏幕，重新加载");
    }

    public void setEmptyImage(int resId) {
        if (mEmptyLayout == null) {
            initEmptyLayout();
        }
        mEmptyImage.setImageResource(resId);
    }

    /**
     * 显示错误的view
     *
     * @param text 显示错误提示信息
     */
    public void showErrorView(CharSequence text) {
        mProgressLayout.setVisibility(GONE);
        if (mEmptyLayout != null){
            mEmptyLayout.setVisibility(GONE);
        }
        if (mErrorLayout == null){
            initErrorLayout();
        }
        mErrorLayout.setVisibility(VISIBLE);
        setVisibility(VISIBLE);
        mErrorText.setText(text);
    }

    /**
     * 显示错误的view，可替换错误提示图片
     *
     * @param text    显示错误提示信息
     * @param imageId 图片ID
     */
    public void showErrorView(CharSequence text, int imageId) {
        mProgressLayout.setVisibility(GONE);
        if (mEmptyLayout != null){
            mEmptyLayout.setVisibility(GONE);
        }
        if (mErrorLayout == null){
            initErrorLayout();
        }
        mErrorLayout.setVisibility(VISIBLE);
        mErrorImage.setImageResource(imageId);
        setVisibility(VISIBLE);
        mErrorText.setText(text);
    }

    /**
     * 显示空view
     */
    public void showEmptyView() {
        mProgressLayout.setVisibility(GONE);
        if (mEmptyLayout == null) {
            initEmptyLayout();
        }
        if (mErrorLayout != null) {
            mErrorLayout.setVisibility(GONE);
        }
        mEmptyLayout.setVisibility(VISIBLE);
        setVisibility(VISIBLE);
        mEmptyText.setText("很抱歉，暂时没有数据");
    }

    /**
     * 显示空view
     *
     * @param text 显示空数据信息
     */
    public void showEmptyView(String text) {
        mProgressLayout.setVisibility(GONE);
        if (mEmptyLayout == null) {
            initEmptyLayout();
        }
        mEmptyLayout.setVisibility(VISIBLE);
        if (mErrorLayout != null) {
            mErrorLayout.setVisibility(GONE);
        }

        setVisibility(VISIBLE);
        mEmptyText.setText(text);
    }

    /**
     * 显示空view ，可替换空数据信息图片
     *
     * @param text    显示空数据信息
     * @param imageId 图片ID
     */
    public void showEmptyView(String text, int imageId) {
        mProgressLayout.setVisibility(GONE);
        if (mEmptyLayout == null) {
            initEmptyLayout();
        }
        mEmptyLayout.setVisibility(VISIBLE);
        mEmptyImage.setImageResource(imageId);
        if (mErrorLayout != null) {
            mErrorLayout.setVisibility(GONE);
        }
        setVisibility(VISIBLE);
        mEmptyText.setText(text);
    }

    @Override
    public void onClick(View v) {
        if (v == mEmptyLayout) {
            if (mListener != null) {
                mListener.onLoadingViewClick(VIEW_TYPE_EMPTY);
            }
        } else if (v == mErrorLayout) {
            if (mListener != null) {
                mListener.onLoadingViewClick(VIEW_TYPE_ERROR);
            }
        }
    }

    public void setOnLoadingViewClickListener(OnLoadingViewClickListener listener) {
        this.mListener = listener;
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        onClickListener = l;
    }

    public interface OnLoadingViewClickListener {
        void onLoadingViewClick(int type);
    }

    public void setTopMargin(int pix) {
        LayoutParams params = (LayoutParams) mEmptyLayout.getLayoutParams();
        params.topMargin = pix;
        mEmptyLayout.setLayoutParams(params);
        LayoutParams rParams = (LayoutParams) mErrorLayout.getLayoutParams();
        rParams.topMargin = pix;
        mErrorLayout.setLayoutParams(rParams);
    }

    /**
     * 初始化空布局
     */
    private void initEmptyLayout() {
        mEmptyLayout = emptyStub.inflate();
        mEmptyText = (TextView) findViewById(R.id.empty_text);
        mEmptyImage = (ImageView) findViewById(R.id.empty_image);
        mEmptyLayout.setOnClickListener(this);
    }

    /**
     * 初始化异常布局
     */
    private void initErrorLayout() {
        mErrorLayout = errorStub.inflate();
        mErrorText = (TextView) findViewById(R.id.error_text);
        mErrorImage = (ImageView) findViewById(R.id.error_image);
        mErrorLayout.setOnClickListener(this);
    }

}
