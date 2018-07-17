package com.dream.moka.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class ListCouponBean implements Serializable{
    /**
     * amount : 0.00
     * updateDate : 2018-01-24 15:16:33
     * quantity : 1
     * effectFrom : 2018-01-24 15:16:33
     * sendUserType : 0
     * isNewRecord : false
     * delFlag : 0
     * sendTime : 2018-01-24 15:16:33
     * sendUserId : aa6bf081006a4fc7a1f1ea84c230bba1
     * beginMiles : 40000
     * effectTo :
     * endMiles : 42500
     * fromType : 1
     * couponType : 5
     * leastAmtUse :
     * name : 5.5公里保养券
     * id : d0777c42c6ed4d0a9d5b466d71fbfa7d
     * carConfigureId : 6d6bbd8883774c9cb1ae3c129a142e37
     * remarks :
     * createDate : 2018-01-24 15:16:33
     * status : 0
     */

    private String amount;
    private String updateDate;
    private String quantity;
    private String effectFrom;
    private String sendUserType;
    private boolean isNewRecord;
    private String delFlag;
    private String sendTime;
    private String sendUserId;
    private String beginMiles;
    private String effectTo;
    private String endMiles;
    private String fromType;
    private String couponType;
    private String leastAmtUse;
    private String name;
    private String id;
    private String carConfigureId;
    private String remarks;
    private String createDate;
    private String status;
    private String carName;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getEffectFrom() {
        return effectFrom;
    }

    public void setEffectFrom(String effectFrom) {
        this.effectFrom = effectFrom;
    }

    public String getSendUserType() {
        return sendUserType;
    }

    public void setSendUserType(String sendUserType) {
        this.sendUserType = sendUserType;
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

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getBeginMiles() {
        return beginMiles;
    }

    public void setBeginMiles(String beginMiles) {
        this.beginMiles = beginMiles;
    }

    public String getEffectTo() {
        return effectTo;
    }

    public void setEffectTo(String effectTo) {
        this.effectTo = effectTo;
    }

    public String getEndMiles() {
        return endMiles;
    }

    public void setEndMiles(String endMiles) {
        this.endMiles = endMiles;
    }

    public String getFromType() {
        return fromType;
    }

    public void setFromType(String fromType) {
        this.fromType = fromType;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getLeastAmtUse() {
        return leastAmtUse;
    }

    public void setLeastAmtUse(String leastAmtUse) {
        this.leastAmtUse = leastAmtUse;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
