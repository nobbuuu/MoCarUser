package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.UserResultBean;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public interface ChangephoneContract extends BaseContract {
    void getCodeSuccess(String resultsPhone,String code);
    void showValidationPhone(EmptyBean dataBean);
    void showChangePhone(EmptyBean dataBean);
    void showBindPhone(UserResultBean dataBean);
}
