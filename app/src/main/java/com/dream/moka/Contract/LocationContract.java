package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface LocationContract {

    interface View extends BasePVContract.BaseView {

        void showLocationData(EmptyBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void uplaodLocation(String url);
    }
}
