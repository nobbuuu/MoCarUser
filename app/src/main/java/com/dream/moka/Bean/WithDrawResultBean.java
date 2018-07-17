package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class WithDrawResultBean {
    /**
     * total : 1
     * startItem : 0
     * totalPage : 1
     * pageSize : 20
     * list : [{"amount":"0.01","updateDate":"2018-01-02 16:24:32","transChannel":2,"userDetailId":"fbbd18ec4be94758b0bf9a366cdcb85e","isNewRecord":false,"delFlag":"0","transNo":"","bankCardId":"123456","serviceCharge":"","transType":1,"id":"fe0f124d85214c7094bac670fa9b9eeb","remarks":"","createDate":"2018-01-02 16:24:32","status":0}]
     * pageNum : 1
     */

    private int total;
    private int startItem;
    private int totalPage;
    private int pageSize;
    private int pageNum;
    private List<ListBean> list;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getStartItem() {
        return startItem;
    }

    public void setStartItem(int startItem) {
        this.startItem = startItem;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
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
         * amount : 0.01
         * updateDate : 2018-01-02 16:24:32
         * transChannel : 2
         * userDetailId : fbbd18ec4be94758b0bf9a366cdcb85e
         * isNewRecord : false
         * delFlag : 0
         * transNo :
         * bankCardId : 123456
         * serviceCharge :
         * transType : 1
         * id : fe0f124d85214c7094bac670fa9b9eeb
         * remarks :
         * createDate : 2018-01-02 16:24:32
         * status : 0
         */

        private String amount;
        private String updateDate;
        private int transChannel;
        private String userDetailId;
        private boolean isNewRecord;
        private String delFlag;
        private String transNo;
        private String bankCardId;
        private String serviceCharge;
        private String transType;
        private String id;
        private String remarks;
        private String createDate;
        private int status;

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

        public int getTransChannel() {
            return transChannel;
        }

        public void setTransChannel(int transChannel) {
            this.transChannel = transChannel;
        }

        public String getUserDetailId() {
            return userDetailId;
        }

        public void setUserDetailId(String userDetailId) {
            this.userDetailId = userDetailId;
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

        public String getTransNo() {
            return transNo;
        }

        public void setTransNo(String transNo) {
            this.transNo = transNo;
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

        public String getTransType() {
            return transType;
        }

        public void setTransType(String transType) {
            this.transType = transType;
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
}
