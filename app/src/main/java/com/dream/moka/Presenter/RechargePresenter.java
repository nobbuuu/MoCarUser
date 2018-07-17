package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.DriverResultBean;
import com.dream.moka.Bean.PayConformAlpayResultBean;
import com.dream.moka.Bean.PayConformWechatResultBean;
import com.dream.moka.Bean.PriceResultBean;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Contract.RechargeContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class RechargePresenter extends RxPresenter<RechargeContract> {
    private ApiService mApiService;

    @Inject
    public RechargePresenter(ApiService apiService) {
        mApiService = apiService;
    }


    public void getRechargePrice() {
        BaseObserver<PriceResultBean> baseObserver = new BaseObserver<PriceResultBean>(mView) {
            @Override
            public void onSuccess(PriceResultBean results) {
                if (results != null) {
                    String driverDeposit = results.getDriverDeposit();
                    if (driverDeposit != null && !driverDeposit.equals("")) {
                        mView.getPriceData(driverDeposit);
                    }
                }
            }
        };
        addObserver(mApiService.getPriceRatio(), baseObserver);

    }

    /**
     * 用户充值(支付宝)
     *
     * @param payType
     */
    public void UserrechargeAlipay(final String payType, String price) {
        String token = CommonAction.getToken();
        BaseObserver<PayConformAlpayResultBean> baseObserver = new BaseObserver<PayConformAlpayResultBean>(mView) {
            @Override
            public void onSuccess(PayConformAlpayResultBean results) {
                String payInfo = results.getPayInfo();
                mView.onAlipaySuccessData(payInfo == null ? "" : payInfo);
            }
        };
        addObserver(mApiService.DriverAlipayYajin(token, payType, price, "close"), baseObserver);

    }

    /**
     * 用户充值(微信)
     *
     * @param payType
     */
    public void UserrechargeWechatPay(String payType, String price) {
        String token = CommonAction.getToken();
        BaseObserver<PayConformWechatResultBean> baseObserver = new BaseObserver<PayConformWechatResultBean>(mView) {
            @Override
            public void onSuccess(PayConformWechatResultBean results) {
                if (results != null) {
                    mView.onWechatSuccessData(results);
                }
            }
        };
        addObserver(mApiService.DriverWechatYajin(token, payType, price, "close"), baseObserver);

    }

    /**
     * 充值押金(支付宝)
     *
     * @param payType
     */
    public void recharge500alPay(String payType, String price) {
        String token = CommonAction.getToken();
        BaseObserver<PayConformAlpayResultBean> baseObserver = new BaseObserver<PayConformAlpayResultBean>(mView) {
            @Override
            public void onSuccess(PayConformAlpayResultBean results) {
                String payInfo = results.getPayInfo();
                mView.onAlipaySuccessData(payInfo == null ? "" : payInfo);
            }
        };
        addObserver(mApiService.DriverAlipayYajin(token, payType, price, "deposit"), baseObserver);

    }

    /**
     * 充值押金(微信)
     *
     * @param payType
     */
    public void recharge500wechatPay(String payType, String price) {
        String token = CommonAction.getToken();
        BaseObserver<PayConformWechatResultBean> baseObserver = new BaseObserver<PayConformWechatResultBean>(mView) {
            @Override
            public void onSuccess(PayConformWechatResultBean results) {
                if (results != null) {
                    mView.onWechatSuccessData(results);
                }
            }
        };
        addObserver(mApiService.DriverWechatYajin(token, payType, price, "deposit"), baseObserver);

    }

    /**
     * 刷新司机信息
     */
    public void updaInfo() {
        String token = CommonAction.getToken();
        BaseObserver<DriverResultBean> baseObserver = new BaseObserver<DriverResultBean>(mView) {
            @Override
            public void onSuccess(DriverResultBean results) {
                CommonAction.saveDriverData(results);
                mView.updataSuccess();
            }
        };
        addObserver(mApiService.getDriverByToken(token), baseObserver);
    }
    /**
     * 刷新用户信息
     */
    public void getUserData(){
        String token = CommonAction.getToken();
        BaseObserver<UserResultBean> baseObserver=new BaseObserver<UserResultBean>(mView) {
            @Override
            public void onSuccess(UserResultBean results) {
                CommonAction.saveUserData(results);
                mView.updataSuccess();
            }
        };
        addObserver(mApiService.getUserInfoByToken(token),baseObserver);
    }

}
