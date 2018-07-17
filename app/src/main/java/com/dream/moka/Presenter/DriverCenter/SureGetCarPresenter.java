package com.dream.moka.Presenter.DriverCenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.KeysBean;
import com.dream.moka.Contract.DriverCenter.SureGetCarContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class SureGetCarPresenter extends RxPvPresenter<SureGetCarContract.View> implements SureGetCarContract.Presenter<SureGetCarContract.View>{

    private ApiService mApiService;

    @Inject
    public SureGetCarPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void saveCarLive(String token, String orderId, Map<String, String> map) {
        addObserver(mApiService.saveCarLive(token,orderId,map), new BasePvObserver<KeysBean>(mView) {
            @Override
            public void onSuccess(KeysBean results) {
                mView.showSureGetCar(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        });
    }
}
