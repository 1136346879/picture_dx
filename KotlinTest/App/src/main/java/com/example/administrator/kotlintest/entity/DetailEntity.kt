package com.xfs.fsyuncai.entity

/**
 * Created by kangf on 2018/6/27.
 */

data class DetailResultEntity(
        var data: List<DetailEntity>?,
        var color: List<String>,
        var errorCode: Int,
        var sectePrice: SectePrice,
        var specifications: List<String>,
        var is_exist_sku:Int
)

data class SectePrice(
        var max_price: String,
        var min_price: String
)

data class DetailEntity(
        var volume_small: String,
        var guarantee_period: String,
        var specifications: String,
        var retail_price: String,
        var salesman_id: String,
        var mini_order: Int?,
        var platform_type: Int,
        var number_decimal: Int,
        var price: String,
        var sku_name: String,
        var model: String,
        var spu_id: Int,
        var last_shelf_time: String,
        var max_length: Double,
        var unit_large: String,
        var is_exist_sku: Int,
        var images: List<Image>?,
        var virtual_stock: Int,
        var max_length_small: String,
        var sub_length_large: String,
        var specify_price: String,
        var weight: Double,
        var sku_id: Int,
        var brand_name: String,
        var number_large: String,
        var brand_id: Int,
        var standards: String,
        var sub_length: Double,
        var volume: Double,
        var number_redium: String,
        var unit: String,
        var name: String,
        var sku_code: String,
        var sub_length_small: String,
        var number_small: String,
        var min_length_redium: String,
        var color: String,
        var min_length_small: String,
        var liability_period: String,
        var unit_small: String,
        var origin: String,
        var pack_list: String,
        var weight_large: String,
        var product_code: String,
        var actual_stock: Int,
        var order_limit_type: Int?,
        var max_length_redium: String,
        var unit_redium: String,
        var sales_status: Int,
        var weight_redium: String,
        var category_id: Int,
        var sale_attri_value: String,
        var subtitile: String,
        var helpcode: String,
        var unit_id: String,
        var revenue_code: String,
        var arrival_cycle: Int,
        var cost_price: String,
        var min_length: Double,
        var weight_small: String,
        var is_specify_price: Int,
        var volume_large: String,
        var sub_length_redium: String,
        var sale_price: String?,
        var mini_buy_count: String,
        var categroyNames: List<String>,
        var sequence_number: String,
        var categoryIds: Int,
        var restricted: Int,
        var max_length_large: String,
        var min_length_large: String,
        var international_barcode_ean: String,
        var volume_redium: String,
        var warehouse_id: String,
        var alias: List<Alias>,
        var priceRange:String,
        var selectCount: Int
)

data class Image(
        var img_type: String,
        var spu_code: String,
        var img_code: String,
        var last_modified_date: String,
        var img_id: Int,
        var creation_date: String,
        var last_modified_by: Int,
        var img_sort: Int,
        var creation_by: String,
        var img_name: String,
        var img_url: String,
        var spu_img_id: Int,
        var spu_id: Int,
        var status: Int
)

data class Alias(var alias_value: String)