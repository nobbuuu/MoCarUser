package com.dream.moka.Contract.DriverCenter;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.DriverCenter.DriverOrderDetailBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.KeysBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface DJCarDetailContract {

    interface View extends BasePVContract.BaseView {

        void showOrderDetailData(KeysBean dataBean);
        void showOrderDetailData(DriverOrderDetailBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void toPickUpCar(Map<String,String> map);
        void getOrderDetail(String orderId);
    }
}
