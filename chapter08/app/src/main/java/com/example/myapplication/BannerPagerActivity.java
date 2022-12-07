package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.util.Utils;
import com.example.myapplication.widget.BannerPager;

import java.util.ArrayList;
import java.util.List;

public class BannerPagerActivity extends AppCompatActivity implements BannerPager.BannerClickListener {
    private static final String TAG = "BannerPagerActivity";
    private TextView tv_pager;

    private List<Integer> getImageList() {
        ArrayList<Integer> imageList = new ArrayList<Integer>();
        imageList.add(R.drawable.banner_1);
        imageList.add(R.drawable.banner_2);
        imageList.add(R.drawable.banner_3);
        imageList.add(R.drawable.banner_4);
        imageList.add(R.drawable.banner_5);
        return imageList; // 返回默认的广告图片列表
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_pager);

        tv_pager = findViewById(R.id.tv_pager);
        // 从布局文件中获取名叫banner_pager的广告轮播条
        BannerPager banner = findViewById(R.id.banner_pager);
        //获取广告轮播条的布局参数
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) banner.getLayoutParams();
        params.height = (int) (Utils.getScreenWidth(this) * 250f / 640);
        banner.setLayoutParams(params);
        banner.setOnBannerListener(this);
        banner.setImage(getImageList());
        banner.start();
    }

    @Override
    public void onBannerClick(int position) {
        String desc = String.format("您点击了第%d张图片", position + 1);
        tv_pager.setText(desc);
    }
}