package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.AboutBean;
import com.dream.moka.Bean.Other.CarsbBean;
import com.dream.moka.Contract.AboutContract;
import com.dream.moka.Contract.CardsbContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class CardsbPresenter extends RxPvPresenter<CardsbContract.View> implements CardsbContract.Presenter<CardsbContract.View>{

    private ApiService mApiService;

    @Inject
    public CardsbPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getDriverInfoByImg(String type) {
        addObserver(mApiService.getDriverInfoByImg(CommonAction.getToken(),type), new BasePvObserver<CarsbBean>(mView) {
            @Override
            public void onSuccess(CarsbBean results) {
                mView.showCardsbData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
