package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Contract.CardsContract;
import com.dream.moka.Other.CommonAction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class CardsPresenter extends RxPresenter<CardsContract> {
    private ApiService mApiService;

    @Inject
    public CardsPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取优惠券
     * @param type
     */
    public void getCardData(String type){
        String token = CommonAction.getToken();
        BaseListObserver<ListCouponBean> baseListObserver=new BaseListObserver<ListCouponBean>(mView) {
            @Override
            public void onSuccess(List<ListCouponBean> results) {
                mView.getDataSuccess(results);
            }
        };
        addObserver(mApiService.getUserCouponList(token,type),baseListObserver);

    }
}
