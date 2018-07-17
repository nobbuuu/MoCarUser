package com.dream.moka.Other;

import android.content.Context;
import android.graphics.Typeface;
import android.support.multidex.MultiDex;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.dream.moka.Component.AppComponent;
import com.dream.moka.Component.DaggerAppComponent;
import com.dream.moka.IM.Foreground;
import com.dream.moka.IM.InitBusiness;
import com.dream.moka.Module.AppModule;
import com.dream.moka.Module.BaseApiModule;
import com.dream.moka.R;
import com.dream.moka.Utils.AppUtils;
import com.dream.moka.Utils.LogUtils;
import com.dream.moka.Utils.ToastUtils;
import com.mob.MobApplication;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.imsdk.TIMGroupReceiveMessageOpt;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMOfflinePushListener;
import com.tencent.imsdk.TIMOfflinePushNotification;
import com.tencent.imsdk.TIMUserStatusListener;
import com.tencent.qalsdk.sdk.MsfSdkUtils;

/**
 * Created by Administrator on 2017/4/5/005.
 */
public class MyApplication extends MobApplication {

    public static boolean isAppIsInBackground = false;
    private static MyApplication context;
    private static Typeface typeface_h, typeface_xh;
    private AppComponent appComponent;
    private static String szImei;

    public static MyApplication getInstance() {
        if (null == context) {
            context = new MyApplication();
        }
        return context;
    }

    public static Typeface getTypeface_fzlth() {
        if (null == typeface_h) {
            typeface_h = Typeface.createFromAsset(context.getAssets(), "fonts/fzlth.ttf");
        }
        return typeface_h;
    }

    public static Typeface getTypeface_fzltxh() {
        if (null == typeface_xh) {
            typeface_xh = Typeface.createFromAsset(context.getAssets(), "fonts/fzltxh.ttf");
        }
        return typeface_xh;
    }

    public static String getDeviceId() {
        if (null == szImei) {
            //设备id
            TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
            szImei = TelephonyMgr.getDeviceId();
        }
        return szImei;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initCompoent();
        AppUtils.init(this);
        XGPushConfig.enableDebug(this, LogUtils.LOG_SWITCH);
//        MobSDK.init(this, "20f4091be79c7", "414be537e9515d5837e78590bd73bac8");
//        ShareSDK.initSDK(this);
//        SpeechUtility.createUtility(context, SpeechConstant.APPID + "=598a8250");
        InitBusiness.start(this);
        initIm();
        registerActivityLifecycleCallbacks(Foreground.get());

    }

    private void initIm() {
        //设置用户状态变更监听器，在回调中进行相应的处理
        TIMManager.getInstance().getUserConfig().setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
                ToastUtils.Toast_short("账号在其他设备登录");
            }

            @Override
            public void onUserSigExpired() {
                //票据过期，需要换票后重新登录
                ToastUtils.Toast_short("请重新登录");
            }
        });

        if (MsfSdkUtils.isMainProcess(this)) {
            TIMManager.getInstance().setOfflinePushListener(new TIMOfflinePushListener() {
                @Override
                public void handleNotification(TIMOfflinePushNotification notification) {
                    if (notification.getGroupReceiveMsgOpt() == TIMGroupReceiveMessageOpt.ReceiveAndNotify) {
                        //消息被设置为需要提醒
                        notification.doNotify(getApplicationContext(), R.mipmap.logo);

                    }
                }
            });
        }
    }

    private void initCompoent() {
        appComponent = DaggerAppComponent.builder()
                .baseApiModule(new BaseApiModule())
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }


    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
