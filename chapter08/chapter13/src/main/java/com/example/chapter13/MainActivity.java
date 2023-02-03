package com.example.chapter13;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 第13章 网络通信
 * 本章节主要介绍App常用的一些网络通信技术，主要包括：如何以官方推荐的方式使用多线程技术，如何通过okhttp实现常见的
 * HTTP接口访问操作，如何使用Glide框架加载网络图片，如何分别运用Socket和WebSocket实现即时通信功能等。最后结合本
 * 章知识演示一个实战项目“仿微信私聊和群聊”的设计与实现。
 *
 * 13.1 多线程
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}