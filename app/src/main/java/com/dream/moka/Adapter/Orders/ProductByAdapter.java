package com.dream.moka.Adapter.Orders;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;

import java.util.List;

public class ProductByAdapter extends RVBaseAdapter<BaoYangFangAnResultBean.ListBaseBean.ListPartBean> {
        private Context mContext;
        public ProductByAdapter(Activity context, List<BaoYangFangAnResultBean.ListBaseBean.ListPartBean> data, int layoutId) {
            super(context, data, layoutId);
            mContext = context;
        }

        @Override
        public void onBind(RVBaseHolder holder, final BaoYangFangAnResultBean.ListBaseBean.ListPartBean listPartBean, int position) {
            String brands = listPartBean.getBrands();
            String name = listPartBean.getName();
//            String count = listPartBean.getCount();
            String price = listPartBean.getPrice();
            String picture = listPartBean.getPicture();
            ImageView view = holder.getView(R.id.img);
            holder.setText(R.id.brandName, name == null ? "" : name);
            holder.setText(R.id.productname, brands == null ? "" : brands);
//            holder.setText(R.id.number, count == null ? "" : "x" + count);
            holder.setText(R.id.price, price == null ? "" : "¥" + price);
            GlidUtils.LoadImgForUrl(mContext, Const.BannerUrl + picture, view, R.mipmap.img_placeholderfigure01);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ToastUtils.Toast_short("查看详情");
                    if (StringUtil.NoNullOrEmpty(listPartBean.getId())){
                        IntentUtils.toActivityWithUrl(WebViewActivity.class,mActivity,listPartBean.getId(),"9");
                    }
                }
            });
        }
    }