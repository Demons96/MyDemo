package com.example.demon.mydemo.chat;

import com.example.demon.mydemo.R;

/**
 * Created by gyp52 on 17/11/5.
 * 判断发送接受方
 */
public class Msg {
    public static final int TYPE_RECEIVED = 0;  //接收
    public static final int TYPE_SENT = 1;     //发送
    private int type;           //发送消息的类型
    private int imageId;        //头像资源id
    private String content;     //消息的内容

    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
        this.imageId = R.mipmap.ic_launcher_round;  //默认系统头像
    }

    public Msg(String content, int type, int imageId){
        this.content = content;
        this.type = type;
        this.imageId = imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getType() {
        return type;
    }

    public int getImageId() {
        return imageId;
    }

    public String getContent() {
        return content;
    }
}
