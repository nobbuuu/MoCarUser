package com.dream.moka.Bean.Merchant;

import java.util.List;

/**
 * Created by Administrator on 2018/1/13 0013.
 */
public class RepairShopInfoBean {

    /**
     * serviceItemList : []
     * repairShop : {"businessLicense":"/MoCar/userfiles/1/_thumbs/images/shop/repairShop/2018/01/050224z4muhck6gzi2mckh.jpg","income":"","updateDate":"2018-01-10 10:24:23","city":"","latitude":"22.540211","dist":"","orderStatus":1,"delFlag":"0","contactTel":"13055558888","content":"","qualifications":"/MoCar/userfiles/1/_thumbs/images/shop/repairShop/2018/01/050224z4muhck6gzi2mckh.jpg","score":"5.00","province":"","contact":"俊铨","id":"e585b0dbe0504d14972ab2fff98352c8","createDate":"2018-01-10 10:23:44","longitude":"113.953831","address":"南山区高新园北6道","servicesIds":"","stores":"","isNewRecord":false,"picture":"/MoCar/userfiles/1/_thumbs/images/shop/repairShop/2018/01/050224z4muhck6gzi2mckh.jpg","technicalCertificate":"/MoCar/userfiles/1/_thumbs/images/shop/repairShop/2018/01/050224z4muhck6gzi2mckh.jpg","name":"测试【深圳南山区】","shopType":0,"openDate":"2018-01-10 00:00:00","region":"","remarks":"","status":1}
     */

    private RepairShopBean repairShop;
    private List<ServiceItemListBean> serviceItemList;

    public RepairShopBean getRepairShop() {
        return repairShop;
    }

    public void setRepairShop(RepairShopBean repairShop) {
        this.repairShop = repairShop;
    }

    public List<ServiceItemListBean> getServiceItemList() {
        return serviceItemList;
    }

    public void setServiceItemList(List<ServiceItemListBean> serviceItemList) {
        this.serviceItemList = serviceItemList;
    }

    public static class ServiceItemListBean{

        /**
         * status : 1
         * remarks :
         * partTypeId : 3762bb9b7bcf4d30b3791ed1de28432b
         * type : 0
         * updateDate : 2018-01-20 12:43:00
         * id : 01afe66ef72a4e07bdc2be1280299a04
         * selected :
         * price : 100.00
         * name : 更换冷却液B
         * isNewRecord : false
         * createDate : 2018-01-20 12:43:00
         * serviceHour : 1
         * delFlag : 0
         */

        private String status;
        private String remarks;
        private String partTypeId;
        private String type;
        private String updateDate;
        private String id;
        private String selected;
        private String price;
        private String name;
        private boolean isNewRecord;
        private String createDate;
        private String serviceHour;
        private String delFlag;


        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getPartTypeId() {
            return partTypeId;
        }

        public void setPartTypeId(String partTypeId) {
            this.partTypeId = partTypeId;
        }


        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
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

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getServiceHour() {
            return serviceHour;
        }

        public void setServiceHour(String serviceHour) {
            this.serviceHour = serviceHour;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }

    public static class RepairShopBean {
        /**
         * businessLicense : /MoCar/userfiles/1/_thumbs/images/shop/repairShop/2018/01/050224z4muhck6gzi2mckh.jpg
         * income :
         * updateDate : 2018-01-10 10:24:23
         * city :
         * latitude : 22.540211
         * dist :
         * orderStatus : 1
         * delFlag : 0
         * contactTel : 13055558888
         * content :
         * qualifications : /MoCar/userfiles/1/_thumbs/images/shop/repairShop/2018/01/050224z4muhck6gzi2mckh.jpg
         * score : 5.00
         * province :
         * contact : 俊铨
         * id : e585b0dbe0504d14972ab2fff98352c8
         * createDate : 2018-01-10 10:23:44
         * longitude : 113.953831
         * address : 南山区高新园北6道
         * servicesIds :
         * stores :
         * isNewRecord : false
         * picture : /MoCar/userfiles/1/_thumbs/images/shop/repairShop/2018/01/050224z4muhck6gzi2mckh.jpg
         * technicalCertificate : /MoCar/userfiles/1/_thumbs/images/shop/repairShop/2018/01/050224z4muhck6gzi2mckh.jpg
         * name : 测试【深圳南山区】
         * shopType : 0
         * openDate : 2018-01-10 00:00:00
         * region :
         * remarks :
         * status : 1
         */

        private String businessLicense;
        private String income;
        private String updateDate;
        private String city;
        private String latitude;
        private String dist;
        private String orderStatus;
        private String delFlag;
        private String contactTel;
        private String content;
        private String qualifications;
        private String score;
        private String province;
        private String contact;
        private String id;
        private String createDate;
        private String longitude;
        private String address;
        private String servicesIds;
        private String stores;
        private boolean isNewRecord;
        private String picture;
        private String technicalCertificate;
        private String name;
        private String shopType;
        private String openDate;
        private String region;
        private String remarks;
        private String status;

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getQualifications() {
            return qualifications;
        }

        public void setQualifications(String qualifications) {
            this.qualifications = qualifications;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getServicesIds() {
            return servicesIds;
        }

        public void setServicesIds(String servicesIds) {
            this.servicesIds = servicesIds;
        }

        public String getStores() {
            return stores;
        }

        public void setStores(String stores) {
            this.stores = stores;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getTechnicalCertificate() {
            return technicalCertificate;
        }

        public void setTechnicalCertificate(String technicalCertificate) {
            this.technicalCertificate = technicalCertificate;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getOpenDate() {
            return openDate;
        }

        public void setOpenDate(String openDate) {
            this.openDate = openDate;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
