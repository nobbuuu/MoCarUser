package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.BankResultBean;
import com.dream.moka.Contract.BankRvContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public class BankRvPresenter extends RxPresenter<BankRvContract> {
    private ApiService mApiService;

    @Inject
    public BankRvPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取开户行信息
     */
    public void getBanksData(){
        BaseListObserver<BankResultBean> baseListObserver=new BaseListObserver<BankResultBean>(mView) {
            @Override
            public void onSuccess(List<BankResultBean> results) {
                mView.onSuccessData(results);
            }
        };
        addObserver(mApiService.getBankList(),baseListObserver);
    }
}
