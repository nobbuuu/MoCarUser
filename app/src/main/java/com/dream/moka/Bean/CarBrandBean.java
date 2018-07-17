package com.dream.moka.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20 0020.
 * 将原数组处理过之后的数据源实体类
 */

public class CarBrandBean {
    private String title;
    private List<ResultBean> mBeanList;

    public List<ResultBean> getBeanList() {
        return mBeanList;
    }

    public void setBeanList(List<ResultBean> beanList) {
        mBeanList = beanList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class ResultBean{
        private String carLogo;
        private String name;
        private String id;

        public String getCarLogo() {
            return carLogo;
        }

        public void setCarLogo(String carLogo) {
            this.carLogo = carLogo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }


}
