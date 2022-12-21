package com.example.chapter11.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 *11.2.1 手势分发流程
 *  * 与手势事件有关的方法主要有三个
 *  *  1、dispatchTouchEvent:进行事件分发处理，返回结果表示该事件是否需要分发。
 *  *  2、onInterceptTouchEvent:进行事件拦截处理
 *  *  3、onTouchEvent:进行事件触摸处理
 *  * 上述手势方法执行者有三个
 *  *  1、页面类：包括Activity及其派生类，可调用方法1和3    1
 *  *  2、容器类：包括从ViewGroup类派生出的各种类容器，可调用1、2和3     2
 *  *  3、控件类：包括从View类派生出的各种类控件，可调用方法1和3        3
 */

public class NotDispatchLayout extends LinearLayout {
    public NotDispatchLayout(Context context) {
        super(context);
    }

    public NotDispatchLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // 在分发触摸事件时触发

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mListener != null){
            mListener.onNotDispatch();
        }
        // 一般容器默认返回true，即允许分发给下级
        return false;
    }


    private NotDispatchListener mListener;
    public interface NotDispatchListener{
        void onNotDispatch();
    }
    public void setNotDispatchListener(NotDispatchListener listener){
        mListener = listener;
    }
}
