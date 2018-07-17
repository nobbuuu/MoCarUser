package com.dream.moka.UI.Activity.Message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Adapter.Orders.CoinSprayAdapter;
import com.dream.moka.Adapter.Orders.ProgramAdapter;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.Message.OrderAddBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.Message.MessageOrderAddContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.Message.MsgOrdeerAddPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.MainActivity;
import com.dream.moka.UI.Activity.RepairShopRvActivity;
import com.dream.moka.UI.Activity.SureOrderActivity;
import com.dream.moka.UI.Activity.set.UserXieYiActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class OrderAddMaintainActivity extends BaseCommonActivity implements MessageOrderAddContract.View {

    @Bind(R.id.program_rv)
    RecyclerView mProgramRv;
    @Bind(R.id.summaryPrice_tv)
    TextView mSummaryPriceTv;
    @Bind(R.id.summary_tv)
    TextView summary_tv;
    @Bind(R.id.price_tv)
    TextView mPriceTv;
    @Bind(R.id.repairshop_tv)
    TextView mRepairshopTv;
    @Bind(R.id.right_iv1)
    ImageView right_iv1;
    @Bind(R.id.repairshop_relay)
    RelativeLayout mRepairshopRelay;
    @Bind(R.id.second_relay)
    RelativeLayout second_relay;
    @Bind(R.id.xupay_tv)
    TextView mXupayTv;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.proName_tv)
    TextView proName_tv;
    @Bind(R.id.ordermoney_tv)
    TextView ordermoney_tv;

    @Inject
    MsgOrdeerAddPresenter mMsgOrdeerAddPresenter;
    @Bind(R.id.carBrand_nameTv)
    TextView mCarBrandNameTv;
    @Bind(R.id.miles_tv)
    TextView mMilesTv;
    @Bind(R.id.bottom_relay)
    LinearLayout bottom_relay;
    @Bind(R.id.car_relay)
    LinearLayout mCarRelay;
    @Bind(R.id.gz_lay)
    LinearLayout mGzTv;
    @Bind(R.id.sumName_tv)
    TextView mSumNameTv;
    @Bind(R.id.itprice_tv)
    TextView mItpriceTv;
    @Bind(R.id.itprice_relay)
    RelativeLayout mItpriceRelay;
    @Bind(R.id.otherPrice_tv)
    TextView mOtherPriceTv;
    @Bind(R.id.otherPrice_relay)
    RelativeLayout mOtherPriceRelay;

    private String orderStatus;
    private List<OrderAddBean.ListSpraySideBean> spraySideList = new ArrayList<>();
    private List<BaoYangFangAnResultBean.ListBaseBean> serviceList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_orderadd;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mMsgOrdeerAddPresenter.attachView(this);
        RvUtils.setOptionnoLine(mProgramRv, this);
    }

    @Override
    public String initTitleText() {
        return "维修推单";
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
        Map<String, String> map = MapUtils.getSingleMap();
        map.put(Const.token, CommonAction.getToken());
        map.put("id", id);
        map.put("longitude", CommonAction.getLongitude());
        map.put("latitude", CommonAction.getLatitude());
        mMsgOrdeerAddPresenter.getMyPushOrderDetail(map);
    }

    @Override
    public void eventListener() {

    }


    @OnClick({R.id.repairshop_relay, R.id.sure_tv, R.id.back_relay, R.id.sumName_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finishActivity();
                break;
            case R.id.repairshop_relay:
                if (orderType.equals("1") || orderType.equals("2")) {
                    Intent intent = new Intent(mContext, RepairShopRvActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", (Serializable) repairList);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 103);
                }
                break;
            case R.id.sure_tv:
                if (checkEmpty()) {
                    Map<String, String> mapwx = MapUtils.getSingleMap();
                    mapwx.put(Const.token, CommonAction.getToken());
                    mapwx.put("carId", mCarId);
                    mapwx.put("repairShopId", repairShopId);
                    mapwx.put("orderId", orderId);
                    if (orderType.equals("0") || orderType.equals("4")) {//维修
                        mapwx.put("type", "3");
                    } else {
                        switch (orderType) {
                            case "1"://保养
                                mapwx.put("type", "1");
                                break;
                            case "2"://钣喷
                                mapwx.put("type", "2");
                                break;
                            case "3"://保险
                                mapwx.put("type", "4");
                                break;
                        }
                    }
                    mLoadingDialog.show();
                    mMsgOrdeerAddPresenter.checkPushOrder(mapwx);
                }
                break;
            case R.id.sumName_tv:
                String sumStr = mSumNameTv.getText().toString();
                if (sumStr.contains("准备资料")) {
                    UserXieYiActivity.openAct(mContext, Const.insurance);
                }
                break;
        }
    }

    public boolean checkEmpty() {
        if (!StringUtil.NoNullOrEmpty(orderId)) {
            return false;
        }
        if (!StringUtil.NoNullOrEmpty(totalMoney)) {
            return false;
        }
        if (!StringUtil.NoNullOrEmpty(mCarId)) {
            return false;
        }
        if (!StringUtil.NoNullOrEmpty(repairShopId)) {
            return false;
        }
        if (!StringUtil.NoNullOrEmpty(mRepairshopTv.getText().toString())) {
            return false;
        }
        if (!StringUtil.NoNullOrEmpty(orderType)) {
            return false;
        }

        return true;
    }


    private int reparStatus;
    private List<ConfirmOrderResultBean.ListShopBean> repairList = new ArrayList<>();
    private String repairShopId = "", totalMoney = "", mCarId = "", orderType = "", orderId = "", carStatus = "", trailerMoney = "";

    @Override
    public void showOrderAddData(OrderAddBean dataBean) {

        OrderAddBean.RepairOrderBean repairOrder = dataBean.getRepairOrder();
        if (repairOrder != null) {
            int status = repairOrder.getStatus();
            reparStatus = status;
            mSummaryPriceTv.setText("一口价");
            summary_tv.setText(StringUtil.checkNull(repairOrder.getDescription()));
        }
        OrderAddBean.UserCarBean userCar = dataBean.getUserCar();
        if (userCar != null) {
            mCarBrandNameTv.setText(StringUtil.checkNull(userCar.getCarname()));
            mCarId = userCar.getId();
        }

        OrderAddBean.OrdersBean ordersBean = dataBean.getOrders();
        if (ordersBean != null) {
            String orderAmount = ordersBean.getOrderAmount();
            String price = ordersBean.getPrice();
            if (StringUtil.NoNullOrEmpty(orderAmount)) {
                ordermoney_tv.setText(orderAmount);
                mPriceTv.setText("￥" + orderAmount);
                totalMoney = orderAmount;
            }
            if (StringUtil.NoNullOrEmpty(price)) {
                mItpriceRelay.setVisibility(View.VISIBLE);
                mItpriceTv.setText("￥" + price);
            }
            orderType = ordersBean.getOrderType();
            orderId = ordersBean.getId();
            Log.e("tag", "orderType=" + orderType);
            if (orderType.equals("1") || orderType.equals("2")) {
                right_iv1.setVisibility(View.VISIBLE);
            } else {
                right_iv1.setVisibility(View.GONE);
            }
            switch (orderType) {
                case "0"://【用户】的维修（包含技师APP的维修推单）
                    proName_tv.setText("维修项目");
                    title_tv.setText("维修推单");
                    mGzTv.setVisibility(View.VISIBLE);
                    break;
                case "1"://所有保养
                    mRepairshopRelay.setVisibility(View.GONE);
                    second_relay.setVisibility(View.GONE);
                    proName_tv.setText("保养项目");
                    title_tv.setText("保养推单");
                    List<BaoYangFangAnResultBean.ListBaseBean> listService = dataBean.getListService();
                    if (listService != null && listService.size() > 0) {
                        serviceList.clear();
                        serviceList.addAll(listService);
                        mProgramRv.setAdapter(new ProgramAdapter(mActivity, listService, R.layout.rvitem_program));
                    }
                    break;
                case "2"://钣喷
                    proName_tv.setText("钣喷项目");
                    title_tv.setText("钣喷推单");
                    second_relay.setVisibility(View.GONE);
                    List<OrderAddBean.ListSpraySideBean> listSpraySide = dataBean.getListSpraySide();
                    if (listSpraySide != null && listSpraySide.size() > 0) {
                        spraySideList.clear();
                        spraySideList.addAll(listSpraySide);
                        RvUtils.setOption_noparam(mProgramRv, mActivity);
                        mProgramRv.setBackgroundColor(ResourcesUtils.getColor(R.color.white));
                        mProgramRv.setAdapter(new CoinSprayAdapter(mActivity, listSpraySide, R.layout.rvitem_coinspray));
                    }
                    String otherPrice = dataBean.getOtherPrice();
                    if (StringUtil.NoNullOrEmpty(otherPrice)){
                        mOtherPriceRelay.setVisibility(View.VISIBLE);
                        mOtherPriceTv.setText("￥"+otherPrice);
                    }
                    break;
                case "3"://保险
                    proName_tv.setText("保险项目");
                    title_tv.setText("保险推单");
                    mSummaryPriceTv.setText("保险维修");
                    mGzTv.setVisibility(View.VISIBLE);
                    mSumNameTv.setText("准备资料/保险信息");
                    OrderAddBean.InsuranceOrderBean insuranceOrder = dataBean.getInsuranceOrder();
                    if (insuranceOrder != null) {
                        String settlementMode = insuranceOrder.getSettlementMode();
                        carStatus = insuranceOrder.getCarStatus();
                        trailerMoney = insuranceOrder.getTrailerMoney();
                        if (settlementMode.equals("0")) {
                            summary_tv.setText("已选择核价后支付");
                        } else {
                            summary_tv.setText("按核价支付");
                        }
                    }
                    break;
                case "4"://【用户】的维修（包含技师APP的维修推单）
                    proName_tv.setText("维修项目");
                    title_tv.setText("维修推单");
                    mGzTv.setVisibility(View.VISIBLE);
                    break;
            }
        }

        List<ConfirmOrderResultBean.ListShopBean> listShopExit = dataBean.getListShopExit();
        if (listShopExit != null && listShopExit.size() > 0) {
            ConfirmOrderResultBean.ListShopBean listShopExitBean = listShopExit.get(0);
            repairShopId = listShopExitBean.getId();
            mRepairshopTv.setText(StringUtil.checkNull(listShopExitBean.getName()));
            repairList.clear();
            repairList.addAll(listShopExit);
        }


        orderStatus = dataBean.getOrderStatus();
        if (orderStatus != null) {
            switch (orderStatus) {
                case "0":
                    mSureTv.setText("确认");
                    bottom_relay.setVisibility(View.VISIBLE);
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

    }

    @Override
    public void showConfirmData(ConfirmOrderResultBean dataBean) {
        String from = "";
        Intent intent = new Intent(mContext, SureOrderActivity.class);
        intent.putExtra(Const.orderId, orderId);
        intent.putExtra("payMOney", totalMoney);
        intent.putExtra("carId", mCarId);
        intent.putExtra("repairShopId", repairShopId);
        intent.putExtra("type", orderType);
        intent.putExtra("repairShopName", mRepairshopTv.getText().toString());
        intent.putExtra("reparStatus", reparStatus + "");
        intent.putExtra("result", dataBean);
        if (orderType.equals("0") || orderType.equals("4")) {
            from = Const.weixiu;
        } else {
            switch (orderType) {
                case "1"://保养
                    from = Const.onekey;
                    intent.putExtra("data", (Serializable) serviceList);
                    break;
                case "2"://钣喷
                    from = Const.coinSpray;
                    intent.putExtra("data", (Serializable) spraySideList);
                    break;
                case "3"://保险
                    from = Const.insurance;
                    intent.putExtra("carStatus", carStatus);
                    intent.putExtra("trailerMoney", trailerMoney);
                    break;
            }
        }
        intent.putExtra(Const.intentTag, from);
        startActivity(intent);


    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103 && resultCode == 103 && data != null) {
            String shopName = data.getStringExtra("shopName");
            mRepairshopTv.setText(shopName);
            repairShopId = data.getStringExtra("repairShopId");
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
        if (!AppManager.getAppManager().isInStack(MainActivity.class)) {
            Intent intent = new Intent(mContext, MainActivity.class);
            Intent intent1 = new Intent(mContext, MessageRvActivity.class);
            startActivities(new Intent[]{intent, intent1});
        }
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
