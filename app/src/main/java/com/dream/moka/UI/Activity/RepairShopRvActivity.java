package com.dream.moka.UI.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.RepairAreaBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.Const;
import com.dream.moka.R;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class RepairShopRvActivity extends BaseCommonActivity {

    private List<ConfirmOrderResultBean.ListShopBean> dataList = new ArrayList<>();
    private List<RepairAreaBean> repairData = new ArrayList<>();
    private RepairShopAdapter commonAdapter;
    private RSAdapter mRSAdapter;
    @Bind(R.id.common_rv)
    RecyclerView common_rv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commonrv;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        RvUtils.setOption_noparam(common_rv, this);
        commonAdapter = new RepairShopAdapter(this, dataList, R.layout.rvitem_repairshop);
        mRSAdapter = new RSAdapter(this, repairData, R.layout.rvitem_repairshop);
    }

    @Override
    public String initTitleText() {
        return "选择维修商店";
    }

    @Override
    public String initRightTv() {
        return null;
    }

    @Override
    public boolean isRighttv() {
        return false;
    }

    @Override
    public void loadResum() {

    }


    public static void openAct(Context context, String latitude, String longitude, String carId) {
        Intent intent = new Intent(context, RepairShopRvActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        intent.putExtra("carId", carId);
        context.startActivity(intent);
    }

    @Override
    public void initDatas() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            List<ConfirmOrderResultBean.ListShopBean> data = (List<ConfirmOrderResultBean.ListShopBean>) extras.getSerializable("data");
            if (data != null) {
                dataList.clear();
                dataList.addAll(data);
                common_rv.setAdapter(commonAdapter);
                commonAdapter.notifyDataSetChanged();
            }
            List<RepairAreaBean> dataRS = (List<RepairAreaBean>) extras.getSerializable("dataRS");
            if (dataRS != null) {
                repairData.clear();
                repairData.addAll(dataRS);
                common_rv.setAdapter(mRSAdapter);
                mRSAdapter.notifyDataSetChanged();
            }
        }

       /* String latitude = getIntent().getStringExtra("latitude");
        String longitude = getIntent().getStringExtra("longitude");
        String carId = getIntent().getStringExtra("carId");

        Map<String,String> map = new HashMap<>();
        map.put(Const.token, CommonAction.getToken());
        if (latitude!=null){
            map.put("latitude",latitude);
        }
        if (longitude!=null){
            map.put("longitude",longitude);
        }
        if (carId!=null){
            map.put("carId",carId);
        }*/
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
        }
    }

    @SuppressLint("NewApi")
    public class RepairShopAdapter extends RVBaseAdapter<ConfirmOrderResultBean.ListShopBean> {

        public RepairShopAdapter(Activity context, List<ConfirmOrderResultBean.ListShopBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final ConfirmOrderResultBean.ListShopBean baseBean, int position) {

            holder.setText(R.id.shopName_tv, baseBean.getName());
            holder.setImageByUrl(R.id.shopPic_iv, Const.BannerUrl + baseBean.getPicture());
            String score = baseBean.getScore();
            if (score != null) {
                double parseDouble = Double.parseDouble(score);
                holder.setText(R.id.score_tv, parseDouble + "星");
                LinearLayout start_lay = holder.getView(R.id.start_lay);
                for (int i = 0; i < parseDouble; i++) {
                    ImageView iv = new ImageView(mContext);
                    iv.setImageResource(R.mipmap.icon_star_selected);
                    start_lay.addView(iv);
                }
                for (int i = 0; i < 5 - parseDouble; i++) {
                    ImageView iv = new ImageView(mContext);
                    iv.setImageResource(R.mipmap.icon_star_default);
                    start_lay.addView(iv);
                }
            }
            String dist = baseBean.getDist();
            Double aDouble = Double.valueOf(dist);
            holder.setText(R.id.mileaddress_tv, new DecimalFormat("#0.00").format(aDouble / 1000) + "km" + StringUtil.checkNull(baseBean.getAddress()));
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    Intent intent = getIntent();
                    intent.putExtra("repairShopId", baseBean.getId());
                    intent.putExtra("shopName", baseBean.getName());
                    intent.putExtra("shopType", baseBean.getShopType());
                    setResult(103, intent);
                    finish();
                }
            });
        }
    }

    @SuppressLint("NewApi")
    public class RSAdapter extends RVBaseAdapter<RepairAreaBean> {

        public RSAdapter(Activity context, List<RepairAreaBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final RepairAreaBean baseBean, int position) {

            holder.setText(R.id.shopName_tv, baseBean.getName());
            holder.setImageByUrl(R.id.shopPic_iv, Const.BannerUrl + baseBean.getPicture());
            String score = baseBean.getScore();
            if (score != null) {
                double parseDouble = Double.parseDouble(score);
                holder.setText(R.id.score_tv, parseDouble + "星");
                LinearLayout start_lay = holder.getView(R.id.start_lay);
                for (int i = 0; i < parseDouble; i++) {
                    ImageView iv = new ImageView(mContext);
                    iv.setImageResource(R.mipmap.icon_star_selected);
                    start_lay.addView(iv);
                }
                for (int i = 0; i < 5 - parseDouble; i++) {
                    ImageView iv = new ImageView(mContext);
                    iv.setImageResource(R.mipmap.icon_star_default);
                    start_lay.addView(iv);
                }
            }
            String dist = baseBean.getDist();
            Double aDouble = Double.valueOf(dist);
            holder.setText(R.id.mileaddress_tv, new DecimalFormat("#0.00").format(aDouble / 1000) + "km" + StringUtil.checkNull(baseBean.getAddress()));
            holder.itemView.setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    Intent intent = getIntent();
                    intent.putExtra("repairShopId", baseBean.getId());
                    intent.putExtra("shopName", baseBean.getName());
                    intent.putExtra("shopType", baseBean.getShopType());
                    setResult(103, intent);
                    finish();
                }
            });
        }
    }
}
