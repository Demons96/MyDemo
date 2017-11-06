package com.example.demon.mydemo.chat;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天活动类
 */
public class ChatView extends BaseActivity {
    private List<Msg> msgList = new ArrayList<Msg>();
    private RecyclerView msgRecyclerView;  //聊天列表
    private EditText inputText;   //输入框
    private Button sendBt;  //发送按钮
    private MsgAdapter adapter; //内容适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        initMsgs(); // 初始化消息数据

        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        inputText = (EditText) findViewById(R.id.input_text);
        sendBt = (Button) findViewById(R.id.send_bt);

        // 消息布局
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        // 发送按监听事件
        sendBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT, R.drawable.apple_pic);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size() - 1); // 当有新消息时，刷新ListView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size() - 1); // 将ListView定位到最后一行
                    inputText.setText(""); // 清空输入框中的内容
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("对象发送消息.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("哦，收到。", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("现在打多一点字，测试一下多行文本显示的效果怎么样。", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}
