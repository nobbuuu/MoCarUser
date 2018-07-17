package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.AppraiseContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/18 0018.
 */

public class AppraisePresenter extends RxPresenter<AppraiseContract> {
    private ApiService mApiService;

    @Inject
    public AppraisePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void PingLun(String orderId,String sendStar,String backStar,String shopStar,String jsStar,String xiStar){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onSuccess();
            }
        };
        addObserver(mApiService.evaluateOrderById(token,orderId,sendStar,backStar,shopStar,jsStar,xiStar),baseObserver);
    }
}
