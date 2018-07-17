package com.dream.moka.Utils;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.dream.moka.Other.MyApplication;
import com.dream.moka.R;


/**
 * Created by Administrator on 2017/9/15 0015.
 */
public abstract class SexPopwindowUtils {

    public SexPopwindowUtils(View sex_relay,Activity mActivity){
        getSexPopwindow(mActivity).showAtLocation(sex_relay, Gravity.BOTTOM, 0, 0);;
    }
    /**
     * 显示popupWindow
     */
    public  PopupWindow getSexPopwindow(final Activity mActivity) {
        //加载弹出框的布局
        View contentView = LayoutInflater.from(MyApplication.getInstance()).inflate(R.layout.pop_sexedit, null);
        //设置弹出框的宽度和高度
        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setFocusable(true);// 取得焦点
        //注意  要是点击外部空白处弹框消息  那么必须给弹框设置一个背景色  不然是不起作用的
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        PopWindowUtil.backgroundAlpaha(mActivity,0.5f);

        //点击外部消失
        popupWindow.setOutsideTouchable(true);
        //设置可以点击
        popupWindow.setTouchable(true);

        //进入退出的动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        RelativeLayout nan_relay = (RelativeLayout) contentView.findViewById(R.id.sex_nanrelay);
        RelativeLayout nv_relay = (RelativeLayout) contentView.findViewById(R.id.sex_nvrelay);
        nan_relay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectNanListener();
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

        nv_relay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectNvListener();
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });
        RelativeLayout quxiao = (RelativeLayout) contentView.findViewById(R.id.quxiao);
        quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                }
            }
        });

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                PopWindowUtil.backgroundAlpaha(mActivity,1f);
            }
        });

        return popupWindow;
    }

    public abstract  void selectNanListener();

    public abstract  void selectNvListener();
}
