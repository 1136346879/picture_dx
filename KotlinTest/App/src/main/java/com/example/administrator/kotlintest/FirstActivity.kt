package com.example.administrator.kotlintest

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.example.administrator.kotlintest.fragment.MeFragment
import com.example.administrator.kotlintest.fragment.NewsFragment
import com.example.administrator.kotlintest.fragment.StockMatchFragment
import com.example.administrator.kotlintest.fragment.StockSelectedMainFragment
import com.hexun.training.hangqing.fragment.MarkHomeFragment
import java.util.ArrayList

class FirstActivity : AppCompatActivity() ,View.OnClickListener{
    protected var savedInstanceState: Bundle? = null
    private var cjNewsFragment: NewsFragment? = null
    private var currentFragmentIndex: Int = 0
    private var fm: FragmentManager? = null
    private val tabs = ArrayList<View>()
    private var lastTab = 2
    val TAB_INDEX = "tab_index"
    private val FRAGMENT_INDEX_KEY = "currentFragmentIndex"
    override fun onCreate(savedInstanceState: Bundle?) {
        this.savedInstanceState = savedInstanceState
        if (savedInstanceState != null) {
            currentFragmentIndex = savedInstanceState.getInt(FRAGMENT_INDEX_KEY)
            Log.e("onCreate", "---currentFragmentIndex---$currentFragmentIndex")
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)
        initView()
        initData()
    }

    /***
     * 设置选中Fragment页面
     *
     * @param index tab的索引
     * @param specifiedIndex 是否指定page
     */

    private fun setFragmentSelect(index: Int, specifiedIndex: Boolean) {
        Log.d("MainActivity", "index=$index")
        Log.e("setFragmentSelect --", "currentFragmentIndex------$currentFragmentIndex")
        if (index < 0 || index > 4) {
            return
        }
        currentFragmentIndex = index
        val transaction = fm!!.beginTransaction()
        var fragment: Fragment?
        var isForce = false
        for (i in 0..4) {
            fragment = fm!!.findFragmentByTag("fragment$i")
            if (fragment == null) {
                if (index == i) {
                    isForce = true
                    fragment = createFragment(i)
                    transaction.add(R.id.tab_content_llt, fragment!!,
                            "fragment$i")
                    if (i == 1 && specifiedIndex) {
                        val markHomeFragment = fragment as MarkHomeFragment?
                        if (null != markHomeFragment) {
                            markHomeFragment!!.toPage(0)
                        }
                    }
                }
            } else {
                if (i == index) {
                    transaction.show(fragment!!)
                    if (i == 1 && specifiedIndex) {
                        transaction.show(fragment!!)
                        val markHomeFragmentShow = fragment as MarkHomeFragment?
                        markHomeFragmentShow!!.toPage(0)
                    }
                } else {
                    transaction.hide(fragment!!)
                }
            }
        }
        if (isForce) {
            transaction.commitNow()
        } else {
            transaction.commitNowAllowingStateLoss()
        }
    }

    /***
     * 设置选中Tab
     *
     * @param index tab的索引
     */
    private fun setTabSelect(index: Int) {
        Log.e("setTabSelect --", "index------$index")
        lastTab = index
        if (tabs != null && index < tabs.size) {
            for (i in tabs.indices) {
                if (i == index) {
                    tabs.get(i).setSelected(true)
                } else {
                    tabs.get(i).setSelected(false)
                }
            }
        }
    }
    /**
     * 创建各个tab页面， 当前有4个
     *
     * @param index
     * @return
     */
    private fun createFragment(index: Int): Fragment? {
        var baseFragment: Fragment? = null
        when (index) {
            0 -> {
                cjNewsFragment = NewsFragment()//新闻页面
//                if (goChannel != null) {//可根据参数跳转到具体频道，如果没有对应频道默认头条
//                    val bundle = Bundle()
//                    bundle.putString(NewsFragment.JUMP_ID, goChannel.getId())
//                    bundle.putString(NewsFragment.JUMP_FROM, upFrom)
//                    cjNewsFragment.setArguments(bundle)
//                }
                baseFragment = cjNewsFragment
            }
            1 -> baseFragment = MarkHomeFragment() as Fragment//行情页面
            2 -> baseFragment = MeFragment()//我的页面
            3 -> baseFragment = StockSelectedMainFragment()//自选页面
            4 -> baseFragment = StockMatchFragment()//股神页面
            else -> {
            }
        }
        return baseFragment
    }
    /**
     * 主页面tab点击事件
     */
    override fun onClick(v: View) {
        val id = v.id
        var event = ""
        when (id) {
            //跳转到新闻
            R.id.tab_news -> {
                setTabSelect(0)
                setFragmentSelect(0, false)
                if (cjNewsFragment != null) {
//                    cjNewsFragment.onFragmentSelected()
                }
            }
            //跳转到行情
            R.id.tab_hangqing -> {
                setTabSelect(1)
                setFragmentSelect(1, false)
            }
            //跳转到我的
            R.id.tab_me -> {
                setTabSelect(2)
                setFragmentSelect(2, false)
            }
            //跳转到自选
            R.id.tab_stock_selected -> {
                setTabSelect(3)
                setFragmentSelect(3, false)
            }

            //跳转到股神
            R.id.tab_stock_god -> {
                setTabSelect(4)
                setFragmentSelect(4, false)
            }
            else -> {
            }
        }
    }
    private fun goTab(intent: Intent) {
        var position = intent.getIntExtra(TAB_INDEX, -1)
        if (position != -1) {
            when (position) {
                0 ->
                    //新闻
                    position = 0
                1 ->
                    //自选
                    position = 3
                2 ->
                    //行情
                    position = 1
                3 ->
                    //我的
                    position = 2
                4 ->
                    //我的
                    position = 4
                else -> position = -1
            }
            Log.e("goTab -- ", "currentFragmentIndex------$position")
            if (position != -1 && currentFragmentIndex != position) {
                setTabSelect(position)
                setFragmentSelect(position, false)
                Log.e("goTab -- position", "------$position")
            }
        }
    }

    /**
     * 初始化页面
     */
    protected fun initView() {

        val tabHangqing = findViewById<LinearLayout>(R.id.tab_hangqing)
        val tabMe = findViewById<RelativeLayout>(R.id.tab_me)
        val tabNews = findViewById<LinearLayout>(R.id.tab_news)
        val tabStockSelected = findViewById<LinearLayout>(R.id.tab_stock_selected)
        val tabStockGod = findViewById<LinearLayout>(R.id.tab_stock_god)
        tabs.add(tabNews)
        tabs.add(tabHangqing)
        tabs.add(tabMe)
        tabs.add(tabStockSelected)
        tabs.add(tabStockGod)

        tabHangqing.setOnClickListener(this)
        tabMe.setOnClickListener(this)
        tabNews.setOnClickListener(this)
        tabStockSelected.setOnClickListener(this)
        tabStockGod.setOnClickListener(this)

        tabNews.isSelected = true
    }

    /**
     * 初始化数据
     */
    protected fun initData() {
        fm = getSupportFragmentManager()
        setTabSelect(currentFragmentIndex)
        setFragmentSelect(currentFragmentIndex, false)
    }

}