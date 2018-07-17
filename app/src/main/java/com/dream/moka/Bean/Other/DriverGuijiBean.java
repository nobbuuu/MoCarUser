package com.dream.moka.Bean.Other;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class DriverGuijiBean {

    /**
     * driverHis : {"updateDate":"2018-03-19 11:34:26","distance":"6778","orderId":"54cd00fcd08b46b7be433d3cc6e06d84","latitude":"22.6866510000,22.6866410000,22.6866360000,22.6866560000,22.6866360000,22.6866410000,22.6866660000,22.6866710000,22.6866810000,22.6866810000,22.6866950000, 22.6866950000,22.6866660000,22.6864480000,22.6863240000,22.6861610000,22.6860470000,22.6858740000,22.6856810000,22.6854680000,22.6852300000,22.6849670000,22.6848240000,22.6845410000, 22.6842540000,22.6838780000,22.6836000000,22.6833870000,22.6831000000,22.6828820000","id":"c20c8a7b7bc74bbf942d1e54e103d647","isNewRecord":false,"delFlag":"0","sendBack":"1","remarks":"","createDate":"2018-03-19 10:48:27","longitude":"113.9521240000,113.9523440000,113.9525750000,113.9528220000,113.9530790000,113.9534440000,113.9536960000,113.9540930000,113.9544360000,113.9549670000, 113.9554500000,113.9559380000,113.9566890000,113.9573280000,113.9576010000,113.9579280000,113.9582070000,113.9585560000,113.9589750000,113.9593020000,113.9595160000, 113.9597090000,113.9597520000,113.9598110000,113.9598220000,113.9598380000,113.9598220000,113.9598330000,113.9598440000,113.9598650000","status":""}
     * list : [{"latitude":"22.6866510000","longtitude":"113.9521240000"},{"latitude":"22.6866410000","longtitude":"113.9523440000"},{"latitude":"22.6866360000","longtitude":"113.9525750000"},{"latitude":"22.6866560000","longtitude":"113.9528220000"},{"latitude":"22.6866360000","longtitude":"113.9530790000"},{"latitude":"22.6866410000","longtitude":"113.9534440000"},{"latitude":"22.6866660000","longtitude":"113.9536960000"},{"latitude":"22.6866710000","longtitude":"113.9540930000"},{"latitude":"22.6866810000","longtitude":"113.9544360000"},{"latitude":"22.6866810000","longtitude":"113.9549670000"},{"latitude":"22.6866950000","longtitude":"113.9554500000"},{"latitude":"22.6866950000","longtitude":"113.9559380000"},{"latitude":"22.6866660000","longtitude":"113.9566890000"},{"latitude":"22.6864480000","longtitude":"113.9573280000"},{"latitude":"22.6863240000","longtitude":"113.9576010000"},{"latitude":"22.6861610000","longtitude":"113.9579280000"},{"latitude":"22.6860470000","longtitude":"113.9582070000"},{"latitude":"22.6858740000","longtitude":"113.9585560000"},{"latitude":"22.6856810000","longtitude":"113.9589750000"},{"latitude":"22.6854680000","longtitude":"113.9593020000"},{"latitude":"22.6852300000","longtitude":"113.9595160000"},{"latitude":"22.6849670000","longtitude":"113.9597090000"},{"latitude":"22.6848240000","longtitude":"113.9597520000"},{"latitude":"22.6845410000","longtitude":"113.9598110000"},{"latitude":"22.6842540000","longtitude":"113.9598220000"},{"latitude":"22.6838780000","longtitude":"113.9598380000"},{"latitude":"22.6836000000","longtitude":"113.9598220000"},{"latitude":"22.6833870000","longtitude":"113.9598330000"},{"latitude":"22.6831000000","longtitude":"113.9598440000"},{"latitude":"22.6828820000","longtitude":"113.9598650000"}]
     */

    private DriverHisBean driverHis;
    private List<GuijiBean> list;

    public DriverHisBean getDriverHis() {
        return driverHis;
    }

    public void setDriverHis(DriverHisBean driverHis) {
        this.driverHis = driverHis;
    }

    public List<GuijiBean> getList() {
        return list;
    }

    public void setList(List<GuijiBean> list) {
        this.list = list;
    }

    public static class DriverHisBean {
        /**
         * updateDate : 2018-03-19 11:34:26
         * distance : 6778
         * orderId : 54cd00fcd08b46b7be433d3cc6e06d84
         * latitude : 22.6866510000,22.6866410000,22.6866360000,22.6866560000,22.6866360000,22.6866410000,22.6866660000,22.6866710000,22.6866810000,22.6866810000,22.6866950000, 22.6866950000,22.6866660000,22.6864480000,22.6863240000,22.6861610000,22.6860470000,22.6858740000,22.6856810000,22.6854680000,22.6852300000,22.6849670000,22.6848240000,22.6845410000, 22.6842540000,22.6838780000,22.6836000000,22.6833870000,22.6831000000,22.6828820000
         * id : c20c8a7b7bc74bbf942d1e54e103d647
         * isNewRecord : false
         * delFlag : 0
         * sendBack : 1
         * remarks :
         * createDate : 2018-03-19 10:48:27
         * longitude : 113.9521240000,113.9523440000,113.9525750000,113.9528220000,113.9530790000,113.9534440000,113.9536960000,113.9540930000,113.9544360000,113.9549670000, 113.9554500000,113.9559380000,113.9566890000,113.9573280000,113.9576010000,113.9579280000,113.9582070000,113.9585560000,113.9589750000,113.9593020000,113.9595160000, 113.9597090000,113.9597520000,113.9598110000,113.9598220000,113.9598380000,113.9598220000,113.9598330000,113.9598440000,113.9598650000
         * status :
         */

        private String updateDate;
        private String distance;
        private String orderId;
        private String latitude;
        private String id;
        private boolean isNewRecord;
        private String delFlag;
        private String sendBack;
        private String remarks;
        private String createDate;
        private String longitude;
        private String status;

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
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

        public String getSendBack() {
            return sendBack;
        }

        public void setSendBack(String sendBack) {
            this.sendBack = sendBack;
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

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
