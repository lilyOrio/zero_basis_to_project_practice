package com.example.chapter12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 第12章 动画特效
 * 本章介绍app开发中常见的动画特效技术，主要包括：使用帧动画实现电影播放效果、使用补间动画实现视图的4种基本状态变化、
 * 如何使用属性动画实现视图各种状态的动态变换效果以及借助绘图层次与滚动器是心啊动画效果。
 * 实战项目：仿手机qq的动感影集
 *
 * 12.2 补间动画
 *  本节主要介绍补间动画的原理和用法，内容：4种补间动画及其基本用法、补间动画的原理和基于旋转动画的思想实现摇摆动画、
 *  通过集合通话同时展示多种动画效果
 *
 *  12.3 属性动画
 *  本节介绍属性动画的应用场景与进阶用法，内容包括：为何属性动画是补间动画的升级版以及属性动画的基本用法；运用属性动画
 *  组合实现多个属性动画的同时播放与顺序播放效果;对动画技术中的插值器和估值器进行分析，并演示不同插值器的动画效果；如何
 *  利用估值器实现直播网站常见的打赏动画
 *  12.3.3 插值器和估值器
 *  插值器：用来控制属性的变化速率，可以理解为动画的播放速度（默认先加速后减速）
 *  调用setInterpolator方法设置对应的插值器实现类
 *  补间动画、属性动画都可以设置插值器
 *  估值器：专用于属性动画，主要描述该属性的数值变化要采用什么单位
 *  调用setEvaluator设置估值器实现类
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}