package com.dream.moka.UI.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.dream.moka.Base.BaseFragmentV4;
import com.dream.moka.Bean.DriverHomeResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.EventBusBean.RefreshOrderBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.DriverCenterMainContract;
import com.dream.moka.Contract.SaveDriverLocationContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.DriverCenter.SaveDriverLocationPresenter;
import com.dream.moka.Presenter.DriverCenterMainPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.DriverOrderDetailActivity;
import com.dream.moka.UI.Activity.GotoGetCarActivity;
import com.dream.moka.UI.Activity.drivercenter.RechargeActivity;
import com.dream.moka.Utils.DateUtils.DateChaUtil;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.SpUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public class DriverCenterMainFragment extends BaseFragmentV4 implements DriverCenterMainContract, SaveDriverLocationContract.View {

    @Inject
    DriverCenterMainPresenter mDriverCenterMainPresenter;
    @Bind(R.id.right_swichiv)
    ImageView right_swichiv;
    @Bind(R.id.toreceivecar_lay)
    LinearLayout toreceivecar_lay;
    @Bind(R.id.order_relay)
    LinearLayout order_relay;
    @Bind(R.id.read)
    CheckBox mRead;
    @Bind(R.id.btn)
    TextView mBtn;
    @Bind(R.id.noMoneyLayout)
    RelativeLayout mNoMoneyLayout;
    @Bind(R.id.nomalLayout)
    ScrollView mNomalLayout;
    @Bind(R.id.todayMoney)
    TextView mTodayMoney;
    @Bind(R.id.orderMoney)
    TextView mOrderMoney;
    @Bind(R.id.yongjinMoney)
    TextView mYongjinMoney;
    @Bind(R.id.orderTodayNumber)
    TextView mOrderTodayNumber;
    @Bind(R.id.orderHistoryNumber)
    TextView mOrderHistoryNumber;
    @Bind(R.id.yajin)
    TextView mYajin;
    @Bind(R.id.name)
    TextView mName;

    @Inject
    SaveDriverLocationPresenter mSaveDriverLocationPresenter;
    @Bind(R.id.orderType_tv)
    TextView mOrderTypeTv;
    @Bind(R.id.tip_tv)
    TextView mTipTv;
    @Bind(R.id.address_StartTv)
    TextView mAddressStartTv;
    @Bind(R.id.address_EndTv)
    TextView mAddressEndTv;
    @Bind(R.id.beforDate_tv)
    TextView beforDate_tv;
    @Bind(R.id.tocarDate_tv)
    TextView mTocarDateTv;
    @Bind(R.id.tonameTip_tv)
    TextView mTonameTipTv;
    @Bind(R.id.tonamePhone_tv)
    TextView mTonamePhoneTv;
    @Bind(R.id.toPhone_tv)
    TextView mToPhoneTv;
    @Bind(R.id.songche_txt)
    TextView mSongcheTxt;
    @Bind(R.id.jiedan_txt)
    TextView mJiedanTxt;

    private boolean isOrder = true;
    private Dialog mLoadingDialog;
    private String dataBeanStatus = "";

    @Override
    public void initDaggerView(BaseComponent build) {
        build.inject(this);
    }

    @Override
    public void initView() {
        mSaveDriverLocationPresenter.attachView(this);
        mDriverCenterMainPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(getActivity());

        String driverOrderStatus = CommonAction.getDriverOrderStatus();
        if (driverOrderStatus.equals("0")) {
            right_swichiv.setImageResource(R.mipmap.icon_switch_default);
            isOrder = false;
            mJiedanTxt.setText("开始接单");
        } else {
            right_swichiv.setImageResource(R.mipmap.icon_switch_selected);
            isOrder = true;
            mJiedanTxt.setText("关闭接单");
        }

        EventBus.getDefault().register(this);

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_drivermain;
    }

    @Override
    public void initResume() {
        loadData();
        if (isOrder&&StringUtil.NoNullOrEmpty(CommonAction.getDriverId())) {
            Map<String, String> singleMap = MapUtils.getSingleMap();
            singleMap.put("token", CommonAction.getToken());
            singleMap.put("latitude", (String) SpUtils.getParam(Const.latitude, ""));
            singleMap.put("longitude", (String) SpUtils.getParam(Const.longitude, ""));
            singleMap.put("driverId", CommonAction.getDriverId());
            mSaveDriverLocationPresenter.saveDriverLocation(singleMap);
        }
    }

    private void loadData() {
        String driverStatus = CommonAction.getDriverStatus();
        if (driverStatus.equals("1")) {
            mNomalLayout.setVisibility(View.VISIBLE);
            mNoMoneyLayout.setVisibility(View.GONE);
            mLoadingDialog.show();
            mDriverCenterMainPresenter.getData();
        } else {
            mNomalLayout.setVisibility(View.GONE);
            mNoMoneyLayout.setVisibility(View.VISIBLE);
            mDriverCenterMainPresenter.getRechargePrice();
        }

        mName.setText(CommonAction.getDriverFirstStr() + "师傅");
        mTipTv.setText(CommonAction.getDriverFirstStr() + "师傅" + ResourcesUtils.getString(R.string.driver_ordertip));
        mTonameTipTv.setText(CommonAction.getDriverFirstStr() + "师傅" + ResourcesUtils.getString(R.string.driver_ordertipTo));
    }

    @Override
    public void initEvents() {

    }

    @Override
    public void initDta() {

    }

    @OnClick({R.id.right_swichiv, R.id.order_relay, R.id.toreceivecar_lay, R.id.btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.right_swichiv:
                mLoadingDialog.show();
                mDriverCenterMainPresenter.openService();
                break;
            case R.id.order_relay:
                if (StringUtil.NoNullOrEmpty(mDorderId)) {
                    Intent intent = new Intent(getContext(), DriverOrderDetailActivity.class);
                    intent.putExtra("orderId", mDorderId);
                    intent.putExtra("tag", dataBeanStatus);
                    startActivityForResult(intent, 521);
                }
                break;
            case R.id.toreceivecar_lay:
                if (StringUtil.NoNullOrEmpty(mQorderId)) {
                    Intent intent = new Intent(getContext(), GotoGetCarActivity.class);
                    intent.putExtra("orderId", mQorderId);
                    intent.putExtra("tag", dataBeanStatus);
                    startActivityForResult(intent, 522);
                }
                break;
            case R.id.btn:
                if (mRead.isChecked()) {
                    RechargeActivity.openAct(getContext(), "");
                } else {
                    ToastUtils.Toast_short("请阅读并同意");
                }

                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 521) {
            order_relay.setVisibility(View.GONE);

        }
        if (requestCode == 522) {
            toreceivecar_lay.setVisibility(View.GONE);

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void eventMain(RefreshOrderBean refreshOrderBean){
        if (refreshOrderBean.getEventStr().equals(Const.refresh)){
            loadData();
        }
    }

    /**
     * 获取押金金额
     *
     * @param s
     */
    @Override
    public void getYaJinData(String s) {
        mYajin.setText("面试通过后您需要缴纳" + s + "元押金才能成为司机押金可退（退押金后将停止代驾接单）");
    }

    private String mDorderId = "";//待接车的订单ID
    private String mQorderId = "";//去接车的订单ID

    @Override
    public void getHomeData(DriverHomeResultBean driverHomeResultBean) {
        boolean isResult1 = false;
        boolean isResult2 = false;
        if (driverHomeResultBean != null) {
            String todayIncome = driverHomeResultBean.getTodayIncome();
            String orderIncome = driverHomeResultBean.getOrderIncome();
            String rebateIncome = driverHomeResultBean.getRebateIncome();
            String totalOrder = driverHomeResultBean.getTotalOrder();
            String totalOrderToday = driverHomeResultBean.getTotalOrderToday();

            mTodayMoney.setText(todayIncome == null ? "0" : todayIncome);
            mOrderMoney.setText(orderIncome == null ? "0" : orderIncome);
            mYongjinMoney.setText(rebateIncome == null ? "0" : rebateIncome);
            mOrderTodayNumber.setText(totalOrderToday == null ? "0" : totalOrderToday);
            mOrderHistoryNumber.setText(totalOrder == null ? "0" : totalOrder);

            DriverHomeResultBean.repairShopBean repairShop = driverHomeResultBean.getRepairShop();
            List<DriverHomeResultBean.ListDJCBean> orderList = driverHomeResultBean.getListDJC();
            if (orderList != null && orderList.size() > 0) {
                isResult1 = true;
                DriverHomeResultBean.ListDJCBean orderBean = orderList.get(0);
                dataBeanStatus = orderBean.getStatus();
                String status = dataBeanStatus;
                if (status.equals("2")) {
                    isResult1 = true;
                    order_relay.setVisibility(View.VISIBLE);
                    mOrderTypeTv.setText("待接车");
                    //beforeDate
                    String updateDate = orderBean.getUpdateDate();
                    Date time = DateFormatUtil.getTime(updateDate, Const.YMD_HMS);
                    String dateCha = DateChaUtil.getDateCha(time.getTime());
                    beforDate_tv.setText(dateCha);

                    mAddressStartTv.setText(StringUtil.checkNull(orderBean.getSendAddress()));
                    mAddressEndTv.setText(StringUtil.checkNull(repairShop.getAddress()));
                    mDorderId = orderBean.getId();
                } else {
                    order_relay.setVisibility(View.GONE);
                }
            } else {
                order_relay.setVisibility(View.GONE);
            }

            List<DriverHomeResultBean.ListQJCBean> listQJCBeen = driverHomeResultBean.getListQJC();
            if (listQJCBeen != null && listQJCBeen.size() > 0) {
                isResult2 = true;
                DriverHomeResultBean.ListQJCBean dataBean = listQJCBeen.get(0);
                dataBeanStatus = dataBean.getStatus();
                String status = dataBeanStatus;
                if (status.equals("3")) {
                    isResult2 = true;
                    mSongcheTxt.setText("去接车");
                    toreceivecar_lay.setVisibility(View.VISIBLE);
                    String updateDate = dataBean.getUpdateDate();
                    Date timeDate = DateFormatUtil.getTime(updateDate, Const.YMD_HMS);
                    String time = DateFormatUtil.getTime(timeDate, Const.MD_HM);
                    mTocarDateTv.setText(time);
                    mTonamePhoneTv.setText(dataBean.getSendName() + "    " + dataBean.getSendMobile());
                    mToPhoneTv.setText(dataBean.getSendAddress());
                    mQorderId = dataBean.getId();

                } else {
                    toreceivecar_lay.setVisibility(View.GONE);
                }

            } else {
                toreceivecar_lay.setVisibility(View.GONE);
            }
            List<DriverHomeResultBean.ListDSCBean> listDSC = driverHomeResultBean.getListDSC();
            if (listDSC != null && listDSC.size() > 0) {

                DriverHomeResultBean.ListDSCBean orderBean = listDSC.get(0);
                dataBeanStatus = orderBean.getStatus();
                String status = dataBeanStatus;
                if (status.equals("7")) {
                    mOrderTypeTv.setText("待送车");
                    order_relay.setVisibility(View.VISIBLE);

                    //beforeDate
                    String updateDate = orderBean.getUpdateDate();
                    Date time = DateFormatUtil.getTime(updateDate, Const.YMD_HMS);
                    String dateCha = DateChaUtil.getDateCha(time.getTime());
                    beforDate_tv.setText(dateCha);

                    mAddressStartTv.setText(StringUtil.checkNull(repairShop.getAddress()));
                    mAddressEndTv.setText(StringUtil.checkNull(orderBean.getBackAddress()));
                    mDorderId = orderBean.getId();

                } else {
                    if (!isResult1) {
                        order_relay.setVisibility(View.GONE);
                    }

                }

            } else {
                if (!isResult1) {
                    order_relay.setVisibility(View.GONE);
                }
            }
            List<DriverHomeResultBean.ListQSCBean> listQSCBeans = driverHomeResultBean.getListQSC();
            if (listQSCBeans != null && listQSCBeans.size() > 0) {
                DriverHomeResultBean.ListQSCBean dataBean = listQSCBeans.get(0);
                dataBeanStatus = dataBean.getStatus();
                String status = dataBeanStatus;
                if (status.equals("8")) {
                    mSongcheTxt.setText("去送车");
                    toreceivecar_lay.setVisibility(View.VISIBLE);
                    String updateDate = dataBean.getUpdateDate();
                    Date timeDate = DateFormatUtil.getTime(updateDate, Const.YMD_HMS);
                    String time = DateFormatUtil.getTime(timeDate, Const.MD_HM);
                    mTocarDateTv.setText(time);
                    mTonamePhoneTv.setText(repairShop.getName() + "    " + repairShop.getContactTel());
                    mToPhoneTv.setText(StringUtil.checkNull(repairShop.getAddress()));
                    mQorderId = dataBean.getId();

                } else {
                    if (!isResult2) {
                        toreceivecar_lay.setVisibility(View.GONE);
                    }

                }

            } else {
                if (!isResult2) {
                    toreceivecar_lay.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 改变接单状态
     */
    @Override
    public void changeOrderStaus() {
        if (isOrder) {
            right_swichiv.setImageResource(R.mipmap.icon_switch_default);
            isOrder = false;
            SpUtils.savaUserInfo(Const.OrderStasus, "0");
            mJiedanTxt.setText("开始接单");
        } else {
            right_swichiv.setImageResource(R.mipmap.icon_switch_selected);
            isOrder = true;
            SpUtils.savaUserInfo(Const.OrderStasus, "1");
            mJiedanTxt.setText("关闭接单");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void showData(EmptyBean dataBean) {

    }
}
