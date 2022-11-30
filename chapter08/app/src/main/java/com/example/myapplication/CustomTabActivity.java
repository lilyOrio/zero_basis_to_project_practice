package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myapplication.adapter.ImagePagerAdapter;
import com.example.myapplication.bean.GoodsInfo;

import java.util.ArrayList;

public class CustomTabActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private ArrayList<GoodsInfo> mGoodsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);

        mGoodsList = GoodsInfo.getDefaultList();
        ImagePagerAdapter adapter = new ImagePagerAdapter(this, mGoodsList);

        ViewPager vp_content = findViewById(R.id.vp_content);
        vp_content.setAdapter(adapter);
        vp_content.setCurrentItem(0); // 设置翻页视图显示第一页
        vp_content.addOnPageChangeListener(this); // 给翻页视图添加页面变更监听器
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(this, "您翻到的手机品牌是：" + mGoodsList.get(position).name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}