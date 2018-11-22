package com.xfs.fsyuncai.bridge.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.administrator.kotlintest.bridge.retrofit.callback.CookieResulte
import com.example.administrator.kotlintest.dao.CookieResulteDao
import com.example.administrator.kotlintest.dao.DaoMaster
import com.example.administrator.kotlintest.MyApplication

/**
 * Created by kangf on 2018/6/16.
 *
 * 数据库操作 -- 网络数据缓存
 */
class CookieDbUtil private constructor() {


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
        val instance = CookieDbUtil()
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

    fun saveCookie(info: CookieResulte) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResulteDao
        downInfoDao.insert(info)
    }

    fun updateCookie(info: CookieResulte) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResulteDao
        downInfoDao.update(info)
    }

    fun deleteCookie(info: CookieResulte) {
        val daoMaster = DaoMaster(getWritableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResulteDao
        downInfoDao.delete(info)
    }

    fun queryCookieBy(url: String): CookieResulte? {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResulteDao
        val qb = downInfoDao.queryBuilder()
        qb.where(CookieResulteDao.Properties.Url.eq(url))
        val list = qb.list()
        return if (list.isEmpty()) {
            null
        } else {
            list[0]
        }
    }

    fun queryCookieAll(): List<CookieResulte> {
        val daoMaster = DaoMaster(getReadableDatabase())
        val daoSession = daoMaster.newSession()
        val downInfoDao = daoSession.cookieResulteDao
        val qb = downInfoDao.queryBuilder()
        return qb.list()
    }


}