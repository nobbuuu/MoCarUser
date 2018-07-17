package com.dream.moka.Adapter.Orders;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.EventBusBean.MoneyUpdateBean;
import com.dream.moka.Bean.Maintain.FreeMaintainAllBean;
import com.dream.moka.Bean.Maintain.ProductSelectBean;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.UI.ChildFragment.FreeMaintainRvFragment;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
public class ProductAdapter extends RVBaseAdapter<FreeMaintainAllBean.ServiceResultBean.ListServiceBean> {

    private ProductDetailAdapter detailAdapter;
    private String fromTag;

    public ProductAdapter(Activity context, List<FreeMaintainAllBean.ServiceResultBean.ListServiceBean> data, int layoutId, String fromTag) {
        super(context, data, layoutId);
        this.fromTag = fromTag;
    }

    @Override
    public void onBind(RVBaseHolder holder, FreeMaintainAllBean.ServiceResultBean.ListServiceBean testBaseBean, int position) {
        FreeMaintainAllBean.ServiceResultBean.ListServiceBean.PartTypeBean partType = testBaseBean.getPartType();
        if (partType!=null){
            holder.setText(R.id.product_itemTv, StringUtil.checkNull(partType.getName()));
        }
        List<FreeMaintainAllBean.ServiceResultBean.ListServiceBean.ListPartBean> listPart = testBaseBean.getListPart();
        RecyclerView productdetail_rv = holder.getView(R.id.productdetail_rv);
        RvUtils.setOption_noparam(productdetail_rv,mActivity);
        List<FreeMaintainAllBean.ServiceResultBean.ListServiceBean.ListPartBean> tempList = new ArrayList<>();
        detailAdapter = new ProductDetailAdapter(mActivity, tempList, R.layout.rvitem_productdetail);
        productdetail_rv.setAdapter(detailAdapter);
        if (fromTag.equals("sure")){
            for (int i = 0; i <listPart.size() ; i++) {
                if (listPart.get(i).isSelect()){
                    tempList.add(listPart.get(i));
                }
            }
            listPart.clear();
            listPart.addAll(tempList);
        }else {
            tempList.addAll(listPart);
        }
        detailAdapter.notifyDataSetChanged();

    }

    private class ProductDetailAdapter extends RVBaseAdapter<FreeMaintainAllBean.ServiceResultBean.ListServiceBean.ListPartBean> {

        public ProductDetailAdapter(Activity context, List<FreeMaintainAllBean.ServiceResultBean.ListServiceBean.ListPartBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final FreeMaintainAllBean.ServiceResultBean.ListServiceBean.ListPartBean baseBean, final int position) {
            ImageView product_iv = holder.getView(R.id.product_iv);
            GlidUtils.LoadImgForUrl(mActivity, Const.BannerUrl+baseBean.getPicture(),product_iv);
            final ImageView selectpro_iv = holder.getView(R.id.selectpro_iv);
            RelativeLayout select_relay = holder.getView(R.id.select_relay);
            LinearLayout upNum_lay = holder.getView(R.id.upNum_lay);
            TextView product_numtv = holder.getView(R.id.product_numtv);
            holder.setText(R.id.product_nameTv,baseBean.getName());
            holder.setText(R.id.summary_tv,baseBean.getContent());
            holder.setText(R.id.product_moneyTv,"¥"+baseBean.getPrice());
            if (baseBean.isSelect()){
                selectpro_iv.setImageResource(R.drawable.small_icon_radio_selected);
            }else {
                selectpro_iv.setImageResource(R.drawable.small_icon_radio_default);
            }
            if (fromTag.equals("sure")){
                upNum_lay.setVisibility(View.GONE);
                selectpro_iv.setVisibility(View.GONE);
                product_numtv.setVisibility(View.VISIBLE);
                product_numtv.setText("x"+ baseBean.getCount());
            }

            select_relay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (baseBean.isSelect()) {
                        selectpro_iv.setImageResource(R.drawable.small_icon_radio_default);
                        baseBean.setSelect(false);
                    } else {
                        selectpro_iv.setImageResource(R.drawable.small_icon_radio_selected);
                        baseBean.setSelect(true);
                    }
                    EventBus.getDefault().post(new MoneyUpdateBean());

                }
            });
            final EditText shop_count_shop = holder.getView(R.id.shop_count_shop);
            if (!StringUtil.NoNullOrEmpty(baseBean.getCount())||baseBean.getCount().equals("0")){
                baseBean.setCount("1");
            }
            shop_count_shop.setText(baseBean.getCount());
            holder.setOnClickListener(R.id.shop_jia, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String count = shop_count_shop.getText().toString();
                    int mCount = Integer.parseInt(count);
                    mCount++;
                    shop_count_shop.setText(String.valueOf(mCount));
                    baseBean.setCount(String.valueOf(mCount));
                    EventBus.getDefault().post(new MoneyUpdateBean());
                }
            });

            holder.setOnClickListener(R.id.shop_jian, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String count = shop_count_shop.getText().toString();
                    int mCount = Integer.parseInt(count);
                    if (mCount>1){
                        mCount--;
                        shop_count_shop.setText(String.valueOf(mCount));
                    }
                    baseBean.setCount(String.valueOf(mCount));
                    EventBus.getDefault().post(new MoneyUpdateBean());
                }
            });
            shop_count_shop.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus){
                        String count = shop_count_shop.getText().toString();
                        baseBean.setCount(String.valueOf(count));
                        EventBus.getDefault().post(new MoneyUpdateBean());
                        Log.e(getClass().getName(),"count="+count);
                    }
                }
            });

            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    if (StringUtil.NoNullOrEmpty(baseBean.getId())){
                        IntentUtils.toActivityWithUrl(WebViewActivity.class,mActivity,baseBean.getId(),"9");
                    }
                }
            });
        }
    }
}