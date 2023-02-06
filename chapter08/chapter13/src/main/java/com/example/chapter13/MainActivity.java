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
 * 本章介绍App开发对多线程的几种进阶用法，内容包括如何利用Message配合Handler完成主线程与分线程之间的简单通信，如
 * 何通过runOnUiThread方法简化分线程与处理器的通信机制，如何使用工作管理器替代IntentService实现后台任务管理。
 * 13.1.1 分线程通过Handler操作界面
 * 由分线程向主线程传递消息的过程有四个步骤：
 * 1、在主线程中构造一个处理器对象，并启动分线程：在Android中启动分线程有两种方式————一是直接调用线程实例的start
 * 方法一是通过处理器对象的post方法启动线程实例。
 * 2、在分线程中构造一个Message类型的消息包：Message是线程通信存放消息的包裹，其作用类似于Intent机制和Bundle工具。
 * 消息实例可以通过Message的obtain方法获得，也可以通过处理器对象的obtainMessage方法获得。获取消息实例后再给它补充
 * 详细的包裹信息（其中replyTo：Messager类型在跨进程通信中使用）
 * 3、在分线程中通过处理器对象将Message消息发出去:调用send***方法
 * 4、主线程的Handler对象处理接收到的消息：主线程收到分线程发送的消息后，需要实现处理器对象的handleMessage方法，
 * 在该方法中根据消息内容分别进行相应处理。因为handleMessage方法在主线程（UI线程）中调用。所以方法内部可以直接操
 * 作界面元素。
 * 13.1.2 通过runOnUiThread方法快速操作界面
 * 13.1.3 工作管理器 WorkManager
 * 对于后台异步服务，官方建议改为使用工作管理器 WorkManager
 * 对于Android6.0或以上版本，它通过JobScheduler完成后台任务；对于Android6.0以下版本，通过AlarmManager和广播
 * 接收器组合完成后台任务。无论用哪种方案，后台任务最终都是由线程池Executor执行的。
 * 1、先引入依赖库
 * 2、定义一个处理后台业务逻辑的工作者，该工作者继承自Worker抽象类
 * 3、自定义工作者必须实现构造方法（可获得外部传来的请求数据），并重写doWork方法（处理具体的业务逻辑，该方法运行于
 * 分线程，不得操作界面控件）
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}