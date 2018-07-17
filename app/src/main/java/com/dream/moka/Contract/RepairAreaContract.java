package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.RepairAreaBean;

import java.util.List;
import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface RepairAreaContract {

    interface View extends BasePVContract.BaseView {

        void showRepairShopData(List<RepairAreaBean> dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getSelfChoseShopList(Map<String,String> map);
    }
}
