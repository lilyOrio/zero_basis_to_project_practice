package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chapter09.adapter.LinearDynamicAdapter;
import com.example.chapter09.bean.NewsInfo;
import com.example.chapter09.widget.RecyclerExtras;
import com.example.chapter09.widget.SpacesDecoration;

import java.util.List;
import java.util.Random;

public class RecyclerDynamicActivity extends AppCompatActivity implements View.OnClickListener
        , RecyclerExtras.OnItemClickListener, RecyclerExtras.OnItemLongClickListener, RecyclerExtras.OnItemDeleteClickListener {
    private RecyclerView rv_dynamic; // 声明一个循环视图对象
    private LinearDynamicAdapter mAdapter; // 声明一个线性适配器对象
    private List<NewsInfo> mPublicList = NewsInfo.getDefaultList(); // 当前的公众号信息列表
    private List<NewsInfo> mOriginList = NewsInfo.getDefaultList(); // 原始的公众号信息列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_dynamic);
        findViewById(R.id.btn_recycler_add).setOnClickListener(this);
        initRecyclerDynamic(); // 初始化动态线性布局的循环视图
    }

    private void initRecyclerDynamic() {
        rv_dynamic = findViewById(R.id.rv_dynamic);
        LinearLayoutManager manager = new LinearLayoutManager(
                this, RecyclerView.VERTICAL, false);
        rv_dynamic.setLayoutManager(manager);

        mAdapter = new LinearDynamicAdapter(this, mPublicList);
        mAdapter.setOnItemClickListener(this); // 设置线性列表的点击监听器
        mAdapter.setOnItemLongClickListener(this); // 设置线性列表的长按监听器
        mAdapter.setOnItemDeleteClickListener(this); // 设置线性列表的删除按钮监听器
        rv_dynamic.setAdapter(mAdapter); // 设置循环视图的线性适配器
        rv_dynamic.setItemAnimator(new DefaultItemAnimator());  // 设置循环视图的动画效果
        rv_dynamic.addItemDecoration(new SpacesDecoration(1));  // 设置循环视图的空白装饰
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_recycler_add) {
            int position = new Random().nextInt(mOriginList.size()-1);
            NewsInfo old_item = mOriginList.get(position);
            NewsInfo new_item = new NewsInfo(old_item.pic_id,old_item.title,old_item.desc);
            mPublicList.add(0,new_item);
            mAdapter.notifyItemInserted(0); // 通知适配器列表在第一项插入数据
            rv_dynamic.scrollToPosition(0); // 让循环视图滚动到第一项所在的位置
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        @SuppressLint("DefaultLocale") String desc = String.format("您点击了第%d项，标题是%s", position + 1,
                mPublicList.get(position).title);
        Toast.makeText(this, desc, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        NewsInfo item = mPublicList.get(position);
        item.isPressed = true;
        mPublicList.set(position, item);//更新列表数据
        mAdapter.notifyItemChanged(position);// 通知适配器列表在第几项发生变更
    }

    @Override
    public void onItemDeleteClick(View view, int position) {
        mPublicList.remove(position);
        mAdapter.notifyItemRemoved(position); // 通知适配器列表在第几项删除数据
    }
}