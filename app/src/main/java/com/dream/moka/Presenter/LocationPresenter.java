package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.CodeContract;
import com.dream.moka.Contract.LocationContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class LocationPresenter extends RxPvPresenter<LocationContract.View> implements LocationContract.Presenter<LocationContract.View>{

    private ApiService mApiService;

    @Inject
    public LocationPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void uplaodLocation(String url) {
        addObserver(mApiService.uploadLocation(url), new BasePvObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.showLocationData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
