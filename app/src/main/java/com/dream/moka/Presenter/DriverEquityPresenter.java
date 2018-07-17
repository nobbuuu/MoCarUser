package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.DriverEquityContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class DriverEquityPresenter extends RxPresenter<DriverEquityContract> {
    private ApiService mApiService;

    @Inject
    public DriverEquityPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void  tuiKuanData(){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onBackMoneySuccess();
            }
        };
        addObserver(mApiService.returnDriverMoney(token),baseObserver);
    }
}
