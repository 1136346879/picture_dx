/**
 * @file XListViewHeader.java
 * @create Apr 18, 2012 5:22:27 PM
 * @author Maxwin
 * @description XListView's header
 */
package com.hexun.training.widget;



import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;


public class XListViewHeader extends LinearLayout {
	private LinearLayout mContainer;
	private ImageView mArrowImageView;
	private CustomProgressView mProgressBar;
	private TextView mHintTextView;
	private int mState = STATE_NORMAL;

	private Animation mRotateUpAnim;
	private Animation mRotateDownAnim;

	private final int ROTATE_ANIM_DURATION = 180;

	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_REFRESHING = 2;

	private String[] textInfo;
	private String[] defaultTextInfo = new String[3];

	public XListViewHeader(Context context) {
		super(context);
		initView(context);
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public XListViewHeader(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		// 初始情况，设置下拉刷新view高度
		LayoutParams lp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT, 0);
		mContainer = (LinearLayout) LayoutInflater.from(context).inflate(
				R.layout.xlistview_header, null);
		addView(mContainer, lp);
		setGravity(Gravity.BOTTOM);

		mArrowImageView = (ImageView) findViewById(R.id.xlistview_header_arrow);
		mHintTextView = (TextView) findViewById(R.id.xlistview_header_hint_textview);
		mProgressBar = (CustomProgressView)findViewById(R.id.xlistview_header_progressbar);

		mRotateUpAnim = new RotateAnimation(0.0f, -180.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateUpAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateUpAnim.setFillAfter(true);
		mRotateDownAnim = new RotateAnimation(-180.0f, 0.0f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		mRotateDownAnim.setDuration(ROTATE_ANIM_DURATION);
		mRotateDownAnim.setFillAfter(true);

		defaultTextInfo[0] = "下拉刷新";
		defaultTextInfo[1] = "松开刷新";
		defaultTextInfo[2] = "加载中...";
	}

	public void updateProgress(float rate) {
		mProgressBar.showProgress(rate);
	}

	public void setState(int state) {
		if (state == mState) {
            return;
        }

//		// 显示进度
//		if (state == STATE_REFRESHING) {
//			mArrowImageView.clearAnimation();
//			mArrowImageView.setVisibility(View.INVISIBLE);
//			mProgressBar.setVisibility(View.VISIBLE);
//		} else { // 显示箭头图片
//			mArrowImageView.setVisibility(View.VISIBLE);
//			mProgressBar.setVisibility(View.INVISIBLE);
//		}

		String[] text = defaultTextInfo;
		if (textInfo != null) {
			text = textInfo;
		}

		switch (state) {
			case STATE_NORMAL:
//				if (mState == STATE_READY) {
//					mArrowImageView.startAnimation(mRotateDownAnim);
//				}
//				if (mState == STATE_REFRESHING) {
//					mArrowImageView.clearAnimation();
//				}
				mHintTextView.setText(text[0]);
				break;
			case STATE_READY:
				if (mState != STATE_READY) {
//					mArrowImageView.clearAnimation();
//					mArrowImageView.startAnimation(mRotateUpAnim);
					mHintTextView.setText(text[1]);
				}
				break;
			case STATE_REFRESHING:
				mProgressBar.showProgress();
				mHintTextView.setText(text[2]);
				break;
			default:
		}

		mState = state;
	}

	public void setTextInfo(String[] textInfo) {
		if (textInfo != null && textInfo.length != 3) {
			return;
		}
		this.textInfo = textInfo;
	}


	public void setVisiableHeight(int height) {
		if (height < 0) {
            height = 0;
        }
		LayoutParams lp = (LayoutParams) mContainer
				.getLayoutParams();
		lp.height = height;
		mContainer.setLayoutParams(lp);
	}

	public int getVisiableHeight() {
		return mContainer.getLayoutParams().height;
	}

}
