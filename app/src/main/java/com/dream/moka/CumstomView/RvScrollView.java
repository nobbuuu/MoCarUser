package com.dream.moka.CumstomView;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.widget.ScrollView;

public class RvScrollView extends ScrollView {

    private int downX;

    private int downY;

    private int mTouchSlop;

    public RvScrollView(Context context) {

        super(context);

        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();

    }





    public RvScrollView(Context context, AttributeSet attrs) {

        super(context, attrs);

        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();

    }





    public RvScrollView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        mTouchSlop= ViewConfiguration.get(context).getScaledTouchSlop();

    }





    @Override

    public boolean onInterceptTouchEvent(MotionEvent e) {

        int action = e.getAction();

        switch (action) {

            case MotionEvent.ACTION_DOWN:

                downX = (int) e.getRawX();

                downY = (int) e.getRawY();

                break;

            case MotionEvent.ACTION_MOVE:

                int moveY = (int) e.getRawY();

                if (Math.abs(moveY - downY) > mTouchSlop) {

                    return true;

                }

        }

        return super.onInterceptTouchEvent(e);

    }

    private OnScrollListener listener;

    public void setOnScrollListener(OnScrollListener listener) {
        this.listener = listener;
    }
    //设置接口
    public interface OnScrollListener{
        void onScroll(int scrollX, int scrollY, int oldScrollX, int oldScrollY);
    }

    //重写原生onScrollChanged方法，将参数传递给接口，由接口传递出去
    @Override
    protected void onScrollChanged(int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        super.onScrollChanged(scrollX, scrollY, oldScrollX, oldScrollY);
        if(listener != null){

            //这里我只传了垂直滑动的距离
            listener.onScroll(scrollX,scrollY,oldScrollX,oldScrollY);
        }
    }
}