package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.WebViewContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class WebViewPresenter extends RxPresenter<WebViewContract> {
    private ApiService mApiService;
    @Inject
    public WebViewPresenter(ApiService apiService) {
        mApiService = apiService;
    }
    public void checkCanDriver(){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onSuccess();
            }

        };
        addObserver(mApiService.checkDriver(token),baseObserver);

    }

}
