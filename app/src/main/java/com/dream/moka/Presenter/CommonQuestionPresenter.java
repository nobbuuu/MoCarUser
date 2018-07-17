package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.NomalQuestionBean;
import com.dream.moka.Contract.CommonQuestionContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/14 0014.
 */

public class CommonQuestionPresenter extends RxPresenter<CommonQuestionContract> {
    private ApiService mApiService;

    @Inject
    public CommonQuestionPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    public void getQuesitionData(){
        BaseListObserver<NomalQuestionBean> baseObserver=new BaseListObserver<NomalQuestionBean>(mView) {
            @Override
            public void onSuccess(List<NomalQuestionBean> results) {
                mView.setQuestionData(results);
            }
        };
        addObserver(mApiService.getNomalQuestion(),baseObserver);


    }
}
