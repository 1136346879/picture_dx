package com.xfs.qrcode_module.recycleview;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：    shaoshuai
 * 时间：    2017/8/30 18:00
 * 电子邮箱：shaoshuai@staff.hexun.com
 * 描述: 重写AdapterDelegatesManager 添加item动画支持,添加获取item类型接口
 */

public class AnimatorAdapterDelegatesManager<T> extends AdapterDelegatesManager<T> {
    private List<Integer> itemTypes = new ArrayList<>();
    private int lastPosition = -1;

    public static AnimatorAdapterDelegatesManager create() {
        return new AnimatorAdapterDelegatesManager();
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder.getAdapterPosition() > lastPosition) {
            PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("translationY", 500f, 0f, 0f);
            PropertyValuesHolder holderAlpha = PropertyValuesHolder.ofFloat("alpha", 0.2f, 1f, 1f);
            ObjectAnimator.ofPropertyValuesHolder(viewHolder.itemView, holderY, holderAlpha).setDuration(550).start();
            lastPosition = viewHolder.getAdapterPosition();
        }
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        super.onViewDetachedFromWindow(viewHolder);
        viewHolder.itemView.clearAnimation();
    }

    @Override
    public AdapterDelegatesManager<T> addDelegate(@NonNull AdapterDelegate<T> delegate) {
        itemTypes.add(delegates.size() + 1);
        return super.addDelegate(delegate);
    }

    @Override
    public AdapterDelegatesManager<T> addDelegate(int viewType, boolean allowReplacingDelegate, @NonNull AdapterDelegate<T> delegate) {
        if (!itemTypes.contains(viewType)) {
            itemTypes.add(viewType);
        }
        return super.addDelegate(viewType, allowReplacingDelegate, delegate);
    }

    @Override
    public AdapterDelegatesManager<T> addDelegate(int viewType, @NonNull AdapterDelegate<T> delegate) {
        if (!itemTypes.contains(viewType)) {
            itemTypes.add(viewType);
        }
        return super.addDelegate(viewType, delegate);
    }

    /**
     * 重设以便动画重新开始
     */
    public void reset(int position) {
        lastPosition = position;
    }

    public List<Integer> getItemTypes() {
        return itemTypes;
    }
}
