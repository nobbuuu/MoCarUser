package com.dream.moka.Bean;

/**
 * Created by Administrator on 2017/12/20 0020.
 * 车型
 */

public class CarCategoryResultBean {

    /**
     * id : f0a0effb6341477f91f083a5430811ad
     * status : 1
     * name : 宝马X1
     * isNewRecord : false
     * remarks :
     * createDate : 2017-11-03 15:50:37
     * updateDate : 2017-11-03 15:50:37
     * delFlag : 0
     * carBrandId : 0011aa49b4744fbc9dd03af342e3e56b
     */

    private String id;
    private int status;
    private String name;
    private boolean isNewRecord;
    private String remarks;
    private String createDate;
    private String updateDate;
    private String delFlag;
    private String carBrandId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCarBrandId() {
        return carBrandId;
    }

    public void setCarBrandId(String carBrandId) {
        this.carBrandId = carBrandId;
    }
}
