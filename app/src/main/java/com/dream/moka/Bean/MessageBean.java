package com.dream.moka.Bean;

/**
 * Created by Administrator on 2018/1/19 0019.
 */
public class MessageBean {
    /**
     * isitnew : 1
     * messageType : 2
     * tagId : fb4105161d9a4449b73a3e887f9039a7
     * type : 2
     */

    private String isitnew;
    private String messageType;
    private String tagId;
    private String type;
    private String status;
    private String title;
    private String eventStr;

    public String getEventStr() {
        return eventStr;
    }

    public void setEventStr(String eventStr) {
        this.eventStr = eventStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsitnew() {
        return isitnew;
    }

    public void setIsitnew(String isitnew) {
        this.isitnew = isitnew;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageBean{" +
                "isitnew='" + isitnew + '\'' +
                ", messageType='" + messageType + '\'' +
                ", tagId='" + tagId + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                ", eventStr='" + eventStr + '\'' +
                '}';
    }
}
