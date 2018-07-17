package com.dream.moka.Presenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Contract.OneMaintainContract;
import com.dream.moka.Other.CommonAction;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class OneMainTainPresenter extends RxPvPresenter<OneMaintainContract.View> implements OneMaintainContract.Presenter<OneMaintainContract.View>{

    private ApiService mApiService;

    @Inject
    public OneMainTainPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getMaintenancePlan(Map<String, String> map) {
        addObserver(mApiService.getMaintenancePlan(map), new BasePvObserver<BaoYangFangAnResultBean>(mView) {
            @Override
            public void onSuccess(BaoYangFangAnResultBean results) {
                mView.showData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        });
    }


    /**
     * 确认订单
     * @param carId
     * @param planTargetid
     * @param mile
     */
    public void getData(String carId,String planTargetid,String mile){
        String token = CommonAction.getToken();
        BasePvObserver<ConfirmOrderResultBean> baseObserver=new BasePvObserver<ConfirmOrderResultBean>(mView) {
            @Override
            public void onSuccess(ConfirmOrderResultBean results) {
                mView.getDataSuccess(results);
            }
            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
                mView.showError();
            }
        };
        addObserver(mApiService.confirmMaintainOrder(token,carId,planTargetid,mile),baseObserver);
    }
}
