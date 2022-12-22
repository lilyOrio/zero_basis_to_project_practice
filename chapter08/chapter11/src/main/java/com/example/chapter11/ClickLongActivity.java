package com.example.chapter11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.chapter11.widget.ClickView;

public class ClickLongActivity extends AppCompatActivity {
    private TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_long);
        tv_desc = findViewById(R.id.tv_desc);
        ClickView cv_gesture = findViewById(R.id.cv_gesture);
        cv_gesture.setLiftListener(new ClickView.LiftListener() {
            @Override
            public void onLift(long time_interval, float pressure) {
                String gesture = time_interval > 500 ? "长按" :"短按";
                String desc = String.format("本次按压时长为%d毫秒，属于%s动作。\n按压的压力峰值为%f",
                        time_interval, gesture, pressure);
                tv_desc.setText(desc);
            }
        });
    }
}