package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.OrderDetailBean;
import com.dream.moka.Bean.OrderDriverBean;
import com.dream.moka.Contract.OrderDetailContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class OrderDetailPresenter extends RxPresenter<OrderDetailContract> {
    private ApiService mApiService;

    @Inject
    public OrderDetailPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getDetailData(String id){
        String token = CommonAction.getToken();
        BaseObserver<OrderDetailBean> baseObserver=new BaseObserver<OrderDetailBean>(mView) {
            @Override
            public void onSuccess(OrderDetailBean results) {
                mView.getDetaildataSuccess(results);
            }
        };
        addObserver(mApiService.getUserOrderDetail(token,id),baseObserver);

    }

    public void getOrderDriver(String id){
        String token = CommonAction.getToken();
        BaseObserver<OrderDriverBean> baseObserver=new BaseObserver<OrderDriverBean>(mView) {
            @Override
            public void onSuccess(OrderDriverBean results) {
                mView.getDriverData(results);
            }
        };
        addObserver(mApiService.getOrderDriverDetail(token,id),baseObserver);

    }
}
