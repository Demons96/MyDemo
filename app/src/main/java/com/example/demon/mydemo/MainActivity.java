package com.example.demon.mydemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demon.mydemo.activity.DialogActivity;
import com.example.demon.mydemo.activity.SecondActivity;
import com.example.demon.mydemo.myclass.User;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.util.LogUtil;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    static private User user = new User();
    private TextView textView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (getSupportActionBar() != null) getSupportActionBar().hide();  //隐藏标题栏

        // 获取程序销毁时保存的重要数据
        if (savedInstanceState != null) {
            String data = savedInstanceState.getString("out_data");
            Toast.makeText(this, "主活动数据恢复" + data, Toast.LENGTH_SHORT).show();
            LogUtil.d(TAG, "主活动数据恢复" + data);
        }
        Toast.makeText(MainActivity.this, "程序开启啦", Toast.LENGTH_SHORT).show();

        textView = (TextView) findViewById(R.id.text_view);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);

        // 取出登陆活动传过来的对象
        user = (User) getIntent().getSerializableExtra("user");
        textView.append("\n" + user.getName());
    }

    // 提供一个启动时需传入必要参数的入口
    public static void actionStart(Context context, User u) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("user", u);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent1);
                break;
            case R.id.button2:
                Intent intent2 = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent2);
                break;
        }
    }

    // 活动被销毁时用于临时保存数据
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("out_data", "活动销毁时保存的数据");
    }
}
