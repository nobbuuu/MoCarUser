package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Maintain.AddCarResultBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface AddCarContract {

    interface View extends BasePVContract.BaseView {

        void showData(AddCarResultBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void addCar(Map<String,String> map);
    }
}
