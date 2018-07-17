package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.DriverHomeResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PriceResultBean;
import com.dream.moka.Contract.DriverCenterMainContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class DriverCenterMainPresenter extends RxPresenter<DriverCenterMainContract> {
    private ApiService mApiService;
    @Inject
    public DriverCenterMainPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getData(){
        String token = CommonAction.getToken();
        BaseObserver<DriverHomeResultBean> baseObserver=new BaseObserver<DriverHomeResultBean>(mView) {
            @Override
            public void onSuccess(DriverHomeResultBean results) {
                mView.getHomeData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.getDriverIndexInfo(token),baseObserver);
    }

    /**
     * 获取押金金额
     */
    public void getRechargePrice() {
        BaseObserver<PriceResultBean> baseObserver = new BaseObserver<PriceResultBean>(mView) {
            @Override
            public void onSuccess(PriceResultBean results) {
                if (results != null) {
                    String driverDeposit = results.getDriverDeposit();
                    if (driverDeposit != null && !driverDeposit.equals("")) {
                        mView.getYaJinData(driverDeposit);
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.getPriceRatio(), baseObserver);

    }
    public void openService(){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.changeOrderStaus();
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.changeDriverOrderStatus(token),baseObserver);
    }
}
