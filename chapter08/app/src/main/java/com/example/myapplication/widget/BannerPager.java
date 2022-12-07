package com.example.myapplication.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.R;
import com.example.myapplication.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class BannerPager extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "BannerPager";
    private Context mContext;
    private ViewPager vp_banner;
    private RadioGroup rg_indicator;
    private List<ImageView> mViewList = new ArrayList<>();
    private int mInterval = 2000;

    public BannerPager(Context context) {
        super(context);
    }

    public BannerPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    //设置广告图片列表
    public void setImage(List<Integer> imageList) {
        int dip_15 = Utils.dip2px(mContext, 15);
        //根据图片列表生成图像视图列表
        for (int i = 0; i < imageList.size(); i++) {
            Integer imageResId = imageList.get(i);
            ImageView iv = new ImageView(mContext);
            iv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setImageResource(imageResId);
            iv.setOnClickListener(this);
            mViewList.add(iv);
        }
        vp_banner.setAdapter(new ImageAdapter());
        vp_banner.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                setSelectedButton(position); // 高亮显示该位置的指示按钮
            }
        });
        //根据图片列表生成RadioButton列表
        for (int i = 0; i < imageList.size(); i++) {
            RadioButton radio = new RadioButton(mContext);
            radio.setLayoutParams(new RadioGroup.LayoutParams(dip_15, dip_15));
            radio.setButtonDrawable(R.drawable.indicator_selector);
            rg_indicator.addView(radio);
        }
        vp_banner.setCurrentItem(0);
        setSelectedButton(0);
    }

    private void setSelectedButton(int position) {
        ((RadioButton) rg_indicator.getChildAt(position)).setChecked(true);
    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.banner_pager, null);
        vp_banner = view.findViewById(R.id.vp_banner);
        rg_indicator = view.findViewById(R.id.rg_indicator);
        addView(view);//将该布局视图添加到广告轮播条
    }

    private Handler mHandler = new Handler(Looper.myLooper());

    private Runnable mScroll = new Runnable() {
        @Override
        public void run() {
            int index = vp_banner.getCurrentItem() + 1;//获得下张广告图索引
            if (index >= mViewList.size()) {
                index = 0;
            }
            vp_banner.setCurrentItem(index);
            mHandler.postDelayed(this, mInterval);
        }
    };

    public void start() {
        mHandler.postDelayed(mScroll, mInterval);
    }

    public void stop() {
        mHandler.removeCallbacks(mScroll);
    }

    // 设置广告轮播的时间间隔
    public void setInterval(int interval) {
        mInterval = interval;
    }

    private class ImageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return object == view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViewList.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }
    }

    @Override
    public void onClick(View view) {
        int position = vp_banner.getCurrentItem();
        mListener.onBannerClick(position);
    }

    private BannerClickListener mListener;

    public void setOnBannerListener(BannerClickListener listener) {
        mListener = listener;
    }

    public interface BannerClickListener {
        void onBannerClick(int position);
    }
}
