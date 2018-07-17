package com.dream.moka.Utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dream.moka.CumstomView.MyDecoration;


/**
 * Created by Administrator on 2017/5/18 0018.
 */
public class RvUtils {

    public static void setRvParam(RecyclerView rv){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin = 35;
        params.rightMargin = 35;
        rv.setLayoutParams(params);
    }
    public static void setRvParam_RelayParent(RecyclerView rv){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
        params.leftMargin = 25;
        params.rightMargin = 25;
        rv.setLayoutParams(params);
    }
    public static void setOption(RecyclerView rv,Activity mActivity){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        //添加分割线
        rv.addItemDecoration(new MyDecoration(mActivity, MyDecoration.VERTICAL_LIST));
        RvUtils.setRvParam(rv);
    }
    public static void setOption_noparam(RecyclerView rv,Activity mActivity){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        //添加分割线
        rv.addItemDecoration(new MyDecoration(mActivity, MyDecoration.VERTICAL_LIST));
    }
    public static void setOption_noparamForScroll(RecyclerView rv,Context mActivity){
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
        //添加分割线
        rv.addItemDecoration(new MyDecoration(mActivity, MyDecoration.VERTICAL_LIST));
    }
    public static void setOptionnoLine(RecyclerView rv,Activity activity){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
    }
    public static void setOptionnoLine_h(RecyclerView rv,Activity activity){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rv.setLayoutManager(linearLayoutManager);
    }

    public static void setOptionnoLineforScroll(RecyclerView rv,Activity activity){
        FullyLinearLayoutManager linearLayoutManager = new FullyLinearLayoutManager(activity);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        rv.setLayoutManager(linearLayoutManager);
    }

    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }
    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager   设置RecyclerView对应的manager
     * @param mRecyclerView  当前的RecyclerView
     * @param n  要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {


        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }

    }
}
