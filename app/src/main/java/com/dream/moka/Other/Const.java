package com.dream.moka.Other;

/**
 * Created by Administrator on 2017/4/5/005.
 */
public class Const {

    public static boolean isTest = false;
    //测试环境
    public static String API_BASE_URL;
    public static String BannerUrl;
    public static boolean logSwich = false;//retrofit日志开关

    //BuildConfig.DEBUG
    static {
        if (isTest) {
            //服务器测试环境
            API_BASE_URL = "http://120.24.234.123:8080/MoCar/app/";
            BannerUrl = "http://120.24.234.123:8080";
            //内网测试环境
//            API_BASE_URL = "http://192.168.1.150:8081/mocar/app/";
//            BannerUrl = "http://192.168.1.150:8081";
        } else {
            //正式环境
//            API_BASE_URL = "http://120.78.137.190/app/";
//            BannerUrl = "http://120.78.137.190";
            API_BASE_URL = "http://admin.mokaqiche.cn/app/";
            BannerUrl = "http://admin.mokaqiche.cn";
        }
    }

    public static String[] lArray = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};
    public static final int CAMERA = 23;
    public static final int CAll = 28;
    public static final int PICTURE = 25;
    public static final int time = 5000;
    public static final String pageSize = "pageSize";
    public static final String activity = "activity";
    public static final String ok = "0";
    public static final String intentTag = "itentTag";
    public static final String webUrl = "webUrl";
    public static final String refresh = "refresh";
    public static final String title = "title";
    public static final String content = "content";
    public static final String Pwd = "pwd";
    public static final String Phone = "phone";
    public static final String finish = "finish";
    public static final String OrderPage = "OrderPage";
    public static final String orderId = "orderId";

    public static final String drviceToken = "drvice_token";

    //存储用户信息
    public static final String userId = "userId";
    public static final String userName = "userName";
    public static final String userPhone = "userPhone";
    public static final String userEmail = "userEmail";
    public static final String userHeadUrl = "userHeadUrl";
    public static final String isLogin = "isLogin";
    public static final String userSex = "userSex";
    public static final String userQq = "userqq";
    public static final String userType = "usertype";
    public static final String userToken = "userToken";
    public static final String userCar = "userCar";
    public static final String openService = "openService";
    public static final String userBalance = "userBalance";
    public static final String userScore = "userScore";
    public static final String userCouponNum = "usercouponNum";
    public static final String userSign = "userSign";

    //ErrorCode
    public static final String ERRORCODE_SUCCESS = "0";
    public static final String ERRORCODE_RELOGIN = "-10102";//token失效
    public static final String ERRORCODE_NOSET = "-20144";//未设置交易密码

    public static final String pageNo = "pageNo";

    //司机信息
    public static final String DriverStatus = "driver_status";
    public static final String Driverreject = "driver_reject";//驳回信息
    public static final String DriverName = "driver_name";
    public static final String DriverIDCard = "driver_idCard";
    public static final String DriverPhone = "driver_phone";
    public static final String DriverQq = "driver_qq";
    public static final String DriverId = "driver_id";
    public static final String DriverUrl = "driver_registerUrl";
    public static final String DriverPhoto = "driver_photo";
    public static final String DriverCity = "driver_City";
    public static final String DriverDrivingType = "driver_DrivingType";
    public static final String DriverLicenseCode = "driver_LicenseCode";
    public static final String DriverlicenseDate = "driver_licenseDate";
    public static final String DriverHomeAddress = "driver_homeAddress";
    public static final String OrderStasus = "driver_orderstatus";
    public static final String latitude = "latitude";
    public static final String longitude = "longitude";
    public static final String isUpload = "isUpload";
    public static final String keysId = "keysId";

    public static final String APP_ID = "wx563a278c479199ff";
    public static final String token = "token";

    public static final String Y_M_D = "yyyy-MM-dd";
    public static final String YMD_HM = "yyyy-MM-dd  HH:mm";
    public static final String YMD_HM2 = "yyyy.MM.dd HH:mm";
    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    public static final String Y_M = "yyyy-MM";
    public static final String NYR = "yyyy年MM月dd日";
    public static final String HM = "HH:mm";
    public static final String MD_HM = "MM月dd  HH:mm";
    public static final String DD = "dd";
    public static final String HMS = "HH:mm:ss";
    public static final String startTime = " 00:00:00";
    public static final String endTime = " 23:59:59";

    //IM
    public static final int ACCOUNT_TYPE = 22188;
    //sdk appid 由腾讯分配
    public static final int SDK_APPID = 1400064402;
    public static final String IM = "IM";

    public static final String free = "free";//自选
    public static final String onekey = "onekey";//0.1km保养
    public static final String weixiu = "weixiu";//维修
    public static final String coinSpray = "coinSpray";//钣喷
    public static final String insurance = "insurance";//保险

    public static final String locationUrl = BannerUrl+"/MoCarDriver/setDriverHisRedis";

    public static final String platformTell = "platformTell";//平台电话
    public static final String helpTell = "helpTell";//平台电话

}
