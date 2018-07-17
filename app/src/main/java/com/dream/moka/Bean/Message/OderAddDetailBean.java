package com.dream.moka.Bean.Message;

/**
 * Created by Administrator on 2018/2/2 0002.
 */
public class OderAddDetailBean {

    /**
     * orderCode : 201801261419011438mt
     * shopName : 俊铨维修商
     * orderIncrease : {"status":2,"remarks":"","partPic":"/MoCar/userfiles/repair/2018012614464985afb174-259c-427a-998f-144cd7343db3.png","type":1,"updateDate":"2018-01-26 20:26:25","addAmount":"","id":"9bfb043d627a45c5910e14648ac3e8af","increasePic":"/MoCar/userfiles/repair/20180126144643a375883d-8a8a-44b0-b10d-bdda7a0ee50b.png","description":"车辆有问题","isNewRecord":false,"repairAmount":"","createDate":"2018-01-26 14:46:54","orderId":"7262de42d1d1432e8fc2af0554a194f6","addTime":"","delFlag":"0","descPic":"/MoCar/userfiles/repair/201801261446378ffb5908-4a64-4242-a0c8-804fd52d04bb.png"}
     * userCar : {"registerCity":"","carConfigure":"","registerDate":"","remarks":"","insuranceName":"","id":"","cardNo":"粤B5685690","insuranceDate":"","isNewRecord":true,"insuranceId":"","createDate":"","idNo":"","lastMainDate":"2018-01-22 00:00:00","mileage":8000,"status":"","vinNo":"","carname":"奥迪A32016款 Limousine 40TFSI 豪华型","buyDate":"","updateDate":"","recomMaintMile":"","owner":"","carConfigureId":"","user":"","drivingLicense":"","delFlag":"0"}
     */

    private String orderCode;
    private String shopName;
    private OrderIncreaseBean orderIncrease;
    private UserCarBean userCar;
    private OrderAddBean.OrdersBean orders;

    private String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderAddBean.OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrderAddBean.OrdersBean orders) {
        this.orders = orders;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public OrderIncreaseBean getOrderIncrease() {
        return orderIncrease;
    }

    public void setOrderIncrease(OrderIncreaseBean orderIncrease) {
        this.orderIncrease = orderIncrease;
    }

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public static class OrderIncreaseBean {
        /**
         * status : 2
         * remarks :
         * partPic : /MoCar/userfiles/repair/2018012614464985afb174-259c-427a-998f-144cd7343db3.png
         * type : 1
         * updateDate : 2018-01-26 20:26:25
         * addAmount :
         * id : 9bfb043d627a45c5910e14648ac3e8af
         * increasePic : /MoCar/userfiles/repair/20180126144643a375883d-8a8a-44b0-b10d-bdda7a0ee50b.png
         * description : 车辆有问题
         * isNewRecord : false
         * repairAmount :
         * createDate : 2018-01-26 14:46:54
         * orderId : 7262de42d1d1432e8fc2af0554a194f6
         * addTime :
         * delFlag : 0
         * descPic : /MoCar/userfiles/repair/201801261446378ffb5908-4a64-4242-a0c8-804fd52d04bb.png
         */

        private String status;
        private String remarks;
        private String partPic;
        private String type;
        private String updateDate;
        private String addAmount;
        private String id;
        private String increasePic;
        private String description;
        private boolean isNewRecord;
        private String repairAmount;
        private String createDate;
        private String orderId;
        private String addTime;
        private String delFlag;
        private String descPic;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getPartPic() {
            return partPic;
        }

        public void setPartPic(String partPic) {
            this.partPic = partPic;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getAddAmount() {
            return addAmount;
        }

        public void setAddAmount(String addAmount) {
            this.addAmount = addAmount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIncreasePic() {
            return increasePic;
        }

        public void setIncreasePic(String increasePic) {
            this.increasePic = increasePic;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getRepairAmount() {
            return repairAmount;
        }

        public void setRepairAmount(String repairAmount) {
            this.repairAmount = repairAmount;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getAddTime() {
            return addTime;
        }

        public void setAddTime(String addTime) {
            this.addTime = addTime;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getDescPic() {
            return descPic;
        }

        public void setDescPic(String descPic) {
            this.descPic = descPic;
        }
    }

    public static class UserCarBean {
        /**
         * registerCity :
         * carConfigure :
         * registerDate :
         * remarks :
         * insuranceName :
         * id :
         * cardNo : 粤B5685690
         * insuranceDate :
         * isNewRecord : true
         * insuranceId :
         * createDate :
         * idNo :
         * lastMainDate : 2018-01-22 00:00:00
         * mileage : 8000
         * status :
         * vinNo :
         * carname : 奥迪A32016款 Limousine 40TFSI 豪华型
         * buyDate :
         * updateDate :
         * recomMaintMile :
         * owner :
         * carConfigureId :
         * user :
         * drivingLicense :
         * delFlag : 0
         */

        private String registerCity;
        private String carConfigure;
        private String registerDate;
        private String remarks;
        private String insuranceName;
        private String id;
        private String cardNo;
        private String insuranceDate;
        private boolean isNewRecord;
        private String insuranceId;
        private String createDate;
        private String idNo;
        private String lastMainDate;
        private String mileage;
        private String status;
        private String vinNo;
        private String carname;
        private String buyDate;
        private String updateDate;
        private String recomMaintMile;
        private String owner;
        private String carConfigureId;
        private String user;
        private String drivingLicense;
        private String delFlag;

        public String getRegisterCity() {
            return registerCity;
        }

        public void setRegisterCity(String registerCity) {
            this.registerCity = registerCity;
        }

        public String getCarConfigure() {
            return carConfigure;
        }

        public void setCarConfigure(String carConfigure) {
            this.carConfigure = carConfigure;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
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

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getInsuranceDate() {
            return insuranceDate;
        }

        public void setInsuranceDate(String insuranceDate) {
            this.insuranceDate = insuranceDate;
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

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getLastMainDate() {
            return lastMainDate;
        }

        public void setLastMainDate(String lastMainDate) {
            this.lastMainDate = lastMainDate;
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

        public String getBuyDate() {
            return buyDate;
        }

        public void setBuyDate(String buyDate) {
            this.buyDate = buyDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getRecomMaintMile() {
            return recomMaintMile;
        }

        public void setRecomMaintMile(String recomMaintMile) {
            this.recomMaintMile = recomMaintMile;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public String getCarConfigureId() {
            return carConfigureId;
        }

        public void setCarConfigureId(String carConfigureId) {
            this.carConfigureId = carConfigureId;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getDrivingLicense() {
            return drivingLicense;
        }

        public void setDrivingLicense(String drivingLicense) {
            this.drivingLicense = drivingLicense;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
