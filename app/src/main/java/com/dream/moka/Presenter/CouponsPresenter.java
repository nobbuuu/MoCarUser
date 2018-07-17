package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.CodeContract;
import com.dream.moka.Contract.CouponsContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class CouponsPresenter extends RxPvPresenter<CouponsContract.View> implements CouponsContract.Presenter<CouponsContract.View>{

    private ApiService mApiService;

    @Inject
    public CouponsPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getPlatFormCoupon(String token) {
        addObserver(mApiService.getPlatFormCoupon(token), new BasePvObserver<List<ListCouponBean>>(mView) {
            @Override
            public void onSuccess(List<ListCouponBean> results) {
                mView.showCouponsData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }

    @Override
    public void acceptCoupon(String token, String couponIds) {
        addObserver(mApiService.acceptCoupon(token,couponIds), new BasePvObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.showaAceptCoupon(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
