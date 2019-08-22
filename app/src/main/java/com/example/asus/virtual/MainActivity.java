package com.example.asus.virtual;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.BaseLayoutHelper;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.example.asus.virtual.adapter.FixLayoutAdapter;
import com.example.asus.virtual.adapter.GridLayoutAdapter;
import com.example.asus.virtual.adapter.GridLayoutHotAdapter;
import com.example.asus.virtual.adapter.GridLayoutShopAdapter;
import com.example.asus.virtual.adapter.GridLayoutTimeLimitAdapter;
import com.example.asus.virtual.adapter.GridLayoutTopAdapter;
import com.example.asus.virtual.adapter.LinearLayoutAdapter;
import com.example.asus.virtual.adapter.SingleLayoutAdAdapter;
import com.example.asus.virtual.adapter.SingleLayoutAdapter;
import com.example.asus.virtual.adapter.StaggeredGridLayoutAdapter;
import com.example.asus.virtual.adapter.StickLayoutAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FixLayoutAdapter fixLayoutAdapter;
    SingleLayoutAdapter singleLayoutHelper;
    GridLayoutAdapter gridLayoutAdapter;
    SingleLayoutAdAdapter singleLayoutAdAdapter;
    GridLayoutShopAdapter gridLayoutShopAdapter;
    GridLayoutTimeLimitAdapter gridLayoutTimeLimitAdapter;
    GridLayoutTopAdapter gridLayoutTopAdapter;
    GridLayoutHotAdapter gridLayoutHotAdapter;
    LinearLayoutAdapter linearLayoutAdapter;
    StaggeredGridLayoutAdapter staggeredGridLayoutAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        initRecyclerview();
        initData();
    }

    private void initRecyclerview() {
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
        // 创建VirtualLayoutManager对象
        // 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找

        recyclerView.setLayoutManager(layoutManager);
        // 设置组件复用回收池
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        recyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);

        // 步骤2：创建自定义的Adapter对象 & 绑定数据 & 绑定上述对应的LayoutHelper
        recyclerView.setAdapter(initAdapter(layoutManager));

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(5, 5, 5, 5);
            }
        });
    }

    private DelegateAdapter initAdapter(VirtualLayoutManager layoutManager) {
        //创建DelegateAdapter对象 & 将layoutManager绑定到DelegateAdapter
        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager);
        // 将VirtualLayoutManager绑定到recyclerView
        List<DelegateAdapter.Adapter> adapters = new LinkedList<>();

        StickLayoutAdapter stickLayoutAdapter = new StickLayoutAdapter(this, initStickLayout(), null);
        fixLayoutAdapter = new FixLayoutAdapter(this, initFixLayout(), null);
        singleLayoutHelper = new SingleLayoutAdapter(this, initSingleLayout(), null);
        gridLayoutAdapter = new GridLayoutAdapter(this, initGridLayout(), null);
        singleLayoutAdAdapter = new SingleLayoutAdAdapter(this, initSingleLayout(), null);
        gridLayoutShopAdapter = new GridLayoutShopAdapter(this, initGridLayoutAd(), null);
        gridLayoutTimeLimitAdapter = new GridLayoutTimeLimitAdapter(this, initGridLayoutTimeLimit(), null);
        gridLayoutTopAdapter = new GridLayoutTopAdapter(this, initGridLayoutTop(), null);
        gridLayoutHotAdapter = new GridLayoutHotAdapter(this, initGridLayoutHot(), null);
        linearLayoutAdapter = new LinearLayoutAdapter(this, initLinerLayout(), null);
        staggeredGridLayoutAdapter = new StaggeredGridLayoutAdapter(this, initStaggeredGridLayout(), null);
        adapters.add(fixLayoutAdapter);
        adapters.add(singleLayoutHelper);
        adapters.add(gridLayoutAdapter);
        adapters.add(singleLayoutAdAdapter);
        adapters.add(gridLayoutShopAdapter);
        adapters.add(gridLayoutTimeLimitAdapter);
        adapters.add(gridLayoutTopAdapter);
        adapters.add(gridLayoutHotAdapter);
        adapters.add(linearLayoutAdapter);
        adapters.add(staggeredGridLayoutAdapter);
        delegateAdapter.setAdapters(adapters);
        return delegateAdapter;
    }

    private LinearLayoutHelper initLinerLayout() {
        /**
         设置线性布局
         */
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        // 创建对应的LayoutHelper对象

        // 所有布局的公共属性（属性会在下面详细说明）
        linearLayoutHelper.setItemCount(4);// 设置布局里Item个数
  //      linearLayoutHelper.setPadding(10,10,10,10);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        linearLayoutHelper.setMargin(10,10,10,10);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
 //       linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        linearLayoutHelper.setLayoutViewBindListener(new BaseLayoutHelper.LayoutViewBindListener() {
            @Override
            public void onBind(View layoutView, BaseLayoutHelper baseLayoutHelper) {
                layoutView.setBackground(getResources().getDrawable(R.drawable.corners_bg, null));
            }
        });
 //       linearLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // linearLayoutHelper特有属性
        linearLayoutHelper.setDividerHeight(1); // 设置每行Item的距离
        return linearLayoutHelper;
    }

    private GridLayoutHelper initGridLayout(){
        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(3);// 设置布局里Item个数
 //       gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
  //      gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
 //       gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // gridLayoutHelper特有属性（下面会详细说明）
  //      gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
  //      gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        gridLayoutHelper.setLayoutViewBindListener(new BaseLayoutHelper.LayoutViewBindListener() {
            @Override
            public void onBind(View layoutView, BaseLayoutHelper baseLayoutHelper) {
                layoutView.setBackground(getResources().getDrawable(R.drawable.corners_bg, null));
            }
        });
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        gridLayoutHelper.setSpanSizeLookup( new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position > 2 ) {
                    return 1;
                }else {
                    return 3;
                }
            }
        });
        return gridLayoutHelper;
    }

    private FixLayoutHelper initFixLayout(){

        FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT,0,0);
        // 参数说明:
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y


        // 公共属性
        fixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
//        @Override
//        public void setItemCount(int itemCount) {
//            if (itemCount > 0) {
//                super.setItemCount(1);
//            } else {
//                super.setItemCount(0);
//            }
//        }
        fixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
 //       fixLayoutHelper.setMargin(20, 20, 50, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离

  //      fixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        fixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        fixLayoutHelper.setBgColor(Color.RED);
   //     fixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
    //    fixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
        return fixLayoutHelper;
    }

    private void initScrollFixLayout(){
        /**
         设置可选固定布局
         */

        ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT,0,0);
        // 参数说明:
        // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
        // 参数2:基准位置的偏移量x
        // 参数3:基准位置的偏移量y

        // 公共属性
        scrollFixLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
//        @Override
//        public void setItemCount(int itemCount) {
//            if (itemCount > 0) {
//                super.setItemCount(1);
//            } else {
//                super.setItemCount(0);
//            }
//        }
        scrollFixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        scrollFixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        scrollFixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // fixLayoutHelper特有属性
        scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
        scrollFixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
        scrollFixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
        scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);// 设置Item的显示模式
    }

    private void initFloatLayout(){
        /**
         设置浮动布局
         */
        FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
        // 创建FloatLayoutHelper对象

        // 公共属性
        floatLayoutHelper.setItemCount(1);// 设置布局里Item个数
        // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
//        @Override
//        public void setItemCount(int itemCount) {
//            if (itemCount > 0) {
//                super.setItemCount(1);
//            } else {
//                super.setItemCount(0);
//            }
//        }
        floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        floatLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // floatLayoutHelper特有属性
        floatLayoutHelper.setDefaultLocation(300,300);// 设置布局里Item的初始位置
    }

    private void initColumLayout(){
        /**
         设置栏格布局
         */
        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        // 创建对象

        // 公共属性
        columnLayoutHelper.setItemCount(3);// 设置布局里Item个数
        columnLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        columnLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        columnLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        columnLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // columnLayoutHelper特有属性
        columnLayoutHelper.setWeights(new float[]{30, 40, 30});// 设置该行每个Item占该行总宽度的比例
        // 同上面Weigths属性讲解
    }

    private SingleLayoutHelper initSingleLayout(){
        /**
         设置通栏布局
         */
        SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();

        // 公共属性
        singleLayoutHelper.setItemCount(1);// 设置布局里Item个数
        singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        singleLayoutHelper.setMargin(20, dip2px(this, 70), 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
 //       singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
  //      singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
        return  singleLayoutHelper;
    }

    private void initOnePlusLyaout(){
        /**
         设置1拖N布局
         */
        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(5);
        // 在构造函数里传入显示的Item数
        // 最多是1拖4,即5个

        // 公共属性
        onePlusNLayoutHelper.setItemCount(3);// 设置布局里Item个数
        onePlusNLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        onePlusNLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        onePlusNLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        onePlusNLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
    }

    private StickyLayoutHelper initStickLayout(){
        /**
         设置吸边布局
         */
        StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();

        // 公共属性
        stickyLayoutHelper.setItemCount(3);// 设置布局里Item个数
        stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
 //       stickyLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        stickyLayoutHelper.setStickyStart(true);
        // true = 组件吸在顶部
        // false = 组件吸在底部

        stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量
        return stickyLayoutHelper;
    }

    private StaggeredGridLayoutHelper initStaggeredGridLayout(){
        /**
         设置瀑布流布局
         */

        StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper();
        // 创建对象

        // 公有属性
        staggeredGridLayoutHelper.setItemCount(20);// 设置布局里Item个数
        staggeredGridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        staggeredGridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        staggeredGridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
  //      staggeredGridLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        // 特有属性
        staggeredGridLayoutHelper.setLane(3);// 设置控制瀑布流每行的Item数
        staggeredGridLayoutHelper.setHGap(20);// 设置子元素之间的水平间距
        staggeredGridLayoutHelper.setVGap(15);// 设置子元素之间的垂直间距
        return staggeredGridLayoutHelper;
    }

    private void initData(){
        List<String> fixData = new ArrayList<>();
        fixData.add("111");
        fixLayoutAdapter.setData(fixData);

        List<String> bannerData = new ArrayList<>();
        bannerData.add("2222");
        singleLayoutHelper.addData(bannerData);

        List<String> gridData = new ArrayList<>();
        gridData.add("33333333");
        gridData.add("4444444444");
        gridData.add("555555555");
        gridLayoutAdapter.addData(gridData);

        singleLayoutAdAdapter.addData(fixData);

        gridLayoutShopAdapter.addData(gridData);

        gridLayoutTimeLimitAdapter.addData(gridData);
        gridLayoutTimeLimitAdapter.addData(gridData);

        gridLayoutTopAdapter.addData(gridData);

        gridLayoutHotAdapter.addData(gridData);
        gridLayoutHotAdapter.addData(gridData);

        linearLayoutAdapter.addData(gridData);

        List<Integer> staggeredList = new ArrayList<>();
        for(int i = 0 ; i < 20 ; i++){
            if(i % 2 == 0) {
                staggeredList.add(R.drawable.test);
            }else {
                staggeredList.add(R.drawable.ic_launcher_foreground);
            }
        }
        staggeredGridLayoutAdapter.addData(staggeredList);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    private GridLayoutHelper initGridLayoutAd(){
        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(3);// 设置布局里Item个数
        //       gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //      gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //       gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // gridLayoutHelper特有属性（下面会详细说明）
        //      gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        //      gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        gridLayoutHelper.setLayoutViewBindListener(new BaseLayoutHelper.LayoutViewBindListener() {
            @Override
            public void onBind(View layoutView, BaseLayoutHelper baseLayoutHelper) {
                layoutView.setBackground(getResources().getDrawable(R.drawable.corners_bg, null));
            }
        });
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        gridLayoutHelper.setSpanSizeLookup( new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position > 7 ) {
                    return 1;
                }else {
                    return 3;
                }
            }
        });
        return gridLayoutHelper;
    }

    private GridLayoutHelper initGridLayoutTimeLimit(){
        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(7);// 设置布局里Item个数
        //       gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //      gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //       gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // gridLayoutHelper特有属性（下面会详细说明）
        //      gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        //      gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        gridLayoutHelper.setLayoutViewBindListener(new BaseLayoutHelper.LayoutViewBindListener() {
            @Override
            public void onBind(View layoutView, BaseLayoutHelper baseLayoutHelper) {
                layoutView.setBackground(getResources().getDrawable(R.drawable.corners_bg, null));
            }
        });
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        gridLayoutHelper.setSpanSizeLookup( new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position > 16 ) {
                    return 2;
                }else {
                    return 1;
                }
            }
        });
        return gridLayoutHelper;
    }

//    private GridLayoutHelper initGridLayoutAd(){
//        /**
//         设置Grid布局
//         */
//        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
//        // 在构造函数设置每行的网格个数
//
//        // 公共属性
//        gridLayoutHelper.setItemCount(3);// 设置布局里Item个数
//        //       gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
//        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
//        //      gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
//        //       gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
//
//        // gridLayoutHelper特有属性（下面会详细说明）
//        //      gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
//        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
//        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
//        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
//        //      gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
//        gridLayoutHelper.setLayoutViewBindListener(new BaseLayoutHelper.LayoutViewBindListener() {
//            @Override
//            public void onBind(View layoutView, BaseLayoutHelper baseLayoutHelper) {
//                layoutView.setBackground(getResources().getDrawable(R.drawable.corners_bg, null));
//            }
//        });
//        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
//        gridLayoutHelper.setSpanSizeLookup( new GridLayoutHelper.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                if (position > 7 ) {
//                    return 1;
//                }else {
//                    return 3;
//                }
//            }
//        });
//        return gridLayoutHelper;
//    }

    private GridLayoutHelper initGridLayoutTop(){
        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(7);// 设置布局里Item个数
        //       gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //      gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //       gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // gridLayoutHelper特有属性（下面会详细说明）
        //      gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        //      gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        gridLayoutHelper.setSpanSizeLookup( new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position > 18 ) {
                    return 1;
                }else {
                    return 3;
                }
            }
        });
        return gridLayoutHelper;
    }

    private GridLayoutHelper initGridLayoutHot(){
        /**
         设置Grid布局
         */
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2);
        // 在构造函数设置每行的网格个数

        // 公共属性
        gridLayoutHelper.setItemCount(7);// 设置布局里Item个数
        //       gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
        gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
        //      gridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
        //       gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

        // gridLayoutHelper特有属性（下面会详细说明）
        //      gridLayoutHelper.setWeights(new float[]{40, 30, 30});//设置每行中 每个网格宽度 占 每行总宽度 的比例
        gridLayoutHelper.setVGap(20);// 控制子元素之间的垂直间距
        gridLayoutHelper.setHGap(20);// 控制子元素之间的水平间距
        gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
        //      gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
        gridLayoutHelper.setLayoutViewBindListener(new BaseLayoutHelper.LayoutViewBindListener() {
            @Override
            public void onBind(View layoutView, BaseLayoutHelper baseLayoutHelper) {
                layoutView.setBackground(getResources().getDrawable(R.drawable.corners_bg, null));
            }
        });
        // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
        gridLayoutHelper.setSpanSizeLookup( new GridLayoutHelper.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position > 22 ) {
                    return 1;
                }else {
                    return 2;
                }
            }
        });
        return gridLayoutHelper;
    }
}
