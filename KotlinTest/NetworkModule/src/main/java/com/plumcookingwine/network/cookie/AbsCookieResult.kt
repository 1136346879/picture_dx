package com.plumcookingwine.network.cookie

import com.plumcookingwine.network.cookie.dao.CookieDao

/**
 * 抽象数据缓存，用户可自定义缓存实现方法
 */
abstract class AbsCookieResult {

    // 保存数据
    abstract fun saveData(t: CookieDao?)

    // 获取数据
    abstract fun getData(): CookieDao?

    // 更新数据
    abstract fun upData(t: CookieDao?)

    // 删除数据
    abstract fun deleteData(t: CookieDao?)


}