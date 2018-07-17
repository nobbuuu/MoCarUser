package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PayResultBean;
import com.dream.moka.Contract.OrderPayContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class OrderPayPresenter extends RxPresenter<OrderPayContract> {
    private ApiService mApiService;

    @Inject
    public OrderPayPresenter(ApiService ApiService) {
        mApiService = ApiService;
    }

//    token,用户唯一标识（string/50/Y)
//    orderId,订单主键id（string/50/Y）
//    payType,0:支付宝，1:微信  2：账户余额
//    tradPsw：交易密码（string/50/N)

    /**
     * 第三方支付
     *
     * @param order
     * @param paytype 0:支付宝，1:微信  2：账户余额
     *  type  1：0.1支付  2:自选支付  3:钣喷支付  4:增项订单支付   5:保险订单支付
     */
    public void PayOrderData(String order, final String paytype) {
        String token = CommonAction.getToken();
        BaseObserver<PayResultBean> baseObserver = new BaseObserver<PayResultBean>(mView) {
            @Override
            public void onSuccess(PayResultBean results) {
                mView.onSuccess(paytype, results);
            }
        };
        addObserver(mApiService.payMaintainOrder(token, order, paytype,""), baseObserver);
    }

    /**
     * 余额支付
     *
     * @param order
     * @param paytype
     * @param tradPsw
     */
    public void PayOrderData(String order, final String paytype, String tradPsw) {
        String token = CommonAction.getToken();
        BaseObserver<PayResultBean> baseObserver = new BaseObserver<PayResultBean>(mView) {
            @Override
            public void onSuccess(PayResultBean results) {
                mView.onSuccess(paytype, results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.payMaintainOrder(token, order, paytype, tradPsw), baseObserver);

    }

}
