package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.UserResultBean;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public interface SetPwdContract extends BaseContract{
    void registerSuccess(UserResultBean results);
}
