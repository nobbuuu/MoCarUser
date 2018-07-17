package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.DriverHomeResultBean;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public interface DriverCenterMainContract extends BaseContract {
    void getYaJinData(String s);
    void getHomeData(DriverHomeResultBean driverHomeResultBean);
    void changeOrderStaus();
}
