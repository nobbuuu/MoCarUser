package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.DriverDataInfoResultBean;
import com.dream.moka.Bean.TodayDataResultBean;
import com.dream.moka.Contract.DataStaticsContract;
import com.dream.moka.Other.CommonAction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class DataStaticsPresenter extends RxPresenter<DataStaticsContract>{
    private ApiService mApiService;

    @Inject
    public DataStaticsPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getData(){
        String token = CommonAction.getToken();
        BaseObserver<DriverDataInfoResultBean> baseObserver=new BaseObserver<DriverDataInfoResultBean>(mView) {
            @Override
            public void onSuccess(DriverDataInfoResultBean results) {
                if (results!=null){
                    mView.getDataSucces(results);
                }
            }
        };
        addObserver(mApiService.getDriverInfo(token),baseObserver);
    }

    public void getTodayData(String data){
        String token = CommonAction.getToken();
        BaseListObserver<TodayDataResultBean> baseObserver=new BaseListObserver<TodayDataResultBean>(mView) {
            @Override
            public void onSuccess(List<TodayDataResultBean> results) {

            }
        };
        addObserver(mApiService.getOrderInfoByTime(token,data),baseObserver);
    }

}
