package com.xfs.fsyuncai.bridge.retrofit.service.body

/**
 * Created by kangf on 2018/6/25.
 *
 * 请求体
 */
class SearchResultRequestBody {
    var categoryId: Int? = null
    var categoryLevel: Int? = null
    var sort: Int? = null
    var scoreSort: Int? = null
    var curPage: Int? = null
    var rows: Int? = null
    var cityId: Int? = null
    var customerId: String? = null
    var attriNameAndValues: String? = null
    var queryString: String? = null
    var stockSort: Int? = null
    var priceRange: String? = null
    var brandNameStr: String? = null
    var brandCatName: Int? = null
    var brandCatLevel: Int? = null
    var brandCatId: Int? = null
    var platformType: Int? = 2  // 普通平台为2，签约平台为1
    var wareHouseId: Int? = 1
}