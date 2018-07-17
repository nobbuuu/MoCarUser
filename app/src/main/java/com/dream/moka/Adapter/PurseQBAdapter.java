package com.dream.moka.Adapter;

import android.app.Activity;

import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.MyAccountBean;
import com.dream.moka.R;

import java.util.List;

public class PurseQBAdapter extends CommonAdapter<MyAccountBean.ListBean> {

        public PurseQBAdapter(Activity context, List<MyAccountBean.ListBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, MyAccountBean.ListBean dataBean, int position) {
            String transType = dataBean.getTransType();
            String amount = dataBean.getAmount();

            switch (transType) {
                case "0":
                    holder.setText(R.id.cusume_tv, "充值");
                    holder.setText(R.id.money, "+" + amount);
                    break;
                case "2":
                    holder.setText(R.id.cusume_tv, "消费");
                    holder.setText(R.id.money, "-" + amount);
                    break;
            }

            String createDate = dataBean.getCreateDate();
            holder.setText(R.id.time, createDate == null ? "" : createDate);
        }
    }