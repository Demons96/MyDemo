package com.example.demon.mydemo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demon.mydemo.R;

/**
 * Created by gyp52 on 17/11/5.
 * 左碎片实例
 */

public class FragmentLeft extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);
        return view;
    }

}
