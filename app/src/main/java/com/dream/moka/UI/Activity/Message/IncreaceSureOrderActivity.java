package com.dream.moka.UI.Activity.Message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.InvoiceBean;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Bean.Message.IncreaceBundle;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.Message.IncreaceOrderConfirmContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.Message.IncreaceConfirmPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.InvoiceActivity;
import com.dream.moka.UI.Activity.OrderPayActivity;
import com.dream.moka.UI.Activity.UseCardActivity;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

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
public class IncreaceSureOrderActivity extends BaseCommonActivity implements IncreaceOrderConfirmContract.View {

    @Bind(R.id.addTime_tv)
    TextView mAddTimeTv;
    @Bind(R.id.repairMoney_tv)
    TextView mRepairMoneyTv;
    @Bind(R.id.card_tv)
    TextView mCardTv;
    @Bind(R.id.next_iv)
    ImageView mNextIv;
    @Bind(R.id.card_relay)
    RelativeLayout mCardRelay;
    @Bind(R.id.invoice_tv)
    TextView mInvoiceTv;
    @Bind(R.id.next_iv1)
    ImageView mNextIv1;
    @Bind(R.id.invoice_relay)
    RelativeLayout mInvoiceRelay;
    @Bind(R.id.carNo_tv)
    TextView mCarNoTv;
    @Bind(R.id.car_name)
    TextView mCarName;
    @Bind(R.id.repairshop_nametv)
    TextView mRepairshopNametv;
    @Bind(R.id.xupay_tv)
    TextView mXupayTv;
    @Bind(R.id.threeMoney_tv)
    TextView mThreeMoneyTv;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.addTime_lay)
    LinearLayout mAddTimeLay;

    @Inject
    IncreaceConfirmPresenter mIncreaceConfirmPresenter;
    @Bind(R.id.swich_btn)
    ImageView mSwichBtn;

    private String expectedDate, userCouponId, invoiceId, orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_increace_commitorder;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mIncreaceConfirmPresenter.attachView(this);
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


    private List<ListCouponBean> mListCouponList = new ArrayList<>();
    private double orderMoney, cardMoney;
    private boolean isOpen;

    @Override
    public void initDatas() {
        IncreaceBundle dataBean = (IncreaceBundle) getIntent().getExtras().getSerializable("dataBean");
        if (dataBean != null) {
            orderId = dataBean.getOrderId();
            if (StringUtil.NoNullOrEmpty(dataBean.getAdd_time())) {
                mAddTimeTv.setText("预计增项订单时间：" + StringUtil.checkNull(dataBean.getAdd_time()) + "小时");
                expectedDate = dataBean.getAdd_time();
            } else {
                mAddTimeLay.setVisibility(View.GONE);
            }
            String orderAmont = dataBean.getOrderMoney();
            if (StringUtil.NoNullOrEmpty(orderAmont)) {
                mRepairMoneyTv.setText("￥" + StringUtil.checkNull(orderAmont));
            }
            mCarName.setText(StringUtil.checkNull(dataBean.getCarName()));
            mCarNoTv.setText(StringUtil.checkNull(dataBean.getCarNo()));
            mRepairshopNametv.setText(StringUtil.checkNull(dataBean.getRepairShopName()));

            List<ListCouponBean> listCoupon = dataBean.getListCoupon();
            if (listCoupon != null && listCoupon.size() > 0) {
                mListCouponList.clear();
                mListCouponList.addAll(listCoupon);
                ListCouponBean listCouponBean = listCoupon.get(0);
                String amount = listCouponBean.getAmount();
                if (StringUtil.NoNullOrEmpty(amount)) {
                    mSwichBtn.setVisibility(View.VISIBLE);
                    mCardTv.setText("已优惠" + amount + "元");
                    userCouponId = listCouponBean.getId();
                    cardMoney = Double.valueOf(amount);
                    isOpen = true;
                }
            }
            orderMoney = Double.valueOf(orderAmont);
            refreshMoney();
        }
    }

    @Override
    public void eventListener() {

    }


    @OnClick({R.id.card_relay, R.id.invoice_relay, R.id.back_relay, R.id.sure_tv, R.id.swich_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.card_relay:
                if (mListCouponList.size() > 0) {
                    UseCardActivity.openAct(mActivity, mListCouponList, 201);
                }
                break;
            case R.id.invoice_relay:
                Intent intent1 = new Intent(mContext, InvoiceActivity.class);
                intent1.putExtra("data", mInvoiceBean);
                startActivityForResult(intent1, 104);
                break;
            case R.id.sure_tv://提交订单
                if (StringUtil.NoNullOrEmpty(orderId)) {
                    Map<String, String> map = MapUtils.getSingleMap();
                    map.put("token", CommonAction.getToken());
                    if (StringUtil.NoNullOrEmpty(expectedDate)) {
                        map.put("expectedDate", expectedDate);
                    }
                    if (StringUtil.NoNullOrEmpty(userCouponId)&&isOpen) {
                        map.put("userCouponId", userCouponId);
                    }
                    if (StringUtil.NoNullOrEmpty(invoiceId)) {
                        map.put("invoiceId", invoiceId);
                    }
                    map.put("orderId", orderId);
                    String realMoney = mThreeMoneyTv.getText().toString();
                    if (Const.isTest) {
                        map.put("payAmount", realMoney);
                    } else {
                        map.put("payAmount", realMoney);
                    }
                    mLoadingDialog.show();
                    mIncreaceConfirmPresenter.confirmIncreaseOrder(map);
                } else {
                    ToastUtils.Toast_long("数据异常");
                }
                break;
            case R.id.swich_btn:
                if (isOpen) {
                    mSwichBtn.setImageResource(R.mipmap.gray_anjian);
                    mCardTv.setVisibility(View.GONE);
                    isOpen = false;
                } else {
                    mSwichBtn.setImageResource(R.mipmap.green_anjian);
                    mCardTv.setVisibility(View.VISIBLE);
                    isOpen = true;
                }
                refreshMoney();
                break;
        }
    }

    public void refreshMoney() {
        double realMoney = 0;
        if (isOpen) {
            realMoney = orderMoney - cardMoney;
            mCardTv.setText("已优惠"+cardMoney+"元");
        } else {
            realMoney = orderMoney;
        }
        if (realMoney < 0) {
            mThreeMoneyTv.setText("0");
        } else {
            mThreeMoneyTv.setText(String.valueOf(realMoney));
        }
    }

    @Override
    public void showData(EmptyBean dataBean) {
        //去支付
        String realMoney = mThreeMoneyTv.getText().toString();
        OrderPayActivity.openAct(mContext, "order", orderId, realMoney);
        finish();
    }

    private InvoiceBean mInvoiceBean;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 104 && resultCode == 104 && data != null) {
            mInvoiceBean = (InvoiceBean) data.getSerializableExtra("invoiceBean");
            if (mInvoiceBean != null) {
                invoiceId = mInvoiceBean.getId();
                String mInvoiceType = mInvoiceBean.getInvoiceType();
                if (mInvoiceType.equals("1")) {
                    mInvoiceTv.setText("电子普通发票");
                } else {
                    mInvoiceTv.setText("增值税发票");
                }
            }
        }

        if (requestCode == 201 && resultCode == 200) {
            ListCouponBean backData = (ListCouponBean) data.getSerializableExtra("backData");
            if (backData != null) {
                userCouponId = backData.getId();
                String carsNum = backData.getAmount();
                if (StringUtil.NoNullOrEmpty(carsNum)) {
                    mCardTv.setText("已优惠" + carsNum + "元");
                    cardMoney = Double.valueOf(carsNum);
                    refreshMoney();
                }
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

}
