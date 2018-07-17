package com.dream.moka.Presenter;

import android.util.Base64;
import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Other.VersionBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.CodeContract;
import com.dream.moka.Contract.VersionContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class VersionPresenter extends RxPvPresenter<VersionContract.View> implements VersionContract.Presenter<VersionContract.View>{

    private ApiService mApiService;

    @Inject
    public VersionPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getNewVersion(String type, String appType) {
        addObserver(mApiService.getVersion(type,appType), new BasePvObserver<VersionBean>(mView) {
            @Override
            public void onSuccess(VersionBean results) {
                mView.showVersionData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
