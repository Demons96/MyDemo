package com.example.demon.mydemo.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.BaseActivity;

/**
 * 碎片控制主活动
 */
public class FragmentMain extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);

        findViewById(R.id.fragment_left_bt).setOnClickListener(this); //调用静态碎片方法

        replaceFragment(new FragmentRight());   //动态加载碎片
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_left_bt:
                replaceFragment(new FragmentRightAnother());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();  //1获取碎片管理
        FragmentTransaction transaction = fragmentManager.beginTransaction();   //2开启事务
        transaction.replace(R.id.right_layout, fragment);   //3向容器添加或替换碎片
        transaction.addToBackStack(null);     //5返回栈，可选择性传入名称
        transaction.commit();   //4提交事务
    }
}
