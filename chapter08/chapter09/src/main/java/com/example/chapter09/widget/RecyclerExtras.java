package com.example.chapter09.widget;

import android.view.View;

public class RecyclerExtras {
    //定义一个item项的点击监听接口
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }

    //定义一个item项的长按监听器接口
    public interface OnItemLongClickListener{
        void onItemLongClick(View view,int position);
    }

    //定义一个item项的删除监听器接口
    public interface OnItemDeleteClickListener{
        void onItemDeleteClick(View view,int position);
    }
}
