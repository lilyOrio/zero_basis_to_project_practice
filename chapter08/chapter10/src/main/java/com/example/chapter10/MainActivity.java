package com.example.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * 第十章
 *
 * 本章介绍应用安装包的基本制作规范，主要包括：如何导出即精美又精简的apk文件、如何按照上线规范
 * 调整APP的相关设置、如何对APK文件进行安全加固，以防止安装包被破解。
 *
 *10.1 应用打包
 * 10.1.1 导出apk包
 * 10.1.2 制作app图标
 * 10.1.3 给apk瘦身 ：去除冗余功能、精简无用功能、压缩图片大小
 *
 *10.2 规范处理 ：正确设置app的版本编号和版本名称、把app从调试模式切换到发布模式
 *              给多渠道同时打包apk文件
 * 10.2.1 版本设置
 * 10.2.2 发布模式
 * 10.2.3 多渠道打包
 *
 *10.3 安全加固
 * 10.3.1 反编译 ：apktool、dex2jar、jd-gui
 * 10.3.2 代码混淆 ：proguard
 *                  ① 压缩apk包大小，删除无用代码，并简化部分类名和方法名
 *                  ② 加大破解源码难度，部分类名和方法名被重命名使得程序逻辑变得难以理解
 * 10.3.3 第三方加固及重签名 ：加固后的apk需重新签名才能成为合法的apk安装包
 *                          360加固、APKSign
 *
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}