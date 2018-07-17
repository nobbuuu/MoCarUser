package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class AllOrderResultBean {
    /**
     * total : 2
     * startItem : 0
     * items : [{"startLongitude":"113.94071504473688","isBookEnd":"","backDriverIncome":"","orderCode":"201801172336453804mt","repairShopIncome":"","technicianId":"","increaseStatus":"","repairShopScore":"","sendDriverIncome":"","bookEndTime":"","orderAmount":"139.00","isNewRecord":false,"finishTime":"2018-01-17 23:49:59","driverStatus":"0","bookSendTime":"","technicianIncome":"","backDriverScore":"","startLatitude":"22.55543257022782","orderType":1,"status":6,"sendTime":"2018-01-17 23:46:53","miles":10000,"transNo":"2018011721001004760259262563","evaluateTime":"2018-01-17 23:49:59","sendDriverScore":"","increaseType":"","transChannel":"0","userCouponId":"","delFlag":"0","technicianScore":"","carId":"0fc156ca90ba4e9087de0560c35c736f","remarks":"","expectedDate":"3.00","sendDriverId":"d7348e28c0a94c5588b4a77c3c4eb588","lastLatitude":"22.554659730399507","serviceName":"","id":"8f7ba7c7fd3140c8942d780ac30be98c","backTime":"","backDriverId":"","sendMobile":"18571112323","backMobile":"18576618690","createDate":"2018-01-17 23:36:46","isBook":0,"payAmount":"0.01","sendAddress":"高新北四道11号","sendName":"王勇1","updateDate":"","repairShopId":"fdbe53f00f29466c83d73fcb49a88032","backName":"王勇2","backAddress":"高新北六道36号","lastLongitude":"113.94059568643571","detailId":""},{"startLongitude":"113.94055411219597","isBookEnd":"","backDriverIncome":"","orderCode":"201801171519528125mt","repairShopIncome":"","technicianId":"","increaseStatus":"","repairShopScore":"","sendDriverIncome":"","bookEndTime":"","orderAmount":"300.00","isNewRecord":false,"finishTime":"2018-01-17 23:38:59","driverStatus":"0","bookSendTime":"","technicianIncome":"","backDriverScore":"","startLatitude":"22.55459161129674","orderType":1,"status":6,"sendTime":"2018-01-17 22:09:20","miles":9000,"transNo":"2018011721001004160213195240","evaluateTime":"2018-01-17 23:38:59","sendDriverScore":"","increaseType":"","transChannel":"0","userCouponId":"","delFlag":"0","technicianScore":"","carId":"6ea60aa5883b413cac02016c9c7fe891","remarks":"","expectedDate":"4.00","sendDriverId":"d7348e28c0a94c5588b4a77c3c4eb588","lastLatitude":"22.55459161129674","serviceName":"","id":"456fff75b2934c1cb9f23666e66e023b","backTime":"","backDriverId":"","sendMobile":"13128861359","backMobile":"13128861359","createDate":"2018-01-17 15:19:53","isBook":0,"payAmount":"0.01","sendAddress":"南山区高新北六道36号","sendName":"马云他爸爸","updateDate":"","repairShopId":"fdbe53f00f29466c83d73fcb49a88032","backName":"马云他爸爸","backAddress":"南山区高新北六道36号","lastLongitude":"113.94055411219597","detailId":""}]
     * pageSize : 10
     * totalPage : 1
     * pageNum : 1
     */

    private int total;
    private int startItem;
    private int pageSize;
    private int totalPage;
    private int pageNum;
    private List<ItemsBean> items;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartItem() {
        return startItem;
    }

    public void setStartItem(int startItem) {
        this.startItem = startItem;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
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
         * finishTime : 2018-01-17 23:49:59
         * driverStatus : 0
         * bookSendTime :
         * technicianIncome :
         * backDriverScore :
         * startLatitude : 22.55543257022782
         * orderType : 1
         * status : 6
         * sendTime : 2018-01-17 23:46:53
         * miles : 10000
         * transNo : 2018011721001004760259262563
         * evaluateTime : 2018-01-17 23:49:59
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
         * backDriverId :
         * sendMobile : 18571112323
         * backMobile : 18576618690
         * createDate : 2018-01-17 23:36:46
         * isBook : 0
         * payAmount : 0.01
         * sendAddress : 高新北四道11号
         * sendName : 王勇1
         * updateDate :
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
        private String jcSendTime;
        private String scSendTime;
        private carBean car;

        public String getJcSendTime() {
            return jcSendTime;
        }

        public void setJcSendTime(String jcSendTime) {
            this.jcSendTime = jcSendTime;
        }

        public String getScSendTime() {
            return scSendTime;
        }

        public void setScSendTime(String scSendTime) {
            this.scSendTime = scSendTime;
        }

        public carBean getCar() {
            return car;
        }

        public void setCar(carBean car) {
            this.car = car;
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

    public static class carBean {
        private String cardNo;
        private String carname;

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCarname() {
            return carname;
        }

        public void setCarname(String carname) {
            this.carname = carname;
        }
    }
}
