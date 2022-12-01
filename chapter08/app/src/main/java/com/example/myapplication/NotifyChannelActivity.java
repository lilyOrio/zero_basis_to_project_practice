package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NotifyChannelActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_title; // 声明一个编辑框对象
    private EditText et_message; // 声明一个编辑框对象
    private String mChannelId = "0"; // 通知渠道的编号
    private String mChannelName; // 通知渠道的名称
    private int mImportance; // 通知渠道的级别

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_channel);

        et_title = findViewById(R.id.et_title);
        et_message = findViewById(R.id.et_message);
        findViewById(R.id.btn_send_channel).setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            initImportanceSpinner(); // 初始化渠道级别的下拉框
        }
    }

    private void initImportanceSpinner() {

    }

    @Override
    public void onClick(View view) {

    }
}