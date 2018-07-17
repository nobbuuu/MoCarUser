package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface CancleAddOrderContract {

    interface View extends BasePVContract.BaseView {

        void showCancleOrderData(EmptyBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void cancleIncreaseOrder(String token,String orderId);
    }
}
