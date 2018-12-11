package com.xfs.qrcode_module.recycleview;

import java.util.List;

/**
 * 对AbsDelegationAdapter的封装。
 * Created by zhangyang on 2017/4/5.
 */

public abstract class BaseDelegationAdapter extends AbsDelegationAdapter<List> {

    public BaseDelegationAdapter() {
        super();
    }

    /**
     * 添加自定义AdapterDelegatesManager支持
     *
     * @param delegatesManager
     */
    public BaseDelegationAdapter(AdapterDelegatesManager<List> delegatesManager) {
        super(delegatesManager);
    }

    /**
     * 注册BaseAdapterDelegate，在 {@link #getItemCount()} 不为0时必须调用此方法注册对应Delegate。
     * 最好在Adapter实例化时进行调用。
     *
     * @param delegate
     */
    protected final void registerAdapterDelegate(BaseAdapterDelegate delegate) {
        if (delegate != null) {
            delegatesManager.addDelegate(delegate);
        }
    }

    public final void registerIgnoreTypes(List<Integer> ignoreTypes) {
        delegatesManager.addIgnoreTypes(ignoreTypes);
    }

    public final void addIgnoreTypes(int ignoreType) {
        delegatesManager.addIgnoreType(ignoreType);
    }

    /**
     * 重设动画，以便重新执行
     */
    public void resetItemAnim(int position) {
        if (delegatesManager instanceof AnimatorAdapterDelegatesManager) {
            ((AnimatorAdapterDelegatesManager) delegatesManager).reset(position);
        }
    }
}
