package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.WithDrawDataBean;
import com.dream.moka.Contract.WithDrawalContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class WithDrawalPresenter extends RxPresenter<WithDrawalContract> {
    private ApiService mApiService;

    @Inject
    public WithDrawalPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getData(){
        String token = CommonAction.getToken();
        BaseObserver<WithDrawDataBean> baseObserver=new BaseObserver<WithDrawDataBean>(mView) {
            @Override
            public void onSuccess(WithDrawDataBean results) {
                mView.getWithDrawData(results);
            }
        };
        addObserver(mApiService.getDriverWithdraw(token),baseObserver);
    }

    public void WithDraw(String accountId,String bankCardId,String withdrawals,String serviceCharge){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.withDrawSuccess();
            }
        };
        addObserver(mApiService.saveDriverWithdraw(token,accountId,bankCardId,withdrawals,serviceCharge),baseObserver);
    }
}
