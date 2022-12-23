package com.example.chapter12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 第12章 动画特效
 * 本章介绍app开发中常见的动画特效技术，主要包括：使用帧动画实现电影播放效果、使用补间动画实现视图的4种基本状态变化、
 * 如何使用属性动画实现视图各种状态的动态变换效果以及借助绘图层次与滚动器是心啊动画效果。
 * 实战项目：仿手机qq的动感影集
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}