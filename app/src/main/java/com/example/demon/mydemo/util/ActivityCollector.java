package com.example.demon.mydemo.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gyp52 on 17/11/3.
 * 活动管理器
 * 功能一：使用集合类对所有的活动进行管理（增加、移除）
 * 功能二：提供退出当前所有活动的方法
 */

public class ActivityCollector {
    // 活动集合类
    public static List<Activity> activities = new ArrayList<>();

    // 向集合类添加活动
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    // 将活动从集合中移除
    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    // 将集合里所有的活动结束
    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear(); //清空集合
        android.os.Process.killProcess(android.os.Process.myPid()); //杀掉当前程序进程
    }
}
