package com.dream.moka.Contract;


import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.CarConfigureResultBean;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface CarConfigContract extends BaseContract{
    void showData(List<CarConfigureResultBean> results);
}
