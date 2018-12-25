package com.example.administrator.kotlintest.dateyearmonthday;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by lirl on 2017-06-22.
 */
public class AttendviewActivity extends RxAppCompatActivity {

    @SuppressWarnings("unused")
    private static final String TAG = "AttendviewActivity";
    public static AttendviewActivity attendviewActivity;


    /**
     * 和viewpager相关变量
     */
    public MyViewPager viewPager = null;
    public MyPagerAdapter pagerAdapter = null;
    private int currPager = 500;
    private TextView shader;
    /**
     * 和日历gridview相关变量
     */
    private GridView gridView = null;
    public CalendarAdapter adapter = null;
    private GridView currentView = null;
    public List<DateInfo> currList = null;
    public List<DateInfo> list = null;
    public int lastSelected = 0;

    /**
     * 显示年月
     */
    public TextView showYearMonth = null;

    /**
     * 第一个页面的年月
     */
    private int currentYear;
    private int currentMonth;

    private int searchYear;
    private int searchMonth;

    /**
     * 收缩展开的面板
     */
    private DatePanelView panel;

    private TextView attendinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendview);
        attendviewActivity = this;

        searchmonth = new ArrayList<String>();
        currentYear = TimeUtil.getCurrentYear();
        currentMonth = TimeUtil.getCurrentMonth();
        searchYear = currentYear;
        searchMonth = currentMonth;

        initFirstData();
        initView();

    }


    /**
     * 初始化view
     */
    private void initView() {
        lastSelected = TimeUtil.getCurrentDay();
        viewPager = (MyViewPager) findViewById(R.id.viewpager);
        shader = (TextView) findViewById(R.id.main_frame_shader);
        panel = (DatePanelView) findViewById(R.id.panel);
        panel.setOpen(true, false);
        shader.setVisibility(View.GONE);
        pagerAdapter = new MyPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(500);
        viewPager.setPageMargin(0);
        showYearMonth = (TextView) findViewById(R.id.main_year_month);
        showYearMonth.setText(String.format("%04d年%02d月", currentYear, currentMonth));

        attendinfo = (TextView) findViewById(R.id.attendinfo);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int arg0) {
                if (arg0 == 1) {
                    shader.setText("");
                    shader.setVisibility(View.VISIBLE);
                }
                if (arg0 == 0) {
                    currentView = (GridView) viewPager.findViewById(currPager);
                    if (currentView != null) {
                        adapter = (CalendarAdapter) currentView.getAdapter();
                        currList = adapter.getList();
                        int pos = DataUtil.getDayFlag(currList, lastSelected);
                        adapter.setSelectedPosition(pos);
                        adapter.notifyDataSetInvalidated();
                    }
                    shader.setVisibility(View.GONE);

                    initFirstData();
                }
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageSelected(int position) {
                int year = TimeUtil.getTimeByPosition(position, currentYear, currentMonth, "year");
                int month = TimeUtil.getTimeByPosition(position, currentYear, currentMonth, "month");
                showYearMonth.setText(String.format("%04d年%02d月", year, month));
                searchYear = year;
                searchMonth = month;

                currPager = position;
                shader.setText(showYearMonth.getText());

            }
        });
    }

    /**
     * 初始化数据
     */
    private void initFirstData() {
        String formatDate = TimeUtil.getFormatDate(searchYear, searchMonth);
        final String searchkey = searchYear + "," + searchMonth;

//        loadData(formatDate, searchMonth, true);
    }

    List<String> searchmonth = null;

    HashMap<String, String> sumdata = new HashMap<String, String>();
//    String attendFtv = ",20181201 加班,20181202 旷工,20181203 旷工,20181204 出勤,20181205 旷工,20181206 旷工,20181207 旷工,20181208 旷工,20181209 旷工,20181210 旷工,20181211 旷工,20181212 旷工,20181213 旷工,20181214 出勤,20181215 旷工,20181216 旷工,20181217 出勤,20181218 旷工";
//    String attendFtv = ",20181201 加班,20181202 旷工,20181203 旷工,20181204 出勤,20181205 旷工,20181206 旷工,20181207 旷工,20181208 旷工,20181209 旷工,20181210 旷工,20181211 旷工,20181212 旷工,20181213 旷工,20181214 出勤,20181215 旷工,20181216 旷工,20181217 出勤,20181218 旷工";
    String attendFtv = "";
    /*考勤查询(按月)*/
//    private List_dataCallback attendlistCallback = new List_dataCallback();
    /**
     * 考勤查询(按月)
     */
//    class List_dataCallback extends HttpManger<AttendMonthView> {
//        @Override
//        public void onDownSuccess_normal(BaseResultEntity<AttendMonthView> m) {
//            try {
//                if (m == null) {
//                    return;
//                }
//                if (m.getCode() == 0) {
//                    AttendMonthView attendMonthView=m.getData();
//                    List<AttendMonthViewItem> attendlist = attendMonthView.getRecords();
//
//                    String attendFtv="";
//                    for (AttendMonthViewItem item: attendlist
//                            ) {
//                        attendFtv += "," + item.getDate()+" "+item.getState();
//                    }
//                    String _attendsum = attendMonthView.getAttendanceSum();
//
//                    sumdata.put(_formatDate, _attendsum);
//                    attendinfo.setText(_attendsum);
//
//                    list = TimeUtil.initCalendar(_formatDate, _month, searchYear, searchMonth, attendFtv.split(","));
//                    adapter.setList(list);
//                    adapter.notifyDataSetInvalidated();
//
//                } else {
//                    ToastUtilKt.INSTANCE.showCustomToast(m.getMessage());
//                }
//
//            } catch (Exception e) {
//                Log.d("List_dataCallback", e.getMessage());
//            }
//        }
//    }


    private String _formatDate;
    private int _month;
    private void loadData(final String formatDate, final int month, boolean issearch) {
        try {
            _formatDate=formatDate;
            _month=month;

//            attendlistCallback.reqeust_normal(this,
//                    IService.attendService().getattendancelist_bymonth(searchYear,searchMonth,UserDef.fattuserid),
//                    TipDef.loading);


            final String searchkey = searchYear + "," + searchMonth;
            if (!issearch || searchmonth.contains(searchkey)) {
                list = TimeUtil.initCalendar(formatDate, month, searchYear, searchMonth, attendFtv.split(","));
                if (sumdata.containsKey(searchkey)) {
                    attendinfo.setText(sumdata.get(searchkey));
                } else {
                    attendinfo.setText("");
                }
            } else {

                searchmonth.add(searchkey);

                //attendlistCallback.reqeust_normal(this, IService.attendService().getattendancelist_bymonth(searchYear,searchMonth,UserDef.fattuserid), TipDef.loading);

//                SimpleArrayMap mapParams = new SimpleArrayMap();
//                mapParams.put("fyear", searchYear);
//                mapParams.put("fmonth", searchMonth);
//                mapParams.put("fattuserid", UserDef.fattuserid);
//
//                WebServiceUtils.call(WSDef.Getattmonth, mapParams,
//                        new WebServiceUtils.Response() {
//                            @Override
//                            public void onSuccess(SoapObject result) {
//                                if (result == null) {
//                                    return;
//                                }
//                                try {
//                                    //Log.d("ret", result.toString());
//                                    HashMap m = AliJsonUtil.decodeMap(result.getProperty(0).toString());
//                                    String _attendFtv = m.get("list").toString();
//                                    attendFtv += "," + _attendFtv;
//                                    String _attendsum = m.get("sum").toString();
//
//                                    sumdata.put(searchkey, _attendsum);
//                                    attendinfo.setText(_attendsum);
//
//                                    list = TimeUtil.initCalendar(formatDate, month, searchYear, searchMonth, attendFtv.split(","));
//                                    adapter.setList(list);
//                                    adapter.notifyDataSetInvalidated();
//                                } catch (Exception e) {
//                                    Log.d("Getattmonth", e.getMessage());
//                                }
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                Log.d("Getattmonth", e.getMessage());
//                            }
//                        }, null, TipDef.loading);


                list = TimeUtil.initCalendar(formatDate, month, searchYear, searchMonth, attendFtv.split(","));
                adapter.setList(list);
                adapter.notifyDataSetInvalidated();
            }
        } catch (Exception e) {
            finish();
        }
    }

    /**
     * 初始化日历的gridview
     */
    private GridView initCalendarView(int position) {
        int year = TimeUtil.getTimeByPosition(position, currentYear, currentMonth, "year");
        int month = TimeUtil.getTimeByPosition(position, currentYear, currentMonth, "month");
        String formatDate = TimeUtil.getFormatDate(year, month);

        loadData(formatDate, month, false);

        gridView = new GridView(this);
        adapter = new CalendarAdapter(this, list);


        if (position == 500) {
            currList = list;
            int pos = DataUtil.getDayFlag(list, lastSelected);
            adapter.setSelectedPosition(pos);
        }
        gridView.setAdapter(adapter);
        gridView.setNumColumns(7);
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        gridView.setGravity(Gravity.CENTER);
        //gridView.setOnItemClickListener(new OnItemClickListenerImpl(adapter, this));
        return gridView;
    }

    /**
     * viewpager的适配器，从第500页开始，最多支持0-1000页
     */
    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public void setPrimaryItem(ViewGroup container, int position,
                                   Object object) {
            currentView = (GridView) object;
            adapter = (CalendarAdapter) currentView.getAdapter();
        }

        @Override
        public int getCount() {
            return 1000;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            GridView gv = initCalendarView(position);
            gv.setId(position);
            container.addView(gv);
            return gv;
        }
    }

    public void clickFeedBack(View v) {
        finish();
    }

}
