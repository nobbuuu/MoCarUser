package com.dream.moka.CumstomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.R;


/**
 * Created by Administrator on 2018/1/6 0006.
 */
public class EmptyView extends LinearLayout {

    private TextView empty_tv;
    public EmptyView(Context context, String tipStr) {
        super(context);
        init(context);
        empty_tv.setText(tipStr);
    }


    public EmptyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        View inflate = inflate(context, R.layout.view_empty, this);
        empty_tv = (TextView) inflate.findViewById(R.id.empty_tiptv);
    }

}
