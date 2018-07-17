package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Contract.ChangePwdContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/16 0016.
 */

public class ChangePwdPresenter extends RxPresenter<ChangePwdContract> {
    private ApiService mApiService;

    @Inject
    public ChangePwdPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    public void ChangePwdData(String oldPwd, String newPwd) {
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver = new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onSuccess();
            }
        };
        addObserver(mApiService.updateUserPassword(token, oldPwd, newPwd), baseObserver);
    }
}
