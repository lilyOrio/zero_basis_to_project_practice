package com.example.chapter09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter09.util.DataUtil;

public class OverflowMenuActivity extends AppCompatActivity {

    private TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overflow_menu);
        tv_desc = findViewById(R.id.tv_desc);
        Toolbar tl_head = findViewById(R.id.tl_head);
        tl_head.setTitle("溢出菜单页面"); // 设置工具栏的标题文字
        setSupportActionBar(tl_head); // 使用tl_head替换系统自带的ActionBar
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //从menu_overflow.xml 中构建菜单界面布局
        getMenuInflater().inflate(R.menu.menu_overflow,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://点击工具栏左边的返回箭头
                finish();
                break;
            case R.id.menu_refresh:
                tv_desc.setText("当前刷新时间：" + DataUtil.getNowTime());
                break;
            case R.id.menu_about:
                Toast.makeText(this,"这是工具栏的演示demo",Toast.LENGTH_LONG).show();
                break;
            case R.id.menu_quit:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}