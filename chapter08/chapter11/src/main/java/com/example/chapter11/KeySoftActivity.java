package com.example.chapter11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 11.1.1 检测软键盘
 * 拦截输入字符可通过注册TextWatcher实现，但该监听器只适用于编辑框控件，无法用与其它控件。
 * 若想让其他控件也能监听按键操作，则要另外调用控件对象的setOnListener方法设置监听器，并
 * 实现监听器接口OnKeyListener的onKey方法。
 */

public class KeySoftActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_soft);
    }
}