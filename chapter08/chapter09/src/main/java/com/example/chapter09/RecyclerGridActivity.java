package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.chapter09.adapter.RecyclerGridAdapter;
import com.example.chapter09.bean.NewsInfo;
import com.example.chapter09.widget.SpacesDecoration;

public class RecyclerGridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_grid);
        initRecyclerGrid();
    }

    private void initRecyclerGrid() {
        RecyclerView rv_grid = findViewById(R.id.rv_grid);
        // 创建一个网格布局管理器（每行5列）
        GridLayoutManager manager = new GridLayoutManager(this, 5);
        rv_grid.setLayoutManager(manager);
        RecyclerGridAdapter adapter = new RecyclerGridAdapter(this, NewsInfo.getDefaultGrid());
        adapter.setOnItemClickListener(adapter); // 设置网格列表的点击监听器
        adapter.setOnItemLongClickListener(adapter);
        rv_grid.setAdapter(adapter);
        rv_grid.setItemAnimator(new DefaultItemAnimator());  // 设置循环视图的动画效果
        rv_grid.addItemDecoration(new SpacesDecoration(1));  // 设置循环视图的空白装饰
    }
}