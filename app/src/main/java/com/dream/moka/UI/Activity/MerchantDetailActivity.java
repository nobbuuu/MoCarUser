package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.Merchant.RepairShopInfoBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.MerchantDetailContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.MerchantDetailPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.GlideImageLoader;
import com.dream.moka.Utils.RvUtils;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class MerchantDetailActivity extends BaseCommonActivity implements MerchantDetailContract.View {

    @Bind(R.id.merchantdetail_banner)
    Banner mMerchantdetailBanner;
    @Bind(R.id.repairshop_nametv)
    TextView mRepairshopNametv;
    @Bind(R.id.start_lay)
    LinearLayout mStartLay;
    @Bind(R.id.address_tv)
    TextView mAddressTv;
    @Bind(R.id.pro_rv)
    RecyclerView mProRv;
    @Bind(R.id.summary_tv)
    TextView mSummaryTv;
    private List<RepairShopInfoBean.ServiceItemListBean> mServiceItemListBeans = new ArrayList<>();
    private MyServiceAdapter myServiceAdapter;
    @Inject
    MerchantDetailPresenter mMerchantDetailPresenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_merchantdetail;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mMerchantDetailPresenter.attachView(this);
        myServiceAdapter = new MyServiceAdapter(mActivity, mServiceItemListBeans, R.layout.rvitem_onlytext);
        RvUtils.setOption_noparamForScroll(mProRv, mActivity);
        mProRv.setAdapter(myServiceAdapter);
    }

    @Override
    public String initTitleText() {
        return "维修商详情";
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

    @Override
    public void initDatas() {
        String id = getIntent().getStringExtra(Const.intentTag);
        if (id != null) {
            mMerchantDetailPresenter.getRepairShopDetail(id);
        }
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

    @Override
    public void showMerchantInfoData(RepairShopInfoBean dataBean) {
        RepairShopInfoBean.RepairShopBean repairShop = dataBean.getRepairShop();
        if (repairShop != null) {

            String picture = repairShop.getStores();
            if (picture != null) {
                List<String> imgUrls = new ArrayList<>();
                if (picture.contains(",")) {
                    String[] pictures = picture.split(",");
                    for (int i = 0; i < pictures.length; i++) {
                        imgUrls.add(Const.BannerUrl + pictures[i]);
                        Log.e("picUrl", "picUrl=" + Const.BannerUrl + pictures[i]);
                    }
                } else if (!picture.isEmpty()) {
                    imgUrls.add(Const.BannerUrl + picture);
                    Log.e("picUrl", "picUrl=" + Const.BannerUrl + picture);
                }
                mMerchantdetailBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
                mMerchantdetailBanner.setImages(imgUrls);
                mMerchantdetailBanner.setDelayTime(Const.time);
                mMerchantdetailBanner.setImageLoader(new GlideImageLoader());
                mMerchantdetailBanner.start();
            }

            mAddressTv.setText(repairShop.getAddress());
            mRepairshopNametv.setText(repairShop.getName());
            mSummaryTv.setText(repairShop.getContent());
            String score = repairShop.getScore();
            if (score != null && !score.isEmpty()) {
                double mScore = Double.parseDouble(score);
                mStartLay.removeAllViews();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 5;
                for (int i = 0; i < mScore; i++) {
                    ImageView iv = new ImageView(mActivity);
                    iv.setLayoutParams(params);
                    iv.setImageResource(R.mipmap.icon_star_selected);
                    mStartLay.addView(iv);
                }
                for (int i = 0; i < 5 - mScore; i++) {
                    ImageView iv = new ImageView(mActivity);
                    iv.setLayoutParams(params);
                    iv.setImageResource(R.mipmap.icon_star_default);
                    mStartLay.addView(iv);
                }
            }
        }
        List<RepairShopInfoBean.ServiceItemListBean> serviceItemList = dataBean.getServiceItemList();
        if (serviceItemList != null && serviceItemList.size() > 0) {
            mServiceItemListBeans.clear();
            mServiceItemListBeans.addAll(serviceItemList);
            myServiceAdapter.notifyDataSetChanged();

        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {

    }

    public class MyServiceAdapter extends RVBaseAdapter<RepairShopInfoBean.ServiceItemListBean> {

        public MyServiceAdapter(Activity context, List<RepairShopInfoBean.ServiceItemListBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, RepairShopInfoBean.ServiceItemListBean serviceItemListBean, int position) {
            TextView only_tv = holder.getView(R.id.only_tv);
            String name = serviceItemListBean.getName();
            only_tv.setText(name);
        }
    }
}
