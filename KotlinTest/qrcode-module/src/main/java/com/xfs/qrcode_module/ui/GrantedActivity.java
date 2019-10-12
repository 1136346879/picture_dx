package com.xfs.qrcode_module.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xfs.qrcode_module.R;
import com.xfs.qrcode_module.event.ScanResultEvent;
import com.xfs.qrcode_module.manager.ScanManager;
import com.xfs.qrcode_module.util.NetworkUtil;


/**
 * 扫一扫授权页面
 *
 * @author yangyi 2018年8月6日17:53:33
 */

public class GrantedActivity extends Activity {

    private static final long WINDOW_DURATION = 300;
    private static final String GRANTED_RESULT = "granted_result";
    private static final int PARAMS_LENGTH = 4;
//    private XRecyclerView grandList;
//    private GrantedAdapter grantedAdapter;
    private TextView readNowText;
    private TextView grandComputerText;
    private TextView reScanText;
    private TextView headPointText;
    private TextView grantHeadContentText;

    /**
     * /**
     * //频道名
     * params.get(0);
     * //时间
     * params.get(1);
     * //新闻id
     * params.get(2);
     * //key
     * params.get(3);
     */
    private String[] params;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_granted);
        initData();
    }

    /**
     * 设置虚化背景
     */
//    public void onEventMainThread(ScanResultEvent scanResultEvent) {
//        Bitmap blurBitmap = scanResultEvent.getBackgroundBitmap();
//        setBlurBackground(blurBitmap);
//    }

//    private void setBlurBackground(final Bitmap blurBitmap) {
//        Observable.create(new ObservableOnSubscribe<Drawable>() {
//            @Override
//            public void subscribe(ObservableEmitter<Drawable> e) throws Exception {
//                e.onNext(ImageUtil.bitmap2Drawable(blurBitmap));
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Drawable>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        addDisposable(d);
//                    }
//
//                    @Override
//                    public void onNext(Drawable drawable) {
//                        if (drawable == null) {
//                            return;
//                        }
//                        grandList.setBackground(drawable);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    /**
     * 授权电脑阅读
     */
//    private void checkAppRead() {
//        //参数不是约定的4个或者压根没有参数，直接return
//        if (params == null || params.length != PARAMS_LENGTH) {
//            return;
//        }
//        QRCodeApi.checkAppQRCodeKey(params[3])
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Response<String>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        addDisposable(d);
//                    }
//
//                    @Override
//                    public void onNext(Response<String> stringResponse) {
//                        if (stringResponse == null) {
//                            return;
//                        }
//                        if (stringResponse.body() == null) {
//                            return;
//                        }
//                        CheckKeyResponse checkKeyResponse = new Gson()
//                                .fromJson(stringResponse.body(),
//                                        new TypeToken<CheckKeyResponse>() {
//                                        }.getType());
//                        if (checkKeyResponse.getStatus() == 1) {
//                            Toast.makeText(GrantedActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
//                            if (!GrantedActivity.this.isFinishing()) {
//                                finish();
//                            }
//
//                            RecorderManager.getInstance().recorderCheckAppReadClick(GrantedActivity.this);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    /**
     * 立即阅读
     */
//    private void readNow() {
//        Intent intent = ActivityRouter.getNewsDetail();
//        intent.putExtra("newsId", params[2]);
//        startActivity(intent);
//        finish();
//        RecorderManager.getInstance().recorderReadNowClick(this);
//    }

//    @Override
//    protected void initView() {
//        QRCodeTopBar grandTopBar = (QRCodeTopBar) findViewById(R.id.grandTopBar);
//        grandTopBar.setTopName("阅读授权");
//        grandTopBar.setQrCodeTopBarClickListener(new QRCodeTopBar.QRCodeTopBarClickListener() {
//            @Override
//            public void startClick() {
//                if (!isFinishing()) {
//                    finish();
//                }
//            }
//
//            @Override
//            public void endClick() {
//
//            }
//        });
//        grandList = (XRecyclerView) findViewById(R.id.grandList);
//        grandList.setLayoutManager(new LinearLayoutManager(this,
//                LinearLayoutManager.VERTICAL, false));
//        grandList.setLoadingMoreEnabled(false);
//        grandList.setPullRefreshEnabled(false);
//        grantedAdapter = new GrantedAdapter(this);
//        grandList.setAdapter(grantedAdapter);
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
//                this, LinearLayoutManager.VERTICAL);
//        dividerItemDecoration.setDrawable(
//                ContextCompat.getDrawable(this, R.drawable.item_granted_divider));
//        grandList.addItemDecoration(dividerItemDecoration);
//        initHeadView();
//    }

//    private void initHeadView() {
//        View headView = LayoutInflater.from(this).inflate(R.layout.head_granted,
//                grandList,
//                false);
//        grandList.addFirstHeadView(headView);
//        readNowText = headView.findViewById(R.id.readNowText);
//        grandComputerText = headView.findViewById(R.id.grandComputerText);
//
//        //有网时按钮能点击，没网时则不能点
//        if (NetworkUtil.isNetworkAvailable(this)) {
//            RxView.clicks(readNowText)
//                    .throttleFirst(WINDOW_DURATION, TimeUnit.MILLISECONDS)
//                    .subscribe(new Consumer<Object>() {
//                        @Override
//                        public void accept(Object o) throws Exception {
//                            readNow();
//                        }
//                    });
//            RxView.clicks(grandComputerText)
//                    .throttleFirst(WINDOW_DURATION, TimeUnit.MILLISECONDS)
//                    .subscribe(new Consumer<Object>() {
//                        @Override
//                        public void accept(Object o) throws Exception {
//                            checkAppRead();
//                        }
//                    });
//        } else {
//            readNowText.setOnClickListener(null);
//            grandComputerText.setOnClickListener(null);
//        }
//
//        reScanText = headView.findViewById(R.id.reScanText);
//        RxView.clicks(reScanText)
//                .throttleFirst(WINDOW_DURATION, TimeUnit.MILLISECONDS)
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        ScanManager.getInstance().openScan(GrantedActivity.this);
//                        RecorderManager.getInstance().recorderReScanClick(GrantedActivity.this);
//                    }
//                });
//        headPointText = headView.findViewById(R.id.headPointText);
//        grantHeadContentText = headView.findViewById(R.id.grantHeadContentText);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ScanManager.getInstance().onActivityResult(this,
                requestCode,
                resultCode,
                data);
    }

    protected void initData() {
        ScanResultEvent scanResultEvent = (ScanResultEvent) getIntent().getSerializableExtra(GRANTED_RESULT);
        params = scanResultEvent.getParams();
        //扫描成功UI
        if (scanResultEvent.getStatus() == 1) {
            reScanText.setVisibility(View.GONE);
            readNowText.setVisibility(View.VISIBLE);
            grandComputerText.setVisibility(View.VISIBLE);
            headPointText.setText("相关新闻");
        }
        //扫描失败UI
        if (scanResultEvent.getStatus() == 0) {
            reScanText.setVisibility(View.VISIBLE);
            readNowText.setVisibility(View.GONE);
            grandComputerText.setVisibility(View.GONE);
            headPointText.setText("大家都在看");
            grantHeadContentText.setText("大人，您扫码的二维码不是由和讯网生成，小的知识有限无法识别~~");
        }
        //没网时的UI
        if (!NetworkUtil.isNetworkAvailable(this)) {
            grantHeadContentText.setText("扫码不成功，请重新再试~");
        }
        if (params != null) {
            //参数不是约定的4个，不显示授权按钮
            if (params.length != PARAMS_LENGTH) {
                grandComputerText.setVisibility(View.GONE);
            }
        }
        int resultStatus = scanResultEvent.getStatus();
//        QRCodeApi.getQRCodeRecommendContent(resultStatus == 1 && params != null ? params[2] : "",
//                String.valueOf(resultStatus))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new NetSubscriber<QRCodeRecommendContent>() {
//                    @Override
//                    public void onStart(Disposable d) {
//                        addDisposable(d);
//                    }
//
//                    @Override
//                    public void onData(QRCodeRecommendContent qrCodeRecommendContent, boolean isFromCache) {
//                        if (qrCodeRecommendContent == null) {
//                            return;
//                        }
//                        if (qrCodeRecommendContent.getNews() == null || qrCodeRecommendContent.getNews().isEmpty()) {
//                            return;
//                        }
//                        grantedAdapter.setQRCodeRecommendContent(qrCodeRecommendContent.getNews());
//                        if (qrCodeRecommendContent.getTitle() != null) {
//                            grantHeadContentText.setText("《" + qrCodeRecommendContent.getTitle()
//                                    + "》文章您尚未阅读完全文，请选择以下方式继续浏览");
//                        }
//                    }
//
//                    @Override
//                    public void onError(int code, Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onDone() {
//
//                    }
//                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        setIntent(intent);
//        initData();
    }

    public static void gotoGrantedActivity(Context context, ScanResultEvent scanResultEvent) {
        Toast.makeText(context,"跳转页面",1).show();
        return;
//        Intent intent = new Intent(context, GrantedActivity.class);
//        intent.putExtra(GRANTED_RESULT, scanResultEvent);
//        context.startActivity(intent);
    }
}