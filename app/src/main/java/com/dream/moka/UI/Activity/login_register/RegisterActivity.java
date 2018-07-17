package com.dream.moka.UI.Activity.login_register;

import android.app.Dialog;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.LoginContract;
import com.dream.moka.Presenter.LoginPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.set.UserXieYiActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.XRegexUtils;

import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class RegisterActivity extends BaseActivity implements LoginContract{
    @Inject
    LoginPresenter mLoginPresenter;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.register_tv)
    TextView mRegisterTv;
    @Bind(R.id.sendCode)
    LinearLayout mSendCode;
    @Bind(R.id.yzm_lay)
    LinearLayout mYzmLay;
    @Bind(R.id.next_tv)
    TextView mNextTv;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.phone_number)
    EditText mPhoneNumber;
    @Bind(R.id.time_number)
    TextView mTimeNumber;
    @Bind(R.id.code_number)
    EditText mCodeNumber;
    @Bind(R.id.xieyi)
    TextView mXieyi;
    private EventHandler eventHandler;
    private Dialog mLoadingDialog;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    startTimer(mTimeNumber, mSendCode);
                    break;
            }
        }
    };
    private String mStringPhone;
    private Thread mThread;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mLoginPresenter.attachView(this);
        AppManager.getAppManager().addActivity(mActivity);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);

    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void eventListener() {
        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                mLoadingDialog.dismiss();
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        SetPwdActivity.openAct(mContext, mStringPhone);
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                        mHandler.sendEmptyMessage(0);
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    try {
                        String message = ((Throwable) data).getMessage();
                        JSONObject jsonObject = new JSONObject(message);
                        String detail = jsonObject.getString("detail");
                        ToastUtils.Toast_short(mActivity, detail);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };


    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // 注册监听器
        SMSSDK.registerEventHandler(eventHandler);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SMSSDK.unregisterEventHandler(eventHandler);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyThread(mThread);
    }

    @OnClick({R.id.back_relay, R.id.next_tv, R.id.sendCode, R.id.register_tv,R.id.xieyi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.next_tv:
                if (TextUtils.isEmpty(mPhoneNumber.getText())) {
                    ToastUtils.Toast_short("请输入手机号");
                    return;
                }
                mStringPhone = mPhoneNumber.getText().toString();
                String string = mStringPhone;
                if (XRegexUtils.checkMobile(string) && !TextUtils.isEmpty(mCodeNumber.getText())) {
//                    mLoadingDialog.show();
//                    SMSSDK.submitVerificationCode("86", string, mCodeNumber.getText().toString());
                    checkCode();
                }
                break;
            case R.id.sendCode:
                if (TextUtils.isEmpty(mPhoneNumber.getText())) {
                    ToastUtils.Toast_short("请输入手机号");
                    return;
                }
                String mobile = mPhoneNumber.getText().toString();
                if (XRegexUtils.checkMobile(mobile)) {
                    mLoadingDialog.show();
                    mLoginPresenter.getCode(mobile);
//                    SMSSDK.getVerificationCode("86", mobile);
                }

                break;
            case R.id.register_tv:
                finish();
                break;
            case R.id.xieyi:
                UserXieYiActivity.openAct(mContext,"user");
                break;
        }
    }

    /**
     * 短信验证码倒计时
     */
    private void startTimer(final TextView smsCodeTv, final LinearLayout sendCode) {
        smsCodeTv.setTextColor(getResources().getColor(R.color.color333));
        smsCodeTv.setText("60s后重新获取");
        smsCodeTv.setEnabled(false);
        sendCode.setEnabled(false);
        mThread = new Thread() {
            @Override
            public void run() {
                for (int i = 59; i >= 0; i--) {
                    final int second = i;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    LogUtils.e("在运行！！！");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (second <= 0) {
                                smsCodeTv.setTextColor(getResources().getColor(R.color.color333));
                                smsCodeTv.setText("重新获取");
                                smsCodeTv.setEnabled(true);
                                sendCode.setEnabled(true);
                            } else {
                                smsCodeTv.setTextColor(getResources().getColor(R.color.color333));
                                smsCodeTv.setText(second + "s后重新获取");
                            }
                        }
                    });
                }
            }
        };
        mThread.start();
    }

    /**
     * 销毁线程方法
     */
    private void destroyThread(Thread thread) {
        try {
            if (null != thread && Thread.State.RUNNABLE == thread.getState()) {
                try {
                    Thread.sleep(500);
                    thread.interrupt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            thread = null;
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
    public void loginSuccess(UserResultBean results) {

    }

    private String mSendPhone = "", mStringSendCode = "";

    @Override
    public void getCodeSuccess(String phone, String code) {
        mSendPhone = phone;
        mStringSendCode = code;
        Log.e("tag","code="+code);
        startTimer(mTimeNumber, mSendCode);
    }

    @Override
    public void goBindPhone(String openId, String type, String name, String headUrl) {

    }

    /**
     * 检验临时的验证码
     */
    private void checkCode() {
        String trim = mCodeNumber.getText().toString().trim();
        if (trim.equals(mStringSendCode)) {
            SetPwdActivity.openAct(mContext, mSendPhone);
        } else {
            ToastUtils.Toast_short("验证码错误");
        }
    }

}
