package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.InComeResultBean;
import com.dream.moka.Bean.WithDrawResultBean;
import com.dream.moka.Contract.PurseDetailContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class PurseDetailPresenter extends RxPresenter<PurseDetailContract> {
    static final String PAGESIZE = "20";
    private int mPage = 1;
    private ApiService mApiService;

    @Inject
    public PurseDetailPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取收入
     */
    public void getShouRuData(final boolean isLoadmore) {
        String page;
        if (isLoadmore){
            page=String.valueOf(mPage+=1);
        }else {
            page="1";
            mPage=1;
        }
        String token = CommonAction.getToken();
        BaseObserver<InComeResultBean> baseObserver = new BaseObserver<InComeResultBean>(mView) {
            @Override
            public void onSuccess(InComeResultBean results) {
                mPage= results.getPageNum();
                int totalPage = results.getTotalPage();
                mView.getShouRuData(results,isLoadmore);
                mView.isLoadAll(mPage>=totalPage);
            }
        };
        addObserver(mApiService.getDriverIncomeList(token,page,PAGESIZE),baseObserver);
    }

    /**
     * 获取提现
     */
    public void getTiXianData(final boolean isLoadmore) {
        String page;
        if (isLoadmore){
            page=String.valueOf(mPage+=1);
        }else {
            page="1";
            mPage=1;
        }
        String token = CommonAction.getToken();
        BaseObserver<WithDrawResultBean> baseObserver = new BaseObserver<WithDrawResultBean>(mView) {
            @Override
            public void onSuccess(WithDrawResultBean results) {
                mPage= results.getPageNum();
                int totalPage = results.getTotalPage();
                mView.getTiXianData(results,isLoadmore);
                mView.isLoadAll(mPage>=totalPage);
            }
        };
        addObserver(mApiService.getWithdrawalsList(token,page,PAGESIZE),baseObserver);
    }
}
