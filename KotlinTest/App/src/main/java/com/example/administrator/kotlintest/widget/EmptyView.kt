package com.example.administrator.kotlintest.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.administrator.kotlintest.R

import org.jetbrains.anko.find

/**
 * Created by kangf on 2018/5/31.
 */
class EmptyView : LinearLayout {

    private lateinit var mInflater: LayoutInflater

    private lateinit var view: View
    private lateinit var ivError: ImageView
    private lateinit var tvError: TextView
    private lateinit var tvRetry: TextView

    private var onClickEmpty: OnClickEmpty? = null

    fun setOnClickEmpty(action: (TYPE) -> Unit) {
        this.onClickEmpty = object : OnClickEmpty {
            override fun onClick(type: TYPE) {
                action(type)
            }
        }
    }

    interface OnClickEmpty {
        fun onClick(type: TYPE)
    }

    constructor(context: Context) : super(context, null)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0) {
        this.visibility = View.GONE
        mInflater = LayoutInflater.from(context)
        view = mInflater.inflate(R.layout.empty_view, null, false)
        ivError = view.find(R.id.ivError)
        tvError = view.find(R.id.tvError)
        tvRetry = view.find(R.id.tvRetry)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @SuppressLint("SetTextI18n")
    fun setView(type: TYPE) {
        if (childCount > 0) removeAllViews()
        this.visibility = View.VISIBLE
        when (type) {
            TYPE.NO_ERROR -> this.visibility = View.GONE
            TYPE.EMPTY -> {
                ivError.setImageResource(R.drawable.error_no_result)
                tvError.text = "矮油~这里空空如也"
                tvRetry.visibility = View.GONE
            }
            TYPE.ERROR -> {
                ivError.setImageResource(R.drawable.error_no_network)
                tvError.text = "没有网络"
                tvRetry.visibility = View.VISIBLE
            }
            TYPE.EMPTY_SHOPPING_CART -> {
                ivError.setImageResource(R.drawable.error_no_result)
                tvError.text = "矮油~这里空空如也"
                tvRetry.visibility = View.VISIBLE
                tvRetry.text = "去逛一逛"
            }
            TYPE.EMPTY_COUPON -> {
                ivError.setImageResource(R.drawable.error_no_coupon)
                tvError.text = "暂时无可用优惠券，点击返回"
                tvRetry.visibility = View.GONE
            }
            TYPE.EMPTY_COUPON_RELOAD -> {
                ivError.setImageResource(R.drawable.error_no_network)
                tvError.text = "页面加载失败"
                tvRetry.visibility = View.VISIBLE
                tvRetry.text = "点击重试"
            }
            TYPE.IMAGE_SEARCH_EMPTY -> {
                ivError.setImageResource(R.drawable.error_no_result)
                tvError.text = "抱歉！暂时未识别到该类型商品～"
                tvRetry.visibility = View.GONE
            }
        }
        addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        if (onClickEmpty != null) {
            this.setOnClickListener { onClickEmpty!!.onClick(type) }
        }

    }

    fun setErrorMsg(msg: String) {
        tvError.text = msg
    }

    fun  setErrorImg(imgId: Int) {
        ivError.setImageResource(imgId)
    }

    enum class TYPE {
        EMPTY,
        IMAGE_SEARCH_EMPTY,
        ERROR,
        NO_ERROR,
        EMPTY_SHOPPING_CART,
        EMPTY_COUPON,
        EMPTY_COUPON_RELOAD
    }
}
