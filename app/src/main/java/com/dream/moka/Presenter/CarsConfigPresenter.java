package com.dream.moka.Presenter;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BaseListObserver;
import com.dream.moka.Base.RxPresenter;
import com.dream.moka.Bean.CarConfigureResultBean;
import com.dream.moka.Contract.CarConfigContract;

import java.util.List;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class CarsConfigPresenter extends RxPresenter<CarConfigContract> {

    private ApiService itApiService;

    private static final String TAG = "CarsConfigPresenter";

    @Inject
    public CarsConfigPresenter(ApiService bookApiService) {
        this.itApiService = bookApiService;
    }

    public void getCarConfig(String id) {
        BaseListObserver<CarConfigureResultBean> baseListObserver=new BaseListObserver<CarConfigureResultBean>(mView) {
            @Override
            public void onSuccess(List<CarConfigureResultBean> results) {
                mView.showData(results);
            }
        };
        addObserver(itApiService.getConfigure(id),baseListObserver);

    }
}
