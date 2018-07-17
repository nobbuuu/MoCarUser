package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.DriverAccountResultBean;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public interface DriverPurseContract extends BaseContract {
    void getAccountSuccess(DriverAccountResultBean resultBean);
}
