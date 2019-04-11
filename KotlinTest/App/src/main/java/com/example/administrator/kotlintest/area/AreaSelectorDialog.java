package com.example.administrator.kotlintest.area;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.administrator.kotlintest.R;
import com.example.administrator.kotlintest.entity.address.AddressAreaEntity;
import com.example.baselibrary.widgets.TLog;
import com.example.baselibrary.widgets.UIUtils;
import com.google.gson.Gson;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.xfs.fsyuncai.bridge.retrofit.callback.HttpOnNextListener;
import com.xfs.fsyuncai.bridge.retrofit.exception.ApiErrorModel;
import com.xfs.fsyuncai.bridge.retrofit.http.HttpManager;
import com.xfs.fsyuncai.bridge.retrofit.http.RequestOption;
import com.xfs.fsyuncai.entity.repository.CommonRepository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : HaoBoy
 * @date : 2018/8/20
 * @description :底部弹出-区域选择
 **/
public class AreaSelectorDialog extends Dialog {

    private ResultCallBack resultCallBack;

    private HttpManager httpManager = HttpManager.instance();

    private TabLayout tab_layout;
    private ImageView ivAreaClose;

    private ArrayList<AddressAreaEntity.ListBean> listBeans = new ArrayList<>();//承载的

    private AddressAreaEntity.ListBean selectTab = new AddressAreaEntity.ListBean();//当前选中的tab

    private AreaSelectorAdapter adapter;    //定义一个数组适配器对象

    private String[] specialArea = {"台湾", "钓鱼岛"};//只有这一个级别的省份
    private Handler handler = new Handler();

    private Context context;

    private RxAppCompatActivity appCompatActivity;

    public AreaSelectorDialog(Context context, ResultCallBack resultCallBack, RxAppCompatActivity appCompatActivity) {
        super(context, R.style.bottom_dialog);
        this.context = context;
        this.resultCallBack = resultCallBack;
        this.appCompatActivity = appCompatActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_area_selector);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = UIUtils.getScreenHeight() / 50 * 33;//UI给的是百分之66
        window.setAttributes(params);

        tab_layout = findViewById(R.id.tab_layout);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        ivAreaClose = findViewById(R.id.ivAreaClose);

        adapter = new AreaSelectorAdapter(listBeans, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
        setLogic();
    }

    //添加一个条目--->请选择前面一个--->tab总数-2
    private void addItem(int position) {
        TabLayout.Tab tab = tab_layout.newTab();
        tab.setTag(listBeans.get(position));
        tab.setText(listBeans.get(position).getName());
        TLog.INSTANCE.i("加之前tab的个数" + tab_layout.getTabCount());
        tab_layout.addTab(tab, tab_layout.getTabCount() - 1);
        TLog.INSTANCE.i("加之后tab的个数" + tab_layout.getTabCount());
        getAreaList(listBeans.get(position), false);
        setTabSelect();

    }

    private void removeItem(int position) {
        //设置当前点击
        tab_layout.getTabAt(tab_layout.getSelectedTabPosition()).setText(listBeans.get(position).getName());
        tab_layout.getTabAt(tab_layout.getSelectedTabPosition()).setTag(listBeans.get(position));

        //移除后面的条目
        int startPosition = tab_layout.getSelectedTabPosition() + 1;//移除tab的起始位置
        int endPosition = tab_layout.getTabCount() - 2;//移除tab的结束位置
        List<TabLayout.Tab> list = new ArrayList<>();
        for (int i = startPosition; i <= endPosition; i++) {
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            list.add(tab);
        }
        for (TabLayout.Tab tab : list) {
            tab_layout.removeTab(tab);
        }
        getAreaList(listBeans.get(position), false);
        setTabSelect();
    }

    private void setTabSelect() {
        handler.post(() -> {
            tab_layout.getTabAt(tab_layout.getTabCount() - 1).select();//选中最后一个-请选择
        });
    }

    private void setLogic() {
        getAreaList(null, false);
        adapter.setOnClickItem((pos) -> {

            //是否是台湾 钓鱼岛这样的地方，只有自己一个级别
            for (String area : specialArea) {
                if (listBeans.get(pos).getName().equals(area)) {
                    ArrayList<AddressAreaEntity.ListBean> localList = new ArrayList<>();
                    localList.add(listBeans.get(pos));
                    if (resultCallBack != null) {
                        resultCallBack.onDismissForResult(localList);
                        dismiss();
                        return null;
                    }
                }
            }

            //防止快速点击，第二个判断用于回点以选中城市时逻辑
            if (selectTab.getLevel() == listBeans.get(pos).getLevel() && !selectTab.isChecked()) {
                return null;
            }

            //只有一个tab-请选择  或者选择的是请选择
            if (tab_layout.getTabCount() == 1 ||
                    tab_layout.getSelectedTabPosition() == tab_layout.getTabCount() - 1) {
                addItem(pos);
            } else {
                removeItem(pos);
            }
            return null;
        });

        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() != tab_layout.getTabCount() - 1) {
                    if (tab.getTag() == null) {
                        getAreaList(null, false);
                    } else {
                        //获取父级列表
                        getAreaList((AddressAreaEntity.ListBean) tab.getTag(), true);
                    }
                }
                //点击请选择，并且条目大于一个
                else if (tab_layout.getTabCount() > 1) {
                    getAreaList((AddressAreaEntity.ListBean) tab_layout.getTabAt(tab_layout.getTabCount() - 2).getTag(), false);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        ivAreaClose.setOnClickListener(v -> dismiss());
    }

    /**
     * 网络请求
     *
     * @param bean
     * @param isParent 是否获取父级数据
     */
    private void getAreaList(AddressAreaEntity.ListBean bean, boolean isParent) {
        String code = "";
        if (bean != null) {
            if (isParent) {
                code = bean.getParent_code();
            } else {
                code = bean.getCode() + "";
            }
            selectTab = bean;
        }
        RequestOption option = new RequestOption();
        option.setShowProgress(false);
        httpManager
                .setOption(option)
                .doHttpDeal(context,
                        CommonRepository.INSTANCE.getAreaByParentCode(code).compose(appCompatActivity.bindToLifecycle()),
                        httpOnNextListener,true);
    }

    private HttpOnNextListener httpOnNextListener = new HttpOnNextListener() {
        @Override
        public void onNext(@NotNull String json) {
            Gson gson = new Gson();
            try {
                AddressAreaEntity resultEntity = gson.fromJson(json, AddressAreaEntity.class);
                if (resultEntity == null) {
                    return;
                }
                if (resultEntity.getErrorCode() != 0) {
                    httpOnNextListener.onError(resultEntity.getErrorCode(), new ApiErrorModel(resultEntity.getErrorCode(), resultEntity.getErrorMessage()));
                    return;
                }
                if (resultEntity.getList() == null || resultEntity.getList().size() == 0) {
                    tab_layout.getTabAt(tab_layout.getTabCount() - 1).select();
                    if (resultCallBack != null) {
                        resultCallBack.onDismissForResult(getListResults());
                        dismiss();
                    }
                } else {
                    listBeans.clear();
                    listBeans.addAll(resultEntity.getList());
                    adapter.notifyDataSetChanged();

                    //选中逻辑
                    for (int o = 0; o < listBeans.size(); o++) {
                        if (listBeans.get(o).getCode() == selectTab.getCode()) {
                            selectTab.setChecked(true);
                            listBeans.set(o, selectTab);
                            adapter.notifyItemByPosition(listBeans.indexOf(selectTab));
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                if (resultCallBack != null) {
                    resultCallBack.onDismiss();
                    dismiss();
                }
            }
        }

        @Override
        public void onError(int statusCode, @Nullable ApiErrorModel apiErrorModel) {
            super.onError(statusCode, apiErrorModel);
            if (resultCallBack != null) {
                resultCallBack.onDismiss();
                dismiss();
            }
        }
    };

    private ArrayList<AddressAreaEntity.ListBean> getListResults() {
        ArrayList<AddressAreaEntity.ListBean> results = new ArrayList<>();
        for (int i = 0; i < tab_layout.getTabCount() - 1; i++) {
            TabLayout.Tab tab = tab_layout.getTabAt(i);
            if (tab.getTag() != null) {
                results.add((AddressAreaEntity.ListBean) tab.getTag());
            }
        }
        return results;
    }

    @Override
    protected void onStop() {
        super.onStop();
        handler.removeCallbacksAndMessages(null);
        handler = null;
    }

    public interface ResultCallBack {
        void onDismiss();

        void onDismissForResult(ArrayList<AddressAreaEntity.ListBean> listBeans);
    }
}