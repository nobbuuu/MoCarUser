package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.OrderDetailBean;
import com.dream.moka.Bean.OrderDriverBean;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public interface OrderDetailContract extends BaseContract {
    void getDetaildataSuccess(OrderDetailBean detailBean);

    void getDriverData(OrderDriverBean results);
}
