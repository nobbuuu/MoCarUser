package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class DriverHomeResultBean {

    /**
     * rebateIncome : 0.00
     * orderIncome : 0.00
     * listQJC : [{"orderType":1,"backDriverScore":"","repairShopIncome":"","evaluateTime":"","bookEndTime":"","lastLongitude":"113.94055411219597","sendDriverId":"fbbd18ec4be94758b0bf9a366cdcb85e","detailId":"","isBook":0,"sendDriverScore":"","payAmount":"0.01","increaseType":"","sendMobile":"","backDriverIncome":"","repairShopScore":"","id":"c255f3b6a7154889b554e7366dbbccb9","technicianId":"","finishTime":"","technicianScore":"","isNewRecord":false,"bookSendTime":"","carId":"235150e7718c4dadaea3ffb6a82762bc","sendAddress":"南山区高新北六道36号","sendTime":"","backDriverId":"","increaseStatus":"","lastLatitude":"22.55459161129674","backTime":"","status":3,"updateDate":"2018-01-17 12:01:59","transChannel":"0","backAddress":"南山区高新北六道36号","repairShopId":"fdbe53f00f29466c83d73fcb49a88032","expectedDate":4,"delFlag":"0","driverStatus":"","miles":30000,"startLatitude":"22.55459161129674","orderAmount":"700.00","createDate":"2018-01-17 11:09:05","backMobile":"","isBookEnd":"","sendName":"","sendDriverIncome":"","userCouponId":"","serviceName":"","transNo":"2018011721001004160212145789","backName":"","orderCode":"201801171109058915mt","startLongitude":"113.94055411219597","technicianIncome":"","remarks":""}]
     * listDJC : [{"orderType":1,"backDriverScore":"","repairShopIncome":"","evaluateTime":"","bookEndTime":"","lastLongitude":"113.9404843747616","sendDriverId":"fbbd18ec4be94758b0bf9a366cdcb85e","detailId":"","isBook":0,"sendDriverScore":"","payAmount":"0.01","increaseType":"","sendMobile":"","backDriverIncome":"","repairShopScore":"","id":"aa4407d03c98420f8316479387531f42","technicianId":"","finishTime":"","technicianScore":"","isNewRecord":false,"bookSendTime":"","carId":"73092e9ddfff463da5e55bac96295c5c","sendAddress":"高新北六道36号","sendTime":"","backDriverId":"","increaseStatus":"","lastLatitude":"22.554613904825004","backTime":"","status":2,"updateDate":"2018-01-17 15:02:02","transChannel":"0","backAddress":"高新北六道36号","repairShopId":"fdbe53f00f29466c83d73fcb49a88032","expectedDate":4,"delFlag":"0","driverStatus":"","miles":3000,"startLatitude":"22.554613904825004","orderAmount":"900.00","createDate":"2018-01-17 14:50:20","backMobile":"","isBookEnd":"","sendName":"","sendDriverIncome":"","userCouponId":"","serviceName":"","transNo":"2018011721001004160211885088","backName":"","orderCode":"201801171450207286mt","startLongitude":"113.9404843747616","technicianIncome":"","remarks":""}]
     * totalOrder : 0
     * listQSC : []
     * todayIncome : 0.00
     * totalOrderToday : 0
     * listDSC : []
     */

    private String rebateIncome;
    private String orderIncome;
    private String totalOrder;
    private String todayIncome;
    private String totalOrderToday;
    private List<ListQJCBean> listQJC;
    private List<ListDJCBean> listDJC;
    private List<ListQSCBean> listQSC;
    private List<ListDSCBean> listDSC;
    private repairShopBean repairShop;

    public repairShopBean getRepairShop() {
        return repairShop;
    }

    public void setRepairShop(repairShopBean repairShop) {
        this.repairShop = repairShop;
    }

    public String getRebateIncome() {
        return rebateIncome;
    }

    public void setRebateIncome(String rebateIncome) {
        this.rebateIncome = rebateIncome;
    }

    public String getOrderIncome() {
        return orderIncome;
    }

    public void setOrderIncome(String orderIncome) {
        this.orderIncome = orderIncome;
    }

    public String getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(String totalOrder) {
        this.totalOrder = totalOrder;
    }

    public String getTodayIncome() {
        return todayIncome;
    }

    public void setTodayIncome(String todayIncome) {
        this.todayIncome = todayIncome;
    }

    public String getTotalOrderToday() {
        return totalOrderToday;
    }

    public void setTotalOrderToday(String totalOrderToday) {
        this.totalOrderToday = totalOrderToday;
    }

    public List<ListQJCBean> getListQJC() {
        return listQJC;
    }

    public void setListQJC(List<ListQJCBean> listQJC) {
        this.listQJC = listQJC;
    }

    public List<ListDJCBean> getListDJC() {
        return listDJC;
    }

    public void setListDJC(List<ListDJCBean> listDJC) {
        this.listDJC = listDJC;
    }

    public List<ListQSCBean> getListQSC() {
        return listQSC;
    }

    public void setListQSC(List<ListQSCBean> listQSC) {
        this.listQSC = listQSC;
    }

    public List<ListDSCBean> getListDSC() {
        return listDSC;
    }

    public void setListDSC(List<ListDSCBean> listDSC) {
        this.listDSC = listDSC;
    }

    public static class repairShopBean{
        /**
         * region :
         * servicesIds :
         * qualifications :
         * score :
         * remarks :
         * contactTel : 18277750576
         * contact :
         * city :
         * id :
         * businessLicense :
         * income :
         * name : 条子维修商
         * isNewRecord : true
         * province :
         * longitude :
         * createDate :
         * openDate :
         * status :
         * orderStatus :
         * updateDate :
         * content :
         * picture :
         * shopType :
         * technicalCertificate :
         * address : 南山区高新园北6道
         * latitude :
         * stores :
         * dist :
         * delFlag : 0
         */

        private String region;
        private String servicesIds;
        private String qualifications;
        private String score;
        private String remarks;
        private String contactTel;
        private String contact;
        private String city;
        private String id;
        private String businessLicense;
        private String income;
        private String name;
        private boolean isNewRecord;
        private String province;
        private String longitude;
        private String createDate;
        private String openDate;
        private String status;
        private String orderStatus;
        private String updateDate;
        private String content;
        private String picture;
        private String shopType;
        private String technicalCertificate;
        private String address;
        private String latitude;
        private String stores;
        private String dist;
        private String delFlag;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getServicesIds() {
            return servicesIds;
        }

        public void setServicesIds(String servicesIds) {
            this.servicesIds = servicesIds;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getOpenDate() {
            return openDate;
        }

        public void setOpenDate(String openDate) {
            this.openDate = openDate;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getTechnicalCertificate() {
            return technicalCertificate;
        }

        public void setTechnicalCertificate(String technicalCertificate) {
            this.technicalCertificate = technicalCertificate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getStores() {
            return stores;
        }

        public void setStores(String stores) {
            this.stores = stores;
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
    }
    public static class ListQJCBean {
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

        public String getIsBook() {
            return isBook;
        }

        public void setIsBook(String isBook) {
            this.isBook = isBook;
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

        public String getMiles() {
            return miles;
        }

        public void setMiles(String miles) {
            this.miles = miles;
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

    public static class ListQSCBean{
        /**
         * startLongitude : 113.94071504473688
         * isBookEnd :
         * backDriverIncome :
         * orderCode : 201801172336453804mt
         * repairShopIncome :
         * technicianId :
         * increaseStatus :
         * repairShopScore :
         * sendDriverIncome :
         * bookEndTime :
         * orderAmount : 139.00
         * isNewRecord : false
         * finishTime : 2018-01-18 01:38:27
         * driverStatus :
         * bookSendTime :
         * technicianIncome :
         * backDriverScore :
         * startLatitude : 22.55543257022782
         * orderType : 1
         * status : 7
         * sendTime : 2018-01-17 23:46:53
         * miles : 10000
         * transNo : 2018011721001004760259262563
         * evaluateTime : 2018-01-18 01:38:27
         * sendDriverScore :
         * increaseType :
         * transChannel : 0
         * userCouponId :
         * delFlag : 0
         * technicianScore :
         * carId : 0fc156ca90ba4e9087de0560c35c736f
         * remarks :
         * expectedDate : 3.00
         * sendDriverId : d7348e28c0a94c5588b4a77c3c4eb588
         * lastLatitude : 22.554659730399507
         * serviceName :
         * id : 8f7ba7c7fd3140c8942d780ac30be98c
         * backTime :
         * backDriverId : d7348e28c0a94c5588b4a77c3c4eb588
         * sendMobile : 18571112323
         * backMobile : 18576618690
         * createDate : 2018-01-17 23:36:46
         * isBook : 0
         * payAmount : 0.01
         * sendAddress : 高新北四道11号
         * sendName : 王勇1
         * updateDate : 2018-01-18 01:43:31
         * repairShopId : fdbe53f00f29466c83d73fcb49a88032
         * backName : 王勇2
         * backAddress : 高新北六道36号
         * lastLongitude : 113.94059568643571
         * detailId :
         */

        private String startLongitude;
        private String isBookEnd;
        private String backDriverIncome;
        private String orderCode;
        private String repairShopIncome;
        private String technicianId;
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
        private String startLatitude;
        private String orderType;
        private String status;
        private String sendTime;
        private String miles;
        private String transNo;
        private String evaluateTime;
        private String sendDriverScore;
        private String increaseType;
        private String transChannel;
        private String userCouponId;
        private String delFlag;
        private String technicianScore;
        private String carId;
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

        public String getRepairShopIncome() {
            return repairShopIncome;
        }

        public void setRepairShopIncome(String repairShopIncome) {
            this.repairShopIncome = repairShopIncome;
        }

        public String getTechnicianId() {
            return technicianId;
        }

        public void setTechnicianId(String technicianId) {
            this.technicianId = technicianId;
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

        public String getStartLatitude() {
            return startLatitude;
        }

        public void setStartLatitude(String startLatitude) {
            this.startLatitude = startLatitude;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }
    }
    public static class ListDSCBean {
        /**
         * startLongitude : 113.94071504473688
         * isBookEnd :
         * backDriverIncome :
         * orderCode : 201801172336453804mt
         * repairShopIncome :
         * technicianId :
         * increaseStatus :
         * repairShopScore :
         * sendDriverIncome :
         * bookEndTime :
         * orderAmount : 139.00
         * isNewRecord : false
         * finishTime : 2018-01-18 01:38:27
         * driverStatus :
         * bookSendTime :
         * technicianIncome :
         * backDriverScore :
         * startLatitude : 22.55543257022782
         * orderType : 1
         * status : 7
         * sendTime : 2018-01-17 23:46:53
         * miles : 10000
         * transNo : 2018011721001004760259262563
         * evaluateTime : 2018-01-18 01:38:27
         * sendDriverScore :
         * increaseType :
         * transChannel : 0
         * userCouponId :
         * delFlag : 0
         * technicianScore :
         * carId : 0fc156ca90ba4e9087de0560c35c736f
         * remarks :
         * expectedDate : 3.00
         * sendDriverId : d7348e28c0a94c5588b4a77c3c4eb588
         * lastLatitude : 22.554659730399507
         * serviceName :
         * id : 8f7ba7c7fd3140c8942d780ac30be98c
         * backTime :
         * backDriverId : d7348e28c0a94c5588b4a77c3c4eb588
         * sendMobile : 18571112323
         * backMobile : 18576618690
         * createDate : 2018-01-17 23:36:46
         * isBook : 0
         * payAmount : 0.01
         * sendAddress : 高新北四道11号
         * sendName : 王勇1
         * updateDate : 2018-01-18 01:43:31
         * repairShopId : fdbe53f00f29466c83d73fcb49a88032
         * backName : 王勇2
         * backAddress : 高新北六道36号
         * lastLongitude : 113.94059568643571
         * detailId :
         */

        private String startLongitude;
        private String isBookEnd;
        private String backDriverIncome;
        private String orderCode;
        private String repairShopIncome;
        private String technicianId;
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
        private String startLatitude;
        private String orderType;
        private String status;
        private String sendTime;
        private String miles;
        private String transNo;
        private String evaluateTime;
        private String sendDriverScore;
        private String increaseType;
        private String transChannel;
        private String userCouponId;
        private String delFlag;
        private String technicianScore;
        private String carId;
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

        public String getRepairShopIncome() {
            return repairShopIncome;
        }

        public void setRepairShopIncome(String repairShopIncome) {
            this.repairShopIncome = repairShopIncome;
        }

        public String getTechnicianId() {
            return technicianId;
        }

        public void setTechnicianId(String technicianId) {
            this.technicianId = technicianId;
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

        public String getStartLatitude() {
            return startLatitude;
        }

        public void setStartLatitude(String startLatitude) {
            this.startLatitude = startLatitude;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public String getDetailId() {
            return detailId;
        }

        public void setDetailId(String detailId) {
            this.detailId = detailId;
        }
    }

    public static class ListDJCBean {
        /**
         * orderType : 1
         * backDriverScore :
         * repairShopIncome :
         * evaluateTime :
         * bookEndTime :
         * lastLongitude : 113.9404843747616
         * sendDriverId : fbbd18ec4be94758b0bf9a366cdcb85e
         * detailId :
         * isBook : 0
         * sendDriverScore :
         * payAmount : 0.01
         * increaseType :
         * sendMobile :
         * backDriverIncome :
         * repairShopScore :
         * id : aa4407d03c98420f8316479387531f42
         * technicianId :
         * finishTime :
         * technicianScore :
         * isNewRecord : false
         * bookSendTime :
         * carId : 73092e9ddfff463da5e55bac96295c5c
         * sendAddress : 高新北六道36号
         * sendTime :
         * backDriverId :
         * increaseStatus :
         * lastLatitude : 22.554613904825004
         * backTime :
         * status : 2
         * updateDate : 2018-01-17 15:02:02
         * transChannel : 0
         * backAddress : 高新北六道36号
         * repairShopId : fdbe53f00f29466c83d73fcb49a88032
         * expectedDate : 4
         * delFlag : 0
         * driverStatus :
         * miles : 3000
         * startLatitude : 22.554613904825004
         * orderAmount : 900.00
         * createDate : 2018-01-17 14:50:20
         * backMobile :
         * isBookEnd :
         * sendName :
         * sendDriverIncome :
         * userCouponId :
         * serviceName :
         * transNo : 2018011721001004160211885088
         * backName :
         * orderCode : 201801171450207286mt
         * startLongitude : 113.9404843747616
         * technicianIncome :
         * remarks :
         */

        private int orderType;
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

        public int getOrderType() {
            return orderType;
        }

        public void setOrderType(int orderType) {
            this.orderType = orderType;
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

        public String getIsBook() {
            return isBook;
        }

        public void setIsBook(String isBook) {
            this.isBook = isBook;
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

        public String getMiles() {
            return miles;
        }

        public void setMiles(String miles) {
            this.miles = miles;
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
}
