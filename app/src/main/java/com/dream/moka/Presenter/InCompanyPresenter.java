package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Other.IncompanyBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.CodeContract;
import com.dream.moka.Contract.InCompanyContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class InCompanyPresenter extends RxPvPresenter<InCompanyContract.View> implements InCompanyContract.Presenter<InCompanyContract.View>{

    private ApiService mApiService;

    @Inject
    public InCompanyPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getInsuranceList() {
        addObserver(mApiService.getInsuranceList(), new BasePvObserver<List<IncompanyBean>>(mView) {
            @Override
            public void onSuccess(List<IncompanyBean> results) {
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
