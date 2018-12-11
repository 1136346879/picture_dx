package com.xfs.qrcode_module.recycleview;

import com.xfs.qrcode_module.recycleview.rx.ApiResult;
import com.xfs.qrcode_module.recycleview.rx.ApiResultConverter;
import com.xfs.qrcode_module.recycleview.rx.RxComposer;
import com.google.gson.reflect.TypeToken;
import com.hexun.base.http.BaseNetworkApi;
import com.lzy.okgo.OkGo;
import com.lzy.okrx2.adapter.ObservableResponse;

import io.reactivex.Observable;

/**
 * Created by ZHANG PEIYUAN on 2017/10/25.
 * Email: zhangpeiyuan@hexun.staff.com
 */

public class NewsApi extends BaseNetworkApi {
    private static String HOST = "http://wapi.hexun.com";

    private NewsApi() {
    }

    /**
     * 线上环境
     */
    private static String NEWS_HOST = "http://bdmw.hexun.com";
    private static String COMMENT_HOST_SSL = "https://commenttool.hexun.com";
    private static String COMMENT_HOST = "http://comment.tool.hexun.com";
    private static String SEARCH_HOST = "http://newssearch.hexun.com";
    /**
     * 线上开屏广告接口host
     */
    private static String ADVERSING_HOST = "http://wapi.hexun.com";

    static {
//        if (HeContext.IS_DEBUG) {
//            //测试环境(待定)
//            HOST = "http://test.wapi.hexun.com";
//            NEWS_HOST = "http://bdmw.hexun.com";
//            COMMENT_HOST = "http://comment.tool.hexun.com";
//            SEARCH_HOST = "http://newssearch.hexun.com";
//            ADVERSING_HOST = "http://test.wapi.hexun.com";
//        }
    }

    //新闻页面
    private static final String NEWS_LIST = HOST + "/App_newsXml_v4.cc";

    //信息流页面
    private static final String RECOMMEND_LIST = NEWS_HOST + "/api/getMoreRecommend";

    //新闻搜索页面
    private static final String NEWS_SEARCH = SEARCH_HOST + "/m/newsjsondatas";

    //新闻评论
    private static final String COMMENT_LIST = COMMENT_HOST_SSL + "/Comment/GetCommentNext.do";

    //开屏广告
    private static final String ADVERTING_OPEN_SCREEN = ADVERSING_HOST + "/Api_openAdInfo.cc?";

    //历史推送
    private static final String HISTORY_PUSH = HOST + "/Api_tipsList.cc";
    //新闻详情页
    private static String NEWSDETAIL = HOST + "/Api_newsDetail.cc";
    //Banner
    private static final String BANNER = "http://wapi.hexun.com" + "/Api_getFocus.cc";
    //发表评论
    private static final String PUBLIUSH_COMMENT = COMMENT_HOST + "/Comment/PostComment.do";
    //评论点赞接口
    private static final String PRAISE_COMMENT = COMMENT_HOST_SSL + "/Comment/praise.do";
    //我的评论
    private static final String MY_COMMENTS = COMMENT_HOST + "/Comment/GetUserCaiJingComments.do";
    //博客详情页
    public static String BLOG_GET = HOST + "/Api_blogInfo.cc";

    // 用户行为上报接口
    public static final String USER_BEHAVIOR_UPLOAD = NEWS_HOST + "/uploadData/uploadUserAction?";

    /**
     * 用户反馈接口
     */
    public static final String USER_FEEDBACK = "http://wiapi.hexun.com/stock/feedbackv2.php";

    /**
     * 获取新闻信息
     *
     * @param pageSize 每页条数
     * @param pageNum  页码
     * @return
     */
    public static Observable<ApiResult<NewsData>> getNewsData(int pageSize, int pageNum) {
        return OkGo.<ApiResult<NewsData>>get(NEWS_LIST)
                .params("appId", 1)
                .params("pid", "100234721")
//                .params("pid", ChannelType.TOPNEWS.getId())
                .params("pc", pageSize)
                .params("pn", pageNum)
                .params("num", 7)
                .params("resType", "1")
                .converter(new ApiResultConverter<NewsData>(NewsData.class))
                .adapt(new ObservableResponse<ApiResult<NewsData>>())
                .compose(RxComposer.<NewsData>handleCacheError());

    }


//    /**
//     * 请求推荐数据
//     *
//     * @param pageSize 请求信息条数
//     * @param type     频道类型
//     * @return 推荐列表
//     */
//    public static Observable<ApiResult<List<RecommendInfo>>> getRecommendList(int pageSize,
//                                                                              @NonNull ChannelType type) {
//        return OkGo.<ApiResult<List<RecommendInfo>>>get(RECOMMEND_LIST)
//                //                .cacheKey("NewsList")
//                //                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
//                .params("iid", OsInfoManager.getInstance().getDeviceId())
//                .params("cnt", pageSize)
//                .params("cateid", type.getId())
//                .converter(new ApiResultConverter<List<RecommendInfo>>(new TypeToken<List<RecommendInfo>>() {
//                }.getType()))
//                .adapt(new ObservableResponse<ApiResult<List<RecommendInfo>>>())
//                .compose(RxComposer.<List<RecommendInfo>>handleCacheError());
//    }
//
//    /**
//     * 评论列表
//     *
//     * @param pageSize
//     * @param maxCommentId
//     * @param type
//     * @param articleId
//     * @param userId
//     * @return
//     */
//    public static Observable<ApiResult<CommentData>> getCommentList(int pageSize, long maxCommentId,
//                                                                    @NonNull SourceType type, long articleId,
//                                                                    @Nullable String userId) {
//        return OkGo.<ApiResult<CommentData>>get(COMMENT_LIST)
//                .params("commentsource", 2)
//                .params("articlesource", type.getId())
//                .params("articleid", articleId)
//                .params("pagesize", pageSize)
//                .params("maxcommentid", maxCommentId)
//                .params("uid", userId)
//                .converter(new ApiResultConverter<CommentData>(CommentData.class))
//                .adapt(new ObservableResponse<ApiResult<CommentData>>())
//                .compose(RxComposer.<CommentData>handleCacheError());
//    }
//
//    /**
//     * 获取历史推送
//     *
//     * @param pageSize 每页条数
//     * @param pageNum  页码
//     * @return
//     */
//    public static Observable<ApiResult<PushHistory>> getPushHistory(int pageSize, int pageNum) {
//        return OkGo.<ApiResult<PushHistory>>get(HISTORY_PUSH)
//                //                .params("appId", "1003")
//                .params("pc", pageSize)
//                .params("pn", pageNum)
//                .params("resType", "1")
//                .converter(new ApiResultConverter<PushHistory>(PushHistory.class))
//                .adapt(new ObservableResponse<ApiResult<PushHistory>>())
//                .compose(RxComposer.<PushHistory>handleCacheError());
//    }
//
//
//    public static Observable<ApiResult<List<SearchItem>>> getSearchResult(String keywords, int pageNum) {
//        return OkGo.<ApiResult<List<SearchItem>>>get(NEWS_SEARCH)
//                .params("q", keywords)
//                .params("pg", pageNum)
//                .params("s", 1)
//                .converter(new ApiResultConverter<List<SearchItem>>(new TypeToken<List<SearchItem>>() {
//                }.getType()))
//                .adapt(new ObservableResponse<ApiResult<List<SearchItem>>>())
//                .compose(RxComposer.<List<SearchItem>>handleCacheError());
//
//    }
//
//    public static Observable<ApiResult<OpenAdvertingInfo>> getOpenScreenAdverting() {
//        return OkGo.<ApiResult<OpenAdvertingInfo>>get(ADVERTING_OPEN_SCREEN)
//                .params("appId", 1)
//                .params("resType", 1)
//                .converter(new ApiResultConverter<OpenAdvertingInfo>(OpenAdvertingInfo.class))
//                .adapt(new ObservableResponse<ApiResult<OpenAdvertingInfo>>())
//                .compose(RxComposer.<OpenAdvertingInfo>handleCacheError());
//    }
//
//    /**
//     * 获取详情页
//     * "http://test.wapi.hexun.com/Api_newsDetail.cc?
//     * channel=AppStore&mos=IOS&softid=1006
//     * &softver=4.3.2&ua=iPhone&screen=414*736&userkey=28141184&switch=1
//     * &enter=1&deviceid=3C15C2DAB62C&devicemac=3C15C2DAB62C&flag=v6&resType=1"
//     *
//     * @param pid
//     * @return
//     */
//    public static Observable<ApiResult<NewsDetailEntity>> getNewsDetail(String pid, String type) {
//        String newurl;
//        if ("-1".equals(type)) {//是博客
//            newurl = BLOG_GET;
//        } else {
//            newurl = NEWSDETAIL;
//        }
//        return OkGo.<ApiResult<NewsDetailEntity>>get(newurl)
//                .params("newsId", pid)
//                .params("flag", "v6")
//                .params("resType", "1")
//                .converter(new ApiResultConverter<NewsDetailEntity>(NewsDetailEntity.class))
//                .adapt(new ObservableResponse<ApiResult<NewsDetailEntity>>())
//                .compose(RxComposer.<NewsDetailEntity>handleCacheError());
//    }
//
//    /**
//     * 大观数据上报
//     */
//    public static Observable<ApiResult<Object>> pushData(final String itemid, final String type) {
//        return OkGo.<ApiResult<Object>>get(USER_BEHAVIOR_UPLOAD)
//                .params("iid", OsInfoManager.getInstance().getDeviceId())
//                .params("itemid", itemid)
//                .params("action_type", type)
//                .converter(new ApiResultConverter<>(Object.class))
//                .adapt(new ObservableResponse<ApiResult<Object>>())
//                .compose(RxComposer.handleCacheError());
//
//    }
//
//    /**
//     * 获取信息流banner
//     *
//     * @param type
//     * @return
//     */
//    public static Observable<ApiResult<List<NewsBanner>>> getBanner(ChannelType type) {
//        return OkGo.<ApiResult<List<NewsBanner>>>get(BANNER)
//                .params("appId", 1)
//                .params("pid", type.getId())
//                .converter(new ApiResultConverter<List<NewsBanner>>(new TypeToken<List<NewsBanner>>() {
//                }.getType()))
//                .adapt(new ObservableResponse<ApiResult<List<NewsBanner>>>())
//                .compose(RxComposer.<List<NewsBanner>>handleCacheError());
//
//    }
//
//    /**
//     * 获取我的评论列表
//     *
//     * @return
//     */
//    public static Observable<ApiResult<List<MyComment>>> getMyComments(int pageNum, String userId) {
//        return OkGo.<ApiResult<List<MyComment>>>get(MY_COMMENTS)
//                .params("pagenum", pageNum)
//                .params("userid", userId)
//                .converter(new ApiResultConverter<List<MyComment>>(new TypeToken<List<MyComment>>() {
//                }.getType()))
//                .adapt(new ObservableResponse<ApiResult<List<MyComment>>>())
//                .compose(RxComposer.<List<MyComment>>handleCacheError());
//    }
//
//    /**
//     * 发表评论接口
//     *
//     * @param articleId 文章id
//     * @param comment   评论内容
//     * @param parentid  （回复id）
//     * @param url       url
//     * @param title     标题
//     * @return
//     */
//    public static Observable<ApiResult<PublishCommentData>> publishComment(String articleId, String comment, String parentid, String url, String title) {
//        return OkGo.<ApiResult<PublishCommentData>>post(PUBLIUSH_COMMENT).params("commentsource", "2")
//                .params("articlesource", "1")
//                .params("articleid", articleId)
//                .params("comment", comment)
//                .params("parentid", parentid)
//                .params("url", url)
//                .params("title", title)
//                .converter(new ApiResultConverter<PublishCommentData>(new TypeToken<PublishCommentData>() {
//                }.getType()))
//                .adapt(new ObservableResponse<ApiResult<PublishCommentData>>()).compose(RxComposer.<PublishCommentData>handleCacheError()).subscribeOn(Schedulers.io());
//    }
//
//    /**
//     * 评论点赞接口
//     *
//     * @param commented
//     * @return
//     */
//    public static Observable<ApiResult<PraiseData>> praiseComment(String commented) {
//        return OkGo.<ApiResult<PraiseData>>get(PRAISE_COMMENT)
//                .params("commentsource", "2")
//                .params("commentid", commented)
//                .converter(new ApiResultConverter<PraiseData>(new TypeToken<PraiseData>() {
//                }.getType()))
//                .adapt(new ObservableResponse<ApiResult<PraiseData>>()).compose(RxComposer.<PraiseData>handleCacheError());
//
//    }
//
//    /**
//     * 用户反馈接口
//     *
//     * @return
//     */
//    public static Observable<ApiResult<Object>> commitUserFeedBack(String content,
//                                                                   String email,
//                                                                   boolean isLogin) {
//        return OkGo.<ApiResult<Object>>get(USER_FEEDBACK)
//                .params("action", isLogin ? "addOnlineMessage" : "addLeaveMessage")
//                .params("content", content)
//                .params("productId", TrainingConst.APP_ID)
//                .params("versionName", AppUtils.getAppVersionName())
//                .params("os", "android")
//                .params("device", DeviceUtils.getManufacturer())
//                .params("deviceId", DeviceUtils.getAndroidID())
//                .params("email", email)
//                .params("questionType", "需求建议")
//                .params("res", "1")
//                .converter(new ApiResultConverter<Object>(new TypeToken<Object>() {
//                }.getType()))
//                .adapt(new ObservableResponse<ApiResult<Object>>())
//                .compose(RxComposer.handleCacheError());
//    }
}
