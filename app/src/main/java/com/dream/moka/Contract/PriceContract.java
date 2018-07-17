package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.PriceResultBean;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public interface PriceContract extends BaseContract {
    void getYaJinData(PriceResultBean s);
}
