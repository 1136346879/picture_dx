package com.example.administrator.kotlintest.picture;

public enum PictureType {

    /**
     * style50   宽高50px
     * style60   宽高60px
     * style150  宽高150px
     * style200  宽高200px
     * style350  宽高350px
     * marking_text   原图尺寸（带水印）现接口返回的
     */
    style50("style50"),
    style60("style60"),
    style150("style150"), //小列表
    style200("style200"),//商品列表 ，搜索列表
    style350("style350"),
    marking_text("marking_text");//默认图片
    private String pictureTypeName;

    PictureType(String pictureTypeName) {
        this.pictureTypeName = pictureTypeName;
    }

    public String getPictureTypeName() {
        return pictureTypeName;
    }
}
