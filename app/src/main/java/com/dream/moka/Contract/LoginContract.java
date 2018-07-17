package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.UserResultBean;

/**
 * Created by Administrator on 2017/12/15 0015.
 */

public interface LoginContract extends BaseContract{
    void loginSuccess(UserResultBean results);
    void getCodeSuccess(String phone,String code);
    void goBindPhone(String openId,String type,String name,String headUrl);
}
