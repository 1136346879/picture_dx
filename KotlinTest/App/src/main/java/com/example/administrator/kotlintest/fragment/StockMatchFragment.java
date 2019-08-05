package com.example.administrator.kotlintest.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.widget.ExpandWebView;
import com.hexun.training.widget.LoadingView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * @Time 2018/8/7 13:09
 * @描述 股神争霸fragment
 */

public class StockMatchFragment extends BaseCaiJingFragment implements View.OnClickListener {

    //股神争霸H5接口
    private static final String TEST_STOCK_MATCH_URL = "https://test-stockmatch.hexun.com/front/index.html?origin=cjapp&utm_source=gs_cjapp&utm_content=cjapp_stock";
    private static final String STOCK_MATCH_URL = "https://stockmatch.hexun.com/front/index.html?origin=cjapp&utm_source=gs_cjapp&utm_content=cjapp_stock";
    private static JavaScriptObject JS_Object;
    /**
     * 封装后的
     */
    private ExpandWebView webView;
    /**
     * 超时错误View
     */
    private LinearLayout timeOutError;
    /**
     * 页面loading progress View
     */
    private ProgressBar webProgressBar;
    /**
     * 页面title View
     */
    private TextView topTitle;
    /**
     * 加载地址
     */
    private String toUrl;
    /**
     * 页面title文字
     */
    private String title;
    /**
     * 是否是根url page
     */
    private boolean isRootPage;
    /**
     * 是否显示关闭页面按钮
     */
    private boolean showClose;
    /**
     * js脚本对象
     */
    private JavaScriptObject jsObject;
    /**
     * webview setting
     */
    private Setting setting;
    /**
     * 回退按钮View
     */
    private View back;
    private LoadingView loadingView;
    /**
     * 分享相关内容
     */
    private String shareTitle, shareContent, shareUrl;
    /**
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v == back) {
            if (!isRootPage && webView != null && webView.canGoBack()) {
                WebBackForwardList webBackForwardList = webView.copyBackForwardList();
                if (webBackForwardList != null && webBackForwardList.getSize() > 0) {
                    jsObject.onPageBack(setting, webView.getUrl(), webBackForwardList.getItemAtIndex(webBackForwardList.getSize() - 1).getUrl());
                }
                webView.goBack();
            }
        } else if (v == timeOutError) {
            webView.reload();
        }
    }

    @Override
    protected View getContentView(LayoutInflater layoutInflater) {
         view = layoutInflater.inflate(R.layout.activity_cd_base_web, null);
        return view;
    }

    @Override
    protected void initView(View view, Bundle bundle) {
        topTitle = getViewById(R.id.top_title);
        topTitle.setText("");
        back = getViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        webProgressBar = getViewById(R.id.webProgressBar);
        timeOutError = getViewById(R.id.llt_error);
        timeOutError.setOnClickListener(this);

        webView = getViewById(R.id.my_webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setSaveFormData(false);
        webView.getSettings().setSavePassword(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(webView.getSettings().MIXED_CONTENT_ALWAYS_ALLOW);
        }
//        webView.getSettings().setUserAgentString(webView.getSettings().getUserAgentString() + HeContext.getInstance().getAppUA());


        webView.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //这event.getAction() == KeyEvent.ACTION_DOWN表示是返回键事件
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {  //表示按返回键 时的操作
                        webView.goBack();   //后退
                        return true;    //已处理     返回true表示被处理否则返回false
                    }
                }
                return false;
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            private String errorUrl;

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (jsObject != null) {
                    return jsObject.shouldOverrideUrlLoading(view, url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//接收证书
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (getActivity().isFinishing()) {
                    return;
                }
                errorUrl = null;
                webProgressBar.setVisibility(View.VISIBLE);
                isRootPage = toUrl.startsWith(url);
                if (isRootPage) {
                    back.setVisibility(View.GONE);
                } else {
                    back.setVisibility(View.VISIBLE);
                }
                if (jsObject != null) {
                    jsObject.onPageStart(setting,webView.getUrl(),url);
                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (url != null && !url.equals(errorUrl)) {
                    //android4.4.4加载错误界面data:text/html,chromewebdata
                    if (url.startsWith("http") || url.startsWith("https")) {
                        timeOutError.setVisibility(View.GONE);
                        webView.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                errorUrl = failingUrl;
                super.onReceivedError(view, errorCode, description, failingUrl);
                timeOutError.setVisibility(View.VISIBLE);
                webView.setVisibility(View.INVISIBLE);
            }


        });
        //设置webChromeClient,注意回调时页面状态的判断
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                if (getActivity().isFinishing()) {//页面异常，退出不处理
                    result.cancel();
                } else {
//                    HeAlertDialog.newInstance(getActivity(), message, null, result).setOnAlterDialogBtnListener(new HeAlertDialog.AlterDialogBtnListener() {
//                        @Override
//                        public void onDialogPositiveClick(HeAlertDialog dialog) {
//                            if (dialog != null) {
//                                dialog.dismiss();
//                            }
//                        }
//
//                        @Override
//                        public void onDialogNegativeClick(HeAlertDialog dialog) {
////
//                        }
//                    }).show();
                }
                return true;
            }

            //页面加载进度展示
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

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if ((StockMatchFragment.this.title == null || StockMatchFragment.this.title.equals("") )
                        && topTitle != null) {//设置页面title，这里判断是否已经通过跳转时传递过来此参数。
                    topTitle.setText(title);
                }
            }
        });
        isRootPage = true;
        loadingView = (LoadingView) view.findViewById(R.id.loading_view);
        loadingView.dismiss();
        loadingView.setBackgroundColor(0x80000000);
    }

    @Override
    protected void initData(Bundle bundle) {
        topTitle.setText("股神争霸");
        setting = new Setting(getActivity());
        toUrl = true ? TEST_STOCK_MATCH_URL : STOCK_MATCH_URL;
        if (JS_Object != null && JS_Object.getOriginalUrl().equals(toUrl)) {
            jsObject = JS_Object;
        } else {
            jsObject = createDefaultJS();
        }
        JS_Object = null;
        jsObject.setUrl(toUrl);
        jsObject.setContext(this);
        jsObject.setWebView(webView);
        webView.addJavascriptInterface(jsObject, "javatojs");
        webView.loadUrl(toUrl);
    }

    /**
     * 创建默认的JSObject
     *
     * @return
     */
    protected JavaScriptObject createDefaultJS() {
        return new JavaScriptObject();
    }

    @JavascriptInterface
    public void bindMobileFinished() {
        getActivity().onBackPressed();
    }

    @JavascriptInterface
    public void login() {
//        Intent intent = new Intent(getActivity(), LocalLoginActivity.class);
//        startActivity(intent);
    }


    public static class Setting {

        private Context activity;

        private Setting(Context activity) {
            this.activity = activity;
        }
    }

    public static class JavaScriptObject {

        private String url;
        private StockMatchFragment activity;
        private boolean onlyLogin;
        private WebView webView;
        private static int loginID;
        private boolean onlyChannelLogin;

        public JavaScriptObject() {

        }

        public JavaScriptObject(boolean onlyLogin) {
            this.onlyLogin = onlyLogin;
        }

        public boolean isOnlyLogin() {
            return onlyLogin;
        }

        final void setContext(StockMatchFragment context) {
            this.activity =  context;
        }

        final void setWebView(WebView webView) {
            this.webView = webView;
        }

        public void destroy() {
            url = null;
            activity = null;
        }

        final String getOriginalUrl() {
            return url;
        }

        final void setUrl(String url) {
            this.url = url;
        }

        @JavascriptInterface
        protected void webLoginSuccess() {
           //
        }

        /**
         * H5调用源生登录
         */

        public void onJSLoaded(StockMatchFragment.Setting setting, String url) {
//
        }

        /**
         * 找回密码成功后，用来返回原生登录页面
         */
        @JavascriptInterface
        public void goToLocalLogin() {
            //
        }


        /**
         * 三方登录js接口
         */

        public int loginID() {
            return hashCode();
        }

        /**
         * 获取微信授权
         */

        /**
         * 关闭当前页面切换导航.
         *
         * @param position
         */
        @JavascriptInterface
        public void swithTab(int position) {
            //
        }
        @JavascriptInterface
        public void shareToPlatformWith(String title, String content, String url) {
            activity.showShare(title, content, url, false);
        }
        @JavascriptInterface
        public void shareToPlatform(String title, String content, String url) {
            activity.showShare(title, content, url, true);
        }
        /**
         * 重写此方法可以拦击点击分享事件，也可以自定义分享面板，否则就会使用通用分享面板
         *
         * @param shareTitle
         * @param shareContent
         * @param shareUrl
         */

        /**
         * 获取当前平台渠道
         * @return
         */



        /**
         * 获取设备唯一标识
         *
         * @return
         */

        /**
         * 当webview返回时调用
         *
         * @param setting    配置对象
         * @param currentUrl 当前页面的地址
         * @param lastUrl    即将返回页面的地址
         */
        public void onPageBack(StockMatchFragment.Setting setting, String currentUrl, String lastUrl) {
//
        }

        /**
         * 开始加载一个页面
         *
         * @param setting    配置对象
         * @param currentUrl 当前的页面地址，如果和nextUrl相同表示是第一个页面
         * @param nextUrl    开始加载页面的地址
         */
        public void onPageStart(StockMatchFragment.Setting setting, String currentUrl, String nextUrl) {
//
        }


        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

    }
    private void showShare(final String title, final String content, final String url, final boolean toShare) {
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content) && !TextUtils.isEmpty(url)) {
            if (toShare) {
                Observable.just(1).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {//
                            }

                            @Override
                            public void onNext(Integer integer) {
                                if ( jsObject == null) {
                                    return;
                                }
                            }

                            @Override
                            public void onError(Throwable e) {//
                            }

                            @Override
                            public void onComplete() {//
                            }
                        });
            } else {
                shareTitle = title;
                shareContent = content;
                shareUrl = url;
                Observable.just(1).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {//
                            }

                            @Override
                            public void onNext(Integer integer) {
                                if (getActivity().isFinishing() ) {
                                    return;
                                }
                            }

                            @Override
                            public void onError(Throwable e) {//
                            }

                            @Override
                            public void onComplete() {//
                            }
                        });
            }
        }
    }

    /**
     * 退出登录后刷新数据
     *
     * @param event 事件
     */
}
