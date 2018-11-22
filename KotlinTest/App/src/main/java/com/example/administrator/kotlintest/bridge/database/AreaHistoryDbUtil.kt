package com.xfs.fsyuncai.bridge.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.administrator.kotlintest.dao.AreaSelectedHistoryDataDao
import com.example.administrator.kotlintest.dao.DaoMaster
import com.example.administrator.kotlintest.entity.date.AreaSelectedHistoryData
import com.example.administrator.kotlintest.MyApplication

/**
*@author : HaoBoy
*@date : 2018/8/21
*@description :地址搜索历史记录
**/
class AreaHistoryDbUtil private constructor() {

    @JvmField
    val DB_NAME = "fsyuncai_db"

    @JvmField
    var openHelper: DaoMaster.DevOpenHelper? = null

    @JvmField
    var context: Context? = null

    init {
        context = MyApplication.cxt
        openHelper = DaoMaster.DevOpenHelper(context, DB_NAME)
    }

    companion object {
        fun instance() = Inner.instance
    }

    private object Inner {
        val instance = AreaHistoryDbUtil()
    }

    /**
     * 获取可读数据库
     */
    private fun getReadableDatabase(): SQLiteDatabase {
        openHelper = if (openHelper == null) DaoMaster.DevOpenHelper(context, DB_NAME) else openHelper
        return openHelper!!.readableDatabase
    }

    /**
     * 获取可写数据库
     */
    private fun getWritableDatabase(): SQLiteDatabase {
        openHelper = if (openHelper == null) DaoMaster.DevOpenHelper(context, DB_NAME) else openHelper
        return openHelper!!.writableDatabase
    }


    fun saveData(info: AreaSelectedHistoryData) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val dao = daoSession.areaSelectedHistoryDataDao
        val oldDao = queryDataByCode(info.code)
        if (oldDao != null) {
            deleteData(oldDao)
        }
        dao.insert(info)
    }

    fun updateData(info: AreaSelectedHistoryData) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.areaSelectedHistoryDataDao
        downInfoDao.update(info)
    }

    fun deleteData(info: AreaSelectedHistoryData) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.areaSelectedHistoryDataDao
        downInfoDao.delete(info)
    }

    fun clear() {
        queryDataAllOrderByTime().map {
            deleteData(it)
        }
    }

    /**根据Code查数据**/
    fun queryDataByCode(code: String): AreaSelectedHistoryData? {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.areaSelectedHistoryDataDao
        val qb = downInfoDao.queryBuilder()
        qb.where(AreaSelectedHistoryDataDao.Properties.Code.eq(code))
        val list = qb.list()
        return if (list.isEmpty()) {
            null
        } else {
            list[0]
        }
    }
    /**查所有数据**/
    fun queryDataAllOrderByTime(): List<AreaSelectedHistoryData> {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.areaSelectedHistoryDataDao
        val qb = downInfoDao.queryBuilder().orderDesc(AreaSelectedHistoryDataDao.Properties.InsertTime)
        return qb.list()
    }


}