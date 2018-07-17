package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Message.OderAddDetailBean;
import com.dream.moka.Contract.Message.CancleAddOrderContract;
import com.dream.moka.Contract.Message.OrderAddDetailContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class CancleIncreaceOrderPresenter extends RxPvPresenter<CancleAddOrderContract.View> implements CancleAddOrderContract.Presenter<CancleAddOrderContract.View>{

    private ApiService mApiService;

    @Inject
    public CancleIncreaceOrderPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void cancleIncreaseOrder(String token, String orderId) {
        addObserver(mApiService.cancleIncreaseOrder(token,orderId), new BasePvObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.showCancleOrderData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
