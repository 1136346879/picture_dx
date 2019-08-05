/**
 * @file XFooterView.java
 * @create Mar 31, 2012 9:33:43 PM
 * @author Maxwin
 * @description XListView's footer
 */
package com.hexun.training.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;


public class XListViewFooter extends LinearLayout {
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_LOADING = 2;

	private Context mContext;

	private View mContentView;
	private View mProgressBar;
	private TextView mHintView;
	private LinearLayout mLayout;
	private CharSequence mHoverText;

	public XListViewFooter(Context context) {
		super(context);
		initView(context);
	}

	public XListViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	public void setState(int state) {
		mHintView.setVisibility(View.INVISIBLE);
		mProgressBar.setVisibility(View.INVISIBLE);
		mLayout.setVisibility(View.INVISIBLE);
		if (state == STATE_READY) {
			mLayout.setVisibility(View.VISIBLE);
			mHintView.setVisibility(View.VISIBLE);
			if (TextUtils.isEmpty(mHoverText)) {
				mHintView.setText(R.string.cd_base_list_view_footer_hint_ready);
			} else {
				mHintView.setText(mHoverText);
			}
		} else if (state == STATE_LOADING) {
			mProgressBar.setVisibility(View.VISIBLE);
			mLayout.setVisibility(View.GONE);
		} else {
			mLayout.setVisibility(View.VISIBLE);
			mHintView.setVisibility(View.VISIBLE);
			if (TextUtils.isEmpty(mHoverText)) {
				mHintView.setText(R.string.cd_base_list_view_footer_hint_normal);
			} else {
				mHintView.setText(mHoverText);
			}
		}
	}

	/**
	 * 设置停留时的文字
	 * @param text
	 */
	public void setHoverText(CharSequence text) {
		mHoverText = text;
		if (text != null) {
			mHintView.setText(text);
		} else {
			mHintView.setText(R.string.cd_base_list_view_footer_hint_normal);
		}
	}

	public void setBottomMargin(int height) {
		if (height < 0) {
            return;
        }
		int half = height/2;
		LayoutParams lp = (LayoutParams) mContentView
				.getLayoutParams();
		lp.bottomMargin = half;
		lp.topMargin = half;
		mContentView.setLayoutParams(lp);
	}

	public int getBottomMargin() {
		LayoutParams lp = (LayoutParams) mContentView
				.getLayoutParams();
		return lp.bottomMargin + lp.topMargin;
	}

	/**
	 * normal status
	 */
	public void normal() {
		mHintView.setVisibility(View.VISIBLE);
		mProgressBar.setVisibility(View.GONE);
	}

	/**
	 * loading status
	 */
	public void loading() {
		mHintView.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.VISIBLE);
	}

	/**
	 * hide footer when disable pull load more
	 */
	public void hide() {
		LayoutParams lp = (LayoutParams) mContentView
				.getLayoutParams();
		lp.height = 0;
		mContentView.setLayoutParams(lp);
		setVisibility(GONE);
	}

	/**
	 * show footer
	 */
	public void show() {
		setVisibility(VISIBLE);
		LayoutParams lp = (LayoutParams) mContentView
				.getLayoutParams();
		lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
		mContentView.setLayoutParams(lp);
	}

	private void initView(Context context) {
		mContext = context;
		moreView = LayoutInflater.from(mContext)
				.inflate(R.layout.xlistview_footer, null);
		LayoutParams params = new LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		params.gravity = Gravity.CENTER_VERTICAL;
		addView(moreView, params);

		mContentView = moreView.findViewById(R.id.xlistview_footer_content);
		mProgressBar = moreView.findViewById(R.id.xlistview_footer_progressbar);
		mHintView = (TextView) moreView.findViewById(R.id.xlistview_footer_hint_textview);
		mLayout = (LinearLayout)moreView.findViewById(R.id.xlistview_footer_loadmore_layout);
	}

	public View getMoreView() {
		return moreView;
	}

	public void setMoreView(View moreView) {
		this.moreView = moreView;
	}

	private View moreView;
	private int mExactlyHeight;
	public void setExactlyHeight(int height) {
		this.mExactlyHeight = height;
		if (moreView != null) {
			LayoutParams params = (LayoutParams)moreView.getLayoutParams();
			if (height > 0) {
				params.height = height;
				moreView.setLayoutParams(params);
			} else if (params.height != LayoutParams.WRAP_CONTENT) {
				params.height = LayoutParams.WRAP_CONTENT;
				moreView.setLayoutParams(params);
			}
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (mExactlyHeight > 0) {
			super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(mExactlyHeight, MeasureSpec.EXACTLY));
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

}
