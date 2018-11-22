package com.xfs.fsyuncai.bridge.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.administrator.kotlintest.dao.DaoMaster
import com.example.administrator.kotlintest.dao.SearchHistoryDao
import com.example.administrator.kotlintest.entity.date.SearchHistory
import com.example.administrator.kotlintest.MyApplication

/**
 *  Created by kangf on 2018/6/16.
 *
 *  数据库操作--搜索历史记录
 */
class SearchHistoryDbUtil private constructor() {


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
        val instance = SearchHistoryDbUtil()
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


    fun saveCookie(info: SearchHistory) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.searchHistoryDao
        val oldDao = queryCookieBy(info.history)
        if (oldDao != null) {
            deleteCookie(oldDao)
        }
        downInfoDao.insert(info)
    }

    fun updateCookie(info: SearchHistory) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.searchHistoryDao
        downInfoDao.update(info)
    }

    fun deleteCookie(info: SearchHistory) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.searchHistoryDao
        downInfoDao.delete(info)
    }

    fun clear(name: String) {
        queryCookieOrderByTime(name)?.map {
            deleteCookie(it)
        }

    }

    fun queryCookieOrderByTime(name: String): List<SearchHistory>? {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.searchHistoryDao
        val qb = downInfoDao.queryBuilder()
        qb.orderDesc(SearchHistoryDao.Properties.Time)
        qb.where(SearchHistoryDao.Properties.Username.eq(name))
        val list = qb.list()
        return if (list.isEmpty()) null else list
    }


    fun queryCookieBy(name: String): SearchHistory? {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.searchHistoryDao
        val qb = downInfoDao.queryBuilder()
        qb.where(SearchHistoryDao.Properties.History.eq(name))
        val list = qb.list()
        return if (list.isEmpty()) {
            null
        } else {
            list[0]
        }
    }

    fun queryCookieAll(): List<SearchHistory> {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.searchHistoryDao
        val qb = downInfoDao.queryBuilder()
        return qb.list()
    }


}