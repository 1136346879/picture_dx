package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2null18/6/23.
 */
class SearchResultEntity {
    var searchResult: SearchResult? = null

    var curPage: Int? = null
    var catAndNumXList: List<CatAndNumXList>? = null

    var attributeXList: List<AttributeXList>? = null
    var errorMessage: String? = null

    var totalPages: Int? = null
    var errorCode: Int? = 0

    var brandList: List<Cate>? = null
    var catAndNumXListNum: Int? = null
    var brandListNum: Int? = null

    var priceRange: PriceRange? = null

    var catLevel: Int? = null

    class SearchResult {

        var recordCount: Int? = null
        var brandlogo: String? = null
        var branddescription: String? = null
        var spuList: List<SpuList>? = null
        var maxPrice: Double? = null
        var itemCode: Int? = null

        class SpuList {
            var spu_id: Int? = null
            var spu_name: String? = null
            var skuNum: Int? = null
            var title: String? = null
            var category_name1: String? = null
            var brand: String? = null
            var cityName: String? = null
            var spuimages: List<String>? = null
            var priceRange: String? = null


        }


    }

    class AttributeValutXList {
        var attri_value: String? = null
    }


    class CatAndNumXList {
        var category_id: Int? = null
        var category1_id: Int? = null
        var category2_id: Int? = null
        var category_name: String? = null
        var category_name1: String? = null
        var category_name2: String? = null


    }

    class AttributeXList {
        var attribute_name: String? = null
        var attributeValueXList: List<AttributeValutXList>? = null
    }

    class PriceRange {
        var attribute_name: String? = null
        var attributeValueXList: List<AttributeValutXList>? = null
    }

    class Cate {
        var A: List<String>? = null
        var B: List<String>? = null
        var C: List<String>? = null
        var D: List<String>? = null
        var E: List<String>? = null
        var F: List<String>? = null
        var G: List<String>? = null
        var H: List<String>? = null
        var I: List<String>? = null
        var J: List<String>? = null
        var K: List<String>? = null
        var L: List<String>? = null
        var M: List<String>? = null
        var N: List<String>? = null
        var O: List<String>? = null
        var P: List<String>? = null
        var Q: List<String>? = null
        var R: List<String>? = null
        var S: List<String>? = null
        var T: List<String>? = null
        var U: List<String>? = null
        var V: List<String>? = null
        var W: List<String>? = null
        var X: List<String>? = null
        var Y: List<String>? = null
        var Z: List<String>? = null


    }
}

