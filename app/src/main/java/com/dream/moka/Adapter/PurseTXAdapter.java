package com.dream.moka.Adapter;

import android.app.Activity;

import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.WithDrawResultBean;
import com.dream.moka.R;

import java.util.List;

public class PurseTXAdapter extends CommonAdapter<WithDrawResultBean.ListBean> {

        public PurseTXAdapter(Activity context, List<WithDrawResultBean.ListBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, WithDrawResultBean.ListBean dataBean, int position) {
            holder.setText(R.id.cusume_tv, "提现");
            String receiptPayment = dataBean.getTransType();
            String amount = dataBean.getAmount();
            if (receiptPayment != null) {
                if (receiptPayment.equals("1")) {
                    holder.setText(R.id.money, "-" + amount);
                } else {
                    holder.setText(R.id.money, "+" + amount);
                }
            } else {
                holder.setText(R.id.money, amount == null ? "0" : amount);
            }
            String createDate = dataBean.getCreateDate();
            holder.setText(R.id.time, createDate == null ? "" : createDate);
        }
    }