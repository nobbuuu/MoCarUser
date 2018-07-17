package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */

public class InComeResultBean {
    /**
     * total : 1
     * startItem : 0
     * totalPage : 1
     * pageSize : 20
     * list : [{"receiptPayment":"","amount":"12.00","updateDate":"","orderId":"","userDetailId":"fbbd18ec4be94758b0bf9a366cdcb85e","id":"1","isNewRecord":false,"paymentsType":"","delFlag":"0","remarks":"","createDate":"2017-12-18 16:05:24","status":0}]
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
         * receiptPayment :
         * amount : 12.00
         * updateDate :
         * orderId :
         * userDetailId : fbbd18ec4be94758b0bf9a366cdcb85e
         * id : 1
         * isNewRecord : false
         * paymentsType :
         * delFlag : 0
         * remarks :
         * createDate : 2017-12-18 16:05:24
         * status : 0
         */

        private String receiptPayment;
        private String amount;
        private String updateDate;
        private String orderId;
        private String userDetailId;
        private String id;
        private boolean isNewRecord;
        private String paymentsType;
        private String delFlag;
        private String remarks;
        private String createDate;
        private String status;

        public String getReceiptPayment() {
            return receiptPayment;
        }

        public void setReceiptPayment(String receiptPayment) {
            this.receiptPayment = receiptPayment;
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

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getUserDetailId() {
            return userDetailId;
        }

        public void setUserDetailId(String userDetailId) {
            this.userDetailId = userDetailId;
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

        public String getPaymentsType() {
            return paymentsType;
        }

        public void setPaymentsType(String paymentsType) {
            this.paymentsType = paymentsType;
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
}
