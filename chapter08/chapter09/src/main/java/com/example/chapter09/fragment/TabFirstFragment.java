package com.example.chapter09.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chapter09.R;

public class TabFirstFragment extends Fragment {
    private static final String TAG = "TabFirstFragment";
    protected View mView; // 声明一个视图对象
    protected Context mContext; // 声明一个上下文对象

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        mView =  inflater.inflate(R.layout.fragment_tab_first, container, false);
        TextView tv_first = mView.findViewById(R.id.tv_first);
        tv_first.setText("我是首页页面");
        return mView;
    }
}