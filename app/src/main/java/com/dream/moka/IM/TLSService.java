package com.dream.moka.IM;

import android.content.Context;

import tencent.tls.platform.TLSAccountHelper;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSGuestLoginListener;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSRefreshUserSigListener;
import tencent.tls.platform.TLSSmsLoginListener;
import tencent.tls.platform.TLSStrAccRegListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * Created by dgy on 15/7/8.
 */
public class TLSService {

    private TLSLoginHelper loginHelper;
    private TLSAccountHelper accountHelper;

    private static int lastErrno = -1;

    private static TLSService tlsService = null;

    private TLSService(){}

    private RefreshUserSigListener refreshUserSigListener = new RefreshUserSigListener() {
        @Override
        public void onUserSigNotExist() {}
        @Override
        public void OnRefreshUserSigSuccess(TLSUserInfo tlsUserInfo) {}
        @Override
        public void OnRefreshUserSigFail(TLSErrInfo tlsErrInfo) {}
        @Override
        public void OnRefreshUserSigTimeout(TLSErrInfo tlsErrInfo) {}
    };

    private GuestLoginListener guestLoginListener = new GuestLoginListener() {
        @Override
        public void OnGuestLoginSuccess(TLSUserInfo userInfo) {}

        @Override
        public void OnGuestLoginFail(TLSErrInfo errInfo) {}

        @Override
        public void OnGuestLoginTimeout(TLSErrInfo errInfo) {}
    };

    public static TLSService getInstance() {
        if (tlsService == null) {
            tlsService = new TLSService();
        }
        return tlsService;
    }

    /**
     * @function: 初始化TLS SDK, 必须在使用TLS SDK相关服务之前调用
     * @param context: 关联的activity
     * */
    public void initTlsSdk(Context context) {
//        QALSDKManager.getInstance().init(context.getApplicationContext(), (int)TLSConfiguration.SDK_APPID);
//        QALSDKManager.getInstance().setEnv(0);     // 0: sso正式环境 1: sso测试环境, 即beta环境

//        TIMManager.getInstance().init(context.getApplicationContext());
//        TIMManager.getInstance().setEnv(1);

        loginHelper = TLSLoginHelper.getInstance().init(context.getApplicationContext(),
                TLSConfiguration.SDK_APPID, TLSConfiguration.ACCOUNT_TYPE, TLSConfiguration.APP_VERSION);
        loginHelper.setTimeOut(TLSConfiguration.TIMEOUT);
        loginHelper.setLocalId(TLSConfiguration.LANGUAGE_CODE);
        loginHelper.setTestHost("", true);                   // 走sso
//        loginHelper.setTestHost("113.108.64.238", false);      // 不走sso

        accountHelper = TLSAccountHelper.getInstance().init(context.getApplicationContext(),
                TLSConfiguration.SDK_APPID, TLSConfiguration.ACCOUNT_TYPE, TLSConfiguration.APP_VERSION);
        accountHelper.setCountry(Integer.parseInt(TLSConfiguration.COUNTRY_CODE)); // 存储注册时所在国家，只须在初始化时调用一次
        accountHelper.setTimeOut(TLSConfiguration.TIMEOUT);
        accountHelper.setLocalId(TLSConfiguration.LANGUAGE_CODE);
        accountHelper.setTestHost("", true);                 // 走sso
//        accountHelper.setTestHost("113.108.64.238", false);    // 不走sso

    }

    public int TLSStrAccReg(String account, String password, TLSStrAccRegListener listener) {
        return accountHelper.TLSStrAccReg(account, password, listener);
    }
    public static void setLastErrno(int errno) {
        lastErrno = errno;
    }

    public String getUserSig(String identify) {
        return loginHelper.getUserSig(identify);
    }

    public String getLastUserIdentifier() {
        TLSUserInfo userInfo = getLastUserInfo();
        if (userInfo != null)
            return userInfo.identifier;
        else
            return null;
    }
    public TLSUserInfo getLastUserInfo() {
        return loginHelper.getLastUserInfo();
    }

    public void clearUserInfo(String identifier) {
        loginHelper.clearUserInfo(identifier);
        lastErrno = -1;
    }

    public boolean needLogin(String identifier) {
        if (identifier == null)
            return true;
        return loginHelper.needLogin(identifier);
    }


    public interface RefreshUserSigListener extends TLSRefreshUserSigListener {
        public void onUserSigNotExist();
    }

    public interface GuestLoginListener extends TLSGuestLoginListener {}

    public int smsLogin(String countryCode, String phoneNumber, TLSSmsLoginListener smsLoginListener) {
        return loginHelper.TLSSmsLogin(Util.getWellFormatMobile(countryCode, phoneNumber), smsLoginListener);
    }

    public int smsLoginAskCode(String countryCode, String phoneNumber, TLSSmsLoginListener listener) {
        return loginHelper.TLSSmsLoginAskCode(Util.getWellFormatMobile(countryCode, phoneNumber), listener);
    }

    public int smsLoginVerifyCode(String code, TLSSmsLoginListener listener) {
        return loginHelper.TLSSmsLoginVerifyCode(code, listener);
    }

}
