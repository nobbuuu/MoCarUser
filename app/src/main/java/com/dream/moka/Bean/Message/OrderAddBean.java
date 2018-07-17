package com.dream.moka.Bean.Message;

import com.dream.moka.Bean.BaoYangFangAnResultBean;
import com.dream.moka.Bean.ConfirmOrderResultBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/1 0001.
 */
public class OrderAddBean implements Serializable {

    /**
     * listShopExit : [{},{},{},{},{},{}]
     * orders : {"startLongitude":"","isBookEnd":"","backDriverIncome":"","orderCode":"","washCarScore":"","technicianId":"00b6b76ba3894f2e8426b035c81bb335","repairShopIncome":"","increaseStatus":2,"repairShopScore":"","sendDriverIncome":"","bookEndTime":"","orderAmount":"200.00","isNewRecord":false,"finishTime":"2018-01-31 21:20:20","driverStatus":"","bookSendTime":"","technicianIncome":"","backDriverScore":"","jcStartTime":"2018-01-31 21:20:20","startLatitude":"","status":-1,"orderType":0,"sendTime":"","miles":"","transNo":"","scSendTime":"2018-01-31 21:20:20","evaluateTime":"2018-01-31 21:20:20","sendDriverScore":"","increaseType":2,"transChannel":"","userCouponId":"","delFlag":"0","technicianScore":"","carId":"e6abcc8a72324f2e97197af289c1feed","repairShopUserScore":"","jcSendTime":"2018-01-31 21:20:20","remarks":"","expectedDate":"","sendDriverId":"","lastLatitude":"","serviceName":"","id":"65f4058bbe2241448f5cbcc535aec0d0","backTime":"","backDriverId":"","sendMobile":"","backMobile":"","createDate":"2018-01-31 21:11:00","isBook":"","payAmount":"","sendAddress":"","sendName":"","updateDate":"2018-01-31 21:11:00","repairShopId":"请选择维修商","backName":"","backAddress":"","lastLongitude":"","scStartTime":"2018-01-31 21:20:20","detailId":"313e93d2dd5b41dc94b2ab239d1de0fc"}
     * repairOrder : {"id":"313e93d2dd5b41dc94b2ab239d1de0fc","status":1,"description":"坏了很多东西","name":"维修订单","isNewRecord":false,"remarks":"","createDate":"2018-01-31 21:11:00","updateDate":"2018-01-31 21:11:00","delFlag":"0"}
     * userCar : {"lastMainDate":"2018-04-22 00:00:00","idNo":"","registerCity":"深圳","mileage":50000,"status":1,"registerDate":"","vinNo":"","carname":"标致20082015款 1.6L 自动 翡翠型 玩酷版","remarks":"","buyDate":"","insuranceName":"","updateDate":"2018-01-31 20:42:58","cardNo":"粤B00000","id":"e6abcc8a72324f2e97197af289c1feed","recomMaintMile":"","insuranceDate":"","owner":"水无伤","isNewRecord":false,"insuranceId":"","carConfigureId":"ebe839c82cb9426b9382dce8010d236b","createDate":"","delFlag":"0","drivingLicense":""}
     */


    private OrdersBean orders;
    private RepairOrderBean repairOrder;
    private UserCarBean userCar;
    private List<ConfirmOrderResultBean.ListShopBean> listShopExit;
    private String orderStatus;
    private String otherPrice;
    private List<BaoYangFangAnResultBean.ListBaseBean> listService;
    private List<ListSpraySideBean> listSpraySide;
    private InsuranceOrderBean insuranceOrder;

    public String getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(String otherPrice) {
        this.otherPrice = otherPrice;
    }

    public InsuranceOrderBean getInsuranceOrder() {
        return insuranceOrder;
    }

    public void setInsuranceOrder(InsuranceOrderBean insuranceOrder) {
        this.insuranceOrder = insuranceOrder;
    }

    public List<ListSpraySideBean> getListSpraySide() {
        return listSpraySide;
    }

    public void setListSpraySide(List<ListSpraySideBean> listSpraySide) {
        this.listSpraySide = listSpraySide;
    }

    public List<BaoYangFangAnResultBean.ListBaseBean> getListService() {
        return listService;
    }

    public void setListService(List<BaoYangFangAnResultBean.ListBaseBean> listService) {
        this.listService = listService;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public RepairOrderBean getRepairOrder() {
        return repairOrder;
    }

    public void setRepairOrder(RepairOrderBean repairOrder) {
        this.repairOrder = repairOrder;
    }

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public List<ConfirmOrderResultBean.ListShopBean> getListShopExit() {
        return listShopExit;
    }

    public void setListShopExit(List<ConfirmOrderResultBean.ListShopBean> listShopExit) {
        this.listShopExit = listShopExit;
    }

    public static class InsuranceOrderBean {

        /**
         * updateDate : 2018-02-26 17:56:37
         * carStatus : 1
         * trailerMoney : 100.00
         * isNewRecord : false
         * delFlag : 0
         * settlementMode : 1
         * liabilitySource : 0
         * nuclearMoney : 200.00
         * insuranceId : 97ec419e28ac4815b21845e09434b9da
         * id : 54380b5206f24fbba65a51a60ee0541a
         * liabilityPercent : 1
         * remarks :
         * createDate : 2018-02-26 17:56:37
         * status :
         */

        private String updateDate;
        private String carStatus;
        private String trailerMoney;
        private boolean isNewRecord;
        private String delFlag;
        private String settlementMode;
        private String liabilitySource;
        private String nuclearMoney;
        private String insuranceId;
        private String id;
        private String liabilityPercent;
        private String remarks;
        private String createDate;
        private String status;

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCarStatus() {
            return carStatus;
        }

        public void setCarStatus(String carStatus) {
            this.carStatus = carStatus;
        }

        public String getTrailerMoney() {
            return trailerMoney;
        }

        public void setTrailerMoney(String trailerMoney) {
            this.trailerMoney = trailerMoney;
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

        public String getSettlementMode() {
            return settlementMode;
        }

        public void setSettlementMode(String settlementMode) {
            this.settlementMode = settlementMode;
        }

        public String getLiabilitySource() {
            return liabilitySource;
        }

        public void setLiabilitySource(String liabilitySource) {
            this.liabilitySource = liabilitySource;
        }

        public String getNuclearMoney() {
            return nuclearMoney;
        }

        public void setNuclearMoney(String nuclearMoney) {
            this.nuclearMoney = nuclearMoney;
        }

        public String getInsuranceId() {
            return insuranceId;
        }

        public void setInsuranceId(String insuranceId) {
            this.insuranceId = insuranceId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLiabilityPercent() {
            return liabilityPercent;
        }

        public void setLiabilityPercent(String liabilityPercent) {
            this.liabilityPercent = liabilityPercent;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class ListSpraySideBean implements Serializable {

        /**
         * updateDate : 2017-11-10 17:33:31
         * price : 260.00
         * name : 右侧下裙边
         * id : 22c69171261b4737946d2c8b8b7ef895
         * isNewRecord : false
         * delFlag : 0
         * remarks :
         * createDate : 2017-11-10 17:33:31
         * status :
         */

        private String updateDate;
        private String price;
        private String name;
        private String id;
        private boolean isNewRecord;
        private String delFlag;
        private String remarks;
        private String createDate;
        private String status;

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class OrdersBean {
        /**
         * startLongitude :
         * isBookEnd :
         * backDriverIncome :
         * orderCode :
         * washCarScore :
         * technicianId : 00b6b76ba3894f2e8426b035c81bb335
         * repairShopIncome :
         * increaseStatus : 2
         * repairShopScore :
         * sendDriverIncome :
         * bookEndTime :
         * orderAmount : 200.00
         * isNewRecord : false
         * finishTime : 2018-01-31 21:20:20
         * driverStatus :
         * bookSendTime :
         * technicianIncome :
         * backDriverScore :
         * jcStartTime : 2018-01-31 21:20:20
         * startLatitude :
         * status : -1
         * orderType : 0
         * sendTime :
         * miles :
         * transNo :
         * scSendTime : 2018-01-31 21:20:20
         * evaluateTime : 2018-01-31 21:20:20
         * sendDriverScore :
         * increaseType : 2
         * transChannel :
         * userCouponId :
         * delFlag : 0
         * technicianScore :
         * carId : e6abcc8a72324f2e97197af289c1feed
         * repairShopUserScore :
         * jcSendTime : 2018-01-31 21:20:20
         * remarks :
         * expectedDate :
         * sendDriverId :
         * lastLatitude :
         * serviceName :
         * id : 65f4058bbe2241448f5cbcc535aec0d0
         * backTime :
         * backDriverId :
         * sendMobile :
         * backMobile :
         * createDate : 2018-01-31 21:11:00
         * isBook :
         * payAmount :
         * sendAddress :
         * sendName :
         * updateDate : 2018-01-31 21:11:00
         * repairShopId : 请选择维修商
         * backName :
         * backAddress :
         * lastLongitude :
         * scStartTime : 2018-01-31 21:20:20
         * detailId : 313e93d2dd5b41dc94b2ab239d1de0fc
         */

        private String startLongitude;
        private String isBookEnd;
        private String backDriverIncome;
        private String orderCode;
        private String washCarScore;
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
        private String repairShopUserScore;
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
        private String price;

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

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

        public String getWashCarScore() {
            return washCarScore;
        }

        public void setWashCarScore(String washCarScore) {
            this.washCarScore = washCarScore;
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

        public String getRepairShopUserScore() {
            return repairShopUserScore;
        }

        public void setRepairShopUserScore(String repairShopUserScore) {
            this.repairShopUserScore = repairShopUserScore;
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

    public static class RepairOrderBean {
        /**
         * id : 313e93d2dd5b41dc94b2ab239d1de0fc
         * status : 1
         * description : 坏了很多东西
         * name : 维修订单
         * isNewRecord : false
         * remarks :
         * createDate : 2018-01-31 21:11:00
         * updateDate : 2018-01-31 21:11:00
         * delFlag : 0
         */

        private String id;
        private int status;
        private String description;
        private String name;
        private boolean isNewRecord;
        private String remarks;
        private String createDate;
        private String updateDate;
        private String delFlag;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }

    public static class UserCarBean {
        /**
         * lastMainDate : 2018-04-22 00:00:00
         * idNo :
         * registerCity : 深圳
         * mileage : 50000
         * status : 1
         * registerDate :
         * vinNo :
         * carname : 标致20082015款 1.6L 自动 翡翠型 玩酷版
         * remarks :
         * buyDate :
         * insuranceName :
         * updateDate : 2018-01-31 20:42:58
         * cardNo : 粤B00000
         * id : e6abcc8a72324f2e97197af289c1feed
         * recomMaintMile :
         * insuranceDate :
         * owner : 水无伤
         * isNewRecord : false
         * insuranceId :
         * carConfigureId : ebe839c82cb9426b9382dce8010d236b
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
