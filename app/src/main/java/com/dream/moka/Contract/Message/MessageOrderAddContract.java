package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.Message.OrderAddBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface MessageOrderAddContract {

    interface View extends BasePVContract.BaseView {

        void showOrderAddData(OrderAddBean dataBean);
        void showConfirmData(ConfirmOrderResultBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getMyPushOrderDetail(Map<String,String> map);
        void checkPushOrder(Map<String, String> map);
    }
}
