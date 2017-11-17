package com.example.demon.mydemo.service;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by gyp52 on 17/11/17.
 * 子线程中下载并通知前台UI进度
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    public static final int TYPE_SUCCESS = 0;   // 下载成功
    public static final int TYPE_FAILED = 1;    // 下载失败
    public static final int TYPE_PAUSED = 2;    // 下载暂停
    public static final int TYPE_CANCELED = 3;  // 下载取消

    private DownloadListener listener;   // 通过此参数进行下载事件的回掉

    private boolean isCanceled = false;     // 是否取消
    private boolean isPaused = false;       // 是否暂停

    private float lastProgress;   // 保存下载进度，用于对比是否需要更新进度条

    public DownloadTask(DownloadListener listener) {
        this.listener = listener;
    }

    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile savedFile = null;
        File file = null;
        try {
            long downloadedLength = 0;      // 记录已下载的文件长度
            String downloadUrl = params[0]; //根据传入的参数解析处下载的地址
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));  //解析出下载的文件名
            // 根据Environment.DIRECTORY_DOWNLOADS取得SD卡的Download目录
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

            file = new File(directory + fileName);

            // 判断是否已存在需下载文件
            if (file.exists()) {
                downloadedLength = file.length();   //若存在则取得已下载的字节数
            }

            long contentLength = getContentLength(downloadUrl); //待下载的文件长度
            if (contentLength == 0) {
                return TYPE_FAILED;     // 如文件长度为0则下载失败
            } else if (contentLength == downloadedLength) {
                return TYPE_SUCCESS;    // 已下载字节和文件总字节相等，说明已经下载完成了
            }

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 断点下载，指定从哪个字节开始下载
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();

            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                savedFile = new RandomAccessFile(file, "rw");
                savedFile.seek(downloadedLength); // 跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (isCanceled) {
                        return TYPE_CANCELED;
                    } else if (isPaused) {
                        return TYPE_PAUSED;
                    } else {
                        total += len;
                        savedFile.write(b, 0, len);
                        // 计算已下载的百分比
                        int progress = (int)((total + downloadedLength) * 100 / contentLength);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (savedFile != null) {
                    savedFile.close();
                }
                if (isCanceled && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    // 显示进度条
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS:
                listener.onSuccess();
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            case TYPE_PAUSED:
                listener.onPaused();
                break;
            case TYPE_CANCELED:
                listener.onCanceled();
            default:
                break;
        }
    }

    // 给后台服务调用
    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCanceled = true;
    }

    // 取得下载文件的长度
    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}