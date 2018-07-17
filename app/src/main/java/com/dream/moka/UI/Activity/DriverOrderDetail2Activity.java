package com.dream.moka.UI.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.DriverCenter.DriverOrderDetailBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenter.OrderDetailContract;
import com.dream.moka.Presenter.DriverCenter.OrderDetailPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.StringUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DriverOrderDetail2Activity extends BaseCommonActivity implements OrderDetailContract.View {
    @Inject
    OrderDetailPresenter mOrderDetailPresenter;
    @Bind(R.id.status)
    TextView mStatus;
    @Bind(R.id.orderTime)
    TextView mOrderTime;
    @Bind(R.id.orderNo)
    TextView mOrderNo;
    @Bind(R.id.carNo)
    TextView mCarNo;
    @Bind(R.id.carType)
    TextView mCarType;
    @Bind(R.id.userQQ)
    TextView mUserQQ;
    @Bind(R.id.managerQQ)
    TextView mManagerQQ;
    @Bind(R.id.paiTime)
    TextView mPaiTime;
    @Bind(R.id.guiji_tv)
    TextView mGuijiTv;
    @Bind(R.id.jcName_phone)
    TextView mJcNamePhone;
    @Bind(R.id.jc_address)
    TextView mJcAddress;
    @Bind(R.id.scName_phone)
    TextView mScNamePhone;
    @Bind(R.id.sc_address)
    TextView mScAddress;
    @Bind(R.id.daijia_tv)
    TextView mDaijiaTv;
    private String mDriverStatus;

    @Override
    public int getLayoutId() {
        return R.layout.activity_driverorder_detail;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mOrderDetailPresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "订单详情";
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
        mLoadingDialog.show();
        String orderId = getIntent().getStringExtra("orderId");
        mDriverStatus = getIntent().getStringExtra("driverStatus");
        Log.e("tag", "orderId=" + orderId);
        if (StringUtil.NoNullOrEmpty(orderId)) {
            mOrderDetailPresenter.getOrderDetail(orderId);
        }
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.daijia_tv, R.id.guiji_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.daijia_tv:
                IntentUtils.toActivity(DaiJiaActivity.class, mActivity);
                break;
            case R.id.guiji_tv:
                if (StringUtil.NoNullOrEmpty(driverHisId)){
                    IntentUtils.toActivityWithTag(GuijiMapActivity.class, mActivity,driverHisId);
                }
                break;
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

    private String driverHisId = "";
    @Override
    public void showOrderDetailData(DriverOrderDetailBean dataBean) {
        DriverOrderDetailBean.OrdersBean ordersBean = dataBean.getOrders();
        DriverOrderDetailBean.UserCarBean carBean = dataBean.getUserCar();
        DriverOrderDetailBean.UserBean user = dataBean.getUser();
        DriverOrderDetailBean.RepairShopBean repairShop = dataBean.getRepairShop();
        driverHisId = dataBean.getDriverHisId();
        if (StringUtil.NoNullOrEmpty(driverHisId)){
            mGuijiTv.setVisibility(View.VISIBLE);
        }
        if (user != null) {
            mUserQQ.setText(user.getQq() + "");
        }
        if (ordersBean != null) {
            String createDate = ordersBean.getCreateDate();
            mOrderTime.setText(createDate);
            mOrderNo.setText(ordersBean.getOrderCode());
            if (mDriverStatus.equals("1")) {//送车单
                mJcAddress.setText(repairShop.getAddress());
                mScAddress.setText(ordersBean.getBackAddress());
                mJcNamePhone.setText(repairShop.getName() + "  " + repairShop.getContactTel());
                mScNamePhone.setText(ordersBean.getBackName() + "  " + ordersBean.getBackMobile());
                mPaiTime.setText(""+ordersBean.getBackTime());
            } else {//接车单
                mJcAddress.setText(ordersBean.getSendAddress());
                mScAddress.setText(repairShop.getAddress());
                mJcNamePhone.setText(ordersBean.getSendName() + "  " + ordersBean.getSendMobile());
                mScNamePhone.setText(repairShop.getName() + "  " + repairShop.getContactTel());
                mPaiTime.setText(""+ordersBean.getSendTime());
            }
//            mPaiTime.setText("");
        }
        if (carBean != null) {
            mCarNo.setText(carBean.getCardNo() + "");
            mCarType.setText(carBean.getCarname() + "");
        }
    }
}
