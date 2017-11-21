package com.example.demon.mydemo.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.view.View;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

/**
 * 通知测试活动
 * 可在活动、服务、广播中打开，服务中用的最少
 */
public class NotificationTestActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_test_activity);
        findViewById(R.id.send_notice).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notice:
                getNotificationManager().notify(1, getNotification("这里写上正文内容"));
                break;
            default:
                break;
        }
    }

    // 管理通知
    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    // 创建通知
    private Notification getNotification(String test) {
        // 设置按下时跳转到的活动
        Intent intent = new Intent(this, NotificationTestActivity.class);
        // 延迟执行的Intent：Context、0、Intent、行为
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle("标题内容");
        builder.setContentText(test);
        builder.setWhen(System.currentTimeMillis());    //通知被创建的时间
        builder.setSmallIcon(R.mipmap.ic_launcher);     //只能用纯alpha图层的图片
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);       // 设置点击跳转的活动
        builder.setPriority(NotificationCompat.PRIORITY_MAX);// 重要程度、default、min、low、high、max
        builder.setAutoCancel(true);    // 点击后取消

//        // 在通知发出时播放一段音频
//        builder.setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")));
//        // 震动：禁止时长、震动时长、禁止时长、震动时长
//        builder.setVibrate(new long[]{0, 1000, 1000, 1000});
//        // 呼吸灯：颜色、亮起时长、暗去时长
//        builder.setLights(Color.GREEN, 1000, 1000);

        builder.setDefaults(NotificationCompat.DEFAULT_ALL); // 懒得设置就用默认的

//        // 设置长文字
//        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to " +
//                "build notifications, send and sync data, and use voice actions. " +
//                "Get the official Android IDE and developer tools to build apps for Android."));
//        // 设置大图片
//        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(
//                BitmapFactory.decodeResource(getResources(), R.drawable.big_image)));

        return builder.build();
    }
}
