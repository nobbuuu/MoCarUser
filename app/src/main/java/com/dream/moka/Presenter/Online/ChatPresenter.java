package com.dream.moka.Presenter.Online;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.ChatContract;
import com.dream.moka.Contract.CodeContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class ChatPresenter extends RxPvPresenter<ChatContract.View> implements ChatContract.Presenter<ChatContract.View>{

    private ApiService mApiService;

    @Inject
    public ChatPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void getOnliner(String carId, String type) {
        addObserver(mApiService.getTechnicianOnLine(CommonAction.getToken(),carId,type), new BasePvObserver<OnlinerBean>(mView) {
            @Override
            public void onSuccess(OnlinerBean results) {
                mView.showOnlinerData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
