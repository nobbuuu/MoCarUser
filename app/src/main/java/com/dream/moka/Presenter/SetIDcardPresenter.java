package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.SetIDcardContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public class SetIDcardPresenter extends RxPresenter<SetIDcardContract> {
    private ApiService mApiService;
    @Inject
    public SetIDcardPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void setIdCardData(String idcard,String from){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.setIdcardSuccess();
            }
        };
        addObserver(mApiService.setTradPswFirst(token,idcard,from),baseObserver);

    }
    public void forgetAndCheckIdCard(String idcard){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.setIdcardSuccess();
            }
        };
        addObserver(mApiService.checkTradPswByIdNo(token,idcard),baseObserver);

    }

}
