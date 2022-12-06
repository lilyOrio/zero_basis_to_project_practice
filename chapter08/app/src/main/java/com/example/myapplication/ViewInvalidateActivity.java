package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.myapplication.widget.OvalView;

public class ViewInvalidateActivity extends AppCompatActivity {
    private OvalView ov_validate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_invalidate);
        ov_validate = findViewById(R.id.ov_validate);
        initRefreshSpinner(); // 初始化刷新方式的下拉框
    }

    private void initRefreshSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_select, refreshArray);
        Spinner sp_refresh = findViewById(R.id.sp_refresh);
        sp_refresh.setAdapter(adapter);
        sp_refresh.setPrompt("选择刷新方式");
        sp_refresh.setSelection(0);
        sp_refresh.setOnItemSelectedListener(new RefreshSelectListener());
    }

    private String[] refreshArray = {
            "主线程调用invalidate",
            "主线程调用postInvalidate",
            "延迟3秒后刷新",
            "分线程调用invalidate",
            "分线程调用postInvalidate"
    };

    class RefreshSelectListener implements OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int arg2, long l) {
            if (arg2 == 0) {  // 主线程调用invalidate
                // 刷新视图（用于主线程）
                ov_validate.invalidate();
            } else if (arg2 == 1) {  // 主线程调用postInvalidate
                // 刷新视图（主线程和分线程均可使用）
                ov_validate.postInvalidate();
            } else if (arg2 == 2) {  // 延迟3秒后刷新
                // 延迟若干时间后再刷新视图
                ov_validate.postInvalidateDelayed(3000);
            } else if (arg2 == 3) {  // 分线程调用invalidate
                // invalidate不是线程安全的，虽然下面代码在分线程中调用invalidate方法也没报错，但在复杂场合可能出错
                new Thread(() -> {
                    ov_validate.invalidate();// 刷新视图（用于主线程）
                }
                ).start();
            } else if (arg2 == 4) {  // 分线程调用postInvalidate
                // postInvalidate是线程安全的，分线程中建议调用postInvalidate方法来刷新视图
                new Thread(() -> {
                ov_validate.postInvalidate();// 刷新视图（主线程和分线程均可使用
                }).start();
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}