package com.dream.moka.UI.Activity.drivercenter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.WithDrawDataBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.WithDrawalContract;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.WithDrawalPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class WithDrawalActivity extends BaseCommonActivity implements WithDrawalContract {

    @Inject
    WithDrawalPresenter mWithDrawalPresenter;
    @Bind(R.id.bankNo)
    TextView mBankNo;
    @Bind(R.id.left_tv)
    TextView mLeftTv;
    @Bind(R.id.withdmoney_edt)
    EditText mWithdmoneyEdt;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    @Bind(R.id.allWithDraw)
    TextView mAllWithDraw;
    private Dialog mLoadingDialog;
    private String mAccountId = "";
    private String mBankCardId = "";
    private String mWithdrawalsAmount;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, WithDrawalActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_driverwithdrawal;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {

    }

    @Override
    public void initView() {

        DaggerBaseComponent.builder()
                .appComponent(MyApplication.getInstance().getAppComponent())
//                .mainActivityModule(new MainActivityModule(this))
                .build()
                .inject(this);
        mWithDrawalPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);

    }

    @Override
    public String initTitleText() {
        return "提现";
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
        mLoadingDialog.show();
        mWithDrawalPresenter.getData();
    }

    @Override
    public void initDatas() {
        mLoadingDialog.show();
        mWithDrawalPresenter.getData();
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.sure_tv, R.id.back_relay, R.id.allWithDraw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sure_tv:
                if (TextUtils.isEmpty(mWithdmoneyEdt.getText())) {
                    ToastUtils.Toast_short("提现金额不能为空");
                    return;
                }
                String trim = mWithdmoneyEdt.getText().toString().trim();
                Double aDouble = Double.valueOf(trim);
                if (aDouble <= 0) {
                    ToastUtils.Toast_short("提现金额不能少于0元");
                    return;
                }
                if (mWithdrawalsAmount != null) {
                    Double valueOf = Double.valueOf(mWithdrawalsAmount);
                    if (aDouble > valueOf) {
                        ToastUtils.Toast_short("提现金额不能少于0元");
                        return;
                    }
                }
                mLoadingDialog.show();
                mWithDrawalPresenter.WithDraw(mAccountId, mBankCardId, trim, "0");
                break;
            case R.id.back_relay:
                finish();
                break;
            case R.id.allWithDraw:
                if (mWithdrawalsAmount != null) {
                    mWithdmoneyEdt.setText(mWithdrawalsAmount);
                    mWithdmoneyEdt.setSelection(mWithdrawalsAmount.length());
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
    public void getWithDrawData(WithDrawDataBean results) {
        if (results != null) {
            String bankNo = results.getBankNo();
            if (bankNo != null) {
                String substring = bankNo.substring(bankNo.length() - 4, bankNo.length());
                mBankNo.setText("**** **** **** " + substring);
            }
            mWithdrawalsAmount = results.getWithdrawalsAmount();
            if (StringUtil.NoNullOrEmpty(mWithdrawalsAmount)){
                mWithdmoneyEdt.setHint("可提现金额为" + mWithdrawalsAmount + "元");
            }else {
                mWithdmoneyEdt.setHint("可提现金额为0.00元");
            }
            mAccountId = results.getAccountId();
            mBankCardId = results.getBankCardId();

        }
    }

    @Override
    public void withDrawSuccess() {
        AuditActivity.openAct(mContext, "withDraw", "");
        finish();
    }

}
