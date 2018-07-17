package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Message.OderAddDetailBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface IncreaceOrderConfirmContract {

    interface View extends BasePVContract.BaseView {

        void showData(EmptyBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void confirmIncreaseOrder(Map<String,String> map);
    }
}
