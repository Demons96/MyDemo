package com.example.demon.mydemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

/**
 * 用于单页时显示新闻内容碎片
 */
public class NewsContentActivity extends BaseActivity {

    // 对调用该活动的对象指明此活动所需要传递的参数
    public static void actionStart(Context context, String newsTitle, String newsContent) {
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", newsTitle);
        intent.putExtra("news_content", newsContent);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_content);

        String newsTitle = getIntent().getStringExtra("news_title"); // 获取传入的新闻标题
        String newsContent = getIntent().getStringExtra("news_content"); // 获取传入的新闻内容

        // 获取碎片实例,向碎片传递需显示的数据
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager()
                .findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(newsTitle, newsContent); // 刷新NewsContentFragment界面
    }

}
