package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.AllOrderResultBean;
import com.dream.moka.Contract.DriverCenterOrderAllContract;
import com.dream.moka.Other.CommonAction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class DriverCenterOrderAllPresenter extends RxPresenter<DriverCenterOrderAllContract> {
    private static  final String PAGESIZE="10";
    private int mPage=1;
    private ApiService mApiService;

    @Inject
    public DriverCenterOrderAllPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getAllOrderData(String status, String orderID, String startTime, String endTime, final boolean isMore){
        String page="1";
        if (isMore){
            page=String.valueOf(mPage+1);
        }else {
            mPage=1;
        }
        String token = CommonAction.getToken();
        BaseObserver<AllOrderResultBean> baseObserver=new BaseObserver<AllOrderResultBean>(mView) {
            @Override
            public void onSuccess(AllOrderResultBean results) {
                List<AllOrderResultBean.ItemsBean> items = results.getItems();
                int totalPage = results.getTotalPage();
                int pageNum = results.getPageNum();
                mPage=pageNum;
                mView.getDataSuccess(items,isMore);
                mView.isAll(totalPage<=pageNum);

            }
        };
        addObserver(mApiService.getOrderList(token,status,orderID,startTime,endTime,page,PAGESIZE),baseObserver);

    }
}
