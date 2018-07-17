package com.dream.moka.Bean.EventBusBean;

/**
 * Created by Administrator on 2017/8/17 0017.
 */
public class FinishBus {
    String eventStr;

    public FinishBus(String eventStr){
        this.eventStr = eventStr;
    }
    public String getEventStr() {
        return eventStr;
    }

    public void setEventStr(String eventStr) {
        this.eventStr = eventStr;
    }
}
