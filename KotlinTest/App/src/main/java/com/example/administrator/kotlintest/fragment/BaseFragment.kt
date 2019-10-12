package com.example.administrator.kotlintest.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.baselibrary.widgets.Density
import com.trello.rxlifecycle2.components.support.RxFragment


/**
 * Created by Kangf on 2017/11/9.
 */

abstract class BaseFragment : RxFragment() {
    protected  var mContext: Context?=null
    protected  var mActivity: androidx.fragment.app.FragmentActivity?=null
    private var mView: View? = null
    protected var mIsFirstVisible = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        base()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = mContext as androidx.fragment.app.FragmentActivity?
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        if(mView!=null){
            val paraent = mView!!.parent as ViewGroup
            paraent.removeView(mView)
            return  mView
        }
        mView = inflater.inflate(layoutResId(), container, false)
        return mView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        logic()
        val isVis = isHidden || userVisibleHint
        if (isVis && mIsFirstVisible) {
            lazyLoad()
            mIsFirstVisible = false
        }
    }

    /**
     * 数据懒加载
     */
    open fun lazyLoad() {

    }

   override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            onVisible()
        } else {
            onInVisible()
        }
    }

   override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            onVisible()
        } else {
            onInVisible()
        }
    }

    /**
     * 当界面可见时的操作
     */
    open fun onVisible() {
        if (mIsFirstVisible && isResumed) {
            lazyLoad()
            mIsFirstVisible = false
        }
    }


    /**
     * 当界面不可见时的操作
     */
    open fun onInVisible() {

    }
    open fun base() {
        if (activity == null) {
            return
        }

        Density.setDefault(this.activity!!)
    }

    protected fun <T : View> getViewById(resId: Int): T {
        return this.view!!.findViewById(resId)
    }


    override fun onDetach() {
        super.onDetach()
        mContext = null
        mActivity = null
    }

    abstract fun layoutResId(): Int

    abstract fun init()

    abstract fun logic()
}
