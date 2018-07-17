package com.dream.moka.Bean;

/**
 * Created by Administrator on 2017/12/19 0019.
 */

public class CarResultBean {
    /**
     * carId : 1
     * brandName : 奔驰
     * branLogo : /mocar/userfiles/1/_thumbs/images/car/carBrand/2017/11/timg.jpg
     * cateName : 奥迪A7
     */

    private String carId;
    private String brandName;
    private String branLogo;
    private String cateName;
    private String configName;
    private String status;
    private String configId;
    private String userName;
    private String cardNo;
    private String mileage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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
}
