package com.example.chapter09.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chapter09.R;
import com.example.chapter09.adapter.ClassPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;


public class DepartmentClassFragment extends Fragment {
    protected View mView; // 声明一个视图对象
    protected AppCompatActivity mActivity; // 声明一个活动对象
    private List<String> mTitleList = new ArrayList<String>(); // 标题文字列表

    public DepartmentClassFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mActivity = (AppCompatActivity) getActivity();
        mView = inflater.inflate(R.layout.fragment_department_class, container, false);
        Toolbar tl_head = mView.findViewById(R.id.tl_head);
        mActivity.setSupportActionBar(tl_head);

        mTitleList.add("服装");
        mTitleList.add("电器");

        TabLayout tab_title = mView.findViewById(R.id.tab_title);
        ViewPager2 vp2_content = mView.findViewById(R.id.vp2_content);

        ClassPagerAdapter adapter = new ClassPagerAdapter(mActivity, mTitleList);
        vp2_content.setAdapter(adapter); // 设置二代翻页视图的适配器
        new TabLayoutMediator(tab_title, vp2_content, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(mTitleList.get(position));
            }
        }).attach();
        return mView;
    }
}