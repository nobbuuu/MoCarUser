package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Online.OnlinerBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface ChatContract {

    interface View extends BasePVContract.BaseView {

        void showOnlinerData(OnlinerBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getOnliner(String carId,String type);
    }
}
