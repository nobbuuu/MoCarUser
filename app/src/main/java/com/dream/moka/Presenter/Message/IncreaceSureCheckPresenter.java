package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Message.CheckResultBean;
import com.dream.moka.Contract.Message.CkechOrderContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class IncreaceSureCheckPresenter extends RxPvPresenter<CkechOrderContract.View> implements CkechOrderContract.Presenter<CkechOrderContract.View>{

    private ApiService mApiService;

    @Inject
    public IncreaceSureCheckPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void checkIncreaseOrder(String token, String id) {
        addObserver(mApiService.checkIncreaseOrder(token,id), new BasePvObserver<CheckResultBean>(mView) {
            @Override
            public void onSuccess(CheckResultBean results) {
                mView.showCheckData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
