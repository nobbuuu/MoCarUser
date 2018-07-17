package com.dream.moka.Contract.FreeMaitain;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;

import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface ConfirmMainTainContract {

    interface View extends BasePVContract.BaseView {
        void showConfirmData(ConfirmOrderResultBean dataBean);
    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {

        void confirmMaintainOrder(Map<String, String> map);

    }
}
