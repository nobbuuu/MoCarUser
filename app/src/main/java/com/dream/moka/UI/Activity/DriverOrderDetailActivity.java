package com.dream.moka.UI.Activity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.DriverCenter.DriverOrderDetailBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.KeysBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenter.DJCarDetailContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.DriverCenter.DJCarPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.StringUtil;

import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DriverOrderDetailActivity extends BaseCommonActivity implements DJCarDetailContract.View {

    @Inject
    DJCarPresenter mDJCarPresenter;

    @Bind(R.id.orderNo_tv)
    TextView mOrderNoTv;
    @Bind(R.id.carNo_tv)
    TextView mCarNoTv;
    @Bind(R.id.car_name)
    TextView mCarName;
    @Bind(R.id.username_tv)
    TextView mUsernameTv;
    @Bind(R.id.phone_tv)
    TextView mPhoneTv;
    @Bind(R.id.address_tv)
    TextView mAddressTv;
    @Bind(R.id.goto_tv)
    TextView mGotoTv;
    @Bind(R.id.title_tv1)
    TextView title_tv;
    private String mTag;

    @Override
    public int getLayoutId() {
        return R.layout.activity_driver_orderdetail;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mDJCarPresenter.attachView(this);
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
        mTag = getIntent().getStringExtra("tag");
        Log.e("tag", "orderId=" + orderId);
        Log.e("tag", "token=" + CommonAction.getToken());
        mDJCarPresenter.getOrderDetail(orderId);
    }

    @Override
    public void eventListener() {

    }

    private boolean isLoad;

    @OnClick({R.id.back_relay, R.id.goto_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.goto_tv:
                if (isLoad) {
                    Map<String, String> singleMap = MapUtils.getSingleMap();
                    singleMap.put(Const.token, CommonAction.getToken());
                    singleMap.put("orderId", getIntent().getStringExtra("orderId"));
                    singleMap.put("longitude", CommonAction.getLongitude());
                    singleMap.put("latitude", CommonAction.getLatitude());
                    mLoadingDialog.show();
                    mDJCarPresenter.toPickUpCar(singleMap);
                }
                break;
        }
    }

    //点击出发后的回调
    @Override
    public void showOrderDetailData(KeysBean dataBean) {
        SpUtils.savaUserInfo(Const.keysId,dataBean.getDriverHisId());
        SpUtils.savaUserInfo(Const.isUpload,false);
        finish();
    }

    //订单详情的回调
    @Override
    public void showOrderDetailData(DriverOrderDetailBean dataBean) {
        isLoad = true;
        DriverOrderDetailBean.OrdersBean ordersBean = dataBean.getOrders();
        DriverOrderDetailBean.UserCarBean carBean = dataBean.getUserCar();
        DriverOrderDetailBean.RepairShopBean repairShop = dataBean.getRepairShop();
        if (ordersBean != null) {
            mOrderNoTv.setText(ordersBean.getOrderCode());
            if (mTag.equals("2")){
                mAddressTv.setText(ordersBean.getSendAddress());
                mUsernameTv.setText(ordersBean.getSendName());
                mPhoneTv.setText(ordersBean.getSendMobile());
            }else {
                mAddressTv.setText(repairShop.getAddress());
                mUsernameTv.setText(repairShop.getName());
                mPhoneTv.setText(repairShop.getContactTel());
            }
            title_tv.setText(CommonAction.getDriverFirstStr() + "师傅，您有新的" + (mTag.equals("2") ? "接" : "送") + "车订单，请确认启程" + (mTag.equals("2") ? "接" : "送") +"车");
        }
        if (carBean != null) {
            mCarNoTv.setText(carBean.getCardNo());
            mCarName.setText(carBean.getCarname());
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

}
