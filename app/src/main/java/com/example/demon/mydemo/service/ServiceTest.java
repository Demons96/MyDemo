package com.example.demon.mydemo.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.MainActivity;
import com.example.demon.mydemo.util.LogUtil;

/**
 * 测试活动
 */
public class ServiceTest extends Service {
    private static final String TAG = "ServiceTest";
    private DownloadBinder mBinder = new DownloadBinder();  //用于在活动中调用服务

    public ServiceTest() {
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    // 在服务创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG, "服务创建成功onCreate executed");
        Toast.makeText(this, "服务创建成功onCreate executed", Toast.LENGTH_SHORT).show();

        // 使用前台服务
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

    // 在每次启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "服务启动onStartCommand executed");
        Toast.makeText(this, "服务启动onStartCommand executed", Toast.LENGTH_SHORT).show();

        return super.onStartCommand(intent, flags, startId);
    }

    // 服务销毁的时候调用
    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "服务销毁onDestroy executed");
        Toast.makeText(this, "服务销毁onDestroy executed", Toast.LENGTH_SHORT).show();
    }

    class DownloadBinder extends Binder {
        public void startDownload() {
            LogUtil.d(TAG, "活动调用服务下载startDownload executed");
//            Toast.makeText(this, "活动调用服务下载startDownload executed", Toast.LENGTH_SHORT).show();
        }
        public int getProgress() {
            LogUtil.d(TAG, "活动调用服务进度条getProgress executed");
//            Toast.makeText(this, "活动调用服务下载startDownload executed", Toast.LENGTH_SHORT).show();
            return 0;
        }
    }
}
