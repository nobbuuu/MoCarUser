package com.dream.moka.Bean;

/**
 * Created by Administrator on 2018/1/5 0005.
 */

public class DriverDataInfoResultBean {
    /**
     * orderIncome : 0.00
     * rebateIncome : 0.00
     * allIncome : 0.00
     */

    private String orderIncome;
    private String rebateIncome;
    private String allIncome;

    public String getOrderIncome() {
        return orderIncome;
    }

    public void setOrderIncome(String orderIncome) {
        this.orderIncome = orderIncome;
    }

    public String getRebateIncome() {
        return rebateIncome;
    }

    public void setRebateIncome(String rebateIncome) {
        this.rebateIncome = rebateIncome;
    }

    public String getAllIncome() {
        return allIncome;
    }

    public void setAllIncome(String allIncome) {
        this.allIncome = allIncome;
    }
}
