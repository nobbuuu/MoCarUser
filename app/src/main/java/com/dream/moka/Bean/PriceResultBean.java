package com.dream.moka.Bean;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class PriceResultBean {

    /**
     * rechargeAmt : 6.00
     * driverDeposit : 500.00
     */

    private String rechargeAmt;
    private String driverDeposit;
    private String averageTime;

    public String getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(String averageTime) {
        this.averageTime = averageTime;
    }

    public String getRechargeAmt() {
        return rechargeAmt;
    }

    public void setRechargeAmt(String rechargeAmt) {
        this.rechargeAmt = rechargeAmt;
    }

    public String getDriverDeposit() {
        return driverDeposit;
    }

    public void setDriverDeposit(String driverDeposit) {
        this.driverDeposit = driverDeposit;
    }
}
