package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class ToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        Toolbar tl_head = findViewById(R.id.tl_head);
        tl_head.setTitle("工具栏页面");
        setSupportActionBar(tl_head);// 使用tl_head替换系统自带的ActionBar
        tl_head.setTitleTextColor(Color.RED);
        tl_head.setLogo(R.drawable.ic_app);
        tl_head.setSubtitle("toolbar");
        tl_head.setSubtitleTextColor(Color.YELLOW);
        tl_head.setBackgroundResource(R.color.blue_light);
        tl_head.setNavigationIcon(R.drawable.ic_back);
        // 给tl_head设置导航图标的点击监听器
        // setNavigationOnClickListener必须放到setSupportActionBar之后，不然不起作用
        tl_head.setNavigationOnClickListener(view -> finish());
    }
}