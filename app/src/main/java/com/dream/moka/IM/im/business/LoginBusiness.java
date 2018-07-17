package com.dream.moka.IM.im.business;

import com.dream.moka.IM.im.event.FriendshipEvent;
import com.dream.moka.IM.im.event.MessageEvent;
import com.dream.moka.IM.im.event.RefreshEvent;
import com.dream.moka.Utils.ToastUtils;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;


/**
 * 登录
 */
public class LoginBusiness {

    private LoginBusiness() {
    }

    /**
     * 登录imsdk
     *
     * @param identify 用户id
     * @param userSig  用户签名
     * @param callBack 登录后回调
     */
    public static void loginIm(String identify, String userSig, TIMCallBack callBack) {
        initFriendCache();
        //发起登录请求
        TIMManager.getInstance().login(
                identify,
                userSig,                    //用户帐号签名，由私钥加密获得，具体请参考文档
                callBack);
    }

    /**
     * 登出imsdk
     *
     * @param callBack 登出后回调
     */
    public static void logout(TIMCallBack callBack) {
        TIMManager.getInstance().logout(callBack);
    }

    /**
     * 登录之前要初始化群和好友关系链缓存
     */
    private static void initFriendCache() {
        TIMUserConfig userConfig = new TIMUserConfig();
        userConfig.setUserStatusListener(new TIMUserStatusListener() {
            @Override
            public void onForceOffline() {
//                Log.d(TAG, "receive force offline message");
                ToastUtils.Toast_short("账号在其它端登录");
            }

            @Override
            public void onUserSigExpired() {
                ToastUtils.Toast_short("账号过期，需要重新登录");
//                票据过期，需要重新登录
//                new NotifyDialog().show(getString(R.string.tls_expire), getSupportFragmentManager(), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
////                            logout();
//                    }
//                });
            }
        })
                .setConnectionListener(new TIMConnListener() {
                    @Override
                    public void onConnected() {
                    }

                    @Override
                    public void onDisconnected(int code, String desc) {
                    }

                    @Override
                    public void onWifiNeedAuth(String name) {
                    }
                });

        //设置刷新监听
        RefreshEvent.getInstance().init(userConfig);
        FriendshipEvent.getInstance().init(userConfig);
        MessageEvent.getInstance().init(userConfig);
        TIMManager.getInstance().setUserConfig(userConfig);
    }
}
