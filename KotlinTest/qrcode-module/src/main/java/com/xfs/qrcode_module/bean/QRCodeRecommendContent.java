package com.xfs.qrcode_module.bean;


import java.util.List;

/**
 * @author yangyi 2018年8月7日14:23:50
 */

public class QRCodeRecommendContent {


    /**
     * id : 193692028
     * news : [{"abstract":"","commentNum":0,"id":193668145,"img":"http://minimg.hexun.com/i2.hexun.com/2015-04-29/175398956_c162x117.jpg","lineImg":"","lineType":1,"media":"和讯网","subtype":1,"time":"2018-08-03 08:21:19","title":"基本面未改善 白糖盘面继续承压 ","url":""},{"abstract":"","commentNum":0,"id":193641987,"img":"http://minimg.hexun.com/i6.hexun.com/2015-11-17/180626129_c162x117.jpg","lineImg":"","lineType":1,"media":"和讯网","subtype":1,"time":"2018-08-01 08:13:17","title":"技术派与基本面派孰高孰低 ","url":""},{"abstract":"","commentNum":0,"id":193618300,"img":"http://minimg.hexun.com/i2.hexun.com/2015-11-17/180626143_c162x117.jpg","lineImg":"","lineType":1,"media":"和讯网","subtype":1,"time":"2018-07-31 08:21:12","title":"基本面支撑 期债易涨难跌 ","url":""},{"abstract":"","commentNum":"0","id":193273006,"img":"http://minimg.hexun.com/i5.hexun.com/2018-06-26/193273005_c162x117.png","lineImg":"","lineType":"1","media":"参考消息","subtype":"1","time":"2018-06-26 11:28:57","title":"参考消息：A股抗得过长期贸易战 资产质量估值等优越","url":""},{"abstract":"","commentNum":"0","id":193272951,"img":"http://minimg.hexun.com/i8.hexun.com/2014-09-02/168105334_c162x117.jpg","lineImg":"","lineType":"1","media":"腾讯财经","subtype":"1","time":"2018-06-26 11:22:48","title":"中兴新董事长浮出水面 西安微电子党委书记将出任","url":""}]
     * title : 鱼和熊掌的问题：论基本面和技术面的选择
     */

    private String id;
    private String title;
    private List<NewsBean> news;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<NewsBean> getNews() {
        return news;
    }

    public void setNews(List<NewsBean> news) {
        this.news = news;
    }

    public static class NewsBean {
        /**
         * abstract :
         * commentNum : 0
         * id : 193668145
         * img : http://minimg.hexun.com/i2.hexun.com/2015-04-29/175398956_c162x117.jpg
         * lineImg :
         * lineType : 1
         * media : 和讯网
         * subtype : 1
         * time : 2018-08-03 08:21:19
         * title : 基本面未改善 白糖盘面继续承压
         * url :
         */

//        @SerializedName("abstract")
        private String abstractX;
        private int commentNum;
        private int id;
        private String img;
        private String lineImg;
        private int lineType;
        private String media;
        private int subtype;
        private String time;
        private String title;
        private String url;

        public String getAbstractX() {
            return abstractX;
        }

        public void setAbstractX(String abstractX) {
            this.abstractX = abstractX;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLineImg() {
            return lineImg;
        }

        public void setLineImg(String lineImg) {
            this.lineImg = lineImg;
        }

        public int getLineType() {
            return lineType;
        }

        public void setLineType(int lineType) {
            this.lineType = lineType;
        }

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        public int getSubtype() {
            return subtype;
        }

        public void setSubtype(int subtype) {
            this.subtype = subtype;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
