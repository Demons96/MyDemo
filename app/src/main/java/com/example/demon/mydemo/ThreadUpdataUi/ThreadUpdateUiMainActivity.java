package com.example.demon.mydemo.ThreadUpdataUi;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.demon.mydemo.R;

/**
 * 此活动简单的使用了三种在线程中更行UI的方法
 * 1、异步消息处理机制Handler
 * 2、runOnUiThread，实际上就是封装了Handler的接口
 * 3、AsyncTask
 */
public class ThreadUpdateUiMainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int UPDATE_TEXT = 1;    //handler接收消息的id
    private TextView textView;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {    // 在这里可以进行UI操作
            switch (msg.what) {
                case UPDATE_TEXT:
                    textView.setText("Handler更新UI\n");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_update_ui_main_activity);

        textView = findViewById(R.id.text_view);

        findViewById(R.id.handler_bt).setOnClickListener(this);
        findViewById(R.id.run_on_ui_thread_bt).setOnClickListener(this);
        findViewById(R.id.async_task_bt).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.handler_bt:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message message = new Message();
                        message.what = UPDATE_TEXT;
                        handler.sendMessage(message); // 将Message对象发送出去
                    }
                }).start();
                break;
            case R.id.run_on_ui_thread_bt:
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("runOnThread更新UI\n");
                    }
                });
                break;
            case R.id.async_task_bt:
                new TestAsyncTask().execute();
                break;
        }
    }

    /**
     * Params：doInBackground需要传入的参数
     * Progress：onProgressUpdate表示当前进度的类型
     * Result：doInBackground完成时返回给onPostExecute的值的类型
     */
    class TestAsyncTask extends AsyncTask<Void, Integer, Boolean>{
        // 后台任务开始之前调用(UI)
        @Override
        protected void onPreExecute() {
            textView.setText("AsyncTask开始执行\n");
            super.onPreExecute();
        }

        // 执行费时间的任务（子线程）
        @Override
        protected Boolean doInBackground(Void... voids) {
            // 执行过程中可调用publishProgress反馈进度
            publishProgress(100);
            return true;
        }

        // 如调用了publishProgress方法则调用此方法更新(UI)
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.append("当前进度："+values[0]+"%\n");
        }

        // 后台任务执行完毕通过return语句进行返回
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean){
                textView.append("线程上的任务执行完毕\n");
            } else {
                textView.append("线程上的任务执行失败\n");
            }
        }
    }

}
