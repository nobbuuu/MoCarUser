package com.dream.moka.Adapter;

import android.app.Activity;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.TestBaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14 0014.
 */
public class BaseRvAdapter extends RVBaseAdapter<TestBaseBean> {
    public BaseRvAdapter(Activity context, List<TestBaseBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(RVBaseHolder holder, TestBaseBean testBaseBean, int position) {

    }
}
