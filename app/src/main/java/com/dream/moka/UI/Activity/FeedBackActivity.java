package com.dream.moka.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Component.DaggerBaseComponent;
import com.dream.moka.Contract.FeedBackContract;
import com.dream.moka.Other.MyApplication;
import com.dream.moka.Presenter.FeedBackPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class FeedBackActivity extends BaseCommonActivity implements FeedBackContract {

    @Inject
    FeedBackPresenter mFeedBackPresenter;
    @Bind(R.id.feedback_edt)
    EditText mFeedbackEdt;
    @Bind(R.id.feedback_relay)
    RelativeLayout mFeedbackRelay;
    @Bind(R.id.et_name)
    EditText mEtName;
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.btn)
    TextView mBtn;

    public static void openAct(Context context) {
        context.startActivity(new Intent(context, FeedBackActivity.class));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
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
        mFeedBackPresenter.attachView(this);

    }

    @Override
    public String initTitleText() {
        return "意见反馈";
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

    }

    @Override
    public void complete() {

    }

    @Override
    public void onSuccess() {
        ToastUtils.Toast_short("提交成功");
        finish();
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
        if (TextUtils.isEmpty(mFeedbackEdt.getText())) {
            ToastUtils.Toast_short("请填写反馈意见");
            return;
        }
        if (TextUtils.isEmpty(mEtName.getText())) {
            ToastUtils.Toast_short("请填写姓名");
            return;
        }
        if (TextUtils.isEmpty(mEtPhone.getText())) {
            ToastUtils.Toast_short("请填写电话");
            return;
        }
        mFeedBackPresenter.CommitData(mFeedbackEdt.getText().toString(), mEtName.getText().toString(), mEtPhone.getText().toString());
    }
}
