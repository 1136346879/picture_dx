package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/6/14.
 */
data class CategoryTopEntity(
        var category_image: Int,
        var category_name: String,
        var category_id: Int,
        var category_code: String,
        var isChecked: Boolean = false)