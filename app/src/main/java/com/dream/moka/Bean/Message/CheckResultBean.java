package com.dream.moka.Bean.Message;

import com.dream.moka.Bean.ListCouponBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/5 0005.
 */
public class CheckResultBean implements Serializable{

    /**
     * orderId : c38c1f7e47de4f0f8f60a3d07250b831
     * listCoupon : [{"amount":"50.00","updateDate":"2018-02-05 15:53:00","quantity":2,"effectFrom":"2018-02-05 15:52:54","carName":"","sendUserType":0,"isNewRecord":false,"delFlag":"0","carId":"","sendTime":"","sendUserId":"a188061836e844eaa3b55e6dcd898668","beginMiles":"","effectTo":"2018-02-28 15:52:57","endMiles":"","fromType":"0","couponType":4,"leastAmtUse":0,"name":"mmp","id":"1730f1484319408f87af9ec81c476148","carConfigureId":"","remarks":"","createDate":"2018-02-05 15:53:00","status":0},{"amount":"50.00","updateDate":"2018-02-05 15:53:00","quantity":2,"effectFrom":"2018-02-05 15:52:54","carName":"","sendUserType":0,"isNewRecord":false,"delFlag":"0","carId":"","sendTime":"","sendUserId":"a188061836e844eaa3b55e6dcd898668","beginMiles":"","effectTo":"2018-02-28 15:52:57","endMiles":"","fromType":"0","couponType":4,"leastAmtUse":0,"name":"mmp","id":"1730f1484319408f87af9ec81c476148","carConfigureId":"","remarks":"","createDate":"2018-02-05 15:53:00","status":0}]
     */

    private String orderId;
    private List<ListCouponBean> listCoupon;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ListCouponBean> getListCoupon() {
        return listCoupon;
    }

    public void setListCoupon(List<ListCouponBean> listCoupon) {
        this.listCoupon = listCoupon;
    }

}
