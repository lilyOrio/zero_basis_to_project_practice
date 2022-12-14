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
import com.example.chapter09.widget.RecyclerExtras;

import java.util.List;
@SuppressLint("DefaultLocale")
public class RecyclerStagAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements RecyclerExtras.OnItemClickListener, RecyclerExtras.OnItemLongClickListener {
    private final static String TAG = "RecyclerStagAdapter";
    private Context mContext; // 声明一个上下文对象
    private List<NewsInfo> mGoodsList;

    public RecyclerStagAdapter(Context context, List<NewsInfo> goodsList) {
        mContext = context;
        mGoodsList = goodsList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 根据布局文件item_staggered.xml生成视图对象
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_staggered, parent, false);
        return new ItemHolder(v);
    }
    // 获取列表项的类型
    public int getItemViewType(int position) {
        return 0;
    }

    // 获取列表项的编号
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vh, int position) {
        ItemHolder holder = (ItemHolder) vh;
        holder.iv_pic.setImageResource(mGoodsList.get(position).pic_id);
        holder.tv_title.setText(mGoodsList.get(position).title);
        // 列表项的点击事件需要自己实现
        holder.ll_item.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
        // 列表项的长按事件需要自己实现
        holder.ll_item.setOnLongClickListener(v -> {
            if (mOnItemLongClickListener != null) {
                mOnItemLongClickListener.onItemLongClick(v, position);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return mGoodsList.size();
    }

    @Override
    public void onItemClick(View view, int position) {
        String desc = String.format("您点击了第%d项，商品名称是%s", position + 1,
                mGoodsList.get(position).title);
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(View view, int position) {
        String desc = String.format("您长按了第%d项，商品名称是%s", position + 1,
                mGoodsList.get(position).title);
        Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
    }

    // 声明列表项的点击监听器对象
    private RecyclerExtras.OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(RecyclerExtras.OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    // 声明列表项的长按监听器对象
    private RecyclerExtras.OnItemLongClickListener mOnItemLongClickListener;

    public void setOnItemLongClickListener(RecyclerExtras.OnItemLongClickListener listener) {
        this.mOnItemLongClickListener = listener;
    }

    // 定义列表项的视图持有者
    public class ItemHolder extends RecyclerView.ViewHolder {
        public LinearLayout ll_item; // 声明列表项的线性布局
        public ImageView iv_pic; // 声明列表项图标的图像视图
        public TextView tv_title; // 声明列表项标题的文本视图

        public ItemHolder(View v) {
            super(v);
            ll_item = v.findViewById(R.id.ll_item);
            iv_pic = v.findViewById(R.id.iv_pic);
            tv_title = v.findViewById(R.id.tv_title);
        }
    }
}
