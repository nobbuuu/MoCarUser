package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Message.CheckResultBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface CkechOrderContract {

    interface View extends BasePVContract.BaseView {

        void showCheckData(CheckResultBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void checkIncreaseOrder(String token, String id);
    }
}
