package com.xfs.qrcode_module.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import android.widget.Toast;

import com.xfs.qrcode_module.R;
import com.xfs.qrcode_module.activity.CaptureActivity;
import com.xfs.qrcode_module.event.ScanResultEvent;
import com.xfs.qrcode_module.ui.GrantedActivity;
import com.xfs.qrcode_module.util.ImageUtil;
import com.xfs.qrcode_module.util.LogUtil;
import com.xfs.qrcode_module.util.RequestConstant;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


/**
 * @author yangyi 2018年08月05日03:16:57
 *         <p>
 *         wechat: yangyi_451686712
 */
public class ScanManager {

    private static final String HOST = "hexun";
    private static ScanManager scanManager;
    private Bitmap bitmap;

    public static synchronized ScanManager getInstance() {
        if (scanManager == null) {
            scanManager = new ScanManager();
        }
        return scanManager;
    }

    private ScanManager() {
    }

    public void openScan(Fragment fragment) {
        Intent intent = new Intent(fragment.getContext(), CaptureActivity.class);
        fragment.startActivityForResult(intent, RequestConstant.REQUEST_CODE_SCAN);
    }

    public void openScan(Activity activity) {
        bitmap = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_launcher_foreground);

        Intent intent = new Intent(activity, CaptureActivity.class);
        activity.startActivityForResult(intent, RequestConstant.REQUEST_CODE_SCAN);
    }

    /**
     * https://i0.hexun.com/2016/app/go.html?a=频道名,时间,新闻id,扫码标识key
     * https://i0.hexun.com/2016/app/go.html?a=频道名,时间,新闻id,JTE1533621019414
     */
    public void onActivityResult(Context context, int requestCode, int resultCode, final Intent data) {
        if (requestCode == RequestConstant.REQUEST_CODE_SCAN) {
            disposeImage();
            if (resultCode == Activity.RESULT_OK && data != null) {
                String result = data.getStringExtra(RequestConstant.REQUEST_CODE_SCAN_RESULT);
//                if (HeContext.IS_DEBUG) {
                    Toast.makeText(context, "扫描结果为:" + result, Toast.LENGTH_SHORT).show();
//                }
                Uri uri = Uri.parse(result);
                String host = uri.getHost();
                //判断host是否是和讯的
                if (host != null && !"".equals(host) && host.contains(HOST)) {
                    String paramString = uri.getQueryParameter("a");
                    String[] params = paramString.split(",");
                    ScanResultEvent scanResultEvent;
                    if (params != null) {
                        scanResultEvent = new ScanResultEvent(1);
                        scanResultEvent.setParams(params);
                    } else {
                        scanResultEvent = new ScanResultEvent(0);
                        scanResultEvent.setParams(params);
                    }
                    GrantedActivity.gotoGrantedActivity(context, scanResultEvent);
                } else {
//                    if (HeContext.IS_DEBUG) {
                        Toast.makeText(context, "扫描失败了，不是和讯看全文", Toast.LENGTH_SHORT).show();
//                    }
//                    GrantedActivity.gotoGrantedActivity(context, new ScanResultEvent(0));
                }
            } else {
//                if (HeContext.IS_DEBUG) {
                    Toast.makeText(context, "扫描失败了，二维码信息不对", Toast.LENGTH_SHORT).show();
//                }
            }
        }
    }

    private void disposeImage() {
        Observable.create(new ObservableOnSubscribe<Bitmap>() {
            @Override
            public void subscribe(ObservableEmitter<Bitmap> e) throws Exception {
                try {
                    //虚化和旋转一下 (因为图片会发生旋转，因此要对图片进行旋转到和手机在一个方向上)
                    Bitmap blurBitmap = ImageUtil.stackBlur(
//                            ImageUtil.rotate(PreviewCallback.previewBitmap, 90, 0, 0),
                            ImageUtil.rotate(bitmap, 90, 0, 0),
                            50,
                            false);
                    //灰度一下
                    Bitmap grayBitmap = ImageUtil.toGray(blurBitmap);
                    e.onNext(grayBitmap);
                } catch (OutOfMemoryError error) {
                    e.onError(error);
                }
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
//                        EventBus.getDefault().postSticky(new ScanResultEvent(bitmap));
                        LogUtil.e("扫描浏览成功图片");
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.e(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
