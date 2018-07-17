package com.dream.moka.Bean.Maintain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class ProductSelectBean implements Serializable{

    /**
     * listPart : [{"updateDate":"2018-01-23 17:24:25","brands":"通用件","code":"1010","salesVolume":0,"count":0,"isPlatform":1,"isNewRecord":false,"delFlag":"0","partShopName":"","content":"","picture":"","settlementPercent":95,"price":"380.00","name":"刹车系统养护包","typeId":"655dd1e31719416893ec6f7c07eedeae","id":"fe57acf5ea2a4221a552a524f142f9aa","shopId":"","remarks":"","createDate":"2018-01-22 22:13:29","status":1}]
     * partType : {"updateDate":"2018-01-22 22:05:46","name":"刹车养护B","id":"655dd1e31719416893ec6f7c07eedeae","isNewRecord":false,"delFlag":"0","remarks":"","createDate":"2018-01-22 22:05:46","status":1}
     */

    private PartTypeBean partType;
    private List<ListPartBean> listPart;

    public PartTypeBean getPartType() {
        return partType;
    }

    public void setPartType(PartTypeBean partType) {
        this.partType = partType;
    }

    public List<ListPartBean> getListPart() {
        return listPart;
    }

    public void setListPart(List<ListPartBean> listPart) {
        this.listPart = listPart;
    }

    public static class PartTypeBean implements Serializable{
        /**
         * updateDate : 2018-01-22 22:05:46
         * name : 刹车养护B
         * id : 655dd1e31719416893ec6f7c07eedeae
         * isNewRecord : false
         * delFlag : 0
         * remarks :
         * createDate : 2018-01-22 22:05:46
         * status : 1
         */

        private String updateDate;
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

    public static class ListPartBean implements Serializable{
        /**
         * updateDate : 2018-01-23 17:24:25
         * brands : 通用件
         * code : 1010
         * salesVolume : 0
         * count : 0
         * isPlatform : 1
         * isNewRecord : false
         * delFlag : 0
         * partShopName :
         * content :
         * picture :
         * settlementPercent : 95
         * price : 380.00
         * name : 刹车系统养护包
         * typeId : 655dd1e31719416893ec6f7c07eedeae
         * id : fe57acf5ea2a4221a552a524f142f9aa
         * shopId :
         * remarks :
         * createDate : 2018-01-22 22:13:29
         * status : 1
         */

        private String updateDate;
        private String brands;
        private String code;
        private String salesVolume;
        private String count = "1";
        private String isPlatform;
        private boolean isNewRecord;
        private boolean select;
        private String delFlag;
        private String partShopName;
        private String content;
        private String picture;
        private String settlementPercent;
        private String price;
        private String name;
        private String typeId;
        private String id;
        private String shopId;
        private String remarks;
        private String createDate;
        private String status;

        public boolean isNewRecord() {
            return isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getBrands() {
            return brands;
        }

        public void setBrands(String brands) {
            this.brands = brands;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(String salesVolume) {
            this.salesVolume = salesVolume;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getIsPlatform() {
            return isPlatform;
        }

        public void setIsPlatform(String isPlatform) {
            this.isPlatform = isPlatform;
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

        public String getPartShopName() {
            return partShopName;
        }

        public void setPartShopName(String partShopName) {
            this.partShopName = partShopName;
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

        public String getSettlementPercent() {
            return settlementPercent;
        }

        public void setSettlementPercent(String settlementPercent) {
            this.settlementPercent = settlementPercent;
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

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
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
