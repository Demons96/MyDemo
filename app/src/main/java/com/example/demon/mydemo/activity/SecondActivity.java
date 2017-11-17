package com.example.demon.mydemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.ThreadUpdataUi.ThreadUpdateUiMainActivity;
import com.example.demon.mydemo.broadcast.BroadcastMainActivity;
import com.example.demon.mydemo.chat.ChatViewActivity;
import com.example.demon.mydemo.fragment.FragmentMain;
import com.example.demon.mydemo.fragment.NewsMainActivity;
import com.example.demon.mydemo.network.HttpActivity;
import com.example.demon.mydemo.network.WebViewActivity;
import com.example.demon.mydemo.service.ServiceTest;
import com.example.demon.mydemo.service.ServiceTextActivity;
import com.example.demon.mydemo.storage.DatabaseActivity;
import com.example.demon.mydemo.storage.FileOutPutActivity;
import com.example.demon.mydemo.storage.LitePalActivity;
import com.example.demon.mydemo.storage.SharedPreferencesActivity;
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
        findViewById(R.id.broadcast_test_bt).setOnClickListener(this);
        findViewById(R.id.file_output_bt).setOnClickListener(this);
        findViewById(R.id.shared_preferences_bt).setOnClickListener(this);
        findViewById(R.id.database_bt).setOnClickListener(this);
        findViewById(R.id.database_lite_pal_bt).setOnClickListener(this);
        findViewById(R.id.web_bt).setOnClickListener(this);
        findViewById(R.id.http_connection_bt).setOnClickListener(this);
        findViewById(R.id.thread_update_ui_bt).setOnClickListener(this);
        findViewById(R.id.service_test_bt).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.finish_activity_bt:
                finishAllActivity(SecondActivity.this);     //弹出强制退出提示框
                break;
            case R.id.list_view_bt:
                MyStartActivity(ListViewTest.class);
                break;
            case R.id.recycler_view_bt:
                MyStartActivity(RecyclerViewTest.class);
                break;
            case R.id.chat_bt:
                MyStartActivity(ChatViewActivity.class);
                break;
            case R.id.fragment_bt:
                MyStartActivity(FragmentMain.class);
                break;
            case R.id.news_fragment_bt:
                MyStartActivity(NewsMainActivity.class);
                break;
            case R.id.broadcast_test_bt:
                MyStartActivity(BroadcastMainActivity.class);
                break;
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
                break;
            case R.id.web_bt:
                MyStartActivity(WebViewActivity.class);
                break;
            case R.id.http_connection_bt:
                MyStartActivity(HttpActivity.class);
                break;
            case R.id.thread_update_ui_bt:
                MyStartActivity(ThreadUpdateUiMainActivity.class);
                break;
            case R.id.service_test_bt:
                MyStartActivity(ServiceTextActivity.class);
                break;

            default:
                break;
        }
    }

    // 自定义活动启动方式
    public void MyStartActivity(Class<?> cls){
        startActivity(new Intent(SecondActivity.this, cls));
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
