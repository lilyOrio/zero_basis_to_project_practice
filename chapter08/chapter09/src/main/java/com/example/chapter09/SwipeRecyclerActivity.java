package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.example.chapter09.adapter.LinearDynamicAdapter;
import com.example.chapter09.bean.NewsInfo;
import com.example.chapter09.widget.RecyclerExtras;
import com.example.chapter09.widget.SpacesDecoration;

import java.util.List;
import java.util.Random;

public class SwipeRecyclerActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        RecyclerExtras.OnItemClickListener, RecyclerExtras.OnItemLongClickListener, RecyclerExtras.OnItemDeleteClickListener {
    private SwipeRefreshLayout srl_dynamic; // 声明一个下拉刷新布局对象
    private RecyclerView rv_dynamic; // 声明一个循环视图对象
    private LinearDynamicAdapter mAdapter; // 声明一个线性适配器对象
    private List<NewsInfo> mPublicList = NewsInfo.getDefaultList(); // 当前的公众号信息列表
    private List<NewsInfo> mOriginList = NewsInfo.getDefaultList(); // 原始的公众号信息列表
    private Handler mHandler = new Handler(Looper.myLooper()); // 声明一个处理器对象

    private Runnable mRefresh = () -> {
        srl_dynamic.setRefreshing(false); // 结束下拉刷新布局的刷新动作
        int position = new Random().nextInt(mOriginList.size() - 1); // 获取一个随机位置
        NewsInfo old_item = mOriginList.get(position);
        NewsInfo new_item = new NewsInfo(old_item.pic_id, old_item.title, old_item.desc);
        mPublicList.add(0, new_item); // 在顶部添加一条公众号消息
        mAdapter.notifyItemInserted(0); // 通知适配器列表在第一项插入数据
        rv_dynamic.scrollToPosition(0); // 让循环视图滚动到第一项所在的位置
        // 当循环视图的列表项已经占满整个屏幕时，再往顶部添加一条新记录，
        // 感觉屏幕没有发生变化，也没看到插入动画。
        // 此时就要调用scrollToPosition(0)方法，表示滚动到第一条记录。
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_recycler);
        // 从布局文件中获取名叫srl_dynamic的下拉刷新布局
        srl_dynamic = findViewById(R.id.srl_dynamic);
        srl_dynamic.setOnRefreshListener(this); // 设置下拉布局的下拉刷新监听器
        // 设置下拉刷新布局的进度圆圈颜色
        srl_dynamic.setColorSchemeResources(
                R.color.red, R.color.orange, R.color.green, R.color.blue);
        initRecyclerDynamic();
    }

    private void initRecyclerDynamic() {
        rv_dynamic = findViewById(R.id.rv_dynamic);
        LinearLayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rv_dynamic.setLayoutManager(manager);

        mAdapter = new LinearDynamicAdapter(this, mPublicList);
        mAdapter.setOnItemClickListener(this); // 设置线性列表的点击监听器
        mAdapter.setOnItemLongClickListener(this); // 设置线性列表的长按监听器
        mAdapter.setOnItemDeleteClickListener(this); // 设置线性列表的删除按钮监听器
        rv_dynamic.setAdapter(mAdapter);  // 设置循环视图的线性适配器
        rv_dynamic.setItemAnimator(new DefaultItemAnimator());  // 设置循环视图的动画效果
        rv_dynamic.addItemDecoration(new SpacesDecoration(1));
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(mRefresh, 2000);
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
        item.isPressed = !item.isPressed;
        mPublicList.set(position, item);
        mAdapter.notifyItemChanged(position); // 通知适配器列表在第几项发生变更
    }

    @Override
    public void onItemDeleteClick(View view, int position) {
        mPublicList.remove(position);
        mAdapter.notifyItemRemoved(position); // 通知适配器列表在第几项删除数据
    }
}