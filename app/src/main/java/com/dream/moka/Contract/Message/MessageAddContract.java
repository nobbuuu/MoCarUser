package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface MessageAddContract {

    interface View extends BasePVContract.BaseView {

        void showMsgAddData(EmptyBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void addUserMessage(String token,String messageId);
    }
}
