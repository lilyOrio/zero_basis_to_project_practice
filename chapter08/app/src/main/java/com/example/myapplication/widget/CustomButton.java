package com.example.myapplication.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.myapplication.R;

public class CustomButton extends Button {
    //自定义控件 step1.构造方法
    //配置优先级：xml文件的style属性 > defStyleAttr > defStyleRes
    public CustomButton(Context context) {
        super(context);
    }

    public CustomButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.customButtonStyle);//设置默认样式属性
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, 0,R.style.CommonButton);
    }

    public CustomButton(Context context, AttributeSet attrs, int defStyleAttr,int defStyleRes) {
        super(context, attrs, defStyleAttr,defStyleRes);
    }

    //step2.重写 onMeasure 方法，即测量尺寸
}
