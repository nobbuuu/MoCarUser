package com.dream.moka.Contract.Message;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Message.MessageListBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface MessageListContract {

    interface View extends BasePVContract.BaseView {

        void showMsgListData(MessageListBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getMyMessageList(String token, Map<String,String> map);
    }
}
