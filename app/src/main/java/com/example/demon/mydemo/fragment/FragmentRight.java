package com.example.demon.mydemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demon.mydemo.R;
import com.example.demon.mydemo.util.LogUtil;

/**
 * Created by gyp52 on 17/11/5.
 * 右边的碎片实例
 */

public class FragmentRight extends Fragment {

    public static final String TAG = "RightFragment";

    // 当碎片和活动建立关联
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        LogUtil.d(TAG, "onAttach：当碎片和活动建立关联");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果销毁时保存了数据，则可恢复
        if(savedInstanceState!=null){
            String tempData = savedInstanceState.getString("data_key");
            LogUtil.d(TAG, "恢复数据："+tempData);
        }
        LogUtil.d(TAG, "onCreate");
    }

    // 为碎片创建视图
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtil.d(TAG, "onCreateView：为碎片创建视图");
        View view = inflater.inflate(R.layout.fragment_right, container, false);
        final EditText editText = view.findViewById(R.id.fragment_right_et);
        view.findViewById(R.id.fragment_right_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), editText.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    // 确保与碎片相关联的活动一定已创建完时调用
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtil.d(TAG, "onActivityCreated：确保与碎片相关联的活动一定已创建完时调用");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.d(TAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d(TAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d(TAG, "onStop");
    }

    // 与碎片相关联的视图被移除
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d(TAG, "onDestroyView：与碎片相关联的视图被移除");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "onDestroy");
    }

    // 当碎片和活动解除关系
    @Override
    public void onDetach() {
        super.onDetach();
        LogUtil.d(TAG, "onDetach：当碎片和活动解除关系");
    }

    // 当碎片被系统收回时调用，用来保存重要数据
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "这是一段只有在被销毁时才会保存的消息";
        outState.putString("data_kay", tempData);
    }
}
