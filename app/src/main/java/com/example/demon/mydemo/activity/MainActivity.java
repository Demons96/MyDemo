package com.example.demon.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) getSupportActionBar().hide();  //隐藏标题栏

        Toast.makeText(MainActivity.this, "程序开启啦", Toast.LENGTH_SHORT).show();

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent1);
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, DialogActivity.class);
                startActivity(intent2);
            }
        });
    }
}
