package com.dream.moka.Bean.Other;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class UserGuijiBean {

    /**
     * latitude : 22.6828820000
     * longtitude : 113.9598650000
     * list : [[{"latitude":"22.6866510000","longtitude":"113.9521240000"},{"latitude":"22.6866410000","longtitude":"113.9523440000"},{"latitude":"22.6866360000","longtitude":"113.9525750000"},{"latitude":"22.6866560000","longtitude":"113.9528220000"},{"latitude":"22.6866360000","longtitude":"113.9530790000"},{"latitude":"22.6866410000","longtitude":"113.9534440000"},{"latitude":"22.6866660000","longtitude":"113.9536960000"},{"latitude":"22.6866710000","longtitude":"113.9540930000"},{"latitude":"22.6866810000","longtitude":"113.9544360000"},{"latitude":"22.6866810000","longtitude":"113.9549670000"},{"latitude":"22.6866950000","longtitude":"113.9554500000"},{"latitude":"22.6866950000","longtitude":"113.9559380000"},{"latitude":"22.6866660000","longtitude":"113.9566890000"},{"latitude":"22.6864480000","longtitude":"113.9573280000"},{"latitude":"22.6863240000","longtitude":"113.9576010000"},{"latitude":"22.6861610000","longtitude":"113.9579280000"},{"latitude":"22.6860470000","longtitude":"113.9582070000"},{"latitude":"22.6858740000","longtitude":"113.9585560000"},{"latitude":"22.6856810000","longtitude":"113.9589750000"},{"latitude":"22.6854680000","longtitude":"113.9593020000"},{"latitude":"22.6852300000","longtitude":"113.9595160000"},{"latitude":"22.6849670000","longtitude":"113.9597090000"},{"latitude":"22.6848240000","longtitude":"113.9597520000"},{"latitude":"22.6845410000","longtitude":"113.9598110000"},{"latitude":"22.6842540000","longtitude":"113.9598220000"},{"latitude":"22.6838780000","longtitude":"113.9598380000"},{"latitude":"22.6836000000","longtitude":"113.9598220000"},{"latitude":"22.6833870000","longtitude":"113.9598330000"},{"latitude":"22.6831000000","longtitude":"113.9598440000"},{"latitude":"22.6828820000","longtitude":"113.9598650000"}]]
     */

    private String latitude;
    private String longtitude;
    private List<List<GuijiBean>> list;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public List<List<GuijiBean>> getList() {
        return list;
    }

    public void setList(List<List<GuijiBean>> list) {
        this.list = list;
    }

}
