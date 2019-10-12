package com.xfs.qrcode_module.recycleview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 对AdapterDelegate的封装。
 * D： 操作的数据类型
 * T： 显示的ViewHolder类型
 * Created by zhangyang on 2017/4/5.
 * <p>
 * <p>
 *
 * @author yangyi
 *         2017年12月13日17:32:17
 *         -------------------------------------------------
 *         原本是实际调用者所编写的XXXAdapterDelegate直接继承AdapterDelegate，而现在在两者中间多了一层Base基类，
 *         用泛型框定。
 */

public abstract class BaseAdapterDelegate<D, T extends BaseAdapterDelegate.ViewHolder>
        extends AdapterDelegate<List> {

    private Class<D> dataType;

    public BaseAdapterDelegate(Class<D> dataType) {
        if (dataType == null) {
            throw new IllegalArgumentException("dataType may not be null");
        }
        this.dataType = dataType;

    }

    @Override
    protected boolean isForViewType(@NonNull List items, int position) {
        Object item = items.get(position);
        if (dataType.isInstance(item)) {
            return isViewType((D) item, position);
        }
        return false;
    }

    @Override
    protected void onBindViewHolder(@NonNull List items,
                                    int position,
                                    @NonNull RecyclerView.ViewHolder holder,
                                    @NonNull List<Object> payloads) {
        bindViewHolder((D) items.get(position), (T) holder, payloads);
    }

    @NonNull
    @Override
    protected RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {
        return createViewHolder(parent);
    }

    protected abstract boolean isViewType(@NonNull D item, int position);

    protected abstract void bindViewHolder(@NonNull D item,
                                           @NonNull T holder,
                                           @NonNull List<Object> payloads);

    @NonNull
    protected abstract T createViewHolder(ViewGroup parent);

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

    }
}
