package com.dream.moka.Contract.DriverCenter;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.KeysBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface SureGetCarContract {

    interface View extends BasePVContract.BaseView {

        void showSureGetCar(KeysBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void saveCarLive(String token, String orderId, Map<String,String> map);
    }
}
