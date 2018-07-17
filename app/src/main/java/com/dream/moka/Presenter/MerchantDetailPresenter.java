package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Merchant.RepairShopInfoBean;
import com.dream.moka.Contract.MerchantDetailContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class MerchantDetailPresenter extends RxPvPresenter<MerchantDetailContract.View> implements MerchantDetailContract.Presenter<MerchantDetailContract.View>{

    private ApiService mApiService;

    @Inject
    public MerchantDetailPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getRepairShopDetail(String id) {
        addObserver(mApiService.getRepairShopById(id), new BasePvObserver<RepairShopInfoBean>(mView) {
            @Override
            public void onSuccess(RepairShopInfoBean results) {
                mView.showMerchantInfoData(results);
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
