package com.example.administrator.kotlintest

import android.content.Intent
import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import com.example.administrator.kotlintest.bus.RxBus
import com.example.administrator.kotlintest.common.ActiveResultDef
import com.example.administrator.kotlintest.fragment.CategoryFragment
import com.example.administrator.kotlintest.fragment.HomeFragment
import com.example.administrator.kotlintest.fragment.ShoppingCarFragment
import com.example.administrator.kotlintest.navigation.TabAdapter
import com.example.administrator.kotlintest.util.SPUtils
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.xfs.fsyuncai.art.base.view.activity.BaseActivity
import com.xfs.fsyuncai.extend.isLogin
import com.xfs.fsyuncai.main.ui.online.OnlineFragment
import com.example.administrator.kotlintest.fragment.PersonalFragment
import kotlinx.android.synthetic.main.activity_home.*
import java.util.ArrayList

class HomeActiivty : BaseActivity(){
    companion object { //新手引导
        private const val HOME_GUIDE_KEY: String = "HOME_GUIDE_KEY"
    }
    private val homeFrag: HomeFragment = HomeFragment.newInstance()
    private val categoryFrag: CategoryFragment = CategoryFragment.newInstance()
    private val onlineFrag: OnlineFragment = OnlineFragment.newInstance(GONE);
    private val purchasingFrag: ShoppingCarFragment = ShoppingCarFragment.newInstance()
    private val personalFragment: PersonalFragment = PersonalFragment.newInstance()

    private val fragments: ArrayList<androidx.fragment.app.Fragment> = ArrayList()
    private lateinit var tabAdapter: TabAdapter
    private var home: TabLayout.Tab? = null
    private var category: TabLayout.Tab? = null
    private var online: TabLayout.Tab? = null
    private var purchase: TabLayout.Tab? = null
    private var personal: TabLayout.Tab? = null
    private var mSelectedTab = 0
    override fun resLayout(): Int {

        return R.layout.activity_home
    }

    override fun init() {
        val isHasGuide = SPUtils.getObjectForKey(HOME_GUIDE_KEY, false) as Boolean
        if (!isHasGuide) {
            iv_home_guide.visibility = View.VISIBLE
        }
        initTablayout()
        fragments.add(homeFrag)
        fragments.add(categoryFrag)
        fragments.add(onlineFrag)
        fragments.add(purchasingFrag)
        fragments.add(personalFragment)
        tabAdapter = TabAdapter(supportFragmentManager, fragments)
        viewPager.adapter = tabAdapter
        viewPager.isScrollable = false
        viewPager.offscreenPageLimit = 5
        viewPager.currentItem = mSelectedTab
        tab_layout?.getTabAt(mSelectedTab)?.select()
        RxBus.get().toFlowable(String::class.java)
                .bindToLifecycle(this)
                .subscribe {
                    when {
                        TextUtils.equals(it, "login") ||
                                TextUtils.equals(it, "back")
                        -> tab_layout.getTabAt(0)!!.select()
//                        TextUtils.equals(it, "mine") -> tab_layout.getTabAt(4)!!.select()
                        TextUtils.equals(it, "shopping") -> tab_layout.getTabAt(3)!!.select()
                    }
                }
    }

    private fun initTablayout() {
        var localView = LayoutInflater.from(this).inflate(R.layout.tab_home, viewPager, false)
        home = tab_layout.newTab()
        home!!.customView = localView
        tab_layout.addTab(home!!)

        localView = LayoutInflater.from(this).inflate(R.layout.tab_assort, viewPager, false)
        category = tab_layout.newTab()
        category!!.customView = localView
        tab_layout.addTab(category!!)

        localView = LayoutInflater.from(this).inflate(R.layout.tab_online, viewPager, false)
        online = tab_layout.newTab()
        online!!.customView = localView
        tab_layout.addTab(online!!)

        localView = LayoutInflater.from(this).inflate(R.layout.tab_purchase, viewPager, false)
        purchase = tab_layout.newTab()
        purchase!!.customView = localView
        tab_layout.addTab(purchase!!)

        localView = LayoutInflater.from(this).inflate(R.layout.tab_personal, viewPager, false)
        personal = tab_layout.newTab()
        personal!!.customView = localView
        tab_layout.addTab(personal!!)
    }

    override fun logic() {
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                if (tab === tab_layout.getTabAt(0)) {
                    mSelectedTab = 0
                    viewPager.setCurrentItem(0, false)
                } else if (tab === tab_layout.getTabAt(1)) {
                    mSelectedTab = 1
                    viewPager.setCurrentItem(1, false)
                } else if (tab === tab_layout.getTabAt(2)) {
                    mSelectedTab = 2
                    viewPager.setCurrentItem(2, false)

                } else if (tab === tab_layout.getTabAt(3)) {
                    isLogin({
                        //                        mSelectedTab = 3
                        viewPager.setCurrentItem(3, false)
                    }, {
//                        tab_layout?.getTabAt(0)?.select()
                        viewPager.setCurrentItem(3, false)
                    })

                } else if (tab === tab_layout.getTabAt(4)) {
                    isLogin({
                        //                        mSelectedTab = 4
                        viewPager.setCurrentItem(4, false)
                    }, {
//                        tab_layout?.getTabAt(0)?.select()
                        viewPager.setCurrentItem(4, false)
                    })

                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}

            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewPager.addOnPageChangeListener(object : androidx.viewpager.widget.ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position != tab_layout.selectedTabPosition) {
                    tab_layout.getTabAt(position)!!.select()

                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        iv_home_guide.setOnClickListener {
            iv_home_guide.visibility = View.GONE
            SPUtils.setObject(HOME_GUIDE_KEY, true)
        }

//        homeFrag.setOnSelectCategory(object : HomeFragment.OnSelectCategory {
//            override fun selected(pos: Int) {
//                tab_layout.getTabAt(1)?.select()
////                categoryFrag.updatePos(pos)
//            }
//        })
    }
//    override fun onBackPressed() {
//        if (!tab_layout.getTabAt(0)!!.isSelected) {
//            tab_layout.getTabAt(0)!!.select()
//            return
//        }
//        moveTaskToBack(true)
//    }

    override fun onDestroy() {
        homeFrag.setOnSelectCategory(null)
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == ActiveResultDef.BACK_MAIN_HOME) {
            tab_layout.getTabAt(0)!!.select()
        }
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent == null) return
        val select = intent.getIntExtra("select", 0)
        if (select in 0..4) tab_layout.getTabAt(select)?.select()
        setIntent(intent)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mSelectedTab = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState?.putInt("currTabIndex", viewPager.currentItem)
        }

        super.onSaveInstanceState(outState!!)
    }

    fun allCategory() {
        tab_layout.getTabAt(1)?.select()
    }
}