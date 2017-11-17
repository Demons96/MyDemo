package com.example.demon.mydemo.service;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.util.LogUtil;

/**
 * 服务使用例子
 * 服务的启动、停止
 * 活动与服务的绑定、解绑
 * 注：如同时调用了启动和绑定，则需要同时调用停止与取消，绑定时自动启动
 * 服务中的代码默认运行在主线程中
 * 服务自己停止自己调用stopSelf()
 * IntentService:解决了服务中需开启线程执行耗时操作，启动后一直处于运行状态等问题
 */
public class ServiceTextActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "ServiceTextActivity";
    private TextView textView;
    private ServiceTest.DownloadBinder downloadBinder;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (ServiceTest.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_text_activity);

        textView = findViewById(R.id.text_view);

        findViewById(R.id.start_service).setOnClickListener(this);
        findViewById(R.id.stop_service).setOnClickListener(this);
        findViewById(R.id.bind_service).setOnClickListener(this);
        findViewById(R.id.unbind_service).setOnClickListener(this);
        findViewById(R.id.start_intent_service).setOnClickListener(this);
        findViewById(R.id.download_bt).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_service:
                Intent startIntent = new Intent(this, ServiceTest.class);
                startService(startIntent); // Context类中启动服务
                textView.append("启动服务\n");
                break;
            case R.id.stop_service:
                Intent stopIntent = new Intent(this, ServiceTest.class);
                stopService(stopIntent); // 停止服务
                textView.append("停止服务\n");
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, ServiceTest.class);
                bindService(bindIntent, serviceConnection, BIND_AUTO_CREATE); // 绑定服务,绑定后自动创建
                textView.append("绑定服务\n");
                break;
            case R.id.unbind_service:
                unbindService(serviceConnection); // 解绑服务
                textView.append("解绑服务\n");
                break;
            case R.id.start_intent_service:
                // 打印主线程的id
                LogUtil.d(TAG, "主线程idThread id is " + Thread.currentThread(). getId());
                textView.append("主线程idThread id is " + Thread.currentThread(). getId() + "\n");
                Intent intentService = new Intent(this, IntentServiceTest.class);
                startService(intentService);
                break;
            case R.id.download_bt:
                Intent intent = new Intent(ServiceTextActivity.this, DownloadActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

}
