/**
 * @file XListView.java
 * @package me.maxwin.view
 * @create Mar 18, 2012 6:28:41 PM
 * @author Maxwin
 * @description An ListView support (a) Pull down to hp_refresh, (b) Pull up to load more.
 * 		Implement IXListViewListener, and see stopRefresh() / stopLoadMore().
 */
package com.hexun.training.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Scroller;
import android.widget.TextView;


import com.example.administrator.kotlintest.R;

import java.util.ArrayList;
import java.util.List;

public class XListView extends ListView implements OnScrollListener {
	private float mLastX = -1; // save event x
	private float mLastY = -1; // save event y
	private Scroller mScroller; // used for scroll back
	private OnScrollListener mScrollListener; // user's scroll listener

	// the interface to trigger hp_refresh and load more.
	private IXListViewListener mListViewListener;

	// -- header view
	private XListViewHeader mHeaderView;
	// header view content, use it to calculate the Header's height. And hide it
	// when disable pull hp_refresh.
	private RelativeLayout mHeaderViewContent;
	private TextView mHeaderTimeView;
	private int mHeaderViewHeight; // header view's height
	private boolean mEnablePullRefresh = true;
	private boolean mPullRefreshing = false; // is refreashing.

	// -- footer view
	private XListViewFooter mFooterView;
	private boolean mEnablePullLoad;
	private boolean mPullLoading;
	private boolean mIsFooterReady = false;
	private boolean mContinuePullLoad = true;//是否可以继续下拉更多操作

	// total list items, used to detect is at the bottom of listview.
	private int mTotalItemCount;

	// for mScroller, scroll back from header or footer.
	private int mScrollBack;
	private final static int SCROLLBACK_HEADER = 0;
	private final static int SCROLLBACK_FOOTER = 1;

	private final static int SCROLL_DURATION = 400; // scroll back duration
	private final static int PULL_LOAD_MORE_DELTA = 50; // when pull up >= 50px
	// at bottom, trigger
	// load more.
	private final static float OFFSET_RADIO = 1.8f; // support iOS like pull
	// feature.
	private List<View> mHeaderViewList;
	/**
	 * HeaderView高度
	 */
	private int mFullFooterHeight;
	boolean hasFooterChanged = false;
	/**
	 * @param context
	 */
	public XListView(Context context) {
		super(context);
		initWithContext(context);
	}

	public XListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initWithContext(context);
	}

	public XListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initWithContext(context);
	}

	private void initWithContext(Context context) {
		setOverScrollMode(ListView.OVER_SCROLL_NEVER);
		mHeaderViewList = new ArrayList<>();
		mScroller = new Scroller(context, new DecelerateInterpolator());
		// XListView need the scroll event, and it will dispatch the event to
		// user's listener (as a proxy).
		super.setOnScrollListener(this);

		// init header view
		mHeaderView = new XListViewHeader(context);
		mHeaderViewContent = (RelativeLayout) mHeaderView
				.findViewById(R.id.xlistview_header_content);
		mHeaderTimeView = (TextView) mHeaderView
				.findViewById(R.id.xlistview_header_time);
		super.addHeaderView(mHeaderView);

		// init footer view
		mFooterView = new XListViewFooter(context);

		// init header height
		mHeaderView.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@Override
					public void onGlobalLayout() {
						mHeaderViewHeight = mHeaderViewContent.getHeight();
						getViewTreeObserver()
								.removeOnGlobalLayoutListener(this);
					}
				});
	}

	@Override
	public void setAdapter(ListAdapter adapter) {
		// make sure XListViewFooter is the last footer view, and only add once.
		if (mIsFooterReady == false) {
			mIsFooterReady = true;
			addFooterView(mFooterView);
		}
		super.setAdapter(adapter);
	}

	/**
	 * enable or disable pull down hp_refresh feature.
	 *
	 * @param enable
	 */
	public void setPullRefreshEnable(boolean enable) {
		mEnablePullRefresh = enable;
		if (!mEnablePullRefresh) { // disable, hide the content
			mHeaderViewContent.setVisibility(View.INVISIBLE);
		} else {
			mHeaderViewContent.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * enable or disable pull up load more feature.
	 *
	 * @param enable
	 */
	public void setPullLoadEnable(boolean enable) {
		mEnablePullLoad = enable;
		if (!mEnablePullLoad) {
			mFooterView.hide();
			mFooterView.setOnClickListener(null);
		} else {
			mPullLoading = false;
			mFooterView.show();
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
			// both "pull up" and "click" will invoke load more.
			mFooterView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					startLoadMore();
				}
			});
		}
	}

	/**
	 * 仅显示下拉刷新状态，不会回调{@link IXListViewListener#onRefresh()}.
	 * 需要调用{@link XListView#stopRefresh()}取消状态.
	 */
	public void showRefreshProgress() {
		if (mPullRefreshing || !mEnablePullRefresh) {
			return;
		}
		mPullRefreshing = true;
		mHeaderView.setVisiableHeight(mHeaderViewHeight);
		mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
		resetHeaderHeight();
	}

	public boolean isRefreshing() {
		return mPullRefreshing;
	}

	/**
	 * stop hp_refresh, reset header view.
	 */
	public void stopRefresh() {
		if (mPullRefreshing == true) {
			mPullRefreshing = false;
			resetHeaderHeight();
		}
	}

	/**
	 * stop load more, reset footer view.
	 */
	public void stopLoadMore() {
		if (mPullLoading == true) {
			mPullLoading = false;
			resetFooterHeight();
			mFooterView.setState(XListViewFooter.STATE_NORMAL);
			changeFooterSize(false);
		}
	}

	/**
	 * 隐藏加载更多
	 * 当传入的值为true时，代表当前列表已经到最后，隐藏加载更多
	 * @param flag
	 */
	public void resultnull(Boolean flag) {
		RelativeLayout r = (RelativeLayout)mFooterView.findViewById(R.id.xlistview_footer_content);

		if(flag){
			r.setVisibility(View.GONE);
		}else{
			r.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 隐藏加载更多
	 * 当传入的值为true时，代表当前列表已经到最后，隐藏加载更多
	 */
	@SuppressLint("ResourceAsColor")
	public void changebackground(Boolean b) {
		if(b){
//			mFooterView.setBackgroundColor(R.color.gray2);
		}
	}
	/**
	 * set last hp_refresh time
	 *
	 * @param time
	 */
	public void setRefreshTime(String time) {
		mHeaderTimeView.setText(time);
	}

	private void invokeOnScrolling() {
		if (mScrollListener instanceof OnXScrollListener) {
			OnXScrollListener l = (OnXScrollListener) mScrollListener;
			l.onXScrolling(this);
		}
	}

	private void updateHeaderHeight(float delta) {
		float newHeight = delta + mHeaderView.getVisiableHeight();
		mHeaderView.setVisiableHeight((int) newHeight);
		if (mEnablePullRefresh && !mPullRefreshing) { // 未处于刷新状态，更新箭头
			mHeaderView.updateProgress(newHeight/mHeaderViewHeight);
			if (mHeaderView.getVisiableHeight() > mHeaderViewHeight) {
				mHeaderView.setState(XListViewHeader.STATE_READY);
			} else {
				mHeaderView.setState(XListViewHeader.STATE_NORMAL);
			}
		}
		setSelection(0); // scroll to top each time
	}

	/**
	 * reset header view's height.
	 */
	private void resetHeaderHeight() {
		int height = mHeaderView.getVisiableHeight();
		if (height == 0) // not visible.
        {
            return;
        }
		// refreshing and header isn't shown fully. do nothing.
		if (mPullRefreshing && height <= mHeaderViewHeight) {
			return;
		}
		int finalHeight = 0; // default: scroll back to dismiss header.
		// is refreshing, just scroll back to show all the header.
		if (mPullRefreshing && height > mHeaderViewHeight) {
			finalHeight = mHeaderViewHeight;
		}
		mScrollBack = SCROLLBACK_HEADER;
		mScroller.startScroll(0, height, 0, finalHeight - height,
				SCROLL_DURATION);
		// trigger computeScroll
		invalidate();
	}

	/**
	 * 隐藏头部布局
	 */
	public void hideHeaderView(){
		resetHeaderHeight();
	}

	private void updateFooterHeight(float delta) {
		int height = mFooterView.getBottomMargin() + (int) delta;
		if (mEnablePullLoad && !mPullLoading) {
			if (height > PULL_LOAD_MORE_DELTA) { // height enough to invoke load
				// more.
				mFooterView.setState(XListViewFooter.STATE_READY);
			} else {
				mFooterView.setState(XListViewFooter.STATE_NORMAL);
			}
		}
		mFooterView.setBottomMargin(height);

		// setSelection(mTotalItemCount - 1); // scroll to bottom
	}

	private void resetFooterHeight() {
		int bottomMargin = mFooterView.getBottomMargin();
		if (bottomMargin > 0) {
			mScrollBack = SCROLLBACK_FOOTER;
			mScroller.startScroll(0, bottomMargin, 0, -bottomMargin,
					SCROLL_DURATION);
			invalidate();
		}
	}

	private void startLoadMore() {
		if (mContinuePullLoad) {
			mPullLoading = true;
			mFooterView.setState(XListViewFooter.STATE_LOADING);
			if (mListViewListener != null) {
				mListViewListener.onLoadMore();
			}
		}
	}

	/**
	 * 设置是否可以继续下拉更多，和{@link #setPullLoadEnable(boolean)}不同之处在于这个方法会显示footer。
	 * @param isContinue
	 */
	public void setContinuePullLoad(boolean isContinue) {
		mContinuePullLoad = isContinue;
		if (isContinue) {
			mFooterView.setHoverText(null);
		}
	}

	/**
	 * 设置FooterView的文字提示，这个提示会覆盖上拉状态提示文字，参数null时可以取消设置。
	 * @param text
	 */
	public void setFooterHoverText(CharSequence text) {
		mFooterView.setHoverText(text);
	}

	/**
	 * 仅显示底部加载状态，不会回调{@link IXListViewListener#onLoadMore()}.
	 * 需要调用{@link XListView#stopLoadMore()}取消状态.
	 */
	public void showLoadMoreProgress() {
		if (!mPullLoading) {
			mPullLoading = true;
			mFooterView.setState(XListViewFooter.STATE_LOADING);
			if (getAdapter() != null || getAdapter().getCount() == 0) {
				changeFooterSize(true);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mLastY == -1) {
			mLastY = ev.getRawY();
		}
		if (mLastX == -1) {
			mLastX = ev.getRawX();
		}
		switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				mLastY = ev.getRawY();
				mLastX=ev.getRawX();
				break;
			case MotionEvent.ACTION_MOVE:
				final float deltaY = ev.getRawY() - mLastY;
				final float deltaX = ev.getRawX() - mLastX;
//				if(deltaY==0){
//					break;
//				}
//				final float delta=deltaX/deltaY;
////			Log.e("onTouchEvent","onTouchEvent="+Math.abs(delta));
//				if(Math.abs(delta)>1){
//					break;
//				}
				mLastY = ev.getRawY();
				mLastX=ev.getRawX();
				if (getFirstVisiblePosition() == 0
						&& (mHeaderView.getVisiableHeight() > 0 || deltaY > 0)) {
					// the first item is showing, header has shown or pull down.
					updateHeaderHeight(deltaY / OFFSET_RADIO);
					invokeOnScrolling();
				} else if (getLastVisiblePosition() == mTotalItemCount - 1
						&& (mFooterView.getBottomMargin() > 0 || deltaY < 0)) {
					// last item, already pulled up or want to pull up.
					updateFooterHeight(-deltaY / OFFSET_RADIO);
				}
				break;
			default:
				mLastY = -1; // reset
				if (getFirstVisiblePosition() == 0) {
					// invoke hp_refresh
					if (mEnablePullRefresh
							&& mHeaderView.getVisiableHeight() > mHeaderViewHeight && !mPullRefreshing) {
						mPullRefreshing = true;
						mHeaderView.setState(XListViewHeader.STATE_REFRESHING);
						if (mListViewListener != null) {
							mListViewListener.onRefresh();
						}
					}
					resetHeaderHeight();
				}
				if (getLastVisiblePosition() == mTotalItemCount - 1) {
					// invoke load more.
					if (mEnablePullLoad
							&& mFooterView.getBottomMargin() > PULL_LOAD_MORE_DELTA && !mPullLoading) {
						startLoadMore();
					}
					resetFooterHeight();
				}
				break;
		}
		return super.onTouchEvent(ev);
	}

	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			if (mScrollBack == SCROLLBACK_HEADER) {
				mHeaderView.setVisiableHeight(mScroller.getCurrY());
			} else {
				mFooterView.setBottomMargin(mScroller.getCurrY());
			}
			postInvalidate();
			invokeOnScrolling();
		}
		super.computeScroll();
	}

	@Override
	public void setOnScrollListener(OnScrollListener l) {
		mScrollListener = l;
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		if (mScrollListener != null) {
			mScrollListener.onScrollStateChanged(view, scrollState);
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
						 int visibleItemCount, int totalItemCount) {
		// send to user's listener
		mTotalItemCount = totalItemCount;
		if (mScrollListener != null) {
			mScrollListener.onScroll(view, firstVisibleItem, visibleItemCount,
					totalItemCount);
		}
	}

	@Override
	public void addHeaderView(View v) {
		super.addHeaderView(v);
		mHeaderViewList.add(v);
	}

	@Override
	public boolean removeHeaderView(View v) {
		mHeaderViewList.remove(v);
		return super.removeHeaderView(v);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (mFooterView != null) {
			if (hasFooterChanged) {
				mFooterView.setExactlyHeight(mFullFooterHeight);
			} else {
				mFooterView.setExactlyHeight(0);
			}
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			int height = 0;
			for (View view:mHeaderViewList) {
				height += view.getMeasuredHeight();
			}
			if (mHeaderViewList.size() > 0 && height > 0) {
				mFullFooterHeight = getMeasuredHeight() - height;
			}
//			HLog.d("XListView", "footerHeight=" + mFullFooterHeight + " headerSize=" + mHeaderViewList.size() + " realSize=" + getHeaderViewsCount());
		}
	}

	private void changeFooterSize(boolean changed) {
		if (mIsFooterReady && (hasFooterChanged^changed)) {
			if (changed) {
				hasFooterChanged = true;
				postInvalidate();
			} else {
				hasFooterChanged = false;
				postInvalidate();
			}
		}
	}

	public void setXListViewListener(IXListViewListener l) {
		mListViewListener = l;
	}

	/**
	 * you can listen ListView.OnScrollListener or this one. it will invoke
	 * onXScrolling when header/footer scroll back.
	 */
	public interface OnXScrollListener extends OnScrollListener {
		void onXScrolling(View view);
	}

	/**
	 * implements this interface to get hp_refresh/load more event.
	 */
	public interface IXListViewListener {
		void onRefresh();

		void onLoadMore();
	}

	public void setHeaderViewBackColor(int color){
		mHeaderView.setBackgroundColor(color);
	}
	public void setFooterViewBackColor(int color){
		mFooterView.setBackgroundColor(color);
		mFooterView.getMoreView().setBackgroundColor(color);
	}

	public void setHeaderTextInfo(String[] textInfo) {
		mHeaderView.setTextInfo(textInfo);
	}
}
