package com.example.myapplication;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import com.example.myapplication.util.NotifyUtil;

public class MainApplication extends Application {
    private final static String TAG = "MainApplication";
    private static MainApplication mApp;

    //单例
    public static MainApplication getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            //Android 8.0 开始必须为每个通知分配对应渠道
            NotifyUtil.createNotifyChannel(this,getString(R.string.app_name),
                    getString(R.string.app_name), NotificationManager.IMPORTANCE_LOW);
        }
        Log.d(TAG, "onCreate: ");
        mApp = this;
    }
}
