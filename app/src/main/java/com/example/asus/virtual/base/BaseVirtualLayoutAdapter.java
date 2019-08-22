package com.example.asus.virtual.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWX
 * Description:
 * on 2019/8/20.
 */
public abstract class BaseVirtualLayoutAdapter<VH extends RecyclerView.ViewHolder, T> extends DelegateAdapter.Adapter<VH>{

    private Context context;
    private LayoutHelper layoutHelper;
    private List<T> data;

    public BaseVirtualLayoutAdapter(Context context, LayoutHelper layoutHelper, List<T> data) {
        this.context = context;
        this.layoutHelper = layoutHelper;
        this.data = data;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @Override
    public abstract VH onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(VH holder, int position);

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    public Context getContext(){
        return context;
    }


    public LayoutHelper getLayoutHelper() {
        return layoutHelper;
    }

    public List<T> getData(){
        if( data == null){
            return data = new ArrayList<>();
        }else {
            return data;
        }
    }
    public void addData(List<T> newData){
        if(newData != null && !newData.isEmpty()){
            if(data == null){
                data = new ArrayList<>();
            }
            data.addAll(newData);
            notifyDataSetChanged();
        }
    }

    public void setData(List<T> newData){
        if(newData != null && !newData.isEmpty()){
            if(data == null){
                data = new ArrayList<>();
            }
            data.clear();
            data.addAll(newData);
            notifyDataSetChanged();
        }
    }

    public void clearData(){
        if(data != null) {
            data.clear();
        }
    }
}
