package com.dream.moka.Utils;


import com.dream.moka.Bean.EventBusBean.ReturnBean;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/10/16 0016.
 */
public class ReturnUtil {

    public static void returnMain(){
        ReturnBean bean = new ReturnBean();
        bean.setEventStr("return");
        EventBus.getDefault().post(bean);
    }
}
