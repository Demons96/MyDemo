package com.example.demon.mydemo.service;

/**
 * Created by gyp52 on 17/11/17.
 * 回掉接口
 * 用于对下载过程中的各种状态尽心监听和回掉
 */

public interface DownloadListener {
    void onProgress(int progress);  //当前进度
    void onSuccess();       //下载成功事件
    void onFailed();        //下载失败事件
    void onPaused();        //下载停止事件
    void onCanceled();      //下载取消事件
}
