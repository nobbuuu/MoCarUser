package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.DriverResultBean;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Contract.MineContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public class MinePresenter extends RxPresenter<MineContract> {
    private ApiService mApiService;

    @Inject
    public MinePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取司机信息
     */
    public void getData(){
        String token = CommonAction.getToken();
        BaseObserver<DriverResultBean> baseObserver=new BaseObserver<DriverResultBean>(mView) {
            @Override
            public void onSuccess(DriverResultBean results) {
                CommonAction.saveDriverData(results);
                mView.getDriverInfoSuccess(results.getStatus());
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
                mView.showError();
            }
        };
        addObserver(mApiService.getDriverByToken(token),baseObserver);
    }

    /**
     * 获取用户信息
     */
    public void getUserData(){
        String token = CommonAction.getToken();
        BaseObserver<UserResultBean> baseObserver=new BaseObserver<UserResultBean>(mView) {
            @Override
            public void onSuccess(UserResultBean results) {
                CommonAction.saveUserData(results);
                mView.getUserDataSuccess(results. getUserTypes()==null?"":results.getUserTypes());
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
                mView.showError();
            }
        };
        addObserver(mApiService.getUserInfoByToken(token),baseObserver);
    }
}
