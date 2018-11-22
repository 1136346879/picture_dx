package com.xfs.qrcode_module.ui.adapter;


/**
 * @author yangyi 2018年8月7日09:56:44
 */

public class GrantedAdapter {

//    private Context context;
//    private List<QRCodeRecommendContent.NewsBean> contentList;
//
//    public GrantedAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setQRCodeRecommendContent(List<QRCodeRecommendContent.NewsBean> contentList) {
//        this.contentList = contentList;
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public GrantedViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        return new GrantedViewHolder(LayoutInflater.from(context)
//                .inflate(R.layout.item_granted,
//                        viewGroup,
//                        false));
//    }
//
//    @Override
//    public void onBindViewHolder(GrantedViewHolder grantedViewHolder, int i) {
//        grantedViewHolder.setItemContent(contentList.get(i));
//    }
//
//    @Override
//    public int getItemCount() {
//        return contentList == null ? 0 : contentList.size();
//    }
//
//    class GrantedViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView nameText;
//        private TextView timeText;
//        private QRCodeRecommendContent.NewsBean newsBean;
//
//        GrantedViewHolder(View itemView) {
//            super(itemView);
//
//            nameText = itemView.findViewById(R.id.nameText);
//            timeText = itemView.findViewById(R.id.timeText);
//            RxView.clicks(itemView)
//                    .throttleFirst(300, TimeUnit.MILLISECONDS)
//                    .subscribe(new Consumer<Object>() {
//                        @Override
//                        public void accept(Object o) throws Exception {
//                            Intent intent = ActivityRouter.getNewsDetail();
//                            intent.putExtra("newsId", String.valueOf(newsBean.getId()));
//                            context.startActivity(intent);
//                        }
//                    });
//        }
//
//        void setItemContent(QRCodeRecommendContent.NewsBean newsBean) {
//            nameText.setText(newsBean.getTitle());
//            timeText.setText(TimeUtil.millis2String(TimeUtil.string2Millis(newsBean.getTime()),
//                    new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())));
//            this.newsBean = newsBean;
//        }
//    }
}
