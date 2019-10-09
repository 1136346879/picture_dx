package com.plumcookingwine.network.cookie

import com.google.gson.Gson
import com.plumcookingwine.network.cookie.dao.CookieDao
import com.plumcookingwine.network.helper.NetworkHelper
import com.plumcookingwine.network.utils.ACache
import java.lang.Exception

class CookieResultDefault(private val cacheUrl: String) : AbsCookieResult() {

    private val mACache = ACache.get(NetworkHelper.instance.getContext(), "NET_WORK_DATA")

    override fun saveData(t: CookieDao?) {
        if (t == null) return
        mACache.put(cacheUrl, Gson().toJson(t))
    }

    override fun getData(): CookieDao? {
        val json = mACache.getAsString(cacheUrl)
        return try {
            Gson().fromJson(json, CookieDao::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override fun upData(t: CookieDao?) {
        if (t == null) return
        mACache.put(cacheUrl, Gson().toJson(t))
    }

    override fun deleteData(t: CookieDao?) {
        mACache.remove(cacheUrl)
    }
}