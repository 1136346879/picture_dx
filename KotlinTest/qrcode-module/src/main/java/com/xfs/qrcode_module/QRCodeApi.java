package com.xfs.qrcode_module;

/**
 * 扫一扫模块网络请求接口
 *
 * @author yangyi 2018年8月7日14:18:20
 */

public class QRCodeApi  {

    private static String qr_recommend_content_host = "http://testnwapi.hexun.com/new";
    private static String check_app_key_host = "http://mtest.hexun.com/api";

    static {
//        if (HeContext.IS_DEBUG) {
//            qr_recommend_content_host = "http://testnwapi.hexun.com/new";
//            check_app_key_host = "http://mtest.hexun.com/api";
//        }
    }

    private static final String QR_RECOMMEND_CONTENT_HOST_URL = qr_recommend_content_host +
            "/Scan_newsInfo.cc";
    private static final String CHECK_APP_KEY_HOST_URL = check_app_key_host +
            "/appCheck";

    /**
     * 获取扫码后推荐内容的接口
     * <p>
     * http://testnwapi.hexun.com/new/Scan_newsInfo.cc?newsId=193692028&status=1
     * <p>
     * status：1  扫码成功  0  扫码失败
     * newsId 新闻ID
     * 扫码成功时newsId为必传参数
     */
//    public static Observable<ApiResult<QRCodeRecommendContent>> getQRCodeRecommendContent(
//            String newsId, String status) {
//        return OkGo.<ApiResult<QRCodeRecommendContent>>get(QR_RECOMMEND_CONTENT_HOST_URL)
//                .params("newsId", newsId)
//                .params("status", status)
//                .converter(new ApiResultConverter<QRCodeRecommendContent>(
//                        new TypeToken<QRCodeRecommendContent>() {
//                        }.getType()
//                ))
//                .adapt(new ObservableResponse<ApiResult<QRCodeRecommendContent>>())
//                .compose(RxComposer.<QRCodeRecommendContent>handleCacheError());
//    }

    /**
     * 校验APP 用户key
     * 测试地址：http://mtest.hexun.com/api/appCheck?key=JTE1533621019414
     * 传入参数： key
     * <p>
     * 返回参数：{"status":1"code":"ok"}
     * <p>
     * status：1:执行成功  0:程序异常，或key不存在
     * <p>
     * code： ok 执行成功
     * <p>
     * notExist  key不存在
     * <p>
     * failure   执行失败
     */
//    public static Observable<Response<String>> checkAppQRCodeKey(String key) {
//        return OkGo.<String>get(CHECK_APP_KEY_HOST_URL)
//                .params("key", key)
//                .converter(new StringConvert())
//                .adapt(new ObservableResponse<String>());
//    }


}
