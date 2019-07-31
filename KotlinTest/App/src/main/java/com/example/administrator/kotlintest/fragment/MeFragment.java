package com.example.administrator.kotlintest.fragment;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.banner.mybanner.imageLoader.ImageLoader;
import com.example.administrator.kotlintest.util.SpManager;
import com.example.administrator.kotlintest.widget.MineItemView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Zhangpeiyuan on 2015/12/3 0003.
 */
public class MeFragment extends BaseCaiJingFragment implements View.OnClickListener {
    public static final String GSZB = "GSZB_DAY";
    //股神争霸H5接口
    private static final String TEST_STOCK_MATCH_URL = "https://test-stockmatch.hexun.com/front/index.html?origin=cjapp&utm_source=gs_cjapp&utm_content=cjapp_my";
    private static final String STOCK_MATCH_URL = "https://stockmatch.hexun.com/front/index.html?origin=cjapp&utm_source=gs_cjapp&utm_content=cjapp_my";
    //提现H5接口
    private static final String TEST_CARRY_CASH_URL = "https://tpay-pay.hexun.com/site-wap/wxtx/checkWxtixian.htm?wxType=APPWX";
    private static final String CARRY_CASH_URL = "https://mepay.hexun.com/wxtx/checkWxtixian.htm?wxType=APPWX";
    //
    private static final String GOLD_COIN_URL = "https://m.hexun.com/activity/goldindex.html";
    private static final String TEST_GOLD_COIN_URL = "http://mtest.hexun.com/activity/goldindex.html";
    private static final String CASH_BALANCE_FORMAT = "余额%s元";

    private MineItemView feedbackItem;
    private MineItemView dealItemTwo;
    private MineItemView favoriteItem;
    private MineItemView pushItem;
    private MineItemView viewHistoryItem;
    private MineItemView myCommentItem;
    private RelativeLayout settingItem;
    private RelativeLayout receiveBenefitItem;

    /**
     * 设置
     */
    private TextView userNameText;
    private View linerUnlogin;
    private TextView tvBindPhone;
    private ImageView header;
    private RelativeLayout headerLayout;
    private View partingLine;
    private RelativeLayout stockMatchItem;
    private View stockMatchRedPoint;

    //提现和金币
    private View itemCarryCash;
    private View itemGoldCoin;
    private View itemCarrYCashLayout;
    private TextView tvCashCount;
    private TextView tvGoldCoinCount;
    private TextView tvBenefitTips;
    private String cashCount;
    private ProgressDialog progressDialog;

    private View signInLayout;
    private TextView signIn;
    private ImageView signInIV;
    private AnimationDrawable signInAnim;
    private static boolean showGSZBDay;
    private static int signInGoldCount = 50;

    static {
        SpManager.getPreferences().getLongObservable(MeFragment.GSZB, 0L)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long day) throws Exception {
//                        showGSZBDay = day != NewsletterHelper.getDay();
                    }
                });
    }

    @Override
    public View getContentView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.fragment_me, null, false);
        return view;
    }


    @Override
    public void initData(Bundle savedInstanceState) {

        //注册EventBus
    }


    private void initListener() {
        feedbackItem.setOnClickListener(this);
        headerLayout.setOnClickListener(this);
        dealItemTwo.setOnClickListener(this);
        favoriteItem.setOnClickListener(this);
        viewHistoryItem.setOnClickListener(this);
        myCommentItem.setOnClickListener(this);
        settingItem.setOnClickListener(this);
        pushItem.setOnClickListener(this);
        tvBindPhone.setOnClickListener(this);
        stockMatchItem.setOnClickListener(this);
        itemCarryCash.setOnClickListener(this);
        itemGoldCoin.setOnClickListener(this);
        receiveBenefitItem.setOnClickListener(this);
        signInLayout.setOnClickListener(this);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        feedbackItem = getViewById(R.id.item_feedback);
        dealItemTwo = getViewById(R.id.item_deal_two);
        stockMatchItem = getViewById(R.id.item_stock_match);
        partingLine = getViewById(R.id.parting_line);
        pushItem = getViewById(R.id.item_push_history);
        favoriteItem = getViewById(R.id.item_favorite);
        viewHistoryItem = getViewById(R.id.item_view_history);
        myCommentItem = getViewById(R.id.item_my_comment);
        settingItem = getViewById(R.id.item_setting);
        stockMatchRedPoint = getViewById(R.id.iv_stock_match_flag);
        headerLayout = getViewById(R.id.img_header_layout);
        header = getViewById(R.id.img_header);
        tvBindPhone = getViewById(R.id.bind_phone);
        userNameText = getViewById(R.id.tv_user_name);
        linerUnlogin = getViewById(R.id.liner_unlogin);
        tvBenefitTips = getViewById(R.id.tv_receive_benefit);

        itemCarryCash = getViewById(R.id.item_carry_cash);
        itemGoldCoin = getViewById(R.id.item_gold_coin);
        itemCarrYCashLayout = getViewById(R.id.item_carry_cash_layout);
        tvCashCount = getViewById(R.id.tv_cash_count);
        tvGoldCoinCount = getViewById(R.id.tv_gold_coin_count);
        receiveBenefitItem = getViewById(R.id.item_receive_benefit);

        signInLayout = getViewById(R.id.sign_in_layout);
        signIn = getViewById(R.id.sign_in);
        signInIV = getViewById(R.id.sign_in_iv);

    }



    @Override
    public void onClick(View v) {

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 更新头像的逻辑， 通过时间判断
     */
    private long updatePhotoTime = 0;

    private void updateTime() {
        updatePhotoTime = SystemClock.elapsedRealtime();
    }

    private static final int UPDATE_PHOTO_TIME_NUM = 180000;





}
