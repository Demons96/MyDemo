package com.example.demon.mydemo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by gyp52 on 17/11/15.
 * 网络请求类
 * 问题：网络请求是耗时的操作，通常会放在子线程中执行，但在子线程中无法返回响应数据。
 * 解决方法：通过回掉实现请求完毕时返回数据
 */

public class HttpUtil {
    /**
     * @param address 请求地址
     * @param listener 自己实现的回掉接口。
     * HttpUrlConnection
     */
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener) {
        if(!isNetworkAvailable()){
            Toast.makeText(MyApplication.getContext(), "无网络", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (listener != null) {
                        // 回调onFinish()方法
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        // 回调onError()方法
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    public interface HttpCallbackListener {
        void onFinish(String response);
        void onError(Exception e);
    }

    /**
     * @param address  请求地址
     * @param callback  回掉接口
     * OkHttp
     */
    public static void sendOkHttpRequest(final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    // 查看是否有网络
    private static boolean isNetworkAvailable(){
        // 取得系统服务类
//        ConnectivityManager connectionManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
//        if (networkInfo != null && networkInfo.isAvailable()) {
//            Toast.makeText(MyApplication.getContext(), "network is available", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(MyApplication.getContext(), "network is unavailable", Toast.LENGTH_SHORT).show();
//        }
        return true;
    }
}
