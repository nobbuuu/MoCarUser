package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.Merchant.RepairShopInfoBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface MerchantDetailContract {

    interface View extends BasePVContract.BaseView {

        void showMerchantInfoData(RepairShopInfoBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getRepairShopDetail(String id);
    }
}
