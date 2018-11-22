package com.xfs.fsyuncai.entity.imagesearch

/**
 * 图片搜索-返回结果
 */
class ImageSearchData {
    /**
     * searchResult : {"recordCount":2,"brandlogo":null,"branddescription":null,"spuList":[{"spu_id":145,"spu_name":"PVC加长直接","skuNum":4,"title":"抗压力强，不怕受压破裂，能承受高电压而不被击穿。","category_name1":"电工电料","brand":"其它","cityName":"廊坊","spuimages":["https://fsyuncai.oss-cn-beijing.aliyuncs.com/product/images/043699a.jpg?x-oss-process=style/marking_text"],"priceRange":"¥0.10~¥6.70"},{"spu_id":8722,"spu_name":"金桥 二氧化碳气体保护焊丝70S-6","skuNum":4,"title":"焊接稳定，不氧化，防电弧","category_name1":"焊接设备、工具及耗材","brand":"金桥","cityName":"东丽","spuimages":["https://fsyuncai.oss-cn-beijing.aliyuncs.com/product/images/047066a.jpg?x-oss-process=style/marking_text"],"priceRange":"¥51.60~¥281.00"}],"itemCode":0}
     * curPage : 1
     * totalPages : 1
     * errorCode : 0
     */

    var searchResult: SearchResultBean? = null
    var curPage: Int = 0
    var totalPages: Int = 0
    var errorCode: Int = 0
    var errorMessage: String? = null

    class SearchResultBean {
        /**
         * recordCount : 2
         * brandlogo : null
         * branddescription : null
         * spuList : [{"spu_id":145,"spu_name":"PVC加长直接","skuNum":4,"title":"抗压力强，不怕受压破裂，能承受高电压而不被击穿。","category_name1":"电工电料","brand":"其它","cityName":"廊坊","spuimages":["https://fsyuncai.oss-cn-beijing.aliyuncs.com/product/images/043699a.jpg?x-oss-process=style/marking_text"],"priceRange":"¥0.10~¥6.70"},{"spu_id":8722,"spu_name":"金桥 二氧化碳气体保护焊丝70S-6","skuNum":4,"title":"焊接稳定，不氧化，防电弧","category_name1":"焊接设备、工具及耗材","brand":"金桥","cityName":"东丽","spuimages":["https://fsyuncai.oss-cn-beijing.aliyuncs.com/product/images/047066a.jpg?x-oss-process=style/marking_text"],"priceRange":"¥51.60~¥281.00"}]
         * itemCode : 0
         */

        var recordCount: Int = 0
        var brandlogo: Any? = null
        var branddescription: Any? = null
        var itemCode: Int = 0
        var spuList: List<SpuListBean>? = null

        class SpuListBean {
            /**
             * spu_id : 145
             * spu_name : PVC加长直接
             * skuNum : 4
             * title : 抗压力强，不怕受压破裂，能承受高电压而不被击穿。
             * category_name1 : 电工电料
             * brand : 其它
             * cityName : 廊坊
             * spuimages : ["https://fsyuncai.oss-cn-beijing.aliyuncs.com/product/images/043699a.jpg?x-oss-process=style/marking_text"]
             * priceRange : ¥0.10~¥6.70
             */

            var spu_id: Int = 0
            var spu_name: String? = null
            var skuNum: Int = 0
            var title: String? = null
            var category_name1: String? = null
            var brand: String? = null
            var cityName: String? = null
            var priceRange: String? = null
            var spuimages: List<String>? = null
        }
    }
}