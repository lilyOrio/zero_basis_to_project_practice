package com.example.myapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

public class FloatWindow extends View {
    private final static String TAG = "FloatWindow";
    private Context mContext;
    private WindowManager wm;
    private static WindowManager.LayoutParams wmParams;//悬浮窗的布局参数
    public View mContentView;//声明一个内容视图对象
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
    @SuppressLint("ClickableViewAccessibility")
    public void setLayout(int layoutId) {
        // 从指定资源编号的布局文件中获取内容视图对象
        mContentView = LayoutInflater.from(mContext).inflate(layoutId, null);
        //接管悬浮窗的触摸事件，使之可以随手势拖动，又可处理点击动作
        mContentView.setOnTouchListener((view, event) -> {
            mScreenX = event.getRawX();
            mScreenY = event.getRawY();
            if (event.getAction() == MotionEvent.ACTION_DOWN) {// 手指按下
                mDownX = mScreenX;
                mDownY = mScreenY;
            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {//手指移动
                updateViewPosition(); // 更新视图的位置
            } else if (event.getAction() == MotionEvent.ACTION_UP) {// 手指松开
                updateViewPosition();
                if (Math.abs(mScreenX - mDownX) < 3 && Math.abs(mScreenY - mDownY) < 3) {//移动距离太小
                    if (mListener != null) { // 响应悬浮窗的点击事件
                        mListener.onFloatClick(view);
                    }
                }
            }
            mLastX = mScreenX;
            mLastY = mScreenY;
            return true;
        });
    }

    // 更新视图的位置
    private void updateViewPosition() {
        // 此处不能直接转为整型，因为小数部分会被截掉，重复多次后就会造成偏移越来越大
        wmParams.x = Math.round(wmParams.x + mScreenX - mLastX);
        wmParams.y = Math.round(wmParams.y + mScreenY - mLastY);
        wm.updateViewLayout(mContentView, wmParams); // 更新内容视图的布局参数
    }

    //显示悬浮窗
    public void show(int gravity) {
        if (mContentView != null) {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
                wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
            } else {
                wmParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            }
            wmParams.format = PixelFormat.RGBA_8888;
            wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            wmParams.alpha = 1.0f; // 1.0为完全不透明，0.0为完全透明
            wmParams.gravity = gravity; // 指定悬浮窗的对齐方式
            wmParams.x = 0;
            wmParams.y = 0;
            // 设置悬浮窗的宽度和高度为自适应
            wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
            wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            // 添加自定义的窗口布局，然后屏幕上就能看到悬浮窗了
            wm.addView(mContentView, wmParams);
            isShowing = true;
        }
    }

    // 关闭悬浮窗
    public void close() {
        if (mContentView != null) {
            wm.removeView(mContentView); // 移除自定义的窗口布局
            isShowing = false;
        }
    }

    // 判断悬浮窗是否打开
    public boolean isShow() {
        return isShowing;
    }

    private FloatClickListener mListener; // 声明一个悬浮窗的点击监听器对象

    // 设置悬浮窗的点击监听器
    public void setOnFloatListener(FloatClickListener listener) {
        mListener = listener;
    }

    // 定义一个悬浮窗的点击监听器接口，用于触发点击行为
    public interface FloatClickListener {
        void onFloatClick(View v);
    }
}
