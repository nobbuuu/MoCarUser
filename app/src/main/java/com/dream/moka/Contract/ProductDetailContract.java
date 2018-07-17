package com.dream.moka.Contract;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Bean.ProductDetailBean;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface ProductDetailContract {

    interface View extends BasePVContract.BaseView {

        void showProductDetailData(ProductDetailBean dataBean);

    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getProductDetail(String partId);
    }
}
