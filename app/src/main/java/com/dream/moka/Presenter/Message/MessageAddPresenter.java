package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Contract.Message.MessageAddContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class MessageAddPresenter extends RxPvPresenter<MessageAddContract.View> implements MessageAddContract.Presenter<MessageAddContract.View>{

    private ApiService mApiService;

    @Inject
    public MessageAddPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void addUserMessage(String token, String messageId) {
        addObserver(mApiService.addUserMessage(token,messageId), new BasePvObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.showMsgAddData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
