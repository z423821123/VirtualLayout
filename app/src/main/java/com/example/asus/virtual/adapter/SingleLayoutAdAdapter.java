package com.example.asus.virtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.LayoutHelper;
import com.example.asus.virtual.R;
import com.example.asus.virtual.base.BaseVirtualLayoutAdapter;
import com.youth.banner.Banner;

import java.util.List;

/**
 * Created by ZWX
 * Description: 广告界面(第4个布局)
 * on 2019/8/21.
 */
public class SingleLayoutAdAdapter extends BaseVirtualLayoutAdapter<SingleLayoutAdAdapter.MyViewHolder, String> {
    public SingleLayoutAdAdapter(Context context, LayoutHelper layoutHelper, List<String> data) {
        super(context, layoutHelper, data);
    }

    @Override
    public SingleLayoutAdAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SingleLayoutAdAdapter.MyViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.layout_item_ad, parent, false));
    }

    @Override
    public void onBindViewHolder(SingleLayoutAdAdapter.MyViewHolder holder, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
