package com.example.asus.virtual.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.example.asus.virtual.R;
import com.example.asus.virtual.base.BaseVirtualLayoutAdapter;
import com.example.asus.virtual.utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZWX
 * Description: 轮播界面
 * on 2019/8/20.
 */
public class SingleLayoutAdapter extends BaseVirtualLayoutAdapter<SingleLayoutAdapter.MyViewHolder, String> {

    private SingleLayoutAdapter.MyViewHolder myViewHolder;

    public SingleLayoutAdapter(Context context, SingleLayoutHelper singleLayoutHelper, List<String> data) {
        super(context, singleLayoutHelper, data);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myViewHolder = new SingleLayoutAdapter.MyViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.layout_banner, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //资源文件
        List<Integer> images= new ArrayList<>();
        images.add(R.mipmap.ic_launcher);
        images.add(R.mipmap.ic_launcher_round);
        images.add(R.mipmap.ic_launcher);
        holder.banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        holder.banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        holder.banner.start();
        holder.banner.startAutoPlay();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        Banner banner;
        public MyViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public void onDestotyBanner(){
        myViewHolder.banner.stopAutoPlay();
    }
}
