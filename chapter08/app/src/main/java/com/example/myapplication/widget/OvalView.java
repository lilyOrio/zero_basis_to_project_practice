package com.example.myapplication.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class OvalView extends View {
    private Paint mPaint = new Paint();
    private int mDrawingAngle = 0;//当前绘制的角度

    public OvalView(Context context) {
        super(context);
    }

    public OvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mDrawingAngle += 30; // 绘制角度增加30度
        //获取布局实际宽高
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        RectF rectF = new RectF(0, 0, width, height);//创建扇形矩形边界

        // 在画布上绘制指定角度的扇形。第四个参数为true表示绘制扇形，为false表示绘制圆弧
        canvas.drawArc(rectF,0,mDrawingAngle,true,mPaint);
    }
}
