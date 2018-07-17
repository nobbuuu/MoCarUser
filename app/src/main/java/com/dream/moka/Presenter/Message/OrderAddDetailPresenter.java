package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Message.OderAddDetailBean;
import com.dream.moka.Contract.Message.OrderAddDetailContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class OrderAddDetailPresenter extends RxPvPresenter<OrderAddDetailContract.View> implements OrderAddDetailContract.Presenter<OrderAddDetailContract.View>{

    private ApiService mApiService;

    @Inject
    public OrderAddDetailPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getMyOrderIncrease(String token, String id) {
        addObserver(mApiService.getMyOrderIncrease(token,id), new BasePvObserver<OderAddDetailBean>(mView) {
            @Override
            public void onSuccess(OderAddDetailBean results) {
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
