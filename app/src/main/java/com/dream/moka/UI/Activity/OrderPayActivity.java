package com.dream.moka.UI.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.EventBusBean.WXPaySuccessBean;
import com.dream.moka.Bean.IntegralBean;
import com.dream.moka.Bean.MyAccountBean;
import com.dream.moka.Bean.PayResultBean;
import com.dream.moka.Bean.WalletBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.MineContract;
import com.dream.moka.Contract.OrderPayContract;
import com.dream.moka.Contract.PurseJifenContract;
import com.dream.moka.Contract.SettingContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Presenter.OrderPayPresenter;
import com.dream.moka.Presenter.PurseJifenPresenter;
import com.dream.moka.Presenter.SettingPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.set.SetIDcardActivity;
import com.dream.moka.UI.order.OrderPaySuccessActivity;
import com.dream.moka.Utils.Dialog.PayPwdDialog;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.WechatPayUtils;
import com.dream.moka.Utils.alipay.AliPayUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class OrderPayActivity extends BaseCommonActivity implements OrderPayContract,PurseJifenContract,SettingContract {

    @Inject
    OrderPayPresenter mOrderPayPresenter;

    @Inject
    PurseJifenPresenter mPurseJifenPresenter;

    @Inject
    SettingPresenter mSettingPresenter;

    @Bind(R.id.alipay_select)
    ImageView alipay_select;
    @Bind(R.id.wechat_select)
    ImageView wechat_select;
    @Bind(R.id.yue_select)
    ImageView yue_select;
    @Bind(R.id.ordermoney_tv)
    TextView mOrdermoneyTv;
    @Bind(R.id.yue_iv)
    ImageView mYueIv;
    @Bind(R.id.yue_relay)
    RelativeLayout mYueRelay;
    @Bind(R.id.alipay_iv)
    ImageView mAlipayIv;
    @Bind(R.id.alipay_relay)
    RelativeLayout mAlipayRelay;
    @Bind(R.id.wechat_iv)
    ImageView mWechatIv;
    @Bind(R.id.wechatpay_relay)
    RelativeLayout mWechatpayRelay;
    @Bind(R.id.sure_pay)
    TextView mSurePay;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.yue)
    TextView mYue;
    private String mPayType = "0";
    private String mOrderId;
    private String mMoney;
    private String mFrom;

    public static void openAct(Context context, String from, String orderId, String money) {
        Intent intent = new Intent(context, OrderPayActivity.class);
        intent.putExtra("from", from);
        intent.putExtra("orderId", orderId);
        intent.putExtra("money", money);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_orderpay;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mOrderPayPresenter.attachView(this);
        mPurseJifenPresenter.attachView(this);
        mSettingPresenter.attachView(this);
        mOrderId = getIntent().getStringExtra("orderId");
        mMoney = getIntent().getStringExtra("money");
        mFrom = getIntent().getStringExtra("from");
        mOrdermoneyTv.setText("¥" + mMoney);

        String userBalance = CommonAction.getUserBalance();
        mYue.setText("¥" + userBalance);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(WXPaySuccessBean closeConformOrder) {
        ToastUtils.Toast_short(" 支付成功");
        if (mFrom.equals("again")) {
            finish();
        } else {
            OrderPaySuccessActivity.openAct(mContext);
        }

    }
    @Subscribe
    public void onEventMain(String tempStr) {
        if (tempStr.equals("setPwd")) {
            SetIDcardActivity.openAct(mContext, "0", "");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public String initTitleText() {
        return "订单支付";
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
        mPurseJifenPresenter.getUserAccountData();
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.yue_relay, R.id.alipay_relay, R.id.wechatpay_relay, R.id.sure_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                backTiXing();
                break;
            case R.id.yue_relay:
                yue_select.setVisibility(View.VISIBLE);
                wechat_select.setVisibility(View.GONE);
                alipay_select.setVisibility(View.GONE);
                mPayType = "2";
                break;
            case R.id.alipay_relay:
                yue_select.setVisibility(View.GONE);
                wechat_select.setVisibility(View.GONE);
                alipay_select.setVisibility(View.VISIBLE);
                mPayType = "0";
                break;
            case R.id.wechatpay_relay:
                yue_select.setVisibility(View.GONE);
                wechat_select.setVisibility(View.VISIBLE);
                alipay_select.setVisibility(View.GONE);
                mPayType = "1";
                break;
            case R.id.sure_pay:
                if (mPayType.equals("2")) {//余额支付
                    if (StringUtil.NoNullOrEmpty(balance)){
                        Double mBalance = Double.valueOf(balance);
                        Double orderMoney = Double.valueOf(mMoney);
                        if (mBalance<orderMoney){
                            ToastUtils.Toast_long("余额不足");
                        }else {
                            mSettingPresenter.checkHasPayCode();
                        }
                    }else {
                        ToastUtils.Toast_long("暂无余额");
                    }
                } else {
                    mLoadingDialog.show();
                    mOrderPayPresenter.PayOrderData(mOrderId, mPayType);
                }
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

    @Override
    public void onSuccess(String type, PayResultBean results) {
        if (type.equals("0")) {//zhi
            String alipayInfo = results.getAlipayInfo();
            new AliPayUtils() {
                @Override
                public void paySuccess() {
                    OrderPaySuccessActivity.openAct(mContext);
                    finish();
                }
            }.alPay(alipayInfo, mActivity);
        } else if (type.equals("1")) {//wei
            PayResultBean.WxpayInfoBean wxpayInfo = results.getWxpayInfo();
            String appid = wxpayInfo.getAppid();
            String mch_id = wxpayInfo.getMch_id();
            String nonce_str = wxpayInfo.getNonce_str();
            String prepay_id = wxpayInfo.getPrepay_id();
            String sign = wxpayInfo.getSign();
            String timestamp = wxpayInfo.getTimestamp();
            new WechatPayUtils().toWechatPay(appid, mch_id, nonce_str, prepay_id, sign, timestamp);
        }else {//余额支付成功
            ToastUtils.Toast_long("支付成功");
            OrderPaySuccessActivity.openAct(mContext);
            finish();
        }
    }


    @Override
    public void onBackPressed() {
        backTiXing();
    }

    public void backTiXing() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("温馨提示")
                .setMessage("确认取消支付？")
                .setCancelable(true)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setNegativeButton("", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
    }

    private String balance;
    @Override
    public void getAccount(WalletBean walletBean) {
        balance = walletBean.getBalance();
        if (StringUtil.NoNullOrEmpty(balance)){
            mYue.setText("￥"+balance);
        }
    }

    @Override
    public void getQianBaoData(MyAccountBean results, boolean isMore) {

    }

    @Override
    public void isLoadAll(boolean isAll) {

    }

    @Override
    public void showScoreList(List<IntegralBean> dataList) {

    }

    @Override
    public void onSuccess(boolean isSet) {
        if (isSet) {
            new PayPwdDialog(mActivity) {
                @Override
                public void onInputCompleteResult(String password) {
                    mLoadingDialog.show();
                    mOrderPayPresenter.PayOrderData(mOrderId, mPayType,password);
                }
            };
        } else {
            SetIDcardActivity.openAct(mContext, "0", "");
        }
    }

    @Override
    public void outLoginSuccess() {

    }
}
