package com.example.demon.mydemo.storage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

public class StorageMainActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storage_main_activity);
        findViewById(R.id.file_output_bt).setOnClickListener(this);
        findViewById(R.id.shared_preferences_bt).setOnClickListener(this);
        findViewById(R.id.database_bt).setOnClickListener(this);
        findViewById(R.id.database_lite_pal_bt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.file_output_bt:
                MyStartActivity(FileOutPutActivity.class);
                break;
            case R.id.shared_preferences_bt:
                MyStartActivity(SharedPreferencesActivity.class);
                break;
            case R.id.database_bt:
                MyStartActivity(DatabaseActivity.class);
                break;
            case R.id.database_lite_pal_bt:
                MyStartActivity(LitePalActivity.class);
        }
    }

    // 自定义活动启动方式
    public void MyStartActivity(Class<?> cls){
        startActivity(new Intent(StorageMainActivity.this, cls));
    }
}
