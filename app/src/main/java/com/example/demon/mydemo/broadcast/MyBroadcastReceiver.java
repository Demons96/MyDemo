package com.example.demon.mydemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 定义一个静态广播接收器来接受自定义广播
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in MyBroadcastReceiver:收到自定义广播", Toast.LENGTH_SHORT).show();

        // 当广播为有序广播时可选择是否拦截
        abortBroadcast();
    }
}
