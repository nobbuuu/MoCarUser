package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.Message.OrderAddBean;
import com.dream.moka.Contract.Message.MessageOrderAddContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class MsgOrdeerAddPresenter extends RxPvPresenter<MessageOrderAddContract.View> implements MessageOrderAddContract.Presenter<MessageOrderAddContract.View>{

    private ApiService mApiService;

    @Inject
    public MsgOrdeerAddPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getMyPushOrderDetail(Map<String, String> map) {
        addObserver(mApiService.getMyPushOrderDetail(map), new BasePvObserver<OrderAddBean>(mView) {
            @Override
            public void onSuccess(OrderAddBean results) {
                mView.showOrderAddData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }

    @Override
    public void checkPushOrder(Map<String, String> map) {
        addObserver(mApiService.checkPushOrder(map), new BasePvObserver<ConfirmOrderResultBean>(mView) {
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
