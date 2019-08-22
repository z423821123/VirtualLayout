package com.example.asus.virtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.example.asus.virtual.R;
import com.example.asus.virtual.base.BaseVirtualLayoutAdapter;

import java.util.List;

import static com.example.asus.virtual.MainActivity.dip2px;
import static com.example.asus.virtual.MainActivity.px2dip;

/**
 * Created by ZWX
 * Description:
 * on 2019/8/22.
 */
public class StaggeredGridLayoutAdapter extends BaseVirtualLayoutAdapter<StaggeredGridLayoutAdapter.MyViewHolder, Integer> {
    public StaggeredGridLayoutAdapter(Context context, LayoutHelper layoutHelper, List<Integer> data) {
        super(context, layoutHelper, data);
    }

    @Override
    public StaggeredGridLayoutAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StaggeredGridLayoutAdapter.MyViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.layout_item_staggered, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageView.setImageDrawable(getContext().getDrawable(getData().get(position)));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_item);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
