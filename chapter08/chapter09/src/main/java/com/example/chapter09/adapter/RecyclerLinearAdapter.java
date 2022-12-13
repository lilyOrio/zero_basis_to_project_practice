package com.example.chapter09.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter09.R;
import com.example.chapter09.bean.NewsInfo;

import java.util.List;

public class RecyclerLinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static String TAG = "RecyclerLinearAdapter";
    private Context mContext; // 声明一个上下文对象
    private List<NewsInfo> mPublicList; // 公众号列表


    //step1 在构造方法中传入消息列表
    public RecyclerLinearAdapter(Context context, List<NewsInfo> publicList) {
        mPublicList = publicList;
        mContext = context;
    }

    //step4 重写onCreateViewHolder方法，根据指定布局文件生成视图对象，并返回该视图对象对应的视图持有者（ViewHolder对象
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_linear, null);
        return new ItemHolder(view);
    }

    //step5 重写onBindViewHolder方法从参数中的视图持有者中获取各个控件示例，以便操作这些控件
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        ItemHolder holder = (ItemHolder) vh;
        holder.iv_pic.setImageResource(mPublicList.get(position).pic_id);
        holder.tv_title.setText(mPublicList.get(position).title);
        holder.tv_desc.setText(mPublicList.get(position).desc);
    }

    //step2 重写getItemCount()方法 返回列表项个数
    @Override
    public int getItemCount() {
        return mPublicList.size();
    }

    //step3 定义一个由RecyclerView.ViewHolder派生而来的内部类，用作列表的视图持有者
    public class ItemHolder extends RecyclerView.ViewHolder {
        public ImageView iv_pic; // 声明列表项图标的图像视图
        public TextView tv_title; // 声明列表项标题的文本视图
        public TextView tv_desc; // 声明列表项描述的文本视图

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }
    }
}
