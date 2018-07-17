package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface BeMerchantContract {

    interface View extends BasePVContract.BaseView {

        void showData(EmptyBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void saveRepairShop(Map<String,String> map);
    }
}
