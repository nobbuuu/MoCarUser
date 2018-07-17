package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.PayConformAlpayResultBean;
import com.dream.moka.Bean.PayConformWechatResultBean;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public interface PayContract extends BaseContract {
    void onAlipaySuccessData(PayConformAlpayResultBean sign_data);
    void onWechatSuccessData(PayConformWechatResultBean payConformWechatResultBean);
}
