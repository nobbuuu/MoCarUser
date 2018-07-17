package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/29 0029.
 */
public class CarsLetterBean {
    private String letter;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    private List<CarBrandResultBean.ListBeanX.ListBean> carList;

    public List<CarBrandResultBean.ListBeanX.ListBean> getCarList() {
        return carList;
    }

    public void setCarList(List<CarBrandResultBean.ListBeanX.ListBean> carList) {
        this.carList = carList;
    }

}
