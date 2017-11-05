package com.example.demon.mydemo.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by gyp52 on 17/11/3.
 * 这是一个基础类（需活动继承此类）
 * 功能一：用来知晓当前处于哪一个活动
 * 功能二：弹出强制退出提示框
 */

@SuppressLint("Registered") //这是什么鬼
public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";
    protected AlertDialog.Builder finishAllActivityDialog = null;     // 错误提示框

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, getClass().getSimpleName());     //打印当前活动的名称

        ActivityCollector.addActivity(this);    //向活动管理器添加活动
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);     //从活动管理器里面移除活动
    }

    // 出现严重错误时提示退出
    public void finishAllActivity(final Context context) {
        finishAllActivityDialog = new AlertDialog.Builder(context);
        finishAllActivityDialog.setTitle("警告！");
        finishAllActivityDialog.setMessage("出错啦，需强制退出。");
        finishAllActivityDialog.setCancelable(false);
        finishAllActivityDialog.setPositiveButton("好吧", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityCollector.finishAll();
            }
        });
        finishAllActivityDialog.setNegativeButton("我拒绝", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "你是用户你牛逼。", Toast.LENGTH_SHORT).show();
            }
        });
        finishAllActivityDialog.show();
    }
}
