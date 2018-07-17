package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.OrderListResultBean;
import com.dream.moka.Bean.PayResultBean;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public interface OrdersContract extends BaseContract {
    void getDataSuccess(OrderListResultBean results,boolean isMore,boolean isAll);
    void deleteSuccess(int position);
    void orderUpdata();
    void sureGetCar();
    void payAgainSuccess(PayResultBean payResultBean,String paytype);

}
