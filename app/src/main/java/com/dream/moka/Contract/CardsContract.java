package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.ListCouponBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public interface CardsContract extends BaseContract{
    void getDataSuccess(List<ListCouponBean> listResultBeans);
}
