package com.dream.moka.IM;

import android.content.Context;
import android.util.Log;

import com.dream.moka.Other.Const;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;


/**
 * 初始化
 * 包括imsdk等
 */
public class InitBusiness {

    private InitBusiness(){}

    public static void start(Context context){
        initImsdk(context, 0);
    }

    public static void start(Context context, int logLevel){
        initImsdk(context, logLevel);
    }


    /**
     * 初始化imsdk
     */
    private static void initImsdk(Context context, int logLevel){
        TIMSdkConfig config = new TIMSdkConfig(Const.SDK_APPID);
        config.enableLogPrint(true).setLogLevel(TIMLogLevel.values()[logLevel]);
        //初始化imsdk
        TIMManager.getInstance().init(context, config);
        //禁止服务器自动代替上报已读
        Log.e(Const.IM, "initIMsdk");

    }

}
