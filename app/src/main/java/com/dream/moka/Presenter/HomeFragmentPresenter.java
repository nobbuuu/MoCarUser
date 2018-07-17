package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.BannerBean;
import com.dream.moka.Bean.DriverResultBean;
import com.dream.moka.Bean.HotActivityBean;
import com.dream.moka.Bean.NewsBean;
import com.dream.moka.Contract.HomeFragmentContract;
import com.dream.moka.Other.CommonAction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/13 0013.
 */

public class HomeFragmentPresenter extends RxPresenter<HomeFragmentContract>{

    private ApiService mApiService;

    @Inject
    public HomeFragmentPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

    public void getBannerData(){
        BaseListObserver<BannerBean> bannerBeanBaseObserver=new BaseListObserver<BannerBean>(mView) {
            @Override
            public void onSuccess(List<BannerBean> results) {
                mView.setBannerInfo(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError();
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.getBannerList(),bannerBeanBaseObserver);
    }

    public void getNewData(){
        BaseListObserver<NewsBean> newsBeanBaseListObserver=new BaseListObserver<NewsBean>(mView) {
            @Override
            public void onSuccess(List<NewsBean> results) {
                mView.setNewsInfo(results);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError();
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.getNews(),newsBeanBaseListObserver);
    }
    public void getHotData(){
        BaseListObserver<HotActivityBean> beanBaseListObserver=new BaseListObserver<HotActivityBean>(mView) {
            @Override
            public void onSuccess(List<HotActivityBean> results) {
                mView.setHotInfo(results);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError();
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.getHotActivity(),beanBaseListObserver);

    }
    /**
     * 获取司机信息
     */
    public void getData(){
        String token = CommonAction.getToken();
        BaseObserver<DriverResultBean> baseObserver=new BaseObserver<DriverResultBean>(mView) {
            @Override
            public void onSuccess(DriverResultBean results) {
                CommonAction.saveDriverData(results);
                mView.driverSuccess(results.getStatus());
            }

            @Override
            public void onCodeError(String errorCode, String errorMsg) {
//                super.onCodeError(errorCode, errorMsg);
                mView.openShenqing();
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError();
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.getDriverByToken(token),baseObserver);
    }

}
