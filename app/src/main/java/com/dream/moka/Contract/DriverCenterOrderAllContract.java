package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.AllOrderResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public interface DriverCenterOrderAllContract extends BaseContract {
    void getDataSuccess(List<AllOrderResultBean.ItemsBean> items, boolean isMore);
    void isAll(boolean isAll);
}
