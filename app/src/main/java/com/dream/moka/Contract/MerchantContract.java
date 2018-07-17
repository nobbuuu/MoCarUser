package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Merchant.MerchantsBean;

import java.util.List;
import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface MerchantContract {

    interface View extends BasePVContract.BaseView {

        void showMerchantData(List<MerchantsBean> dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getRepairShopList(Map<String,String> map);
    }
}
