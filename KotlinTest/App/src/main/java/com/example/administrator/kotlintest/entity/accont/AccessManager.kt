package com.xfs.fsyuncai.entity.accont

/**
 * Created by kangf on 2018/7/16.
 */

class AccessManager {

    private var accessDataResource: AccessDataResource? = null

    private constructor() {
        accessDataResource = LocalAccessData()
    }

    companion object {
        @JvmStatic
        fun instance(): AccessDataResource {
            return Inner.instance.accessDataResource!!
        }
    }



    private object Inner {

        @JvmStatic
        val instance: AccessManager = AccessManager()
    }
}