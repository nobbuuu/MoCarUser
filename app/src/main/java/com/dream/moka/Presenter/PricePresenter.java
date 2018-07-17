package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.PriceResultBean;
import com.dream.moka.Contract.PriceContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class PricePresenter extends RxPresenter<PriceContract> {
    private ApiService mApiService;

    @Inject
    public PricePresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取押金金额
     */
    public void getRechargePrice() {
        addObserver(mApiService.getPriceRatio(), new BaseObserver<PriceResultBean>(mView) {
            @Override
            public void onSuccess(PriceResultBean results) {
                mView.getYaJinData(results);
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
