package com.dream.moka.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/18 0018.
 */

public class AddressListResultBean implements Serializable{
    /**
     * area : 南山区
     * updateDate : 2017-12-18 10:15:49
     * city : 深圳市
     * postcode :
     * telephone : 131454647899
     * isNewRecord : false
     * delFlag : 0
     * detailAddr : 绿创云谷大厦
     * province : 广东省
     * id : 979d11be1baf41699d38703d03709dc9
     * recipientsName : 老王
     * recipientsEmail :
     * remarks :
     * createDate : 2017-12-18 10:15:49
     * status : 0
     */

    private String area;
    private String updateDate;
    private String city;
    private String postcode;
    private String telephone;
    private boolean isNewRecord;
    private String delFlag;
    private String detailAddr;
    private String province;
    private String id;
    private String recipientsName;
    private String recipientsEmail;
    private String remarks;
    private String createDate;
    private String latitude;
    private String longitude;
    private int status;

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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName;
    }

    public String getRecipientsEmail() {
        return recipientsEmail;
    }

    public void setRecipientsEmail(String recipientsEmail) {
        this.recipientsEmail = recipientsEmail;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
