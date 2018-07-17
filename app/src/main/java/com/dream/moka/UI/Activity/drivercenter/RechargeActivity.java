package com.dream.moka.UI.Activity.drivercenter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Bean.EventBusBean.WXPaySuccessBean;
import com.dream.moka.Bean.PayConformWechatResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.RechargeContract;
import com.dream.moka.Presenter.RechargePresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.Dialog.DialogUtils;
import com.dream.moka.Utils.LogUtils;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.alipay.AuthResult;
import com.dream.moka.Utils.alipay.PayResult;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class RechargeActivity extends BaseActivity implements RechargeContract {

    @Inject
    RechargePresenter mRechargePresenter;
    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.money)
    TextView mMoney;
    @Bind(R.id.alpay)
    RadioButton mAlpay;
    @Bind(R.id.wechat)
    RadioButton mWechat;
    @Bind(R.id.payGroup)
    RadioGroup mPayGroup;
    @Bind(R.id.btn)
    TextView mBtn;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;
    @Bind(R.id.driverLayout)
    LinearLayout mDriverLayout;
    @Bind(R.id.usermoney)
    EditText mUsermoney;
    @Bind(R.id.userLayout)
    LinearLayout mUserLayout;
    private Drawable mDrawable;
    private String mPayType = "0";//支付类型0:支付宝，1:微信
    private Dialog mLoadingDialog;
    private String mForm;

    public static void openAct(Context context, String form) {
        Intent intent = new Intent(context, RechargeActivity.class);
        intent.putExtra("form", form);
        context.startActivity(intent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public void initView() {
        mRechargePresenter.attachView(this);
        mDrawable = ContextCompat.getDrawable(mContext, R.mipmap.icon_select);
        /// 这一步必须要做,否则不会显示.
        mDrawable.setBounds(0, 0, mDrawable.getMinimumWidth(), mDrawable.getMinimumHeight());
        mLoadingDialog = DialogUtils.initLoadingDialog(mActivity);
        EventBus.getDefault().register(this);
        mForm = getIntent().getStringExtra("form");
        if (mForm.equals("user")) {
            mUserLayout.setVisibility(View.VISIBLE);
            mDriverLayout.setVisibility(View.GONE);
        }
    }

    @Subscribe
    public void onEvent(WXPaySuccessBean closeConformOrder) {
        if (mForm.equals("user")) {
            mRechargePresenter.getUserData();
        } else {
            mRechargePresenter.updaInfo();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void loadResum() {

    }

    @Override
    public void initDatas() {
        if (mForm.equals("")) {//交押金
            mLoadingDialog.show();
            mRechargePresenter.getRechargePrice();
        }
    }

    @Override
    public void eventListener() {
        mPayGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.alpay:
                        mAlpay.setCompoundDrawables(null, null, mDrawable, null);
                        mWechat.setCompoundDrawables(null, null, null, null);
                        mPayType = "0";
                        break;
                    case R.id.wechat:
                        mAlpay.setCompoundDrawables(null, null, null, null);
                        mWechat.setCompoundDrawables(null, null, mDrawable, null);
                        mPayType = "1";
                        break;
                }
            }
        });

    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @OnClick({R.id.back_relay, R.id.btn, R.id.money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.btn:

                if (mForm.equals("user")) {//用户充值
                    if (TextUtils.isEmpty(mUsermoney.getText())) {
                        ToastUtils.Toast_short("请输入充值金额");
                        return;
                    }
                    mLoadingDialog.show();
                    if (mPayType.equals("0")) {//支付
                        mRechargePresenter.UserrechargeAlipay(mPayType, mUsermoney.getText().toString().trim());
//                        mRechargePresenter.UserrechargeAlipay(mPayType, "0.01");
                    } else if (mPayType.equals("1")) {
                        mRechargePresenter.UserrechargeWechatPay(mPayType, mUsermoney.getText().toString().trim());
                    }
                } else {//商家充值
                    if (TextUtils.isEmpty(mMoney.getText())) {
                        ToastUtils.Toast_short("充值金额异常");
                        return;
                    }
                    mLoadingDialog.show();
                    if (mPayType.equals("0")) {//支付
                        mRechargePresenter.recharge500alPay(mPayType, mMoney.getText().toString().trim());
                    } else if (mPayType.equals("1")) {
                        mRechargePresenter.recharge500wechatPay(mPayType, mMoney.getText().toString().trim());
                    }
                }
                break;
            case R.id.money:
                mLoadingDialog.show();
                mRechargePresenter.getRechargePrice();
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
     * 支付完成关闭页面
     */
    @Override
    public void updataSuccess() {
//        EventBus.getDefault().post(new CloseConformOrder());
        finish();
    }


    /**
     * 获取到押金
     *
     * @param price
     */
    @Override
    public void getPriceData(String price) {
        mMoney.setText(price);
    }


    /**
     * 获取支付宝订单
     *
     * @param sign_data
     */
    @Override
    public void onAlipaySuccessData(String sign_data) {
        alPay(sign_data);
    }

    /**
     * 获取微信订单
     *
     * @param payConformWechatResultBean
     */
    @Override
    public void onWechatSuccessData(PayConformWechatResultBean payConformWechatResultBean) {
        PayConformWechatResultBean.PayInfoBean payInfo = payConformWechatResultBean.getPayInfo();
        if (payInfo != null) {
            String sign = payInfo.getSign();
            String nonce_str = payInfo.getNonce_str();
            String appid = payInfo.getAppid();
            String prepay_id = payInfo.getPrepay_id();
            String mch_id = payInfo.getMch_id();
            String timestamp = payInfo.getTimestamp();
            genPayReq(appid, mch_id, nonce_str, prepay_id, sign, timestamp);
            sendPayReq(appid);
//            genPayReq("wxb4ba3c02aa476ea1", "1900006771", "bcecf01d42f55f4521dc09babfc4ee54",
//                    "wx201801052120401ffaca84970323200305", "2B8AC71949BE355BEFEE58FEA83C89AD", "1515158440");
//            sendPayReq("wxb4ba3c02aa476ea1");

        }

    }


    /**
     * 微信方法
     */
    IWXAPI api;
    PayReq req = new PayReq();

    private void genPayReq(String appid, String mch_id, String nonce_str, String prepay_id, String sign, String time) {
        LogUtils.e(appid + "+" + mch_id + "+" + nonce_str + "+" + prepay_id + "+" + sign + time);
        req.appId = appid;// 商户在微信开放平台申请的应用id,支付请求对象req的字段appId的值
        req.partnerId = mch_id;// 商户id
        req.prepayId = prepay_id;// 预支付订单
        req.packageValue = "Sign=WXPay";// 商家根据文档填写的数据和签名
        req.nonceStr = nonce_str;// 随机串，防重发
        req.timeStamp = time;// 时间戳，防重发
        req.sign = sign;// 签名字段
    }

    /**
     * 微信支付方法
     */
    private void sendPayReq(String appid) {
        LogUtils.e("开始支付");
        // 注册微信app，注册成功后该应用将显示在微信的app列表中
        api = WXAPIFactory.createWXAPI(this, null);
        api.registerApp(appid);
        api.sendReq(req);
    }


    /**
     * 支付宝支付方法
     *
     * @param sign_data
     */
    private void alPay(final String sign_data) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(mActivity);
                Map<String, String> result = alipay.payV2(sign_data, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝回调
     */
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.Toast_short("支付成功");
                        if (mForm.equals("user")) {
                            mRechargePresenter.getUserData();
                        } else {
                            mRechargePresenter.updaInfo();
                        }

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.Toast_short("支付失败");

                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        ToastUtils.Toast_short("授权成功");
                    } else {
                        // 其他状态值则为授权失败
                        ToastUtils.Toast_short("授权失败");

                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
