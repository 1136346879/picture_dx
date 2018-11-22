package com.example.administrator.kotlintest.ui.entity

/**
 * Created by kangf on 2018/7/27.
 */
data class PersonControlDao(
        var text: String,
        var hint: String?,
        var icon: String? = null,
        var isShowIn: Boolean? = true,
        var hasCircle: Boolean? = null)