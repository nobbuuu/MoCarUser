package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/16 0016.
 */

public class MyAccountBean {
    /**
     * startItem : 0
     * total : 6
     * pageSize : 20
     * list : [{"phone":"","status":-1,"userTypes":"","userDetailId":"1412bb1cca6f448a9d978b14987492d0","bankName":"","remarks":"","transNo":"","updateDate":"2018-01-23 17:59:16","kAmount":"","amount":"0.01","id":"201801231759161278rw","transType":0,"nickName":"","isNewRecord":false,"transChannel":0,"createDate":"2018-01-23 17:59:16","bankCardId":"","serviceCharge":"","delFlag":"0"},{"phone":"","status":1,"userTypes":"","userDetailId":"1412bb1cca6f448a9d978b14987492d0","bankName":"","remarks":"","transNo":"2018012321001004160233887600","updateDate":"2018-01-23 17:57:29","kAmount":"","amount":"0.01","id":"201801231757227210rw","transType":0,"nickName":"","isNewRecord":false,"transChannel":0,"createDate":"2018-01-23 17:57:23","bankCardId":"","serviceCharge":"","delFlag":"0"},{"phone":"","status":1,"userTypes":"","userDetailId":"1412bb1cca6f448a9d978b14987492d0","bankName":"","remarks":"","transNo":"2018012321001004160231738647","updateDate":"2018-01-23 17:56:01","kAmount":"","amount":"0.01","id":"201801231755530246rw","transType":0,"nickName":"","isNewRecord":false,"transChannel":0,"createDate":"2018-01-23 17:55:54","bankCardId":"","serviceCharge":"","delFlag":"0"},{"phone":"","status":1,"userTypes":"","userDetailId":"1412bb1cca6f448a9d978b14987492d0","bankName":"","remarks":"","transNo":"2018012321001004160231618341","updateDate":"2018-01-23 17:54:33","kAmount":"","amount":"0.01","id":"201801231754258095rw","transType":0,"nickName":"","isNewRecord":false,"transChannel":0,"createDate":"2018-01-23 17:54:26","bankCardId":"","serviceCharge":"","delFlag":"0"},{"phone":"","status":1,"userTypes":"","userDetailId":"1412bb1cca6f448a9d978b14987492d0","bankName":"","remarks":"","transNo":"2018012321001004160231738422","updateDate":"2018-01-23 17:49:45","kAmount":"","amount":"0.01","id":"201801231749379140rw","transType":0,"nickName":"","isNewRecord":false,"transChannel":0,"createDate":"2018-01-23 17:49:38","bankCardId":"","serviceCharge":"","delFlag":"0"},{"phone":"","status":1,"userTypes":"","userDetailId":"1412bb1cca6f448a9d978b14987492d0","bankName":"","remarks":"","transNo":"2018012321001004160232883614","updateDate":"2018-01-23 17:28:22","kAmount":"","amount":"0.01","id":"201801231727335178rw","transType":0,"nickName":"","isNewRecord":false,"transChannel":0,"createDate":"2018-01-23 17:27:33","bankCardId":"","serviceCharge":"","delFlag":"0"}]
     * totalPage : 1
     * pageNum : 1
     */

    private int startItem;
    private int total;
    private int pageSize;
    private int totalPage;
    private int pageNum;
    private List<ListBean> list;

    public int getStartItem() {
        return startItem;
    }

    public void setStartItem(int startItem) {
        this.startItem = startItem;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * phone :
         * status : -1
         * userTypes :
         * userDetailId : 1412bb1cca6f448a9d978b14987492d0
         * bankName :
         * remarks :
         * transNo :
         * updateDate : 2018-01-23 17:59:16
         * kAmount :
         * amount : 0.01
         * id : 201801231759161278rw
         * transType : 0
         * nickName :
         * isNewRecord : false
         * transChannel : 0
         * createDate : 2018-01-23 17:59:16
         * bankCardId :
         * serviceCharge :
         * delFlag : 0
         */

        private String phone;
        private String status;
        private String userTypes;
        private String userDetailId;
        private String bankName;
        private String remarks;
        private String transNo;
        private String updateDate;
        private String kAmount;
        private String amount;
        private String id;
        private String transType;
        private String nickName;
        private boolean isNewRecord;
        private String transChannel;
        private String createDate;
        private String bankCardId;
        private String serviceCharge;
        private String delFlag;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUserTypes() {
            return userTypes;
        }

        public void setUserTypes(String userTypes) {
            this.userTypes = userTypes;
        }

        public String getUserDetailId() {
            return userDetailId;
        }

        public void setUserDetailId(String userDetailId) {
            this.userDetailId = userDetailId;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getTransNo() {
            return transNo;
        }

        public void setTransNo(String transNo) {
            this.transNo = transNo;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getKAmount() {
            return kAmount;
        }

        public void setKAmount(String kAmount) {
            this.kAmount = kAmount;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getTransChannel() {
            return transChannel;
        }

        public void setTransChannel(String transChannel) {
            this.transChannel = transChannel;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getBankCardId() {
            return bankCardId;
        }

        public void setBankCardId(String bankCardId) {
            this.bankCardId = bankCardId;
        }

        public String getServiceCharge() {
            return serviceCharge;
        }

        public void setServiceCharge(String serviceCharge) {
            this.serviceCharge = serviceCharge;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }
    }
}
