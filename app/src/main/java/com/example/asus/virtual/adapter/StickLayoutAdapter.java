package com.example.asus.virtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.example.asus.virtual.R;
import com.example.asus.virtual.base.BaseVirtualLayoutAdapter;

import java.util.List;

/**
 * Created by ZWX
 * Description:
 * on 2019/8/20.
 */
public class StickLayoutAdapter extends BaseVirtualLayoutAdapter<StickLayoutAdapter.MyViewHolder, String> {

    public StickLayoutAdapter(Context context, LayoutHelper layoutHelper, List<String> data) {
        super(context, layoutHelper, data);
    }

    @Override
    public StickLayoutAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.layout_title, parent, false));
    }

    @Override
    public void onBindViewHolder(StickLayoutAdapter.MyViewHolder holder, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
