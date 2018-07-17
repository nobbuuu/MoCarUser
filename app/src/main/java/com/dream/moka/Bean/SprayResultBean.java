package com.dream.moka.Bean;

import com.dream.moka.Bean.Message.OrderAddBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/14 0014.
 */

public class SprayResultBean implements Serializable{

    /**
     * shopData : [{"updateDate":"2017-11-10 17:32:17","price":"500.00","name":"车顶","id":"4a4e58f5e2c2451a8f2f1dd888cfd6dd","isNewRecord":false,"delFlag":"0","remarks":"","createDate":"2017-11-10 17:32:17","status":""},{"updateDate":"2017-11-10 17:32:11","price":"500.00","name":"前引擎盖","id":"4abf5324994047f282f6707a766d48ce","isNewRecord":false,"delFlag":"0","remarks":"","createDate":"2017-11-10 17:32:11","status":""}]
     * repairId : 8dcfb04c07d44cf8a5d6b6ae9fe611df
     * repairName : 条子维修商
     * sprayIdStr : 4a4e58f5e2c2451a8f2f1dd888cfd6dd,4abf5324994047f282f6707a766d48ce
     * tokenId : EUTXTI7W6RCLJO6RP2J1XK2MWPMNH0
     * latitude : 22.554536
     * longitude : 113.939681
     * carId : ce0bc5cb28aa4c80a231c6b2315ec49b
     * carInfo : {"configName":"2017款 1.5L 手动 尊贵版","brandName":"福特","configId":"000738d728844e4abc54ea525aa8ff05","branLogo":"http://pic1.jisuapi.cn/car/static/images/logo/300/52.png","cateName":"翼搏","carId":"ce0bc5cb28aa4c80a231c6b2315ec49b","status":1}
     * sum : 1000
     */

    private String repairId;
    private String repairName;
    private String sprayIdStr;
    private String tokenId;
    private String latitude;
    private String longitude;
    private String carId;
    private CarInfoBean carInfo;
    private String sum;
    private List<OrderAddBean.ListSpraySideBean> shopData;

    public String getRepairId() {
        return repairId;
    }

    public void setRepairId(String repairId) {
        this.repairId = repairId;
    }

    public String getRepairName() {
        return repairName;
    }

    public void setRepairName(String repairName) {
        this.repairName = repairName;
    }

    public String getSprayIdStr() {
        return sprayIdStr;
    }

    public void setSprayIdStr(String sprayIdStr) {
        this.sprayIdStr = sprayIdStr;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public CarInfoBean getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfoBean carInfo) {
        this.carInfo = carInfo;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    public List<OrderAddBean.ListSpraySideBean> getShopData() {
        return shopData;
    }

    public void setShopData(List<OrderAddBean.ListSpraySideBean> shopData) {
        this.shopData = shopData;
    }

    public static class CarInfoBean implements Serializable{
        /**
         * configName : 2017款 1.5L 手动 尊贵版
         * brandName : 福特
         * configId : 000738d728844e4abc54ea525aa8ff05
         * branLogo : http://pic1.jisuapi.cn/car/static/images/logo/300/52.png
         * cateName : 翼搏
         * carId : ce0bc5cb28aa4c80a231c6b2315ec49b
         * status : 1
         */

        private String configName;
        private String brandName;
        private String configId;
        private String branLogo;
        private String cateName;
        private String carId;
        private String status;

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

        public String getConfigId() {
            return configId;
        }

        public void setConfigId(String configId) {
            this.configId = configId;
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

        public String getCarId() {
            return carId;
        }

        public void setCarId(String carId) {
            this.carId = carId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class ShopDataBean implements Serializable{
        /**
         * updateDate : 2017-11-10 17:32:17
         * price : 500.00
         * name : 车顶
         * id : 4a4e58f5e2c2451a8f2f1dd888cfd6dd
         * isNewRecord : false
         * delFlag : 0
         * remarks :
         * createDate : 2017-11-10 17:32:17
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
}
