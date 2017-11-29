package com.example.demon.mydemo.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.widget.Toast;

/**
 * 可以定时触发CPU的服务
 */
public class LongRunningService extends Service {
    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 执行耗时任务
            }
        }).start();
        Toast.makeText(this, "后台唤醒", Toast.LENGTH_SHORT).show();

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        int setTime = 10 * 1000;    // 十秒

        // SystemClock.elapsedRealtime()：开机到现在的毫秒System.currentTimeMillis():1970年至今
        long triggerAtTime = SystemClock.elapsedRealtime() + setTime;

        Intent i = new Intent(this, LongRunningService.class);
        PendingIntent pi = PendingIntent.getService(this, 0, i, 0);
        /**
         * ELAPSED_REALTIME_WAKEUP：触发事件从开机时算起，唤醒CUP, ELAPSED_REALTIME不唤醒
         * AlarmManager.RTC_WAKEUP:触发时间从1970/1/1：0：00算起，触发CPU, RTC不唤醒
         *
         */
        manager.set(AlarmManager.ELAPSED_REALTIME, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "定时唤醒活动销毁", Toast.LENGTH_SHORT).show();
    }
}
