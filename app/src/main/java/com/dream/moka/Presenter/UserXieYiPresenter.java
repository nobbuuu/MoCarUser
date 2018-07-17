package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.XieYiBean;
import com.dream.moka.Contract.UserXieYiContract;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class UserXieYiPresenter extends RxPresenter<UserXieYiContract> {
    private ApiService mApiService;

    @Inject
    public UserXieYiPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getData(String type){
        BaseObserver<XieYiBean> baseObserver=new BaseObserver<XieYiBean>(mView) {
            @Override
            public void onSuccess(XieYiBean results) {
                String content = results.getContent();
                if (content!=null){
                    mView.getXiyiSuccess(content);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
                mView.showError();
            }
        };
        addObserver(mApiService.getProtocolByType(type),baseObserver);
    }
}
