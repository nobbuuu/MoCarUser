package com.dream.moka.UI.Activity.login_register;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.ResetPwdContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.ResetPwdPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.MyUtils;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class ResetPwdActivity extends BaseCommonActivity implements ResetPwdContract {

    @Inject
    ResetPwdPresenter mResetPwdPresenter;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.eyes)
    CheckBox mEyes;
    @Bind(R.id.sure_tv)
    TextView mSureTv;
    private Dialog mLoadingDialog;

    public static void openAct(Context context, String phone,String tag) {
        Intent intent = new Intent(context, ResetPwdActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_resetpwd;
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
        mResetPwdPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        MyUtils.initEye(mPwd,mEyes);
    }

    @Override
    public String initTitleText() {
        return "忘记密码";
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

    @OnClick({R.id.back_relay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
        }
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
    public void setPwdSuccess() {
        String intentTag = getIntent().getStringExtra(Const.intentTag);
        if (intentTag!=null&&intentTag.equals("login")){
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }else {
            ToastUtils.Toast_short("重置成功!");
        }
    }

    @OnClick(R.id.sure_tv)
    public void onViewClicked() {
        if(TextUtils.isEmpty(mPwd.getText())){
            ToastUtils.Toast_short("密码不能为空");
            return;
        }
        if (mPwd.length()>=6&&mPwd.length()<=16){
            mLoadingDialog.show();
            String phone = getIntent().getStringExtra("phone");
            mResetPwdPresenter.saveData(phone,mPwd.getText().toString().trim());
        }else {
            ToastUtils.Toast_short("密码长度为6-16位");
        }

    }
}
