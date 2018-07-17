package com.dream.moka.Bean.Maintain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class FreeMaintainAllBean implements Serializable{


    private List<ServiceResultBean> serviceResult;

    public List<ServiceResultBean> getServiceResult() {
        return serviceResult;
    }

    public void setServiceResult(List<ServiceResultBean> serviceResult) {
        this.serviceResult = serviceResult;
    }

    public static class ServiceResultBean implements Serializable{
        /**
         * serviceItem : {"id":"18470ae058a1465cac985efb4a7b3ff6","instr":"","isNewRecord":false,"remarks":"40","createDate":"2018-03-29 13:45:23","updateDate":"2018-04-03 11:17:01","name":"更换电池","type":0,"partTypeId":"e6d28e8ad7c94cd69463458e90f0825d","price":30,"serviceHour":"","status":1,"selected":""}
         * listService : [{"listPart":[{"updateDate":"2018-04-04 19:32:40","brands":"瓦尔塔","code":"12345","salesVolume":0,"count":0,"isPlatform":1,"isNewRecord":false,"delFlag":"0","partShopName":"","content":"","picture":"/MoCar/userfiles/part/201804041932400db303ca-f789-4d40-a5a6-7fae64ee3498.jpg","settlementPercent":90,"price":"460.00","name":"电池","typeId":"e6d28e8ad7c94cd69463458e90f0825d","id":"55b8a5b8fd404a5e977e55e7a315c94d","shopId":"","remarks":"","createDate":"2018-04-04 19:32:40","status":1},{"updateDate":"2018-04-04 19:32:42","brands":"瓦尔塔","code":"12345","salesVolume":0,"count":0,"isPlatform":1,"isNewRecord":false,"delFlag":"0","partShopName":"","content":"","picture":"/MoCar/userfiles/part/2018040419324113f8ae99-c7da-4299-9f70-b7bc16d64cd9.jpg","settlementPercent":90,"price":"460.00","name":"电池","typeId":"e6d28e8ad7c94cd69463458e90f0825d","id":"9532fb2c312e42db957e8a0d5099010f","shopId":"","remarks":"","createDate":"2018-04-04 19:32:42","status":1},{"updateDate":"2018-04-04 19:32:43","brands":"瓦尔塔","code":"12345","salesVolume":0,"count":0,"isPlatform":1,"isNewRecord":false,"delFlag":"0","partShopName":"","content":"","picture":"/MoCar/userfiles/part/20180404193243578dbdf0-8a2f-4f2d-9fc3-ef79d8cd19d4.jpg","settlementPercent":90,"price":"460.00","name":"电池","typeId":"e6d28e8ad7c94cd69463458e90f0825d","id":"b4f6af49700d414c98abc1943d32a4b0","shopId":"","remarks":"","createDate":"2018-04-04 19:32:43","status":1}],"partType":{"updateDate":"2018-03-29 21:48:38","name":"电池","id":"e6d28e8ad7c94cd69463458e90f0825d","isNewRecord":false,"delFlag":"0","remarks":"","createDate":"2018-03-29 21:48:38","status":1}}]
         */

        private ServiceItemBean serviceItem;
        private List<ListServiceBean> listService;

        public ServiceItemBean getServiceItem() {
            return serviceItem;
        }

        public void setServiceItem(ServiceItemBean serviceItem) {
            this.serviceItem = serviceItem;
        }

        public List<ListServiceBean> getListService() {
            return listService;
        }

        public void setListService(List<ListServiceBean> listService) {
            this.listService = listService;
        }

        public static class ServiceItemBean implements Serializable{
            /**
             * id : 18470ae058a1465cac985efb4a7b3ff6
             * instr :
             * isNewRecord : false
             * remarks : 40
             * createDate : 2018-03-29 13:45:23
             * updateDate : 2018-04-03 11:17:01
             * name : 更换电池
             * type : 0
             * partTypeId : e6d28e8ad7c94cd69463458e90f0825d
             * price : 30
             * serviceHour :
             * status : 1
             * selected :
             */

            private String id;
            private String instr;
            private boolean isNewRecord;
            private boolean isExpand = true;
            private String remarks;
            private String createDate;
            private String updateDate;
            private String name;
            private String type;
            private String partTypeId;
            private String price;
            private String serviceHour;
            private String status;
            private String selected;

            public boolean isExpand() {
                return isExpand;
            }

            public void setExpand(boolean expand) {
                isExpand = expand;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getInstr() {
                return instr;
            }

            public void setInstr(String instr) {
                this.instr = instr;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPartTypeId() {
                return partTypeId;
            }

            public void setPartTypeId(String partTypeId) {
                this.partTypeId = partTypeId;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getServiceHour() {
                return serviceHour;
            }

            public void setServiceHour(String serviceHour) {
                this.serviceHour = serviceHour;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
            }
        }

        public static class ListServiceBean implements Serializable{
            /**
             * listPart : [{"updateDate":"2018-04-04 19:32:40","brands":"瓦尔塔","code":"12345","salesVolume":0,"count":0,"isPlatform":1,"isNewRecord":false,"delFlag":"0","partShopName":"","content":"","picture":"/MoCar/userfiles/part/201804041932400db303ca-f789-4d40-a5a6-7fae64ee3498.jpg","settlementPercent":90,"price":"460.00","name":"电池","typeId":"e6d28e8ad7c94cd69463458e90f0825d","id":"55b8a5b8fd404a5e977e55e7a315c94d","shopId":"","remarks":"","createDate":"2018-04-04 19:32:40","status":1},{"updateDate":"2018-04-04 19:32:42","brands":"瓦尔塔","code":"12345","salesVolume":0,"count":0,"isPlatform":1,"isNewRecord":false,"delFlag":"0","partShopName":"","content":"","picture":"/MoCar/userfiles/part/2018040419324113f8ae99-c7da-4299-9f70-b7bc16d64cd9.jpg","settlementPercent":90,"price":"460.00","name":"电池","typeId":"e6d28e8ad7c94cd69463458e90f0825d","id":"9532fb2c312e42db957e8a0d5099010f","shopId":"","remarks":"","createDate":"2018-04-04 19:32:42","status":1},{"updateDate":"2018-04-04 19:32:43","brands":"瓦尔塔","code":"12345","salesVolume":0,"count":0,"isPlatform":1,"isNewRecord":false,"delFlag":"0","partShopName":"","content":"","picture":"/MoCar/userfiles/part/20180404193243578dbdf0-8a2f-4f2d-9fc3-ef79d8cd19d4.jpg","settlementPercent":90,"price":"460.00","name":"电池","typeId":"e6d28e8ad7c94cd69463458e90f0825d","id":"b4f6af49700d414c98abc1943d32a4b0","shopId":"","remarks":"","createDate":"2018-04-04 19:32:43","status":1}]
             * partType : {"updateDate":"2018-03-29 21:48:38","name":"电池","id":"e6d28e8ad7c94cd69463458e90f0825d","isNewRecord":false,"delFlag":"0","remarks":"","createDate":"2018-03-29 21:48:38","status":1}
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
                 * updateDate : 2018-03-29 21:48:38
                 * name : 电池
                 * id : e6d28e8ad7c94cd69463458e90f0825d
                 * isNewRecord : false
                 * delFlag : 0
                 * remarks :
                 * createDate : 2018-03-29 21:48:38
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
                 * updateDate : 2018-04-04 19:32:40
                 * brands : 瓦尔塔
                 * code : 12345
                 * salesVolume : 0
                 * count : 0
                 * isPlatform : 1
                 * isNewRecord : false
                 * delFlag : 0
                 * partShopName :
                 * content :
                 * picture : /MoCar/userfiles/part/201804041932400db303ca-f789-4d40-a5a6-7fae64ee3498.jpg
                 * settlementPercent : 90
                 * price : 460.00
                 * name : 电池
                 * typeId : e6d28e8ad7c94cd69463458e90f0825d
                 * id : 55b8a5b8fd404a5e977e55e7a315c94d
                 * shopId :
                 * remarks :
                 * createDate : 2018-04-04 19:32:40
                 * status : 1
                 */

                private String updateDate;
                private String brands;
                private String code;
                private String salesVolume;
                private String count;
                private String isPlatform;
                private boolean isNewRecord;
                private boolean isSelect;
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

                public boolean isSelect() {
                    return isSelect;
                }

                public void setSelect(boolean select) {
                    isSelect = select;
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
    }
}
