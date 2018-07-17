package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.BaseObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.AddServiceUserCarResultBean;
import com.dream.moka.Bean.CarDetailResultBean;
import com.dream.moka.Bean.CarResultBean;
import com.dream.moka.Contract.OneChooseCarContract;
import com.dream.moka.Other.CommonAction;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/12/23 0023.
 */

public class OneChooseCarPresenter extends RxPresenter<OneChooseCarContract> {
    private ApiService mApiService;

    @Inject
    public OneChooseCarPresenter(ApiService apiService) {
        mApiService = apiService;
    }

    /**
     * 获取默认车辆
     */
    public void getDefultCar(){
        String token = CommonAction.getToken();
        BaseListObserver<CarResultBean> baseListObserver=new BaseListObserver<CarResultBean>(mView) {
            @Override
            public void onSuccess(List<CarResultBean> results) {
                if (results!=null&&results.size()>0){
                    for (int i = 0; i < results.size(); i++) {
                        String status = results.get(i).getStatus();
                        if (status!=null&&status.equals("1")){
                            getCarInfo(results.get(i).getCarId());
                            break;
                        }
                    }
                }else {
                    mView.showNoCar();
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(),"onError="+e);
            }
        };
        addObserver(mApiService.getUserCar(token),baseListObserver);
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
                mView.onSuccess(carDetailResultBean);

            }
        };
        addObserver(mApiService.getUserCarById(token,carId),baseObserver);

    }

    public void addServiceUserCar(String configId,String carName,String registerCity,String lastMianData,String cardNo,String mileage){
        String token = CommonAction.getToken();
        BaseObserver<AddServiceUserCarResultBean> baseObserver=new BaseObserver<AddServiceUserCarResultBean>(mView) {
            @Override
            public void onSuccess(AddServiceUserCarResultBean results) {
                mView.addServiceUserCarSuccess(results);
            }
        };
        addObserver(mApiService.addServiceUserCar(token,configId,carName,registerCity,lastMianData,cardNo,mileage),baseObserver);

    }

}
