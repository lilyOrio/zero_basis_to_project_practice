package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chapter09.adapter.GoodsPagerAdapter;
import com.example.chapter09.util.DataUtil;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private final static String TAG = "TabLayoutActivity";
    private ViewPager vp_content; // 声明一个翻页视图对象
    private TabLayout tab_title; // 声明一个标签布局对象
    private String[] mTitleArray = {"商品", "详情"}; // 标题文字数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        Toolbar tl_head = findViewById(R.id.tl_head);
        tl_head.setTitle("");
        setSupportActionBar(tl_head);
        tab_title = findViewById(R.id.tab_title);
        vp_content = findViewById(R.id.vp_content);

        initTabLayout(); // 初始化标签布局
        initTabViewPager(); // 初始化标签翻页
    }

    private void initTabViewPager() {
        GoodsPagerAdapter adapter = new GoodsPagerAdapter(getSupportFragmentManager(), mTitleArray);
        vp_content.setAdapter(adapter);
        vp_content.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                tab_title.getTabAt(position).select();
            }
        });
    }

    private void initTabLayout() {
        tab_title.addTab(tab_title.newTab().setText(mTitleArray[0]));
        tab_title.addTab(tab_title.newTab().setText(mTitleArray[1]));
        tab_title.addOnTabSelectedListener(this);
    }

    // 在标签选中时触发
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vp_content.setCurrentItem(tab.getPosition());
    }

    // 在标签取消选中时触发
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    // 在标签被重复选中时触发
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // 从menu_overflow.xml中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_overflow, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) { // 点击了工具栏左边的返回箭头
            finish(); // 结束当前页面
        } else if (id == R.id.menu_refresh) { // 点击了刷新图标getNowDataTime
            Toast.makeText(this, "当前刷新时间: " + DataUtil.getNowDateTime("yyyy-MM-dd HH:mm:ss")
                    , Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_about) { // 点击了关于菜单项
            Toast.makeText(this, "这个是标签布局的演示demo", Toast.LENGTH_LONG).show();
            return true;
        } else if (id == R.id.menu_quit) { // 点击了退出菜单项
            finish(); // 结束当前页面
        }
        return super.onOptionsItemSelected(item);
    }

}