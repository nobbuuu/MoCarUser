package com.dream.moka.Base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class RVBaseAdapter<T> extends RecyclerView.Adapter<RVBaseHolder> {
    public Activity mActivity;
    List<T> data;
    int []layoutIds;//布局资源Id数组layoutIds中

    public RVBaseAdapter(Activity context, List<T> data, int layoutId) {

        this.mActivity = context;
        this.data = data;
        //不使用多布局时的构造函数，将layoutId放到布局资源Id数组layoutIds中
        this.layoutIds = new int[]{layoutId};

    }
    //使用多布局时的构造函数，可以接收传入的布局资源Id数组
    public RVBaseAdapter(Activity context, List<T> data, int[] layoutIds) {
        this.mActivity = context;
        this.data = data;
        this.layoutIds = layoutIds;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return this.data;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public RVBaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      //根据viewType从布局资源数组中加载指定的布局
      //1.当不需要加载多布局时，子类继承BaseAdapter，不用覆写getItemViewType()
      //此时，viewType值默认为0；
      //2.当需要加载多布局时，子类继承MultiLayoutsBaseAdapter，需要覆写getItemViewType()
      //此时，viewType值为子类adapter指定的值；
        return RVBaseHolder.getHolder(mActivity, parent, layoutIds[viewType]);

    }

    @Override
    public void onBindViewHolder(RVBaseHolder holder, int position) {

        onBind(holder, data.get(position), position);
    }

    public abstract void onBind(RVBaseHolder holder, T t, int position);

}