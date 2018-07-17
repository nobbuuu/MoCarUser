package com.dream.moka.UI.Activity.drivercenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.DriverAccountResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.DriverMineContract;
import com.dream.moka.Contract.DriverPurseContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.DriverMinePresenter;
import com.dream.moka.Presenter.DriverPursePresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.StringUtil;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class DriverPurseActivity extends BaseCommonActivity implements DriverPurseContract,DriverMineContract {

    @Inject
    DriverPursePresenter mDriverPursePresenter;
    @Inject
    DriverMinePresenter mDriverMinePresenter;

    @Bind(R.id.ktxMoney)
    TextView mKtxMoney;
    @Bind(R.id.ytxMoney)
    TextView mYtxMoney;
    @Bind(R.id.jyzMoney)
    TextView mJyzMoney;
    @Bind(R.id.bankcard_relay)
    RelativeLayout mBankcardRelay;
    @Bind(R.id.withdrawal_relay)
    RelativeLayout mWithdrawalRelay;
    @Bind(R.id.see_tv)
    TextView mSeeTv;
    @Bind(R.id.tishi)
    TextView mTishi;
    private Dialog mLoadingDialog;
    private String mDriverStatus;


    public static void openAct(Context context) {
        context.startActivity(new Intent(context, DriverPurseActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_driverpurse;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mDriverPursePresenter.attachView(this);
        mDriverMinePresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
    }

    @Override
    public String initTitleText() {
        return "我的钱包";
    }

    @Override
    public String initRightTv() {
        return "钱包明细";
    }

    @Override
    public boolean isRighttv() {
        return true;
    }

    @Override
    public void loadResum() {
        mLoadingDialog.show();
        mDriverPursePresenter.getAccountData();
        mDriverMinePresenter.getDriverInfo();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.right_tv, R.id.bankcard_relay, R.id.withdrawal_relay, R.id.see_tv, R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bankcard_relay://我的银行卡
                ChangeBankCardActivity.openAct(mActivity, false);
                break;
            case R.id.right_tv://钱包
                DriverPurseDetaileActivity.openAct(mActivity);
                break;
            case R.id.withdrawal_relay://提现
                WithDrawalActivity.openAct(mContext);
                break;
            case R.id.see_tv://查看
                if (StringUtil.NoNullOrEmpty(mDriverStatus)){
                    if (mDriverStatus.equals("1")) {
                        DriverEquityActivity.openAct(mContext);
                    } else if (mDriverStatus.equals("2")) {
                        RechargeActivity.openAct(mContext,"");
                    }
                }
                break;
            case R.id.back_relay:
                finish();
                break;
        }
    }

    @Override
    public void showError() {

    }

    @Override
    public void complete() {
        if (mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void getAccountSuccess(DriverAccountResultBean resultBean) {
        String withdrawalsAmount = resultBean.getWithdrawalsAmount();
        mKtxMoney.setText(StringUtil.NoNullOrEmpty(withdrawalsAmount) ? withdrawalsAmount : "0");
        String drawAmount = resultBean.getDrawAmount();
        mYtxMoney.setText(StringUtil.NoNullOrEmpty(drawAmount) ? drawAmount : "0");
        String transAmount = resultBean.getTransAmount();
        mJyzMoney.setText(StringUtil.NoNullOrEmpty(transAmount) ? transAmount : "0");

    }

    @Override
    public void setHomeModeSuccess(String homeMode) {
        mDriverStatus = CommonAction.getDriverStatus();
        if (mDriverStatus.equals("1")) {
            mTishi.setText("已缴纳押金，可享有司机各种权益");
            mSeeTv.setText("查看");
        } else if (mDriverStatus.equals("2")) {
            mTishi.setText("缴纳押金，可享有司机各种权益");
            mSeeTv.setText("缴纳押金");
        }
    }

    @Override
    public void saveddrseeSuccess(String lat, String lng, String Address) {

    }
}
