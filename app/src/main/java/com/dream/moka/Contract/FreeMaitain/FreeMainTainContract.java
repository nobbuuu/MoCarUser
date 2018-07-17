package com.dream.moka.Contract.FreeMaitain;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Bean.Maintain.FreeMaintainAllBean;

import java.util.List;
import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface FreeMainTainContract {

    interface View extends BasePVContract.BaseView {

        void showServiceData(List<FreeMaintainAllBean.ServiceResultBean> dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getSelfChoseServiceList(Map<String,String> map);
    }
}
