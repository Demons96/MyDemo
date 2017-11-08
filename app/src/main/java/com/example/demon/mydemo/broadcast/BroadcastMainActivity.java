package com.example.demon.mydemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.util.LogUtil;

/**
 * 广播用法主活动
 * 1监听广播
 * 2发送自定义标准广播
 * 3发送自定义有序广播
 * 4添加、发送、接收、注销本地广播
 */
public class BroadcastMainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "BroadcastMainActivity";
    private NetworkChangeReceiver networkChangeReceiver = null; //网络广播
    private LocalReceiver localReceiver = null;        //本地广播
    private LocalBroadcastManager localBroadcastManager = null;    //本地广播管理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_main);

        findViewById(R.id.add_network_broadcast_bt).setOnClickListener(this);   //打开网络监听广播
        findViewById(R.id.remove_network_broadcast_bt).setOnClickListener(this);//关闭网络监听广播
        findViewById(R.id.send_normal_broadcast_bt).setOnClickListener(this);   //自定义标准广播
        findViewById(R.id.send_ordered_broadcast_bt).setOnClickListener(this);  //自定义有序广播
        findViewById(R.id.add_local_broadcast_bt).setOnClickListener(this);     //打开本地广播
        findViewById(R.id.remove_local_broadcast_bt).setOnClickListener(this);  //关闭本地广播
        findViewById(R.id.send_local_broadcast_bt).setOnClickListener(this);    //发送广播
        findViewById(R.id.force_offline_bt).setOnClickListener(this);    //强制下线广播
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (networkChangeReceiver != null) {
            unregisterReceiver(networkChangeReceiver);  // 动态注册的广播一定要取消注册
            networkChangeReceiver = null;
            LogUtil.d(TAG, "监听网络广播自动取消注册");
        }
        if ((localReceiver != null) && (localBroadcastManager != null)) {
            localBroadcastManager.unregisterReceiver(localReceiver);
            localReceiver = null;
            localBroadcastManager = null;
            LogUtil.d(TAG, "本地广播自动取消注册");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_network_broadcast_bt:     // 动态添加网络注册广播
                IntentFilter intentFilter1 = new IntentFilter();
                intentFilter1.addAction("android.net.conn.CONNECTIVITY_CHANGE"); //网络发生变化时系统发出的广播
                networkChangeReceiver = new NetworkChangeReceiver();
                registerReceiver(networkChangeReceiver, intentFilter1);
                LogUtil.d(TAG, "监听网络广播完成注册");
                break;
            case R.id.remove_network_broadcast_bt:
                if (networkChangeReceiver != null) {
                    unregisterReceiver(networkChangeReceiver);  // 动态注册的广播一定要取消注册
                    networkChangeReceiver = null;
                    LogUtil.d(TAG, "监听网络广播手动取消注册");
                } else {
                    LogUtil.d(TAG, "无广播可移除");
                }
                break;
            case R.id.send_normal_broadcast_bt:    //发送自定义标准广播
                Intent intent1 = new Intent("com.example.demon.mydemo.MY_BROADCAST");
                sendBroadcast(intent1);
                break;
            case R.id.send_ordered_broadcast_bt:    //发送自定义有序广播
                Intent intent2 = new Intent("com.example.demon.mydemo.MY_BROADCAST");
                sendOrderedBroadcast(intent2, null);    //参数二：与权限相关的字符串
                break;
            case R.id.add_local_broadcast_bt:   //添加本地广播监听
                localBroadcastManager = LocalBroadcastManager.getInstance(this); // 获取实例
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("com.example.demon.mydemo.LOCAL_BROADCAST");
                localReceiver = new LocalReceiver();
                localBroadcastManager.registerReceiver(localReceiver, intentFilter2); // 注册本地广播监听器
                LogUtil.d(TAG, "本地广播完成注册");
                break;
            case R.id.remove_local_broadcast_bt:
                if ((localReceiver != null) && (localBroadcastManager != null)) {
                    localBroadcastManager.unregisterReceiver(localReceiver);
                    localReceiver = null;
                    localBroadcastManager = null;
                    LogUtil.d(TAG, "本地广播手动取消注册");
                }
                break;
            case R.id.send_local_broadcast_bt:
                if (localBroadcastManager != null) {
                    Intent intent3 = new Intent("com.example.demon.mydemo.LOCAL_BROADCAST");
                    localBroadcastManager.sendBroadcast(intent3); // 发送本地广播
                }
                break;
            case R.id.force_offline_bt:    //发送强制下线广播
                Intent intent4 = new Intent("com.example.demon.mydemo.FORCE_OFFLINE");
                sendBroadcast(intent4);
                break;
        }
    }

    // 广播的动态注册，此例子实现网络变化监听
    class NetworkChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 取得系统服务类
            ConnectivityManager connectionManager = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);

            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
                LogUtil.d(TAG, "network is available");
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
                LogUtil.d(TAG, "network is unavailable");
            }
        }
    }

    // 本地广播
    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }

}
