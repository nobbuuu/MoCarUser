package com.dream.moka.UI.Activity.set;

import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseCommonActivity;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.ChangephoneContract;
import com.dream.moka.Presenter.ChangephonePresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.CodeUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.XRegexUtils;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/1 0001.
 */
public class SetNewPhoneActivity extends BaseCommonActivity implements ChangephoneContract {

    @Inject
    ChangephonePresenter mChangephonePresenter;
    @Bind(R.id.tip_tv)
    LinearLayout tip_tv;
    @Bind(R.id.call_service)
    TextView call_service;
    @Bind(R.id.next_tv)
    TextView next_tv;
    @Bind(R.id.phone_edt)
    EditText phone_edt;
    @Bind(R.id.time)
    TextView mTime;
    @Bind(R.id.yzm_lay)
    LinearLayout mYzmLay;
    @Bind(R.id.code_number)
    EditText mCodeNumber;
    @Bind(R.id.line_phone)
    View mLinePhone;
    @Bind(R.id.line_yzm)
    View mLineYzm;
    @Bind(R.id.line_getyzm)
    View mLineGetyzm;
    @Bind(R.id.getyzm_lay)
    LinearLayout mGetyzmLay;

    private String mPhone;
    private String mCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_changephone;
    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void initView() {
        mChangephonePresenter.attachView(this);
    }

    @Override
    public String initTitleText() {
        tip_tv.setVisibility(View.GONE);
        call_service.setVisibility(View.GONE);
        phone_edt.setHint("请输入新手机号");
        phone_edt.setText("");
        mCodeNumber.setText("");
        next_tv.setText("确定");
        return "修改手机号";
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
        phone_edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mLinePhone.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mLinePhone.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexiand9));
                }
            }
        });
        mCodeNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mLineYzm.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mLineYzm.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexiand9));
                }
            }
        });


    }

    private  CountDownTimer timer;
    @OnClick({R.id.back_relay, R.id.next_tv, R.id.call_service, R.id.getyzm_lay, R.id.tip_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.next_tv:
                checkCode();
                break;
            case R.id.call_service:

                break;
            case R.id.getyzm_lay:
                mLineGetyzm.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                if (TextUtils.isEmpty(phone_edt.getText())) {
                    ToastUtils.Toast_short("请输入手机号");
                    return;
                }
                String mobile = phone_edt.getText().toString();
                if (XRegexUtils.checkMobile(mobile)) {
//                    SMSSDK.getVerificationCode("86", mobile);
                    mChangephonePresenter.getCodeData(mobile);
                    timer = new CodeUtils(60000, 1000) {
                        @Override
                        protected void onTicked(long l) {
                            if (mTime!=null){
                                mTime.setTextColor(getResources().getColor(R.color.color333));
                                mTime.setText(l/1000 + "s后重新获取");
                            }
                        }

                        @Override
                        protected void onFinished() {
                            if (mTime!=null){
                                mTime.setTextColor(getResources().getColor(R.color.color333));
                                mTime.setText("重新获取");
                                mTime.setEnabled(true);
                            }
                        }
                    }.start();
                } else {
                    ToastUtils.Toast_short("手机号码输入有误");
                }
                break;
            case R.id.tip_tv:

                break;
        }
    }

    /**
     * 检验临时的验证码
     */
    private void checkCode() {
        String trim = mCodeNumber.getText().toString().trim();
        String phone = phone_edt.getText().toString();
        if (phone.isEmpty()) {
            ToastUtils.Toast_short("请输入手机号");
            return;
        }
        if (trim.isEmpty()) {
            ToastUtils.Toast_short("请先获取验证码");
            return;
        }
        if (trim.equals(mCode)) {
            mLoadingDialog.show();
            mChangephonePresenter.setNewPhone(phone);
        } else {
            ToastUtils.Toast_short("验证码错误");
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
    public void getCodeSuccess(String resultsPhone, String code) {
        mPhone = resultsPhone;
        mCode = code;
        Log.e("tag","code="+code);
    }

    @Override
    public void showValidationPhone(EmptyBean dataBean) {
    }

    @Override
    public void showChangePhone(EmptyBean dataBean) {
        ToastUtils.Toast_short("修改成功");
        finish();
    }

    @Override
    public void showBindPhone(UserResultBean dataBean) {

    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();
        if (isShouldHideInput(mGetyzmLay, ev)) {
            mLineGetyzm.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexiand9));
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();


            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
