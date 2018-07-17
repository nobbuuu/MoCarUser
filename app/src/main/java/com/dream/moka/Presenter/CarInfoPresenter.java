package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.CarDetailResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Contract.CarInfoContract;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/20 0020.
 */

public class CarInfoPresenter extends RxPresenter<CarInfoContract> {
    private ApiService mApiService;
    @Inject
    public CarInfoPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取爱车详情
     * @param carId
     */
    public void getCarInfo(String carId){
        String token = CommonAction.getToken();
        BaseObserver<CarDetailResultBean> baseObserver=new BaseObserver<CarDetailResultBean>(mView) {
            @Override
            public void onSuccess(CarDetailResultBean carDetailResultBean) {
                mView.onDataSuccess(carDetailResultBean);
            }
        };
        addObserver(mApiService.getUserCarById(token,carId),baseObserver);

    }
    /**
     * 编辑爱车信息
     * @param
     */
    public void editUserCar(Map<String,String> map){

        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean emptyBean) {
                mView.onEditSuccess();
            }
        };
        addObserver(mApiService.editUserCar(map),baseObserver);
    }

}
