package com.dream.moka.Contract;


import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.CarBrandResultBean;
import com.dream.moka.Bean.CarCategoryResultBean;

import java.util.List;

/**
 * @author lfh.
 * @date 2016/8/6.
 */
public interface CarsChooseContract extends BaseContract {
    void showData(CarBrandResultBean results);
    void showCarStleData(List<CarCategoryResultBean> results);
}
