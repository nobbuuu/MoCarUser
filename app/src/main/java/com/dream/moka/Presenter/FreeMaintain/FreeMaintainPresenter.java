package com.dream.moka.Presenter.FreeMaintain;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Bean.Maintain.FreeMaintainAllBean;
import com.dream.moka.Contract.FreeMaitain.FreeMainTainContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;


/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class FreeMaintainPresenter extends RxPvPresenter<FreeMainTainContract.View> implements FreeMainTainContract.Presenter<FreeMainTainContract.View>{

    private ApiService mApiService;

    @Inject
    public FreeMaintainPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getSelfChoseServiceList(Map<String, String> map) {
        addObserver(mApiService.getSelfChoseServiceList(map), new BasePvObserver<FreeMaintainAllBean>(mView) {
            @Override
            public void onSuccess(FreeMaintainAllBean results) {
                if (results.getServiceResult()!=null){
                    mView.showServiceData(results.getServiceResult());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
