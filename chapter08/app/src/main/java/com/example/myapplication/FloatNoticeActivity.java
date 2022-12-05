package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.util.AuthorityUtil;
import com.example.myapplication.util.Utils;
import com.example.myapplication.widget.FloatWindow;

public class FloatNoticeActivity extends AppCompatActivity {
    private EditText et_content; // 声明一个编辑框对象
    private static FloatWindow mFloatWindow; // 声明一个悬浮窗对象
    private TextView tv_content; // 声明一个文本视图对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_notice);
        et_content = findViewById(R.id.et_content);
        findViewById(R.id.btn_open_float).setOnClickListener(v -> openFloatWindow());
        findViewById(R.id.btn_close_float).setOnClickListener(v -> closeFloatWindow());
        // AndroidManifest.xml要先声明悬浮窗权限SYSTEM_ALERT_WINDOW
        // AppOpsManager.OP_SYSTEM_ALERT_WINDOW是隐藏变量（值为24），不能直接引用
        if (!AuthorityUtil.checkOp(this, 24)) { // 未开启悬浮窗权限
            Toast.makeText(this, "请先给该应用开启悬浮窗权限", Toast.LENGTH_SHORT).show();
            // 跳到悬浮窗权限的设置页面
            AuthorityUtil.requestAlertWindowPermission(this);
        }
    }

    private void closeFloatWindow() {
        if (mFloatWindow != null && mFloatWindow.isShow()) {
            mFloatWindow.close(); // 关闭悬浮窗
        }
    }

    private void openFloatWindow() {
        Log.d("lily", "openFloatWindow: ");
        if (mFloatWindow == null){
            mFloatWindow = new FloatWindow(MainApplication.getInstance());
            // 设置悬浮窗的布局内容
            mFloatWindow.setLayout(R.layout.float_notice);
            tv_content = mFloatWindow.mContentView.findViewById(R.id.tv_content);
            LinearLayout ll_float = mFloatWindow.mContentView.findViewById(R.id.ll_float);
            int margin = Utils.dip2px(this, 5);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ll_float.getLayoutParams();
            params.width = Utils.getScreenWidth(this) - 2*margin;
            // 在悬浮窗四周留白
            params.setMargins(margin, margin, margin, margin);
            ll_float.setLayoutParams(params);
            // 设置悬浮窗的点击监听器
            mFloatWindow.setOnFloatListener(v -> mFloatWindow.close());
        }
        if (mFloatWindow != null && !mFloatWindow.isShow()) {
            tv_content.setText(et_content.getText());
            mFloatWindow.show(Gravity.LEFT | Gravity.TOP); // 显示悬浮窗
        }
    }
}