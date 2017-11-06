package com.example.demon.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.chat.ChatView;
import com.example.demon.mydemo.fragment.FragmentMain;
import com.example.demon.mydemo.fragment.NewsMainActivity;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.view.ListViewTest;
import com.example.demon.mydemo.view.RecyclerViewTest;

public class SecondActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.finish_activity_bt).setOnClickListener(this);
        findViewById(R.id.list_view_bt).setOnClickListener(this);
        findViewById(R.id.recycler_view_bt).setOnClickListener(this);
        findViewById(R.id.chat_bt).setOnClickListener(this);
        findViewById(R.id.fragment_bt).setOnClickListener(this);
        findViewById(R.id.news_fragment_bt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finish_activity_bt:
                finishAllActivity(SecondActivity.this);     //弹出强制退出提示框
                break;
            case R.id.list_view_bt:
                Intent intent1 = new Intent(SecondActivity.this, ListViewTest.class);
                startActivity(intent1);
                break;
            case R.id.recycler_view_bt:
                Intent intent2 = new Intent(SecondActivity.this, RecyclerViewTest.class);
                startActivity(intent2);
                break;
            case R.id.chat_bt:
                Intent intent3 = new Intent(SecondActivity.this, ChatView.class);
                startActivity(intent3);
                break;
            case R.id.fragment_bt:
                Intent intent4 = new Intent(SecondActivity.this, FragmentMain.class);
                startActivity(intent4);
                break;
            case R.id.news_fragment_bt:
                Intent intent5 = new Intent(SecondActivity.this, NewsMainActivity.class);
                startActivity(intent5);
                break;
            default:
                break;
        }
    }

    /**
     * 创建Menu
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // 设置Menu的监听事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "添加啦", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "移除啦", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
        return true;
    }
}
