package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Contract.SetPwdContract;
import com.dream.moka.Other.CommonAction;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public class SetPwdPresenter extends RxPresenter<SetPwdContract> {
    private ApiService mApiService;

    @Inject
    public SetPwdPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    public void registerData(String phone, String pwd,String nickName) {
        String drviceToken = CommonAction.getDrviceToken();
        BaseObserver<UserResultBean> baseObserver = new BaseObserver<UserResultBean>(mView) {
            @Override
            public void onSuccess(UserResultBean results) {
                CommonAction.saveUserData(results);
                mView.registerSuccess(results);
            }
        };
        addObserver(mApiService.register(phone, pwd, drviceToken, "1",nickName), baseObserver);
    }
    public void loginByOthersBindPhone(Map<String,String> map) {
        BaseObserver<UserResultBean> baseObserver = new BaseObserver<UserResultBean>(mView) {
            @Override
            public void onSuccess(UserResultBean results) {
                CommonAction.saveUserData(results);
                mView.registerSuccess(results);
            }
        };
        addObserver(mApiService.loginByOthersBindPhone(map), baseObserver);
    }
}
