package com.dream.moka.Bean;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class InTheRelayResultBean {

    /**
     * order : {"startLongitude":"113.9401645837758","isBookEnd":"","backDriverIncome":"","orderCode":"201801221533443169mt","technicianId":"","repairShopIncome":"","increaseStatus":"","repairShopScore":"","sendDriverIncome":"","bookEndTime":"","orderAmount":"1188.00","isNewRecord":false,"finishTime":"","driverStatus":"","bookSendTime":"","technicianIncome":"","backDriverScore":"","jcStartTime":"2018-01-22 15:37:21","startLatitude":"22.55447482356677","status":4,"orderType":1,"sendTime":"2018-01-22 15:38:05","miles":5000,"transNo":"4200000058201801228644084156","scSendTime":"","evaluateTime":"","sendDriverScore":"","increaseType":"","transChannel":"1","userCouponId":"","delFlag":"0","technicianScore":"","carId":"c60df04e38a546b5bdea20958dae0d6f","jcSendTime":"2018-01-22 15:34:03","remarks":"","expectedDate":"3.50","sendDriverId":"6818c75c0209451fb00a4d094a22bfc0","lastLatitude":"22.55447482356677","serviceName":"","id":"cf025392c37e48fcb4b5ea2106b8ca6b","backTime":"","backDriverId":"","sendMobile":"15112497075","backMobile":"15112497075","createDate":"2018-01-22 15:33:44","isBook":0,"payAmount":"0.01","sendAddress":"西丽街道高新北六道38号","sendName":"曹先生","updateDate":"2018-01-22 15:38:05","repairShopId":"88e3410405014277943872bdfcbcf7e6","backName":"曹先生","backAddress":"西丽街道高新北六道38号","lastLongitude":"113.9401645837758","scStartTime":"","detailId":""}
     * driverHisId : db5c99e6b3c54609a2c433dc0a1a5302
     * user : {"sex":"","phone":"","newPassword":"","userDetailId":"","score":"","remarks":"","recomUserId":"","recomCode":"","blog":"","password":"","roleNames":"","id":"665be1cd2ca14b4f958096eaa7685684","balance":"","loginIp":"","token":"","name":"15112497075","isNewRecord":false,"carName":"","loginDate":"","xgType":"","createDate":"","qq":"","xgToken":"","loginName":"","wechart":"","oldLoginName":"","userTypes":"","oldLoginDate":"","openService":"","loginFlag":"1","photo":"","updateDate":"","sign":"","oldLoginIp":"","signCreateTime":"","email":"","admin":false,"delFlag":"0","mobile":""}
     * userCar : {"lastMainDate":"","idNo":"","registerCity":"","mileage":"","status":"","registerDate":"","vinNo":"","carname":"奥迪A32016款 Sportback 35TFSI 进取型","remarks":"","buyDate":"","insuranceName":"","updateDate":"","cardNo":"粤b45666","id":"c60df04e38a546b5bdea20958dae0d6f","recomMaintMile":"","insuranceDate":"","owner":"","isNewRecord":false,"insuranceId":"","carConfigureId":"","createDate":"","delFlag":"0","drivingLicense":""}
     * userTechnician :
     */

    private OrderBean order;
    private String driverHisId;
    private UserBean user;
    private UserCarBean userCar;
    private UserTechnicianBean userTechnician;
    private DriverHomeResultBean.repairShopBean repairShop;

    public DriverHomeResultBean.repairShopBean getRepairShop() {
        return repairShop;
    }

    public void setRepairShop(DriverHomeResultBean.repairShopBean repairShop) {
        this.repairShop = repairShop;
    }

    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public String getDriverHisId() {
        return driverHisId;
    }

    public void setDriverHisId(String driverHisId) {
        this.driverHisId = driverHisId;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public UserTechnicianBean getUserTechnician() {
        return userTechnician;
    }

    public void setUserTechnician(UserTechnicianBean userTechnician) {
        this.userTechnician = userTechnician;
    }

    public static class UserTechnicianBean{

        /**
         * sex :
         * phone :
         * newPassword :
         * userDetailId :
         * score :
         * remarks :
         * recomUserId :
         * recomCode :
         * blog :
         * password :
         * roleNames :
         * id :
         * balance :
         * loginIp :
         * token :
         * name :
         * isNewRecord : true
         * carName :
         * loginDate :
         * xgType :
         * createDate :
         * qq :
         * xgToken :
         * loginName :
         * wechart :
         * oldLoginName :
         * userTypes :
         * oldLoginDate :
         * openService :
         * loginFlag : 1
         * photo :
         * updateDate :
         * sign :
         * oldLoginIp :
         * signCreateTime :
         * email :
         * admin : false
         * delFlag : 0
         * mobile :
         */

        private String sex;
        private String phone;
        private String newPassword;
        private String userDetailId;
        private String score;
        private String remarks;
        private String recomUserId;
        private String recomCode;
        private String blog;
        private String password;
        private String roleNames;
        private String id;
        private String balance;
        private String loginIp;
        private String token;
        private String name;
        private boolean isNewRecord;
        private String carName;
        private String loginDate;
        private String xgType;
        private String createDate;
        private String qq;
        private String xgToken;
        private String loginName;
        private String wechart;
        private String oldLoginName;
        private String userTypes;
        private String oldLoginDate;
        private String openService;
        private String loginFlag;
        private String photo;
        private String updateDate;
        private String sign;
        private String oldLoginIp;
        private String signCreateTime;
        private String email;
        private boolean admin;
        private String delFlag;
        private String mobile;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getUserDetailId() {
            return userDetailId;
        }

        public void setUserDetailId(String userDetailId) {
            this.userDetailId = userDetailId;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getRecomUserId() {
            return recomUserId;
        }

        public void setRecomUserId(String recomUserId) {
            this.recomUserId = recomUserId;
        }

        public String getRecomCode() {
            return recomCode;
        }

        public void setRecomCode(String recomCode) {
            this.recomCode = recomCode;
        }

        public String getBlog() {
            return blog;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public String getXgType() {
            return xgType;
        }

        public void setXgType(String xgType) {
            this.xgType = xgType;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getXgToken() {
            return xgToken;
        }

        public void setXgToken(String xgToken) {
            this.xgToken = xgToken;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getWechart() {
            return wechart;
        }

        public void setWechart(String wechart) {
            this.wechart = wechart;
        }

        public String getOldLoginName() {
            return oldLoginName;
        }

        public void setOldLoginName(String oldLoginName) {
            this.oldLoginName = oldLoginName;
        }

        public String getUserTypes() {
            return userTypes;
        }

        public void setUserTypes(String userTypes) {
            this.userTypes = userTypes;
        }

        public String getOldLoginDate() {
            return oldLoginDate;
        }

        public void setOldLoginDate(String oldLoginDate) {
            this.oldLoginDate = oldLoginDate;
        }

        public String getOpenService() {
            return openService;
        }

        public void setOpenService(String openService) {
            this.openService = openService;
        }

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getOldLoginIp() {
            return oldLoginIp;
        }

        public void setOldLoginIp(String oldLoginIp) {
            this.oldLoginIp = oldLoginIp;
        }

        public String getSignCreateTime() {
            return signCreateTime;
        }

        public void setSignCreateTime(String signCreateTime) {
            this.signCreateTime = signCreateTime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
    public static class OrderBean {
        /**
         * startLongitude : 113.9401645837758
         * isBookEnd :
         * backDriverIncome :
         * orderCode : 201801221533443169mt
         * technicianId :
         * repairShopIncome :
         * increaseStatus :
         * repairShopScore :
         * sendDriverIncome :
         * bookEndTime :
         * orderAmount : 1188.00
         * isNewRecord : false
         * finishTime :
         * driverStatus :
         * bookSendTime :
         * technicianIncome :
         * backDriverScore :
         * jcStartTime : 2018-01-22 15:37:21
         * startLatitude : 22.55447482356677
         * status : 4
         * orderType : 1
         * sendTime : 2018-01-22 15:38:05
         * miles : 5000
         * transNo : 4200000058201801228644084156
         * scSendTime :
         * evaluateTime :
         * sendDriverScore :
         * increaseType :
         * transChannel : 1
         * userCouponId :
         * delFlag : 0
         * technicianScore :
         * carId : c60df04e38a546b5bdea20958dae0d6f
         * jcSendTime : 2018-01-22 15:34:03
         * remarks :
         * expectedDate : 3.50
         * sendDriverId : 6818c75c0209451fb00a4d094a22bfc0
         * lastLatitude : 22.55447482356677
         * serviceName :
         * id : cf025392c37e48fcb4b5ea2106b8ca6b
         * backTime :
         * backDriverId :
         * sendMobile : 15112497075
         * backMobile : 15112497075
         * createDate : 2018-01-22 15:33:44
         * isBook : 0
         * payAmount : 0.01
         * sendAddress : 西丽街道高新北六道38号
         * sendName : 曹先生
         * updateDate : 2018-01-22 15:38:05
         * repairShopId : 88e3410405014277943872bdfcbcf7e6
         * backName : 曹先生
         * backAddress : 西丽街道高新北六道38号
         * lastLongitude : 113.9401645837758
         * scStartTime :
         * detailId :
         */

        private String startLongitude;
        private String isBookEnd;
        private String backDriverIncome;
        private String orderCode;
        private String technicianId;
        private String repairShopIncome;
        private String increaseStatus;
        private String repairShopScore;
        private String sendDriverIncome;
        private String bookEndTime;
        private String orderAmount;
        private boolean isNewRecord;
        private String finishTime;
        private String driverStatus;
        private String bookSendTime;
        private String technicianIncome;
        private String backDriverScore;
        private String jcStartTime;
        private String startLatitude;
        private String status;
        private String orderType;
        private String sendTime;
        private String miles;
        private String transNo;
        private String scSendTime;
        private String evaluateTime;
        private String sendDriverScore;
        private String increaseType;
        private String transChannel;
        private String userCouponId;
        private String delFlag;
        private String technicianScore;
        private String carId;
        private String jcSendTime;
        private String remarks;
        private String expectedDate;
        private String sendDriverId;
        private String lastLatitude;
        private String serviceName;
        private String id;
        private String backTime;
        private String backDriverId;
        private String sendMobile;
        private String backMobile;
        private String createDate;
        private String isBook;
        private String payAmount;
        private String sendAddress;
        private String sendName;
        private String updateDate;
        private String repairShopId;
        private String backName;
        private String backAddress;
        private String lastLongitude;
        private String scStartTime;
        private String detailId;

        public String getStartLongitude() {
            return startLongitude;
        }

        public void setStartLongitude(String startLongitude) {
            this.startLongitude = startLongitude;
        }

        public String getIsBookEnd() {
            return isBookEnd;
        }

        public void setIsBookEnd(String isBookEnd) {
            this.isBookEnd = isBookEnd;
        }

        public String getBackDriverIncome() {
            return backDriverIncome;
        }

        public void setBackDriverIncome(String backDriverIncome) {
            this.backDriverIncome = backDriverIncome;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getTechnicianId() {
            return technicianId;
        }

        public void setTechnicianId(String technicianId) {
            this.technicianId = technicianId;
        }

        public String getRepairShopIncome() {
            return repairShopIncome;
        }

        public void setRepairShopIncome(String repairShopIncome) {
            this.repairShopIncome = repairShopIncome;
        }

        public String getIncreaseStatus() {
            return increaseStatus;
        }

        public void setIncreaseStatus(String increaseStatus) {
            this.increaseStatus = increaseStatus;
        }

        public String getRepairShopScore() {
            return repairShopScore;
        }

        public void setRepairShopScore(String repairShopScore) {
            this.repairShopScore = repairShopScore;
        }

        public String getSendDriverIncome() {
            return sendDriverIncome;
        }

        public void setSendDriverIncome(String sendDriverIncome) {
            this.sendDriverIncome = sendDriverIncome;
        }

        public String getBookEndTime() {
            return bookEndTime;
        }

        public void setBookEndTime(String bookEndTime) {
            this.bookEndTime = bookEndTime;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getDriverStatus() {
            return driverStatus;
        }

        public void setDriverStatus(String driverStatus) {
            this.driverStatus = driverStatus;
        }

        public String getBookSendTime() {
            return bookSendTime;
        }

        public void setBookSendTime(String bookSendTime) {
            this.bookSendTime = bookSendTime;
        }

        public String getTechnicianIncome() {
            return technicianIncome;
        }

        public void setTechnicianIncome(String technicianIncome) {
            this.technicianIncome = technicianIncome;
        }

        public String getBackDriverScore() {
            return backDriverScore;
        }

        public void setBackDriverScore(String backDriverScore) {
            this.backDriverScore = backDriverScore;
        }

        public String getJcStartTime() {
            return jcStartTime;
        }

        public void setJcStartTime(String jcStartTime) {
            this.jcStartTime = jcStartTime;
        }

        public String getStartLatitude() {
            return startLatitude;
        }

        public void setStartLatitude(String startLatitude) {
            this.startLatitude = startLatitude;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getMiles() {
            return miles;
        }

        public void setMiles(String miles) {
            this.miles = miles;
        }

        public String getTransNo() {
            return transNo;
        }

        public void setTransNo(String transNo) {
            this.transNo = transNo;
        }

        public String getScSendTime() {
            return scSendTime;
        }

        public void setScSendTime(String scSendTime) {
            this.scSendTime = scSendTime;
        }

        public String getEvaluateTime() {
            return evaluateTime;
        }

        public void setEvaluateTime(String evaluateTime) {
            this.evaluateTime = evaluateTime;
        }

        public String getSendDriverScore() {
            return sendDriverScore;
        }

        public void setSendDriverScore(String sendDriverScore) {
            this.sendDriverScore = sendDriverScore;
        }

        public String getIncreaseType() {
            return increaseType;
        }

        public void setIncreaseType(String increaseType) {
            this.increaseType = increaseType;
        }

        public String getTransChannel() {
            return transChannel;
        }

        public void setTransChannel(String transChannel) {
            this.transChannel = transChannel;
        }

        public String getUserCouponId() {
            return userCouponId;
        }

        public void setUserCouponId(String userCouponId) {
            this.userCouponId = userCouponId;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getTechnicianScore() {
            return technicianScore;
        }

        public void setTechnicianScore(String technicianScore) {
            this.technicianScore = technicianScore;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getJcSendTime() {
            return jcSendTime;
        }

        public void setJcSendTime(String jcSendTime) {
            this.jcSendTime = jcSendTime;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getExpectedDate() {
            return expectedDate;
        }

        public void setExpectedDate(String expectedDate) {
            this.expectedDate = expectedDate;
        }

        public String getSendDriverId() {
            return sendDriverId;
        }

        public void setSendDriverId(String sendDriverId) {
            this.sendDriverId = sendDriverId;
        }

        public String getLastLatitude() {
            return lastLatitude;
        }

        public void setLastLatitude(String lastLatitude) {
            this.lastLatitude = lastLatitude;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBackTime() {
            return backTime;
        }

        public void setBackTime(String backTime) {
            this.backTime = backTime;
        }

        public String getBackDriverId() {
            return backDriverId;
        }

        public void setBackDriverId(String backDriverId) {
            this.backDriverId = backDriverId;
        }

        public String getSendMobile() {
            return sendMobile;
        }

        public void setSendMobile(String sendMobile) {
            this.sendMobile = sendMobile;
        }

        public String getBackMobile() {
            return backMobile;
        }

        public void setBackMobile(String backMobile) {
            this.backMobile = backMobile;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getIsBook() {
            return isBook;
        }

        public void setIsBook(String isBook) {
            this.isBook = isBook;
        }

        public String getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(String payAmount) {
            this.payAmount = payAmount;
        }

        public String getSendAddress() {
            return sendAddress;
        }

        public void setSendAddress(String sendAddress) {
            this.sendAddress = sendAddress;
        }

        public String getSendName() {
            return sendName;
        }

        public void setSendName(String sendName) {
            this.sendName = sendName;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getRepairShopId() {
            return repairShopId;
        }

        public void setRepairShopId(String repairShopId) {
            this.repairShopId = repairShopId;
        }

        public String getBackName() {
            return backName;
        }

        public void setBackName(String backName) {
            this.backName = backName;
        }

        public String getBackAddress() {
            return backAddress;
        }

        public void setBackAddress(String backAddress) {
            this.backAddress = backAddress;
        }

        public String getLastLongitude() {
            return lastLongitude;
        }

        public void setLastLongitude(String lastLongitude) {
            this.lastLongitude = lastLongitude;
        }

        public String getScStartTime() {
            return scStartTime;
        }

        public void setScStartTime(String scStartTime) {
            this.scStartTime = scStartTime;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }
    }

    public static class UserBean {
        /**
         * sex :
         * phone :
         * newPassword :
         * userDetailId :
         * score :
         * remarks :
         * recomUserId :
         * recomCode :
         * blog :
         * password :
         * roleNames :
         * id : 665be1cd2ca14b4f958096eaa7685684
         * balance :
         * loginIp :
         * token :
         * name : 15112497075
         * isNewRecord : false
         * carName :
         * loginDate :
         * xgType :
         * createDate :
         * qq :
         * xgToken :
         * loginName :
         * wechart :
         * oldLoginName :
         * userTypes :
         * oldLoginDate :
         * openService :
         * loginFlag : 1
         * photo :
         * updateDate :
         * sign :
         * oldLoginIp :
         * signCreateTime :
         * email :
         * admin : false
         * delFlag : 0
         * mobile :
         */

        private String sex;
        private String phone;
        private String newPassword;
        private String userDetailId;
        private String score;
        private String remarks;
        private String recomUserId;
        private String recomCode;
        private String blog;
        private String password;
        private String roleNames;
        private String id;
        private String balance;
        private String loginIp;
        private String token;
        private String name;
        private boolean isNewRecord;
        private String carName;
        private String loginDate;
        private String xgType;
        private String createDate;
        private String qq;
        private String xgToken;
        private String loginName;
        private String wechart;
        private String oldLoginName;
        private String userTypes;
        private String oldLoginDate;
        private String openService;
        private String loginFlag;
        private String photo;
        private String updateDate;
        private String sign;
        private String oldLoginIp;
        private String signCreateTime;
        private String email;
        private boolean admin;
        private String delFlag;
        private String mobile;

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getUserDetailId() {
            return userDetailId;
        }

        public void setUserDetailId(String userDetailId) {
            this.userDetailId = userDetailId;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getRecomUserId() {
            return recomUserId;
        }

        public void setRecomUserId(String recomUserId) {
            this.recomUserId = recomUserId;
        }

        public String getRecomCode() {
            return recomCode;
        }

        public void setRecomCode(String recomCode) {
            this.recomCode = recomCode;
        }

        public String getBlog() {
            return blog;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public String getXgType() {
            return xgType;
        }

        public void setXgType(String xgType) {
            this.xgType = xgType;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getXgToken() {
            return xgToken;
        }

        public void setXgToken(String xgToken) {
            this.xgToken = xgToken;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getWechart() {
            return wechart;
        }

        public void setWechart(String wechart) {
            this.wechart = wechart;
        }

        public String getOldLoginName() {
            return oldLoginName;
        }

        public void setOldLoginName(String oldLoginName) {
            this.oldLoginName = oldLoginName;
        }

        public String getUserTypes() {
            return userTypes;
        }

        public void setUserTypes(String userTypes) {
            this.userTypes = userTypes;
        }

        public String getOldLoginDate() {
            return oldLoginDate;
        }

        public void setOldLoginDate(String oldLoginDate) {
            this.oldLoginDate = oldLoginDate;
        }

        public String getOpenService() {
            return openService;
        }

        public void setOpenService(String openService) {
            this.openService = openService;
        }

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getOldLoginIp() {
            return oldLoginIp;
        }

        public void setOldLoginIp(String oldLoginIp) {
            this.oldLoginIp = oldLoginIp;
        }

        public String getSignCreateTime() {
            return signCreateTime;
        }

        public void setSignCreateTime(String signCreateTime) {
            this.signCreateTime = signCreateTime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }

    public static class UserCarBean {
        /**
         * lastMainDate :
         * idNo :
         * registerCity :
         * mileage :
         * status :
         * registerDate :
         * vinNo :
         * carname : 奥迪A32016款 Sportback 35TFSI 进取型
         * remarks :
         * buyDate :
         * insuranceName :
         * updateDate :
         * cardNo : 粤b45666
         * id : c60df04e38a546b5bdea20958dae0d6f
         * recomMaintMile :
         * insuranceDate :
         * owner :
         * isNewRecord : false
         * insuranceId :
         * carConfigureId :
         * createDate :
         * delFlag : 0
         * drivingLicense :
         */

        private String lastMainDate;
        private String idNo;
        private String registerCity;
        private String mileage;
        private String status;
        private String registerDate;
        private String vinNo;
        private String carname;
        private String remarks;
        private String buyDate;
        private String insuranceName;
        private String updateDate;
        private String cardNo;
        private String id;
        private String recomMaintMile;
        private String insuranceDate;
        private String owner;
        private boolean isNewRecord;
        private String insuranceId;
        private String carConfigureId;
        private String createDate;
        private String delFlag;
        private String drivingLicense;

        public String getLastMainDate() {
            return lastMainDate;
        }

        public void setLastMainDate(String lastMainDate) {
            this.lastMainDate = lastMainDate;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getRegisterCity() {
            return registerCity;
        }

        public void setRegisterCity(String registerCity) {
            this.registerCity = registerCity;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getVinNo() {
            return vinNo;
        }

        public void setVinNo(String vinNo) {
            this.vinNo = vinNo;
        }

        public String getCarname() {
            return carname;
        }

        public void setCarname(String carname) {
            this.carname = carname;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getBuyDate() {
            return buyDate;
        }

        public void setBuyDate(String buyDate) {
            this.buyDate = buyDate;
        }

        public String getInsuranceName() {
            return insuranceName;
        }

        public void setInsuranceName(String insuranceName) {
            this.insuranceName = insuranceName;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRecomMaintMile() {
            return recomMaintMile;
        }

        public void setRecomMaintMile(String recomMaintMile) {
            this.recomMaintMile = recomMaintMile;
        }

        public String getInsuranceDate() {
            return insuranceDate;
        }

        public void setInsuranceDate(String insuranceDate) {
            this.insuranceDate = insuranceDate;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getInsuranceId() {
            return insuranceId;
        }

        public void setInsuranceId(String insuranceId) {
            this.insuranceId = insuranceId;
        }

        public String getCarConfigureId() {
            return carConfigureId;
        }

        public void setCarConfigureId(String carConfigureId) {
            this.carConfigureId = carConfigureId;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getDrivingLicense() {
            return drivingLicense;
        }

        public void setDrivingLicense(String drivingLicense) {
            this.drivingLicense = drivingLicense;
        }
    }
}
