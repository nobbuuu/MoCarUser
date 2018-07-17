package com.dream.moka.Adapter.Maintain;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Adapter.Orders.ProductAdapter;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Bean.Maintain.FreeMaintainAllBean;
import com.dream.moka.Bean.Maintain.ProductSelectBean;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.R;
import com.dream.moka.UI.ChildFragment.FreeMaintainRvFragment;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class FreeMaintaiAdapter extends RVBaseAdapter<FreeMaintainAllBean.ServiceResultBean>{
    private String mFrom;
    public FreeMaintaiAdapter(Activity context, List<FreeMaintainAllBean.ServiceResultBean> data, int layoutId,String from) {
        super(context, data, layoutId);
        mFrom = from;
    }

    @Override
    public void onBind(RVBaseHolder holder, final FreeMaintainAllBean.ServiceResultBean baseBean, final int position) {

        final FreeMaintainAllBean.ServiceResultBean.ServiceItemBean serviceItem = baseBean.getServiceItem();
        TextView moneyTv = holder.getView(R.id.money);
        String price = serviceItem.getPrice();
        if (StringUtil.NoNullOrEmpty(price)){
            if (Double.valueOf(price)>0){
                moneyTv.setVisibility(View.VISIBLE);
            }else {
                moneyTv.setVisibility(View.GONE);
            }
        }
        moneyTv.setText("¥"+ price);
        holder.setText(R.id.program_name, serviceItem.getName());


        final LinearLayout pro_lay = holder.getView(R.id.pro_lay);
        if (serviceItem.isExpand()){
            pro_lay.setVisibility(View.GONE);
        }else {
            pro_lay.setVisibility(View.VISIBLE);
        }
        final RecyclerView product_rv = holder.getView(R.id.product_rv);
        RvUtils.setOptionnoLine(product_rv, mActivity);
        product_rv.setAdapter(new ProductAdapter(mActivity, baseBean.getListService(), R.layout.rvitem_productfree,mFrom));
        holder.setOnClickListener(R.id.service_topRelay, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceItem.isExpand()){
                    pro_lay.setVisibility(View.VISIBLE);
                    serviceItem.setExpand(false);
                }else {
                    pro_lay.setVisibility(View.GONE);
                    serviceItem.setExpand(true);
                }
            }
        });
    }
}