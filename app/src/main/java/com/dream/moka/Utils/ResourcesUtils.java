package com.dream.moka.Utils;

import android.graphics.drawable.Drawable;

import com.dream.moka.Other.MyApplication;


/**
 * Created by Administrator on 2017/8/7 0007.
 */
public class ResourcesUtils {

    public static int getColor(int resId){
        return MyApplication.getInstance().getResources().getColor(resId);
    }
    public static Drawable getDrable(int resId){
        return MyApplication.getInstance().getResources().getDrawable(resId);
    }
    public static String getString(int resId){
        return MyApplication.getInstance().getResources().getString(resId);
    }
    public static int getDp(int resId){
        return MyApplication.getInstance().getResources().getInteger(resId);
    }
}
