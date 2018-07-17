package com.dream.moka.Presenter.DriverCenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.DriverCenter.DriverOrderDetailBean;
import com.dream.moka.Contract.DriverCenter.OrderDetailContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class OrderDetailPresenter extends RxPvPresenter<OrderDetailContract.View> implements OrderDetailContract.Presenter<OrderDetailContract.View>{

    private ApiService mApiService;

    @Inject
    public OrderDetailPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void getOrderDetail(String orderId) {
        addObserver(mApiService.getOrderDetail(CommonAction.getToken(),orderId), new BasePvObserver<DriverOrderDetailBean>(mView) {
            @Override
            public void onSuccess(DriverOrderDetailBean results) {
                mView.showOrderDetailData(results);
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
