package com.dream.moka.Bean.Message;

import com.dream.moka.Bean.ListCouponBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5 0005.
 */
public class IncreaceBundle implements Serializable{
    private String add_time;
    private String orderMoney;
    private String carNo;
    private String carName;
    private String repairShopName;
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    private List<ListCouponBean> listCoupon;

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getRepairShopName() {
        return repairShopName;
    }

    public void setRepairShopName(String repairShopName) {
        this.repairShopName = repairShopName;
    }

    public List<ListCouponBean> getListCoupon() {
        return listCoupon;
    }

    public void setListCoupon(List<ListCouponBean> listCoupon) {
        this.listCoupon = listCoupon;
    }
}
