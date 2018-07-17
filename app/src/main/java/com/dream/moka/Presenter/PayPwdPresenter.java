package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.PayPwdContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class PayPwdPresenter extends RxPresenter<PayPwdContract> {
    private ApiService mApiService;

    @Inject
    public PayPwdPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 设置支付密码
     */
    public void setPayCode(String idcard,String code){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.setPayCodeSuccess();
            }
        };
        addObserver(mApiService.setTradPswSecond(token,idcard,code),baseObserver);

    }
    /**
     * 验证原来的支付密码
     */
    public void CheckPayCode(String OldCode){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.CheckOldCodeSuccess();
            }
        };
        addObserver(mApiService.checkTradPsw(token,OldCode),baseObserver);
    }
    /**
     * 修改为新的支付密码
     */
    public void setNewPayCode(String NewCode){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.changePayCodeSuccess();
            }
        };
        addObserver(mApiService.saveTradPsw(token,NewCode),baseObserver);
    }
}
