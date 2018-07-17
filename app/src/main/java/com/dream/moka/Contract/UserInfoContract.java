package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

public interface UserInfoContract extends BaseContract {
    void onPicSuccess(String filePath);
    void onSuccess();
}
