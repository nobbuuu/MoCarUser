package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class OrderListResultBean {

    /**
     * startItem : 0
     * total : 2
     * pageSize : 10
     * list : [{"startLongitude":"113.9404843747616","isBookEnd":"","backDriverIncome":"","orderCode":"201801152004023640mt","repairShopIncome":"","technicianId":"","increaseStatus":"","repairShopScore":"","sendDriverIncome":"","bookEndTime":"","orderAmount":"100.00","isNewRecord":false,"finishTime":"","driverStatus":"","bookSendTime":"","technicianIncome":"","backDriverScore":"","startLatitude":"22.554613904825004","orderType":1,"status":2,"sendTime":"","miles":8266,"transNo":"2018011521001004500207607761","evaluateTime":"","sendDriverScore":"","increaseType":"","transChannel":"0","userCouponId":"","delFlag":"0","technicianScore":"","carId":"1509d23b9f0b4595a3943b1af7023f35","remarks":"","expectedDate":4,"sendDriverId":"","lastLatitude":"22.554613904825004","serviceName":"","id":"3cf1ba46be8740f98bf3bd56b1bd01fe","backTime":"","backDriverId":"","sendMobile":"","backMobile":"","createDate":"2018-01-15 20:04:03","isBook":0,"payAmount":"0.01","sendAddress":"高新北六道36号","sendName":"","updateDate":"2018-01-15 20:04:27","repairShopId":"fdbe53f00f29466c83d73fcb49a88032","backName":"","backAddress":"高新北六道36号","lastLongitude":"113.9404843747616","detailId":""},{"startLongitude":"113.9404843747616","isBookEnd":"","backDriverIncome":"","orderCode":"201801151911119671mt","repairShopIncome":"","technicianId":"","increaseStatus":"","repairShopScore":"","sendDriverIncome":"","bookEndTime":"","orderAmount":"100.00","isNewRecord":false,"finishTime":"","driverStatus":"","bookSendTime":"","technicianIncome":"","backDriverScore":"","startLatitude":"22.554613904825004","orderType":1,"status":1,"sendTime":"","miles":38000,"transNo":"","evaluateTime":"","sendDriverScore":"","increaseType":"","transChannel":"","userCouponId":"","delFlag":"0","technicianScore":"","carId":"a1f8a5dda0ba4b63abd6ab0a63bbc1ce","remarks":"","expectedDate":4,"sendDriverId":"","lastLatitude":"22.554613904825004","serviceName":"","id":"cbfc3ac65b8f4bd6b66ffaff3f081c92","backTime":"","backDriverId":"","sendMobile":"","backMobile":"","createDate":"2018-01-15 19:11:11","isBook":0,"payAmount":"700.00","sendAddress":"高新北六道36号","sendName":"","updateDate":"2018-01-15 19:11:11","repairShopId":"fdbe53f00f29466c83d73fcb49a88032","backName":"","backAddress":"高新北六道36号","lastLongitude":"113.9404843747616","detailId":""}]
     * totalPage : 1
     * pageNum : 1
     */

    private int startItem;
    private int total;
    private int pageSize;
    private int totalPage;
    private int pageNum;
    private List<ListBean> list;

    public int getStartItem() {
        return startItem;
    }

    public void setStartItem(int startItem) {
        this.startItem = startItem;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * startLongitude : 113.9404843747616
         * isBookEnd :
         * backDriverIncome :
         * orderCode : 201801152004023640mt
         * repairShopIncome :
         * technicianId :
         * increaseStatus :
         * repairShopScore :
         * sendDriverIncome :
         * bookEndTime :
         * orderAmount : 100.00
         * isNewRecord : false
         * finishTime :
         * driverStatus :
         * bookSendTime :
         * technicianIncome :
         * backDriverScore :
         * startLatitude : 22.554613904825004
         * orderType : 1
         * status : 2
         * sendTime :
         * miles : 8266
         * transNo : 2018011521001004500207607761
         * evaluateTime :
         * sendDriverScore :
         * increaseType :
         * transChannel : 0
         * userCouponId :
         * delFlag : 0
         * technicianScore :
         * carId : 1509d23b9f0b4595a3943b1af7023f35
         * remarks :
         * expectedDate : 4
         * sendDriverId :
         * lastLatitude : 22.554613904825004
         * serviceName :
         * id : 3cf1ba46be8740f98bf3bd56b1bd01fe
         * backTime :
         * backDriverId :
         * sendMobile :
         * backMobile :
         * createDate : 2018-01-15 20:04:03
         * isBook : 0
         * payAmount : 0.01
         * sendAddress : 高新北六道36号
         * sendName :
         * updateDate : 2018-01-15 20:04:27
         * repairShopId : fdbe53f00f29466c83d73fcb49a88032
         * backName :
         * backAddress : 高新北六道36号
         * lastLongitude : 113.9404843747616
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
        private String repairShopUserScore;
        private carBean car;

        public String getRepairShopUserScore() {
            return repairShopUserScore;
        }

        public void setRepairShopUserScore(String repairShopUserScore) {
            this.repairShopUserScore = repairShopUserScore;
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
    public static class carBean{
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
