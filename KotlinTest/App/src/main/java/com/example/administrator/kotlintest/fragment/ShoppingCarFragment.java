package com.example.administrator.kotlintest.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.dx.banner.newbaselibrary.routerapi.RouterApi;
import com.example.administrator.kotlintest.HomeActiivty;
import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.bus.RxBus;
import com.example.administrator.kotlintest.common.ActiveResultDef;
import com.example.administrator.kotlintest.common.IntentDataDef;
import com.example.baselibrary.common.ToastUtil;
import com.example.baselibrary.widgets.TLog;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.xfs.fsyuncai.entity.CitySelectEntityRxBus;
import com.xfs.fsyuncai.entity.accont.AccessManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.example.administrator.kotlintest.common.IntentDataDef.DETAIL_SPU_ID_KEY;
import static com.example.administrator.kotlintest.common.IntentDataDef.NO_RELOAD_WEBVIEW;


@Route(path = "/test/fragment")
public class ShoppingCarFragment extends BaseTabFragment implements View.OnClickListener {

    private WebView webView;
    private ProgressBar webProgressBar;
    private String toUrl;
    private String denTif;
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);
    private String activityId;
    private String activityTitle;
    private int height;
    private RelativeLayout inNoNetWork;
    private TextView tvReload;

    @Override
    public int layoutResId() {
        return R.layout.fragment_shopping_car;
    }

    @Override
    public void init() {
        if (getActivity() != null) {
            Intent intent = getActivity().getIntent();
            atomicBoolean.set(intent.getBooleanExtra(NO_RELOAD_WEBVIEW, false));
            initView();
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "CheckResult"})
    private void initView() {

        webProgressBar = getViewById(R.id.webProgressBar);
        inNoNetWork = getViewById(R.id.inNoNetWork);
        tvReload = inNoNetWork.findViewById(R.id.tvReload);
        tvReload.setOnClickListener(this);

        webView = getViewById(R.id.my_webview);
        //noinspection deprecation
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);//解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(android.webkit.WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
        webView.setWebViewClient(new WebViewClient() {

            private String errorUrl;

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                errorUrl = null;
                webProgressBar.setVisibility(View.VISIBLE);
//                if (getActivity() != null) {
//                    LoadingDialog.INSTANCE.show(getActivity(), "");
//                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url != null && !url.equals(errorUrl)) {
                    //android4.4.4加载错误界面data:text/html,chromeWebData
                    if (url.startsWith("http") || url.startsWith("https")) {
                        inNoNetWork.setVisibility(View.GONE);
                        webView.setVisibility(View.VISIBLE);
                    }
                }
                //LoadingDialog.INSTANCE.dissmiss();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                errorUrl = failingUrl;
                super.onReceivedError(view, errorCode, description, failingUrl);
                inNoNetWork.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);
                //LoadingDialog.INSTANCE.dissmiss();
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    webProgressBar.setVisibility(View.GONE);
                } else {
                    if (View.GONE == webProgressBar.getVisibility() && newProgress > 0) {
                        webProgressBar.setVisibility(View.VISIBLE);
                    }
                    webProgressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        webView.addJavascriptInterface(this, getString(R.string.webview_js_javainterface));
        initData(0);
        //noinspection ResultOfMethodCallIgnored
        RxBus.get().toFlowable(CitySelectEntityRxBus.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    atomicBoolean.set(true);
                    initData(0);
                });
    }

    /**
     * 采购单
     */
    protected void initData(int height) {
        try {
            if (getActivity() == null) return;
            String activityName = getActivity().getClass().getSimpleName();
            if (activityName.equals("MainActivity")) {
                //隐藏返回键
                denTif = "1";
            } else if (activityName.equals("ShoppingCartActivity")) {
                //显示返回键
                denTif = "2";
            }
            toUrl = "https://t3.fsyuncai.com/h5/orderlist/purchaseOrder/purchaseOrder.html?catSort=0&cityId=1&currentPage=1&customerId=CN00000052&memberId=1505&timeSort=1&warehouseId=1&platformType=1&token=11026e355a48149a8ff8d3cc2bbb3dc1&denTif=1&login_account=hetong004&citytexter=北京&height=0";
            toUrl = "https://www.baidu.com/";
            TLog.INSTANCE.i("weburl === " + toUrl);
        } catch (Exception e) {
            //
        }
        if (webView != null && toUrl != null && !TextUtils.isEmpty(toUrl)) {
            webView.loadUrl(toUrl);
        }
    }

    @Override
    public void onDestroyView() {
        if (webView != null) {
            webView.setWebViewClient(null);
            webView.setWebChromeClient(null);
            webView.removeJavascriptInterface(getString(R.string.webview_js_javainterface));
            webView.destroy();
            webView = null;
        }
        super.onDestroyView();
    }

    @Override
    public void onClick(View v) {
        if (v == tvReload) {
            webView.reload();
        }
    }

    @Override
    public void logic() {
        //
    }

    public static ShoppingCarFragment newInstance() {
        return new ShoppingCarFragment();
    }

    /**
     * 返回
     */
    @JavascriptInterface
    public void goBack() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    @Override
    public void lazyLoad() {
        super.lazyLoad();
        initData(0);
    }

    /**
     * 返回
     */
    @JavascriptInterface
    public void goNext() {
        ToastUtil.INSTANCE.showCustomToast("返回");
    }


    /**
     * 返回首页
     */
    @JavascriptInterface
    public void indexList() {
        Intent intent = new Intent(getActivity(), HomeActiivty.class);
        intent.putExtra("select", 0);
        startActivity(intent);
    }

    @JavascriptInterface
    public void comment(String ids) {

        ToastUtil.INSTANCE.showCustomToast("评论");
    }

    @JavascriptInterface
    public void Collectbills(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            if (jsonObject.has("activityid")) {
                activityId = jsonObject.getString("activityid");
            }
            if (jsonObject.has("activityTitle")) {
                activityTitle = jsonObject.getString("activityTitle");
            }
            if (jsonObject.has("height")) {
                height = jsonObject.getInt("height");
            }
            ARouter.getInstance()
                    .build(RouterApi.GoodsCenter.ROUTER_GOODS_PROMOTION)
                    .withInt(IntentDataDef.SEARCH_ACTIVE_ID, Integer.valueOf(activityId))
                    .withString(IntentDataDef.SEARCH_ACTIVE_NAME, activityTitle)
                    .navigation();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回值 支付方式
     */
    @JavascriptInterface
    public void goPay(String code) {
        ToastUtil.INSTANCE.showCustomToast("支付方式");
    }

    /**
     * 商品详情页
     */
    @JavascriptInterface
    public void goodsDetaile(String spuid) {
        if (spuid == null) {
            return;
        }
        ARouter.getInstance().build(RouterApi.GoodsCenter.ROUTER_GOODS_DETAIL)
                .withInt(DETAIL_SPU_ID_KEY, Integer.valueOf(spuid))
                .navigation();
    }

    /**
     * 采购单切换地址
     */
    @JavascriptInterface
    public void region() {
        if (AccessManager.instance().isHasshopCustomerAddress()) {
            ToastUtil.INSTANCE.showCustomToast(getString(R.string.tip_hasShopCustomer_address));
            return;
        }
        atomicBoolean.set(true);
        ARouter.getInstance()
                .build(RouterApi.MainLibrary.ROUTER_MAIN_SELECT_CITY)
                .withString(IntentDataDef.LOCATION_KEY, "")
                .navigation(getActivity(), ActiveResultDef.ACTIVITY_RESULT_LOCATION);
    }

    //采购单结算  结算之前先核查商品是否有误，否则提示用户
    @JavascriptInterface
    public void confirmdata(String data) {

        if ("{}".equals(data)) {
            ToastUtil.INSTANCE.showCustomToast(getString(R.string.place_choices_goods));
            return;
        }
        ToastUtil.INSTANCE.showCustomToast("结算喽");
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!atomicBoolean.get() && height == 0) {
            webView.reload();
        } else {
            //进入采购单的去凑单 回到采购单页面需要记住当前位置
            initData(height);
            height = 0;
        }
        atomicBoolean.set(false);
    }
}
