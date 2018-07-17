package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Bean.ProductDetailBean;
import com.dream.moka.Contract.CodeContract;
import com.dream.moka.Contract.ProductDetailContract;
import com.dream.moka.Other.CommonAction;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class ProductDetailPresenter extends RxPvPresenter<ProductDetailContract.View> implements ProductDetailContract.Presenter<ProductDetailContract.View>{

    private ApiService mApiService;

    @Inject
    public ProductDetailPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getProductDetail(String partId) {
        addObserver(mApiService.getPartDetail(CommonAction.getToken(),partId), new BasePvObserver<ProductDetailBean>(mView) {
            @Override
            public void onSuccess(ProductDetailBean results) {
                mView.showProductDetailData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
