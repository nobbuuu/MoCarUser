package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/31 0031.
 */
public class CityDataBean {
    private String letter;

    private List<CityItemBean> cityItemBeanList;

    public List<CityItemBean> getCityItemBeanList() {
        return cityItemBeanList;
    }

    public void setCityItemBeanList(List<CityItemBean> cityItemBeanList) {
        this.cityItemBeanList = cityItemBeanList;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }
    public static class CityItemBean{
        private String cityName;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
    }
}
