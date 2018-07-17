package com.dream.moka.Adapter.Orders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.R;
import com.dream.moka.Utils.RvUtils;

import java.util.List;

public class ProgramAdapter extends RVBaseAdapter<BaoYangFangAnResultBean.ListBaseBean> {
        private Activity mContext;
        public ProgramAdapter(Activity context, List<BaoYangFangAnResultBean.ListBaseBean> data, int layoutId) {
            super(context, data, layoutId);
            mContext = context;
        }

        @Override
        public void onBind(RVBaseHolder holder, final BaoYangFangAnResultBean.ListBaseBean listBaseBean, final int position) {
            BaoYangFangAnResultBean.ListBaseBean.ServiceItemBean serviceItem = listBaseBean.getServiceItem();
            List<BaoYangFangAnResultBean.ListBaseBean.ListPartBean> listPart = listBaseBean.getListPart();
//            final ImageView select_iv = holder.getView(R.id.select_iv);
            holder.setText(R.id.program_name, serviceItem.getName());

//            if (baseBean.isSelect()) {
//                select_iv.setImageResource(R.mipmap.icon_radio_selected);
//            } else {
//                select_iv.setImageResource(R.mipmap.icon_radio_default);
//            }

            holder.setText(R.id.money, "¥" + serviceItem.getPrice());
            RecyclerView product_rv = holder.getView(R.id.product_rv);
            RvUtils.setOption_noparamForScroll(product_rv, mContext);
            product_rv.setAdapter(new ProductByAdapter(mContext, listPart, R.layout.rvitem_product));
//            holder.setOnClickListener(R.id.select_relay, new NoDoubleClickListener() {
//                @Override
//                public void onNoDoubleClick(View view) {
//                    if (!baseBean.isSelect()) {
//                        for (int i = 0; i < gramList.size(); i++) {
//                            if (position == i) {
//                                select_iv.setImageResource(R.mipmap.icon_radio_selected);
//                                baseBean.setSelect(true);
//                            } else {
//                                select_iv.setImageResource(R.mipmap.icon_radio_default);
//                                gramList.get(i).setSelect(false);
//                            }
//                        }
//                    }
//                    programAdapter.notifyDataSetChanged();
//                }
//            });
        }
    }