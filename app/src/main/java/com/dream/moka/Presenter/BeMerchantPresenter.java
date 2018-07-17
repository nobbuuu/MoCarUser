package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.BeMerchantContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class BeMerchantPresenter extends RxPvPresenter<BeMerchantContract.View> implements BeMerchantContract.Presenter<BeMerchantContract.View>{

    private ApiService mApiService;

    @Inject
    public BeMerchantPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void saveRepairShop(Map<String, String> map) {
        addObserver(mApiService.saveRepairShop(map), new BasePvObserver<EmptyBean>(mView) {
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
