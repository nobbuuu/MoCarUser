package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.DriverResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.DriverMineContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/29 0029.
 */

public class DriverMinePresenter extends RxPresenter<DriverMineContract> {
    private ApiService mApiService;

    @Inject
    public DriverMinePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取司机信息
     */
    public void getDriverInfo(){
        String token = CommonAction.getToken();
        BaseObserver<DriverResultBean> baseObserver=new BaseObserver<DriverResultBean>(mView) {
            @Override
            public void onSuccess(DriverResultBean results) {
                CommonAction.saveDriverData(results);
                String homeMode = results.getHomeMode();
                if (homeMode!=null){
                    mView.setHomeModeSuccess(homeMode);
                }
            }
        };
        addObserver(mApiService.getDriverByToken(token),baseObserver);

    }

    /**
     * homeMode:是否设置回家模式（回家模式开启（0：否1：是））
     * @param homeMode
     */
    public void changeHomeMode(final String homeMode){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.setHomeModeSuccess(homeMode);
            }
        };
        addObserver(mApiService.changeHomeMode(token,homeMode),baseObserver);
    }

    public void saveHomeAddress(final String lat, final String lng, final String Address){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.saveddrseeSuccess(lat,lng,Address);
            }
        };
        addObserver(mApiService.saveDriverHomeAddress(token,Address,lng,lat),baseObserver);
    }
}
