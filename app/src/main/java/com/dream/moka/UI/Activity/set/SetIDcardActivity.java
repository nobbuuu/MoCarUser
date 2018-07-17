package com.dream.moka.UI.Activity.set;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.SetIDcardContract;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.SetIDcardPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

public class SetIDcardActivity extends BaseCommonActivity implements SetIDcardContract {

    @Inject
    SetIDcardPresenter mSetIDcardPresenter;
    @Bind(R.id.icCard)
    EditText mIcCard;
    @Bind(R.id.btn)
    TextView mBtn;
    private String mIdcardString;
    private Dialog mLoadingDialog;
    private String mFromType;
    private String mForget;

    public static void openAct(Context context,String fromType,String forget) {
        Intent intent = new Intent(context, SetIDcardActivity.class);
        intent.putExtra("fromType",fromType);
        intent.putExtra("forget",forget);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_idcard;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mSetIDcardPresenter.attachView(this);
        mFromType = getIntent().getStringExtra("fromType");
        mForget = getIntent().getStringExtra("forget");
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
    }

    @Override
    public String initTitleText() {
        return "设置交易密码";
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

    }

    @Override
    public void showError() {
        if (mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void complete() {
        if (mLoadingDialog!=null){
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void setIdcardSuccess() {
        if (mForget.equals("forget")){
            PayPwdActivity.openAct(mContext, "", "", "");
        }else {
            PayPwdActivity.openAct(mContext,"set",mIdcardString,mFromType);
        }
        finish();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        if (TextUtils.isEmpty(mIcCard.getText())){
            ToastUtils.Toast_short("请输入身份证号码");
            return;
        }
        if (mIcCard.getText().toString().trim().length()!=18){
            ToastUtils.Toast_short("身份证号码错误");
            return;
        }
        mLoadingDialog.show();
        mIdcardString = mIcCard.getText().toString().trim();
        if (mForget.equals("forget")){
            mSetIDcardPresenter.forgetAndCheckIdCard(mIdcardString);
        }else {
            mSetIDcardPresenter.setIdCardData(mIdcardString,mFromType);
        }

    }
}
