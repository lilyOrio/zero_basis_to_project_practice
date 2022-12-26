package com.example.chapter12;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * 为解决长时间间隔切换图片显示太突兀，Android提供了过度图形TransitionDrawable处理两张图片之间的渐变显示，
 * 即淡入淡出动画效果
 */
public class FadeAnimActivity extends AppCompatActivity {
    private ImageView iv_fade_anim; // 声明一个图像视图对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fade_anim);
        iv_fade_anim = findViewById(R.id.iv_fade_anim);
        iv_fade_anim.setOnClickListener(v -> showFadeAnimation());
        showFadeAnimation(); // 开始播放淡入淡出动画
    }

    private void showFadeAnimation() {
        // 淡入淡出动画需要先定义一个图形资源数组，用于变换图片
        Drawable[] drawableArray = {getDrawable(R.drawable.fade_begin), getDrawable(R.drawable.fade_end)};
        // 创建一个用于淡入淡出动画的过渡图形
        TransitionDrawable td_fade = new TransitionDrawable(drawableArray);
        iv_fade_anim.setImageDrawable(td_fade); // 设置过渡图形
        td_fade.setCrossFadeEnabled(true); // 是否启用交叉淡入。启用后淡入效果更柔和
        td_fade.startTransition(3000); // 开始时长3秒的过渡转换
    }
}