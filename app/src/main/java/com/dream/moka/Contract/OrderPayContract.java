package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.PayResultBean;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public interface OrderPayContract extends BaseContract {
    void onSuccess(String type,PayResultBean results);
}
