package com.example.chapter09.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter09.R;
import com.example.chapter09.bean.NewsInfo;
import com.example.chapter09.widget.RecyclerExtras.OnItemLongClickListener;
import com.example.chapter09.widget.RecyclerExtras.OnItemClickListener;

import java.util.List;
@SuppressLint("RecyclerView")
public class RecyclerGridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
        OnItemLongClickListener, OnItemClickListener {
    private Context mContext;
    private List<NewsInfo> mGoodsList;

    //1
    public RecyclerGridAdapter(Context context, List<NewsInfo> goodsList) {
        mContext = context;
        mGoodsList = goodsList;
    }

    //4
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid, parent, false);
        return new ItemHolder(view);
    }

    //5
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.iv_pic.setImageResource(mGoodsList.get(position).pic_id);
        itemHolder.tv_title.setText(mGoodsList.get(position).title);
        itemHolder.ll_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickLister != null){
                    mOnItemClickLister.onItemClick(view,position);
                }
            }
        });

        itemHolder.ll_item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (mOnItemLongClickLister != null){
                    mOnItemLongClickLister.onItemLongClick(view,position);
                }
                return true;
            }
        });

    }

    //2
    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }

    //3
    public class ItemHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;
        public ImageView iv_pic;
        public LinearLayout ll_item;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            iv_pic = itemView.findViewById(R.id.iv_pic);
            ll_item = itemView.findViewById(R.id.ll_item);
        }
    }

    // 声明列表项的长按监听器对象
    private OnItemLongClickListener mOnItemLongClickLister;
    private OnItemClickListener mOnItemClickLister;

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.mOnItemLongClickLister = listener;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickLister = listener;
    }
    @SuppressLint("DefaultLocale")
    @Override
    public void onItemClick(View view, int position) {
        String desc = String.format("您点击了第%d项，栏目名称是%s", position + 1,
                mGoodsList.get(position).title);
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("DefaultLocale")
    @Override
    public void onItemLongClick(View view, int position) {
        String desc = String.format("您长按了第%d项，栏目名称是%s", position + 1,
                mGoodsList.get(position).title);
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }
}
