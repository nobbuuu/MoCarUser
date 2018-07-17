package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.Contract.ButlerServiceContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class ButlerSevicerPresenter extends RxPvPresenter<ButlerServiceContract.View> implements ButlerServiceContract.Presenter<ButlerServiceContract.View>{

    private ApiService mApiService;

    @Inject
    public ButlerSevicerPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getJSDetail(Map<String, String> map) {
        addObserver(mApiService.getJSDetail(map), new BasePvObserver<List<OnlinerBean>>(mView) {
            @Override
            public void onSuccess(List<OnlinerBean> results) {
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
