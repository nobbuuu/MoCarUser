package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Merchant.MerchantsBean;
import com.dream.moka.Contract.MerchantContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class MerchantPresenter extends RxPvPresenter<MerchantContract.View> implements MerchantContract.Presenter<MerchantContract.View>{

    private ApiService mApiService;

    @Inject
    public MerchantPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getRepairShopList(Map<String, String> map) {
        addObserver(mApiService.getRepairShopList(map), new BasePvObserver<List<MerchantsBean>>(mView) {
            @Override
            public void onSuccess(List<MerchantsBean> results) {
                mView.showMerchantData(results);
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
