package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.util.ViewUtil;

public class NotifySimpleActivity extends AppCompatActivity {

    private EditText et_title, et_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_simple);

        et_title = findViewById(R.id.et_title);
        et_message = findViewById(R.id.et_message);

        findViewById(R.id.btn_send_simple).setOnClickListener(view -> {
            ViewUtil.hideOneInputMethod(this, et_message); // 隐藏输入法软键盘
            if (TextUtils.isEmpty(et_title.getText())) {
                Toast.makeText(this, "请填写消息标题", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(et_message.getText())) {
                Toast.makeText(this, "请填写消息内容", Toast.LENGTH_SHORT).show();
                return;
            }
            String title = et_title.getText().toString();
            String message = et_message.getText().toString();
            sendSimpleNotify(title, message);
        });
    }

    // 发送简单的通知消息（包括消息标题和消息内容）
    private void sendSimpleNotify(String title, String message) {
        // 发送消息之前要先创建通知渠道，创建代码见MainApplication.java
        // 创建一个跳转到活动页面的意图
        Intent clickIntent = new Intent(NotifySimpleActivity.this, MainActivity.class);
        //创建一个用于页面跳转的延时意图
        PendingIntent contentIntent = PendingIntent.getActivity(NotifySimpleActivity.this,
                R.string.app_name, clickIntent, PendingIntent.FLAG_IMMUTABLE);
        // 创建一个通知消息的建造器
        Notification.Builder builder = new Notification.Builder(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0开始必须给每个通知分配对应的渠道
            builder = new Notification.Builder(this, getString(R.string.app_name));
        }
        builder.setContentIntent(contentIntent)//设置内容的点击意图
                .setAutoCancel(true) //点击通知栏后是否自动清除该通知
                .setSmallIcon(R.mipmap.ic_launcher) //设置应用名称左边的小图标
                .setSubText("这里是副本")//设置通知栏里面的附件说明文本
                //设置通知栏右边的大图标
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_app))
                .setProgress(100,60,false)//设置进度条和具体进度（会将显示内容覆盖掉，如要显示可将内容显示放到标题右边）
                .setUsesChronometer(true)//设置是否显示计时器
                .setContentTitle(title)
                .setContentText(message);
        Notification notify = builder.build();//利用通知构造器创建一个通知对象
        //从系统服务中获取通知管理器
        NotificationManager notifyMgr = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        //使用通知管理器推送通知，就可以在手机通知栏看到该消息
        notifyMgr.notify(R.string.app_name, notify);
        Log.d("lily", "sendSimpleNotify: ");
    }

}