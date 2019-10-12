package com.example.administrator.kotlintest.fragment;

import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.example.administrator.kotlintest.R;
import com.hexun.caidao.hangqing.StockManager;
import java.util.ArrayList;
import java.util.List;


/**
 * @author yangyi 2018年3月27日13:58:51
 */

public class StockSelectedMainFragment extends BaseCaiJingFragment {

    private ViewPager stockSelectedMainPager;

    @Override
    protected View getContentView(LayoutInflater layoutInflater) {
        return layoutInflater.inflate(R.layout.fragment_stock_selected_main, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void initView(View view, Bundle bundle) {
        List<String> titleList = getTitleList();
        stockSelectedMainPager = getViewById(R.id.stockSelectedMainPager);
        stockSelectedMainPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                stockSelectedMainPager.setCurrentItem(position);
                if (position == 0) {
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        stockSelectedMainPager.setCurrentItem(0);
        stockSelectedMainPager.setOffscreenPageLimit(titleList.size());

    }

    @Override
    protected void initData(Bundle bundle) {
        if (StockManager.getInstance().getMyStockCount() > 0) {
        }
    }


    private List<String> getTitleList() {
        List<String> titleList = new ArrayList<>();
        return titleList;
    }

}
