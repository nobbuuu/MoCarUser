package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.InTheRelayResultBean;
import com.dream.moka.Contract.DriverCenterOrderDoingContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class DriverCenterOrderDoingPresenter extends RxPresenter<DriverCenterOrderDoingContract> {
    private ApiService mApiService;
    @Inject
    public DriverCenterOrderDoingPresenter(ApiService apiService) {
        mApiService = apiService;
    }
    public void getDongingOrder(){
        String token = CommonAction.getToken();
        BaseObserver<InTheRelayResultBean> baseObserver=new BaseObserver<InTheRelayResultBean>(mView) {
            @Override
            public void onSuccess(InTheRelayResultBean results) {
                mView.getDataSuccess(results);
            }
        };
        addObserver(mApiService.getOrderInTheRelay(token),baseObserver);

    }
}
