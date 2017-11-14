package com.example.demon.mydemo.network;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

/**
 * 在程序里面调用系统内置的浏览器
 */
public class WebViewActivity extends BaseActivity {
    private EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.network_web_view_activity);

        webView = (WebView) findViewById(R.id.web_tv);
        editText = findViewById(R.id.web_et);
        findViewById(R.id.search_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editText.getText().toString();
                webView.getSettings().setJavaScriptEnabled(true);
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(url);
            }
        });
    }
}
