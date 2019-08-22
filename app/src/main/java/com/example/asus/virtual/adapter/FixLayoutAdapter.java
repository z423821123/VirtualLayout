package com.example.asus.virtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.example.asus.virtual.R;
import com.example.asus.virtual.base.BaseVirtualLayoutAdapter;
import com.example.asus.virtual.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWX
 * Description: 搜索栏界面
 * on 2019/8/20.
 */
public class FixLayoutAdapter extends BaseVirtualLayoutAdapter<FixLayoutAdapter.MyViewHolder, String> {

    public FixLayoutAdapter(Context context, FixLayoutHelper fixLayoutHelper, List<String> data) {
       super(context, fixLayoutHelper, data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.layout_title, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
         MyViewHolder(View itemView) {
            super(itemView);
        }
    }

}
