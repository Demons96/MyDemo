package com.example.demon.mydemo.util;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Created by gyp52 on 17/11/26.
 * 获取全局的Context
 * 系统启动的时候会自动将Application初始化
 */

public class MyApplication extends Application{
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        //代替数据库的初始化android:name="org.litepal.LitePalApplication
        LitePalApplication.initialize(context);
    }

    public static Context getContext(){
        return context;
    }
}
