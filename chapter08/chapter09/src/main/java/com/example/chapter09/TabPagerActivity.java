package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.chapter09.adapter.TabPagerAdapter;

public class TabPagerActivity extends AppCompatActivity {
    private ViewPager vp_content; // 声明一个翻页视图对象
    private RadioGroup rg_tabbar; // 声明一个单选组对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_pager);
        vp_content = findViewById(R.id.vp_content); // 从布局文件获取翻页视图
        rg_tabbar = findViewById(R.id.rg_tabbar);
        TabPagerAdapter adapter = new TabPagerAdapter(getSupportFragmentManager());
        vp_content.setAdapter(adapter);

        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                //选中指定位置的单选按钮
                rg_tabbar.check(rg_tabbar.getChildAt(position).getId());
            }
        });

        rg_tabbar.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            for (int i = 0; i < rg_tabbar.getChildCount(); i++) {
                RadioButton tab = (RadioButton) rg_tabbar.getChildAt(i);
                if (tab.getId() == checkedId){
                    vp_content.setCurrentItem(i);
                }
            }
        });
    }
}