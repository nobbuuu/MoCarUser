package com.dream.moka.Bean.Maintain;

/**
 * Created by Administrator on 2018/1/11 0011.
 */
public class AddCarResultBean {

    /**
     * isFirstTime : true
     * userCar : {"lastMainDate":"2017-11-10 00:00:00","registerCity":"","mileage":"5000.00","status":0,"registerDate":"2017-11-14 00:00:00","carname":"NB的车","remarks":"","buyDate":"","updateDate":"","cardNo":"赣B 11111","id":"1","recomMaintMile":5000,"isNewRecord":false,"owner":"","carConfigureId":"2a9aa0f7fb1a4dbf92500ef1e4688b9b","createDate":"","delFlag":"0","drivingLicense":""}
     * latestMile : 上次保养公里数
     */

    private String isFirstTime;
    private UserCarBean userCar;
    private String latestMile;

    public String getIsFirstTime() {
        return isFirstTime;
    }

    public void setIsFirstTime(String isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public String getLatestMile() {
        return latestMile;
    }

    public void setLatestMile(String latestMile) {
        this.latestMile = latestMile;
    }

    public static class UserCarBean {
        /**
         * lastMainDate : 2017-11-10 00:00:00
         * registerCity :
         * mileage : 5000.00
         * status : 0
         * registerDate : 2017-11-14 00:00:00
         * carname : NB的车
         * remarks :
         * buyDate :
         * updateDate :
         * cardNo : 赣B 11111
         * id : 1
         * recomMaintMile : 5000
         * isNewRecord : false
         * owner :
         * carConfigureId : 2a9aa0f7fb1a4dbf92500ef1e4688b9b
         * createDate :
         * delFlag : 0
         * drivingLicense :
         */

        private String lastMainDate;
        private String registerCity;
        private String mileage;
        private int status;
        private String registerDate;
        private String carname;
        private String remarks;
        private String buyDate;
        private String updateDate;
        private String cardNo;
        private String id;
        private int recomMaintMile;
        private boolean isNewRecord;
        private String owner;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
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

        public int getRecomMaintMile() {
            return recomMaintMile;
        }

        public void setRecomMaintMile(int recomMaintMile) {
            this.recomMaintMile = recomMaintMile;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
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
