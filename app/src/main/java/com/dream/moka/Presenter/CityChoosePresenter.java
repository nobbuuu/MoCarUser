package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.CitysResultBean;
import com.dream.moka.Contract.CityChooseContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class CityChoosePresenter extends RxPresenter<CityChooseContract> {
    private ApiService mApiService;

    @Inject
    public CityChoosePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getCityData(){
        BaseObserver<CitysResultBean> baseObserver=new BaseObserver<CitysResultBean>(mView) {
            @Override
            public void onSuccess(CitysResultBean results) {
                mView.getDataSuccess(results);
            }
        };
        addObserver(mApiService.getOpenCity(),baseObserver);
    }
}
