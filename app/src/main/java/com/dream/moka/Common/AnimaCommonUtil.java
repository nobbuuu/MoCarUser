package com.dream.moka.Common;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public class AnimaCommonUtil {


    public static Animation getShunRotate(){
        Animation animation = AnimationUtils.loadAnimation(MyApplication.getInstance(), R.anim.arrow_anim);
        animation.setFillAfter(!animation.getFillAfter());
        return animation;
    }
    public static Animation getNiRotate(){
        Animation animation = AnimationUtils.loadAnimation(MyApplication.getInstance(), R.anim.arrow_anim_ni);
        animation.setFillAfter(!animation.getFillAfter());
        return animation;
    }
}
