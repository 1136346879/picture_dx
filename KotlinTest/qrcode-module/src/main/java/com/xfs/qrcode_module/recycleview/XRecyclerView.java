package com.xfs.qrcode_module.recycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.google.android.material.appbar.AppBarLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.AppBarStateChangeListener;
import com.jcodecraeer.xrecyclerview.ArrowRefreshHeader;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.SimpleViewSwitcher;

import java.util.ArrayList;
import java.util.List;

public class XRecyclerView extends RecyclerView {
    private boolean appBarExpand = true;
    private boolean isLoadingData = false;
    private boolean isNoMore = false;
    private int mRefreshProgressStyle = ProgressStyle.SysProgress;
    private int mLoadingMoreProgressStyle = ProgressStyle.SysProgress;
    private ArrayList<View> mHeaderViews = new ArrayList<>();
    private WrapAdapter mWrapAdapter;
    private float mLastY = -1;
    private static final float DRAG_RATE = 2;
    private LoadingListener mLoadingListener;
    private ArrowRefreshHeader mRefreshHeader;
    private boolean pullRefreshEnabled = true;
    private boolean loadingMoreEnabled = true;
    //下面的ItemViewType是保留值(ReservedItemViewType),如果用户的adapter与它们重复将会强制抛出异常。不过为了简化,我们检测到重复时对用户的提示是ItemViewType必须小于10000
    private static final int TYPE_REFRESH_HEADER = 10000;//设置一个很大的数字,尽可能避免和用户的adapter冲突
    private static final int TYPE_FOOTER = 10001;
    private static final int HEADER_INIT_INDEX = 10002;
    private List<Integer> sHeaderTypes = new ArrayList<>();//每个header必须有不同的type,不然滚动的时候顺序会变化
    private int mPageCount = 0;
    //adapter没有数据的时候显示,类似于listView的emptyView
    private View mEmptyView;
    private View mFootView;
    private final AdapterDataObserver mDataObserver = new DataObserver();
    AppBarLayout appBarLayout = null;
    private AppBarStateChangeListener.State appbarState = AppBarStateChangeListener.State.EXPANDED;
    private HeaderViewHolderBuilder headerViewHolderBuilder;

    public XRecyclerView(Context context) {
        this(context, null);
    }

    public XRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        if (pullRefreshEnabled) {
            mRefreshHeader = new ArrowRefreshHeader(getContext());
            mRefreshHeader.setProgressStyle(mRefreshProgressStyle);
        }
        LoadingMoreFooter footView = new LoadingMoreFooter(getContext());
        footView.setProgressStyle(mLoadingMoreProgressStyle);
        mFootView = footView;
        mFootView.setVisibility(View.GONE);
    }

//    public void setFootViewText(String loading, String noMore) {
//        if(mFootView instanceof LoadingMoreFooter){
//            ((LoadingMoreFooter) mFootView).setLoadingHint(loading);
//            ((LoadingMoreFooter) mFootView).setNoMoreHint(noMore);
//        }
//    }

    public void addHeaderView(View view) {
        sHeaderTypes.add(HEADER_INIT_INDEX + mHeaderViews.size());
        if (getAdapter() instanceof BaseDelegationAdapter) {
            ((BaseDelegationAdapter)getAdapter()).addIgnoreTypes(HEADER_INIT_INDEX + mHeaderViews.size());
        }
        mHeaderViews.add(view);
        if (mWrapAdapter != null) {
            mWrapAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 添加第一个位置headView
     * @param view
     */
    public void addFirstHeadView(View view) {
        sHeaderTypes.add(HEADER_INIT_INDEX + mHeaderViews.size());
        if (getAdapter() instanceof BaseDelegationAdapter) {
            ((BaseDelegationAdapter)getAdapter()).addIgnoreTypes(HEADER_INIT_INDEX + mHeaderViews.size());
        }
        mHeaderViews.add(0,view);
        if (mWrapAdapter != null) {
            mWrapAdapter.notifyDataSetChanged();
        }
    }


    public int getHeaderViewCount() {
        if (null != mHeaderViews) {
            return mHeaderViews.size();
        }
        return 0;
    }

    //根据header的ViewType判断是哪个header
    private View getHeaderViewByType(int itemType) {
        if (!isHeaderType(itemType)) {
            return null;
        }
        return mHeaderViews.get(itemType - HEADER_INIT_INDEX);
    }

    //判断一个type是否为HeaderType
    private boolean isHeaderType(int itemViewType) {
        return mHeaderViews.size() > 0 && sHeaderTypes.contains(itemViewType);
    }

    //判断是否是XRecyclerView保留的itemViewType
    private boolean isReservedItemViewType(int itemViewType) {
        if (itemViewType == TYPE_REFRESH_HEADER || itemViewType == TYPE_FOOTER || sHeaderTypes.contains(itemViewType)) {
            return true;
        } else {
            return false;
        }
    }

    public void showFootView(String hint) {
        if (mFootView instanceof LoadingMoreFooter) {
            if (hint != null) {
                View view = ((LoadingMoreFooter) mFootView).getChildAt(1);
                View progress = ((LoadingMoreFooter) mFootView).getChildAt(0);
                if (progress instanceof SimpleViewSwitcher) {
                    progress.setVisibility(View.GONE);
                }
                if (view instanceof TextView) {
                    ((TextView) view).setText(hint);
                    mFootView.setVisibility(View.VISIBLE);
                }
            } else {
                View progress = ((LoadingMoreFooter) mFootView).getChildAt(0);
                if (progress instanceof AVLoadingIndicatorView) {
                    progress.setVisibility(View.GONE);
                }
                mFootView.setVisibility(View.VISIBLE);
            }
        }
    }

    public void setFootView(final View view) {
        mFootView = view;
    }

    public void loadMoreComplete() {
        isLoadingData = false;
        if (mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) mFootView).setState(LoadingMoreFooter.STATE_COMPLETE);
        } else {
            mFootView.setVisibility(View.GONE);
        }
    }

    public void setNoMore(boolean noMore) {
        isLoadingData = false;
        isNoMore = noMore;
        if (mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) mFootView).setState(isNoMore ? LoadingMoreFooter.STATE_NOMORE : LoadingMoreFooter.STATE_COMPLETE);
        } else {
            mFootView.setVisibility(View.GONE);
        }
    }

    public void refresh() {
        if (pullRefreshEnabled && mLoadingListener != null) {
            mRefreshHeader.setState(ArrowRefreshHeader.STATE_REFRESHING);
            mLoadingListener.onRefresh();
        }
    }

    /**
     * 仅显示下拉刷新状态，不会回调{@link LoadingListener#onRefresh()}
     * 需要调用{@link XRecyclerView#refreshComplete()}取消状态.
     */
    public void showRefresh() {
        if (pullRefreshEnabled && mLoadingListener != null) {
            mRefreshHeader.setState(ArrowRefreshHeader.STATE_REFRESHING);
        }
    }

    public void reset() {
        setNoMore(false);
        loadMoreComplete();
        refreshComplete();
    }

    public void refreshComplete() {
        mRefreshHeader.refreshComplete();
        setNoMore(false);
    }

    public void setRefreshHeader(ArrowRefreshHeader refreshHeader) {
        mRefreshHeader = refreshHeader;
    }

    public void setPullRefreshEnabled(boolean enabled) {
        pullRefreshEnabled = enabled;
    }

    public void setLoadingMoreEnabled(boolean enabled) {
        loadingMoreEnabled = enabled;
        if (!enabled) {
            if (mFootView instanceof LoadingMoreFooter) {
                ((LoadingMoreFooter) mFootView).setState(LoadingMoreFooter.STATE_COMPLETE);
            }
        }
    }

    public void setRefreshProgressStyle(int style) {
        mRefreshProgressStyle = style;
        if (mRefreshHeader != null) {
            mRefreshHeader.setProgressStyle(style);
        }
    }

    public void setLoadingMoreProgressStyle(int style) {
        mLoadingMoreProgressStyle = style;
        if (mFootView instanceof LoadingMoreFooter) {
            ((LoadingMoreFooter) mFootView).setProgressStyle(style);
        }
    }

    public void setArrowImageView(int resId) {
        if (mRefreshHeader != null) {
            mRefreshHeader.setArrowImageView(resId);
        }
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
        mDataObserver.onChanged();
    }

    public View getEmptyView() {
        return mEmptyView;
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (adapter == null) {
            super.setAdapter(adapter);
            return;
        }
        mWrapAdapter = new WrapAdapter(adapter);
        super.setAdapter(mWrapAdapter);
        if (adapter instanceof BaseDelegationAdapter) {
            List<Integer> integers = new ArrayList<>();
            integers.add(TYPE_REFRESH_HEADER);
            integers.add(TYPE_FOOTER);
            integers.addAll(sHeaderTypes);
            ((BaseDelegationAdapter) adapter).registerIgnoreTypes(integers);
        }
        adapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    //避免用户自己调用getAdapter() 引起的ClassCastException
    @Override
    public Adapter getAdapter() {
        if (mWrapAdapter != null) {
            return mWrapAdapter.getOriginalAdapter();
        } else {
            return null;
        }
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        if (mWrapAdapter != null) {
            if (layout instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) layout);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (mWrapAdapter.isHeader(position) || mWrapAdapter.isFooter(position) || mWrapAdapter.isRefreshHeader(position))
                                ? gridManager.getSpanCount() : 1;
                    }
                });

            }
        }
    }

    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        if (state == RecyclerView.SCROLL_STATE_IDLE && mLoadingListener != null && !isLoadingData && loadingMoreEnabled) {
            LayoutManager layoutManager = getLayoutManager();
            int lastVisibleItemPosition;
            if (layoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                int[] into = new int[((StaggeredGridLayoutManager) layoutManager).getSpanCount()];
                ((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(into);
                lastVisibleItemPosition = findMax(into);
            } else {
                lastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            }
            if (layoutManager.getChildCount() > 0
                    && lastVisibleItemPosition >= layoutManager.getItemCount() - 1 && !isNoMore && mRefreshHeader.getState() < ArrowRefreshHeader.STATE_REFRESHING) {
                isLoadingData = true;
                if (mFootView instanceof LoadingMoreFooter) {
                    ((LoadingMoreFooter) mFootView).setState(LoadingMoreFooter.STATE_LOADING);
                } else {
                    mFootView.setVisibility(View.VISIBLE);
                }
                mLoadingListener.onLoadMore();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (mLastY == -1) {
            mLastY = ev.getRawY();
        }
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = ev.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float deltaY = ev.getRawY() - mLastY;
                if (deltaY == 0.0) {
                    return true;
                }
                mLastY = ev.getRawY();
                if (isOnTop() && pullRefreshEnabled && appbarState == AppBarStateChangeListener.State.EXPANDED) {
                    mRefreshHeader.onMove(deltaY / DRAG_RATE);
                    if (mRefreshHeader.getVisibleHeight() > 0 && mRefreshHeader.getState() < ArrowRefreshHeader.STATE_REFRESHING) {
                        return false;
                    }
                }
                break;
            default:
                mLastY = -1; // reset
                if (isOnTop() && pullRefreshEnabled) {
                    if (mRefreshHeader.releaseAction()) {
                        if (mLoadingListener != null) {
                            mLoadingListener.onRefresh();
                        }
                    }
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private boolean isOnTop() {
        if (mRefreshHeader.getParent() != null) {
            return true;
        } else {
            return false;
        }
    }

    private class DataObserver extends AdapterDataObserver {
        @Override
        public void onChanged() {
            if (mWrapAdapter != null) {
                mWrapAdapter.notifyDataSetChanged();
            }
            if (mWrapAdapter != null && mEmptyView != null) {
                int emptyCount = 1 + mWrapAdapter.getHeadersCount();
                if (loadingMoreEnabled) {
                    emptyCount++;
                }
                if (mWrapAdapter.getItemCount() == emptyCount) {
                    mEmptyView.setVisibility(View.VISIBLE);
                    XRecyclerView.this.setVisibility(View.GONE);
                } else {

                    mEmptyView.setVisibility(View.GONE);
                    XRecyclerView.this.setVisibility(View.VISIBLE);
                }
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    }

    ;

    private class WrapAdapter extends Adapter<ViewHolder> {

        private Adapter adapter;

        public WrapAdapter(Adapter adapter) {
            this.adapter = adapter;
        }

        public Adapter getOriginalAdapter() {
            return this.adapter;
        }

        public boolean isHeader(int position) {
            return position >= 1 && position < mHeaderViews.size() + 1;
        }

        public boolean isFooter(int position) {
            if (loadingMoreEnabled) {
                return position == getItemCount() - 1;
            } else {
                return false;
            }
        }

        public boolean isRefreshHeader(int position) {
            return position == 0;
        }

        public int getHeadersCount() {
            return mHeaderViews.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_REFRESH_HEADER) {
                return new SimpleViewHolder(mRefreshHeader);
            } else if (isHeaderType(viewType)) {
                View view = getHeaderViewByType(viewType);
                ViewHolder viewHolder = headerViewHolderBuilder != null ? headerViewHolderBuilder.buildViewHolder(view): null;
                if (viewHolder == null) {
                    viewHolder = new SimpleViewHolder(view);
                }
                return viewHolder;
            } else if (viewType == TYPE_FOOTER) {
                return new SimpleViewHolder(mFootView);
            }
            return adapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (isHeader(position) || isRefreshHeader(position)) {
                if (getLayoutManager() instanceof ItemSpaceWatcher) {
                    ((ItemSpaceWatcher) getLayoutManager()).onBind(holder, position);
                }
                return;
            }
            int adjPosition = position - (getHeadersCount() + 1);
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    adapter.onBindViewHolder(holder, adjPosition);
                    if (getLayoutManager() instanceof ItemSpaceWatcher) {
                        ((ItemSpaceWatcher) getLayoutManager()).onBind(holder, adjPosition);
                    }
                }
            }
        }

        // some times we need to override this
        @Override
        public void onBindViewHolder(ViewHolder holder, int position, List<Object> payloads) {
            if (isHeader(position) || isRefreshHeader(position)) {
                if (getLayoutManager() instanceof ItemSpaceWatcher) {
                    ((ItemSpaceWatcher) getLayoutManager()).onBind(holder, position);
                }
                return;
            }
            int adjPosition = position - (getHeadersCount() + 1);
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    if (payloads.isEmpty()) {
                        adapter.onBindViewHolder(holder, adjPosition);
                    } else {
                        adapter.onBindViewHolder(holder, adjPosition, payloads);
                    }
                    if (getLayoutManager() instanceof ItemSpaceWatcher) {
                        ((ItemSpaceWatcher) getLayoutManager()).onBind(holder, adjPosition);
                    }
                }
            }
        }

        @Override
        public int getItemCount() {
            if (loadingMoreEnabled) {
                if (adapter != null) {
                    return getHeadersCount() + adapter.getItemCount() + 2;
                } else {
                    return getHeadersCount() + 2;
                }
            } else {
                if (adapter != null) {
                    return getHeadersCount() + adapter.getItemCount() + 1;
                } else {
                    return getHeadersCount() + 1;
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            int adjPosition = position - (getHeadersCount() + 1);
            if (isRefreshHeader(position)) {
                return TYPE_REFRESH_HEADER;
            }
            if (isHeader(position)) {
                position = position - 1;
                return sHeaderTypes.get(position);
            }
            if (isFooter(position)) {
                return TYPE_FOOTER;
            }
            int adapterCount;
            if (adapter != null) {
                adapterCount = adapter.getItemCount();
                if (adjPosition < adapterCount) {
                    int type = adapter.getItemViewType(adjPosition);
                    if (isReservedItemViewType(type)) {
                        throw new IllegalStateException("XRecyclerView require itemViewType in adapter should be less than 10000 ");
                    }
                    return type;
                }
            }
            return 0;
        }

        @Override
        public long getItemId(int position) {
            if (adapter != null && position >= getHeadersCount() + 1) {
                int adjPosition = position - (getHeadersCount() + 1);
                if (adjPosition < adapter.getItemCount()) {
                    return adapter.getItemId(adjPosition);
                }
            }
            return -1;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof GridLayoutManager) {
                final GridLayoutManager gridManager = ((GridLayoutManager) manager);
                gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (isHeader(position) || isFooter(position) || isRefreshHeader(position))
                                ? gridManager.getSpanCount() : 1;
                    }
                });
            }
            adapter.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            adapter.onDetachedFromRecyclerView(recyclerView);
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams
                    && (isHeader(holder.getLayoutPosition()) || isRefreshHeader(holder.getLayoutPosition()) || isFooter(holder.getLayoutPosition()))) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
            adapter.onViewAttachedToWindow(holder);
            if (getLayoutManager() instanceof ItemSpaceWatcher) {
                ((ItemSpaceWatcher) getLayoutManager()).onAttached(holder);
            }
        }

        @Override
        public void onViewDetachedFromWindow(ViewHolder holder) {
            adapter.onViewDetachedFromWindow(holder);
            if (getLayoutManager() instanceof ItemSpaceWatcher) {
                ((ItemSpaceWatcher) getLayoutManager()).onDetached(holder);
            }
        }

        @Override
        public void onViewRecycled(ViewHolder holder) {
            adapter.onViewRecycled(holder);
            if (getLayoutManager() instanceof ItemSpaceWatcher) {
                ((ItemSpaceWatcher) getLayoutManager()).onRecycler(holder, true);
            }
        }

        @Override
        public boolean onFailedToRecycleView(ViewHolder holder) {
            boolean bol = adapter.onFailedToRecycleView(holder);
            if (getLayoutManager() instanceof ItemSpaceWatcher) {
                ((ItemSpaceWatcher) getLayoutManager()).onRecycler(holder, false);
            }
            return bol;
        }

        @Override
        public void unregisterAdapterDataObserver(AdapterDataObserver observer) {
            adapter.unregisterAdapterDataObserver(observer);
        }

        @Override
        public void registerAdapterDataObserver(AdapterDataObserver observer) {
            adapter.registerAdapterDataObserver(observer);
        }

        private class SimpleViewHolder extends ViewHolder {
            public SimpleViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    public void setLoadingListener(LoadingListener listener) {
        mLoadingListener = listener;
    }

    public interface LoadingListener {

        void onRefresh();

        void onLoadMore();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //解决和CollapsingToolbarLayout冲突的问题

        ViewParent p = getParent();
        while (p != null) {
            if (p instanceof CoordinatorLayout) {
                break;
            }
            p = p.getParent();
        }
        if (p instanceof CoordinatorLayout) {
            CoordinatorLayout coordinatorLayout = (CoordinatorLayout) p;
            final int childCount = coordinatorLayout.getChildCount();
            for (int i = childCount - 1; i >= 0; i--) {
                final View child = coordinatorLayout.getChildAt(i);
                if (child instanceof AppBarLayout) {
                    appBarLayout = (AppBarLayout) child;
                    break;
                }
            }
            if (appBarLayout != null) {
                appBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
                    @Override
                    public void onStateChanged(AppBarLayout appBarLayout, State state) {
                        appbarState = state;
                    }
                });
            }
        }
    }

    public class DividerItemDecoration extends ItemDecoration {

        private Drawable mDivider;
        private int mOrientation;

        /**
         * Sole constructor. Takes in a {@link Drawable} to be used as the interior
         * divider.
         *
         * @param divider A divider {@code Drawable} to be drawn on the RecyclerView
         */
        public DividerItemDecoration(Drawable divider) {
            mDivider = divider;
        }

        /**
         * Draws horizontal or vertical dividers onto the parent RecyclerView.
         *
         * @param canvas The {@link Canvas} onto which dividers will be drawn
         * @param parent The RecyclerView onto which dividers are being added
         * @param state  The current RecyclerView.State of the RecyclerView
         */
        @Override
        public void onDraw(Canvas canvas, RecyclerView parent, State state) {
            if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                drawHorizontalDividers(canvas, parent);
            } else if (mOrientation == LinearLayoutManager.VERTICAL) {
                drawVerticalDividers(canvas, parent);
            }
        }

        /**
         * Determines the size and location of offsets between items in the parent
         * RecyclerView.
         *
         * @param outRect The {@link Rect} of offsets to be added around the child
         *                view
         * @param view    The child view to be decorated with an offset
         * @param parent  The RecyclerView onto which dividers are being added
         * @param state   The current RecyclerView.State of the RecyclerView
         */
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
            super.getItemOffsets(outRect, view, parent, state);

            if (parent.getChildAdapterPosition(view) <= mWrapAdapter.getHeadersCount() + 1) {
                return;
            }
            mOrientation = ((LinearLayoutManager) parent.getLayoutManager()).getOrientation();
            if (mOrientation == LinearLayoutManager.HORIZONTAL) {
                outRect.left = mDivider.getIntrinsicWidth();
            } else if (mOrientation == LinearLayoutManager.VERTICAL) {
                outRect.top = mDivider.getIntrinsicHeight();
            }
        }

        /**
         * Adds dividers to a RecyclerView with a LinearLayoutManager or its
         * subclass oriented horizontally.
         *
         * @param canvas The {@link Canvas} onto which horizontal dividers will be
         *               drawn
         * @param parent The RecyclerView onto which horizontal dividers are being
         *               added
         */
        private void drawHorizontalDividers(Canvas canvas, RecyclerView parent) {
            int parentTop = parent.getPaddingTop();
            int parentBottom = parent.getHeight() - parent.getPaddingBottom();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = parent.getChildAt(i);

                LayoutParams params = (LayoutParams) child.getLayoutParams();

                int parentLeft = child.getRight() + params.rightMargin;
                int parentRight = parentLeft + mDivider.getIntrinsicWidth();

                mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
                mDivider.draw(canvas);
            }
        }

        /**
         * Adds dividers to a RecyclerView with a LinearLayoutManager or its
         * subclass oriented vertically.
         *
         * @param canvas The {@link Canvas} onto which vertical dividers will be
         *               drawn
         * @param parent The RecyclerView onto which vertical dividers are being
         *               added
         */
        private void drawVerticalDividers(Canvas canvas, RecyclerView parent) {
            int parentLeft = parent.getPaddingLeft();
            int parentRight = parent.getWidth() - parent.getPaddingRight();

            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount - 1; i++) {
                View child = parent.getChildAt(i);

                LayoutParams params = (LayoutParams) child.getLayoutParams();

                int parentTop = child.getBottom() + params.bottomMargin;
                int parentBottom = parentTop + mDivider.getIntrinsicHeight();

                mDivider.setBounds(parentLeft, parentTop, parentRight, parentBottom);
                mDivider.draw(canvas);
            }
        }
    }

    public void setSlowFling(boolean allowFling) {
        appBarExpand = allowFling;
    }

    @Override
    public boolean fling(int velocityX, int velocityY) {
        if (appBarLayout != null && !appBarExpand) {
            velocityY = velocityY / 3;
        } else {
            velocityY = velocityY * 2 / 3;
        }
        return super.fling(velocityX, velocityY);
    }

    @Override
    protected void onDetachedFromWindow() {
        if (mFootView instanceof LoadingMoreFooter) {
            LoadingMoreFooter footer = (LoadingMoreFooter)mFootView;
            footer.recycle();
        }
        super.onDetachedFromWindow();
    }

    public void setHeaderViewHolderBuilder(HeaderViewHolderBuilder headerViewHolderBuilder) {
        this.headerViewHolderBuilder = headerViewHolderBuilder;
    }

    public interface HeaderViewHolderBuilder {
        ViewHolder buildViewHolder(View view);
    }

    public interface ItemSpaceWatcher {
        void onBind(RecyclerView.ViewHolder viewHolder, int position);
        void onRecycler(RecyclerView.ViewHolder viewHolder, boolean successful);
        void onAttached(RecyclerView.ViewHolder viewHolder);
        void onDetached(RecyclerView.ViewHolder viewHolder);
    }
}