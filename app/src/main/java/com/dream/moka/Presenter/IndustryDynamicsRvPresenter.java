package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.NewsBean;
import com.dream.moka.Contract.IndustryDynamicsContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class IndustryDynamicsRvPresenter extends RxPresenter<IndustryDynamicsContract> {
    private ApiService mApiService;

    @Inject
    public IndustryDynamicsRvPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    public void getNewData(){
        BaseListObserver<NewsBean> newsBeanBaseListObserver=new BaseListObserver<NewsBean>(mView) {
            @Override
            public void onSuccess(List<NewsBean> results) {
                mView.setNewsData(results);
            }
        };
        addObserver(mApiService.getNews(),newsBeanBaseListObserver);
    }
}
