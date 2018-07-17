package com.dream.moka.Contract;

import com.dream.moka.Base.BaseContract;
import com.dream.moka.Bean.PayConformWechatResultBean;

/**
 * Created by Administrator on 2018/1/3 0003.
 */

public interface RechargeContract extends BaseContract {
    void getPriceData(String price);
    void onAlipaySuccessData(String sign_data);
    void onWechatSuccessData(PayConformWechatResultBean payConformWechatResultBean);
    void updataSuccess();
}
