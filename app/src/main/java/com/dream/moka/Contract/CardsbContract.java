package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.AboutBean;
import com.dream.moka.Bean.Other.CarsbBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface CardsbContract {

    interface View extends BasePVContract.BaseView {

        void showCardsbData(CarsbBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getDriverInfoByImg(String type);
    }
}
