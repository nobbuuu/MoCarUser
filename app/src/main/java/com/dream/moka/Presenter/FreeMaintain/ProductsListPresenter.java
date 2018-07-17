package com.dream.moka.Presenter.FreeMaintain;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.Maintain.FreeMainTainBean;
import com.dream.moka.Bean.Maintain.ProductSelectBean;
import com.dream.moka.Contract.FreeMaitain.ProductListContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;


/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class ProductsListPresenter extends RxPvPresenter<ProductListContract.View> implements ProductListContract.Presenter<ProductListContract.View>{

    private ApiService mApiService;

    @Inject
    public ProductsListPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }


    @Override
    public void getSelfChosePartList(Map<String, String> map, final RecyclerView recyclerView, final FreeMainTainBean.ListServiceBean bean) {
        addObserver(mApiService.getSelfChosePartList(map), new BasePvObserver<List<ProductSelectBean>>(mView) {
            @Override
            public void onSuccess(List<ProductSelectBean> results) {
                mView.showProductsData(results,recyclerView,bean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
