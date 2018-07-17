package com.dream.moka.Bean.Maintain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/31 0031.
 */
public class FreeMainTainBean implements Serializable{

    /**
     * listService : [{"updateDate":"2018-01-23 00:12:53","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"655dd1e31719416893ec6f7c07eedeae,7df1f58bb6744203b2deed59d0bd3e7e","price":"180.00","name":"刹车深度养护-更换刹车油+","id":"78914fa4939042fd96f6d99f25767701","remarks":"","selected":"","createDate":"2018-01-20 12:46:59","status":1},{"updateDate":"2018-01-23 00:13:12","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"a98da2dc50c94fc9938c3b21c1f33d9b","price":"100.00","name":"更换风格+","id":"34641d2ee5b7427d9694e72508babd6e","remarks":"","selected":"","createDate":"2018-01-20 12:46:23","status":1},{"updateDate":"2018-01-23 00:13:02","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":3,"partTypeId":"668fda6f9f0e4c24ab51a4ba507ab1ec,77f46820483541f59e820dd4ddfb1c49,f776d5b87b0f4a6faa7adb399098aa3d","price":"300.00","name":"间隔10000公里保养-检查及紧固螺丝+","id":"2148f5d256174ffcbb0b04dcb3c49a1c","remarks":"","selected":"","createDate":"2018-01-20 12:18:27","status":1},{"updateDate":"2018-01-23 00:13:54","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"3762bb9b7bcf4d30b3791ed1de28432b","price":"100.00","name":"更换冷却液+","id":"01afe66ef72a4e07bdc2be1280299a04","remarks":"","selected":"","createDate":"2018-01-20 12:43:00","status":1},{"updateDate":"2018-01-23 21:53:13","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"40f32dc12b564799a5b1f62a20889963,491abde2e5934b06af428a44dfb38a31","price":"100.00","name":"空调系统护理及更换空调滤清器+","id":"1b60f0198c2446238437ffcd4a200a82","remarks":"","selected":"","createDate":"2018-01-20 12:45:41","status":1},{"updateDate":"2018-01-23 00:13:30","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"b08da95c5a894098bfabd82f070fd33d","price":"350.00","name":"更换火花塞+","id":"fd40f68c9bbe44be83bfabda28701be1","remarks":"","selected":"","createDate":"2018-01-20 12:44:50","status":1},{"updateDate":"2018-01-23 00:13:46","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"6f35cf66fefd44bd9de03117883ccbe0","price":"200.00","name":"更换燃油滤清器（外置）+","id":"126ef57ed58c47f08bac8b429ddd3ddd","remarks":"","selected":"","createDate":"2018-01-20 12:43:40","status":1},{"updateDate":"2018-01-23 00:14:03","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"a99ba4a73b3e4eadb602403fc3cd6bf1","price":"100.00","name":"四轮动平衡+","id":"0932439327a74050bb662c3eeabd2b1d","remarks":"","selected":"","createDate":"2018-01-20 12:26:37","status":1},{"updateDate":"2018-01-23 00:13:22","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"a49d667ada074d4089c9e0b77771039f","price":"250.00","name":"更换波箱油+","id":"29c669ab9ad044e48f6fe5985a8d82c1","remarks":"","selected":"","createDate":"2018-01-20 12:45:16","status":1},{"updateDate":"2018-01-23 00:13:38","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"9669ce67b0c14129afbaca4aaa376766","price":"150.00","name":"更换方向机油+","id":"a3f83078130e468284fff619658e23cd","remarks":"","selected":"","createDate":"2018-01-20 12:44:14","status":1},{"updateDate":"2018-01-23 00:14:44","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":0,"partTypeId":"a99ba4a73b3e4eadb602403fc3cd6bf1","price":"0.00","name":"底盘紧固+","id":"d19f8969cd3144da8624863f2215478f","remarks":"","selected":"","createDate":"2018-01-20 12:17:05","status":1},{"updateDate":"2018-01-23 00:15:48","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":0,"partTypeId":"a98da2dc50c94fc9938c3b21c1f33d9b","price":"50.00","name":"更换风格","id":"715849a4eb664508b677d95f648b9fe1","remarks":"","selected":"","createDate":"2018-01-17 15:43:45","status":1},{"updateDate":"2018-01-23 00:15:54","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"7df1f58bb6744203b2deed59d0bd3e7e","price":"80.00","name":"更换刹车油","id":"12d831a4908741e1812a68747d346c7e","remarks":"","selected":"","createDate":"2018-01-17 12:43:33","status":1},{"updateDate":"2018-01-23 00:15:13","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"9669ce67b0c14129afbaca4aaa376766","price":"100.00","name":"更换方向机油","id":"17c863152e9c4e56b0b982330f76d888","remarks":"","selected":"","createDate":"2018-01-17 15:44:55","status":1},{"updateDate":"2018-01-23 00:14:34","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":"","partTypeId":"a99ba4a73b3e4eadb602403fc3cd6bf1","price":"","name":"底盘紧固","id":"b33da70e6d72408d98109f3c3067ccf0","remarks":"","selected":"","createDate":"2018-01-19 15:51:38","status":1},{"updateDate":"2018-01-23 00:15:18","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"b08da95c5a894098bfabd82f070fd33d","price":"240.00","name":"更换火花塞","id":"e18054b2c62a44469dbc7b3d3b6594f0","remarks":"","selected":"","createDate":"2018-01-17 15:39:56","status":1},{"updateDate":"2018-01-23 00:14:52","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"a99ba4a73b3e4eadb602403fc3cd6bf1","price":"120.00","name":"四轮动平衡","id":"78f88dee08204ed381766eed13cc46a5","remarks":"","selected":"","createDate":"2018-01-17 17:22:30","status":1},{"updateDate":"2018-01-23 00:15:25","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"a49d667ada074d4089c9e0b77771039f","price":"150.00","name":"更换波箱油","id":"c87a406dcc6240da8500111f0906cb39","remarks":"","selected":"","createDate":"2018-01-17 17:25:46","status":1},{"updateDate":"2018-01-23 00:14:14","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":4,"partTypeId":"6f35cf66fefd44bd9de03117883ccbe0,f776d5b87b0f4a6faa7adb399098aa3d,491abde2e5934b06af428a44dfb38a31,a98da2dc50c94fc9938c3b21c1f33d9b,e75642cbb4b645cc8900741232435871","price":"350.00","name":"大保养，检查及紧固底盘螺丝","id":"37af263372dc4a3491a40d0251ad9011","remarks":"","selected":"","createDate":"2018-01-17 17:27:26","status":1},{"updateDate":"2018-01-23 00:14:58","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":2,"partTypeId":"3762bb9b7bcf4d30b3791ed1de28432b","price":"100.00","name":"更换冷却液","id":"9b8ef0484c3d4c94a8c5244221ed69f1","remarks":"","selected":"","createDate":"2018-01-17 12:41:56","status":1},{"updateDate":"2018-01-23 00:15:43","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"491abde2e5934b06af428a44dfb38a31","price":"80.00","name":"更换空调滤器","id":"6f434937ca024ae8a8d090453ae91e47","remarks":"","selected":"","createDate":"2018-01-17 12:42:51","status":1},{"updateDate":"2018-01-23 00:14:20","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":4,"partTypeId":"f776d5b87b0f4a6faa7adb399098aa3d,e75642cbb4b645cc8900741232435871","price":"150.00","name":"间隔5000KM保养-检查及紧固螺丝","id":"d60ae11a47b6427d9aed31bfe11011fb","remarks":"","selected":"","createDate":"2018-01-17 17:24:27","status":1},{"updateDate":"2018-01-23 00:15:05","isNewRecord":false,"delFlag":"0","type":0,"serviceHour":1,"partTypeId":"6f35cf66fefd44bd9de03117883ccbe0","price":"150.00","name":"更换燃油滤清器（外置）","id":"f3638c4046d8420b8b46a193a697bc80","remarks":"","selected":"","createDate":"2017-12-30 10:10:14","status":1}]
     * partShopId :
     */

    private String partShopId;
    private List<ListServiceBean> listService;

    public String getPartShopId() {
        return partShopId;
    }

    public void setPartShopId(String partShopId) {
        this.partShopId = partShopId;
    }

    public List<ListServiceBean> getListService() {
        return listService;
    }

    public void setListService(List<ListServiceBean> listService) {
        this.listService = listService;
    }

    public static class ListServiceBean implements Serializable{
        /**
         * updateDate : 2018-01-23 00:12:53
         * isNewRecord : false
         * delFlag : 0
         * type : 0
         * serviceHour : 1
         * partTypeId : 655dd1e31719416893ec6f7c07eedeae,7df1f58bb6744203b2deed59d0bd3e7e
         * price : 180.00
         * name : 刹车深度养护-更换刹车油+
         * id : 78914fa4939042fd96f6d99f25767701
         * remarks :
         * selected :
         * createDate : 2018-01-20 12:46:59
         * status : 1
         */

        private String updateDate;
        private boolean isNewRecord;
        private String delFlag;
        private String type;
        private String serviceHour;
        private String partTypeId;
        private String price;
        private String name;
        private String id;
        private String remarks;
        private String selected;
        private String createDate;
        private String status;
        private boolean select;
        private boolean isExpand = true;
        private boolean isLoad;

        public boolean isLoad() {
            return isLoad;
        }

        public void setLoad(boolean load) {
            isLoad = load;
        }

        private List<ProductSelectBean> productSelect;

        public List<ProductSelectBean> getProductSelect() {
            return productSelect;
        }

        public void setProductSelect(List<ProductSelectBean> productSelect) {
            this.productSelect = productSelect;
        }

        public boolean isExpand() {
            return isExpand;
        }

        public void setExpand(boolean expand) {
            isExpand = expand;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }

        public boolean isNewRecord() {
            return isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
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

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getSelected() {
            return selected;
        }

        public void setSelected(String selected) {
            this.selected = selected;
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
