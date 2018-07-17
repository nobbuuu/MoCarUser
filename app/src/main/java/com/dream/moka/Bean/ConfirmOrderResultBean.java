package com.dream.moka.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/15 0015.
 */

public class ConfirmOrderResultBean implements Serializable {
    /**
     * userAddress : {"recipientsName":"马云他爸爸","status":1,"remarks":"","postcode":"","updateDate":"2018-01-13 13:04:59","recipientsEmail":"","city":"深圳市","id":"81af7b6de85748ee8776291376805454","area":"南山区","detailAddr":"南山区高新北六道36号","isNewRecord":false,"province":"广东省","longitude":"113.94055411219597","latitude":"22.55459161129674","createDate":"2018-01-10 11:57:40","telephone":"13128861359","delFlag":"0"}
     * listCoupon : []
     * listShop : [{"region":"","qualifications":"/MoCar/userfiles/driver/201801121743472ee0b0e6-7943-48ac-b25c-2bbbb5967dc4.jpg","score":"5.00","remarks":"","contactTel":"18277750576","contact":"条子","city":"","id":"fdbe53f00f29466c83d73fcb49a88032","businessLicense":"/MoCar/userfiles/driver/201801121743423efd31f3-aeec-4adf-b67e-32eb7f35e5a5.jpg","income":"","name":"条子维修商","isNewRecord":false,"province":"","longitude":"114.030334","createDate":"2018-01-12 17:44:16","openDate":"","status":1,"orderStatus":1,"updateDate":"2018-01-15 13:58:05","content":"","picture":"/MoCar/userfiles/driver/20180112174414c148dfd5-5562-4ca1-9a86-ab7b699e9663.jpg","shopType":1,"technicalCertificate":"/MoCar/userfiles/driver/201801121744013029789a-6be7-44d5-8bb1-807a7e8f5cd2.jpg","address":"","latitude":"22.610507","stores":"/MoCar/userfiles/repair/201801151201515d2eb756-507b-41aa-93c8-5946eb430e8c.jpg,","dist":11131,"delFlag":"0"}]
     * userCar : {"lastMainDate":"2018-01-13 00:00:00","idNo":"","registerCity":"深圳","mileage":30000,"status":1,"registerDate":"","vinNo":"","carname":"奥迪A3 插电混动2015款 Sportback e-tron 舒适型","remarks":"","buyDate":"","insuranceName":"","updateDate":"2018-01-15 14:11:25","cardNo":"粤B555666","id":"7ed674319db14cf18a03c1af4b37e815","recomMaintMile":"","insuranceDate":"","owner":"奔驰司机","isNewRecord":false,"insuranceId":"","carConfigureId":"ff48a1c0391d485baaf20c13cf5a1dc9","createDate":"","delFlag":"0","drivingLicense":""}
     */

    private UserAddressBean userAddress;
    private UserCarBean userCar;
    private List<ListCouponBean> listCoupon;
    private List<ListShopBean> listShop;

    private String djf;
    private String score;

    public String getDjf() {
        return djf;
    }

    public void setDjf(String djf) {
        this.djf = djf;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public UserAddressBean getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(UserAddressBean userAddress) {
        this.userAddress = userAddress;
    }

    public UserCarBean getUserCar() {
        return userCar;
    }

    public void setUserCar(UserCarBean userCar) {
        this.userCar = userCar;
    }

    public List<ListCouponBean> getListCoupon() {
        return listCoupon;
    }

    public void setListCoupon(List<ListCouponBean> listCoupon) {
        this.listCoupon = listCoupon;
    }

    public List<ListShopBean> getListShop() {
        return listShop;
    }

    public void setListShop(List<ListShopBean> listShop) {
        this.listShop = listShop;
    }

    public static class UserAddressBean implements Serializable {
        /**
         * recipientsName : 马云他爸爸
         * status : 1
         * remarks :
         * postcode :
         * updateDate : 2018-01-13 13:04:59
         * recipientsEmail :
         * city : 深圳市
         * id : 81af7b6de85748ee8776291376805454
         * area : 南山区
         * detailAddr : 南山区高新北六道36号
         * isNewRecord : false
         * province : 广东省
         * longitude : 113.94055411219597
         * latitude : 22.55459161129674
         * createDate : 2018-01-10 11:57:40
         * telephone : 13128861359
         * delFlag : 0
         */

        private String recipientsName;
        private String status;
        private String remarks;
        private String postcode;
        private String updateDate;
        private String recipientsEmail;
        private String city;
        private String id;
        private String area;
        private String detailAddr;
        private boolean isNewRecord;
        private String province;
        private String longitude;
        private String latitude;
        private String createDate;
        private String telephone;
        private String delFlag;

        public String getRecipientsName() {
            return recipientsName;
        }

        public void setRecipientsName(String recipientsName) {
            this.recipientsName = recipientsName;
        }

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

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getRecipientsEmail() {
            return recipientsEmail;
        }

        public void setRecipientsEmail(String recipientsEmail) {
            this.recipientsEmail = recipientsEmail;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDetailAddr() {
            return detailAddr;
        }

        public void setDetailAddr(String detailAddr) {
            this.detailAddr = detailAddr;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }

    public static class UserCarBean implements Serializable {
        /**
         * lastMainDate : 2018-01-13 00:00:00
         * idNo :
         * registerCity : 深圳
         * mileage : 30000
         * status : 1
         * registerDate :
         * vinNo :
         * carname : 奥迪A3 插电混动2015款 Sportback e-tron 舒适型
         * remarks :
         * buyDate :
         * insuranceName :
         * updateDate : 2018-01-15 14:11:25
         * cardNo : 粤B555666
         * id : 7ed674319db14cf18a03c1af4b37e815
         * recomMaintMile :
         * insuranceDate :
         * owner : 奔驰司机
         * isNewRecord : false
         * insuranceId :
         * carConfigureId : ff48a1c0391d485baaf20c13cf5a1dc9
         * createDate :
         * delFlag : 0
         * drivingLicense :
         */

        private String lastMainDate;
        private String idNo;
        private String registerCity;
        private String mileage;
        private String status;
        private String registerDate;
        private String vinNo;
        private String carname;
        private String remarks;
        private String buyDate;
        private String insuranceName;
        private String updateDate;
        private String cardNo;
        private String id;
        private String recomMaintMile;
        private String insuranceDate;
        private String owner;
        private boolean isNewRecord;
        private String insuranceId;
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

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(String registerDate) {
            this.registerDate = registerDate;
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

        public String getInsuranceName() {
            return insuranceName;
        }

        public void setInsuranceName(String insuranceName) {
            this.insuranceName = insuranceName;
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

        public String getRecomMaintMile() {
            return recomMaintMile;
        }

        public void setRecomMaintMile(String recomMaintMile) {
            this.recomMaintMile = recomMaintMile;
        }

        public String getInsuranceDate() {
            return insuranceDate;
        }

        public void setInsuranceDate(String insuranceDate) {
            this.insuranceDate = insuranceDate;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
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

    public static class ListShopBean implements Serializable {
        /**
         * region :
         * qualifications : /MoCar/userfiles/driver/201801121743472ee0b0e6-7943-48ac-b25c-2bbbb5967dc4.jpg
         * score : 5.00
         * remarks :
         * contactTel : 18277750576
         * contact : 条子
         * city :
         * id : fdbe53f00f29466c83d73fcb49a88032
         * businessLicense : /MoCar/userfiles/driver/201801121743423efd31f3-aeec-4adf-b67e-32eb7f35e5a5.jpg
         * income :
         * name : 条子维修商
         * isNewRecord : false
         * province :
         * longitude : 114.030334
         * createDate : 2018-01-12 17:44:16
         * openDate :
         * status : 1
         * orderStatus : 1
         * updateDate : 2018-01-15 13:58:05
         * content :
         * picture : /MoCar/userfiles/driver/20180112174414c148dfd5-5562-4ca1-9a86-ab7b699e9663.jpg
         * shopType : 1
         * technicalCertificate : /MoCar/userfiles/driver/201801121744013029789a-6be7-44d5-8bb1-807a7e8f5cd2.jpg
         * address :
         * latitude : 22.610507
         * stores : /MoCar/userfiles/repair/201801151201515d2eb756-507b-41aa-93c8-5946eb430e8c.jpg,
         * dist : 11131
         * delFlag : 0
         */

        private String region;
        private String qualifications;
        private String score;
        private String remarks;
        private String contactTel;
        private String contact;
        private String city;
        private String id;
        private String businessLicense;
        private String income;
        private String name;
        private boolean isNewRecord;
        private String province;
        private String longitude;
        private String createDate;
        private String openDate;
        private String status;
        private String orderStatus;
        private String updateDate;
        private String content;
        private String picture;
        private String shopType;
        private String technicalCertificate;
        private String address;
        private String latitude;
        private String stores;
        private String dist;
        private String delFlag;

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getContactTel() {
            return contactTel;
        }

        public void setContactTel(String contactTel) {
            this.contactTel = contactTel;
        }

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getOpenDate() {
            return openDate;
        }

        public void setOpenDate(String openDate) {
            this.openDate = openDate;
        }


        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getDist() {
            return dist;
        }

        public void setDist(String dist) {
            this.dist = dist;
        }

        public String getTechnicalCertificate() {
            return technicalCertificate;
        }

        public void setTechnicalCertificate(String technicalCertificate) {
            this.technicalCertificate = technicalCertificate;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getStores() {
            return stores;
        }

        public void setStores(String stores) {
            this.stores = stores;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
