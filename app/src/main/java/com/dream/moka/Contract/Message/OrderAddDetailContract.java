package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Message.OderAddDetailBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface OrderAddDetailContract {

    interface View extends BasePVContract.BaseView {

        void showData(OderAddDetailBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getMyOrderIncrease(String token, String id);
    }
}
