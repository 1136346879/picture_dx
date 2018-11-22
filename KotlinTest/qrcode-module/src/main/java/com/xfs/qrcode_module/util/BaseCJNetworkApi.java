package com.xfs.qrcode_module.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;

import com.hexun.base.http.BaseNetworkApi;
import com.hexun.base.utils.HLog;
import com.hexun.base.utils.HUtils;

import java.util.HashMap;

import okhttp3.Headers;

/**
 * Created by hexun on 2017/12/28.
 */

public class BaseCJNetworkApi extends BaseNetworkApi {

    private static Headers headers;

    @Override
    protected Headers addDefaultHeaders(Context context) {
        if(headers == null) {
            HashMap<String, String> header = new HashMap();
            header.put("AppVersion", getAppVersion(context));
            header.put("deviceId", HUtils.getDeviceId(context));
            header.put("User-Agent", "HeXun/" + HUtils.getAppVersion(context) + " Android/" + Build.VERSION.RELEASE + " (" + Build.MODEL + ")");
            headers = Headers.of(header);
        }

        return headers;
    }

    protected String getAppVersion(Context context) {
        if(context == null) {
            throw new IllegalArgumentException("context is null!");
        } else {
            PackageManager packageManager = context.getPackageManager();
            String versionName = null;

            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
                versionName = packageInfo.versionName;

                //版本名称过滤掉非数字字符
                if (!TextUtils.isEmpty(versionName)) {
                    final int len = versionName.length();
                    StringBuilder vn = new StringBuilder();
                    char ch;
                    for (int i = 0; i < len; i++) {
                        ch = versionName.charAt(i);
                        if (Character.isDigit(ch)) {
                            vn.append(ch);
                        } else if (vn.length() > 0 && Character.isDigit(vn.charAt(vn.length() - 1))) {
                            vn.append('.');
                        }
                    }
                    if (!Character.isDigit(vn.charAt(vn.length() - 1))) {
                        vn.deleteCharAt(vn.length() - 1);
                    }
                    versionName = vn.toString();
                }
            } catch (PackageManager.NameNotFoundException var4) {
                var4.printStackTrace();
            }

            HLog.d("HUtils", "getAppVersion:" + versionName);
            return versionName == null?"":versionName;
        }
    }

}
