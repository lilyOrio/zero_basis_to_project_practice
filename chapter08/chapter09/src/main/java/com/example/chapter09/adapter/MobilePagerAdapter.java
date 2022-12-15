package com.example.chapter09.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.chapter09.bean.GoodsInfo;
import com.example.chapter09.fragment.MobileFragment;

import java.util.ArrayList;
import java.util.List;

public class MobilePagerAdapter extends FragmentStateAdapter {
    private List<GoodsInfo> mGoodsList = new ArrayList<GoodsInfo>(); // 声明一个商品列表

    public MobilePagerAdapter(@NonNull FragmentActivity fragmentActivity,List<GoodsInfo> goodsList) {
        super(fragmentActivity);
        mGoodsList = goodsList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return MobileFragment.newInstance(position,
                mGoodsList.get(position).pic, mGoodsList.get(position).desc);
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }
}
