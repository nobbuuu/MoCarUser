package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface FeedBackServiceContract {

    interface View extends BasePVContract.BaseView {

        void showData(EmptyBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void addOrderComplaint(Map<String,String> map);
    }
}
