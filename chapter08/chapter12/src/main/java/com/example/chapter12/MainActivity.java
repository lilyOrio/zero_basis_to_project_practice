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
 *
 *  12.4 遮盖动画及滚动器
 *  本节介绍其他几种常见的动画实现手段，内容包括：遮盖动画画布的绘图层次类型及其相互之间的区别；如何利用绘图层次百叶窗动画
 *  和马赛克动画；滚动器动画在平滑翻书特效中的具体运用。
 *  12.4.1 画布的绘图层次
 *  画布Canvas上的绘图操作都是在一个图层上进行的，这意味着如果存在重叠区域，后面绘制的图形就必然覆盖前面的图形。
 *  绘图是比较复杂的事情，不是直接覆盖这么简单，有些特殊的绘图操作往往需要做与、或、非运算，如此才能实现百变的图像特效。
 *  12.4.3 利用滚动器实现平滑翻页
 *  以平滑翻书为例，在自定义的滚动布局中，需要重写onTouchEvent方法，分别记录手势按下和松开的起始点，再计算这两点的距离
 *  是否超过屏幕宽度的一半，以确定翻页还是回退。无论是翻页还是回退，都得调用startScroll方法执行滚动操作，同时重写布局的
 *  computeScroll方法，根据当前的滚动距离设置书页的偏移量，并在滚到终点时结束滚动操作。
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}