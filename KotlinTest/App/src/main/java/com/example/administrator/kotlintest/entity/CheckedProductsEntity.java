package com.example.administrator.kotlintest.entity;

import java.util.List;

public class CheckedProductsEntity {


    /**
     * abnormalProducts : [{"code":"73","skuCode":"91001282001","skuId":"4680","skuName":"玻璃水2L","tip":"没有宽度信息没有高度信息"}]
     * errorCode : 61
     * skuModelList : [{"arrivalCycle":"3","buyyerCount":"1","cartId":"862","categoryCode":"16","categoryDiscountRate":"1","categoryId":"533","color":"","costPrice":"2.67","helpcode":"BLS2L-25C","high":"","length":"1.0","miniOrder":"1.0","orderLimitType":"0","origin":"朝阳","originalPrice":"6.30","partsNumber":"12瓶/件","platDiscountRate":"1","productCode":"06.11.11.045393","productName":"玻璃水2L","productPic":"https://fsyuncai.oss-cn-beijing.aliyuncs.com/product/images/045393a.jpg?x-oss-process=style/marking_text","saleOrNot":"1","salePrice":"6.30","salesManagerId":"0","skuCode":"91001282001","skuId":"4680","skuInfo":"-25°C","skuName":"玻璃水2L","skuWeight":"1.0","specifications":"-25°C","spuId":"1282","srcSalePrice":"6.30","status":"1","stockNum":"118","tempPurchase":"0","unitName":"瓶","virtualStock":"9999999","volume":"1.0","warehouseId":"1","warehouseName":"","width":""},{"arrivalCycle":"3","buyyerCount":"1","cartId":"861","categoryCode":"17","categoryDiscountRate":"1","categoryId":"571","color":"","costPrice":"28.38","helpcode":"GSGB2.44M*1.22M*8MM","high":"0.800000011920929","length":"244.0","miniOrder":"1.0","orderLimitType":"0","origin":"唐山","originalPrice":"44.80","partsNumber":"90张/件","platDiscountRate":"1","productCode":"07.01.08.056911","productName":"硅酸钙板","productPic":"https://fsyuncai.oss-cn-beijing.aliyuncs.com/product/images/056912a.jpg?x-oss-process=style/marking_text","saleOrNot":"1","salePrice":"44.80","salesManagerId":"0","skuCode":"91008473003","skuId":"37263","skuInfo":"2.44m*1.22m*8mm","skuName":"硅酸钙板","skuWeight":"40.0","specifications":"2.44m*1.22m*8mm","spuId":"8473","srcSalePrice":"44.80","status":"1","stockNum":"227","tempPurchase":"0","unitName":"张","virtualStock":"9999999","volume":"0.023809999227523804","warehouseId":"1","warehouseName":"","width":"122.0"}]
     */

    private String errorCode;
    private List<AbnormalProductsBean> abnormalProducts;
    private List<SkuModelListBean> skuModelList;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public List<AbnormalProductsBean> getAbnormalProducts() {
        return abnormalProducts;
    }

    public void setAbnormalProducts(List<AbnormalProductsBean> abnormalProducts) {
        this.abnormalProducts = abnormalProducts;
    }

    public List<SkuModelListBean> getSkuModelList() {
        return skuModelList;
    }

    public void setSkuModelList(List<SkuModelListBean> skuModelList) {
        this.skuModelList = skuModelList;
    }

    public static class AbnormalProductsBean {
        /**
         * code : 73
         * skuCode : 91001282001
         * skuId : 4680
         * skuName : 玻璃水2L
         * tip : 没有宽度信息没有高度信息
         */

        private String code;
        private String skuCode;
        private String skuId;
        private String skuName;
        private String tip;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }

    public static class SkuModelListBean {
        /**
         * arrivalCycle : 3
         * buyyerCount : 1
         * cartId : 862
         * categoryCode : 16
         * categoryDiscountRate : 1
         * categoryId : 533
         * color :
         * costPrice : 2.67
         * helpcode : BLS2L-25C
         * high :
         * length : 1.0
         * miniOrder : 1.0
         * orderLimitType : 0
         * origin : 朝阳
         * originalPrice : 6.30
         * partsNumber : 12瓶/件
         * platDiscountRate : 1
         * productCode : 06.11.11.045393
         * productName : 玻璃水2L
         * productPic : https://fsyuncai.oss-cn-beijing.aliyuncs.com/product/images/045393a.jpg?x-oss-process=style/marking_text
         * saleOrNot : 1
         * salePrice : 6.30
         * salesManagerId : 0
         * skuCode : 91001282001
         * skuId : 4680
         * skuInfo : -25°C
         * skuName : 玻璃水2L
         * skuWeight : 1.0
         * specifications : -25°C
         * spuId : 1282
         * srcSalePrice : 6.30
         * status : 1
         * stockNum : 118
         * tempPurchase : 0
         * unitName : 瓶
         * virtualStock : 9999999
         * volume : 1.0
         * warehouseId : 1
         * warehouseName :
         * width :
         */

        private String arrivalCycle;
        private String buyyerCount;
        private String cartId;
        private String categoryCode;
        private String categoryDiscountRate;
        private String categoryId;
        private String color;
        private String costPrice;
        private String helpcode;
        private String high;
        private String length;
        private String miniOrder;
        private String orderLimitType;
        private String origin;
        private String originalPrice;
        private String partsNumber;
        private String platDiscountRate;
        private String productCode;
        private String productName;
        private String productPic;
        private String saleOrNot;
        private String salePrice;
        private String salesManagerId;
        private String skuCode;
        private String skuId;
        private String skuInfo;
        private String skuName;
        private String skuWeight;
        private String specifications;
        private String spuId;
        private String srcSalePrice;
        private String status;
        private String stockNum;
        private String tempPurchase;
        private String unitName;
        private String virtualStock;
        private String volume;
        private String warehouseId;
        private String warehouseName;
        private String width;

        public String getArrivalCycle() {
            return arrivalCycle;
        }

        public void setArrivalCycle(String arrivalCycle) {
            this.arrivalCycle = arrivalCycle;
        }

        public String getBuyyerCount() {
            return buyyerCount;
        }

        public void setBuyyerCount(String buyyerCount) {
            this.buyyerCount = buyyerCount;
        }

        public String getCartId() {
            return cartId;
        }

        public void setCartId(String cartId) {
            this.cartId = cartId;
        }

        public String getCategoryCode() {
            return categoryCode;
        }

        public void setCategoryCode(String categoryCode) {
            this.categoryCode = categoryCode;
        }

        public String getCategoryDiscountRate() {
            return categoryDiscountRate;
        }

        public void setCategoryDiscountRate(String categoryDiscountRate) {
            this.categoryDiscountRate = categoryDiscountRate;
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getCostPrice() {
            return costPrice;
        }

        public void setCostPrice(String costPrice) {
            this.costPrice = costPrice;
        }

        public String getHelpcode() {
            return helpcode;
        }

        public void setHelpcode(String helpcode) {
            this.helpcode = helpcode;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getMiniOrder() {
            return miniOrder;
        }

        public void setMiniOrder(String miniOrder) {
            this.miniOrder = miniOrder;
        }

        public String getOrderLimitType() {
            return orderLimitType;
        }

        public void setOrderLimitType(String orderLimitType) {
            this.orderLimitType = orderLimitType;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(String originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getPartsNumber() {
            return partsNumber;
        }

        public void setPartsNumber(String partsNumber) {
            this.partsNumber = partsNumber;
        }

        public String getPlatDiscountRate() {
            return platDiscountRate;
        }

        public void setPlatDiscountRate(String platDiscountRate) {
            this.platDiscountRate = platDiscountRate;
        }

        public String getProductCode() {
            return productCode;
        }

        public void setProductCode(String productCode) {
            this.productCode = productCode;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getProductPic() {
            return productPic;
        }

        public void setProductPic(String productPic) {
            this.productPic = productPic;
        }

        public String getSaleOrNot() {
            return saleOrNot;
        }

        public void setSaleOrNot(String saleOrNot) {
            this.saleOrNot = saleOrNot;
        }

        public String getSalePrice() {
            return salePrice;
        }

        public void setSalePrice(String salePrice) {
            this.salePrice = salePrice;
        }

        public String getSalesManagerId() {
            return salesManagerId;
        }

        public void setSalesManagerId(String salesManagerId) {
            this.salesManagerId = salesManagerId;
        }

        public String getSkuCode() {
            return skuCode;
        }

        public void setSkuCode(String skuCode) {
            this.skuCode = skuCode;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuInfo() {
            return skuInfo;
        }

        public void setSkuInfo(String skuInfo) {
            this.skuInfo = skuInfo;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getSkuWeight() {
            return skuWeight;
        }

        public void setSkuWeight(String skuWeight) {
            this.skuWeight = skuWeight;
        }

        public String getSpecifications() {
            return specifications;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

        public String getSpuId() {
            return spuId;
        }

        public void setSpuId(String spuId) {
            this.spuId = spuId;
        }

        public String getSrcSalePrice() {
            return srcSalePrice;
        }

        public void setSrcSalePrice(String srcSalePrice) {
            this.srcSalePrice = srcSalePrice;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStockNum() {
            return stockNum;
        }

        public void setStockNum(String stockNum) {
            this.stockNum = stockNum;
        }

        public String getTempPurchase() {
            return tempPurchase;
        }

        public void setTempPurchase(String tempPurchase) {
            this.tempPurchase = tempPurchase;
        }

        public String getUnitName() {
            return unitName;
        }

        public void setUnitName(String unitName) {
            this.unitName = unitName;
        }

        public String getVirtualStock() {
            return virtualStock;
        }

        public void setVirtualStock(String virtualStock) {
            this.virtualStock = virtualStock;
        }

        public String getVolume() {
            return volume;
        }

        public void setVolume(String volume) {
            this.volume = volume;
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }
    }
}
