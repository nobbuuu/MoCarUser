package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.NewsDetailBean;
import com.dream.moka.Contract.Message.ContentContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class ContentPresenter extends RxPvPresenter<ContentContract.View> implements ContentContract.Presenter<ContentContract.View>{

    private ApiService mApiService;

    @Inject
    public ContentPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void getContent(String id) {
        addObserver(mApiService.getActivityById(id), new BasePvObserver<HotActivityBean>(mView) {
            @Override
            public void onSuccess(HotActivityBean results) {
                mView.showContentData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }

}
