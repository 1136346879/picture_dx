package com.example.administrator.kotlintest.ui.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.text.Spanned
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.administrator.kotlintest.bridge.OnClickItem
import com.example.administrator.kotlintest.bridge.loadimg.LoadImage
import com.example.administrator.kotlintest.picture.PictureType


/**
 * Created by kangf on 2018/6/18.
 */
abstract class BaseRvAdapter<T>(val mDatas: ArrayList<T>, private val resLayoutId: Int, val context: Context) :
        androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {

    private var onClickItem: OnClickItem? = null


    fun setVisibility(holder: BaseRvHolder, isVis: Boolean) {
        val itemView = holder.itemView
        val param = itemView.layoutParams as androidx.recyclerview.widget.RecyclerView.LayoutParams
        if (isVis) {
            itemView.visibility = View.VISIBLE
            param.height = androidx.recyclerview.widget.RecyclerView.LayoutParams.WRAP_CONTENT
            param.width = androidx.recyclerview.widget.RecyclerView.LayoutParams.MATCH_PARENT
        } else {
            itemView.visibility = View.GONE
            param.height = 1
            param.width = 0

        }
        itemView.layoutParams = param
    }


    fun setOnClickItem(action: (Int) -> Unit) {
        onClickItem = object : OnClickItem {
            override fun onClickItem(position: Int) {
                action(position)
            }
        }
    }

    abstract fun onBindView(holder: BaseRvHolder, data: T)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val view = LayoutInflater.from(context).inflate(resLayoutId, parent, false)
        return BaseRvHolder(view)
    }

    override fun getItemCount(): Int = mDatas.size

    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { onClickItem?.onClickItem(position) }

        if (mDatas.isNotEmpty())
            onBindView(holder as BaseRvHolder, mDatas[position])
    }

    private fun addData(list: List<T>) {
        mDatas.addAll(list)
        notifyDataSetChanged()
    }

    fun refreshData(list: List<T>) {
        mDatas.clear()
        addData(list)
    }

//    fun removeData(t: T) {
//        mDatas.remove(t)
//        notifyDataSetChanged()
//    }

    fun removeData(pos: Int) {
        mDatas.removeAt(pos)
        notifyDataSetChanged()
    }

    fun clear() {
        mDatas.clear()
        notifyDataSetChanged()
    }

    companion object {

        class BaseRvHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

            private var mViews = SparseArray<View>()

            @Suppress("UNCHECKED_CAST")
            fun <R> findViewById(viewId: Int): R {
                var view = mViews.get(viewId)
                if (view == null) {
                    view = itemView.findViewById(viewId)
                    mViews.put(viewId, view)
                }
                return if (view == null)
                    itemView.findViewById<TextView>(viewId) as R
                else
                    view as R
            }

            fun setText(viewId: Int, s: Any?) {
                val view = findViewById<TextView>(viewId)
                view.text = s?.toString() ?: ""
            }

            fun setText(viewId: Int, s: Spanned?) {
                val view = findViewById<TextView>(viewId)
                view.text = s ?: ""
            }

//            fun loadAvatar(viewId: Int, url: String) {
//                val imageView = findViewById<ImageView>(viewId)
//                LoadImage.instance().loadAvatar(imageView, url)
//            }

            fun loadImage(viewId: Int?, url: String?, pictureType: PictureType? = null) {
                if (viewId != null && url != null) {
                    val imageView = findViewById<ImageView>(viewId)
                    LoadImage.instance().loadImage(imageView, url, pictureType)

                }

            }

            fun loadImage(viewId: Int, url: Int) {
                val imageView = findViewById<ImageView>(viewId)
                LoadImage.instance().loadImage(imageView, url)
            }


        }
    }


}