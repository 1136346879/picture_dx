package com.xfs.qrcode_module.recycleview;

import com.google.gson.annotations.SerializedName;

import java.util.List;


/**
 * Created by ZHANG PEIYUAN on 2017/10/31.
 * Email: zhangpeiyuan@hexun.staff.com
 *
 * @author ZHANG PEIYUAN
 *         <p>
 * @author yangyi updated on 2017年11月8日14:30:50
 *         <p>
 *         注意: 字段上方带***的为老版本的财经数据库中也存在的字段，在新版本中仍需要沿用。
 *         老版本对应的收藏列表的数据表的详情如下：
 *         <p>
 *         Table [mysave_news_detail_db]
 *         Fields: 16
 *         [_id]: INTEGER
 *         [newsID]: text
 *         [url]: text
 *         [date]: text
 *         [title]: text
 *         [media]: text
 *         [mediaid]: text
 *         [content]: text
 *         [abstract]: text
 *         [subtitle]: text
 *         [subtype]: text
 *         [author]: text
 *         [relatednews]: text
 *         [picture]: text
 *         [video]: text
 *         [mvideo]: text
 *         Indexes: 0
 *         Triggers: 0
 *         Table constraints:
 *         Primary Key:
 *         Fields: [_id]
 *         On Conflict:
 *         Foreign Keys: 0
 *         Unique constraints: 0
 *         Check constraints: 0
 *         Table [mysave_news_detail_db] end
 */

//@Entity
public class NewsInfo {

    @SerializedName("abstract")
    private String summary;

    private int commentNum;

    /**
     * [newsID]: text
     */
//    @Id(assignable = true)
    @SerializedName("id")
    private long newsId;

    /**
     * [picture]: text
     */
    private String img;

    private String lineImg;

    private int lineType;

    /**
     * [media]: text
     */
    private String media;

    private String subtype;

    /**
     * [date]: text  新闻发布的时间
     */
    private String time;

    /**
     * [title]: text
     */
    private String title;

    /**
     * [url]: text
     */
    @SerializedName(value = "url", alternate = {"URL"})
    private String url;

    /**
     * 添加收藏的时间
     */
    private long addTime;

    private String channel;
    /**
     * 原创新闻关键字
     */
    private String keyword;
    /**
     * 广告内外链标识
     * 0 内链 1外链
     */
    private String isOut;
//    @Transient
//    private List<HotTopicInfo> topics;

//    public void setTopics(List<HotTopicInfo> topics) {
//        this.topics = topics;
//    }

//    public List<HotTopicInfo> getTopics() {
//        return topics;
//    }

    public String getIsOut() {
        return isOut;
    }

    public void setIsOut(String isOut) {
        this.isOut = isOut;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public long getNewsId() {
        return newsId;
    }

    public void setNewsId(long newsId) {
        this.newsId = newsId;
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

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
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


    public long getAddTime() {
        return addTime;
    }

    public void setAddTime(long addTime) {
        this.addTime = addTime;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "summary='" + summary + '\'' +
                ", commentNum=" + commentNum +
                ", newsId=" + newsId +
                ", img='" + img + '\'' +
                ", lineImg='" + lineImg + '\'' +
                ", lineType=" + lineType +
                ", media='" + media + '\'' +
                ", subtype='" + subtype + '\'' +
                ", time='" + time + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", addTime=" + addTime +
                ", channel='" + channel + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
