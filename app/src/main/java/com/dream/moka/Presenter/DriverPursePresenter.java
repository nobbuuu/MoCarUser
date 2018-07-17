package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.DriverAccountResultBean;
import com.dream.moka.Contract.DriverPurseContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class DriverPursePresenter extends RxPresenter<DriverPurseContract> {
    private ApiService mApiService;

    @Inject
    public DriverPursePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getAccountData(){
        String token = CommonAction.getToken();
        BaseObserver<DriverAccountResultBean> baseObserver=new BaseObserver<DriverAccountResultBean>(mView) {
            @Override
            public void onSuccess(DriverAccountResultBean results) {
                mView.getAccountSuccess(results);
            }
        };
        addObserver(mApiService.getDriverAccount(token),baseObserver);
    }
}
