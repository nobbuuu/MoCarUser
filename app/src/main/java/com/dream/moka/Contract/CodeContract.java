package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.PhoneCodeResultBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface CodeContract {

    interface View extends BasePVContract.BaseView {

        void showData(PhoneCodeResultBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getYzCode(String mobilePhone);
    }
}
