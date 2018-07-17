package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.AboutBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.AboutContract;
import com.dream.moka.Contract.CodeContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class AboutPresenter extends RxPvPresenter<AboutContract.View> implements AboutContract.Presenter<AboutContract.View>{

    private ApiService mApiService;

    @Inject
    public AboutPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getAbout(String type) {
        addObserver(mApiService.getForHelp(type), new BasePvObserver<AboutBean>(mView) {
            @Override
            public void onSuccess(AboutBean results) {
                mView.showData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
