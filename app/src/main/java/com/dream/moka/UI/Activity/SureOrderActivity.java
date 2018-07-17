package com.dream.moka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.dream.moka.Adapter.Maintain.FreeMaintaiAdapter;
import com.dream.moka.Adapter.Orders.CoinSprayAdapter;
import com.dream.moka.Adapter.Orders.ProgramAdapter;
import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.AddressListResultBean;
import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.InvoiceBean;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Bean.Maintain.FreeListBean;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Bean.Maintain.FreeMaintainAllBean;
import com.dream.moka.Bean.Message.OrderAddBean;
import com.dream.moka.Bean.Other.OnlyStringBean;
import com.dream.moka.Bean.PayResultBean;
import com.dream.moka.Bean.SaveOrderResultBean;
import com.dream.moka.Bean.SprayResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.FreeMaitain.ConfirmMainTainContract;
import com.dream.moka.Contract.OrderPayContract;
import com.dream.moka.Contract.SureOrderContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.OrderPayPresenter;
import com.dream.moka.Presenter.SureOrderPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.address.AddressActivity;
import com.dream.moka.UI.order.OrderPaySuccessActivity;
import com.dream.moka.Utils.DateUtils.DateFormatUtil;
import com.dream.moka.Utils.MyUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.RvUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;
import com.google.gson.Gson;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class SureOrderActivity extends BaseCommonActivity implements SureOrderContract, ConfirmMainTainContract.View, OrderPayContract {

    @Inject
    SureOrderPresenter mSureOrderPresenter;
    @Bind(R.id.tip_tv)
    TextView tip_tv;
    @Bind(R.id.line_view)
    View line_view;
    @Bind(R.id.select_timerelay)
    RelativeLayout select_timerelay;
    @Bind(R.id.sendcar_lay)
    LinearLayout sendcar_lay;
    @Bind(R.id.swich_btn1)
    ImageView swich_btn1;
    @Bind(R.id.swich_btn2)
    ImageView swich_btn2;
    @Bind(R.id.swich_btn3)
    ImageView swich_btn3;
    @Bind(R.id.repairshop_tv)
    TextView repairshop_tv;
    @Bind(R.id.yuyuedate_tv)
    TextView yuyuedate_tv;
    @Bind(R.id.onedikou_lay)
    LinearLayout onedikou_lay;
    @Bind(R.id.jifendikou_lay)
    LinearLayout jifendikou_lay;
    @Bind(R.id.yuyue_tv)
    TextView mYuyueTv;
    @Bind(R.id.address_iv)
    ImageView mAddressIv;
    @Bind(R.id.address_tv)
    TextView mAddressTv;
    @Bind(R.id.jaddress_lay)
    LinearLayout mJaddressLay;
    @Bind(R.id.address_ivs)
    ImageView mAddressIvs;
    @Bind(R.id.address_tvs)
    TextView mAddressTvs;
    @Bind(R.id.saddress_lay)
    LinearLayout mSaddressLay;
    @Bind(R.id.right_iv1)
    ImageView mRightIv1;
    @Bind(R.id.repairshop_relay)
    RelativeLayout mRepairshopRelay;
    @Bind(R.id.baoyang_rv)
    RecyclerView mBaoyangRv;
    @Bind(R.id.onokeydeduction_relay)
    RelativeLayout mOnokeydeductionRelay;
    @Bind(R.id.jifendikou_relay)
    RelativeLayout mJifendikouRelay;
    @Bind(R.id.youhuicard_relay)
    RelativeLayout mYouhuicardRelay;
    @Bind(R.id.right_iv2)
    ImageView mRightIv2;
    @Bind(R.id.swich_btn4)
    ImageView swich_btn4;
    @Bind(R.id.invoice_relay)
    RelativeLayout mInvoiceRelay;
    @Bind(R.id.xupay_tv)
    TextView mXupayTv;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.carNo)
    TextView mCarNo;
    @Bind(R.id.carName)
    TextView mCarName;
    @Bind(R.id.jiecheName)
    TextView mJiecheName;
    @Bind(R.id.jiechePhone)
    TextView mJiechePhone;
    @Bind(R.id.sendName)
    TextView mSendName;
    @Bind(R.id.sendPhone)
    TextView mSendPhone;
    @Bind(R.id.invoiceType_tv)
    TextView invoiceType_tv;
    @Bind(R.id.card_tv)
    TextView card_tv;

    public static final int JIECHE_CODE = 100;
    public static final int SONGCHE_CODE = 101;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.allMoney)
    TextView mAllMoney;
    @Bind(R.id.hejiMoney)
    TextView mHejiMoney;
    @Bind(R.id.hejiMoney2)
    TextView mHejiMoney2;
    @Bind(R.id.djf_tv)
    TextView mDjfTv;
    @Bind(R.id.jifenNum_tv)
    TextView mJifenNumTv;
    @Bind(R.id.jifenMoney_tv)
    TextView mJifenMoneyTv;
    @Bind(R.id.serviceName_Tv)
    TextView mServiceNameTv;
    @Bind(R.id.wxname_tv)
    TextView mWxnameTv;
    @Bind(R.id.djfName_tv)
    TextView mDjfNameTv;
    private TimePickerView.Builder dateDialog;

    private boolean isOpen1, isOpen2 = true, isOpen3 = true, isOpen4 = true;
    private String mTagetId;
    private String mCarId;
    private String mrepairShopName, reparStatus;
    private String mMile;
    private List<BaoYangFangAnResultBean.ListBaseBean> gramList;
    private ProgramAdapter mProgramAdapter;
    private String mJCAddressId = "";
    private String mSCAddressId = "";
    private List<ConfirmOrderResultBean.ListShopBean> mListShopBeans = new ArrayList<>();
    private String mShopId = "";
    private String mPayMOney;
    private String mRealPrice;
    private String sideIds;
    private ConfirmOrderResultBean mConfirmOrderResultBean;
    private InvoiceBean mInvoiceBean;
    private String mInvoiceId;
    private String mInvoiceType;
    private String serviceType = "0";
    private String type, orderId = "";
    private String mDjfNum, score, carsNum, payDjf, deduMoney;
    private String carStatus, trailerMoney;
    private String Tag;
    private boolean isSelectRepair = true;
    //free
    private List<FreeMaintainAllBean.ServiceResultBean> freeList;
    private List<FreeListBean> serviceList = new ArrayList<>();


    @Inject
    OrderPayPresenter mOrderPayPresenter;

    //0.1km保养订单
    public static void openAct(Context context, String from, String payMOney, String realPrice, String tagetId, String carId,
                               String mile, List<BaoYangFangAnResultBean.ListBaseBean> data, ConfirmOrderResultBean results, String serviceType) {
        Intent intent = new Intent(context, SureOrderActivity.class);
        intent.putExtra(Const.intentTag, from);
        intent.putExtra("tagetId", tagetId);
        intent.putExtra("payMOney", payMOney);
        intent.putExtra("realPrice", realPrice);
        intent.putExtra("carId", carId);
        intent.putExtra("mile", mile);
        intent.putExtra("data", (Serializable) data);
        intent.putExtra("result", results);
        intent.putExtra("serviceType", serviceType);
        context.startActivity(intent);
    }

    //增项订单
    public static void openAct(Context context, String from, String totalMoney, String carId, String repairShopId, List<FreeMainTainBean.ListServiceBean> data, int type) {
        Intent intent = new Intent(context, SureOrderActivity.class);
        intent.putExtra(Const.intentTag, from);
        intent.putExtra("payMOney", totalMoney);
        intent.putExtra("carId", carId);
        intent.putExtra("repairShopId", repairShopId);
        intent.putExtra("data", (Serializable) data);
        intent.putExtra("type", type + "");
        context.startActivity(intent);
    }

    //钣喷订单
    public static void openAct(Context context, String from, SprayResultBean data, ConfirmOrderResultBean results) {
        Intent intent = new Intent(context, SureOrderActivity.class);
        intent.putExtra(Const.intentTag, from);
        intent.putExtra("payMOney", data.getSum());
        intent.putExtra("carId", data.getCarId());
        intent.putExtra("repairShopId", data.getRepairId());
        intent.putExtra("repairShopName", data.getRepairName());
        intent.putExtra("data", (Serializable) data.getShopData());
        intent.putExtra("result", results);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sureorder;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mSureOrderPresenter.attachView(this);
        mOrderPayPresenter.attachView(this);
        Tag = getIntent().getStringExtra(Const.intentTag);
        RvUtils.setOptionnoLineforScroll(mBaoyangRv, this);
        mPayMOney = getIntent().getStringExtra("payMOney");
        mConfirmOrderResultBean = (ConfirmOrderResultBean) getIntent().getSerializableExtra("result");
        switch (Tag) {
            case Const.free://自选
                freeMaintainInit();
                break;
            case Const.onekey://0.1保养
                onekeyMaintainInit();
                break;
            case Const.weixiu://维修
                weixiuInit();
                break;
            case Const.coinSpray://钣喷
                coinSprayInit();
                break;
            case Const.insurance://保险
                insuranceInit();
                break;
        }
        mHejiMoney.setText("合计：¥" + mPayMOney);

        if (StringUtil.NoNullOrEmpty(orderId)) {//平台推单
            swich_btn1.setImageResource(R.mipmap.icon_switch_selected);
            select_timerelay.setVisibility(View.VISIBLE);
            isOpen1 = true;
        }
    }

    private String djfType = "0";

    private void insuranceInit() {
        orderId = getIntent().getStringExtra(Const.orderId);
        mCarId = getIntent().getStringExtra("carId");
        mShopId = getIntent().getStringExtra("repairShopId");
        carStatus = getIntent().getStringExtra("carStatus");
        trailerMoney = getIntent().getStringExtra("trailerMoney");
        mServiceNameTv.setText("保险项目");
        title_tv.setText("确认订单");
        mrepairShopName = getIntent().getStringExtra("repairShopName");
        repairshop_tv.setText(StringUtil.checkNull(mrepairShopName));
        if (carStatus.equals("1")) {
            mDjfNameTv.setText("代驾费        需要拖车");
            djfType = "1";
        }
        mWxnameTv.setVisibility(View.VISIBLE);
        if (Tag.equals(Const.insurance)) {
            mWxnameTv.setText("保险维修");
        }
    }

    private void coinSprayInit() {
        orderId = getIntent().getStringExtra(Const.orderId);
        mCarId = getIntent().getStringExtra("carId");
        mShopId = getIntent().getStringExtra("repairShopId");
        mServiceNameTv.setText("钣喷项目");
        title_tv.setText("钣金喷漆");
        mrepairShopName = getIntent().getStringExtra("repairShopName");
        repairshop_tv.setText(StringUtil.checkNull(mrepairShopName));
        List<OrderAddBean.ListSpraySideBean> coinSprayList = (List<OrderAddBean.ListSpraySideBean>) getIntent().getSerializableExtra("data");
        if (coinSprayList != null && coinSprayList.size() > 0) {
            RvUtils.setOption_noparam(mBaoyangRv, mActivity);
            mBaoyangRv.setBackgroundColor(ResourcesUtils.getColor(R.color.white));
            mBaoyangRv.setAdapter(new CoinSprayAdapter(mActivity, coinSprayList, R.layout.rvitem_coinspray));
            mBaoyangRv.setNestedScrollingEnabled(false);
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < coinSprayList.size(); i++) {
                if (i != coinSprayList.size() - 1) {
                    buffer.append(coinSprayList.get(i).getId() + ",");
                } else {
                    buffer.append(coinSprayList.get(i).getId());
                }
            }
            sideIds = buffer.toString();
        }
        if (!StringUtil.NoNullOrEmpty(orderId)) {
            //维修商不可选
            mRightIv1.setVisibility(View.GONE);
            isSelectRepair = false;
        }
    }

    private void weixiuInit() {
        mServiceNameTv.setText("维修项目");
        type = getIntent().getStringExtra("type");
        orderId = getIntent().getStringExtra(Const.orderId);
        mCarId = getIntent().getStringExtra("carId");
        mShopId = getIntent().getStringExtra("repairShopId");
        mrepairShopName = getIntent().getStringExtra("repairShopName");
        repairshop_tv.setText(StringUtil.checkNull(mrepairShopName));
        mRightIv1.setVisibility(View.GONE);
        isSelectRepair = false;
        reparStatus = getIntent().getStringExtra("reparStatus");
        mWxnameTv.setVisibility(View.VISIBLE);
        if (reparStatus.equals("2")) {
            mWxnameTv.setText("一口价");
            djfType = "2";
        }

    }

    private void onekeyMaintainInit() {
        jifendikou_lay.setVisibility(View.GONE);
        mTagetId = getIntent().getStringExtra("tagetId");
        mCarId = getIntent().getStringExtra("carId");
        mRealPrice = getIntent().getStringExtra("realPrice");
        mMile = getIntent().getStringExtra("mile");
        orderId = getIntent().getStringExtra(Const.orderId);
        if (StringUtil.NoNullOrEmpty(orderId)) {
            //维修商不可选
            mRightIv1.setVisibility(View.GONE);
            isSelectRepair = false;
        } else {
            onedikou_lay.setVisibility(View.VISIBLE);

        }
        serviceType = getIntent().getStringExtra("serviceType");
        gramList = (List<BaoYangFangAnResultBean.ListBaseBean>) getIntent().getSerializableExtra("data");
        mShopId = getIntent().getStringExtra("repairShopId");
        mrepairShopName = getIntent().getStringExtra("repairShopName");
        repairshop_tv.setText(StringUtil.checkNull(mrepairShopName));
        mProgramAdapter = new ProgramAdapter(this, gramList, R.layout.rvitem_program);
        mBaoyangRv.setAdapter(mProgramAdapter);
        mBaoyangRv.setNestedScrollingEnabled(false);
    }

    private void freeMaintainInit() {
        mCarId = getIntent().getStringExtra("carId");
        freeList = (List<FreeMaintainAllBean.ServiceResultBean>) getIntent().getSerializableExtra("data");
        mShopId = getIntent().getStringExtra("repairShopId");
        mRepairshopRelay.setVisibility(View.GONE);
        mBaoyangRv.setAdapter(new FreeMaintaiAdapter(this, freeList, R.layout.rvitem_program, "sure"));

        serviceList.clear();
        for (int i = 0; i < freeList.size(); i++) {
            FreeListBean serviceBean = new FreeListBean();
            serviceBean.setServiceId(freeList.get(i).getServiceItem().getId());
            List<FreeMaintainAllBean.ServiceResultBean.ListServiceBean> productSelect = freeList.get(i).getListService();
            for (int j = 0; j < productSelect.size(); j++) {
                List<FreeMaintainAllBean.ServiceResultBean.ListServiceBean.ListPartBean> listPart = productSelect.get(j).getListPart();
                List<FreeListBean.PartIdsBean> partIdsBeanList = new ArrayList<>();
                boolean isSelect = false;
                for (int p = 0; p < listPart.size(); p++) {
                    if (listPart.get(p).isSelect()) {
                        FreeListBean.PartIdsBean partIdsBean = new FreeListBean.PartIdsBean();
                        partIdsBean.setPartId(listPart.get(p).getId());
                        partIdsBean.setCount(listPart.get(p).getCount() + "");
                        partIdsBeanList.add(partIdsBean);
                        isSelect = true;
                    }
                }
                if (isSelect) {
                    serviceBean.setPartIds(partIdsBeanList);
                }
            }
            serviceList.add(serviceBean);
        }
    }

    @Override
    public String initTitleText() {
        return "确认订单";
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
        getDataSuccess(mConfirmOrderResultBean);
        //获取接送加保养平均耗时
        mSureOrderPresenter.getTimeData();

    }

    @Override
    public void eventListener() {
        dateDialog = new TimePickerView.Builder(this, new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                long time = date.getTime();
                Log.e("tag", "time=" + time);
                String dateStr = DateFormatUtil.getTime(time, "yyyy-MM-dd  HH:mm");
                if (dateStr != null) {
                    if (MyUtils.IsToday(date)) {//当天
                        if (setTimeStr.equals(dateStr)||(time-System.currentTimeMillis())>=twoHour) {
                            if (MyUtils.isCurrentInTimeScope(time, 8, 0, 19, 0)) {
                                yuyuedate_tv.setText(dateStr);
                            } else {
                                ToastUtils.Toast_short("预约时间请在8：00—19：00之间选择");
                                yuyuedate_tv.setText("");
                            }
                        } else {
                            ToastUtils.Toast_short("预约时间需大于当前2小时");
                            yuyuedate_tv.setText("");
                        }
                    } else {
                        if ((time - System.currentTimeMillis()) < (long) (60 * 60 * 1000 * 24 * 15) && (time - System.currentTimeMillis()) > 0) {
                            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            String t = format.format(date);
                            String substring = t.substring(11, 13);
                            Log.e("msg====>>", substring);
                            if (Double.valueOf(substring) >= 8 && Double.valueOf(substring) <= 19) {
                                if (Double.valueOf(substring) == 8 || Double.valueOf(substring) == 19) {
                                    String substring1 = t.substring(14, 16);
                                    if (substring1.equals("00")) {
                                        yuyuedate_tv.setText(dateStr);
                                    } else {
                                        ToastUtils.Toast_short("预约时间请在8：00—19：00之间选择");
                                        yuyuedate_tv.setText("");
                                    }
                                } else {
                                    yuyuedate_tv.setText(dateStr);
                                }

                            } else {
                                ToastUtils.Toast_short("预约时间请在8：00—19：00之间选择");
                                yuyuedate_tv.setText("");
                            }
                        } else {
                            ToastUtils.Toast_short("预约时间间隔需小于15天");
                            yuyuedate_tv.setText("");
                        }
                    }
                }
                Log.e("tag", "dateStr=" + dateStr);
            }
        }).setType(new boolean[]{true, true, true, true, true, false})
                .setContentSize(16)//滚轮文字大小
                .setTitleSize(13)//标题文字大小
                .setCancelText("取消")//取消按钮文字
                .setLabel(" 年", "月", "日", "时", "分", "秒")
                .isCyclic(false);//是否循环滚动


    }

    private long twoHour = 7200000;
    private String setTimeStr = "";

    private Calendar getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis() + twoHour);
        Log.e("tag", "setDate=" + calendar.getTimeInMillis());
        setTimeStr = DateFormatUtil.getTime(calendar.getTimeInMillis(), "yyyy-MM-dd  HH:mm");
        return calendar;
    }

    @OnClick({R.id.back_relay, R.id.swich_btn1, R.id.sure_tv, R.id.youhuicard_relay, R.id.swich_btn2, R.id.swich_btn3, R.id.swich_btn4,
            R.id.repairshop_relay, R.id.invoice_relay, R.id.select_timerelay, R.id.jaddress_lay, R.id.saddress_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.swich_btn1:
                if (isOpen1) {
                    if (!StringUtil.NoNullOrEmpty(orderId)) {
                        tip_tv.setVisibility(View.VISIBLE);
                        line_view.setVisibility(View.GONE);
                        select_timerelay.setVisibility(View.GONE);
                        swich_btn1.setImageResource(R.mipmap.icon_switch_default);
                        isOpen1 = false;
                    }
                } else {
                    tip_tv.setVisibility(View.GONE);
                    line_view.setVisibility(View.VISIBLE);
                    select_timerelay.setVisibility(View.VISIBLE);
                    swich_btn1.setImageResource(R.mipmap.green_anjian);
                    isOpen1 = true;
                }
                break;
            case R.id.swich_btn2://接送车地址
                if (isOpen2) {
                    sendcar_lay.setVisibility(View.VISIBLE);
                    swich_btn2.setImageResource(R.mipmap.gray_anjian);
                    isOpen2 = false;
                } else {
                    sendcar_lay.setVisibility(View.GONE);
                    swich_btn2.setImageResource(R.mipmap.green_anjian);
                    isOpen2 = true;
                }
            case R.id.swich_btn3:
                if (isOpen3) {
                    swich_btn3.setImageResource(R.mipmap.icon_switch_default);
                    isOpen3 = false;
                    mJifenMoneyTv.setVisibility(View.GONE);
                } else {
                    swich_btn3.setImageResource(R.mipmap.icon_switch_selected);
                    isOpen3 = true;
                    mJifenMoneyTv.setVisibility(View.VISIBLE);
                }
                refreshTwoMoney();
                break;
            case R.id.swich_btn4:
                if (isOpen4) {
                    swich_btn4.setImageResource(R.mipmap.icon_switch_default);
                    isOpen4 = false;
                    card_tv.setVisibility(View.GONE);
                } else {
                    swich_btn4.setImageResource(R.mipmap.icon_switch_selected);
                    isOpen4 = true;
                    card_tv.setVisibility(View.VISIBLE);
                }
                refreshTwoMoney();
                break;
            case R.id.repairshop_relay:
                if (isSelectRepair) {
                    Intent intent = new Intent(mContext, RepairShopRvActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", (Serializable) mListShopBeans);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 103);
                }
                break;
            case R.id.invoice_relay://开发票
                String jcarName = mJiecheName.getText().toString();
                String jcarPhone = mJiechePhone.getText().toString();
                String jcarAddress = mAddressTv.getText().toString();
                if (mInvoiceBean == null) {
                    mInvoiceBean = new InvoiceBean();
                }
                mInvoiceBean.setName(jcarName);
                mInvoiceBean.setTelphone(jcarPhone);
                mInvoiceBean.setRecipientsAddr(jcarAddress);
                Intent intent1 = new Intent(mContext, InvoiceActivity.class);
                intent1.putExtra("data", mInvoiceBean);
                startActivityForResult(intent1, 104);
                break;
            case R.id.select_timerelay:
                dateDialog.setDate(getCurrentDate()).build().show();
                break;
            case R.id.jaddress_lay://接车地址
                AddressActivity.openAct(mActivity, JIECHE_CODE);
                break;
            case R.id.saddress_lay://送车地址
                AddressActivity.openAct(mActivity, SONGCHE_CODE);
                break;
            case R.id.sure_tv://提交订单
                String isAppoint;
                if (isOpen1) {
                    isAppoint = "1";
                    if (TextUtils.isEmpty(yuyuedate_tv.getText())) {
                        ToastUtils.Toast_short("请选择预约时间");
                        return;
                    }
                } else if (StringUtil.NoNullOrEmpty(orderId) && TextUtils.isEmpty(yuyuedate_tv.getText())) {
                    ToastUtils.Toast_short("请选择预约时间");
                    return;
                } else {
                    isAppoint = "0";
                }
                String isSame;
                if (isOpen2) {
                    isSame = "1";
                } else {
                    isSame = "0";
                }
                if (Tag.equals(Const.onekey)) {
                    if (!StringUtil.NoNullOrEmpty(mShopId)) {
                        ToastUtils.Toast_short("请选择维修商");
                        return;
                    }
                }
                mLoadingDialog.show();
                String realMoney = mAllMoney.getText().toString();

                if (Tag.equals(Const.onekey) && (orderId == null || orderId.isEmpty())) {
                    if (Const.isTest) {
                        mSureOrderPresenter.saveMaintainOrder(mCarId, isAppoint, yuyuedate_tv.getText().toString(), mJCAddressId, isSame, mSCAddressId, mShopId,
                                mTagetId, mMile, userCouponId, realMoney, mPayMOney, invoiceId, expectedDate, serviceType, isOpen4);
                    } else {
                        mSureOrderPresenter.saveMaintainOrder(mCarId, isAppoint, yuyuedate_tv.getText().toString(), mJCAddressId
                                , isSame, mSCAddressId, mShopId, mTagetId, mMile, userCouponId, realMoney, mPayMOney, invoiceId, expectedDate, serviceType, isOpen4);
                    }
                } else {
                    String type = "1";
                    if (Tag.equals(Const.free)) {
                        type = getIntent().getStringExtra("type");
                    }
                    String serviceJson = new Gson().toJson(serviceList);
                    Log.e("tag", "serviceJson=" + serviceJson);
                    if (Const.isTest) {
                        mSureOrderPresenter.commitOrder(mCarId, isAppoint, yuyuedate_tv.getText().toString(), mJCAddressId, isSame, mSCAddressId,
                                mShopId, userCouponId, realMoney, mPayMOney, invoiceId, expectedDate, serviceJson, type, orderId,
                                sideIds, mDjfNum, payDjf, score, deduMoney, Tag, isOpen4);
                    } else {
                        mSureOrderPresenter.commitOrder(mCarId, isAppoint, yuyuedate_tv.getText().toString(), mJCAddressId, isSame, mSCAddressId,
                                mShopId, userCouponId, realMoney, mPayMOney, invoiceId, expectedDate, serviceJson, type, orderId, sideIds, mDjfNum, payDjf, score,
                                deduMoney, Tag, isOpen4);
                    }
                }
                break;
            case R.id.youhuicard_relay://优惠券
                if (mListCouponList.size() > 0) {
                    UseCardActivity.openAct(mActivity, mListCouponList, 201);
                }
                break;
        }
    }

    private String invoiceId = "";
    private String userCouponId = "";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 103 && resultCode == 103 && data != null) {
            String shopName = data.getStringExtra("shopName");
            repairshop_tv.setText(shopName);
            mShopId = data.getStringExtra("repairShopId");
        }
        if (requestCode == 104 && resultCode == 104 && data != null) {
            mInvoiceId = data.getStringExtra("invoiceId");
            mInvoiceType = data.getStringExtra("invoiceType");
            mInvoiceBean = (InvoiceBean) data.getSerializableExtra("invoiceBean");
            Log.e("tag", "invoiceId=" + mInvoiceId);
            if (mInvoiceId != null) {
                this.invoiceId = mInvoiceId;
            }

            if (mInvoiceType.equals("1")) {
                invoiceType_tv.setText("电子普通发票");
            } else {
                invoiceType_tv.setText("增值税发票");
            }
        }
        if (requestCode == JIECHE_CODE && resultCode == AddressActivity.BACKCODE) {//接车地址
            AddressListResultBean addressListResultBean = (AddressListResultBean) data.getSerializableExtra("data");
            String recipientsName = addressListResultBean.getRecipientsName();
            String telephone = addressListResultBean.getTelephone();
            String province = addressListResultBean.getProvince();
            String city = addressListResultBean.getCity();
            String area = addressListResultBean.getArea();
            String detailAddr = addressListResultBean.getDetailAddr();
            String id = addressListResultBean.getId();

            if (isOpen2) {
                mJiecheName.setText(recipientsName + "");
                mSendName.setText(recipientsName + "");
                mJiechePhone.setText(telephone + "");
                mSendPhone.setText(telephone + "");
                mAddressTv.setText(province + city + area + detailAddr + "");
                mAddressTvs.setText(province + city + area + detailAddr + "");

                mJCAddressId = (id == null ? "" : id);
                mSCAddressId = (id == null ? "" : id);
            } else {
                mJiecheName.setText(recipientsName + "");
                mJiechePhone.setText(telephone + "");
                mAddressTv.setText(province + city + area + detailAddr + "");

                mJCAddressId = (id == null ? "" : id);

            }
            if (Tag.equals(Const.onekey)) {
                mSureOrderPresenter.changeRepairShopData(mCarId, mTagetId, mJCAddressId);
            }
            getDJF();
        } else if (requestCode == SONGCHE_CODE && resultCode == AddressActivity.BACKCODE) {//送车地址
            AddressListResultBean addressListResultBean = (AddressListResultBean) data.getSerializableExtra("data");
            String recipientsName = addressListResultBean.getRecipientsName();
            String telephone = addressListResultBean.getTelephone();
            String province = addressListResultBean.getProvince();
            String city = addressListResultBean.getCity();
            String area = addressListResultBean.getArea();
            String detailAddr = addressListResultBean.getDetailAddr();
            String id = addressListResultBean.getId();

            mSendName.setText(recipientsName + "");
            mSendPhone.setText(telephone + "");
            mAddressTvs.setText(province + city + area + detailAddr + "");
            mSCAddressId = (id == null ? "" : id);
            Log.e("tag", "mSCAddressId=" + mSCAddressId);
            getDJF();
        }

        if (requestCode == 201 && resultCode == 200) {
            ListCouponBean backData = (ListCouponBean) data.getSerializableExtra("backData");
            if (backData != null) {
                userCouponId = backData.getId();
                carsNum = backData.getAmount();
                if (StringUtil.NoNullOrEmpty(carsNum)) {
                    card_tv.setText("已优惠" + carsNum + "元");
                }
                refreshTwoMoney();
            }
        }
    }

    private void getDJF() {
        //0.1没有代驾费
        if (!Tag.equals(Const.onekey)) {
            if (StringUtil.NoNullOrEmpty(mJCAddressId) && StringUtil.NoNullOrEmpty(mSCAddressId) && StringUtil.NoNullOrEmpty(mShopId)) {
                mSureOrderPresenter.getChangeDjf(mJCAddressId, mSCAddressId, mShopId, djfType);
            }
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


    private List<ListCouponBean> mListCouponList = new ArrayList<>();

    //填充数据
    @Override
    public void getDataSuccess(ConfirmOrderResultBean results) {
        if (results != null) {
            score = results.getScore();
            String djf = results.getDjf();
            if (Tag.equals(Const.coinSpray) && Double.valueOf(mPayMOney) >= 500) {
                mDjfNum = "0";
            } else {
                if (carStatus != null && carStatus.equals("1")) {
                    double tempMoney = Double.valueOf(djf) + Double.valueOf(trailerMoney);
                    mDjfNum = String.valueOf(tempMoney);
                } else {
                    mDjfNum = djf;
                }
            }
            if (StringUtil.NoNullOrEmpty(mDjfNum)) {
                mDjfTv.setText("￥" + mDjfNum);
            }
            if (StringUtil.NoNullOrEmpty(score)) {
                mJifenNumTv.setText("共" + score + "积分");
                mJifenMoneyTv.setText("￥-" + Double.valueOf(score) / 100);
            }

            ConfirmOrderResultBean.UserCarBean userCar = results.getUserCar();
            if (userCar != null) {
                String carname = userCar.getCarname();
                String cardNo = userCar.getCardNo();
                mCarName.setText(carname + "");
                mCarNo.setText(cardNo + "");
            }
            ConfirmOrderResultBean.UserAddressBean userAddress = results.getUserAddress();
            if (userAddress != null) {
                String recipientsName = userAddress.getRecipientsName();
                String telephone = userAddress.getTelephone();
                String province = userAddress.getProvince();
                String city = userAddress.getCity();
                String area = userAddress.getArea();
                String detailAddr = userAddress.getDetailAddr();
                String id = userAddress.getId();

                mJiecheName.setText(recipientsName + "");
                mSendName.setText(recipientsName + "");
                mJiechePhone.setText(telephone + "");
                mSendPhone.setText(telephone + "");
                mAddressTv.setText(province + city + area + detailAddr + "");
                mAddressTvs.setText(province + city + area + detailAddr + "");

                mJCAddressId = (id == null ? "" : id);
                mSCAddressId = (id == null ? "" : id);

            }
            List<ListCouponBean> listCoupon = results.getListCoupon();
            if (listCoupon != null && listCoupon.size() > 0) {
                mListCouponList.clear();
                mListCouponList.addAll(listCoupon);
                ListCouponBean listCouponBean = listCoupon.get(0);
                carsNum = listCouponBean.getAmount();
                if (StringUtil.NoNullOrEmpty(carsNum)) {
                    card_tv.setText("已优惠" + carsNum + "元");
                }
                userCouponId = listCouponBean.getId();
            }

            if (!Tag.equals(Const.free)) {
                List<ConfirmOrderResultBean.ListShopBean> listShop = results.getListShop();
                if (listShop != null && listShop.size() > 0) {
                    String name = listShop.get(0).getName();
                    mShopId = listShop.get(0).getId();
                    repairshop_tv.setText(name + "");
                    if (mListShopBeans.size() != 0) {
                        mListShopBeans.clear();
                    }
                    mListShopBeans.addAll(listShop);
                }
            }

            refreshTwoMoney();
        }
    }

    //计算价格
    private void refreshTwoMoney() {
        double twoMoney = 0.00;
        if (StringUtil.NoNullOrEmpty(mDjfNum)) {
            twoMoney += Double.valueOf(mDjfNum);
        }
        if (StringUtil.NoNullOrEmpty(score) && isOpen3) {
            int anInt = Integer.parseInt(score);
            twoMoney -= Double.valueOf(anInt / 100);
            //实际支付代驾费
            payDjf = String.valueOf(Double.valueOf(mDjfNum) - Double.valueOf(anInt / 100));
            deduMoney = String.valueOf(Double.valueOf(anInt / 100));
        }

        if (StringUtil.NoNullOrEmpty(carsNum) && isOpen4) {
            twoMoney -= Double.valueOf(carsNum);
        }
        mHejiMoney2.setText("合计：¥" + Math.abs(twoMoney));
        if (StringUtil.NoNullOrEmpty(mPayMOney)) {
            double realMoney = Double.valueOf(mPayMOney) + twoMoney;
            if (realMoney < 0) {
                mAllMoney.setText("0");
            } else {
                mAllMoney.setText(realMoney + "");
            }
        }

    }

    /**
     * 获取送货时间
     *
     * @param time
     */
    private String expectedDate = "";

    @Override
    public void getSendTimeSuccess(String time) {
        expectedDate = time;
        switch (Tag) {
            case Const.onekey:
                mTime.setText("接送加保养平均耗时" + (time == null ? "0" : time) + "小时");
                break;
            case Const.free:
                mTime.setText("接送加保养平均耗时" + (time == null ? "0" : time) + "小时");
                break;
            case Const.weixiu:
                mTime.setText("接送加维修平均耗时" + (time == null ? "0" : time) + "小时");
                break;
            case Const.coinSpray:
                mTime.setText("接送加钣喷平均耗时" + (time == null ? "0" : time) + "小时");
                break;
            case Const.insurance:
                mTime.setText("接送加保险维修平均耗时" + (time == null ? "0" : time) + "小时");
                break;
        }
    }

    /**
     * 更换维修商成功
     *
     * @param listShopBeans
     */
    @Override
    public void chooseAddressChangeShopSuccess(List<ConfirmOrderResultBean.ListShopBean> listShopBeans) {
        if (listShopBeans != null && listShopBeans.size() > 0) {
            String name = listShopBeans.get(0).getName();
            mShopId = listShopBeans.get(0).getId();
            repairshop_tv.setText(name + "");
            if (mListShopBeans.size() != 0) {
                mListShopBeans.clear();
            }
            mListShopBeans.addAll(listShopBeans);
        }
    }

    /**
     * 提交订单成功的回调
     */
    @Override
    public void saveOrderSuccess(SaveOrderResultBean results) {
        String orderCode = results.getId();
        if (orderCode != null) {
            String realMoney = mAllMoney.getText().toString();
            if (StringUtil.NoNullOrEmpty(realMoney)) {
                Double aDouble = Double.valueOf(realMoney);
                if (aDouble <= 0) {//优惠券全额抵扣
                    mOrderPayPresenter.PayOrderData(orderCode, "3");
                } else {
                    OrderPayActivity.openAct(mContext, "order", orderCode, realMoney);
                }
                finish();
            }
        }
    }

    //获取代驾费的回调
    @Override
    public void showDJF(OnlyStringBean results) {
        String djf = results.getDjf();
        if (carStatus != null && carStatus.equals("1")) {
            double tempMoney = Double.valueOf(djf) + Double.valueOf(trailerMoney);
            mDjfNum = String.valueOf(tempMoney);
        } else {
            mDjfNum = djf;
        }
        mDjfTv.setText(StringUtil.checkNull(mDjfNum));
        refreshTwoMoney();
    }

    //确认自选订单、获取优惠券
    @Override
    public void showConfirmData(ConfirmOrderResultBean dataBean) {
        getDataSuccess(dataBean);
    }


    @Override
    public void onSuccess(String type, PayResultBean results) {
        OrderPaySuccessActivity.openAct(mContext);
    }
}
