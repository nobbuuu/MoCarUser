package com.dream.moka.UI.Activity.login_register;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Bean.EventBusBean.RefreshLoginStatus;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.LoginContract;
import com.dream.moka.Contract.ThirdLoginContract;
import com.dream.moka.Listener.NoDoubleClickListener;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.LoginPresenter;
import com.dream.moka.Presenter.ThirdLoginPresenter;
import com.dream.moka.R;
import com.dream.moka.UI.Activity.set.ChangephoneActivity;
import com.dream.moka.UI.Activity.set.UserXieYiActivity;
import com.dream.moka.Utils.AppManager;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.IntentUtils;
import com.dream.moka.Utils.LogUtils;
import com.dream.moka.Utils.MyUtils;
import com.dream.moka.Utils.ResourcesUtils;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.XRegexUtils;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.XGPushManager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by Administrator on 2017/8/4 0004.
 */
public class LoginActivity extends BaseActivity implements LoginContract, ThirdLoginContract.View {

    @Bind(R.id.mm_loginlay)
    RelativeLayout mm_loginlay;
    @Bind(R.id.yzm_loginlay)
    RelativeLayout yzm_loginlay;
    @Bind(R.id.yzm_lay)
    LinearLayout yzm_lay;
    @Bind(R.id.zh_lay)
    LinearLayout zh_lay;
    @Bind(R.id.bottom_lay)
    LinearLayout bottom_lay;
    @Bind(R.id.forgotpwd_tv)
    TextView forgotpwd_tv;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.register_tv)
    TextView mRegisterTv;
    @Bind(R.id.yzm_logintv)
    TextView mYzmLogintv;
    @Bind(R.id.mm_logintv)
    TextView mMmLogintv;
    @Bind(R.id.btn)
    TextView mBtn;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.phone_right)
    EditText mPhoneRight;
    @Bind(R.id.password_number)
    EditText mPasswordNumber;
    @Bind(R.id.phone_left)
    EditText mPhoneLeft;
    @Bind(R.id.xieyi)
    TextView mXieyi;
    @Bind(R.id.call_service)
    TextView mCallService;
    @Bind(R.id.wechat)
    TextView mWechat;
    @Bind(R.id.qq)
    TextView mQq;
    @Bind(R.id.weibo)
    TextView mWeibo;
    @Bind(R.id.eyes)
    CheckBox mEyes;
    @Bind(R.id.yzm_edt)
    EditText mYzmEdt;
    @Bind(R.id.yzm_line)
    RelativeLayout mYzmLine;
    @Bind(R.id.yanzm_tv)
    TextView mYanzmTv;
    @Bind(R.id.getyzm_line)
    RelativeLayout mGetyzmLine;
    @Bind(R.id.getyzm_tv)
    LinearLayout mGetyzmTv;
    @Bind(R.id.phone_line)
    RelativeLayout mPhoneLine;
    @Bind(R.id.pwdPhone_line)
    RelativeLayout pwdPhone_line;
    @Bind(R.id.pwd_line)
    RelativeLayout pwd_line;

    private boolean isPwdlogin = true;//是否密码登录
    private EventHandler eventHandler;
    private Dialog mLoadingDialog;

    @Inject
    LoginPresenter mLoginPresenter;
    @Inject
    ThirdLoginPresenter mThirdLoginPresenter;


    public static void openAct(Context context) {
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    startTimer(mYanzmTv);
                    break;
            }
        }
    };
    private String mStringPhoneLeft = "";
    private Thread mThread;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        AppManager.getAppManager().addActivity(mActivity);
        mLoginPresenter.attachView(this);
        mThirdLoginPresenter.attachView(this);
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        MyUtils.initEye(mPasswordNumber, mEyes);
//        if (BuildConfig.DEBUG){
//            mServicegroup.setVisibility(View.VISIBLE);
//        }else {
//            mServicegroup.setVisibility(View.GONE);
//        }
        //重新注册
        if (CommonAction.getDrviceToken().isEmpty()) {
            XGPushManager.registerPush(this, new XGIOperateCallback() {
                @Override
                public void onSuccess(Object data, int flag) {
//token在设备卸载重装的时候有可能会变
                    LogUtils.e("TPush", "注册成功，设备token为：" + data);
                    CommonAction.setDrviceToken((String) data);
                }

                @Override
                public void onFail(Object data, int errCode, String msg) {
                    LogUtils.e("TPush", "注册失败，错误码：" + errCode + ",错误信息：" + msg);
                }
            });
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
       /* new KeyboardChangeListener(this).setKeyBoardListener(new KeyboardChangeListener.KeyBoardListener() {
            @Override
            public void onKeyboardChange(boolean isShow, int keyboardHeight) {
                if (isShow) {
                    bottom_lay.setVisibility(View.GONE);
                } else {
//                    bottom_lay.setVisibi*//**//*lity(View.VISIBLE);
                }
            }
        });*/

        // 创建EventHandler对象
        eventHandler = new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
                mLoadingDialog.dismiss();
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        //提交验证码成功
                        mLoginPresenter.Login(mStringPhoneLeft, "", "0");
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

        mPhoneLeft.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mPhoneLine.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mPhoneLine.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexian1));
                }
            }
        });

        mYzmEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mYzmLine.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                } else {
                    mYzmLine.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexian1));
                }
            }
        });

        mPhoneRight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    pwdPhone_line.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                }else {
                    pwdPhone_line.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexian1));
                }
            }
        });
        mPasswordNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    pwd_line .setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                }else {
                    pwd_line.setBackgroundColor(ResourcesUtils.getColor(R.color.fengexian1));
                }
            }
        });

        mQq.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mLoadingDialog.show();
                mThirdLoginPresenter.toAuthorize(QQ.NAME);

            }
        });
        mWechat.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mLoadingDialog.show();
                mThirdLoginPresenter.toAuthorize(Wechat.NAME);

            }
        });
        mWeibo.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View view) {
                mLoadingDialog.show();
                mThirdLoginPresenter.toAuthorize(SinaWeibo.NAME);

            }
        });
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

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @OnClick({R.id.mm_logintv, R.id.yzm_logintv, R.id.back_relay, R.id.register_tv, R.id.forgotpwd_tv, R.id.btn, R.id.getyzm_tv, R.id.call_service, R.id.xieyi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mm_logintv:
                mm_loginlay.setBackgroundColor(ResourcesUtils.getColor(R.color.yellow_login));
                yzm_loginlay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                zh_lay.setVisibility(View.VISIBLE);
                forgotpwd_tv.setVisibility(View.VISIBLE);
                yzm_lay.setVisibility(View.GONE);
                isPwdlogin = true;
                break;
            case R.id.yzm_logintv:
                mm_loginlay.setBackgroundColor(ResourcesUtils.getColor(R.color.blue_them));
                yzm_loginlay.setBackgroundColor(ResourcesUtils.getColor(R.color.yellow_login));
                zh_lay.setVisibility(View.GONE);
                forgotpwd_tv.setVisibility(View.GONE);
                yzm_lay.setVisibility(View.VISIBLE);
                isPwdlogin = false;
                break;
            case R.id.back_relay:
                finish();
                break;
            case R.id.xieyi:
                UserXieYiActivity.openAct(mContext, "user");
                break;
            case R.id.register_tv:
                Intent intent = new Intent(mContext, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.forgotpwd_tv:
                Intent intent1 = new Intent(mContext, ChangephoneActivity.class);
                intent1.putExtra(Const.intentTag, "pwd");
                startActivity(intent1);
                break;
            case R.id.getyzm_tv:
                if (TextUtils.isEmpty(mPhoneLeft.getText())) {
                    ToastUtils.Toast_short("请输入手机号");
                    return;
                }
                String mobile = mPhoneLeft.getText().toString();
                if (XRegexUtils.checkMobile(mobile)) {
                    mLoadingDialog.show();
//                    SMSSDK.getVerificationCode("86", mobile);
                    mLoginPresenter.getCode(mobile);
                } else {
                    ToastUtils.Toast_short("手机号码输入有误");
                }
                break;
            case R.id.btn:
//                0:验证码登录 1：密码登录
                if (isPwdlogin) {//密码登录
                    Editable text = mPhoneRight.getText();
                    if (TextUtils.isEmpty(text)) {
                        ToastUtils.Toast_short("请输入手机号");
                        return;
                    }
                    if (TextUtils.isEmpty(mPasswordNumber.getText())) {
                        ToastUtils.Toast_short("请输入密码");
                        return;
                    }
                    if (!XRegexUtils.checkMobile(text.toString())) {
                        ToastUtils.Toast_short("手机号码输入有误");
                        return;
                    }

                    mLoginPresenter.Login(text.toString(), mPasswordNumber.getText().toString(), "1");

                } else {//验证码登录
                    mStringPhoneLeft = mPhoneLeft.getText().toString();//此处防止修改帐号达到登陆别人账号的目的
                    if (TextUtils.isEmpty(mStringPhoneLeft)) {
                        ToastUtils.Toast_short("请输入手机号");
                        return;
                    }
                    if (TextUtils.isEmpty(mYzmEdt.getText())) {
                        ToastUtils.Toast_short("请输入验证码");
                        return;
                    }
                    if (XRegexUtils.checkMobile(mStringPhoneLeft)) {
//                        mLoadingDialog.show();
//                        SMSSDK.submitVerificationCode("86", mStringPhoneLeft, mVerificationCode.getText().toString());
                        checkCode();

                    } else {
                        ToastUtils.Toast_short("手机号码输入有误");
                    }
                }
                break;
            case R.id.call_service:
                IntentUtils.call_Dall(CommonAction.getPlatPhone(), mActivity);
                break;
        }
    }

    /**
     * 检验临时的验证码
     */
    private void checkCode() {
        String trim = mYzmEdt.getText().toString().trim();
        if (trim.equals(mSendCode)) {
            // 3. 向TLS验证手机号和验证码
            mLoginPresenter.Login(mSendPhone, "", "0");
        } else {
            ToastUtils.Toast_short("验证码错误");
        }
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
    public void loginSuccess(UserResultBean results) {
        finish();
    }

    private String mSendPhone = "", mSendCode = "";

    @Override
    public void getCodeSuccess(String phone, String code) {
        mSendPhone = phone;
        mSendCode = code;
        Log.e("tag", "code=" + code);
        startTimer(mYanzmTv);
    }

    /**
     * 绑定手机号
     *
     * @param openId
     * @param type
     * @param name
     * @param headUrl
     */
    @Override
    public void goBindPhone(String openId, String type, String name, String headUrl) {
        mLoadingDialog.dismiss();
        ChangephoneActivity.openAct(mContext,openId,type,name,headUrl);
        finish();
    }

    /**
     * 短信验证码倒计时
     */
    private void startTimer(final TextView smsCodeTv) {
        smsCodeTv.setTextColor(getResources().getColor(R.color.color333));
        smsCodeTv.setText("60s后重新获取");
        smsCodeTv.setEnabled(false);
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (second <= 0) {
                                smsCodeTv.setTextColor(getResources().getColor(R.color.color333));
                                smsCodeTv.setText("重新获取");
                                smsCodeTv.setEnabled(true);
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
    public void authorizeComplete(String openID, String userIcon, String nickName, String unionId, String typeName) {
        String type = "";//（0:微信登录 1：QQ登录 2：微博登录）
        if (typeName.equals("QQ")) {
            type = "1";
        } else if (typeName.equals("Wechat")) {
            type = "0";
        } else if (typeName.equals("SinaWeibo")) {
            type = "2";
        }
        mLoginPresenter.thirdLogin(openID, type, nickName, userIcon);
    }

    @Override
    public void showThirdLoginErro(Platform platform, int i, Throwable throwable) {
        mLoadingDialog.dismiss();
        if (platform.getName().equals("Wechat")&&throwable.toString().contains("WechatClientNotExist")){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.Toast_short("您还没安装微信客户端");
                }
            });
        }else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.Toast_short("授权不成功，请稍后再试");
                }
            });
        }
    }

    @Override
    public void showThirdLoginCancle(Platform platform, int i) {
        mLoadingDialog.dismiss();
    }

}
