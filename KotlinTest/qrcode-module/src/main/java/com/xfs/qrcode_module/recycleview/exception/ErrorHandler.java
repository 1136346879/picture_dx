package com.xfs.qrcode_module.recycleview.exception;

import android.app.Application;
import android.widget.Toast;


/**
 * Created by hexun on 2017/10/19.
 * @author ZHANG PEIYUAN
 */

public class ErrorHandler {

    private static ErrorHandler errorHandler;
    private static Application app;

    public static final int NULL_DATA = 10000;
    public static final int PARSE_DATA_EXCEPTION = 10001;
    public static final int NET_EXCEPTION = 10002;
    public static final int UNKNOWN_EXCEPTION = 10003;

    private ErrorHandler(Application application){
        app = application;
    }

    public static void init(Application application){
        if (errorHandler == null){
            errorHandler = new ErrorHandler(application);
        }
    }


    public static void showNetWorkError(String message){
        Toast.makeText(app,message, Toast.LENGTH_SHORT).show();
    }


}
