package com.dream.moka.Bean.DriverCenter;

/**
 * Created by Administrator on 2018/1/16 0016.
 */
public class DriverOrderDetailBean {

    /**
     * userCar : {"owner":"奔驰司机","registerCity":"深圳","updateDate":"2018-01-17 13:49:46","buyDate":"","recomMaintMile":"","isNewRecord":false,"delFlag":"0","vinNo":"","cardNo":"粤B5208888","idNo":"","insuranceDate":"","drivingLicense":"","lastMainDate":"2017-11-15 00:00:00","carname":"奥迪A3 插电混动2017款 Sportback e-tron 舒适型","insuranceId":"","insuranceName":"","id":"235150e7718c4dadaea3ffb6a82762bc","carConfigureId":"f5e6a9992ba64f67b11376934f93e0d5","remarks":"","createDate":"","mileage":30000,"registerDate":"","status":0}
     * driverHisId : e6ac52acf68048c08df7a22829e7eb85
     * orders : {"orderType":1,"backDriverScore":"","repairShopIncome":"","evaluateTime":"","bookEndTime":"","lastLongitude":"113.94055411219597","sendDriverId":"fbbd18ec4be94758b0bf9a366cdcb85e","detailId":"","isBook":0,"sendDriverScore":"","payAmount":"0.01","increaseType":"","sendMobile":"","backDriverIncome":"","repairShopScore":"","id":"c255f3b6a7154889b554e7366dbbccb9","technicianId":"","finishTime":"","technicianScore":"","isNewRecord":false,"bookSendTime":"","carId":"235150e7718c4dadaea3ffb6a82762bc","sendAddress":"南山区高新北六道36号","sendTime":"","backDriverId":"","increaseStatus":"","lastLatitude":"22.55459161129674","backTime":"","status":3,"updateDate":"2018-01-17 12:01:59","transChannel":"0","backAddress":"南山区高新北六道36号","repairShopId":"fdbe53f00f29466c83d73fcb49a88032","expectedDate":4,"delFlag":"0","driverStatus":"","miles":30000,"startLatitude":"22.55459161129674","orderAmount":"700.00","createDate":"2018-01-17 11:09:05","backMobile":"","isBookEnd":"","sendName":"","sendDriverIncome":"","userCouponId":"","serviceName":"","transNo":"2018011721001004160212145789","backName":"","orderCode":"201801171109058915mt","startLongitude":"113.94055411219597","technicianIncome":"","remarks":""}
     * user : {"updateDate":"","openService":"","carName":"","oldLoginIp":"","sign":"","userDetailId":"","xgToken":"","admin":false,"loginDate":"","loginFlag":"1","oldLoginName":"","userTypes":"","blog":"","delFlag":"0","signCreateTime":"","xgType":"","score":"","password":"","wechart":"","balance":"","recomUserId":"","loginIp":"","loginName":"13128861359","recomCode":"","id":"16400fe57cee406ab3c0e3002c0c6fb1","email":"","createDate":"","qq":"","sex":"","mobile":"","newPassword":"","photo":"","isNewRecord":false,"token":"","oldLoginDate":"","phone":"","name":"奔驰司机","roleNames":"","remarks":""}
     * repairShop : {"businessLicense":"/MoCar/userfiles/driver/201801121743423efd31f3-aeec-4adf-b67e-32eb7f35e5a5.jpg","income":"","updateDate":"2018-01-16 09:24:51","city":"深圳市","latitude":"22.610507","dist":"","orderStatus":1,"delFlag":"0","contactTel":"18277750576","content":"","qualifications":"/MoCar/userfiles/driver/201801121743472ee0b0e6-7943-48ac-b25c-2bbbb5967dc4.jpg","score":"5.00","province":"广东省","contact":"条子","id":"fdbe53f00f29466c83d73fcb49a88032","createDate":"2018-01-12 17:44:16","longitude":"114.030334","address":"南山区高新园北6道","servicesIds":"","stores":"/MoCar/userfiles/repair/201801151201515d2eb756-507b-41aa-93c8-5946eb430e8c.jpg,/MoCar/userfiles/repair/201801160924030623b20c-bce4-4474-ab3f-2a23a8bea7a6.jpg,","isNewRecord":false,"picture":"/MoCar/userfiles/driver/20180112174414c148dfd5-5562-4ca1-9a86-ab7b699e9663.jpg","technicalCertificate":"/MoCar/userfiles/driver/201801121744013029789a-6be7-44d5-8bb1-807a7e8f5cd2.jpg","name":"条子维修商","shopType":1,"openDate":"","region":"南山区","remarks":"","status":1}
     */

    private UserCarBean userCar;
    private String driverHisId;
    private OrdersBean orders;
    private UserBean user;
    private RepairShopBean repairShop;

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public String getDriverHisId() {
        return driverHisId;
    }

    public void setDriverHisId(String driverHisId) {
        this.driverHisId = driverHisId;
    }

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public RepairShopBean getRepairShop() {
        return repairShop;
    }

    public void setRepairShop(RepairShopBean repairShop) {
        this.repairShop = repairShop;
    }

    public static class UserCarBean {
        /**
         * owner : 奔驰司机
         * registerCity : 深圳
         * updateDate : 2018-01-17 13:49:46
         * buyDate :
         * recomMaintMile :
         * isNewRecord : false
         * delFlag : 0
         * vinNo :
         * cardNo : 粤B5208888
         * idNo :
         * insuranceDate :
         * drivingLicense :
         * lastMainDate : 2017-11-15 00:00:00
         * carname : 奥迪A3 插电混动2017款 Sportback e-tron 舒适型
         * insuranceId :
         * insuranceName :
         * id : 235150e7718c4dadaea3ffb6a82762bc
         * carConfigureId : f5e6a9992ba64f67b11376934f93e0d5
         * remarks :
         * createDate :
         * mileage : 30000
         * registerDate :
         * status : 0
         */

        private String owner;
        private String registerCity;
        private String updateDate;
        private String buyDate;
        private String recomMaintMile;
        private boolean isNewRecord;
        private String delFlag;
        private String vinNo;
        private String cardNo;
        private String idNo;
        private String insuranceDate;
        private String drivingLicense;
        private String lastMainDate;
        private String carname;
        private String insuranceId;
        private String insuranceName;
        private String id;
        private String carConfigureId;
        private String remarks;
        private String createDate;
        private String mileage;
        private String registerDate;
        private String status;

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getRegisterCity() {
            return registerCity;
        }

        public void setRegisterCity(String registerCity) {
            this.registerCity = registerCity;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getBuyDate() {
            return buyDate;
        }

        public void setBuyDate(String buyDate) {
            this.buyDate = buyDate;
        }

        public String getRecomMaintMile() {
            return recomMaintMile;
        }

        public void setRecomMaintMile(String recomMaintMile) {
            this.recomMaintMile = recomMaintMile;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getVinNo() {
            return vinNo;
        }

        public void setVinNo(String vinNo) {
            this.vinNo = vinNo;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getInsuranceDate() {
            return insuranceDate;
        }

        public void setInsuranceDate(String insuranceDate) {
            this.insuranceDate = insuranceDate;
        }

        public String getDrivingLicense() {
            return drivingLicense;
        }

        public void setDrivingLicense(String drivingLicense) {
            this.drivingLicense = drivingLicense;
        }

        public String getLastMainDate() {
            return lastMainDate;
        }

        public void setLastMainDate(String lastMainDate) {
            this.lastMainDate = lastMainDate;
        }

        public String getCarname() {
            return carname;
        }

        public void setCarname(String carname) {
            this.carname = carname;
        }

        public String getInsuranceId() {
            return insuranceId;
        }

        public void setInsuranceId(String insuranceId) {
            this.insuranceId = insuranceId;
        }

        public String getInsuranceName() {
            return insuranceName;
        }

        public void setInsuranceName(String insuranceName) {
            this.insuranceName = insuranceName;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCarConfigureId() {
            return carConfigureId;
        }

        public void setCarConfigureId(String carConfigureId) {
            this.carConfigureId = carConfigureId;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getMileage() {
            return mileage;
        }

        public void setMileage(String mileage) {
            this.mileage = mileage;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class OrdersBean {
        /**
         * orderType : 1
         * backDriverScore :
         * repairShopIncome :
         * evaluateTime :
         * bookEndTime :
         * lastLongitude : 113.94055411219597
         * sendDriverId : fbbd18ec4be94758b0bf9a366cdcb85e
         * detailId :
         * isBook : 0
         * sendDriverScore :
         * payAmount : 0.01
         * increaseType :
         * sendMobile :
         * backDriverIncome :
         * repairShopScore :
         * id : c255f3b6a7154889b554e7366dbbccb9
         * technicianId :
         * finishTime :
         * technicianScore :
         * isNewRecord : false
         * bookSendTime :
         * carId : 235150e7718c4dadaea3ffb6a82762bc
         * sendAddress : 南山区高新北六道36号
         * sendTime :
         * backDriverId :
         * increaseStatus :
         * lastLatitude : 22.55459161129674
         * backTime :
         * status : 3
         * updateDate : 2018-01-17 12:01:59
         * transChannel : 0
         * backAddress : 南山区高新北六道36号
         * repairShopId : fdbe53f00f29466c83d73fcb49a88032
         * expectedDate : 4
         * delFlag : 0
         * driverStatus :
         * miles : 30000
         * startLatitude : 22.55459161129674
         * orderAmount : 700.00
         * createDate : 2018-01-17 11:09:05
         * backMobile :
         * isBookEnd :
         * sendName :
         * sendDriverIncome :
         * userCouponId :
         * serviceName :
         * transNo : 2018011721001004160212145789
         * backName :
         * orderCode : 201801171109058915mt
         * startLongitude : 113.94055411219597
         * technicianIncome :
         * remarks :
         */

        private String orderType;
        private String backDriverScore;
        private String repairShopIncome;
        private String evaluateTime;
        private String bookEndTime;
        private String lastLongitude;
        private String sendDriverId;
        private String detailId;
        private String isBook;
        private String sendDriverScore;
        private String payAmount;
        private String increaseType;
        private String sendMobile;
        private String backDriverIncome;
        private String repairShopScore;
        private String id;
        private String technicianId;
        private String finishTime;
        private String technicianScore;
        private boolean isNewRecord;
        private String bookSendTime;
        private String carId;
        private String sendAddress;
        private String sendTime;
        private String backDriverId;
        private String increaseStatus;
        private String lastLatitude;
        private String backTime;
        private String status;
        private String updateDate;
        private String transChannel;
        private String backAddress;
        private String repairShopId;
        private String expectedDate;
        private String delFlag;
        private String driverStatus;
        private String miles;
        private String startLatitude;
        private String orderAmount;
        private String createDate;
        private String backMobile;
        private String isBookEnd;
        private String sendName;
        private String sendDriverIncome;
        private String userCouponId;
        private String serviceName;
        private String transNo;
        private String backName;
        private String orderCode;
        private String startLongitude;
        private String technicianIncome;
        private String remarks;

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public void setIsBook(String isBook) {
            this.isBook = isBook;
        }

        public void setMiles(String miles) {
            this.miles = miles;
        }

        public String getBackDriverScore() {
            return backDriverScore;
        }

        public void setBackDriverScore(String backDriverScore) {
            this.backDriverScore = backDriverScore;
        }

        public String getRepairShopIncome() {
            return repairShopIncome;
        }

        public void setRepairShopIncome(String repairShopIncome) {
            this.repairShopIncome = repairShopIncome;
        }

        public String getEvaluateTime() {
            return evaluateTime;
        }

        public void setEvaluateTime(String evaluateTime) {
            this.evaluateTime = evaluateTime;
        }

        public String getBookEndTime() {
            return bookEndTime;
        }

        public void setBookEndTime(String bookEndTime) {
            this.bookEndTime = bookEndTime;
        }

        public String getLastLongitude() {
            return lastLongitude;
        }

        public void setLastLongitude(String lastLongitude) {
            this.lastLongitude = lastLongitude;
        }

        public String getSendDriverId() {
            return sendDriverId;
        }

        public void setSendDriverId(String sendDriverId) {
            this.sendDriverId = sendDriverId;
        }

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }


        public String getSendDriverScore() {
            return sendDriverScore;
        }

        public void setSendDriverScore(String sendDriverScore) {
            this.sendDriverScore = sendDriverScore;
        }

        public String getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(String payAmount) {
            this.payAmount = payAmount;
        }

        public String getIncreaseType() {
            return increaseType;
        }

        public void setIncreaseType(String increaseType) {
            this.increaseType = increaseType;
        }

        public String getSendMobile() {
            return sendMobile;
        }

        public void setSendMobile(String sendMobile) {
            this.sendMobile = sendMobile;
        }

        public String getBackDriverIncome() {
            return backDriverIncome;
        }

        public void setBackDriverIncome(String backDriverIncome) {
            this.backDriverIncome = backDriverIncome;
        }

        public String getRepairShopScore() {
            return repairShopScore;
        }

        public void setRepairShopScore(String repairShopScore) {
            this.repairShopScore = repairShopScore;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTechnicianId() {
            return technicianId;
        }

        public void setTechnicianId(String technicianId) {
            this.technicianId = technicianId;
        }

        public String getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(String finishTime) {
            this.finishTime = finishTime;
        }

        public String getTechnicianScore() {
            return technicianScore;
        }

        public void setTechnicianScore(String technicianScore) {
            this.technicianScore = technicianScore;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getBookSendTime() {
            return bookSendTime;
        }

        public void setBookSendTime(String bookSendTime) {
            this.bookSendTime = bookSendTime;
        }

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getSendAddress() {
            return sendAddress;
        }

        public void setSendAddress(String sendAddress) {
            this.sendAddress = sendAddress;
        }

        public String getSendTime() {
            return sendTime;
        }

        public void setSendTime(String sendTime) {
            this.sendTime = sendTime;
        }

        public String getBackDriverId() {
            return backDriverId;
        }

        public void setBackDriverId(String backDriverId) {
            this.backDriverId = backDriverId;
        }

        public String getIncreaseStatus() {
            return increaseStatus;
        }

        public void setIncreaseStatus(String increaseStatus) {
            this.increaseStatus = increaseStatus;
        }

        public String getLastLatitude() {
            return lastLatitude;
        }

        public void setLastLatitude(String lastLatitude) {
            this.lastLatitude = lastLatitude;
        }

        public String getBackTime() {
            return backTime;
        }

        public void setBackTime(String backTime) {
            this.backTime = backTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getTransChannel() {
            return transChannel;
        }

        public void setTransChannel(String transChannel) {
            this.transChannel = transChannel;
        }

        public String getBackAddress() {
            return backAddress;
        }

        public void setBackAddress(String backAddress) {
            this.backAddress = backAddress;
        }

        public String getRepairShopId() {
            return repairShopId;
        }

        public void setRepairShopId(String repairShopId) {
            this.repairShopId = repairShopId;
        }

        public String getExpectedDate() {
            return expectedDate;
        }

        public void setExpectedDate(String expectedDate) {
            this.expectedDate = expectedDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getDriverStatus() {
            return driverStatus;
        }

        public void setDriverStatus(String driverStatus) {
            this.driverStatus = driverStatus;
        }

        public String getIsBook() {
            return isBook;
        }

        public String getMiles() {
            return miles;
        }

        public String getStartLatitude() {
            return startLatitude;
        }

        public void setStartLatitude(String startLatitude) {
            this.startLatitude = startLatitude;
        }

        public String getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(String orderAmount) {
            this.orderAmount = orderAmount;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getBackMobile() {
            return backMobile;
        }

        public void setBackMobile(String backMobile) {
            this.backMobile = backMobile;
        }

        public String getIsBookEnd() {
            return isBookEnd;
        }

        public void setIsBookEnd(String isBookEnd) {
            this.isBookEnd = isBookEnd;
        }

        public String getSendName() {
            return sendName;
        }

        public void setSendName(String sendName) {
            this.sendName = sendName;
        }

        public String getSendDriverIncome() {
            return sendDriverIncome;
        }

        public void setSendDriverIncome(String sendDriverIncome) {
            this.sendDriverIncome = sendDriverIncome;
        }

        public String getUserCouponId() {
            return userCouponId;
        }

        public void setUserCouponId(String userCouponId) {
            this.userCouponId = userCouponId;
        }

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getTransNo() {
            return transNo;
        }

        public void setTransNo(String transNo) {
            this.transNo = transNo;
        }

        public String getBackName() {
            return backName;
        }

        public void setBackName(String backName) {
            this.backName = backName;
        }

        public String getOrderCode() {
            return orderCode;
        }

        public void setOrderCode(String orderCode) {
            this.orderCode = orderCode;
        }

        public String getStartLongitude() {
            return startLongitude;
        }

        public void setStartLongitude(String startLongitude) {
            this.startLongitude = startLongitude;
        }

        public String getTechnicianIncome() {
            return technicianIncome;
        }

        public void setTechnicianIncome(String technicianIncome) {
            this.technicianIncome = technicianIncome;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }

    public static class UserBean {
        /**
         * updateDate :
         * openService :
         * carName :
         * oldLoginIp :
         * sign :
         * userDetailId :
         * xgToken :
         * admin : false
         * loginDate :
         * loginFlag : 1
         * oldLoginName :
         * userTypes :
         * blog :
         * delFlag : 0
         * signCreateTime :
         * xgType :
         * score :
         * password :
         * wechart :
         * balance :
         * recomUserId :
         * loginIp :
         * loginName : 13128861359
         * recomCode :
         * id : 16400fe57cee406ab3c0e3002c0c6fb1
         * email :
         * createDate :
         * qq :
         * sex :
         * mobile :
         * newPassword :
         * photo :
         * isNewRecord : false
         * token :
         * oldLoginDate :
         * phone :
         * name : 奔驰司机
         * roleNames :
         * remarks :
         */

        private String updateDate;
        private String openService;
        private String carName;
        private String oldLoginIp;
        private String sign;
        private String userDetailId;
        private String xgToken;
        private boolean admin;
        private String loginDate;
        private String loginFlag;
        private String oldLoginName;
        private String userTypes;
        private String blog;
        private String delFlag;
        private String signCreateTime;
        private String xgType;
        private String score;
        private String password;
        private String wechart;
        private String balance;
        private String recomUserId;
        private String loginIp;
        private String loginName;
        private String recomCode;
        private String id;
        private String email;
        private String createDate;
        private String qq;
        private String sex;
        private String mobile;
        private String newPassword;
        private String photo;
        private boolean isNewRecord;
        private String token;
        private String oldLoginDate;
        private String phone;
        private String name;
        private String roleNames;
        private String remarks;

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getOpenService() {
            return openService;
        }

        public void setOpenService(String openService) {
            this.openService = openService;
        }

        public String getCarName() {
            return carName;
        }

        public void setCarName(String carName) {
            this.carName = carName;
        }

        public String getOldLoginIp() {
            return oldLoginIp;
        }

        public void setOldLoginIp(String oldLoginIp) {
            this.oldLoginIp = oldLoginIp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getUserDetailId() {
            return userDetailId;
        }

        public void setUserDetailId(String userDetailId) {
            this.userDetailId = userDetailId;
        }

        public String getXgToken() {
            return xgToken;
        }

        public void setXgToken(String xgToken) {
            this.xgToken = xgToken;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
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

        public String getBlog() {
            return blog;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getSignCreateTime() {
            return signCreateTime;
        }

        public void setSignCreateTime(String signCreateTime) {
            this.signCreateTime = signCreateTime;
        }

        public String getXgType() {
            return xgType;
        }

        public void setXgType(String xgType) {
            this.xgType = xgType;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getWechart() {
            return wechart;
        }

        public void setWechart(String wechart) {
            this.wechart = wechart;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public String getRecomUserId() {
            return recomUserId;
        }

        public void setRecomUserId(String recomUserId) {
            this.recomUserId = recomUserId;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getLoginName() {
            return loginName;
        }

        public void setLoginName(String loginName) {
            this.loginName = loginName;
        }

        public String getRecomCode() {
            return recomCode;
        }

        public void setRecomCode(String recomCode) {
            this.recomCode = recomCode;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getOldLoginDate() {
            return oldLoginDate;
        }

        public void setOldLoginDate(String oldLoginDate) {
            this.oldLoginDate = oldLoginDate;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }

    public static class RepairShopBean {
        /**
         * businessLicense : /MoCar/userfiles/driver/201801121743423efd31f3-aeec-4adf-b67e-32eb7f35e5a5.jpg
         * income :
         * updateDate : 2018-01-16 09:24:51
         * city : 深圳市
         * latitude : 22.610507
         * dist :
         * orderStatus : 1
         * delFlag : 0
         * contactTel : 18277750576
         * content :
         * qualifications : /MoCar/userfiles/driver/201801121743472ee0b0e6-7943-48ac-b25c-2bbbb5967dc4.jpg
         * score : 5.00
         * province : 广东省
         * contact : 条子
         * id : fdbe53f00f29466c83d73fcb49a88032
         * createDate : 2018-01-12 17:44:16
         * longitude : 114.030334
         * address : 南山区高新园北6道
         * servicesIds :
         * stores : /MoCar/userfiles/repair/201801151201515d2eb756-507b-41aa-93c8-5946eb430e8c.jpg,/MoCar/userfiles/repair/201801160924030623b20c-bce4-4474-ab3f-2a23a8bea7a6.jpg,
         * isNewRecord : false
         * picture : /MoCar/userfiles/driver/20180112174414c148dfd5-5562-4ca1-9a86-ab7b699e9663.jpg
         * technicalCertificate : /MoCar/userfiles/driver/201801121744013029789a-6be7-44d5-8bb1-807a7e8f5cd2.jpg
         * name : 条子维修商
         * shopType : 1
         * openDate :
         * region : 南山区
         * remarks :
         * status : 1
         */

        private String businessLicense;
        private String income;
        private String updateDate;
        private String city;
        private String latitude;
        private String dist;
        private String orderStatus;
        private String delFlag;
        private String contactTel;
        private String content;
        private String qualifications;
        private String score;
        private String province;
        private String contact;
        private String id;
        private String createDate;
        private String longitude;
        private String address;
        private String servicesIds;
        private String stores;
        private boolean isNewRecord;
        private String picture;
        private String technicalCertificate;
        private String name;
        private String shopType;
        private String openDate;
        private String region;
        private String remarks;
        private String status;

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }


        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getQualifications() {
            return qualifications;
        }

        public void setQualifications(String qualifications) {
            this.qualifications = qualifications;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getServicesIds() {
            return servicesIds;
        }

        public void setServicesIds(String servicesIds) {
            this.servicesIds = servicesIds;
        }

        public String getStores() {
            return stores;
        }

        public void setStores(String stores) {
            this.stores = stores;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTechnicalCertificate() {
            return technicalCertificate;
        }

        public void setTechnicalCertificate(String technicalCertificate) {
            this.technicalCertificate = technicalCertificate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public String getOpenDate() {
            return openDate;
        }

        public void setOpenDate(String openDate) {
            this.openDate = openDate;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
