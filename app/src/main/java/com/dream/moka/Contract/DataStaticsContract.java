package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.DriverDataInfoResultBean;
import com.dream.moka.Bean.TodayDataResultBean;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public interface DataStaticsContract extends BaseContract {
    void getDataSucces(DriverDataInfoResultBean results);
    void getToDayData(TodayDataResultBean todayDataResultBean);
}
