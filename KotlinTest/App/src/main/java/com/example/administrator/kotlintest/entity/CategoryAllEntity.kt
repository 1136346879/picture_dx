package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/6/19.
 */
data class CategoryAllEntity(
        var category_level: Int,
        var category_code: String,
        var subList: ArrayList<SubListBeanX>) {

    data class SubListBeanX(
            var category_level: Int,
            var parent_cate_id: Int,
            var category_name: String,
            var category_id: Int,
            var category_code: String,
            var subList: ArrayList<SubListBean>
    ) {

        data class SubListBean(
                var category_level: Int,
                var parent_cate_id: Int,
                var category_name: String,
                var category_id: Int,
                var category_code: String
        )
    }

}