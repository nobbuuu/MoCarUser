package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.BankCardBean;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public interface ChangeBankContract extends BaseContract {
    void getBankDataSuccess(BankCardBean results);
    void hasPayCode(boolean has);
    void checkPwdSuccess();
}
