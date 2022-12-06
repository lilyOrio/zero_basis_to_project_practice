package com.example.myapplication.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PieAnimation extends View {
    private Paint mPaint = new Paint();
    private Handler mHandler = new Handler(Looper.myLooper()); // 声明一个处理器对象
    private int mDrawingAngle = 0;
    private boolean isRunning = false;

    public PieAnimation(Context context) {
        super(context);
    }

    public PieAnimation(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.GREEN);
    }

    public void start(){
        mDrawingAngle = 0;
        isRunning = true;
        mHandler.post(mRefresh);
    }

    private Runnable mRefresh = new Runnable() {
        @Override
        public void run() {
            mDrawingAngle += 3;
            if (mDrawingAngle <= 270){
                invalidate();
                mHandler.postDelayed(this,70);
            }else {
                isRunning = false;
            }
        }
    };

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isRunning){
            int width = getMeasuredWidth();
            int height = getMeasuredHeight();
            int diameter = Math.min(width,height);
            // 创建扇形的矩形边界
            RectF rectf = new RectF((width - diameter) / 2, (height - diameter) / 2,
                    (width + diameter) / 2, (height + diameter) / 2);
            // 在画布上绘制指定角度的图形。第四个参数为true绘制扇形，为false绘制圆弧
            canvas.drawArc(rectf, 0, mDrawingAngle, true, mPaint);
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
