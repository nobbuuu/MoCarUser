package com.dream.moka.Bean;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class WalletBean {

    /**
     * id : 1
     * balance : 0.00
     * transAmount : 0.00
     * drawAmount : 0.00
     * status : 0
     * incomeAmount : 0.00
     * isNewRecord : false
     * remarks :
     * createDate :
     * withdrawalsAmount : 0.00
     * updateDate :
     * delFlag : 0
     */

    private String id;
    private String balance;
    private String transAmount;
    private String drawAmount;
    private String status;
    private String incomeAmount;
    private boolean isNewRecord;
    private String remarks;
    private String createDate;
    private String withdrawalsAmount;
    private String updateDate;
    private String delFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(String transAmount) {
        this.transAmount = transAmount;
    }

    public String getDrawAmount() {
        return drawAmount;
    }

    public void setDrawAmount(String drawAmount) {
        this.drawAmount = drawAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(String incomeAmount) {
        this.incomeAmount = incomeAmount;
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

    public String getWithdrawalsAmount() {
        return withdrawalsAmount;
    }

    public void setWithdrawalsAmount(String withdrawalsAmount) {
        this.withdrawalsAmount = withdrawalsAmount;
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
}
