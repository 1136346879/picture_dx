package com.xfs.qrcode_module.recycleview;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.baselibrary.common.ToastUtil;
import com.example.baselibrary.ui.fragment.BaseLibraryFragment;
import com.xfs.qrcode_module.R;
import com.xfs.qrcode_module.recycleview.exception.ErrorHandler;
import com.xfs.qrcode_module.recycleview.rx.ApiResult;
import com.xfs.qrcode_module.recycleview.rx.NetSubscriber;
import com.xfs.qrcode_module.recycleview.rx.ResourceHelper;
import com.xfs.qrcode_module.recycleview.rx.RxComposer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecycleViewFragment extends BaseLibraryFragment implements XRecyclerView.LoadingListener{

    private LoadingRecycleViewView loadingView;
    private NewsAdapter newsAdapter;
    private XRecyclerView recyclerView;
    private TextView footerText;
    /**
     * 页码 & 每页条目
     */
    private int pageNum = 0;
    private int pageSize = 20;

    @Override
    public int layoutResId() {
        return R.layout.recycleview_fragment;
    }

    @Override
    public void init() {
        recyclerView = getViewById(R.id.xRecycleview);
        loadingView = getViewById(R.id.loading_view);
//        newsAdapter = new NewsAdapter(getContext(),"");
        recyclerView.setLayoutManager(new LinearLayoutManager(activity(), LinearLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(newsAdapter);
        LoadingMoreFooter footer = new LoadingMoreFooter(activity());
        footer.setPadding(0, 30, 0, 30);
        footerText = (TextView) footer.getChildAt(1);
        footerText.setText("查看更多");
        recyclerView.setFootView(footer);
        recyclerView.setLoadingMoreEnabled(true);
        recyclerView.setPullRefreshEnabled(true);
        recyclerView.setLoadingListener(this);
        loadingView.setOnLoadingViewClickListener(new LoadingRecycleViewView.OnLoadingViewClickListener() {
            @Override
            public void onLoadingViewClick(int type) {
               ToastUtil.INSTANCE.showCustomToast("点击重新加载");
            }
        });
    }

    @Override
    public void logic() {
        if (newsAdapter == null) {
            loadingView.showProgress();
            newsAdapter = new NewsAdapter(getContext(),"");
            recyclerView.setAdapter(newsAdapter);
            loadingView.showProgress();
            loadData(false);
        } else {
            recyclerView.setAdapter(newsAdapter);
//            initBanner(null);
            if (loadingView.isLoading()) {
                loadingView.setVisibility(View.GONE);
            }
        }

    }
    private DataSubscriber dataSubscriber;
    /**
     * 加载数据
     *
     * @param isLoadMore
     */
    protected void loadData(boolean isLoadMore) {

        if (dataSubscriber == null){
            dataSubscriber = new DataSubscriber();
        }
        dataSubscriber.setLoadMore(isLoadMore);

        NewsApi.getNewsData(pageSize, pageNum)
                .compose(RxComposer.<ApiResult<NewsData>>ioToMain())
                .subscribe(dataSubscriber);
    }
    public RxAppCompatActivity activity() {
        return (RxAppCompatActivity) getActivity();
    }

    public static RecycleViewFragment newInstance(){
        RecycleViewFragment recycleViewFragment = new RecycleViewFragment();
        Bundle bundle = new Bundle();
        recycleViewFragment.setArguments(bundle);
         return recycleViewFragment;
    }


    @Override
    public void onRefresh() {

        pageNum = 0;
        loadData(false);
        ToastUtil.INSTANCE.showCustomToast("下拉刷新");
    }

    @Override
    public void onLoadMore() {
        footerText.setVisibility(View.VISIBLE);
        ToastUtil.INSTANCE.showCustomToast("上拉加载更多");
        loadData(true);

    }
    /**
     * 头条数据订阅者
     */
    public class DataSubscriber extends NetSubscriber<NewsData> {

        private boolean isLoadMore;

        @Override
        public void onStart(Disposable d) {
        }

        @Override
        public void onData(NewsData newsData, boolean isFromCache) {
            if (!isAdded()) {
                return;
            }
            if (isLoadMore) {
                recyclerView.loadMoreComplete();
            } else {
                recyclerView.refreshComplete();
            }
            if (newsData == null || newsData.getNews() == null) {
                if (newsAdapter.getItems()!=null && newsAdapter.getItems().size()>0){
                    recyclerView.setNoMore(true);
                    footerText.setText(ResourceHelper.getInstance().getString(R.string.list_no_more));
                }
                return;
            }
            if (newsData.getFocus() != null && newsData.getFocus().getFrame() != null) {
                List<NewsData.Frame> frames = newsData.getFocus().getFrame();
//                initBanner(frames);
            }
            List<NewsInfo> newsInfos = newsData.getNews();
            if (newsInfos == null){
                return;
            }
            if (newsAdapter.getItems() == null) {
                newsAdapter.setItems(newsInfos);
            } else if (!isLoadMore) {
                newsAdapter.getItems().clear();
                newsAdapter.getItems().addAll(newsInfos);
            } else {
                newsAdapter.getItems().addAll(newsInfos);
            }
            if (newsAdapter.getItemCount() == 0) {
                loadingView.showEmptyView();
            }
            if (newsInfos.size() == 0){
                recyclerView.setNoMore(true);
            }
            newsAdapter.notifyDataSetChanged();
            pageNum++;
        }

        @Override
        public void onError(int code, Throwable e) {
            if (!isAdded()) {
                return;
            }
            if (newsAdapter.getItemCount() == 0) {
                loadingView.showErrorView();
            }
            if (isLoadMore) {
                recyclerView.loadMoreComplete();
            } else {
                recyclerView.refreshComplete();
            }
            if (newsAdapter.getItemCount() != 0 && code == ErrorHandler.NULL_DATA){
                recyclerView.setNoMore(true);
            }

        }

        @Override
        public void onDone() {
            if (loadingView.isLoading()) {
                loadingView.setVisibility(View.GONE);
            }
        }

        public void setLoadMore(boolean isLoadMore){
            this.isLoadMore = isLoadMore;
        }
    }

}
