package com.dream.moka.Utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;

import java.io.File;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
public class GlidUtils {
    public static DrawableRequestBuilder<String> getCircleBitmap(String url){
        DrawableRequestBuilder<String> transform = Glide.with(MyApplication.getInstance()).load(Const.API_BASE_URL+url).transform(new GlideCircleTransform(MyApplication.getInstance()));
        return transform;
    }

    public static void LoadImgForUrl(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.img_car)
                .error(R.mipmap.img_car)
                .into(imageView);
    }
    public static void LoadHeadIconForUrl(Context context, String url, ImageView imageView){
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.img_defaultavatar)
                .error(R.mipmap.img_defaultavatar)
                .into(imageView);
    }

    public static void LoadImgForUrl(Context context, String url, ImageView imageView,int EmptyImg){
        Glide.with(context)
                .load(url)
                .placeholder(EmptyImg)
                .error(EmptyImg)
                .into(imageView);
    }

    /**
     * 加载圆形图
     * @param context
     * @param url
     * @param imageView
     * @param EmptyImg
     */
    public static void LoadCircleImg(Context context, String url, ImageView imageView,int EmptyImg){
        Glide.with(context)
                .load(url)
                .placeholder(EmptyImg)
                .error(EmptyImg)
                .bitmapTransform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 通过路径加载本地保存的图片(元型)
     * @param context
     * @param path
     * @param imageView
     * @param holdImg
     */
    public static void LoadFromFileLocation(Context context, String path, ImageView imageView,int holdImg){
        Glide.with(context)
                .load(new File(path))
                .placeholder(holdImg)
                .error(holdImg)
                .bitmapTransform(new GlideCircleTransform(context))
                .into(imageView);

    }
}
