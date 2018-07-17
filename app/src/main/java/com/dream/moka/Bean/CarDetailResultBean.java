package com.dream.moka.Bean;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class CarDetailResultBean {

    /**
     * configName : 1.8T
     * brandName : 奥迪
     * userCar : {"owner":"宋董","registerCity":"深圳市","updateDate":"2017-12-21 11:54:42","buyDate":"","recomMaintMile":0,"isNewRecord":false,"delFlag":"0","vinNo":"123456","cardNo":"粤B88888888","idNo":"","insuranceDate":"","drivingLicense":"","lastMainDate":"","carname":"奥迪奥迪A71.8T","insuranceId":"","id":"ff3c5842a06e4a5f9dc756f4d83a901e","carConfigureId":"2a9aa0f7fb1a4dbf92500ef1e4688b9b","remarks":"","createDate":"2017-12-21 11:54:42","mileage":15689,"registerDate":"","status":1}
     * branLogo : /mocar/userfiles/1/_thumbs/images/car/carBrand/2017/11/timg%20(5).jpg
     * cateName : 奥迪A7
     * carInsurance : {}
     */

    private String configName;
    private String brandName;
    private UserCarBean userCar;
    private String branLogo;
    private String cateName;
    private String carId;
    private CarInsuranceBean carInsurance;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public String getBranLogo() {
        return branLogo;
    }

    public void setBranLogo(String branLogo) {
        this.branLogo = branLogo;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public CarInsuranceBean getCarInsurance() {
        return carInsurance;
    }

    public void setCarInsurance(CarInsuranceBean carInsurance) {
        this.carInsurance = carInsurance;
    }

    public static class UserCarBean {
        /**
         * owner : 宋董
         * registerCity : 深圳市
         * updateDate : 2017-12-21 11:54:42
         * buyDate :
         * recomMaintMile : 0
         * isNewRecord : false
         * delFlag : 0
         * vinNo : 123456
         * cardNo : 粤B88888888
         * idNo :
         * insuranceDate :
         * drivingLicense :
         * lastMainDate :
         * carname : 奥迪奥迪A71.8T
         * insuranceId :
         * id : ff3c5842a06e4a5f9dc756f4d83a901e
         * carConfigureId : 2a9aa0f7fb1a4dbf92500ef1e4688b9b
         * remarks :
         * createDate : 2017-12-21 11:54:42
         * mileage : 15689
         * registerDate :
         * status : 1
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

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class CarInsuranceBean {
        private String  name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
