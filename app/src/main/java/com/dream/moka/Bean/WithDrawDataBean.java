package com.dream.moka.Bean;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public class WithDrawDataBean {

    /**
     * accountId : 9d86b6ee40a545b39453bb3c5ee01dfc
     * bankNo : 123456758888888
     * withdrawalsAmount : 0.00
     * bankCardId : 43a4895580d74d0582218d37a9afe07c
     */

    private String accountId;
    private String bankNo;
    private String withdrawalsAmount;
    private String bankCardId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getWithdrawalsAmount() {
        return withdrawalsAmount;
    }

    public void setWithdrawalsAmount(String withdrawalsAmount) {
        this.withdrawalsAmount = withdrawalsAmount;
    }

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }
}
