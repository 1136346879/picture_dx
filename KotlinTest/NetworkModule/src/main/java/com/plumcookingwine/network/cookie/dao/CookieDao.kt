package com.plumcookingwine.network.cookie.dao

data class CookieDao(

    // 数据id
    var id: Long? = null,
    // 缓存结果
    var result: String? = null,
    // 缓存时间
    var date: Long? = null
)