package com.dream.moka.UI.Activity.set;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.BankInfoBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.PayPwdContract;
import com.dream.moka.CumstomView.PayPsdInputView;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.PayPwdPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.drivercenter.AddBankCardActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class PayPwdActivity extends BaseCommonActivity implements PayPwdContract {
    @Inject
    PayPwdPresenter mPayPwdPresenter;
    @Bind(R.id.tip_tv)
    TextView tip_tv;
    @Bind(R.id.sure_tv)
    TextView sure_tv;
    @Bind(R.id.forgotpwd_tv)
    TextView forgotpwd_tv;
    @Bind(R.id.contac_service)
    TextView contac_service;
    @Bind(R.id.paypwd_view)
    PayPsdInputView mPaypwdView;

    private String intentTag = "";
    private Dialog mLoadingDialog;
    private String mPasswordString = "";
    private String mStringIDCard;
    private String mFromTag;

    public static void openAct(Context context, String tag, String idCard, String from) {//idcard用于初次设置密码时使用
        Intent intent = new Intent(context, PayPwdActivity.class);
        intent.putExtra(Const.intentTag, tag);
        intent.putExtra("idCard", idCard);
        intent.putExtra("from", from);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_paypassword;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mPayPwdPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
    }

    @Override
    public String initTitleText() {
        intentTag = getIntent().getStringExtra(Const.intentTag);
        mFromTag = getIntent().getStringExtra("from");
        if (intentTag.equals("change")||intentTag.equals("sure")) {
            tip_tv.setText("请输入当前交易密码，验证身份");
            sure_tv.setText("下一步");
            forgotpwd_tv.setVisibility(View.VISIBLE);
            contac_service.setVisibility(View.VISIBLE);
            return "确认交易密码";
        } else if (intentTag.equals("set")) {
            mStringIDCard = getIntent().getStringExtra("idCard");
            tip_tv.setText("请设置交易密码");
            sure_tv.setText("确定");
            forgotpwd_tv.setVisibility(View.GONE);
            contac_service.setVisibility(View.VISIBLE);
            return "设置交易密码";
        } else {
            tip_tv.setText("请输入新的支付密码");
            sure_tv.setText("确定");
            forgotpwd_tv.setVisibility(View.GONE);
            contac_service.setVisibility(View.GONE);
            return "修改交易密码";
        }
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

    }

    @Override
    public void eventListener() {
        mPaypwdView.setInputCompleteListener(new PayPsdInputView.inputCompleteListener() {
            @Override
            public void onInputComplete(String pwd) {
                mPasswordString = mPaypwdView.getPasswordString();
            }
        });
    }

    @OnClick({R.id.back_relay, R.id.sure_tv,R.id.contac_service,R.id.forgotpwd_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.sure_tv:
                if (mPasswordString.length() != 6) {
                    ToastUtils.Toast_short("支付密码格式有误");
                    return;
                }
                mLoadingDialog.show();
                if (intentTag.equals("change")||intentTag.equals("sure")) {//修改的第一步
                    mPayPwdPresenter.CheckPayCode(mPasswordString);
                } else if (intentTag.equals("set")) {//第一次设置交易密码
                    mPayPwdPresenter.setPayCode(mStringIDCard, mPasswordString);
                } else {//修改的第二步
                    mPayPwdPresenter.setNewPayCode(mPasswordString);
                }
                break;
            case R.id.contac_service:
                IntentUtils.call_Dall(CommonAction.getPlatPhone(),mActivity);
                break;
            case R.id.forgotpwd_tv:
                SetIDcardActivity.openAct(mContext,"0","forget");
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
    public void setPayCodeSuccess() {
        switch (mFromTag) {
            case "0":
                ToastUtils.Toast_short("支付密码设置成功");
                finish();
                break;
            case "1":
                AddBankCardActivity.openAct(mContext,"add",new BankInfoBean());
                finish();
                break;
            default:
                ToastUtils.Toast_short("支付密码设置成功");
                finish();
                break;
        }

    }

    /**
     * 修改密码第一步，验证原密码
     */
    @Override
    public void CheckOldCodeSuccess() {
        if (intentTag!=null&&intentTag.equals("sure")){
            setResult(101);
        }else {
            PayPwdActivity.openAct(mContext, "", "", "");
        }
        finish();
    }

    @Override
    public void changePayCodeSuccess() {
        ToastUtils.Toast_short("支付密码修改成功");
        finish();
    }
}
