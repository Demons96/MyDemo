package com.example.demon.mydemo.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;    //3
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.util.LogUtil;

/**
 * 使用SharedPreferences存储数据
 * 获取SharedPreferences对象的三种方法。
 * 1:SharedPreferences.Editor editor = getSharedPreferences(文件名, MODE_PRIVATE).edit();
 * 2:SharedPreferences.Editor editor = (SharedPreferences.Editor) getPreferences(MODE_PRIVATE);
 * 3:PreferenceManager.getDefaultSharedPreferences(Context参数如SharedPreferencesActivity.this);
 */
public class SharedPreferencesActivity extends BaseActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_shared_preferences_activity);

        textView = findViewById(R.id.data_tv);  //显示保存的数据

        // 存数据
        findViewById(R.id.save_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();     //存完提交
            }
        });

        // 取数据
        findViewById(R.id.restore_data).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);

                /**
                 * 参数一：键
                 * 参数二：默认值
                 */
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);

                textView.setText("");
                textView.append("name is " + name + "\n");
                textView.append("age is " + age + "\n");
                textView.append("married is " + married + "\n");

                LogUtil.d("MainActivity", "name is: " + name);
                LogUtil.d("MainActivity", "age is: " + age);
                LogUtil.d("MainActivity", "married is: " + married);
            }
        });
    }
}
