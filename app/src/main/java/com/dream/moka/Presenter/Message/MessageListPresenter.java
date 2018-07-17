package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Message.MessageListBean;
import com.dream.moka.Contract.Message.MessageListContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class MessageListPresenter extends RxPvPresenter<MessageListContract.View> implements MessageListContract.Presenter<MessageListContract.View>{

    private ApiService mApiService;

    @Inject
    public MessageListPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void getMyMessageList(String token, Map<String, String> map) {
        addObserver(mApiService.getMyMessageList(token,map), new BasePvObserver<MessageListBean>(mView) {
            @Override
            public void onSuccess(MessageListBean results) {
                mView.showMsgListData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
