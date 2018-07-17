package com.dream.moka.Contract.DriverCenter;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.DriverCenter.DriverOrderDetailBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface OrderDetailContract {

    interface View extends BasePVContract.BaseView {

        void showOrderDetailData(DriverOrderDetailBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getOrderDetail(String orderId);
    }
}
