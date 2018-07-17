package com.dream.moka.Common;

import android.app.Activity;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.TestBaseBean;

import java.util.List;

public class RvCommonAdapter extends RVBaseAdapter<TestBaseBean> {

    public RvCommonAdapter(Activity context, List<TestBaseBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(RVBaseHolder holder, TestBaseBean testBaseBean, int position) {

    }
}