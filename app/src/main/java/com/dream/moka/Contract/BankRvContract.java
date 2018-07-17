package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.BankResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/4 0004.
 */

public interface BankRvContract extends BaseContract {
    void onSuccessData(List<BankResultBean> results);
}
