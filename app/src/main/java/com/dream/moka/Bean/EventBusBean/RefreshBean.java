package com.dream.moka.Bean.EventBusBean;

/**
 * Created by Administrator on 2017/10/12 0012.
 */
public class RefreshBean {
    String eventStr;
    public RefreshBean(String eventStr){
        this.eventStr = eventStr;
    }
    public RefreshBean(){

    }
    public String getEventStr() {
        return eventStr;
    }

    public void setEventStr(String eventStr) {
        this.eventStr = eventStr;
    }
}
