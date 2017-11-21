package com.example.demon.mydemo.media;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.util.LogUtil;

import java.io.File;
import java.io.IOException;

/**
 * 调用系统相机
 * 从Android6.0开始读写SD卡变成了危险权限，将图片存入除关联目录可以跳过运行时权限
 * 改进：对于体积大的照片需进行压缩,视频格式支持和播放效率都有很大的不足
 */
public class MediaTestActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "MediaTestActivity";

    public static final int TAKE_PHOTO = 1;     //直接调用摄像头
    public static final int CHOOSE_PHOTO = 2;   //从相册中取出照片
    private ImageView picture;
    private Uri imageUri;

    private MediaPlayer mediaPlayer = new MediaPlayer();    // 创建音频实例

    private VideoView videoView;        // 视频实例

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_test_activity);

        // 图像
        findViewById(R.id.take_photo).setOnClickListener(this);
        findViewById(R.id.choose_from_album).setOnClickListener(this);
        picture = (ImageView) findViewById(R.id.picture);

        // 音频
        findViewById(R.id.play).setOnClickListener(this);
        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(MediaTestActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MediaTestActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            initMediaPlayer(); // 初始化MediaPlayer
        }

        // 视频
        videoView = (VideoView) findViewById(R.id.video_view);
        findViewById(R.id.play_video).setOnClickListener(this);
        findViewById(R.id.pause_video).setOnClickListener(this);
        findViewById(R.id.replay_video).setOnClickListener(this);
        if (ContextCompat.checkSelfPermission(MediaTestActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MediaTestActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 3);
        } else {
            initVideoPath(); // 初始化MediaPlayer
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo:
                // 创建File对象，指定照片名存在应用关联缓存目录下
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                LogUtil.d(TAG, "拍照时间：" + System.currentTimeMillis() + "\n" +
                        "应用关联缓存：" + getExternalCacheDir());
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT < 24) {
                    imageUri = Uri.fromFile(outputImage);
                    LogUtil.d(TAG, "低于7的系统图片路径：" + imageUri);
                } else {
                    // FileProvider特殊的内容提供器进行数据保护，需注册！。第二参数：任意唯一字符串
                    imageUri = FileProvider.getUriForFile(MediaTestActivity.this,
                            "com.example.demon.mydemo.fileprovider", outputImage);
                    LogUtil.d(TAG, "高于7的系统图片路径：" + imageUri);
                }
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                break;
            case R.id.choose_from_album:    // 读取SD卡权限
                if (ContextCompat.checkSelfPermission(MediaTestActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MediaTestActivity.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
                break;
            case R.id.play:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start(); // 开始播放
                }
                break;
            case R.id.pause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause(); // 暂停播放
                }
                break;
            case R.id.stop:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset(); // 停止播放
                    initMediaPlayer();
                }
                break;
            case R.id.play_video:
                if (!videoView.isPlaying()) {
                    videoView.start(); // 开始播放
                }
                break;
            case R.id.pause_video:
                if (videoView.isPlaying()) {
                    videoView.pause(); // 暂停播放
                }
                break;
            case R.id.replay_video:
                if (videoView.isPlaying()) {
                    videoView.resume(); // 重新播放
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:   // 读取SD卡权限
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            case 3:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPath();
                } else {
                    Toast.makeText(this, "拒绝权限将无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    /**
     * *******************************图像*************************************************
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitKat(data); // 4.4及以上系统使用这个方法处理图片
                    } else {
                        handleImageBeforeKitKat(data); // 4.4以下系统使用这个方法处理图片
                    }
                }
                break;
            default:
                break;
        }
    }

    // 打开相册
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    // uri没有被封装过
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        LogUtil.d(TAG, "handleImageOnKitKat: uri is " + uri);

        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理

            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                // 如果uri的authority是media格式，需取出后半部分的数字id

                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理

            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可

            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    // 显示图片
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * *******************************音频*************************************************
     */
    // 创建实例
    private void initMediaPlayer() {
        try {
            // 需要在sd卡根目录下放置一个mp3文件
            File file = new File(Environment.getExternalStorageDirectory(), "music.mp3");
            mediaPlayer.setDataSource(file.getPath()); // 指定音频文件的路径
            mediaPlayer.prepare(); // 让MediaPlayer进入到准备状态
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * *******************************视频*************************************************
     */
    // 读取视频文件
    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
        videoView.setVideoPath(file.getPath()); // 指定视频文件的路径
    }

    /**
     * *******************************公用*************************************************
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放音频资源
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        // 释放视频资源
        if (videoView != null) {
            videoView.suspend();
        }
    }
}
