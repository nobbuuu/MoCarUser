package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.PayConformAlpayResultBean;
import com.dream.moka.Bean.PayConformWechatResultBean;
import com.dream.moka.Contract.PayContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class PayPresenter extends RxPresenter<PayContract> {
    private ApiService mApiService;

    @Inject
    public PayPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    //支付宝支付
    public void payWithAlipay( String token, String payType, String money, String openService) {

        addObserver(mApiService.DriverAlipayYajin(token,payType,money,openService), new BaseObserver<PayConformAlpayResultBean>(mView) {
            @Override
            public void onSuccess(PayConformAlpayResultBean results) {
                mView.onAlipaySuccessData(results);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        });

    }

    //微信支付
    public void payWithWechat(String token, String payType, String money, String openService) {
        addObserver(mApiService.DriverWechatYajin(token,payType,money,openService), new BaseObserver<PayConformWechatResultBean>(mView) {
            @Override
            public void onSuccess(PayConformWechatResultBean results) {
                mView.onWechatSuccessData(results);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        });

    }
}
