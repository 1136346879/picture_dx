package com.xfs.qrcode_module.recycleview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

class NewsAdapter extends BaseDelegationAdapter{

    private TopNewsNormalDelegate topNewsNormalDelegate;//普通新闻
//    private GroupPictureDelegate pictureDelegate;//三小图
//    private TopThroughAdvertiseDelegate topThroughAdvertiseDelegate;//恒通广告
//    private TopNormalAdvertiseDelegate topNormalAdvertiseDelegate;//普通广告
//    private TopNewsTopicDelegate topNewsTopicDelegate;//专题
//    private TopNewsHotTopicDelegate topNewsHotTopicDelegate;//热门话题
//    private TopNewsSpecialDelegate topNewsSpecialDelegate;//专栏新闻
//    public TopNewsRecommendSpecialDelegate topNewsRecommendSpecial;//推荐three专栏

//    public NewsAdapter(Context context, ChannelType channelType) {
    public NewsAdapter(Context context, String channelType) {
        super();

//        topNormalAdvertiseDelegate = new TopNormalAdvertiseDelegate(context, channelType);
//        topNewsTopicDelegate = new TopNewsTopicDelegate(context, channelType);
//        topNewsHotTopicDelegate = new TopNewsHotTopicDelegate(context, channelType);
        topNewsNormalDelegate = new TopNewsNormalDelegate(context, channelType);
//        pictureDelegate = new GroupPictureDelegate(context, channelType);
//        topNewsSpecialDelegate = new TopNewsSpecialDelegate(context, channelType);
//        topNewsSpecialDelegate.setListener(new TopNewsSpecialDelegate.OnAttentionListener() {
//            @Override
//            public void onAttention(boolean attention) {
//                notifyDataSetChanged();
//            }
//        });
//        topThroughAdvertiseDelegate = new TopThroughAdvertiseDelegate(context, channelType);
//        topNewsRecommendSpecial = new TopNewsRecommendSpecialDelegate(context,channelType);
//        topNewsRecommendSpecial.setListener(new TopNewsRecommendSpecialDelegate.OnAttentionListener() {
//            @Override
//            public void onAttention(boolean attention) {
//                notifyDataSetChanged();
//            }
//        });
//        registerAdapterDelegate(topThroughAdvertiseDelegate);
//        registerAdapterDelegate(topNormalAdvertiseDelegate);
//        registerAdapterDelegate(topNewsTopicDelegate);
//        registerAdapterDelegate(topNewsHotTopicDelegate);
        registerAdapterDelegate(topNewsNormalDelegate);
//        registerAdapterDelegate(pictureDelegate);
//        registerAdapterDelegate(topNewsSpecialDelegate);
//        registerAdapterDelegate(topNewsRecommendSpecial);
//        registerAdapterDelegate(new ExceptionDelegate(context));
    }

//    public void setChannelType(ChannelType type) {
//        topThroughAdvertiseDelegate.setChannelType(type);
//        topNormalAdvertiseDelegate.setChannelType(type);
//        topNewsTopicDelegate.setChannelType(type);
//        topNewsHotTopicDelegate.setChannelType(type);
//        topNewsNormalDelegate.setChannelType(type);
//        pictureDelegate.setChannelType(type);
//        topNewsRecommendSpecial.setChannelType(type);
//    }
    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }
}
