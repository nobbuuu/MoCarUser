package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Message.MessageDetailBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface MessageDetailContract {

    interface View extends BasePVContract.BaseView {

        void showMsgIfoData(MessageDetailBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getMyMessageDetail(String token, String id);
    }
}
