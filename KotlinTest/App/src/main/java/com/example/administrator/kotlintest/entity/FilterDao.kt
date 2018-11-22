package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/6/23.
 */
data class FilterDao(var id: Int? = null,
                     var name: String? = null,
                     var desc: String? = null,
                     var options: ArrayList<OptionDao>? = null) {


    data class OptionDao(var id: Int? = null,
                         var name: String? = null,
                         var desc: String? = null,
                         var isCheck: Boolean = false)
}