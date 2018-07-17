package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.AddressListResultBean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public interface AddressContract extends BaseContract {
    void getDataSuccess(List<AddressListResultBean> results);
    void setDefultAddressSuccess(int position);
    void deleteAddressSuccess(int position);
}
