package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.CarDetailResultBean;

/**
 * Created by Administrator on 2017/12/20 0020.
 */

public interface CarInfoContract extends BaseContract {
    void onDataSuccess(CarDetailResultBean carDetailResultBean);
    void onEditSuccess();
}
