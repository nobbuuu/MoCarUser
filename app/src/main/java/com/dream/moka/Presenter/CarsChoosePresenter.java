package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.CarBrandResultBean;
import com.dream.moka.Bean.CarCategoryResultBean;
import com.dream.moka.Contract.CarsChooseContract;


import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class CarsChoosePresenter extends RxPresenter<CarsChooseContract>{
    private ApiService itApiService;

    @Inject
    public CarsChoosePresenter(ApiService bookApiService) {
        this.itApiService = bookApiService;
    }

    /**
     * 获取车品牌
     */
    public void getCarsData(Map<String,String> map) {
        BaseObserver<CarBrandResultBean> baseObserver=new BaseObserver<CarBrandResultBean>(mView) {
            @Override
            public void onSuccess(CarBrandResultBean results) {
                if (results!=null){
                    mView.showData(results);
                }
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError();
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(itApiService.getCarBrand(map),baseObserver);

    }
    /**
     * 获取车型
     * @param id
     */
    public void getCarStyle(String id) {

        BaseListObserver<CarCategoryResultBean> baseListObserver=new BaseListObserver<CarCategoryResultBean>(mView) {
            @Override
            public void onSuccess(List<CarCategoryResultBean> results) {
                if(results!=null){
                    mView.showCarStleData(results);
                }
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                mView.showError();
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(itApiService.getCarCategory(id),baseListObserver);

    }
}
