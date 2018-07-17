package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.WithDrawDataBean;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public interface WithDrawalContract extends BaseContract {
    void getWithDrawData(WithDrawDataBean results);
    void withDrawSuccess();
}
