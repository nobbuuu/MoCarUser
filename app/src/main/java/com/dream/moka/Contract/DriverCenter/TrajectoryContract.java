package com.dream.moka.Contract.DriverCenter;


import com.dream.moka.Base.BasePVContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Other.DriverGuijiBean;
import com.dream.moka.Bean.Other.GuijiBean;
import com.dream.moka.Bean.Other.UserGuijiBean;
import com.dream.moka.Bean.PhoneCodeResultBean;

import java.util.List;
import java.util.Map;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface TrajectoryContract {

    interface View extends BasePVContract.BaseView {

        void showTrackData(DriverGuijiBean dataBean);
        void showUserTrackData(UserGuijiBean dataBean);
    }

    interface Presenter<T> extends BasePVContract.BasePresenter<T> {
        void getDrivingTrack(String token,String driverHisId);
        void getUserDrivingTrack(Map<String,String> map);
    }
}
