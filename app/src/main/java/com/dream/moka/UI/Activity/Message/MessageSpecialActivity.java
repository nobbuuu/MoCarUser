package com.dream.moka.UI.Activity.Message;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.BaseViewHolder;
import com.dream.moka.Base.CommonAdapter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Bean.Message.CheckResultBean;
import com.dream.moka.Bean.Message.IncreaceBundle;
import com.dream.moka.Bean.Message.OderAddDetailBean;
import com.dream.moka.Bean.Message.OrderAddBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.Message.CancleAddOrderContract;
import com.dream.moka.Contract.Message.CkechOrderContract;
import com.dream.moka.Contract.Message.OrderAddDetailContract;
import com.dream.moka.CumstomView.MyGridView;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.Message.CancleIncreaceOrderPresenter;
import com.dream.moka.Presenter.Message.IncreaceOrderDetailPresenter;
import com.dream.moka.Presenter.Message.IncreaceSureCheckPresenter;
import com.dream.moka.Presenter.Message.OrderAddDetailPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.MainActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.DensityUtil;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 * 增项详情
 */
public class MessageSpecialActivity extends BaseCommonActivity implements OrderAddDetailContract.View, CkechOrderContract.View,CancleAddOrderContract.View{


    @Inject
    OrderAddDetailPresenter mOrderAddDetailPresenter;
    @Inject
    IncreaceOrderDetailPresenter mIncreaceOrderDetailPresenter;
    @Inject
    IncreaceSureCheckPresenter mIncreaceSureCheckPresenter;
    @Inject
    CancleIncreaceOrderPresenter mCancleIncreaceOrderPresenter;

    @Bind(R.id.car_name)
    TextView mCarName;
    @Bind(R.id.orderNo_tv)
    TextView mOrderNoTv;
    @Bind(R.id.repairshop_nametv)
    TextView mRepairshopNametv;
    @Bind(R.id.date_Tv)
    TextView mDateTv;
    @Bind(R.id.describe_tv)
    TextView mDescribeTv;
    @Bind(R.id.oneMoney_tv)
    TextView mOneMoneyTv;
    @Bind(R.id.accessories_tv)
    TextView mAccessoriesTv;
    @Bind(R.id.twoMoney_tv)
    TextView mTwoMoneyTv;
    @Bind(R.id.xupay_tv)
    TextView mXupayTv;
    @Bind(R.id.threeMoney_tv)
    TextView mThreeMoneyTv;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.despic_gv)
    MyGridView mDespicGv;
    @Bind(R.id.addzz_iv)
    ImageView mAddzzIv;
    @Bind(R.id.partzz_iv)
    ImageView mPartzzIv;
    @Bind(R.id.bottom_relay)
    RelativeLayout bottom_relay;
    @Bind(R.id.repairMount_relay)
    RelativeLayout mRepairMountRelay;
    @Bind(R.id.addMount_relay)
    RelativeLayout mAddMountRelay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_messageorder_special;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mOrderAddDetailPresenter.attachView(this);
        mIncreaceSureCheckPresenter.attachView(this);
        mIncreaceOrderDetailPresenter.attachView(this);
        mCancleIncreaceOrderPresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "消息详情";
    }

    @Override
    public String initRightTv() {
        return "取消增项";
    }

    @Override
    public boolean isRighttv() {
        return false;
    }

    @Override
    public void loadResum() {

    }

    public static void openAct(Context context, String orderId, String from) {
        Intent intent = new Intent(context, MessageSpecialActivity.class);
        intent.putExtra(Const.intentTag, orderId);
        intent.putExtra("from", from);
        context.startActivity(intent);
    }

    @Override
    public void initDatas() {
        loadData();
    }

    private void loadData() {
        String id = getIntent().getStringExtra(Const.intentTag);
        Log.e("tag", "id=" + id);
        String from = getIntent().getStringExtra("from");
        mLoadingDialog.show();
        if (from.equals("orderList")) {
            mIncreaceOrderDetailPresenter.getMyOrderIncrease(CommonAction.getToken(), id);
        } else {
            mOrderAddDetailPresenter.getMyOrderIncrease(CommonAction.getToken(), id);
        }
    }

    @Override
    public void eventListener() {

    }

    private String orderStatus;

    @OnClick({R.id.back_relay, R.id.sure_tv, R.id.addzz_iv, R.id.partzz_iv,R.id.right_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finishActivity();
                break;
            case R.id.sure_tv:
                if (orderStatus != null) {
                    switch (orderStatus) {
                        case "0":
//                            SureOrderActivity.openAct();
                            if (orderIncreaseId != null) {
                                mLoadingDialog.show();
                                mIncreaceSureCheckPresenter.checkIncreaseOrder(CommonAction.getToken(), orderIncreaseId);
                            }
                            break;
                    }
                }
                break;
            case R.id.addzz_iv:
                IntentUtils.toImgDetail(mActivity, mAddzzIv, Const.BannerUrl + addPic);
                break;
            case R.id.partzz_iv:
                IntentUtils.toImgDetail(mActivity, mAddzzIv, Const.BannerUrl + partPic);
                break;
            case R.id.right_tv:
                if (StringUtil.NoNullOrEmpty(orderId)){
                    mCancleIncreaceOrderPresenter.cancleIncreaseOrder(CommonAction.getToken(),orderId);
                }
                break;
        }
    }

    private String addPic, partPic, orderIncreaseId, add_time, orderMoney, carNo, carName, repairShopName,orderId;

    @Override
    public void showData(OderAddDetailBean dataBean) {
        orderStatus = dataBean.getOrderStatus();
        if (orderStatus != null) {
            right_tv.setVisibility(View.GONE);
            switch (orderStatus) {
                case "0":
                    mSureTv.setText("确认");
                    bottom_relay.setVisibility(View.VISIBLE);
                    right_tv.setVisibility(View.VISIBLE);
                    right_tv.setText("取消增项");
                    break;
                case "1":
                    mSureTv.setText("查看详情");
                    bottom_relay.setVisibility(View.GONE);
                    break;
                case "2":
                    mSureTv.setText("已失效");
                    bottom_relay.setVisibility(View.VISIBLE);
                    mSureTv.setBackgroundColor(ResourcesUtils.getColor(R.color.color98));
                    break;
            }
        }

        OderAddDetailBean.UserCarBean userCar = dataBean.getUserCar();
        if (userCar != null) {
            mCarName.setText(userCar.getCarname());
            carNo = userCar.getCardNo();
            carName = userCar.getCarname();
        }
        mOrderNoTv.setText(StringUtil.checkNull(dataBean.getOrderCode()));
        mRepairshopNametv.setText(StringUtil.checkNull(dataBean.getShopName()));
        repairShopName = dataBean.getShopName();
        OderAddDetailBean.OrderIncreaseBean orderIncrease = dataBean.getOrderIncrease();
        if (orderIncrease != null) {
            orderIncreaseId = orderIncrease.getId();
            Log.e("tag", "orderIncreaseId=" + orderIncreaseId);
            String descPic = orderIncrease.getDescPic();
            if (StringUtil.NoNullOrEmpty(descPic)) {
                List<String> picList = new ArrayList<>();
                if (descPic.contains(",")) {
                    String[] picArrary = descPic.split(",");
                    for (int i = 0; i < picArrary.length; i++) {
                        if (!picArrary[i].contains(",")) {
                            picList.add(picArrary[i]);
                        }
                    }
                } else {
                    picList.add(descPic);
                }
                mDespicGv.setAdapter(new DescribpicAdapter(mActivity, picList, R.layout.gvitem_onlyimg));
                mDateTv.setText(DateFormatUtil.getTime(orderIncrease.getCreateDate(), Const.YMD_HMS, Const.YMD_HM));
            }

            addPic = orderIncrease.getIncreasePic();
            partPic = orderIncrease.getPartPic();
            if (StringUtil.NoNullOrEmpty(addPic)) {
                GlidUtils.LoadImgForUrl(mContext, Const.BannerUrl + StringUtil.checkNull(addPic), mAddzzIv);
            } else {
                mAddzzIv.setVisibility(View.GONE);
            }
            if (StringUtil.NoNullOrEmpty(partPic)) {
                GlidUtils.LoadImgForUrl(mContext, Const.BannerUrl + StringUtil.checkNull(partPic), mPartzzIv);
            } else {
                mPartzzIv.setVisibility(View.GONE);
            }
            if (StringUtil.NoNullOrEmpty(orderIncrease.getRepairAmount())) {
                mOneMoneyTv.setText("￥" + orderIncrease.getRepairAmount());
            } else {
                mRepairMountRelay.setVisibility(View.GONE);
            }
            if (StringUtil.NoNullOrEmpty(orderIncrease.getAddAmount())) {
                mTwoMoneyTv.setText("￥" + orderIncrease.getAddAmount());
            }else {
                mAddMountRelay.setVisibility(View.GONE);
            }
            mDescribeTv.setText(StringUtil.checkNull(orderIncrease.getDescription()));

            add_time = orderIncrease.getAddTime();

        }

        OrderAddBean.OrdersBean orders = dataBean.getOrders();
        if (orders != null) {

            mThreeMoneyTv.setText(StringUtil.checkNull(orders.getOrderAmount()));
            orderMoney = orders.getOrderAmount();
            orderId = orders.getId();
        }

    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    //点击确认后的回调
    @Override
    public void showCheckData(CheckResultBean dataBean) {
        List<ListCouponBean> listCoupon = dataBean.getListCoupon();
        IncreaceBundle mIncreaceBean = new IncreaceBundle();
        mIncreaceBean.setAdd_time(add_time);
        mIncreaceBean.setCarName(carName);
        mIncreaceBean.setCarNo(carNo);
        mIncreaceBean.setListCoupon(listCoupon);
        mIncreaceBean.setOrderMoney(orderMoney);
        mIncreaceBean.setRepairShopName(repairShopName);
        mIncreaceBean.setOrderId(dataBean.getOrderId());
        Intent intent = new Intent(mActivity, IncreaceSureOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("dataBean", mIncreaceBean);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showCancleOrderData(EmptyBean dataBean) {
        ToastUtils.Toast_short("取消成功");
        loadData();
    }

    public class DescribpicAdapter extends CommonAdapter<String> {
        private int itemWith = DensityUtil.getScreenWidth();
        private int mWidth = (itemWith - DensityUtil.dip2px(MyApplication.getInstance(), 54)) / 4;

        public DescribpicAdapter(Activity context, List<String> mDatas, int itemLayoutId) {
            super(context, mDatas, itemLayoutId);
        }

        @Override
        public void convert(BaseViewHolder holder, final String s, int position) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth, mWidth);
            final ImageView only_iv = holder.getView(R.id.only_iv);
            only_iv.setLayoutParams(params);
            holder.setImageByUrl(R.id.only_iv, Const.BannerUrl + s, false);

            holder.getConvertView().setOnClickListener(new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View view) {
                    IntentUtils.toImgDetail(mActivity, only_iv, Const.BannerUrl + s);
                }
            });
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finishActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void finishActivity() {
        if (!AppManager.getAppManager().isInStack(MainActivity.class)){
            Intent intent = new Intent(mContext, MainActivity.class);
            Intent intent1 = new Intent(mContext, MessageRvActivity.class);
            startActivities(new Intent[]{intent,intent1});
        }
        finish();
    }
}
