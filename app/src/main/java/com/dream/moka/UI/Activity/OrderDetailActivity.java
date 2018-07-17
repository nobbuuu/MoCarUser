package com.dream.moka.UI.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Adapter.Orders.CoinSprayAdapter;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Base.RVBaseAdapter;
import com.dream.moka.Base.RVBaseHolder;
import com.dream.moka.Bean.Message.OrderAddBean;
import com.dream.moka.Bean.OrderDetailBean;
import com.dream.moka.Bean.OrderDriverBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.OrderDetailContract;
import com.dream.moka.IM.im.activity.ChatActivity;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.OrderDetailPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.other.WebViewActivity;
import com.dream.moka.Utils.GlidUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.RvUtils;
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
 */
public class OrderDetailActivity extends BaseCommonActivity implements OrderDetailContract {
    @Inject
    OrderDetailPresenter mOrderDetailPresenter;
    @Bind(R.id.daijia_relay)
    RelativeLayout daijia_relay;
    @Bind(R.id.line)
    View mLine;
    @Bind(R.id.orderType)
    TextView mOrderType;
    @Bind(R.id.lay1)
    LinearLayout mLay1;
    @Bind(R.id.line1)
    View mLine1;
    @Bind(R.id.baoy_rv)
    RecyclerView mBaoyRv;
    @Bind(R.id.line2)
    View mLine2;
    @Bind(R.id.parts_rv)
    RecyclerView mPartsRv;
    @Bind(R.id.line3)
    View mLine3;
    @Bind(R.id.line4)
    View mLine4;
    @Bind(R.id.driverinfo_jtv)
    TextView mDriverinfoJtv;
    @Bind(R.id.driverinfo_stv)
    TextView mDriverinfoStv;
    @Bind(R.id.status)
    TextView mStatus;
    @Bind(R.id.carNo)
    TextView mCarNo;
    @Bind(R.id.carName)
    TextView mCarName;
    @Bind(R.id.orderTime)
    TextView mOrderTime;
    @Bind(R.id.orderShopname)
    TextView mOrderShopname;
    @Bind(R.id.orderAddress)
    TextView mOrderAddress;
    @Bind(R.id.orderType2)
    TextView mOrderType2;
    @Bind(R.id.orderMoney)
    TextView mOrderMoney;
    @Bind(R.id.orderNo2)
    TextView mOrderNo2;
    @Bind(R.id.orderTime2)
    TextView mOrderTime2;
    @Bind(R.id.jc_drivername)
    TextView mJcDrivername;
    @Bind(R.id.jc_driverPhone)
    TextView mJcDriverPhone;
    @Bind(R.id.sc_drivername)
    TextView mScDrivername;
    @Bind(R.id.sc_driverPhone)
    TextView mScDriverPhone;
    @Bind(R.id.jc_drivername1)
    TextView mJcDrivername1;
    @Bind(R.id.jc_driverPhone1)
    TextView mJcDriverPhone1;
    @Bind(R.id.jc_address)
    TextView mJcAddress;
    @Bind(R.id.sc_drivername1)
    TextView mScDrivername1;
    @Bind(R.id.sc_driverPhone1)
    TextView mScDriverPhone1;
    @Bind(R.id.sc_address)
    TextView mScAddress;
    @Bind(R.id.djf_tv)
    TextView mDjfTv;
    @Bind(R.id.onkeydk_relay)
    RelativeLayout mOnkeydkRelay;
    @Bind(R.id.vehicle_tra1)
    TextView mVehicleTra1;
    @Bind(R.id.vehicle_tra2)
    TextView mVehicleTra2;
    private String mId;
    private ProgramAdapter mProgramAdapter;
    private List<OrderDetailBean.ListServiceBean> gramList = new ArrayList<>();

    public static void openAct(Context context, String id) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_orderdetail;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {

        mOrderDetailPresenter.attachView(this);
        mId = getIntent().getStringExtra("id");
        RvUtils.setOptionnoLine(mBaoyRv, this);
        mProgramAdapter = new ProgramAdapter(this, gramList, R.layout.rvitem_program);

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
        mOrderDetailPresenter.getDetailData(mId);
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.driverinfo_jtv, R.id.driverinfo_stv, R.id.daijia_relay, R.id.vehicle_tra1, R.id.vehicle_tra2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.driverinfo_jtv:
                Intent intent = new Intent(mContext, DriverInfoActivity.class);
                intent.putExtra(Const.intentTag, "接车");
                intent.putExtra("id", mJcDriverId);
                intent.putExtra("time", time);
                startActivity(intent);
                break;
            case R.id.driverinfo_stv:
                Intent intent1 = new Intent(mContext, DriverInfoActivity.class);
                intent1.putExtra(Const.intentTag, "送车");
                intent1.putExtra("id", mScDriverId);
                intent1.putExtra("time", time);
                startActivity(intent1);
                break;
            case R.id.vehicle_tra1://接车轨迹
                GuijiMapActivity.openAct(mContext, orderId, mJcDriverId, "0",rsLongitude,rsLatitude);
                break;
            case R.id.vehicle_tra2://送车轨迹
                GuijiMapActivity.openAct(mContext, orderId, mScDriverId, "1",rsLongitude,rsLatitude);
                break;
            case R.id.daijia_relay:
                IntentUtils.toActivity(DaiJiaActivity.class, mActivity);
                break;

        }
    }

    @Override
    public void showError() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    private String mJcDriverId = "";
    private String mScDriverId = "";
    private String orderId = "";
    private String time = "";
    private String rsLongitude = "";
    private String rsLatitude = "";

    @Override
    public void getDetaildataSuccess(OrderDetailBean orderDetailBean) {
        OrderDetailBean.OrderBean order = orderDetailBean.getOrder();
        orderId = order.getId();
        String orderType = order.getOrderType();
        switch (orderType) {//订单类型 0【用户】的维修（包含技师APP的维修推单）、1所有保养 、2钣喷 、3保险、 4pc总后台的维修推单（只限pc总后台）
            case "0":
                mBaoyRv.setAdapter(mProgramAdapter);
                mOrderType.setText("维修");
                mOrderType2.setText("维修项目");
                break;
            case "1":
                mBaoyRv.setAdapter(mProgramAdapter);
                mOrderType.setText("保养");
                mOrderType2.setText("保养项目");
                if (order.getOrderCode().contains("mt")) {//0.1km的保养
                    mOnkeydkRelay.setVisibility(View.VISIBLE);
                }
                break;
            case "2":
                mOrderType.setText("钣喷");
                mOrderType2.setText("钣喷项目");
                break;
            case "3":
                mOrderType.setText("保险");
                mOrderType2.setText("保险项目");
                break;
            default:
                mBaoyRv.setAdapter(mProgramAdapter);
                mOrderType.setText("维修");
                mOrderType2.setText("维修项目");
                break;
        }
        String status = order.getStatus();
        String statusString = "服务中";
        switch (status) {
            case "1":
                statusString = "待支付";
                break;
            case "3":
                time = order.getDriToUser();
                break;
            case "4":
                time = order.getDriToUserToRepair();
                break;
            case "8":
                time = order.getDriToRepair();
                break;
            case "9":
                time = order.getDriToRepairToUser();
                break;
            case "10":
                statusString = "已完成";
                break;
            case "11":
                statusString = "退款中";
                break;
            case "99":
                statusString = "交易关闭";
                break;
        }
        if (StringUtil.NoNullOrEmpty(order.getDjf())) {
            mDjfTv.setText("￥" + order.getDjf());
        }
        mStatus.setText(statusString);
        String sendAddress = order.getSendAddress();
        String backAddress = order.getBackAddress();
        String sendName = order.getSendName();
        String backName = order.getBackName();
        String sendMobile = order.getSendMobile();
        String backMobile = order.getBackMobile();
        mJcDrivername1.setText("" + sendName);
        mJcDriverPhone1.setText("" + sendMobile);
        mScDrivername1.setText("" + backName);
        mScDriverPhone1.setText("" + backMobile);

        mJcAddress.setText("" + sendAddress);
        mScAddress.setText("" + backAddress);
        OrderDetailBean.UserCarBean userCar = orderDetailBean.getUserCar();
        String cardNo = userCar.getCardNo();
        mCarNo.setText(cardNo + "");

        String carname = userCar.getCarname();
        mCarName.setText(carname + "");

        String createDate = order.getCreateDate();
        mOrderTime.setText(createDate + "");
        mOrderTime2.setText(createDate + "");

        OrderDetailBean.RepairShopBean repairShop = orderDetailBean.getRepairShop();
        if (repairShop != null) {
            String name = repairShop.getName();
            mOrderShopname.setText(name + "");
            rsLongitude = repairShop.getLongitude();
            rsLatitude = repairShop.getLatitude();
            String province = repairShop.getProvince();
            String city = repairShop.getCity();
            String region = repairShop.getRegion();
            String address = repairShop.getAddress();
            mOrderAddress.setText(province + city + region + address);
        }

        List<OrderDetailBean.ListServiceBean> listSP = orderDetailBean.getListService();
        if (listSP != null && listSP.size() != 0) {
            gramList.addAll(listSP);
            mProgramAdapter.notifyDataSetChanged();
        }

        String payAmount = order.getPayAmount();
        mOrderMoney.setText(payAmount + "");

        String orderCode = order.getOrderCode();
        mOrderNo2.setText(orderCode + "");

        OrderDetailBean.DriverJCBean driverJC = orderDetailBean.getDriverJC();
        if (driverJC != null) {
            String driverName = driverJC.getDriverName();
            mJcDrivername.setText("" + driverName);
            String phone = driverJC.getPhone();
            mJcDriverPhone.setText("" + phone);
            mDriverinfoJtv.setVisibility(View.VISIBLE);
            mVehicleTra1.setVisibility(View.VISIBLE);
            mJcDriverId = driverJC.getId();
        }
        OrderDetailBean.DriverSCBean driverSC = orderDetailBean.getDriverSC();
        if (driverSC != null) {
            String driverName = driverSC.getDriverName();
            mScDrivername.setText("" + driverName);
            String phone = driverSC.getPhone();
            mScDriverPhone.setText("" + phone);
            mDriverinfoStv.setVisibility(View.VISIBLE);
            mVehicleTra2.setVisibility(View.VISIBLE);
            mScDriverId = driverSC.getId();
        }
        List<OrderAddBean.ListSpraySideBean> listSpraySide = orderDetailBean.getListSpraySide();
        if (listSpraySide != null && listSpraySide.size() > 0) {
            RvUtils.setOption_noparam(mBaoyRv, mActivity);
            mBaoyRv.setAdapter(new CoinSprayAdapter(mActivity, listSpraySide, R.layout.rvitem_coinspray));
        }
    }

    @Override
    public void getDriverData(OrderDriverBean results) {

    }


    private class ProgramAdapter extends RVBaseAdapter<OrderDetailBean.ListServiceBean> {

        public ProgramAdapter(Activity context, List<OrderDetailBean.ListServiceBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final OrderDetailBean.ListServiceBean listBaseBean, final int position) {
            OrderDetailBean.ListServiceBean.ServiceItemBean serviceItem = listBaseBean.getServiceItem();
            List<OrderDetailBean.ListServiceBean.ListPartBean> listPart = listBaseBean.getListPart();
            holder.setText(R.id.program_name, serviceItem.getName());
           /* TextView servicePrice_tv = holder.getView(R.id.money);
            String serviceItemPrice = serviceItem.getPrice();
            if (StringUtil.NoNullOrEmpty(serviceItemPrice)){
                servicePrice_tv.setVisibility(View.VISIBLE);
                servicePrice_tv.setText("¥" + serviceItemPrice);
            }else {
                servicePrice_tv.setVisibility(View.GONE);
            }*/
            LinearLayout pro_lay = holder.getView(R.id.pro_lay);
            pro_lay.setVisibility(View.VISIBLE);
            RecyclerView product_rv = holder.getView(R.id.product_rv);
            RvUtils.setOption_noparam(product_rv, mActivity);
            product_rv.setAdapter(new ProductAdapter(mActivity, listPart, R.layout.rvitem_product));
        }
    }

    private class ProductAdapter extends RVBaseAdapter<OrderDetailBean.ListServiceBean.ListPartBean> {

        public ProductAdapter(Activity context, List<OrderDetailBean.ListServiceBean.ListPartBean> data, int layoutId) {
            super(context, data, layoutId);
        }

        @Override
        public void onBind(RVBaseHolder holder, final OrderDetailBean.ListServiceBean.ListPartBean listPartBean, int position) {
            String brands = listPartBean.getBrands();
            String name = listPartBean.getName();
//            String price = listPartBean.getPrice();
            String picture = listPartBean.getPicture();
            ImageView view = holder.getView(R.id.img);
            holder.setText(R.id.brandName, name == null ? "" : name);
//            holder.setText(R.id.productname, brands == null ? "" : brands);
            TextView partNameTv = holder.getView(R.id.productname);
//            TextView priceTv = holder.getView(R.id.price);
            TextView numberTv = holder.getView(R.id.number);
            partNameTv.setVisibility(View.GONE);
//            priceTv.setVisibility(View.VISIBLE);
            numberTv.setVisibility(View.VISIBLE);
            String count = listPartBean.getCount();
            if (count.equals("0")){
                listPartBean.setCount("1");
            }
            numberTv.setText("x"+listPartBean.getCount());
//            holder.setText(R.id.price, price == null ? "" : "¥" + price);
            GlidUtils.LoadImgForUrl(mContext, Const.BannerUrl + picture, view, R.mipmap.img_placeholderfigure01);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    ToastUtils.Toast_short("查看详情");
                    if (StringUtil.NoNullOrEmpty(listPartBean.getId())) {
                        IntentUtils.toActivityWithUrl(WebViewActivity.class, mContext, listPartBean.getId(), "9");
                    }
                }
            });
        }
    }


}
