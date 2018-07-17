package com.dream.moka.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.R;
import com.dream.moka.Utils.StringUtil;

import java.util.List;

public class CounponsAdapter extends RVBaseAdapter<ListCouponBean> {

    public CounponsAdapter(Activity context, List<ListCouponBean> data, int layoutId) {
        super(context, data, layoutId);
    }

    @Override
    public void onBind(RVBaseHolder holder, final ListCouponBean cardListResultBean, int position) {
        holder.setText(R.id.couponMoney_tv, cardListResultBean.getAmount());
        String couponType = cardListResultBean.getCouponType();// 优惠券类型(0：单程代驾券 1：保养券 2：钣喷券 3：维修券 4：现金券)
        String couponsName = "";
        TextView couponsNameTv = holder.getView(R.id.couponName_tv);
        TextView couponsSummary_tv = holder.getView(R.id.couponsSummary_tv);
        if (couponType != null) {
            switch (couponType) {
                case "0":
                    couponsName = "单程代驾券";
                    break;
                case "1":
                    couponsName = "保养券";
                    break;
                case "2":
                    couponsName = "钣喷券";
                    break;
                case "3":
                    couponsName = "维修券";
                    break;
                case "4":
                    couponsName = "现金券";
                    break;
            }
        }
        if (!couponsName.isEmpty()){
            couponsNameTv.setVisibility(View.VISIBLE);
            couponsNameTv.setText(couponsName);
        }else {
            couponsNameTv.setVisibility(View.GONE);
        }

        String leastAmtUse = cardListResultBean.getLeastAmtUse();
        if (StringUtil.NoNullOrEmpty(leastAmtUse)){
            couponsSummary_tv.setVisibility(View.VISIBLE);
            couponsSummary_tv.setText("满"+leastAmtUse+"元可用");
        }else {
            couponsSummary_tv.setVisibility(View.GONE);
        }
    }
}