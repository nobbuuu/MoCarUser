package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.CitysResultBean;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public interface CityChooseContract extends BaseContract {
    void getDataSuccess(CitysResultBean citysResultBean);
}
