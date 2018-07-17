package com.dream.moka.Bean.Online;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/8 0008.
 */

public class OnlinerBean implements Serializable{

    /**
     * income : 0.00
     * serviceType :
     * updateDate : 2018-03-02 13:57:24
     * code : 技师110编号
     * sign :
     * photo : /MoCar/userfiles/1/images/shop/repairShop/2018/02/3b4b23c7-1419-4a71-992f-7b4c8c77bf0c.jpg
     * isNewRecord : false
     * delFlag : 0
     * serviceCount : 0
     * money : 0.00
     * id : 4b205224bd2641cbaecd51e90941c2d5
     * starLevel : 3.00
     * remarks :
     * createDate : 2018-03-02 13:57:24
     * status : 1
     */

    private String income;
    private String serviceType;
    private String updateDate;
    private String code;
    private String sign;
    private String photo;
    private boolean isNewRecord;
    private String delFlag;
    private String serviceCount;
    private String money;
    private String id;
    private String starLevel;
    private String remarks;
    private String createDate;
    private String status;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIncome() {
        return income;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(String serviceCount) {
        this.serviceCount = serviceCount;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
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
