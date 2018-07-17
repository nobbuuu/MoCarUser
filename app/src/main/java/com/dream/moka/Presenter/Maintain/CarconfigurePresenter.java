package com.dream.moka.Presenter.Maintain;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Maintain.CarconfigureBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.CodeContract;
import com.dream.moka.Contract.FreeMaitain.CarconfigureContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class CarconfigurePresenter extends RxPvPresenter<CarconfigureContract.View> implements CarconfigureContract.Presenter<CarconfigureContract.View>{

    private ApiService mApiService;

    @Inject
    public CarconfigurePresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getCarConfigure(String carId) {
        addObserver(mApiService.getCarConfigure(CommonAction.getToken(),carId), new BasePvObserver<CarconfigureBean>(mView) {
            @Override
            public void onSuccess(CarconfigureBean results) {
                mView.showCarconfigureData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
