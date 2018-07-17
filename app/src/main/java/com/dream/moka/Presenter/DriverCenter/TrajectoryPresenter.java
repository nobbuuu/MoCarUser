package com.dream.moka.Presenter.DriverCenter;

import android.util.Log;

import com.dream.moka.Api.ApiService;
import com.dream.moka.Base.BasePvObserver;
import com.dream.moka.Base.RxPvPresenter;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Other.DriverGuijiBean;
import com.dream.moka.Bean.Other.GuijiBean;
import com.dream.moka.Bean.Other.UserGuijiBean;
import com.dream.moka.Bean.PhoneCodeResultBean;
import com.dream.moka.Contract.CodeContract;
import com.dream.moka.Contract.DriverCenter.TrajectoryContract;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public class TrajectoryPresenter extends RxPvPresenter<TrajectoryContract.View> implements TrajectoryContract.Presenter<TrajectoryContract.View>{

    private ApiService mApiService;

    @Inject
    public TrajectoryPresenter(ApiService bookApi) {
        this.mApiService = bookApi;
    }

    @Override
    public void getDrivingTrack(String token, String driverHisId) {
        addObserver(mApiService.getDrivingTrack(token,driverHisId), new BasePvObserver<DriverGuijiBean>(mView) {
            @Override
            public void onSuccess(DriverGuijiBean results) {
                mView.showTrackData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }

    @Override
    public void getUserDrivingTrack(Map<String, String> map) {
        addObserver(mApiService.getUserDrivingTrack(map), new BasePvObserver<UserGuijiBean>(mView) {
            @Override
            public void onSuccess(UserGuijiBean results) {
                mView.showUserTrackData(results);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                Log.e(getClass().getName(), "onError: " + e);
            }
        });
    }
}
