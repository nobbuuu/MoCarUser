package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.ListCouponBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface CouponsContract {

    interface View extends BasePVContract.BaseView {
        void showCouponsData(List<ListCouponBean> dataBean);
        void showaAceptCoupon(EmptyBean dataBean);
    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getPlatFormCoupon(String token);
        void acceptCoupon(String token,String couponIds);
    }

}
