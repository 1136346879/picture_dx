package com.example.administrator.kotlintest.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.util.SPUtil;
import com.example.administrator.kotlintest.util.SpManager;
import com.example.administrator.kotlintest.widget.SlidingTabLayout;
import com.example.baselibrary.common.ToastUtil;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author wdx
 * @Time ${20171027} 15:12
 * @描述 新闻资讯模块
 */

public class NewsFragment extends BaseCaiJingFragment {

    public static final String JUMP_ID = "jump_id";
    public static final String JUMP_FROM = "jump_from";
    public static final String TRANS_LATINO_X = "translationX";

    /**
     * 顶部tab展示组件
     */
    SlidingTabLayout tlNewType;
    /**
     * 内容展示组件
     */
    ViewPager vpNewListContent;
//    CustomPagerAdapter pagerAdapter;
    ImageView titleSearch;
    private boolean hintSwitchOn;
    private int lastMsgCount = -1;
    private int tab = 1;
//    final LinkedList<ChannelType> channelTypes = new LinkedList();
    private String jumpID;
    private String jumpFrom;
    private boolean playerRight = false;
    private int moveDistance;
    private TextView hasNewChannels;
    private ViewStub vbNewsChannels;
    private RelativeLayout rlOffline;

    @Override
    protected View getContentView(LayoutInflater layoutInflater) {
        view = layoutInflater.inflate(R.layout.cjfragment_news, null, false);
        return view;
    }

    @Override
    protected void initView(View view, Bundle bundle) {
        if (getArguments() != null) {
            jumpID = getArguments().getString(JUMP_ID);
            jumpFrom = getArguments().getString(JUMP_FROM);
        }
////        initTopBarData();
//        tlNewType = (SlidingTabLayout) view.findViewById(R.id.tl_new_type);
//        vpNewListContent = (ViewPager) view.findViewById(R.id.vp_new_list_content);
        titleSearch = (ImageView) view.findViewById(R.id.title_search);
////        pagerAdapter = new CustomPagerAdapter(getChildFragmentManager(), channelTypes);
//        vpNewListContent.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
////        vpNewListContent.setAdapter(pagerAdapter);
//        tlNewType.setViewPager(vpNewListContent);
////        pagerAdapter.notifyDataSetChanged();
////        pagerAdapter.setCacheSize(7);
////        vpNewListContent.setOffscreenPageLimit(1);
////        initPageChangeListener();
//        tlNewType.setCurrentTab(tab);
//        tlNewType.getTitleView(tab).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
//        vpNewListContent.setCurrentItem(tab);
//        //监听开关
//        SpManager.getPreferences().getBooleanObservable(
//                "EXPRESS_NEWS_SWITCH", true)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        hintSwitchOn = aBoolean;
//                        ToastUtil.INSTANCE.showCustomToast("Sp监听");
//                    }
//                });
        view.findViewById(R.id.scan_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.INSTANCE.showCustomToast("扫一扫");
            }
        });

        view.findViewById(R.id.news_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.INSTANCE.showCustomToast("搜索页面");
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

//    /**
//     * 添加 addOnPageChangeListener
//     */
//    private void initPageChangeListener() {
//        vpNewListContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                // 页面滚动回调
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (channelTypes.get(position).isNew()) {
//                    channelTypes.get(position).setNew(false);
//                    tlNewType.hideMsg(position);
//                    NewsChannelManager.getInstance().saveInternal();
//                } else {
//                    tlNewType.hideMsg(position);
//                }
//                //改变选中位置相应的字体
//                changeTopbarNameSize(position);
//                if (position == pagerAdapter.getChannelPosition(ChannelType.SPECIAL_COLUMN)) {
//                    tlNewType.hideMsg(position);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {//
//            }
//        });
//    }
//
//    /**
//     * 页面滑动后改变SIZE
//     *
//     * @param position 位置
//     */
//    private void changeTopbarNameSize(int position) {
//        for (int i = 0; i < channelTypes.size(); i++) {
//            if (i == position) {
//                // 选中字号
//                TextView textView = tlNewType.getTitleView(position);
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
//                textView.invalidate();
//            } else {
//                // 未选中 字号
//                TextView textView = tlNewType.getTitleView(i);
//                textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
//                textView.invalidate();
//            }
//        }
//    }
//
//    public void onFragmentSelected() {
//        if (pagerAdapter.getCurrentFragment() instanceof ExpressNewsFragment) {
//            pagerAdapter.getCurrentFragment().reloadData();
//        } else if (pagerAdapter.getCurrentFragment() instanceof SpecialColumnMainFragment) {
//            pagerAdapter.getCurrentFragment().reloadData();
//        }
//    }
//
//    @Override
    protected void initData(Bundle bundle) {
        RxView.clicks(titleSearch).throttleFirst(1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
//                ChannelActivity.toChannelActivity(getActivity(),
//                        pagerAdapter.getType(vpNewListContent.getCurrentItem()));
                ToastUtil.INSTANCE.showCustomToast("搜索");

            }
        });
    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//    }
//
//    private void initTopBarData() {
//        channelTypes.clear();
//        channelTypes.addAll(NewsChannelManager.getInstance().getSelected(HeContext.getInstance()));
//
//        if (channelTypes.isEmpty()) {
//            return;
//        }
//        ChannelType goChannel = channelTypes.get(1);
//        if (jumpID != null) {
//            final ChannelType channelType = NewsChannelManager.getChannelType(jumpID);
//            if (channelType != null) {
//                goChannel = channelType;
//                int index = channelTypes.indexOf(channelType);
//                if (index != -1) {
//                    tab = index;
//                } else {
//                    Observable.timer(300, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(new Consumer<Long>() {
//                                @Override
//                                public void accept(Long aLong) throws Exception {
//                                    if (isAdded()) {
//                                        ArrayList<ChannelType> newChannels = new ArrayList<>(channelTypes);
//                                        newChannels.add(channelType);
//                                        NewsChannelManager.getInstance().save(getActivity(), newChannels);
//                                        NewsChannelManager.getInstance().setChannelChanged(channelType, true);
//                                    }
//                                }
//                            });
//                }
//            }
//            jumpID = null;
//        } else {
//            tab = channelTypes.indexOf(ChannelType.TOPNEWS);
//        }
//        if (channelTypes.get(0).equals(goChannel)) {//如果要路由的频道是第0个，则不会调用onPageSelected,这里手动记录一次dplus
//            addChannelViewDplus(goChannel);
//        }
//    }
//
//    private class CustomPagerAdapter extends android.support.v4.view.PagerAdapter {
//        private List<ChannelType> types;
//
//        private final FragmentManager mFragmentManager;
//        private FragmentTransaction mCurTransaction = null;
//        private BaseNewsFragment mCurrentPrimaryItem = null;
//        private int cacheSize = 1;
//
//        public CustomPagerAdapter(FragmentManager fm, List<ChannelType> types) {
//            mFragmentManager = fm;
//            this.types = types;
//        }
//
//        private void setChannelTypes(List<ChannelType> types) {
//            this.types = types;
//        }
//
//        @Override
//        public int getItemPosition(Object object) {
//            if (object instanceof BaseNewsFragment) {
//                final BaseNewsFragment fragment = (BaseNewsFragment) object;
//                final ChannelType channelType = fragment.getChannelType();
//                return channelType != null && channelType.equals(getType(fragment.getPosition())) ?
//                        POSITION_UNCHANGED : POSITION_NONE;
//            }
//            return super.getItemPosition(object);
//        }
//
//        public int getChannelPosition(ChannelType channelType) {
//            if (types != null) {
//                for (int i = 0; i < types.size(); i++) {
//                    if (types.get(i).equals(channelType)) {
//                        return i;
//                    }
//                }
//            }
//            return -1;
//        }
//
//        void setCacheSize(int cacheSize) {
//            this.cacheSize = cacheSize;
//        }
//
//        public void setUserVisibleHint(boolean isVisible) {
//            if (mCurrentPrimaryItem != null) {
//                mCurrentPrimaryItem.setMenuVisibility(isVisible);
//                mCurrentPrimaryItem.setUserVisibleHint(isVisible);
//            }
//        }
//
//        /**
//         * @param position
//         * @return if (ChannelType.OPTIONAL.equals(type)) {
//         * fragment = new OptionalNewsFragment();
//         * fragment.setChannelType(ChannelType.OPTIONAL);
//         * } else
//         */
//        private Fragment getItem(int position) {
//            if (position >= types.size()) {
//                return null;
//            }
//
//            BaseNewsFragment fragment;
//            final ChannelType type = getType(position);
//            if (ChannelType.TOPNEWS.equals(type)) {
//                fragment = new TopNewsFragment();
//                fragment.setChannelType(ChannelType.TOPNEWS);
//            } else if (ChannelType.EXPRESS.equals(type)) {
//                fragment = new ExpressNewsFragment();
//                fragment.setChannelType(ChannelType.EXPRESS);
//            } else if (ChannelType.RADIO.equals(type)) {
//                fragment = RadioMainFragment.newInstance();
//                fragment.setChannelType(ChannelType.RADIO);
//            } else if (ChannelType.SPECIAL_COLUMN.equals(type)) {
//                fragment = SpecialColumnMainFragment.newInstance();
//                fragment.setChannelType(ChannelType.SPECIAL_COLUMN);
//            } else if (ChannelType.COMPLAINT.equals(type)) {
//                fragment = ComplaintMainFragment.newInstance();
//                fragment.setChannelType(ChannelType.COMPLAINT);
//            } else {
//                fragment = new OthersNewsFragment();
//                fragment.setChannelType(type);
//            }
//            return fragment;
//        }
//
//        private ChannelType getType(int position) {
//            if (position < types.size()) {
//                return types.get(position);
//            }
//            return null;
//        }
//
//        BaseNewsFragment getCurrentFragment() {
//            return mCurrentPrimaryItem;
//        }
//
//        @Override
//        public int getCount() {
//            return types == null ? 0 : types.size();
//        }
//
//        @Override
//        public void startUpdate(ViewGroup container) {
//            if (container.getId() == View.NO_ID) {
//                throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
//            }
//        }
//
//        @Override
//        public CharSequence getPageTitle(int position) {
//            if (types.get(position) == ChannelType.EXPRESS) {
//                return "7×24";
//            }
//            return types.get(position).getName();
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            if (mCurTransaction == null) {
//                mCurTransaction = mFragmentManager.beginTransaction();
//            }
//
//            final String name = makeFragmentName(position);
//            Fragment fragment = mFragmentManager.findFragmentByTag(name);
//            if (fragment != null) {
//                if (fragment.isHidden()) {
//                    mCurTransaction.show(fragment);
//                } else {
//                    mCurTransaction.attach(fragment);
//                }
//            } else {
//                fragment = getItem(position);
//                if (fragment != null) {
//                    mCurTransaction.add(container.getId(), fragment, name);
//                }
//            }
//
//            if (fragment instanceof BaseNewsFragment) {
//                ((BaseNewsFragment) fragment).setPosition(position);
//            }
//
//            if (fragment != null && fragment != mCurrentPrimaryItem) {
//                fragment.setMenuVisibility(false);
//                fragment.setUserVisibleHint(false);
//            }
//
//            return fragment;
//        }
//
//        private String makeFragmentName(int position) {
//            if (position < types.size()) {
//                return types.get(position).getId();
//            }
//            return "null";
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            if (mCurTransaction == null) {
//                mCurTransaction = mFragmentManager.beginTransaction();
//            }
//            BaseNewsFragment fragment = (BaseNewsFragment) object;
//            if (position < cacheSize || fragment.keepAlive()) {
//                mCurTransaction.hide(fragment);
//            } else {
//                mCurTransaction.detach(fragment);
//            }
//        }
//
//        @Override
//        public void setPrimaryItem(ViewGroup container, int position, Object object) {
//            BaseNewsFragment fragment = (BaseNewsFragment) object;
//            if (fragment != mCurrentPrimaryItem) {
//                if (mCurrentPrimaryItem != null) {
//                    mCurrentPrimaryItem.setMenuVisibility(false);
//                    mCurrentPrimaryItem.setUserVisibleHint(false);
//                    mCurrentPrimaryItem.setPrimary(false);
//                }
//                if (fragment != null) {
//                    fragment.setMenuVisibility(true);
//                    fragment.setUserVisibleHint(true);
//                    fragment.setPrimary(true);
//                    fragment.reloadData();
//                }
//                mCurrentPrimaryItem = fragment;
//            }
//        }
//
//        @Override
//        public void finishUpdate(ViewGroup container) {
//            if (mCurTransaction != null) {
//                mCurTransaction.commitNowAllowingStateLoss();
//                mCurTransaction = null;
//            }
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object object) {
//            return ((Fragment) object).getView() == view;
//        }
//
//        @Override
//        public Parcelable saveState() {
//            return null;
//        }
//
//    }
//
//    @Override
//    protected void onShowingChanged(boolean isShowing) {
//        super.onShowingChanged(isShowing);
//        if (pagerAdapter != null) {
//            pagerAdapter.setUserVisibleHint(isShowing);
//        }
//
//        if (playerView != null) {
//            playerView.closePlayer();
//            //判断电台频道是否存在 （否，播放器不显示）
//            if (NewsChannelManager.getInstance().getUnSelected(getActivity()).contains(ChannelType.RADIO)) {
//                playerView.setVisibility(View.GONE);
//                HePlayer.getInstance(getContext()).setPlayStatus(false);
//                HePlayer.getInstance(getActivity()).stop();
//            }
//        }
//        showNewChannelMark();
//    }
//
//    /**
//     * 上导航栏显示new标识
//     * 如又新添加频道并且没有点击过（或进入该频道）显示新标识
//     */
//    private void showNewChannelMark() {
//        if (tlNewType == null) {
//            return;
//        }
//        //添加（在我的频道区域）新频道标识new
//        List<Integer> positionList = NewsChannelManager.getInstance().getNewAddChannelsPosition();
//        if (positionList != null && !positionList.isEmpty()) {
//            for (Integer integer : positionList) {
//                tlNewType.showNewChannel(integer);
//            }
//        }
//        if (isShowing()) {
//            showHasNewMark();
//        }
//    }
//
//    /**
//     * 异步处理
//     * 是否展示 新增频道标记
//     */
//    private void showHasNewMark() {
//        //(只有其他频道区域的频道有新加的频道时，显示)
//        if (NewsChannelManager.getInstance().unselectHasNewAddChannels()) {
//            Observable.just(!SPUtil.getInstance().getBoolean("hide", true))
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(new Consumer<Boolean>() {
//                        @Override
//                        public void accept(Boolean aBoolean) throws Exception {
//                            if (hasNewChannels == null) {
//                                hasNewChannels = (TextView) vbNewsChannels.inflate();
//                            }
//                            if (aBoolean) {
//                                hasNewChannels.setVisibility(View.GONE);
//                            } else {
//                                hasNewChannels.setVisibility(View.VISIBLE);
//                            }
//                        }
//                    });
//        } else {
//            if (null != hasNewChannels) {
//                hasNewChannels.setVisibility(View.GONE);
//            }
//        }
//    }
}
