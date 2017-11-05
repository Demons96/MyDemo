package com.example.demon.mydemo.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.demon.mydemo.R;

/**
 * Created by gyp52 on 17/11/4.
 * 自定义标题栏
 * 设置标题公用的监听事件
 */

public class TitleLayout extends LinearLayout implements View.OnClickListener {

    public TitleLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // 引入布局文件
        LayoutInflater.from(context).inflate(R.layout.title, this);
        findViewById(R.id.title_back).setOnClickListener(this);
        findViewById(R.id.title_edit).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                ((Activity) getContext()).finish(); // 返回上一步
                break;
            case R.id.title_edit:
                Toast.makeText(getContext(), "你点击了Edit按钮", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
