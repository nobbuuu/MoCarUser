package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.Bean.ConfirmOrderResultBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface OneMaintainContract {

    interface View extends BasePVContract.BaseView {
        void showData(BaoYangFangAnResultBean dataBean);
        void getDataSuccess(ConfirmOrderResultBean results);
    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getMaintenancePlan(Map<String,String> map);
    }
}
