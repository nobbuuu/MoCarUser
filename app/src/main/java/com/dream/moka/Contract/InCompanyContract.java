package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Other.IncompanyBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface InCompanyContract {

    interface View extends BasePVContract.BaseView {

        void showData(List<IncompanyBean> dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getInsuranceList();
    }
}
