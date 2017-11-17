package com.example.demon.mydemo.service;

import android.app.IntentService;
import android.content.Intent;

import com.example.demon.mydemo.util.LogUtil;

/**
 * Created by gyp52 on 17/11/16.
 * 异步且可以自动停止的活动
 */

public class IntentServiceTest extends IntentService {
    private static final String TAG = "IntentServiceTest";

    // 必须在其内部调用父类的有参构造函数
    public IntentServiceTest() {
        super("IntentServiceTest"); // 调用父类的有参构造函数
    }

    // 在子线程中运行，可执行耗时操作
    @Override
    protected void onHandleIntent(Intent intent) {
        // 打印当前线程的id
        LogUtil.d(TAG, "活动线程Thread id is " + Thread.currentThread(). getId());
    }

    // 执行完毕自动停止
    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy executed");
    }
}
