package com.example.chapter09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 第九章 组合控件
 *
 * 本章主要介绍App开发常用的一些组合控件的用法，主要包括：如何实现底部标签栏，如何应用顶部导航栏，
 * 如何利用循环视图实现3中增强型列表，如何利用二代翻页视图实现更炫的翻页效果。然后结合本章所学知识，
 * 演示一个实战项目，“电商首页”的设计与实现
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}