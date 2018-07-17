package com.dream.moka.Other;

import com.dream.moka.Bean.DriverResultBean;
import com.dream.moka.Bean.EventBusBean.LoginBus;
import com.dream.moka.Bean.UserResultBean;
import com.dream.moka.Utils.IMUtil.IMAcountUtils;
import com.dream.moka.Utils.SpUtils;
import com.tencent.android.tpush.XGPushManager;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/8/8 0008.
 */
public class CommonAction {

    public static String getDrviceToken() {
        return (String) SpUtils.getParam(Const.drviceToken, "");
    }

    public static void setDrviceToken(String token) {
        SpUtils.setParam(Const.drviceToken, token);
    }

    /**
     * 用户信息
     */

    public static String getToken() {
        return (String) SpUtils.getParam(Const.userToken, "");
    }

    public static String getUserId() {
        return (String) SpUtils.getParam(Const.userId, "");
    }
    public static String getUserSign() {
        return (String) SpUtils.getParam(Const.userSign, "");
    }

    public static String getUserQQ() {
        return (String) SpUtils.getParam(Const.userQq, "");
    }

    public static String getUserName() {
        return (String) SpUtils.getParam(Const.userName, "");
    }

    public static String getUserHeadUrl() {
        return (String) SpUtils.getParam(Const.userHeadUrl, "");
    }

    public static String getUserSex() {
        return (String) SpUtils.getParam(Const.userSex, "");
    }

    public static String getUserPhone() {
        return (String) SpUtils.getParam(Const.userPhone, "");
    }

    public static String getUserType() {
        return (String) SpUtils.getParam(Const.userType, "");
    }

    public static String getUserCarName() {
        return (String) SpUtils.getParam(Const.userCar, "");
    }

    public static String getLatitude() {
        return (String) SpUtils.getParam(Const.latitude, "");
    }

    public static String getLongitude() {
        return (String) SpUtils.getParam(Const.longitude, "");
    }

    public static String getUserBalance() {
        return (String) SpUtils.getParam(Const.userBalance, "");
    }

    public static String getUserCouponNum() {
        return (String) SpUtils.getParam(Const.userCouponNum, "");
    }
    public static String getPlatPhone() {
        return (String) SpUtils.getParam(Const.platformTell, "");
    }
    public static String getHelpPhone() {
        return (String) SpUtils.getParam(Const.helpTell, "");
    }

    public static String getUserScore() {
        return (String) SpUtils.getParam(Const.userScore, "");
    }
    public static String getKeysId() {
        return (String) SpUtils.getParam(Const.keysId, "");
    }

    public static boolean getIsLogin() {
        return (boolean) SpUtils.getParam(Const.isLogin, false);
    }
    public static boolean getIsUpLoad() {
        return (boolean) SpUtils.getParam(Const.isUpload, false);
    }
    public static void setIsUpLoad() {
        SpUtils.setParam(Const.isUpload, false);
    }

    public static boolean getIsOpenService() {
        if (SpUtils.getParam(Const.openService, "0").equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 司机信息获取
     */
    public static String getDriverReject() {
        return (String) SpUtils.getParam(Const.Driverreject, "");
    }

    public static String getDriverStatus() {
        return (String) SpUtils.getParam(Const.DriverStatus, "");
    }

    public static String getDriverName() {
        return (String) SpUtils.getParam(Const.DriverName, "");
    }

    public static String getDriverFirstStr() {
        String driverName = (String) SpUtils.getParam(Const.DriverName, "");
        if (!driverName.isEmpty() && driverName.length() > 0) {
            if (driverName.length() == 1) {
                return driverName;
            } else {
                return driverName.substring(0, 1);
            }
        }
        return driverName;
    }

    public static String getDriverPhone() {
        return (String) SpUtils.getParam(Const.DriverPhone, "");
    }

    public static String getDriverQQ() {
        return (String) SpUtils.getParam(Const.DriverQq, "");
    }

    public static String getDriverIdCard() {
        return (String) SpUtils.getParam(Const.DriverIDCard, "");
    }

    public static String getDriverId() {
        return (String) SpUtils.getParam(Const.DriverId, "");
    }

    public static String getDriverPhoto() {
        return (String) SpUtils.getParam(Const.DriverPhoto, "");
    }

    public static String getDriverCity() {
        return (String) SpUtils.getParam(Const.DriverCity, "");
    }

    public static String getDriverDrivingType() {
        return (String) SpUtils.getParam(Const.DriverDrivingType, "");
    }

    public static String getDriverLicenseCode() {
        return (String) SpUtils.getParam(Const.DriverLicenseCode, "");
    }

    public static String getDriverlicenseDate() {
        return (String) SpUtils.getParam(Const.DriverlicenseDate, "");
    }

    public static String getRegistDriverUrl() {
        return (String) SpUtils.getParam(Const.DriverUrl, "");
    }

    public static String getDriverHomeAddress() {
        return (String) SpUtils.getParam(Const.DriverHomeAddress, "");
    }

    public static String getDriverOrderStatus() {
        return (String) SpUtils.getParam(Const.OrderStasus, "");
    }


    public static void saveUserData(UserResultBean resultBean) {
        if (resultBean != null) {
            SpUtils.savaUserInfo(Const.userPhone, resultBean.getPhone());
            SpUtils.savaUserInfo(Const.userToken, resultBean.getToken());
            SpUtils.savaUserInfo(Const.userId, resultBean.getId());
            SpUtils.savaUserInfo(Const.userName, resultBean.getName());
            SpUtils.savaUserInfo(Const.userHeadUrl, resultBean.getPhoto());
            SpUtils.savaUserInfo(Const.userEmail, resultBean.getEmail());
            SpUtils.savaUserInfo(Const.userCar, resultBean.getCarName());
            SpUtils.savaUserInfo(Const.userSex, resultBean.getSex());
            SpUtils.savaUserInfo(Const.userQq, resultBean.getQq());
            SpUtils.savaUserInfo(Const.userType, resultBean.getUserTypes());
            SpUtils.savaUserInfo(Const.openService, resultBean.getOpenService());
            SpUtils.savaUserInfo(Const.isLogin, true);
            SpUtils.savaUserInfo(Const.userBalance, resultBean.getBalance());
            SpUtils.savaUserInfo(Const.userScore, resultBean.getScore());
            SpUtils.savaUserInfo(Const.userCouponNum, resultBean.getCouponNum());
            SpUtils.savaUserInfo(Const.userSign, resultBean.getSign());
            XGPushManager.registerPush(MyApplication.getInstance(),resultBean.getToken());
            EventBus.getDefault().post(new LoginBus());
            IMAcountUtils.loginSig(CommonAction.getUserId(),CommonAction.getUserSign());
        }
    }

    //退出登录
    public static void clearUserData() {
        SpUtils.savaUserInfo(Const.userPhone, "");
        SpUtils.savaUserInfo(Const.userToken, "");
        SpUtils.savaUserInfo(Const.userId, "");
        SpUtils.savaUserInfo(Const.userName, "");
        SpUtils.savaUserInfo(Const.userHeadUrl, "");
        SpUtils.savaUserInfo(Const.userEmail, "");
        SpUtils.savaUserInfo(Const.userCar, "");
        SpUtils.savaUserInfo(Const.userSex, "");
        SpUtils.savaUserInfo(Const.userQq, "");
        SpUtils.savaUserInfo(Const.userType, "");
        SpUtils.savaUserInfo(Const.userBalance, "0");
        SpUtils.savaUserInfo(Const.userScore, "0");
        SpUtils.savaUserInfo(Const.userCouponNum, "0");
        SpUtils.savaUserInfo(Const.isLogin, false);
        SpUtils.savaUserInfo(Const.isUpload, false);

        SpUtils.savaUserInfo(Const.DriverStatus, "");
        SpUtils.savaUserInfo(Const.OrderStasus, "");
        SpUtils.savaUserInfo(Const.Driverreject, "");
        SpUtils.savaUserInfo(Const.DriverName, "");
        SpUtils.savaUserInfo(Const.DriverIDCard, "");
        SpUtils.savaUserInfo(Const.DriverPhone, "");
        SpUtils.savaUserInfo(Const.DriverQq, "");
        SpUtils.savaUserInfo(Const.DriverId, "");
        SpUtils.savaUserInfo(Const.DriverUrl, "");
        SpUtils.savaUserInfo(Const.DriverPhoto, "");
        SpUtils.savaUserInfo(Const.DriverCity, "");
        SpUtils.savaUserInfo(Const.DriverDrivingType, "");
        SpUtils.savaUserInfo(Const.DriverLicenseCode, "");
        SpUtils.savaUserInfo(Const.DriverlicenseDate, "");
        SpUtils.savaUserInfo(Const.DriverHomeAddress, "");
        SpUtils.savaUserInfo(Const.openService, "");
        XGPushManager.unregisterPush(MyApplication.getInstance());

        //退出登录后刷新ui
        EventBus.getDefault().post(new LoginBus());

    }

    public static void saveDriverData(DriverResultBean driverResultBean) {
        SpUtils.savaUserInfo(Const.OrderStasus, driverResultBean.getOrderStatus());
        SpUtils.savaUserInfo(Const.DriverStatus, driverResultBean.getStatus());
        SpUtils.savaUserInfo(Const.Driverreject, driverResultBean.getReject());
        SpUtils.savaUserInfo(Const.DriverName, driverResultBean.getDriverName());
        SpUtils.savaUserInfo(Const.DriverIDCard, driverResultBean.getIdCard());
        SpUtils.savaUserInfo(Const.DriverPhone, driverResultBean.getPhone());
        SpUtils.savaUserInfo(Const.DriverQq, driverResultBean.getQq());
        SpUtils.savaUserInfo(Const.DriverId, driverResultBean.getId());
        SpUtils.savaUserInfo(Const.DriverUrl, driverResultBean.getRegisterUrl());

        SpUtils.savaUserInfo(Const.DriverPhoto, driverResultBean.getPhoto());
        SpUtils.savaUserInfo(Const.DriverCity, driverResultBean.getCity());
        SpUtils.savaUserInfo(Const.DriverDrivingType, driverResultBean.getDrivingType());
        SpUtils.savaUserInfo(Const.DriverLicenseCode, driverResultBean.getLicenseCode());
        SpUtils.savaUserInfo(Const.DriverlicenseDate, driverResultBean.getLicenseDate());
        SpUtils.savaUserInfo(Const.DriverHomeAddress, driverResultBean.getHomeAddress());

    }

}
