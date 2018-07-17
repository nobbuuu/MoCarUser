package com.dream.moka.Presenter.Message;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.NewsDetailBean;
import com.dream.moka.Contract.DynamicDetailContract;
import com.dream.moka.Contract.Message.ContentContract;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class DynamicDetailPresenter extends RxPvPresenter<DynamicDetailContract.View> implements DynamicDetailContract.Presenter<DynamicDetailContract.View>{

    private ApiService mApiService;

    @Inject
    public DynamicDetailPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getDynamicContent(String id) {
        addObserver(mApiService.getNewsById(id), new BasePvObserver<NewsDetailBean>(mView) {
            @Override
            public void onSuccess(NewsDetailBean results) {
                mView.showDynamicData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
