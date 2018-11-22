package com.xfs.fsyuncai.entity.imagesearch

class ImageSearchRequestBody {
    /**
     * platformType : 2
     * wareHouseId : 1
     * cityId : 1
     * rows : 50
     * imgList : ["043699a.jpg","047066a.jpg","043699e.jpg"]
     */

     var platformType: Int = 0
     var wareHouseId: Int = 0
     var cityId: Int = 0
     var rows: Int = 0
     var imgList: List<String>? = null
}