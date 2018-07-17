package com.dream.moka.Bean.EventBusBean;

/**
 * Created by Administrator on 2017/10/12 0012.
 */
public class RefreshMsgBean {
    String eventStr;

    public RefreshMsgBean(String eventStr){
        this.eventStr = eventStr;
    }
    public String getEventStr() {
        return eventStr;
    }

    public void setEventStr(String eventStr) {
        this.eventStr = eventStr;
    }
}
