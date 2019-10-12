package com.example.administrator.kotlintest.ui.divider

/**
 * Created by kangf on 2018/6/26.
 */
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.View
import com.example.baselibrary.widgets.UIUtils

class GridDividerItemDecoration(private val mDividerWidth: Int,  color: Int) : androidx.recyclerview.widget.RecyclerView.ItemDecoration() {
    private val mPaint: Paint?

    init {
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.color = UIUtils.getColor(color)
        mPaint.style = Paint.Style.FILL
    }


    override fun getItemOffsets(outRect: Rect, view: View, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val itemPosition = (view.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams).viewLayoutPosition
        val spanCount = getSpanCount(parent)
        val childCount = parent.adapter?.itemCount?:0

        val isLastRow = isLastRow(parent, itemPosition, spanCount, childCount)
//        val isLastColumn = isLastColumn(parent, itemPosition, spanCount, childCount)

        val top = 0
        val left: Int
        val right: Int
        var bottom: Int
        val eachWidth = (spanCount - 1) * mDividerWidth / spanCount
        val dl = mDividerWidth - eachWidth

        left = itemPosition % spanCount * dl
        right = eachWidth - left
        bottom = mDividerWidth
        if (isLastRow) {
            bottom = 0
        }
        outRect.set(left, top, right, bottom)

    }

    override fun onDraw(c: Canvas, parent: androidx.recyclerview.widget.RecyclerView, state: androidx.recyclerview.widget.RecyclerView.State) {
        super.onDraw(c, parent, state)
        draw(c, parent)
    }

    //绘制item分割线
    private fun draw(canvas: Canvas, parent: androidx.recyclerview.widget.RecyclerView) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val layoutParams = child.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams

            //画水平分隔线
            var left = child.left
            var right = child.right
            var top = child.bottom + layoutParams.bottomMargin
            var bottom = top + mDividerWidth
            if (mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
            //画垂直分割线
            top = child.top
            bottom = child.bottom + mDividerWidth
            left = child.right + layoutParams.rightMargin
            right = left + mDividerWidth
            if (mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint)
            }
        }
    }

//    private fun isLastColumn(parent: RecyclerView, pos: Int, spanCount: Int,
//                             childCount: Int): Boolean {
//        var childCount = childCount
//        val layoutManager = parent.layoutManager
//        if (layoutManager is GridLayoutManager) {
//            if ((pos + 1) % spanCount == 0) {// 如果是最后一列，则不需要绘制右边
//                return true
//            }
//        } else if (layoutManager is StaggeredGridLayoutManager) {
//            val orientation = layoutManager
//                    .orientation
//            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
//                if ((pos + 1) % spanCount == 0)
//                // 如果是最后一列，则不需要绘制右边
//                {
//                    return true
//                }
//            } else {
//                childCount -= childCount % spanCount
//                if (pos >= childCount)
//                // 如果是最后一列，则不需要绘制右边
//                    return true
//            }
//        }
//        return false
//    }

    private fun isLastRow(parent: androidx.recyclerview.widget.RecyclerView, pos: Int, spanCount: Int,
                          childCount: Int): Boolean {
        @Suppress("NAME_SHADOWING") var childCount = childCount
        val layoutManager = parent.layoutManager
        if (layoutManager is androidx.recyclerview.widget.GridLayoutManager) {
            val lines = if (childCount % spanCount == 0) childCount / spanCount else childCount / spanCount + 1
            return lines == pos / spanCount + 1
        } else if (layoutManager is androidx.recyclerview.widget.StaggeredGridLayoutManager) {
            val orientation = layoutManager
                    .orientation
            // StaggeredGridLayoutManager 且纵向滚动
            if (orientation == androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL) {
                childCount -= childCount % spanCount
                // 如果是最后一行，则不需要绘制底部
                if (pos >= childCount)
                    return true
            } else {
                // 如果是最后一行，则不需要绘制底部
                if ((pos + 1) % spanCount == 0) {
                    return true
                }
            }
        }
        return false
    }

//    private fun isFirstRow(parent: RecyclerView, pos: Int, spanCount: Int,
//                           childCount: Int): Boolean {
//        var childCount = childCount
//        val layoutManager = parent.layoutManager
//        if (layoutManager is GridLayoutManager) {
//            return pos / spanCount + 1 == 1
//        } else if (layoutManager is StaggeredGridLayoutManager) {
//            val orientation = layoutManager
//                    .orientation
//            // StaggeredGridLayoutManager 且纵向滚动
//            if (orientation == StaggeredGridLayoutManager.VERTICAL) {
//                childCount = childCount - childCount % spanCount
//                // 如果是最后一行，则不需要绘制底部
//                if (pos >= childCount)
//                    return true
//            } else {
//                // 如果是最后一行，则不需要绘制底部
//                if ((pos + 1) % spanCount == 0) {
//                    return true
//                }
//            }
//        }
//        return false
//    }

    //获取列数
    private fun getSpanCount(parent: androidx.recyclerview.widget.RecyclerView): Int {
        var spanCount = -1
        val layoutManager = parent.layoutManager
        if (layoutManager is androidx.recyclerview.widget.GridLayoutManager) {

            spanCount = layoutManager.spanCount
        } else if (layoutManager is androidx.recyclerview.widget.StaggeredGridLayoutManager) {
            spanCount = layoutManager
                    .spanCount
        }
        return spanCount
    }
}