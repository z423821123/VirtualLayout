package com.example.asus.virtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.alibaba.android.vlayout.LayoutHelper;
import com.example.asus.virtual.R;
import com.example.asus.virtual.base.BaseVirtualLayoutAdapter;

import java.util.List;

/**
 * Created by ZWX
 * Description:
 * on 2019/8/21.
 */
public class GridLayoutTimeLimitAdapter extends BaseVirtualLayoutAdapter<RecyclerView.ViewHolder, String> {

    //Type
    private int TYPE_FOOT = 1002;

    @Override
    public int getItemCount() {
        //加了头布局,多一个
        return getData().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        //在第一个位置添加头
        if (position == getData().size()){
            return TYPE_FOOT;
        }
        return super.getItemViewType(position);
    }

    public GridLayoutTimeLimitAdapter(Context context, LayoutHelper layoutHelper, List<String> data) {
        super(context, layoutHelper, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOT){
            View headerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_foot_time_limit, parent, false);
            return new FootHoder(headerView);
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_time_limit, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }

    class FootHoder extends RecyclerView.ViewHolder{
        public FootHoder(View itemView) {
            super(itemView);
        }
    }
}
