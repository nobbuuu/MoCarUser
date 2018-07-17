package com.dream.moka.Bean;

/**
 * Created by Administrator on 2018/1/11 0011.
 */

public class AddServiceUserCarResultBean {

    /**
     * latestMile : 0
     * userCar : {"owner":"sdfs啥地方啥地方萨芬","registerCity":"深圳","updateDate":"2018-01-11 19:32:58","buyDate":"","recomMaintMile":10000,"isNewRecord":false,"delFlag":"0","vinNo":"","cardNo":"粤B85726668","idNo":"","insuranceDate":"","drivingLicense":"","lastMainDate":"2017-12-08 00:00:00","carname":"奥迪Q7 插电混动2017款 45 e-tron","insuranceId":"","insuranceName":"","id":"54262fb13963447ea4946df7b23aadac","carConfigureId":"c691337ffacd41c8844f0fa5adcb8c45","remarks":"","createDate":"","mileage":8888,"registerDate":"","status":""}
     * isFirstTime : true
     */

    private String latestMile;
    private UserCarBean userCar;
    private String isFirstTime;

    public String getLatestMile() {
        return latestMile;
    }

    public void setLatestMile(String latestMile) {
        this.latestMile = latestMile;
    }

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public String getIsFirstTime() {
        return isFirstTime;
    }

    public void setIsFirstTime(String isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    public static class UserCarBean {
        /**
         * owner : sdfs啥地方啥地方萨芬
         * registerCity : 深圳
         * updateDate : 2018-01-11 19:32:58
         * buyDate :
         * recomMaintMile : 10000
         * isNewRecord : false
         * delFlag : 0
         * vinNo :
         * cardNo : 粤B85726668
         * idNo :
         * insuranceDate :
         * drivingLicense :
         * lastMainDate : 2017-12-08 00:00:00
         * carname : 奥迪Q7 插电混动2017款 45 e-tron
         * insuranceId :
         * insuranceName :
         * id : 54262fb13963447ea4946df7b23aadac
         * carConfigureId : c691337ffacd41c8844f0fa5adcb8c45
         * remarks :
         * createDate :
         * mileage : 8888
         * registerDate :
         * status :
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

        public String getRecomMaintMile() {
            return recomMaintMile;
        }

        public void setRecomMaintMile(String recomMaintMile) {
            this.recomMaintMile = recomMaintMile;
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
}
