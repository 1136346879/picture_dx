package com.xfs.qrcode_module.StickyItemRecycleview.view;

import android.graphics.Canvas;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wdx on 2018/4/25.
 */
public class StickyItemDecoration extends RecyclerView.ItemDecoration{
    /**
     * Adapter ：托管数据集合，为每个子项创建视图
     */
    private RecyclerView.Adapter<RecyclerView.ViewHolder> mAdapter;
    /**
     * 标记：UI滚动过程中是否找到标题
     */
    private boolean mCurrentUIFindStickView;
    /**
     * 标题距离顶部距离
     */
    private int mStickyItemViewMarginTop;
    /**
     * 标题布局高度
     */
    private int mItemViewHeight;
    /**
     * 标题的视图View
     */
    private View mStickyItemView;
    /**
     * 承载子项视图的holder
     */
    private RecyclerView.ViewHolder mViewHolder;
    /**
     * 子项布局管理
     */
    private LinearLayoutManager mLayoutManager;
    /**
     * 绑定数据的position
     */
    private int mBindDataPosition = -1;

    /**
     * 所有标题的position list
     */
    private List<Integer> mStickyPositionList = new ArrayList<>();

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getAdapter().getItemCount() <= 0) return; //非空判断
        mCurrentUIFindStickView = false;//标记默认不存在小标题
        mLayoutManager = (LinearLayoutManager) parent.getLayoutManager();//获取布局管理方式

        for (int i =0 ,size = parent.getChildCount() ; i < size ; i++){//viewgroup.getChildCount()：获取所有可见子元素个数。

            View item = parent.getChildAt(i); //循环得到每一个子项

            if ((boolean)item.getTag() == true){//判断第几个子项是标题（值在Adapter中设置）
                mCurrentUIFindStickView =true;//标记为true
                getStickyViewHolder(parent);//得到标题的 viewHolder
                cacheStickyViewPosition(i); //收集标题的 position

                if (item.getTop() <= 0) {//标题和父布局的距离。（一般初始化时候先进入）
                    bindDataForStickyView(mLayoutManager.findFirstVisibleItemPosition(), parent.getMeasuredWidth());//将第一个可见子项位置 和 父布局宽 传入
                } else {
                    if (mStickyPositionList.size() > 0) {
                        if (mStickyPositionList.size() == 1) {//若只缓存一个标题
                            bindDataForStickyView(mStickyPositionList.get(0), parent.getMeasuredWidth());
                        } else {
                            int currentPosition = mLayoutManager.findFirstVisibleItemPosition() + i;//得到标题在RecyclerView中的position
                            int indexOfCurrentPosition = mStickyPositionList.lastIndexOf(currentPosition);//根据标题的position获得所在缓存列表中的索引
                            bindDataForStickyView(mStickyPositionList.get(indexOfCurrentPosition - 1), parent.getMeasuredWidth());
                        }
                    }
                }

                if (item.getTop() > 0 && item.getTop() <= mItemViewHeight) {//处理两个标题叠在一起的绘制效果
                    mStickyItemViewMarginTop = mItemViewHeight - item.getTop();
                } else {
                    mStickyItemViewMarginTop = 0;
                    View nextStickyView = getNextStickyView(parent);//得到下一个标题view
                    if (nextStickyView != null && nextStickyView.getTop() <= mItemViewHeight) {//若两标题叠在一起了
                        mStickyItemViewMarginTop = mItemViewHeight - nextStickyView.getTop();//第二个标题盖住第一个标题多少了
                    }
                }

                drawStickyItemView(c);// 准备工作已就绪，开始画出吸附的标题

                break;  //结束循环
            }
        }

        if (!mCurrentUIFindStickView) {//取反判断（因为它默认值是false）表示：若存在小标题则进入
            mStickyItemViewMarginTop = 0;
            //判断子元素等于item总数并且缓存数大于0
            if (mLayoutManager.findFirstVisibleItemPosition() + parent.getChildCount() == parent.getAdapter().getItemCount() && mStickyPositionList.size() > 0) {
                bindDataForStickyView(mStickyPositionList.get(mStickyPositionList.size() - 1), parent.getMeasuredWidth());
            }
            drawStickyItemView(c);//绘制图层
        }

    }
    /**
     * 得到下一个标题
     * @param parent
     * @return
     */
    private View getNextStickyView(RecyclerView parent) {
        int num = 0;
        View nextStickyView = null;
        for (int m = 0, size = parent.getChildCount(); m < size; m++) {
            View view = parent.getChildAt(m);//循环获取每个子项
            if ((boolean)view.getTag() == true) {//拿到标题
                nextStickyView = view;
                num++;
            }
            if (num == 2) break;//拿到第二个标题 ，就结束循环。
        }
        return nextStickyView;
    }
    /**
     * 得到标题的 viewHolder
     * @param recyclerView
     */
    private void getStickyViewHolder(RecyclerView recyclerView) {
        if (mAdapter != null) return; //判断是否已创建
        mAdapter = recyclerView.getAdapter();
        mViewHolder = mAdapter.onCreateViewHolder(recyclerView, 1); //该方法属于Adapter中的重写Override

        mStickyItemView = mViewHolder.itemView;//得到布局
    }

    /**
     *  收集标题的 position
     * @param i
     */
    private void cacheStickyViewPosition(int i) {
        int position = mLayoutManager.findFirstVisibleItemPosition() + i;//得到标题在RecyclerView中的position
        if (!mStickyPositionList.contains(position)) {//防止重复
            mStickyPositionList.add(position);
        }
    }

    /**
     * 给StickyView绑定数据
     * @param position
     */
    private void bindDataForStickyView(int position, int width) {
        if (mBindDataPosition == position || mViewHolder == null) return;//已经是吸附位置了 或 视图不存在
        mBindDataPosition = position;
        mAdapter.onBindViewHolder(mViewHolder, mBindDataPosition);//改变标题的展示效果，该方法在Adapter中
        measureLayoutStickyItemView(width);//设置布局位置及大小
        mItemViewHeight = mViewHolder.itemView.getBottom() - mViewHolder.itemView.getTop();//计算标题布局高度
    }
    /**
     * 设置布局位置及大小
     * @param parentWidth  父布局宽度
     */
    private void measureLayoutStickyItemView(int parentWidth) {
        if (mStickyItemView == null || !mStickyItemView.isLayoutRequested()) return;

        int widthSpec = View.MeasureSpec.makeMeasureSpec(parentWidth, View.MeasureSpec.EXACTLY);
        int heightSpec;

        ViewGroup.LayoutParams layoutParams = mStickyItemView.getLayoutParams();
        if (layoutParams != null && layoutParams.height > 0) {
            heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
        } else {
            heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        }

        mStickyItemView.measure(widthSpec, heightSpec);
        /**
         * view.layout(l,t,r,b) ; 子布局相对于父布局的绘制的位置及大小。
         * l 和 t 是控件左边缘和上边缘相对于父类控件左边缘和上边缘的距离。r 和 b是控件右边缘和下边缘相对于父类控件左边缘和上边缘的距离。
         */
        mStickyItemView.layout(0, 0, mStickyItemView.getMeasuredWidth(), mStickyItemView.getMeasuredHeight());
    }

    /**
     * 绘制标题
     * @param canvas
     */
    private void drawStickyItemView(Canvas canvas) {
        if (mStickyItemView == null) return;

        int saveCount = canvas.save();//保存当前图层
        canvas.translate(0, -mStickyItemViewMarginTop);//图层转换位移
        mStickyItemView.draw(canvas);
        canvas.restoreToCount(saveCount); //恢复指定层的图层
    }
}
