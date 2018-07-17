package com.dream.moka.Presenter.FreeMaintain;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Contract.FreeMaitain.ConfirmMainTainContract;
import com.dream.moka.Contract.FreeMaitain.FreeMainTainContract;

import java.util.Map;

import javax.inject.Inject;


/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class ConfirmMaintainPresenter extends RxPvPresenter<ConfirmMainTainContract.View> implements ConfirmMainTainContract.Presenter<ConfirmMainTainContract.View>{

    private ApiService mApiService;

    @Inject
    public ConfirmMaintainPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void confirmMaintainOrder(Map<String, String> map) {
        addObserver(mApiService.confirmSelfChoseOrder(map), new BasePvObserver<ConfirmOrderResultBean>(mView) {
            @Override
            public void onSuccess(ConfirmOrderResultBean results) {
                mView.showConfirmData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }

}
