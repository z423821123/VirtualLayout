package com.example.asus.virtual.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
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
public class GridLayoutShopAdapter extends BaseVirtualLayoutAdapter<RecyclerView.ViewHolder, String> {

    //Type
    private int TYPE_HEADER = 1001;

    public GridLayoutShopAdapter(Context context, LayoutHelper layoutHelper, List<String> data) {
        super(context, layoutHelper, data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER){
            View headerView = LayoutInflater.from(getContext()).inflate(R.layout.layout_header_shop, parent, false);
            return new HeaderHoder(headerView);
        }
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.layout_item_shop, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        //加了头布局,多一个
        return getData().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        //在第一个位置添加头
        if (position == 0){
            return TYPE_HEADER;
        }
        return super.getItemViewType(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout relativeLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.rl_item);
        }
    }

    class HeaderHoder extends RecyclerView.ViewHolder{
        public HeaderHoder(View itemView) {
            super(itemView);
        }
    }
}
