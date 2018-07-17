package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public interface PayPwdContract extends BaseContract {
    void setPayCodeSuccess();
    void CheckOldCodeSuccess();
    void changePayCodeSuccess();
}
