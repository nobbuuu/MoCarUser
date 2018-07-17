package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.IntegralBean;
import com.dream.moka.Bean.MyAccountBean;
import com.dream.moka.Bean.WalletBean;
import com.dream.moka.Contract.PurseJifenContract;
import com.dream.moka.Other.CommonAction;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class PurseJifenPresenter extends RxPresenter<PurseJifenContract> {
    private ApiService mApiService;
    static final String PAGESIZE = "20";
    private int mPage = 1;

    @Inject
    public PurseJifenPresenter(ApiService apiService) {
        mApiService = apiService;
    }


    public void getUserAccountData(){
        String token = CommonAction.getToken();
        BaseObserver<WalletBean> baseObserver=new BaseObserver<WalletBean>(mView) {
            @Override
            public void onSuccess(WalletBean results) {
                mView.getAccount(results);
            }
        };
        addObserver(mApiService.getUserAccount(token),baseObserver);

    }


    public void getYUeData(String type, final boolean isLoadmore) {
        String token = CommonAction.getToken();
        String page;
        if (isLoadmore) {
            page = String.valueOf(mPage += 1);
        } else {
            page = "1";
            mPage = 1;
        }
        BaseObserver<MyAccountBean> baseObserver = new BaseObserver<MyAccountBean>(mView) {
            @Override
            public void onSuccess(MyAccountBean results) {
                mPage = results.getPageNum();
                int totalPage = results.getTotalPage();
                mView.getQianBaoData(results, isLoadmore);
                mView.isLoadAll(mPage >= totalPage);

            }
        };
        addObserver(mApiService.getUserRechargeList(token, type, page, PAGESIZE), baseObserver);
    }


    public void getJiFenData( Map<String,String> map) {
        BaseObserver<List<IntegralBean>> baseObserver = new BaseObserver<List<IntegralBean>>(mView) {
            @Override
            public void onSuccess(List<IntegralBean> results) {
                mView.showScoreList(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
                mView.showError();
            }
        };
        addObserver(mApiService.getUserScoreList(map), baseObserver);
    }
}
