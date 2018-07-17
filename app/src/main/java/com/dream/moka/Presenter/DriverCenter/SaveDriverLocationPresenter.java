package com.dream.moka.Presenter.DriverCenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.SaveDriverLocationContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class SaveDriverLocationPresenter extends RxPvPresenter<SaveDriverLocationContract.View> implements SaveDriverLocationContract.Presenter<SaveDriverLocationContract.View>{

    private ApiService mApiService;

    @Inject
    public SaveDriverLocationPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void saveDriverLocation(Map<String, String> map) {
        addObserver(mApiService.saveDriverLocation(map), new BasePvObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.showData(results);
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
