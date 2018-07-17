package com.dream.moka.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dream.moka.R;
import com.youth.banner.loader.ImageLoader;


/**
 * 使用第三方bannerview而重写的加载器
 * Created by Administrator on 2017/5/2.
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        //Glide 加载图片简单用法
        Glide
                .with(context)
                .load(path)
                .placeholder(R.drawable.banner_zhanweitu_9)
                .error(R.drawable.banner_zhanweitu_9)
                .into(imageView);
    }
}

