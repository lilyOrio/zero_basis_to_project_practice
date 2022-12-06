package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HandlerPostActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_count; // 声明一个按钮对象
    private TextView tv_result; // 声明一个文本视图对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_post);
        btn_count = findViewById(R.id.btn_count);
        tv_result = findViewById(R.id.tv_result);
        btn_count.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_count) {
            if (!isStarted) {
                btn_count.setText("停止计数");
                mHandler.post(mCounter);
            } else {
                btn_count.setText("开始计数");
                mHandler.removeCallbacks(mCounter);
            }
            isStarted = !isStarted;
        }
    }

    private boolean isStarted = false;
    private Handler mHandler = new Handler(Looper.myLooper());
    private int mCount = 0;

    private Runnable mCounter = new Runnable() {
        @Override
        public void run() {
            mCount++;
            tv_result.setText("当前计数值为：" + mCount);
            mHandler.postDelayed(this, 1000);// 延时1秒后重复计数任务
        }
    };
}