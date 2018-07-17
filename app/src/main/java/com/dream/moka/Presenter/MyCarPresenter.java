package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.CarResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Other.CommonAction;
import com.dream.moka.Contract.MyCarContract;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/20 0020.
 */

public class MyCarPresenter extends RxPresenter<MyCarContract> {
    private ApiService mApiService;

    @Inject
    public MyCarPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    public void getMyCarData(){
        String token = CommonAction.getToken();
        BaseListObserver<CarResultBean> baseListObserver=new BaseListObserver<CarResultBean>(mView) {
            @Override
            public void onSuccess(List<CarResultBean> results) {
                mView.onDataSuccess(results);
            }
        };
        addObserver(mApiService.getUserCar(token),baseListObserver);
    }


    /**
     * 删除
     * @param carId
     * @param position
     */
    public void deleteCarData(String carId, final int position){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onDeleteSuccess(position);
            }
        };
        addObserver(mApiService.deleteUserCar(token,carId),baseObserver);
    }

    /**
     * 设为默认
     * @param carId
     * @param position
     */
    public void setDefult(String carId, final int position){
        String token = CommonAction.getToken();
        BaseObserver<EmptyBean> baseObserver=new BaseObserver<EmptyBean>(mView) {
            @Override
            public void onSuccess(EmptyBean results) {
                mView.onDefultSuccess(position);
            }
        };
        addObserver(mApiService.setUserCarDefault(token,carId),baseObserver);
    }
}
