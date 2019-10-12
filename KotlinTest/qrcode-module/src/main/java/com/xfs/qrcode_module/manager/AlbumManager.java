package com.xfs.qrcode_module.manager;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Looper;
import androidx.fragment.app.Fragment;
import android.widget.Toast;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.xfs.qrcode_module.util.PermissionUtil;
import com.xfs.qrcode_module.util.RequestConstant;

import java.util.Hashtable;

/**
 * @author yangyi 2018年08月05日01:30:56
 *         <p>
 *         wechat: yangyi_451686712
 */
public class AlbumManager {

    private static AlbumManager albumManager;
    private String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static synchronized AlbumManager getInstance() {
        if (albumManager == null) {
            albumManager = new AlbumManager();
        }
        return albumManager;
    }

    private AlbumManager() {

    }

    public void openAlbum(final Activity activity) {
        PermissionUtil.requestPermissions(activity,
                RequestConstant.REQUEST_CODE_ALBUM,
                permissions,
                new PermissionUtil.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        gotoAlbum(activity);
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        Toast.makeText(activity,
                                "请给予权限，否则无法使用", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void gotoAlbum(Activity activity) {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");
        activity.startActivityForResult(wrapperIntent, RequestConstant.REQUEST_CODE_ALBUM);
    }

    public void openAlbum(final Fragment fragment) {
        PermissionUtil.requestPermissions(fragment.getContext(),
                RequestConstant.REQUEST_CODE_ALBUM,
                permissions,
                new PermissionUtil.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        gotoAlbum(fragment);
                    }

                    @Override
                    public void onPermissionDenied(String[] deniedPermissions) {
                        Toast.makeText(fragment.getContext(),
                                "请给予权限，否则无法使用", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void gotoAlbum(Fragment fragment) {
        Intent innerIntent = new Intent(Intent.ACTION_GET_CONTENT);
        innerIntent.setType("image/*");
        Intent wrapperIntent = Intent.createChooser(innerIntent, "选择二维码图片");
        fragment.startActivityForResult(wrapperIntent, RequestConstant.REQUEST_CODE_ALBUM);
    }

    private Result scanningImage(Context context, Uri path) {
        if (path == null || path.equals("")) {
            return null;
        }
        // DecodeHintType 和EncodeHintType
        Hashtable<DecodeHintType, String> hints = new Hashtable<>();
        hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
        try {
            Bitmap scanBitmap = BitmapFactory.decodeStream(
                    context.getContentResolver().openInputStream(path));
            int width = scanBitmap.getWidth();
            int height = scanBitmap.getHeight();
            int[] pixels = new int[width * height];
            scanBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
            BinaryBitmap bitmap1 = new BinaryBitmap(new HybridBinarizer(source));
            QRCodeReader reader = new QRCodeReader();
            return reader.decode(bitmap1, hints);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void onActivityResult(final Context context,
                                 int requestCode,
                                 final Intent data) {
        if (requestCode == RequestConstant.REQUEST_CODE_ALBUM) {
            if (data == null) {
                return;
            }
            ThreadPoolManager.getInstance().submitToPool(new Runnable() {
                @Override
                public void run() {
                    Result result = AlbumManager.getInstance().scanningImage(
                            context, data.getData());
                    if (result == null) {
                        Looper.prepare();
                        Toast.makeText(context, "图片信息有误", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    } else {
                        String recode = result.getText();
                        Looper.prepare();
                        Toast.makeText(context, "图片内容为:" + recode, Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            });
        }
    }
}
