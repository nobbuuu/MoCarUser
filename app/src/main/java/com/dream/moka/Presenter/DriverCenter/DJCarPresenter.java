package com.dream.moka.Presenter.DriverCenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.DriverCenter.DriverOrderDetailBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.KeysBean;
import com.dream.moka.Contract.DriverCenter.DJCarDetailContract;
import com.dream.moka.Other.CommonAction;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class DJCarPresenter extends RxPvPresenter<DJCarDetailContract.View> implements DJCarDetailContract.Presenter<DJCarDetailContract.View>{

    private ApiService mApiService;

    @Inject
    public DJCarPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void toPickUpCar(Map<String, String> map) {
        addObserver(mApiService.toPickUpCar(map), new BasePvObserver<KeysBean>(mView) {
            @Override
            public void onSuccess(KeysBean results) {
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
