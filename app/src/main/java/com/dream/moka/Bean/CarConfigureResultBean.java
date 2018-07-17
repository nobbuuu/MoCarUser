package com.dream.moka.Bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/20 0020.
 * 车配置
 */

public class CarConfigureResultBean implements Serializable{

    /**
     * id : 2a9aa0f7fb1a4dbf92500ef1e4688b9b
     * carType : 2
     * carConfigure : 嗷嗷嗷
     * status : 1
     * carCategoryId : 46610cfc7c9a46be9e80f80f7818809c
     * name : 奔驰 大奔
     * isNewRecord : false
     * remarks :
     * createDate : 2017-11-03 15:53:36
     * carPrice : 350000.00
     * updateDate : 2017-11-14 16:13:43
     * delFlag : 0
     * carBrandId : c07af55d880347de8f9f90505028824c
     */

    private String id;
    private String carType;
    private String carConfigure;
    private String status;
    private String carCategoryId;
    private String name;
    private boolean isNewRecord;
    private String remarks;
    private String createDate;
    private String carPrice;
    private String updateDate;
    private String delFlag;
    private String carBrandId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCarConfigure() {
        return carConfigure;
    }

    public void setCarConfigure(String carConfigure) {
        this.carConfigure = carConfigure;
    }


    public String getCarCategoryId() {
        return carCategoryId;
    }

    public void setCarCategoryId(String carCategoryId) {
        this.carCategoryId = carCategoryId;
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

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
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
