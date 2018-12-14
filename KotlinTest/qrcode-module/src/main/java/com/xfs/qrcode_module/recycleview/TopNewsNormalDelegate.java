package com.xfs.qrcode_module.recycleview;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.example.baselibrary.common.ToastUtil;
//import com.example.baselibrary.imageLoader.ImageLoader;
//import com.example.baselibrary.widgets.ToastUtilKt;
import com.example.baselibrary.common.ToastUtil;
import com.example.baselibrary.imageLoader.ImageLoader;
import com.jakewharton.rxbinding2.view.RxView;
import com.xfs.qrcode_module.R;
import com.xfs.qrcode_module.StickyItemRecycleview.StickyItemREcycleviewActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;


/**
 * 普通新闻UI适配器
 *
 * @author ZHANG PEIYUAN
 *         时间：2017/11/1
 * @author yangyi
 *         2017年12月13日16:09:02
 */
public class TopNewsNormalDelegate extends BaseAdapterDelegate<NewsInfo,
        TopNewsNormalDelegate.TopNewsNormalViewHolder> {

    private Context mContext;
    private String channelType;

    public TopNewsNormalDelegate(Context context, String channelType) {
        super(NewsInfo.class);
        this.mContext = context;
        this.channelType = channelType;
    }

    public void setChannelType(String type) {
        this.channelType = type;
    }

    /**
     * 因为目前只有一种新闻类型，所以不做区分
     * 已修改为只匹配新闻类型
     * 普通新闻和原创新闻
     */
    @Override
    protected boolean isViewType(@NonNull NewsInfo info, int position) {
//        return info.getLineType() == NewsType.NORMAL_NEWS.getId()
//                || info.getLineType() == NewsType.ORIGINAL_NEWS.getId();
        return true;
    }

    @Override
    protected void bindViewHolder(@NonNull NewsInfo item,
                                  @NonNull TopNewsNormalViewHolder holder,
                                  @NonNull List<Object> payloads) {
        holder.bindData(item);
    }


    @NonNull
    @Override
    protected TopNewsNormalViewHolder createViewHolder(ViewGroup parent) {
        return new TopNewsNormalViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_normal_news, parent, false));
    }

    /**
     * ViewHolder
     */
    class TopNewsNormalViewHolder extends BaseAdapterDelegate.ViewHolder implements Consumer<Object>{

        protected TextView tvTitle;
        protected TextView tvSource;
        protected TextView tvTime;
        protected NewsInfo newsInfo;
        protected ImageView ivItemImg;

        public TopNewsNormalViewHolder(View itemView){
            super(itemView);

            tvTitle = (TextView) itemView.findViewById(R.id.tv_news_title);
            tvSource = (TextView) itemView.findViewById(R.id.tv_news_source);
            tvTime = (TextView) itemView.findViewById(R.id.tv_news_time);
            ivItemImg = (ImageView) itemView.findViewById(R.id.iv_item_img);

            RxView.clicks(itemView)
                    .throttleFirst(1, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this);
        }

        public void bindData(NewsInfo info) {
            this.newsInfo = info;
            String title = info.getTitle();
            if (!TextUtils.isEmpty(title)) {
                tvTitle.setText(title);
            } else {
                tvTitle.setText("");
            }

            String media = info.getMedia();
            if (!TextUtils.isEmpty(media)) {
                tvSource.setText(media);
            } else {
                tvSource.setText("");
            }

            // 设置时间格式 2017-07-26 产品新需求
            String time = info.getTime();
            if (time != null) {
                time = time.trim();
                if (time.length() > 3) {
                    tvTime.setText(time.substring(0, time.length() - 3));
                    tvTime.setVisibility(View.VISIBLE);
                }
            }
            final String imgUrl = info.getImg();
            if (!TextUtils.isEmpty(imgUrl)) {
                ImageLoader.with(mContext).load(imgUrl)
                        .error(R.mipmap.icon_img_placeholder)
                        .placeholder(R.mipmap.icon_img_placeholder)
                        .into(ivItemImg);
            }else {
                ImageLoader.with(mContext).load(R.mipmap.icon_img_placeholder)
                        .into(ivItemImg);
            }
        }


        @Override
        public void accept(Object o) throws Exception {
            ToastUtil.Companion.showToast(mContext,"跳转至详情页but 现跳转至RecycleView列表带吸附的标题",1);

            mContext.startActivity(new Intent(mContext, StickyItemREcycleviewActivity.class));
//            if (newsInfo != null) {
//                ToastUtilKt.INSTANCE.showCustomToast("跳转至详情页");
//                DetailActivity.gotoNewDetailWith(mContext, Long.toString(newsInfo.getNewsId()), "1", TOPNEWS.getId(),TOPNEWS.getName());
//            }
        }
    }
}
