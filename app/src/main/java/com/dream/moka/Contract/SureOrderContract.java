package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.ConfirmOrderResultBean;
import com.dream.moka.Bean.EmptyBean;
import com.dream.moka.Bean.Other.OnlyStringBean;
import com.dream.moka.Bean.SaveOrderResultBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

public interface SureOrderContract extends BaseContract {
    void getDataSuccess(ConfirmOrderResultBean results);
    void getSendTimeSuccess(String time);
    void chooseAddressChangeShopSuccess(List<ConfirmOrderResultBean.ListShopBean> listShopBeans);
    void saveOrderSuccess(SaveOrderResultBean results);

    void showDJF(OnlyStringBean results);

}
