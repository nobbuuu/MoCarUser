package com.dream.moka.UI.Activity.set;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.SettingContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.SettingPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.AboutUsActivity;
import com.dream.moka.UI.Activity.AddTicketsZZActivity;
import com.dream.moka.UI.Activity.ChangePwdActivity;
import com.dream.moka.UI.Activity.FeedBackActivity;
import com.dream.moka.UI.Activity.VersionUpdateActivity;
import com.dream.moka.UI.Activity.address.AddressActivity;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.IntentUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class SetActivity extends BaseCommonActivity implements SettingContract {

    @Inject
    SettingPresenter mSettingPresenter;
    @Bind(R.id.address_relay)
    RelativeLayout mAddressRelay;
    @Bind(R.id.addticket_relay)
    RelativeLayout mAddticketRelay;
    @Bind(R.id.change_phone)
    RelativeLayout mChangePhone;
    @Bind(R.id.change_pwd)
    RelativeLayout mChangePwd;
    @Bind(R.id.change_paypwd)
    RelativeLayout mChangePaypwd;
    @Bind(R.id.protocol_relay)
    RelativeLayout mProtocolRelay;
    @Bind(R.id.aboutus_relay)
    RelativeLayout mAboutusRelay;
    @Bind(R.id.feedback_relay)
    RelativeLayout mFeedbackRelay;
    @Bind(R.id.check_version)
    RelativeLayout mCheckVersion;
    @Bind(R.id.out_login)
    TextView mOutLogin;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;

    @Override
    public int getLayoutId() {
        return R.layout.activity_set;
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
        mSettingPresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "设置";
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
//        DialogUtils.initLoadingDialog(mActivity).show();
    }

    @Override
    public void eventListener() {

    }

    @OnClick({R.id.back_relay, R.id.address_relay, R.id.addticket_relay, R.id.change_phone, R.id.change_pwd,
            R.id.change_paypwd, R.id.protocol_relay, R.id.feedback_relay, R.id.check_version,
            R.id.aboutus_relay, R.id.out_login})
    public void onClick(View view) {
        boolean isLogin = CommonAction.getIsLogin();
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.address_relay://地址
                if (isLogin) {
                    AddressActivity.openAct(mActivity, 10);
                } else {
                    DialogUtils.getLoginTip(mActivity).show();
                }
                break;
            case R.id.addticket_relay://赠票资质
                if (isLogin) {
                    IntentUtils.toActivity(AddTicketsZZActivity.class, this);
                } else {
                    DialogUtils.getLoginTip(mActivity).show();
                }

                break;
            case R.id.change_phone://修改手机号
                if (isLogin) {
                    IntentUtils.toActivityWithTag(ChangephoneActivity.class, this, "phone");
                } else {
                    DialogUtils.getLoginTip(mActivity).show();
                }

                break;
            case R.id.change_pwd://修改密码
                if (isLogin) {
                    IntentUtils.toActivity(ChangePwdActivity.class, this);
                } else {
                    DialogUtils.getLoginTip(mActivity).show();
                }

                break;
            case R.id.change_paypwd://交易密码
                if (isLogin) {
                    mSettingPresenter.checkHasPayCode();
                } else {
                    DialogUtils.getLoginTip(mActivity).show();
                }
                break;
            case R.id.protocol_relay:
                UserXieYiActivity.openAct(mContext, "user");
                break;
            case R.id.aboutus_relay:
                IntentUtils.toActivity(AboutUsActivity.class, this);
                break;
            case R.id.feedback_relay:
                IntentUtils.toActivity(FeedBackActivity.class, this);
                break;
            case R.id.check_version:
                IntentUtils.toActivity(VersionUpdateActivity.class, this);
                break;
            case R.id.out_login:
                mLoadingDialog.show();
                mSettingPresenter.checkOut();
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

    /**
     * 是否设置过支付密码
     *
     * @param isSet
     */
    @Override
    public void onSuccess(boolean isSet) {
        if (isSet) {
            PayPwdActivity.openAct(mContext, "change", "", "0");
        } else {
            SetIDcardActivity.openAct(mContext, "0", "");
        }

    }

    @Override
    public void outLoginSuccess() {
        CommonAction.clearUserData();
        finish();
    }
}
