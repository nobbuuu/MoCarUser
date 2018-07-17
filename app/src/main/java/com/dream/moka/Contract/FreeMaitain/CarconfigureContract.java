package com.dream.moka.Contract.FreeMaitain;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Maintain.CarconfigureBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface CarconfigureContract {

    interface View extends BasePVContract.BaseView {

        void showCarconfigureData(CarconfigureBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getCarConfigure(String carId);
    }
}
