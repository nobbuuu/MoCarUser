package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.RepairAreaBean;
import com.dream.moka.Contract.RepairAreaContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class RepairShopChosePresenter extends RxPvPresenter<RepairAreaContract.View> implements RepairAreaContract.Presenter<RepairAreaContract.View>{

    private ApiService mApiService;

    @Inject
    public RepairShopChosePresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getSelfChoseShopList(Map<String, String> map) {
        addObserver(mApiService.getSelfChoseShopList(map), new BasePvObserver<List<RepairAreaBean>>(mView) {
            @Override
            public void onSuccess(List<RepairAreaBean> results) {
                mView.showRepairShopData(results);
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
