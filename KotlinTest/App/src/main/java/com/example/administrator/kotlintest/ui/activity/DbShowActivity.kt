package com.example.administrator.kotlintest.ui.activity

import android.app.Activity
import android.os.Bundle
import com.blankj.ALog
import com.example.administrator.kotlintest.R
import com.example.administrator.kotlintest.dbutil.MeiziDaoUtils
import com.example.administrator.kotlintest.entity.daoentity.Meizi
import com.example.baselibrary.widgets.ToastUtilKt
import kotlinx.android.synthetic.main.db_show_activity.*

class DbShowActivity : Activity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.db_show_activity)

        //单个插入
        tv1.setOnClickListener {
            insertMeiziData(Meizi(null, "China",
                    "http://7xi8d6.48096_n.jpg"))
        }
        //数据库查询
        tv2.setOnClickListener {
            queryMeiziData()
        }
       // 批量插入操作
        tv3.setOnClickListener {

            var meiziList = arrayListOf(
                    Meizi(null, "HuaWei",
                            "http://7xi8d648096_n.jpg"),
                    Meizi(null, "Apple",
                            "http://7xi8d648096_n.jpg"),
                    Meizi(null, "MIUI",
                            "http://7xi8d648096_n.jpg"))
            manyMeiziaddData(meiziList)
        }
        //查询所有数据
        tv4.setOnClickListener {
            queryMeiziData()
        }
        //删除单条记录
        tv5.setOnClickListener {

                        deleteOneData(Meizi(22,"",""))

        }
        //使用queryBuilder进行条件查询
        tv6.setOnClickListener {
            queryByThings()
        }
        //单条数据更新
        tv7.setOnClickListener {
            updateOneData(Meizi(1, "China",
                    "http://buidu。com"))
        }

        tv8.setOnClickListener {
            nativeQueryAll()
        }
        //使用native sql进行条件查询：
        tv9.setOnClickListener {
                    ToastUtilKt.showCustomToast("删除数据库数据 is " +    MeiziDaoUtils(this).deleteAll())
        }
    }



    /**
     * 单个插入操作
     */
    private fun insertMeiziData(meizi: Meizi){
        ToastUtilKt.showCustomToast("数据库增删改查" + MeiziDaoUtils(this).insertMeizi(meizi))
    }

    /**
     * 查询所有数据
     */
    private fun queryMeiziData(){
        ALog.d("数据库数量：","${MeiziDaoUtils(this).queryAllMeizi().size}")

        MeiziDaoUtils(this).queryAllMeizi().forEach {
            //            ToastUtilKt.showCustomToast("妹子来自==${it.source}" )
            ALog.e("妹子来自==${it.source}","妹子来自==${it?._id} + ${it.url}")

        }

    }
    /**
     * 批量插入操作：
     */
    private fun manyMeiziaddData(meiziList:List<Meizi> ){
        ToastUtilKt.showCustomToast("添加一串妹子 is "+ MeiziDaoUtils(this).insertMultMeizi(meiziList))
    }
    /**
     * 单个更改操作：（其中原有的数据都不会保存，如果新建的对象有属性没有设置，则会为空，不为空的字段没有设置，则报错）
     *
     * 根据id更改对象其他信息
     */
    private fun updateOneData(meizi: Meizi){
        ToastUtilKt.showCustomToast("更新妹子信息 is "+ MeiziDaoUtils(this).updateMeizi(meizi))

    }
    /**
     * 删除某条记录操作：  根据id删除该条数据
     */

    private fun deleteOneData(meizi: Meizi){
        ToastUtilKt.showCustomToast("删除该妹子信息 is "+ MeiziDaoUtils(this).deleteMeizi(meizi))
    }

    /**
     * 使用native sql进行条件查询：
     * @TODO 会崩溃  condition没理解什么意思
     */
    private fun nativeQueryAll(){
        val sql = "where _id > 25"
        val condition =  arrayOf("27")
        val meiziList2 = MeiziDaoUtils(this).queryMeiziByNativeSql(sql, null)
        meiziList2.forEach {
            ALog.e("妹子来自==${it.source}","妹子来自==${it?._id} + ${it.url}")
        }
    }

    /**
     * 使用queryBuilder进行条件查询：
     */
    private fun queryByThings(){
        val meiziList2 = MeiziDaoUtils(this).queryMeiziByQueryBuilder(27)
        meiziList2.forEach {
            ALog.e("妹子来自==${it.source}","妹子来自==${it?._id} + ${it.url}")
        }
    }












}