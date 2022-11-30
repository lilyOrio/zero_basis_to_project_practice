package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.util.MeasureUtil;

public class MeasureTextActivity extends AppCompatActivity {
    private TextView tv_desc, tv_text; // 声明一个文本视图对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure_text);
        tv_desc = findViewById(R.id.tv_desc);
        tv_text = findViewById(R.id.tv_text);
        initSizeSpinner(); // 初始化文字大小的下拉框
    }

    private void initSizeSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_select, descArray);
        Spinner sp_size = findViewById(R.id.sp_size);
        sp_size.setPrompt("请选择文字大小");
        sp_size.setAdapter(adapter);
        sp_size.setOnItemSelectedListener(new SizeSelectedListener());
    }

    private String[] descArray = {"12sp", "15sp", "17sp", "20sp", "22sp", "25sp", "27sp", "30sp"};
    private int[] sizeArray = {12, 15, 17, 20, 22, 25, 27, 30};

    class SizeSelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            String text = tv_text.getText().toString();
            int textSize = sizeArray[i];
            tv_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,textSize);
            //计算获取指定文本宽度
            int width = (int) MeasureUtil.getTextWidth(text,textSize);
            // 计算获取指定文本的高度
            int height = (int) MeasureUtil.getTextHeight(text, textSize);
            //显示值单位是dp
            String desc = String.format("下面的文字宽度是%d，高度是%d",width,height);
            tv_desc.setText(desc);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }
}