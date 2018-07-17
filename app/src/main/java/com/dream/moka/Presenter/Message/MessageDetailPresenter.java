package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Message.MessageDetailBean;
import com.dream.moka.Contract.Message.MessageDetailContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class MessageDetailPresenter extends RxPvPresenter<MessageDetailContract.View> implements MessageDetailContract.Presenter<MessageDetailContract.View>{

    private ApiService mApiService;

    @Inject
    public MessageDetailPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }



    @Override
    public void getMyMessageDetail(String token, String id) {
        addObserver(mApiService.getMyMessageDetail(token,id), new BasePvObserver<MessageDetailBean>(mView) {
            @Override
            public void onSuccess(MessageDetailBean results) {
                mView.showMsgIfoData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
