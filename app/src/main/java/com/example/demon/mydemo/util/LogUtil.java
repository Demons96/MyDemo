package com.example.demon.mydemo.util;

import android.util.Log;

/**
 * Created by gyp52 on 17/11/6.
 * 自定义打印工具
 */

public class LogUtil {
    private static final int VERBOSE = 1;   //最为繁琐，意义最小的
    private static final int DEBUG = 2;     //打印调试信息
    private static final int INFO = 3;      //比较重要的信息
    private static final int WARN = 4;      //警告信息
    private static final int ERROR = 5;     //错误信息
    private static final int NOTHING = 6;   //什么都不打印

    private static int level = DEBUG;      //当前程序的显示级别

    public static void v(String tag, String msg) {
        if (level <= VERBOSE) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (level <= DEBUG) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (level <= INFO) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (level <= WARN) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (level <= ERROR) {
            Log.e(tag, msg);
        }
    }
}
