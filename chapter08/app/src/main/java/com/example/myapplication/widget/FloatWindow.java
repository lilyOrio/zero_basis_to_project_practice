package com.example.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.Nullable;

public class FloatWindow extends View {
    private final static String TAG = "FloatWindow";
    private Context mContext;
    private WindowManager wm;
    private static WindowManager.LayoutParams wmParams;//悬浮窗的布局参数
    public View mContextView;//声明一个内容视图对象
    private float mScreenX, mScreenY;//触摸点在屏幕上的横纵坐标
    private float mLastX, mLastY;//上次触摸点在屏幕的坐标
    private float mDownX, mDownY;//按下点的坐标
    private boolean isShowing;//是否正在显示


    public FloatWindow(Context context) {
        super(context);
        // 从系统中获取窗口管理器，后续将通过该管理器添加悬浮窗
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wmParams == null) {
            wmParams = new WindowManager.LayoutParams();
        }
        mContext = context;
    }

    //设置悬浮窗的内容布局
    public void setLayout(int layoutId){
        // 从指定资源编号的布局文件中获取内容视图对象
        mContextView = LayoutInflater.from(mContext).inflate(layoutId,null);

    }
}
