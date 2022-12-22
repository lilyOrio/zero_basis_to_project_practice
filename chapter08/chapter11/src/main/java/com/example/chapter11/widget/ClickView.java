package com.example.chapter11.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.chapter11.util.Utils;

/**
 * 根据按压时长判断点击还是长按
 */
public class ClickView extends View {
    private static final String TAG = "ClickView";
    private Paint mPaint = new Paint(); // 声明一个画笔对象
    private long mLastTime; // 上次按下手指的系统时间
    private PointF mPos; // 按下手指的坐标点
    private float mPressure = 0; // 按压的压力值
    private int dip_10;

    public ClickView(Context context) {
        super(context);
    }

    public ClickView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dip_10 = Utils.dip2px(context, 10);
        mPaint.setColor(Color.DKGRAY); // 设置画笔的颜色
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mPos != null){
            canvas.drawCircle(mPos.x,mPos.y,dip_10*mPressure,mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || (event.getPressure() > mPressure)) {
            mPos = new PointF(event.getX(), event.getY());
            mPressure = event.getPressure();
        }
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastTime = event.getEventTime();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null){
                 mListener.onLift(event.getEventTime()-mLastTime,mPressure);
                }
        }
        postInvalidate(); // 立即刷新视图（线程安全方式）
        return true;
    }

    private LiftListener mListener; // 声明一个手势抬起监听器
    public void setLiftListener(LiftListener listener) {
        mListener = listener;
    }

    // 定义一个手势抬起的监听器接口
    public interface LiftListener {
        void onLift(long time_interval, float pressure);
    }
}
