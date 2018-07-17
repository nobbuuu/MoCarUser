package com.dream.moka.Bean.EventBusBean;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class MaintainBusBean {
    private String carId;
    private String repairShopId;
    private String shopType;

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getRepairShopId() {
        return repairShopId;
    }

    public void setRepairShopId(String repairShopId) {
        this.repairShopId = repairShopId;
    }
}
