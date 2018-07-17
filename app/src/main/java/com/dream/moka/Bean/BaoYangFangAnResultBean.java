package com.dream.moka.Bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/13 0013.
 */

public class BaoYangFangAnResultBean implements Serializable{

    /**
     * payMoney : 3998300
     * listBase : [{"listPart":[{"count":0,"status":1,"remarks":"","code":"C123","salesVolume":0,"partShopName":"","updateDate":"2018-01-03 16:30:31","content":"<p> \tdasdasdasdsadas<\/p>","id":"c35eb1eac5eb456596d69d19023674d1","picture":"","settlementPercent":12,"brands":"试试","price":"200.00","shopId":"","name":"120","isNewRecord":false,"createDate":"2018-01-03 16:30:31","typeId":"aa3219974a694f24a3b9fba69e45e442","isPlatform":1,"delFlag":"0"},{"count":0,"status":1,"remarks":"","code":"11","salesVolume":0,"partShopName":"","updateDate":"2018-01-05 17:40:31","content":"","id":"f3275f42cd044ceeb6b49b88368a0870","picture":"","settlementPercent":11,"brands":"11","price":"11.00","shopId":"","name":"10","isNewRecord":false,"createDate":"2018-01-05 17:40:31","typeId":"a99ba4a73b3e4eadb602403fc3cd6bf1","isPlatform":1,"delFlag":"0"}],"serviceItem":{"status":1,"remarks":"","partTypeId":"aa3219974a694f24a3b9fba69e45e442,a99ba4a73b3e4eadb602403fc3cd6bf1","type":1,"updateDate":"2018-01-03 16:27:58","id":"082df494f565466b8abd460ff356aa2d","selected":"","price":"100.00","name":"机油滤清器","isNewRecord":false,"createDate":"2017-12-19 14:04:39","serviceHour":120,"delFlag":"0"}}]
     * planTarget : {"status":1,"beginMiles":27600,"endMiles":32500,"remarks":"","type":1,"updateDate":"2017-12-30 11:41:52","endHours":6,"id":"0daea42ed10e4252995f9f2e5312d6cb","beginHour":5,"name":"30000KM","isNewRecord":false,"createDate":"","delFlag":"0"}
     * planTargetPackage: {"status":1,"beginMiles":27600,"endMiles":32500,"remarks":"","type":1,"updateDate":"2017-12-30 11:41:52","endHours":6,"id":"0daea42ed10e4252995f9f2e5312d6cb","beginHour":5,"name":"30000KM","isNewRecord":false,"createDate":"","delFlag":"0"}
     * listPackage : [{"listPart":[{"count":0,"status":1,"remarks":"","code":"C123","salesVolume":0,"partShopName":"","updateDate":"2018-01-03 16:30:31","content":"<p> \tdasdasdasdsadas<\/p>","id":"c35eb1eac5eb456596d69d19023674d1","picture":"","settlementPercent":12,"brands":"试试","price":"200.00","shopId":"","name":"120","isNewRecord":false,"createDate":"2018-01-03 16:30:31","typeId":"aa3219974a694f24a3b9fba69e45e442","isPlatform":1,"delFlag":"0"},{"count":0,"status":1,"remarks":"","code":"11","salesVolume":0,"partShopName":"","updateDate":"2018-01-05 17:40:31","content":"","id":"f3275f42cd044ceeb6b49b88368a0870","picture":"","settlementPercent":11,"brands":"11","price":"11.00","shopId":"","name":"10","isNewRecord":false,"createDate":"2018-01-05 17:40:31","typeId":"a99ba4a73b3e4eadb602403fc3cd6bf1","isPlatform":1,"delFlag":"0"}],"serviceItem":{"status":1,"remarks":"","partTypeId":"aa3219974a694f24a3b9fba69e45e442,a99ba4a73b3e4eadb602403fc3cd6bf1","type":1,"updateDate":"2018-01-03 16:27:58","id":"082df494f565466b8abd460ff356aa2d","selected":"","price":"100.00","name":"机油滤清器","isNewRecord":false,"createDate":"2017-12-19 14:04:39","serviceHour":120,"delFlag":"0"}}]
     */

    private String payMoney;
    private PlanTargetBean planTarget;
    private PlanTargetBean planTargetPackage;
    private List<ListBaseBean> listBase;
    private List<ListBaseBean> listPackage;

    public PlanTargetBean getPlanTargetPackage() {
        return planTargetPackage;
    }

    public void setPlanTargetPackage(PlanTargetBean planTargetPackage) {
        this.planTargetPackage = planTargetPackage;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public PlanTargetBean getPlanTarget() {
        return planTarget;
    }

    public void setPlanTarget(PlanTargetBean planTarget) {
        this.planTarget = planTarget;
    }

    public List<ListBaseBean> getListBase() {
        return listBase;
    }

    public void setListBase(List<ListBaseBean> listBase) {
        this.listBase = listBase;
    }

    public List<ListBaseBean> getListPackage() {
        return listPackage;
    }

    public void setListPackage(List<ListBaseBean> listPackage) {
        this.listPackage = listPackage;
    }

    public static class PlanTargetBean implements Serializable{
        /**
         * status : 1
         * beginMiles : 27600
         * endMiles : 32500
         * remarks :
         * type : 1
         * updateDate : 2017-12-30 11:41:52
         * endHours : 6
         * id : 0daea42ed10e4252995f9f2e5312d6cb
         * beginHour : 5
         * name : 30000KM
         * isNewRecord : false
         * createDate :
         * delFlag : 0
         */

        private String status;
        private String beginMiles;
        private String endMiles;
        private String remarks;
        private String type;
        private String updateDate;
        private String endHours;
        private String id;
        private String beginHour;
        private String name;
        private boolean isNewRecord;
        private String createDate;
        private String delFlag;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBeginMiles() {
            return beginMiles;
        }

        public void setBeginMiles(String beginMiles) {
            this.beginMiles = beginMiles;
        }

        public String getEndMiles() {
            return endMiles;
        }

        public void setEndMiles(String endMiles) {
            this.endMiles = endMiles;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getEndHours() {
            return endHours;
        }

        public void setEndHours(String endHours) {
            this.endHours = endHours;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBeginHour() {
            return beginHour;
        }

        public void setBeginHour(String beginHour) {
            this.beginHour = beginHour;
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

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }

    public static class ListBaseBean implements Serializable{
        /**
         * listPart : [{"count":0,"status":1,"remarks":"","code":"C123","salesVolume":0,"partShopName":"","updateDate":"2018-01-03 16:30:31","content":"<p> \tdasdasdasdsadas<\/p>","id":"c35eb1eac5eb456596d69d19023674d1","picture":"","settlementPercent":12,"brands":"试试","price":"200.00","shopId":"","name":"120","isNewRecord":false,"createDate":"2018-01-03 16:30:31","typeId":"aa3219974a694f24a3b9fba69e45e442","isPlatform":1,"delFlag":"0"},{"count":0,"status":1,"remarks":"","code":"11","salesVolume":0,"partShopName":"","updateDate":"2018-01-05 17:40:31","content":"","id":"f3275f42cd044ceeb6b49b88368a0870","picture":"","settlementPercent":11,"brands":"11","price":"11.00","shopId":"","name":"10","isNewRecord":false,"createDate":"2018-01-05 17:40:31","typeId":"a99ba4a73b3e4eadb602403fc3cd6bf1","isPlatform":1,"delFlag":"0"}]
         * serviceItem : {"status":1,"remarks":"","partTypeId":"aa3219974a694f24a3b9fba69e45e442,a99ba4a73b3e4eadb602403fc3cd6bf1","type":1,"updateDate":"2018-01-03 16:27:58","id":"082df494f565466b8abd460ff356aa2d","selected":"","price":"100.00","name":"机油滤清器","isNewRecord":false,"createDate":"2017-12-19 14:04:39","serviceHour":120,"delFlag":"0"}
         */

        private ServiceItemBean serviceItem;
        private List<ListPartBean> listPart;

        public ServiceItemBean getServiceItem() {
            return serviceItem;
        }

        public void setServiceItem(ServiceItemBean serviceItem) {
            this.serviceItem = serviceItem;
        }

        public List<ListPartBean> getListPart() {
            return listPart;
        }

        public void setListPart(List<ListPartBean> listPart) {
            this.listPart = listPart;
        }

        public static class ServiceItemBean implements Serializable{
            /**
             * status : 1
             * remarks :
             * partTypeId : aa3219974a694f24a3b9fba69e45e442,a99ba4a73b3e4eadb602403fc3cd6bf1
             * type : 1
             * updateDate : 2018-01-03 16:27:58
             * id : 082df494f565466b8abd460ff356aa2d
             * selected :
             * price : 100.00
             * name : 机油滤清器
             * isNewRecord : false
             * createDate : 2017-12-19 14:04:39
             * serviceHour : 120
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

            public String getPartTypeId() {
                return partTypeId;
            }

            public void setPartTypeId(String partTypeId) {
                this.partTypeId = partTypeId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

        public static class ListPartBean implements Serializable{
            /**
             * count : 0
             * status : 1
             * remarks :
             * code : C123
             * salesVolume : 0
             * partShopName :
             * updateDate : 2018-01-03 16:30:31
             * content : <p> 	dasdasdasdsadas</p>
             * id : c35eb1eac5eb456596d69d19023674d1
             * picture :
             * settlementPercent : 12
             * brands : 试试
             * price : 200.00
             * shopId :
             * name : 120
             * isNewRecord : false
             * createDate : 2018-01-03 16:30:31
             * typeId : aa3219974a694f24a3b9fba69e45e442
             * isPlatform : 1
             * delFlag : 0
             */

            private String count;
            private String status;
            private String remarks;
            private String code;
            private String salesVolume;
            private String partShopName;
            private String updateDate;
            private String content;
            private String id;
            private String picture;
            private String settlementPercent;
            private String brands;
            private String price;
            private String shopId;
            private String name;
            private boolean isNewRecord;
            private String createDate;
            private String typeId;
            private String isPlatform;
            private String delFlag;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
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

            public String getPartShopName() {
                return partShopName;
            }

            public void setPartShopName(String partShopName) {
                this.partShopName = partShopName;
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getBrands() {
                return brands;
            }

            public void setBrands(String brands) {
                this.brands = brands;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
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

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getIsPlatform() {
                return isPlatform;
            }

            public void setIsPlatform(String isPlatform) {
                this.isPlatform = isPlatform;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }
        }
    }

    public static class ListPackageBean implements Serializable{
        /**
         * listPart : [{"count":0,"status":1,"remarks":"","code":"C123","salesVolume":0,"partShopName":"","updateDate":"2018-01-03 16:30:31","content":"<p> \tdasdasdasdsadas<\/p>","id":"c35eb1eac5eb456596d69d19023674d1","picture":"","settlementPercent":12,"brands":"试试","price":"200.00","shopId":"","name":"120","isNewRecord":false,"createDate":"2018-01-03 16:30:31","typeId":"aa3219974a694f24a3b9fba69e45e442","isPlatform":1,"delFlag":"0"},{"count":0,"status":1,"remarks":"","code":"11","salesVolume":0,"partShopName":"","updateDate":"2018-01-05 17:40:31","content":"","id":"f3275f42cd044ceeb6b49b88368a0870","picture":"","settlementPercent":11,"brands":"11","price":"11.00","shopId":"","name":"10","isNewRecord":false,"createDate":"2018-01-05 17:40:31","typeId":"a99ba4a73b3e4eadb602403fc3cd6bf1","isPlatform":1,"delFlag":"0"}]
         * serviceItem : {"status":1,"remarks":"","partTypeId":"aa3219974a694f24a3b9fba69e45e442,a99ba4a73b3e4eadb602403fc3cd6bf1","type":1,"updateDate":"2018-01-03 16:27:58","id":"082df494f565466b8abd460ff356aa2d","selected":"","price":"100.00","name":"机油滤清器","isNewRecord":false,"createDate":"2017-12-19 14:04:39","serviceHour":120,"delFlag":"0"}
         */

        private ServiceItemBeanX serviceItem;
        private List<ListPartBeanX> listPart;

        public ServiceItemBeanX getServiceItem() {
            return serviceItem;
        }

        public void setServiceItem(ServiceItemBeanX serviceItem) {
            this.serviceItem = serviceItem;
        }

        public List<ListPartBeanX> getListPart() {
            return listPart;
        }

        public void setListPart(List<ListPartBeanX> listPart) {
            this.listPart = listPart;
        }

        public static class ServiceItemBeanX implements Serializable{
            /**
             * status : 1
             * remarks :
             * partTypeId : aa3219974a694f24a3b9fba69e45e442,a99ba4a73b3e4eadb602403fc3cd6bf1
             * type : 1
             * updateDate : 2018-01-03 16:27:58
             * id : 082df494f565466b8abd460ff356aa2d
             * selected :
             * price : 100.00
             * name : 机油滤清器
             * isNewRecord : false
             * createDate : 2017-12-19 14:04:39
             * serviceHour : 120
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

            public String getPartTypeId() {
                return partTypeId;
            }

            public void setPartTypeId(String partTypeId) {
                this.partTypeId = partTypeId;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
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

        public static class ListPartBeanX implements Serializable{
            /**
             * count : 0
             * status : 1
             * remarks :
             * code : C123
             * salesVolume : 0
             * partShopName :
             * updateDate : 2018-01-03 16:30:31
             * content : <p> 	dasdasdasdsadas</p>
             * id : c35eb1eac5eb456596d69d19023674d1
             * picture :
             * settlementPercent : 12
             * brands : 试试
             * price : 200.00
             * shopId :
             * name : 120
             * isNewRecord : false
             * createDate : 2018-01-03 16:30:31
             * typeId : aa3219974a694f24a3b9fba69e45e442
             * isPlatform : 1
             * delFlag : 0
             */

            private String count;
            private String status;
            private String remarks;
            private String code;
            private String salesVolume;
            private String partShopName;
            private String updateDate;
            private String content;
            private String id;
            private String picture;
            private String settlementPercent;
            private String brands;
            private String price;
            private String shopId;
            private String name;
            private boolean isNewRecord;
            private String createDate;
            private String typeId;
            private String isPlatform;
            private String delFlag;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
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

            public String getPartShopName() {
                return partShopName;
            }

            public void setPartShopName(String partShopName) {
                this.partShopName = partShopName;
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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

            public String getBrands() {
                return brands;
            }

            public void setBrands(String brands) {
                this.brands = brands;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
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

            public String getTypeId() {
                return typeId;
            }

            public void setTypeId(String typeId) {
                this.typeId = typeId;
            }

            public String getIsPlatform() {
                return isPlatform;
            }

            public void setIsPlatform(String isPlatform) {
                this.isPlatform = isPlatform;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }
        }
    }
}
