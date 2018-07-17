package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;

/**
 * Created by Administrator on 2017/12/25 0025.
 */

public interface MineContract extends BaseContract {
    void getDriverInfoSuccess(String status);
    void getUserDataSuccess(String userType);
}
