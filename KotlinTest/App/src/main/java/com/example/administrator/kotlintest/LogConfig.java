package com.example.administrator.kotlintest;

import android.app.Application;

import com.blankj.ALog;

/**
 * Created by hexun on 2017/10/11.
 * @author ZHANG PEIYUAN
 */

public class LogConfig {


    /**
     * LOG是否打开
     */
    private static boolean log_switch = true;
    /**
     * 是否输出日至到Console
     */
    private static boolean console_switch = true;
    /**
     * 是否输出日至到File
     */
    private static boolean file_switch = false;
    /**
     * 是否显示LOG头信息
     */
    private static boolean show_log_head = true;
    /**
     * 是否显示日志边框
     */
    private static boolean border_switch = false;
    /**
     * 当自定义路径为空时，写入应用的/cache/log/目录中
     */
    private static String file_path = "";
    /**
     * 当文件前缀为空时，默认为"alog"，即写入文件为"alog-MM-dd.txt"
     */
    private static String file_prefix = "";
    /**
     * log的控制台过滤器，和logcat过滤器同理，默认Verbose
     */
    private static int log_console_filter = ALog.V;
    /**
     * log文件过滤器，和logcat过滤器同理，默认Verbose
     */
    private static int log_file_filter = ALog.V;
    /**
     * log栈深度，默认为1
     */
    private static int log_stack_deep = 1;


    private LogConfig() {
    }


    public static void initLog(Application context){
        ALog.init(context)
                .setLogSwitch(log_switch)
                .setConsoleSwitch(console_switch)
                .setLogHeadSwitch(show_log_head)
                .setLog2FileSwitch(file_switch)
                .setDir(file_path)
                .setFilePrefix(file_prefix)
                .setBorderSwitch(border_switch)
                .setConsoleFilter(log_console_filter)
                .setFileFilter(log_file_filter)
                .setStackDeep(log_stack_deep);
    }


}
