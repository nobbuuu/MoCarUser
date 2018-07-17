package com.dream.moka.UI.Activity.login_register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.SetPwdContract;
import com.dream.moka.IM.TLSService;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.SetPwdPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.set.UserXieYiActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.MapUtils;
import com.dream.moka.Utils.MyUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.ToastUtils;

import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class SetPwdActivity extends BaseActivity implements SetPwdContract {

    @Inject
    SetPwdPresenter mSetPwdPresenter;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.yzm_lay)
    LinearLayout mYzmLay;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.password_number)
    EditText mPasswordNumber;
    @Bind(R.id.btn)
    TextView mBtn;
    @Bind(R.id.xieyi)
    TextView mXieyi;
    @Bind(R.id.eyes)
    CheckBox mEyes;
    @Bind(R.id.pwd_relay)
    RelativeLayout mPwdRelay;
    @Bind(R.id.nickname_edt)
    EditText mNicknameEdt;
    @Bind(R.id.nick_name_relay)
    RelativeLayout mNickNameRelay;
    @Bind(R.id.nickName_lay)
    LinearLayout mNickNameLay;
    private String mPhoneString;

    public static void openAct(Context context, String phone) {
        Intent intent = new Intent(context, SetPwdActivity.class);
        intent.putExtra("phone", phone);
        context.startActivity(intent);
    }

    public static void openAct(Context context, String phone, String openId, String type, String name, String headUrl) {
        Intent intent = new Intent(context, SetPwdActivity.class);
        intent.putExtra("phone", phone);
        intent.putExtra("openId", openId);
        intent.putExtra("type", type);
        intent.putExtra("name", name);
        intent.putExtra("headUrl", headUrl);
        intent.putExtra(Const.intentTag, "bindPhone");
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_setpwd;
    }

    @Override
    public void initView() {
        AppManager.getAppManager().addActivity(mActivity);
        mSetPwdPresenter.attachView(this);
        mPhoneString = getIntent().getStringExtra("phone");
        MyUtils.initEye(mPasswordNumber, mEyes);

        String tag = getIntent().getStringExtra(Const.intentTag);
        if (tag!=null){
            mNickNameLay.setVisibility(View.GONE);
            mNickNameRelay.setVisibility(View.GONE);
        }
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void eventListener() {
        mPasswordNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mPwdRelay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mPwdRelay.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexian1));
                }
            }
        });
        mNicknameEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mNickNameRelay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mNickNameRelay.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexian1));
                }
            }
        });
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
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
    public void registerSuccess(UserResultBean results) {
//        EventBus.getDefault().post(new RefreshLoginStatus());
        AppManager.getAppManager().finishSomeActivity();
    }


    @OnClick({R.id.back_relay, R.id.btn, R.id.xieyi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.btn:
                if (TextUtils.isEmpty(mPasswordNumber.getText())) {
                    ToastUtils.Toast_short("请输入密码");
                    return;
                }
                String pwd = mPasswordNumber.getText().toString();
                if (pwd.length() < 6 || pwd.length() > 16) {
                    ToastUtils.Toast_short("请输入6到16位密码");
                    return;
                }
                String tag = getIntent().getStringExtra(Const.intentTag);
                if (tag==null){
                    String nickName = mNicknameEdt.getText().toString();
                    if (nickName.isEmpty()) {
                        ToastUtils.Toast_short("请输入昵称");
                        return;
                    } else if (nickName.length() > 8) {
                        ToastUtils.Toast_short("字数过多");
                        return;
                    }
                    mSetPwdPresenter.registerData(mPhoneString, pwd, nickName);
                }else {
                    Map<String, String> map = MapUtils.getSingleMap();
                    String phone = getIntent().getStringExtra("phone");
                    String openId = getIntent().getStringExtra("openId");
                    String type = getIntent().getStringExtra("type");
                    String name = getIntent().getStringExtra("name");
                    String headUrl = getIntent().getStringExtra("headUrl");
                    map.put("phone",phone);
                    map.put("openId",openId);
                    map.put("type",type);
                    map.put("nickName",name);
                    map.put("passWord",pwd);
                    map.put("photo",headUrl);
                    map.put("xgType","1");
                    mLoadingDialog.show();
                    mSetPwdPresenter.loginByOthersBindPhone(map);
                }

                break;
            case R.id.xieyi:
                UserXieYiActivity.openAct(mContext, "user");
                break;
        }
    }


    class StrAccRegListener implements TLSStrAccRegListener {
        @Override
        public void OnStrAccRegSuccess(TLSUserInfo userInfo) {
            ToastUtils.Toast_long("TLS注册成功" + userInfo.identifier);
            TLSService.getInstance().setLastErrno(0);
            AppManager.getAppManager().finishSomeActivity();
        }

        @Override
        public void OnStrAccRegFail(TLSErrInfo errInfo) {
            Log.e(Const.IM, "OnStrAccRegFail=" + String.format("%s: %s",
                    errInfo.ErrCode == TLSErrInfo.TIMEOUT ?
                            "网络超时" : "错误", errInfo.Msg));
        }

        @Override
        public void OnStrAccRegTimeout(TLSErrInfo errInfo) {
            Log.e(Const.IM, "OnStrAccRegTimeout=" + String.format("%s: %s",
                    errInfo.ErrCode == TLSErrInfo.TIMEOUT ?
                            "网络超时" : "错误", errInfo.Msg));
        }
    }
}
