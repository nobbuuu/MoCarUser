package com.dream.moka.Bean.EventBusBean;

/**
 * Created by Administrator on 2018/1/17 0017.
 */

public class ChooseOrderBean {
    private String status="";
    private String orderCode="";
    private String beginDate="";
    private String endDate="";

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
