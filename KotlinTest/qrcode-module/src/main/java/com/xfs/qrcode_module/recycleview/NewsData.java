package com.xfs.qrcode_module.recycleview;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ZHANG PEIYUAN on 2017/10/31.
 * Email: zhangpeiyuan@hexun.staff.com
 * @author ZHANG PEIYUAN
 */

public class NewsData {

    private Focus focus;

    private List<NewsInfo> news;


    public Focus getFocus() {
        return focus;
    }

    public void setFocus(Focus focus) {
        this.focus = focus;
    }

    public List<NewsInfo> getNews() {
        return news;
    }

    public void setNews(List<NewsInfo> news) {
        this.news = news;
    }

    public class Focus{
        List<Frame> frame;

        public List<Frame> getFrame() {
            return frame;
        }

        public void setFrame(List<Frame> frame) {
            this.frame = frame;
        }
    }


    public class Frame{

        //isnews 0:广告;1:新闻;2:组图;3:直播
        //isout 0:内链;1:外链
        //本版本只保留外链广告和新闻


        private long id;

        private String img;

        @SerializedName("isnews")
        private int isNews;

        @SerializedName("isout")
        private int isOut;

        private String pic;

        @SerializedName("pics_abstract")
        private String picsAbstract;

        private String platform;

        private String subtype;

        private String title;

        private String url;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getIsNews() {
            return isNews;
        }

        public void setIsNews(int isNews) {
            this.isNews = isNews;
        }

        public int getIsOut() {
            return isOut;
        }

        public void setIsOut(int isOut) {
            this.isOut = isOut;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getPicsAbstract() {
            return picsAbstract;
        }

        public void setPicsAbstract(String picsAbstract) {
            this.picsAbstract = picsAbstract;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public String getSubtype() {
            return subtype;
        }

        public void setSubtype(String subtype) {
            this.subtype = subtype;
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
