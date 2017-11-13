package com.example.demon.mydemo.chat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demon.mydemo.R;

import java.util.List;


/**
 * Created by gyp52 on 17/11/5.
 * 消息适配器
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    private List<Msg> mMsgList;     //消息数据

    static class ViewHolder extends RecyclerView.ViewHolder {
        View chatView;      // 聊天消息的View

        LinearLayout leftLayout;    //左聊天View布局
        TextView leftMsg;           //左消息
        ImageView leftImage;          //左头像

        LinearLayout rightLayout;   //右聊天View布局
        TextView rightMsg;          //右消息
        ImageView rightImage;         //右头像

        public ViewHolder(View view) {
            super(view);
            chatView = view;

            leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
            leftMsg = (TextView) view.findViewById(R.id.left_msg_tv);
            leftImage = (ImageView) view.findViewById(R.id.left_msg_image);

            rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
            rightMsg = (TextView) view.findViewById(R.id.right_msg_tv);
            rightImage = (ImageView) view.findViewById(R.id.right_msg_image);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        mMsgList = msgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_msg_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        holder.chatView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "你点击了消息", Toast.LENGTH_SHORT).show();
            }
        });
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        switch (msg.getType()) {
            case Msg.TYPE_RECEIVED:     // 左边接受消息
                // 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
                holder.leftLayout.setVisibility(View.VISIBLE);  //设置布局可见性
                holder.rightLayout.setVisibility(View.GONE);
                holder.leftMsg.setText(msg.getContent());       //设置消息内容
                holder.leftImage.setImageResource(msg.getImageId());    //设置头像
                break;
            case Msg.TYPE_SENT:         //右边发送消息
                holder.rightLayout.setVisibility(View.VISIBLE);
                holder.leftLayout.setVisibility(View.GONE);
                holder.rightMsg.setText(msg.getContent());
                holder.rightImage.setImageResource(msg.getImageId());
                break;
            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

}
