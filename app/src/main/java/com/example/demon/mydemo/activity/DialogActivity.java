package com.example.demon.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

/**
 * 这是一个对话框
 */
public class DialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        if (getSupportActionBar() != null) getSupportActionBar().hide();  //隐藏标题栏
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DialogActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
