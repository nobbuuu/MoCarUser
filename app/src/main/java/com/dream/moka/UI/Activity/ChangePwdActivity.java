package com.dream.moka.UI.Activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.ChangePwdContract;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.ChangePwdPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.set.ChangephoneActivity;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.ToastUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class ChangePwdActivity extends BaseCommonActivity implements ChangePwdContract {

    @Inject
    ChangePwdPresenter mChangePwdPresenter;

    @Bind(R.id.oldPwd)
    EditText mOldPwd;
    @Bind(R.id.newPwd)
    EditText mNewPwd;
    @Bind(R.id.againPwd)
    EditText mAgainPwd;
    @Bind(R.id.line_oldpwd)
    View mLineOldpwd;
    @Bind(R.id.line_newpwd)
    View mLineNewpwd;
    @Bind(R.id.line_pwdagain)
    View mLinePwdagain;
    @Bind(R.id.sure_tv)
    TextView mSureTv;

    @Override
    public int getLayoutId() {
        return R.layout.activity_changepwd;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mChangePwdPresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        return "修改密码";
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
        mOldPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mLineOldpwd.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mLineOldpwd.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexiand9));
                }
            }
        });
        mNewPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mLineNewpwd.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mLineNewpwd.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexiand9));
                }
            }
        });
        mAgainPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mLinePwdagain.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mLinePwdagain.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexiand9));
                }
            }
        });
    }

    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void onSuccess() {
        ToastUtils.Toast_short("修改成功");
        finish();
    }

    @OnClick({R.id.sure_tv,R.id.forgotpwd_tv})
    public void onViewClicked(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.sure_tv:
                if (checkPwd()){
                    mLoadingDialog.show();
                    mChangePwdPresenter.ChangePwdData(mOldPwd.getText().toString(), mAgainPwd.getText().toString());
                }
                break;
            case R.id.forgotpwd_tv:
                Intent intent1 = new Intent(mContext, ChangephoneActivity.class);
                intent1.putExtra(Const.intentTag, "pwd");
                startActivity(intent1);
                break;
        }
    }

    private boolean checkPwd(){
        if (TextUtils.isEmpty(mOldPwd.getText().toString())) {
            ToastUtils.Toast_short("请输入旧密码");
            return false;
        } else if (mOldPwd.getText().toString().length() < 6 || mOldPwd.getText().toString().length() > 12) {
            ToastUtils.Toast_short("请输入6~12位密码");
            return false;
        }
        if (TextUtils.isEmpty(mNewPwd.getText().toString())) {
            ToastUtils.Toast_short("请输入新密码");
            return false;
        }else if (mNewPwd.getText().toString().length() < 6 || mNewPwd.getText().toString().length() > 12) {
            ToastUtils.Toast_short("请输入6~12位密码");
            return false;
        }
        if (TextUtils.isEmpty(mAgainPwd.getText().toString())) {
            ToastUtils.Toast_short("请再次确认密码");
            return false;
        }else if (mAgainPwd.getText().toString().length() < 6 || mAgainPwd.getText().toString().length() > 12) {
            ToastUtils.Toast_short("请输入6~12位密码");
            return false;
        }

        if (!mNewPwd.getText().toString().equals(mAgainPwd.getText().toString())) {
            ToastUtils.Toast_short("两次输入密码不一致");
            return false;
        }

        return  true;
    }
}
