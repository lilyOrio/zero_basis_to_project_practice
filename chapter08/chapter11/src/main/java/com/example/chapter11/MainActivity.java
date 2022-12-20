package com.example.chapter11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 第十一章 事件交互
 *
 * 本章介绍app开发常见的一些事件交互技术，主要包括：如何检测并接管按键事件；如何对触摸事件进行分发、拦截与处理；
 * 如何根据触摸行为辨别几种手势动作；如何正确避免手势冲突的意外状况。
 * 实战项目 -->仿美图秀秀的抠图工具
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}