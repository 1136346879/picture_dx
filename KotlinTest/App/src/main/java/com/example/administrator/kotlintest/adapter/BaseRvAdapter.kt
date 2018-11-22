package com.example.administrator.kotlintest.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Spanned
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.kotlintest.bridge.OnClickItem
import com.example.administrator.kotlintest.bridge.loadimg.LoadImage


/**
 * Created by kangf on 2018/6/18.
 */
abstract class BaseRvAdapter<T>(val mDatas: ArrayList<T>, private val resLayoutId: Int, val context: Context) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickItem: OnClickItem? = null


    @SuppressLint("UseSparseArrays")
    private var isShows = SparseArray<Boolean>()


    fun getVisibility(pos: Int): Boolean {
        return isShows[pos] ?: false
    }

    fun setVisibility(pos: Int, holder: BaseRvHolder, isVis: Boolean) {
        isShows.put(pos, isVis)
        val itemView = holder.itemView
        val param = itemView.layoutParams as RecyclerView.LayoutParams
        if (isVis) {
            itemView.visibility = View.VISIBLE
            param.height = RecyclerView.LayoutParams.WRAP_CONTENT
            param.width = RecyclerView.LayoutParams.MATCH_PARENT
        } else {
            itemView.visibility = View.GONE
            param.height = 1
            param.width = 0

        }
        itemView.layoutParams = param
    }


    fun setOnClickItem(action: (Int) -> Unit) {
        onClickItem = object : OnClickItem {
            override fun onClickItem(index: Int) {
                action(index)
            }
        }
    }

    abstract fun onBindView(holder: BaseRvHolder, data: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(resLayoutId, parent, false)
        return BaseRvHolder(view)
    }

    override fun getItemCount(): Int = if (mDatas == null) 0 else mDatas.size;

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        isShows.put(holder.adapterPosition, false)
        holder.itemView.setOnClickListener { onClickItem?.onClickItem(position) }

        if (mDatas.isNotEmpty())
            onBindView(holder as BaseRvHolder, mDatas.get(position))
    }

    fun addData(list: List<T>) {
        if (list != null) {
            mDatas.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun refreshData(list: List<T>) {
        mDatas.clear()
        addData(list)
    }

    fun removeData(t: T) {
        mDatas.remove(t)
        notifyDataSetChanged()
    }

    fun removeData(pos: Int) {
        mDatas.removeAt(pos)
        notifyDataSetChanged()
    }

    fun clear() {
        mDatas.clear()
        notifyDataSetChanged()
    }

    companion object {

        class BaseRvHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private var mViews = SparseArray<View>()


            companion object {
                private var convertView: View? = null

                fun get(context: Context, parent: ViewGroup, layoutId: Int): BaseRvHolder {
                    convertView = LayoutInflater.from(context).inflate(layoutId, parent, false)
                    return BaseRvHolder(convertView!!)
                }
            }

            init {
                convertView = itemView

            }


            fun <R> findViewById(viewId: Int): R {
                var view = mViews?.get(viewId)
                if (view == null) {
                    view = convertView!!.findViewById(viewId)
                    mViews.put(viewId, view)
                }
                return view!! as R
            }

            fun setText(viewId: Int, s: Any?) {
                var view = findViewById<TextView>(viewId)
                view.text = s?.toString() ?: ""
            }

            fun setText(viewId: Int, s: Spanned?) {
                var view = findViewById<TextView>(viewId)
                view.text = s ?: ""
            }

            fun loadAvatar(viewId: Int, url: String) {
                var imageView = itemView.findViewById<ImageView>(viewId)
                if (imageView != null)
                    LoadImage.instance().loadAvatar(imageView, url)
            }

            fun loadImage(viewId: Int?, url: String?) {
                if (viewId != null && url != null) {
                    var imageView = itemView.findViewById<ImageView>(viewId)
                    imageView?.let {
                        LoadImage.instance().loadImage(imageView, url)
                    }

                }

            }

            fun loadImage(viewId: Int, url: Int) {
                var imageView = itemView.findViewById<ImageView>(viewId)
                if (imageView != null)
                    LoadImage.instance().loadImage(imageView, url)
            }


        }
    }


}