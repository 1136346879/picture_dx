package com.example.administrator.kotlintest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import android.view.ViewGroup

import java.util.ArrayList
import android.support.v7.widget.GridLayoutManager


/**
 * Created by Kangfan on 2017/11/9.
 */

class BaseRvHeaderAndFooterWrapper : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /**
     * RecyclerView使用的，真正的Adapter
     */
    private var mInnerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>? = null

    private val mHeaderViews = ArrayList<View>()
    private val mFooterViews = ArrayList<View>()

    private val mDataObserver = object : RecyclerView.AdapterDataObserver() {

        override fun onChanged() {
            super.onChanged()
            notifyDataSetChanged()
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount)
            notifyItemRangeChanged(positionStart + headerViewsCount, itemCount)
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            notifyItemRangeInserted(positionStart + headerViewsCount, itemCount)
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            notifyItemRangeRemoved(positionStart + headerViewsCount, itemCount)
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount)
            val headerViewsCountCount = headerViewsCount
            notifyItemRangeChanged(fromPosition + headerViewsCountCount, toPosition + headerViewsCountCount + itemCount)
        }
    }

    val innerAdapter: RecyclerView.Adapter<*>?
        get() = mInnerAdapter

    /**
     * 返回第一个FoView
     * @return
     */
    val footerView: View?
        get() = if (footerViewsCount > 0) mFooterViews[0] else null

    /**
     * 返回第一个HeaderView
     * @return
     */
    val headerView: View?
        get() = if (headerViewsCount > 0) mHeaderViews[0] else null

    val headerViewsCount: Int
        get() = mHeaderViews.size

    val footerViewsCount: Int
        get() = mFooterViews.size

    constructor() {}

    constructor(innerAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>) {
        setAdapter(innerAdapter)
    }

    /**
     * 设置adapter
     * @param adapter
     */
    fun setAdapter(adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {

        if (adapter != null) {
            if (adapter !is RecyclerView.Adapter<*>)
                throw RuntimeException("your adapter must be a RecyclerView.Adapter")
        }

        if (mInnerAdapter != null) {
            notifyItemRangeRemoved(headerViewsCount, mInnerAdapter!!.itemCount)
            mInnerAdapter!!.unregisterAdapterDataObserver(mDataObserver)
        }

        this.mInnerAdapter = adapter
        mInnerAdapter!!.registerAdapterDataObserver(mDataObserver)
        notifyItemRangeInserted(headerViewsCount, mInnerAdapter?.itemCount?:0)
    }

    fun addHeaderView(header: View?) {

        if (header == null) {
            throw RuntimeException("header is null")
        }

        mHeaderViews.add(header)
        this.notifyDataSetChanged()
    }

    fun addFooterView(footer: View?) {

        if (footer == null) {
            throw RuntimeException("footer is null")
        }

        mFooterViews.add(footer)
        this.notifyDataSetChanged()
    }

    fun removeHeaderView(view: View) {
        mHeaderViews.remove(view)
        this.notifyDataSetChanged()
    }

    fun removeFooterView(view: View) {
        mFooterViews.remove(view)
        this.notifyDataSetChanged()
    }

    fun isHeader(position: Int): Boolean {
        return headerViewsCount > 0 && position == 0
    }

    fun isFooter(position: Int): Boolean {
        val lastPosition = itemCount - 1
        return footerViewsCount > 0 && position == lastPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val headerViewsCountCount = headerViewsCount
        return if (viewType < TYPE_HEADER_VIEW + headerViewsCountCount) {
            ViewHolder(mHeaderViews[viewType - TYPE_HEADER_VIEW])
        } else if (viewType >= TYPE_FOOTER_VIEW && viewType < Integer.MAX_VALUE / 2) {
            ViewHolder(mFooterViews[viewType - TYPE_FOOTER_VIEW])
        } else {
            mInnerAdapter!!.onCreateViewHolder(parent, viewType - Integer.MAX_VALUE / 2)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val headerViewsCountCount = headerViewsCount
        if (position >= headerViewsCountCount && position < headerViewsCountCount + mInnerAdapter!!.itemCount) {
            mInnerAdapter!!.onBindViewHolder(holder, position - headerViewsCountCount)
        } else {
            val layoutParams = holder.itemView.layoutParams
            if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
                layoutParams.isFullSpan = true
            }
        }
    }

    override fun getItemCount(): Int {
        return headerViewsCount + footerViewsCount + mInnerAdapter!!.itemCount
    }

    override fun getItemViewType(position: Int): Int {
        val innerCount = mInnerAdapter!!.itemCount
        val headerViewsCountCount = headerViewsCount
        if (position < headerViewsCountCount) {
            return TYPE_HEADER_VIEW + position
        } else if (headerViewsCountCount <= position && position < headerViewsCountCount + innerCount) {

            val innerItemViewType = mInnerAdapter!!.getItemViewType(position - headerViewsCountCount)
            if (innerItemViewType >= Integer.MAX_VALUE / 2) {
                throw IllegalArgumentException("your adapter's return value of getViewTypeCount() must < Integer.MAX_VALUE / 2")
            }
            return innerItemViewType + Integer.MAX_VALUE / 2
        } else {
            return TYPE_FOOTER_VIEW + position - headerViewsCountCount - innerCount
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        innerAdapter?.onAttachedToRecyclerView(recyclerView)

        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {

            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val viewType = getItemViewType(position)
                    if (mHeaderViews.get(viewType) != null) {
                        return layoutManager.spanCount
                    } else if (mFooterViews.get(viewType) != null) {
                        return layoutManager.spanCount
                    }
                    return 1
                }
            }
            layoutManager.spanCount = layoutManager.spanCount
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private val TYPE_HEADER_VIEW = Integer.MIN_VALUE
        private val TYPE_FOOTER_VIEW = Integer.MIN_VALUE + 1
    }
}
