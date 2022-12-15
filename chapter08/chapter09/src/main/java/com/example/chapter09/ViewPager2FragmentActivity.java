package com.example.chapter09;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.chapter09.adapter.MobilePagerAdapter;
import com.example.chapter09.bean.GoodsInfo;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class ViewPager2FragmentActivity extends AppCompatActivity {
    private List<GoodsInfo> mGoodsList = GoodsInfo.getDefaultList(); // 商品信息列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_fragment);

        TabLayout tab_title = findViewById(R.id.tab_title);
        ViewPager2 vp2_content = findViewById(R.id.vp2_content);
        MobilePagerAdapter adapter = new MobilePagerAdapter(this, mGoodsList);
        vp2_content.setAdapter(adapter);
        // 把标签布局跟翻页视图通过指定策略连为一体，二者在页面切换时一起联动
        new TabLayoutMediator(tab_title, vp2_content, (tab, position) -> {
            tab.setText(mGoodsList.get(position).name);
        }).attach();
    }
}