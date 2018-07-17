package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;

/**
 * Created by Administrator on 2017/12/16 0016.
 */

public interface SettingContract extends BaseContract {
    void onSuccess(boolean isSet);//是否设置了支付密码
    void outLoginSuccess();
}
