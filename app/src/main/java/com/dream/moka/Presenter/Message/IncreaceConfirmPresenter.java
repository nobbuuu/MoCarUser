package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Message.OderAddDetailBean;
import com.dream.moka.Contract.Message.IncreaceOrderConfirmContract;
import com.dream.moka.Contract.Message.OrderAddDetailContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class IncreaceConfirmPresenter extends RxPvPresenter<IncreaceOrderConfirmContract.View> implements IncreaceOrderConfirmContract.Presenter<IncreaceOrderConfirmContract.View>{

    private ApiService mApiService;

    @Inject
    public IncreaceConfirmPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void confirmIncreaseOrder(Map<String, String> map) {
        addObserver(mApiService.confirmIncreaseOrder(map), new BasePvObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
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
