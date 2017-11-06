package com.example.demon.mydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demon.mydemo.R;

/**
 * Created by gyp52 on 17/11/5.
 * 另外一个右边的碎片实例
 */
public class FragmentRightAnother extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_right_another, container, false);
    }
}
