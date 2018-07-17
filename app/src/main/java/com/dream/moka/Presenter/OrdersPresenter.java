package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.OrderListResultBean;
import com.dream.moka.Bean.PayResultBean;
import com.dream.moka.Contract.OrdersContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class OrdersPresenter extends RxPresenter<OrdersContract> {
    private ApiService mApiService;
    private static final String PAGESIZE="10";
    private int mPage=1;

    @Inject
    public OrdersPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取订单列表
     * @param type
     * @param isMore
     */
    public void getOrderData(String type, final boolean isMore){
        String page;
        if (isMore){
            page=String.valueOf(mPage+1);
        }else {
            page="1";
        }
        String token = CommonAction.getToken();
        BaseObserver<OrderListResultBean> baseObserver=new BaseObserver<OrderListResultBean>(mView) {
            @Override
            public void onSuccess(OrderListResultBean results) {
                int pageNum = results.getPageNum();
                mPage=pageNum;
                int totalPage = results.getTotalPage();
                mView.getDataSuccess(results,isMore,mPage>=totalPage);
            }
        };
        addObserver(mApiService.getUserOrderList(token,type,page,PAGESIZE),baseObserver);
    }
    /**
     * 删除订单
     */
    public void deleteOrderById(String id, final int position){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.deleteSuccess(position);
            }
        };
        addObserver(mApiService.deleteOrderById(token,id),baseObserver);
    }

    /**
     * 收车
     */
    public void confirmOrderById(String id){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.sureGetCar();
            }
        };
        addObserver(mApiService.confirmOrderById(token,id),baseObserver);
    }

    /**
     * 申请退款
     */
    public void returnMoney(String id,String reason){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.orderUpdata();
            }
        };
        addObserver(mApiService.drawbackOrderById(token,id,reason),baseObserver);
    }

    /**
     * 去支付
     */
    public void payAgain(String id, final String paytype){
        String token = CommonAction.getToken();
        BaseObserver<PayResultBean> baseObserver=new BaseObserver<PayResultBean>(mView) {
            @Override
            public void onSuccess(PayResultBean results) {
                mView.payAgainSuccess(results,paytype);
            }
        };
        addObserver(mApiService.againPayOrder(token,id),baseObserver);
    }
}
