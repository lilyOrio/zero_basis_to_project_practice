package com.example.chapter11;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.chapter11.util.DateUtil;

/**
 * 11.1.2 检测物理按键
 * <p>
 * 除了给控件注册按键监听外，还可以在活动页面上检测物理按键，即重写Activity的 onKeyDown 方法。
 * 该方法与 onKey 类似，同样拥有按键编码与按键事件keyEvent两个参数。当然两者使用也是有差异的：
 * 1、onKeyDown只能在活动代码中使用，onKey 只要注册监听都可以使用
 * 2、onKeyDown只能检测物理按键，onKey都可以检测
 * 3、onKeyDown不区分按下松开两个动作
 */
@SuppressLint("DefaultLocale")
public class KeyHardActivity extends AppCompatActivity {
    private TextView tv_result; // 声明一个文本视图对象
    private String desc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_key_hard);
        tv_result = findViewById(R.id.tv_result);
        initDesktopReceiver(); // 初始化桌面广播
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        desc = String.format("%s物理按键的编码是%d", desc, keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            desc = String.format("%s，按键为返回键", desc);
            // 延迟3秒后启动页面关闭任务
            new Handler(Looper.myLooper()).postDelayed(() -> finish(), 3000);
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            desc = String.format("%s，按键为加大音量键", desc);
        } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            desc = String.format("%s，按键为减小音量键", desc);
        }
        desc = desc + "\n";
        tv_result.setText(desc);
        // 返回true表示不再响应系统动作，返回false表示继续响应系统动作
        return true;
    }

    // 初始化桌面广播。用于监听按下主页键和任务键
    private void initDesktopReceiver() {
// 创建一个返回桌面的广播接收器
        mDesktopReceiver = new DesktopReceiver();
        // 创建一个意图过滤器，只接收关闭系统对话框（即返回桌面）的广播
        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
        registerReceiver(mDesktopReceiver, intentFilter); // 注册广播接收器
    }

    private DesktopReceiver mDesktopReceiver;
    class DesktopReceiver extends BroadcastReceiver {
        private String SYSTEM_DIALOG_REASON_KEY = "reason"; // 键名
        private String SYSTEM_DIALOG_REASON_HOME = "homekey"; // 主页键
        private String SYSTEM_DIALOG_REASON_TASK = "recentapps"; // 任务键

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (!TextUtils.isEmpty(reason)) {
                    if (reason.equals(SYSTEM_DIALOG_REASON_HOME)) { // 按下了主页键
                        desc = String.format("%s%s\t 按键为主页键\n", desc, DateUtil.getNowTime());
                        tv_result.setText(desc);
                    } else if (reason.equals(SYSTEM_DIALOG_REASON_TASK)) { // 按下了任务键
                        desc = String.format("%s%s\t 按键为任务键\n", desc, DateUtil.getNowTime());
                        tv_result.setText(desc);
                    }
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDesktopReceiver); // 注销广播接收器
    }
}