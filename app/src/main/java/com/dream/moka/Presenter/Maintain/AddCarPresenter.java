package com.dream.moka.Presenter.Maintain;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Contract.AddCarContract;

import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class AddCarPresenter extends RxPvPresenter<AddCarContract.View> implements AddCarContract.Presenter<AddCarContract.View>{

    private ApiService mApiService;

    @Inject
    public AddCarPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void addCar(Map<String, String> map) {
//        addObserver(mApiService.addServiceUserCar(map), new BasePvObserver<AddCarResultBean>(mView) {
//            @Override
//            public void onSuccess(AddCarResultBean results) {
//                mView.showData(results);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                super.onError(e);
//                Log.e(getClass().getName(), "onError: " + e);
//                mView.showError();
//            }
//        });
    }
}
