package com.dream.moka.Adapter;

import android.app.Activity;

import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.IntegralBean;
import com.dream.moka.R;
import com.dream.moka.Utils.StringUtil;

import java.util.List;

public class IntegralAdapter extends CommonAdapter<IntegralBean> {

    public IntegralAdapter(Activity context, List<IntegralBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void convert(BaseViewHolder holder, IntegralBean dataBean, int position) {
        if (StringUtil.NoNullOrEmpty(dataBean.getDescription())){
            holder.setText(R.id.cusume_tv, dataBean.getDescription());
        }
        String type = dataBean.getType();
        String amount = dataBean.getScore();
        if (type!=null){
            switch (type) {
                case "0":
                    holder.setText(R.id.money, "+" + amount);
                    break;
                case "1":
                    holder.setText(R.id.money, "-" + amount);
                    break;
            }
        }
        String createDate = dataBean.getCreateDate();
        holder.setText(R.id.time, createDate == null ? "" : createDate);
    }
}