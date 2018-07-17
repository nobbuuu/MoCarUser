package com.dream.moka.Base;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;
import com.dream.moka.Utils.GlideCircleTransform;

public class RVBaseHolder extends RecyclerView.ViewHolder {
    public View itemView;

    public RVBaseHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    //供adapter调用，返回holder
    public static <T extends RVBaseHolder> T getHolder(Context cxt, ViewGroup parent, int layoutId) {

        return (T) new RVBaseHolder(LayoutInflater.from(cxt).inflate(layoutId, parent, false));
    }

    //根据Item中的控件Id获取控件（不建议从views中取，响应速度慢，影响性能）
    public <T extends View> T getView(int viewId) {
        View childreView = itemView.findViewById(viewId);
        return (T) childreView;
    }

    //根据Item中的控件Id向控件添加事件监听
    public RVBaseHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public RVBaseHolder setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public RVBaseHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public RVBaseHolder setBackgroundResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setBackgroundResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public RVBaseHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @return
     */
    public RVBaseHolder setImageByUrl(int viewId, String url) {
       /* ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
                (ImageView) getView(viewId));*/
        Glide.with(MyApplication.getInstance())
                .load(url)
                .placeholder(R.mipmap.img_placeholderfigure01)
                .error(R.mipmap.img_placeholderfigure01)
                .into((ImageView) getView(viewId));
        return this;
    }
    public RVBaseHolder setImageByUrl(int viewId, String url,boolean yuan) {
       /* ImageLoader.getInstance(3, Type.LIFO).loadImage(url,
                (ImageView) getView(viewId));*/
        Glide.with(MyApplication.getInstance())
                .load(url)
                .transform(new GlideCircleTransform(MyApplication.getInstance()))
                .placeholder(R.mipmap.img_car)
                .error(R.mipmap.img_car)
                .into((ImageView) getView(viewId));
        return this;
    }
}