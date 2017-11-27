package com.example.demon.mydemo.baidulocation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;
import com.example.demon.mydemo.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class LocationTestActivity extends BaseActivity {
    private static final String TAG = "LocationTestActivity";
    private StringBuilder currentPosition;

    public LocationClient mLocationClient;  // 位置监听
    private TextView positionText;

    private MapView mapView;        // 显示地图的view
    private BaiduMap baiduMap;      // 地图控制器
    private boolean isFirstLocate = true;   // 为了防止多次调用animateMapStatus

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener()); // 注册位置监听器
        SDKInitializer.initialize(getApplicationContext());     // 地图初始化

        setContentView(R.layout.baidulocation_location_test_activity);

        positionText = (TextView) findViewById(R.id.position_text_view);
        positionText.setText("地图加载中...");

        mapView = (MapView) findViewById(R.id.map_view);
        baiduMap = mapView.getMap();    // 设置地图控制器
        baiduMap.setMyLocationEnabled(true);    // 设置当前位置的显示

        // 一次性申请多个权限
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(LocationTestActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(LocationTestActivity.this,
                Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(LocationTestActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(LocationTestActivity.this, permissions, 1);
        } else {
            requestLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    // 请求定位
    private void requestLocation() {
        initLocation();             // 更新当前位置
        mLocationClient.start();    // 开始定位 默认只执行一次
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);   // 五秒更新一次
        // Device_Sensors：传感器模式，Hight_Accuracy：自动切换模式默认，Battery_Saving：网络定位
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);  // 表示获取当前位置的详细信息
        mLocationClient.setLocOption(option);   // 程序销毁是需调用stop方法停止
    }

    // 位置监听
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation( BDLocation location) {
            if (location.getLocType() == BDLocation.TypeGpsLocation ||
                    location.getLocType() == BDLocation.TypeNetWorkLocation) {
                navigateTo(location);
            }

            currentPosition = new StringBuilder();
            currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
            currentPosition.append("经线：").append(location.getLongitude()).append("\n");
            currentPosition.append("定位方式：");
            if (location.getLocType() == BDLocation.TypeGpsLocation) {
                currentPosition.append("GPS").append("\n");
            } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                currentPosition.append("网络").append("\n");
            }

            currentPosition.append("国家：").append(location.getCountry()).append("\n");
            currentPosition.append("省：").append(location.getProvince()).append("\n");
            currentPosition.append("市：").append(location.getCity()).append("\n");
            currentPosition.append("区：").append(location.getDistrict()).append("\n");
            currentPosition.append("街道：").append(location.getStreet()).append("\n");

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    positionText.setText(currentPosition);
                    LogUtil.d(TAG, currentPosition.toString());
                }
            });
        }

    }

    // 当前位置
    private void navigateTo(BDLocation location) {
        if (isFirstLocate) {
            Toast.makeText(this, "nav to " + location.getAddrStr(), Toast.LENGTH_SHORT).show();
            // 保存经纬度
            LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
            // 存放地图的状态更新，将屏幕移动到当前位置
            MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update = MapStatusUpdateFactory.zoomTo(16f);    // 设置缩放级别3~19
            baiduMap.animateMapStatus(update);
            isFirstLocate = false;
        }
        // 封装设备当前所在位置，显示小点
        MyLocationData.Builder locationBuilder = new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData = locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }

    // 在可见范围内对地图进行操作
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }
}
