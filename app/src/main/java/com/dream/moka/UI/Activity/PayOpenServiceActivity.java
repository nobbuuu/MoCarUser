package com.dream.moka.UI.Activity;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dream.moka.Base.BaseActivity;
import com.dream.moka.Bean.EventBusBean.WXPaySuccessBean;
import com.dream.moka.Bean.PayConformAlpayResultBean;
import com.dream.moka.Bean.PayConformWechatResultBean;
import com.dream.moka.Component.BaseComponent;
import com.dream.moka.Contract.MineContract;
import com.dream.moka.Contract.PayContract;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Other.Const;
import com.dream.moka.Presenter.MinePresenter;
import com.dream.moka.Presenter.PayPresenter;
import com.dream.moka.R;
import com.dream.moka.Utils.StringUtil;
import com.dream.moka.Utils.ToastUtils;
import com.dream.moka.Utils.WechatPayUtils;
import com.dream.moka.Utils.alipay.AliPayUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/10/23 0023.
 */
public class PayOpenServiceActivity extends BaseActivity implements PayContract,MineContract{


    @Bind(R.id.back_relay)
    RelativeLayout mBackRelay;
    @Bind(R.id.money_tv)
    TextView mMoneyTv;
    @Bind(R.id.alipay_iv)
    ImageView mAlipayIv;
    @Bind(R.id.wechat_iv)
    ImageView mWechatIv;
    @Bind(R.id.root_lay)
    LinearLayout mRootLay;

    @Inject
    MinePresenter mMinePresenter;
    @Inject
    PayPresenter mPayPresenter;
    @Bind(R.id.select_alipay_iv)
    ImageView mSelectAlipayIv;
    @Bind(R.id.alipay_relay)
    RelativeLayout mAlipayRelay;
    @Bind(R.id.select_wechat_iv)
    ImageView mSelectWechatIv;
    @Bind(R.id.wechat_relay)
    RelativeLayout mWechatRelay;
    @Bind(R.id.topay_tv)
    TextView mTopayTv;


    private String tempMoney;
    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    public void initView() {
        mPayPresenter.attachView(this);
        mMinePresenter.attachView(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void loadResum() {

    }

    @SuppressLint("NewApi")
    @Override
    public void initDatas() {
        tempMoney = getIntent().getStringExtra(Const.intentTag);
        if (tempMoney != null) {
            Log.e("tag","tempMoney="+tempMoney);
            mMoneyTv.setText("¥" + tempMoney);
        }
    }

    @Override
    public void eventListener() {

    }

    @Override
    public void initDaggerView(BaseComponent baseComponent) {
        baseComponent.inject(this);
    }

    @Override
    public void onAlipaySuccessData(PayConformAlpayResultBean sign_data) {
        new AliPayUtils() {
            @Override
            public void paySuccess() {
                ToastUtils.Toast_short("支付成功");
                mMinePresenter.getUserData();
            }
        }.alPay(StringUtil.checkNull(sign_data.getPayInfo()),mActivity);
    }

    @Override
    public void onWechatSuccessData(PayConformWechatResultBean payConformWechatResultBean) {
        PayConformWechatResultBean.PayInfoBean payInfo = payConformWechatResultBean.getPayInfo();
        if (payInfo!=null){
            String sign = payInfo.getSign();
            String nonce_str = payInfo.getNonce_str();
            String appid = payInfo.getAppid();
            String prepay_id = payInfo.getPrepay_id();
            String mch_id = payInfo.getMch_id();
            String timestamp = payInfo.getTimestamp();
            new WechatPayUtils().toWechatPay(appid,mch_id,nonce_str,prepay_id,sign,timestamp);
        }
    }

    //微信支付成功
    @Subscribe
    public void onEvent(WXPaySuccessBean closeConformOrder) {
        mMinePresenter.getUserData();
    }
    @Override
    public void showError() {
        mLoadingDialog.dismiss();
    }

    @Override
    public void complete() {
        mLoadingDialog.dismiss();
        Log.e("tag","complete");
    }

    private String payType = "0";
    @OnClick({R.id.back_relay, R.id.topay_tv,R.id.alipay_relay, R.id.wechat_relay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_relay:
                finish();
                break;
            case R.id.topay_tv:
                if (tempMoney!=null){
                    mLoadingDialog.show();
                    if (payType.equals("0")){
                        mPayPresenter.payWithAlipay(CommonAction.getToken(),payType,tempMoney,"open");
                    }else {
                        mPayPresenter.payWithWechat(CommonAction.getToken(),payType,tempMoney,"open");
                    }
                }
                break;

            case R.id.alipay_relay:
                payType = "0";
                mSelectAlipayIv.setVisibility(View.VISIBLE);
                mSelectWechatIv.setVisibility(View.GONE);
                break;
            case R.id.wechat_relay:
                payType = "1";
                mSelectAlipayIv.setVisibility(View.GONE);
                mSelectWechatIv.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void getDriverInfoSuccess(String status) {

    }

    @Override
    public void getUserDataSuccess(String userType) {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
