package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.AddServiceUserCarResultBean;
import com.dream.moka.Bean.CarDetailResultBean;

/**
 * Created by Administrator on 2017/12/23 0023.
 */

public interface OneChooseCarContract extends BaseContract {
    void onSuccess(CarDetailResultBean carDetailResultBean);
    void addServiceUserCarSuccess(AddServiceUserCarResultBean results);
    void showNoCar();
}
