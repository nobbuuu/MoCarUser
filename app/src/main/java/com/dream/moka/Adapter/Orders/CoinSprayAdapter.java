package com.dream.moka.Adapter.Orders;

import android.app.Activity;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.Message.OrderAddBean;
import com.dream.moka.Bean.TestBaseBean;
import com.dream.moka.R;
import com.dream.moka.Utils.StringUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/11/14 0014.
 */
public class CoinSprayAdapter extends RVBaseAdapter<OrderAddBean.ListSpraySideBean> {
    public CoinSprayAdapter(Activity context, List<OrderAddBean.ListSpraySideBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(RVBaseHolder holder, OrderAddBean.ListSpraySideBean testBaseBean, int position) {
        holder.setText(R.id.coinsprayName_tv,testBaseBean.getName());
        if (StringUtil.NoNullOrEmpty(testBaseBean.getPrice())){
            holder.setText(R.id.coinMoney_tv,"￥"+testBaseBean.getPrice());
        }
    }
}
