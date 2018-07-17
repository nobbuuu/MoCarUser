package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.InTheRelayResultBean;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public interface DriverCenterOrderDoingContract extends BaseContract {
    void getDataSuccess(InTheRelayResultBean results);
}
