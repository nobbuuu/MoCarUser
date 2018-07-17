package com.dream.moka.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dream.moka.Other.MyApplication;

/**
 * Created by Administrator on 2017/11/9 0009.
 */
public class ImmUtils {
    public static InputMethodManager getImm(){
        InputMethodManager imm = (InputMethodManager) MyApplication.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm;
    }

    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();


            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    public static InputMethodManager getImm(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm;
    }
    public static void hideImm(Activity activity){
        getImm(activity).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
    public static void hideImm(Activity activity,InputMethodManager imm){
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
    public static void showImm(Activity activity,View v){
        getImm(activity).showSoftInput(v,InputMethodManager.SHOW_FORCED);
    }
    public static void showImm(Activity activity,View v,InputMethodManager imm){
        imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);
    }
}
